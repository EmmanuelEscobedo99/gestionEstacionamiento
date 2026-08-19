package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
