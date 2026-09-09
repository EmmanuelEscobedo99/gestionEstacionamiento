package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Rol;
import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.model.Vehiculo;
import com.emmanuelescobedo.gestionestacionamiento.service.IUsuarioService;
import com.emmanuelescobedo.gestionestacionamiento.service.IVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    private final IVehiculoService vehiServ;
    private final IUsuarioService usuServ;

    public VehiculoController(IVehiculoService vehiServ, IUsuarioService usuServ) {
        this.vehiServ = vehiServ;
        this.usuServ = usuServ;
    }

    //READ
    @GetMapping
    public List<Vehiculo> traerVehiculos(@AuthenticationPrincipal UserDetails user){
        List<Vehiculo> vehiculos = vehiServ.traerVehiculos();

        if (user != null){
            Usuario usuario = usuServ.buscarPorEmail(user.getUsername());
            if (usuario != null && usuario.getRol() == Rol.CLIENTE){
                return vehiculos.stream()
                        .filter(v -> v.getUsuario() != null
                                && v.getUsuario().getCodeUsuario().equals(usuario.getCodeUsuario()))
                        .collect(Collectors.toList());
            }
        }

        return vehiculos;
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

        return ResponseEntity.ok("El vehiculo ha sido eliminado.");
    }
}
