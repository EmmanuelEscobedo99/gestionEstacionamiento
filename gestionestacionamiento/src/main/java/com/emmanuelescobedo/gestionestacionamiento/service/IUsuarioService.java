package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    // Metodos para el CRUD

    //READ
    List<Usuario> traerUsuarios();
    Usuario buscarUsuario(Long codeUsuario);

    //CREATE
    Usuario crearUsuario(Usuario usuario);

    //UPDATE
    Usuario editarUsuario(Long codeUsuario, Usuario usuario);

    //DELETE
    boolean eliminarUsuario(Long codeUsuario);
}
