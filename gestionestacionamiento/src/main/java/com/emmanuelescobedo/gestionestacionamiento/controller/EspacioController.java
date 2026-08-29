package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;
import com.emmanuelescobedo.gestionestacionamiento.service.IEspacioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacio")
public class EspacioController {

    private final IEspacioService espaServ;

    public EspacioController(IEspacioService espaServ) {
        this.espaServ = espaServ;
    }

    //READ
    @GetMapping
    public List<Espacio> traerEspacios(){
        return espaServ.traerEspacios();
    }

    //READ espacio especifico
    @GetMapping("/{codeEspacio}")
    public ResponseEntity<?> buscarEspacio(@PathVariable Long codeEspacio) {
        Espacio espacioBuscar = espaServ.buscarEspacio(codeEspacio);

        if (espacioBuscar == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar el espacio o no existe.");
        }

        return ResponseEntity.ok(espacioBuscar);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<?>crearEspacio(@RequestBody Espacio espacio){
        Espacio espacioCrear = espaServ.crearEspacio(espacio);
        if(espacioCrear == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(espacioCrear);
    }

    //UPDATE
    @PutMapping("/{codeEspacio}")
    public ResponseEntity<?>editarEspacio(@PathVariable Long codeEspacio, @RequestBody Espacio espacio){
        Espacio espacioEditar = espaServ.editarEspacio(codeEspacio, espacio);

        if (espacioEditar == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos para editar el espacio son invalidos.");
        }

        return ResponseEntity.ok(espacioEditar);
    }

    //DELETE
    @DeleteMapping("/{codeEspacio}")
    public ResponseEntity<?>eliminarEspacio(@PathVariable Long codeEspacio) {
        boolean espacioEliminar = espaServ.eliminarEspacio(codeEspacio);
        if (espacioEliminar == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede eliminar el espacio.");
        }

        return ResponseEntity.ok("El espacio ha sido eliminado");
    }
}
