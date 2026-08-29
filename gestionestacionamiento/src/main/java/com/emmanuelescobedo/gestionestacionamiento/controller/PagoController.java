package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import com.emmanuelescobedo.gestionestacionamiento.service.IPagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final IPagoService pagoServ;

    public PagoController(IPagoService pagoServ) {
        this.pagoServ = pagoServ;
    }

    //READ
    @GetMapping
    public List<Pago>traerPago(){
        return pagoServ.traerPago();
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
