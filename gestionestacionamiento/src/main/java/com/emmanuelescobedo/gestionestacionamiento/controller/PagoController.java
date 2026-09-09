package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.dto.EstadisticasPagoDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionDiariaDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionMensualDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionMetodoDTO;
import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import com.emmanuelescobedo.gestionestacionamiento.model.Rol;
import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.service.IPagoService;
import com.emmanuelescobedo.gestionestacionamiento.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final IPagoService pagoServ;
    private final IUsuarioService usuServ;

    public PagoController(IPagoService pagoServ, IUsuarioService usuServ) {
        this.pagoServ = pagoServ;
        this.usuServ = usuServ;
    }

    //READ
    @GetMapping
    public List<Pago>traerPago(@AuthenticationPrincipal UserDetails user){
        List<Pago> pagos = pagoServ.traerPago();

        if (user != null){
            Usuario usuario = usuServ.buscarPorEmail(user.getUsername());
            if (usuario != null && usuario.getRol() == Rol.CLIENTE){
                return pagos.stream()
                        .filter(p -> p.getEntradaSalida() != null
                                && p.getEntradaSalida().getVehiculo() != null
                                && p.getEntradaSalida().getVehiculo().getUsuario() != null
                                && p.getEntradaSalida().getVehiculo().getUsuario().getCodeUsuario()
                                        .equals(usuario.getCodeUsuario()))
                        .collect(Collectors.toList());
            }
        }

        return pagos;
    }
    //READ pago especifico
    @GetMapping("/{codePago}")
    public ResponseEntity<?>buscarPago(@PathVariable Long codePago){
        Pago pagoBuscar = pagoServ.buscarPago(codePago);

        if (pagoBuscar == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar el pago.");
        }

        return ResponseEntity.ok(pagoBuscar);
    }
    @GetMapping("/estadisticas")
    public EstadisticasPagoDTO obtenerEstadisticas() {
        return pagoServ.obtenerEstadisticas();
    }
    @GetMapping("/por-metodo")
    public List<RecaudacionMetodoDTO> recaudacionPorMetodo() {
        return pagoServ.obtenerRecaudacionMetodo();
    }
    @GetMapping("/por-dia")
    public List<RecaudacionDiariaDTO> recaudacionPorDia() {
        return pagoServ.obtenerRecaudacionDiaria();
    }
    @GetMapping("/por-mes")
    public List<RecaudacionMensualDTO> recaudacionPorMes() {
        return pagoServ.obtenerRecaudacionMensual();
    }
    //CREATE
    @PostMapping
    public ResponseEntity<?>crearPago(@RequestBody Pago pago){
        Pago pagoCrear = pagoServ.crearPago(pago);

        if (pagoCrear == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(pagoCrear);
    }
    //UPDATE
    @PutMapping("/{codePago}")
    public ResponseEntity<?>editarPago(@PathVariable Long codePago, @RequestBody Pago pago){
        Pago pagoEditar = pagoServ.editarPago(codePago, pago);

        if (pagoEditar == null){
            return ResponseEntity.badRequest()
                    .body("Datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(pagoEditar);
    }
    //DELETE
    @DeleteMapping("/{codePago}")
    public ResponseEntity<?>eliminarPago(@PathVariable Long codePago){
        boolean pagoEliminar = pagoServ.eliminarPago(codePago);

        if (pagoEliminar == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar o no existe el Pago.");
        }

        return ResponseEntity.ok("El Pago ha sido eliminado.");
    }
}
