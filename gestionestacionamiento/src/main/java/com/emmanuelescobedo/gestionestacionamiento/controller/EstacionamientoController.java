package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Estacionamiento;
import com.emmanuelescobedo.gestionestacionamiento.service.IEstacionamientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacionamiento")
public class EstacionamientoController {

    private final IEstacionamientoService estaServ;

    public EstacionamientoController(IEstacionamientoService estaServ) {
        this.estaServ = estaServ;
    }

    //READ
    @GetMapping
    public List<Estacionamiento> traerEstacionamientos(){
        return estaServ.traerEstacionamientos();
    }

    //READ especifico
    @GetMapping("/{codeEstacionamiento}")
    public ResponseEntity<?> buscarEstacionamiento(@PathVariable Long codeEstacionamiento) {
        Estacionamiento estacionamientoBuscar = estaServ.buscarEstacionamiento(codeEstacionamiento);

        if(estacionamientoBuscar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encuentra el estacionamiento con id: " + codeEstacionamiento);
        }

        return ResponseEntity.ok(estacionamientoBuscar);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<?> crearEstacionamiento(@RequestBody Estacionamiento estacionamiento){

        Estacionamiento estacionamientoCrear = estaServ.crearEstacionamiento(estacionamiento);

        if(estacionamientoCrear == null) {
            return ResponseEntity.badRequest()
                    .body("Datos invalidos para la creacion del estacionamiento.");
        }

        return ResponseEntity.ok(estacionamientoCrear);
    }

    //UPDATE
    @PutMapping("/{codeEstacionamiento}")
    public ResponseEntity<?> editarEstacionamiento(@PathVariable Long codeEstacionamiento, @RequestBody Estacionamiento estacionamiento){

        Estacionamiento estacionamientoEditar = estaServ.editarEstacionamiento(codeEstacionamiento, estacionamiento);

        if(estacionamientoEditar == null){
            return ResponseEntity.badRequest()
                    .body("No fue posible editar el estacionamiento. Los datos son invalidos.");
        }

        return ResponseEntity.ok(estacionamientoEditar);
    }

    //DELETE
    @DeleteMapping("/{codeEstacionamiento}")
    public ResponseEntity<?> eliminarEstacionamiento(@PathVariable Long codeEstacionamiento){

        boolean estacionamientoEliminar = estaServ.eliminarEstacionamiento(codeEstacionamiento);

        if(estacionamientoEliminar == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible eliminar el estacionamiento!");
        }

        return ResponseEntity.ok("El estacionamiento ha sido eliminado.");
    }

}
