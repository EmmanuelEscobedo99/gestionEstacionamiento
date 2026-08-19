package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
