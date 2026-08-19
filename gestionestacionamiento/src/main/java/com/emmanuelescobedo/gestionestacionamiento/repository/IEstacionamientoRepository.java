package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
}
