package com.emmanuelescobedo.gestionestacionamiento.controller;

import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuaServ;

    public UsuarioController(IUsuarioService usuaServ) {
        this.usuaServ = usuaServ;
    }

    //READ
    @GetMapping
    public List<Usuario> traerUsuarios() {
        return usuaServ.traerUsuarios();
    }

    //READ de usuario especifico
    @GetMapping("/{codeUsuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long codeUsuario) {
        Usuario usuarioBuscar = usuaServ.buscarUsuario(codeUsuario);

        if(usuarioBuscar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible encontrar el usuario con el id: " + codeUsuario);
        }

        return ResponseEntity.ok(usuarioBuscar);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {

        Usuario usuarioCreado = usuaServ.crearUsuario(usuario);

        if(usuarioCreado == null) {
            return ResponseEntity.badRequest()
                    .body("Datos invalidos para crear el usuario.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioCreado);
    }

    //UPDATE
    @PutMapping("/{codeUsuario}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long codeUsuario, @RequestBody Usuario usuario) {

        Usuario usuarioEditado = usuaServ.editarUsuario(codeUsuario, usuario);

        if(usuarioEditado == null) {
            return ResponseEntity.badRequest()
                    .body("No fue posible editar el usuario, los datos son invalidos.");
        }

        return ResponseEntity.ok(usuarioEditado);
    }

    //DELETE
    @DeleteMapping("/{codeUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long codeUsuario) {

        boolean usuarioEliminado = usuaServ.eliminarUsuario(codeUsuario);

        if(usuarioEliminado == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No fue posible eliminar el usuario o no fue encontrado.");
        }

        return ResponseEntity.ok("El usuario ha sido eliminado!");
    }
    
}
