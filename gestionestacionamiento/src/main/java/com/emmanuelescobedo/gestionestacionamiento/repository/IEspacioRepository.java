package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspacioRepository extends JpaRepository<Espacio, Long> {
}
