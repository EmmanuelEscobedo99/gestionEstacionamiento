package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;
import com.emmanuelescobedo.gestionestacionamiento.service.IEntradaSalidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradaSalida")
public class EntradaSalidaController {

    private final IEntradaSalidaService entrServ;

    public EntradaSalidaController(IEntradaSalidaService entrServ) {
        this.entrServ = entrServ;
    }

    //READ
    @GetMapping
    public List<EntradaSalida>traerEntradaSalida(){
        return entrServ.traerEntradaSalida();
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

        entrServ.eliminarEntradaSalida(codeEntradaSalida);
        return ResponseEntity.ok("La EntradaSalida ha sido eliminada.");
    }
}
