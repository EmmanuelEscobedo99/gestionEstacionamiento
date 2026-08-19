package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepository extends JpaRepository<Pago, Long> {
}
