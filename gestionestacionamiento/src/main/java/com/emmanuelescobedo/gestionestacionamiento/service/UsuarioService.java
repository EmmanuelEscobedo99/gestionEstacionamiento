package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    private final IUsuarioRepository usuaRepo;

    public UsuarioService(IUsuarioRepository usuaRepo) {
        this.usuaRepo = usuaRepo;
    }

    @Override
    public List<Usuario> traerUsuarios() {
        return usuaRepo.findAll();
    }

    @Override
    public Usuario buscarUsuario(Long codeUsuario) {
        return usuaRepo.findById(codeUsuario).orElse(null);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        if(usuario == null) {
            return null;
        }

        return usuaRepo.save(usuario);
    }

    @Override
    public Usuario editarUsuario(Long codeUsuario, Usuario usuario) {

        Usuario usuarioEditar = buscarUsuario(codeUsuario);

        if(usuarioEditar == null) {
            return null;
        }

        usuarioEditar.setNombre(usuario.getNombre());
        usuarioEditar.setApellido(usuario.getApellido());
        usuarioEditar.setEmail(usuario.getEmail());
        usuarioEditar.setPassword(usuario.getPassword());
        usuarioEditar.setTelefono(usuario.getTelefono());
        usuarioEditar.setRol(usuario.getRol());
        usuarioEditar.setFechaRegistro(usuario.getFechaRegistro());

        return usuaRepo.save(usuarioEditar);
    }

    @Override
    public boolean eliminarUsuario(Long codeUsuario) {

        Usuario usuarioEliminar = buscarUsuario(codeUsuario);

        if(usuarioEliminar == null) {
            return false;
        }

        usuaRepo.delete(usuarioEliminar);
        return true;
    }
}
