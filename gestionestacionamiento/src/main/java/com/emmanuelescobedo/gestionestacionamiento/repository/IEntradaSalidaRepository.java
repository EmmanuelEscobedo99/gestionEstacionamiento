package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEntradaSalidaRepository extends JpaRepository<EntradaSalida, Long> {
}
