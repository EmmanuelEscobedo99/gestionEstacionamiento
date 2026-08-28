package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Vehiculo;
import com.emmanuelescobedo.gestionestacionamiento.service.IVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    private final IVehiculoService vehiServ;

    public VehiculoController(IVehiculoService vehiServ) {
        this.vehiServ = vehiServ;
    }

    //READ
    @GetMapping
    public List<Vehiculo> traerVehiculos(){
        return vehiServ.traerVehiculos();
    }

    //READ vehiculo especifico
    @GetMapping("/{codeVehiculo}")
    public ResponseEntity<?>buscarVehiculo(@PathVariable Long codeVehiculo){
        Vehiculo vehiculoBuscar = vehiServ.buscarVehiculos(codeVehiculo);

        if (vehiculoBuscar == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar el vehiculo.");
        }

        return ResponseEntity.ok(vehiculoBuscar);
    }
    //CREATE
    @PostMapping
    public ResponseEntity<?>crearVehiculo(@RequestBody Vehiculo vehiculo){
        Vehiculo vehiculoCrear = vehiServ.crearVehiculo(vehiculo);

        if (vehiculoCrear == null){
            return ResponseEntity.badRequest()
                    .body("Los datos ingresados son invalidos.");
        }

        return ResponseEntity.ok(vehiculoCrear);
    }
    //UPDATE
    @PutMapping("/{codeVehiculo}")
    public ResponseEntity<?>editarVehiculo(@PathVariable Long codeVehiculo, @RequestBody Vehiculo vehiculo){
        Vehiculo vehiculoEditar = vehiServ.editarVehiculo(codeVehiculo, vehiculo);

        if (vehiculoEditar == null){
            return ResponseEntity.badRequest()
                    .body("Los datos para editar el vehiculo son invalidos.");
        }

        return ResponseEntity.ok(vehiculoEditar);
    }
    //DELETE
    @DeleteMapping("/{codeVehiculo}")
    public ResponseEntity<?>eliminarVehiculo(@PathVariable Long codeVehiculo){
        boolean vehiculoEliminar = vehiServ.eliminarVehiculo(codeVehiculo);

        if (vehiculoEliminar == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No es posible eliminar el vehiculo o no existe.");
        }

        vehiServ.eliminarVehiculo(codeVehiculo);
        return ResponseEntity.ok("El vehiculo ha sido eliminado.");
    }
}
