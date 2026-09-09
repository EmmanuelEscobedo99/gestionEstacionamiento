package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.dto.QrConsultaRequest;
import com.emmanuelescobedo.gestionestacionamiento.dto.QrPagoRequest;
import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;
import com.emmanuelescobedo.gestionestacionamiento.model.MetodoPago;
import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import com.emmanuelescobedo.gestionestacionamiento.model.Rol;
import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.service.IEntradaSalidaService;
import com.emmanuelescobedo.gestionestacionamiento.service.IPagoMovilService;
import com.emmanuelescobedo.gestionestacionamiento.service.IUsuarioService;
import com.emmanuelescobedo.gestionestacionamiento.service.IWalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entradaSalida")
public class EntradaSalidaController {

    private final IEntradaSalidaService entrServ;
    private final IPagoMovilService pagoMovilServ;
    private final IWalletService walletServ;
    private final IUsuarioService usuServ;

    public EntradaSalidaController(IEntradaSalidaService entrServ, IPagoMovilService pagoMovilServ,
                                   IWalletService walletServ, IUsuarioService usuServ) {
        this.entrServ = entrServ;
        this.pagoMovilServ = pagoMovilServ;
        this.walletServ = walletServ;
        this.usuServ = usuServ;
    }

    //READ
    @GetMapping
    public List<EntradaSalida>traerEntradaSalida(@AuthenticationPrincipal UserDetails user){
        List<EntradaSalida> entradas = entrServ.traerEntradaSalida();

        if (user != null){
            Usuario usuario = usuServ.buscarPorEmail(user.getUsername());
            if (usuario != null && usuario.getRol() == Rol.CLIENTE){
                return entradas.stream()
                        .filter(e -> e.getVehiculo() != null
                                && e.getVehiculo().getUsuario() != null
                                && e.getVehiculo().getUsuario().getCodeUsuario()
                                        .equals(usuario.getCodeUsuario()))
                        .collect(Collectors.toList());
            }
        }

        return entradas;
    }

    //READ elemento especifico
    @GetMapping("/{codeEntradaSalida}")
    public ResponseEntity<?>buscarEntradaSalida(@PathVariable Long codeEntradaSalida){
        EntradaSalida entradaSalidaBuscar = entrServ.buscarEntradaSalida(codeEntradaSalida);

        if (entradaSalidaBuscar == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar la EntradaSalida.");
        }

        return ResponseEntity.ok(entradaSalidaBuscar);
    }

    //READ por codigo QR
    @PostMapping("/buscar-por-qr")
    public ResponseEntity<?>buscarPorQr(@RequestBody QrConsultaRequest request){
        if (request == null || request.codigoQr() == null || request.codigoQr().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Los datos ingresados son invalidos.");
        }

        EntradaSalida entradaSalida = pagoMovilServ.buscarPorCodigoQr(request.codigoQr());

        if (entradaSalida == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar una entrada con ese codigo QR.");
        }

        return ResponseEntity.ok(entradaSalida);
    }

    //Pago del cliente con monedero (solo el propietario del vehiculo)
    @PostMapping("/{codeEntradaSalida}/pagar-con-monedero")
    public ResponseEntity<?>pagarConMonedero(@PathVariable Long codeEntradaSalida,
                                             @AuthenticationPrincipal UserDetails user){
        EntradaSalida entradaSalida = entrServ.buscarEntradaSalida(codeEntradaSalida);

        if (entradaSalida == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar la EntradaSalida.");
        }

        if (entradaSalida.getPago() != null){
            return ResponseEntity.badRequest().body("Esta estancia ya fue cobrada.");
        }

        if (entradaSalida.getVehiculo() == null || entradaSalida.getVehiculo().getUsuario() == null
                || !entradaSalida.getVehiculo().getUsuario().getEmail().equalsIgnoreCase(user.getUsername())){
            return ResponseEntity.badRequest().body("Solo el propietario del vehiculo puede pagar con monedero.");
        }

        Usuario cliente = usuServ.buscarPorEmail(user.getUsername());
        if (cliente == null){
            return ResponseEntity.badRequest().body("No fue posible identificar al usuario.");
        }

        BigDecimal monto = pagoMovilServ.calcularTotal(entradaSalida);
        BigDecimal saldo = walletServ.saldoDe(cliente);

        if (saldo.compareTo(monto) < 0){
            return ResponseEntity.badRequest()
                    .body("Saldo insuficiente. Tu saldo es $" + saldo + " y el monto es $" + monto + ".");
        }

        Pago pago = pagoMovilServ.registrarPago(entradaSalida, monto, MetodoPago.MONEDERO);
        if (pago == null){
            return ResponseEntity.badRequest().body("No fue posible registrar el pago.");
        }

        walletServ.cobrar(cliente, monto);

        return ResponseEntity.ok(pago);
    }

    //Pago del empleado usando el codigo QR de la entrada
    @PostMapping("/pagar-por-qr")
    public ResponseEntity<?>pagarPorQr(@RequestBody QrPagoRequest request){
        if (request == null || request.codigoQr() == null || request.codigoQr().trim().isEmpty()){
            return ResponseEntity.badRequest().body("Los datos ingresados son invalidos.");
        }

        EntradaSalida entradaSalida = pagoMovilServ.buscarPorCodigoQr(request.codigoQr());

        if (entradaSalida == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar una entrada con ese codigo QR.");
        }

        if (entradaSalida.getPago() != null){
            return ResponseEntity.badRequest().body("Esta estancia ya fue cobrada.");
        }

        MetodoPago metodo = request.metodoPago();

        if (metodo == MetodoPago.MONEDERO){
            if (entradaSalida.getVehiculo() == null || entradaSalida.getVehiculo().getUsuario() == null){
                return ResponseEntity.badRequest().body("El vehiculo no pertenece a un cliente con monedero.");
            }

            Usuario propietario = entradaSalida.getVehiculo().getUsuario();
            BigDecimal monto = request.monto() != null ? request.monto() : pagoMovilServ.calcularTotal(entradaSalida);
            BigDecimal saldo = walletServ.saldoDe(propietario);

            if (saldo.compareTo(monto) < 0){
                return ResponseEntity.badRequest()
                        .body("El cliente no tiene saldo suficiente. Su saldo es $" + saldo + " y el monto es $" + monto + ".");
            }

            Pago pago = pagoMovilServ.registrarPago(entradaSalida, monto, MetodoPago.MONEDERO);
            if (pago == null){
                return ResponseEntity.badRequest().body("No fue posible registrar el pago.");
            }

            walletServ.cobrar(propietario, monto);

            return ResponseEntity.ok(pago);
        }

        if (metodo == null){
            return ResponseEntity.badRequest().body("Selecciona un metodo de pago.");
        }

        BigDecimal monto = request.monto() != null ? request.monto() : pagoMovilServ.calcularTotal(entradaSalida);
        Pago pago = pagoMovilServ.registrarPago(entradaSalida, monto, metodo);

        if (pago == null){
            return ResponseEntity.badRequest().body("No fue posible registrar el pago.");
        }

        return ResponseEntity.ok(pago);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<?>crearEntradaSalida(@RequestBody EntradaSalida entradaSalida){
        EntradaSalida entradaSalidaCrear = entrServ.crearEntradaSalida(entradaSalida);

        if (entradaSalidaCrear == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(entradaSalidaCrear);
    }

    //UPDATE
    @PutMapping("/{codeEntradaSalida}")
    public ResponseEntity<?>editarEntradaSalida(@PathVariable Long codeEntradaSalida, @RequestBody EntradaSalida entradaSalida){
        EntradaSalida entradaSalidaEditar = entrServ.editarEntradaSalida(codeEntradaSalida, entradaSalida);

        if (entradaSalidaEditar == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(entradaSalidaEditar);
    }

    //DELETE
    @DeleteMapping("/{codeEntradaSalida}")
    public ResponseEntity<?>eliminarEntradaSalida(@PathVariable Long codeEntradaSalida){
        boolean entradaSalidaEliminar = entrServ.eliminarEntradaSalida(codeEntradaSalida);

        if (entradaSalidaEliminar == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar o no existe EntradaSalida deseada.");
        }

        return ResponseEntity.ok("La EntradaSalida ha sido eliminada.");
    }
}
