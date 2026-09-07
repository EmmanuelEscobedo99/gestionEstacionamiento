package com.emmanuelescobedo.gestionestacionamiento.repository;

import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Long> {

    @Query("""
        SELECT
            COUNT(p),
            SUM(p.monto),
            AVG(p.monto),
            MAX(p.monto),
            MIN(p.monto)
        FROM Pago p
    """)
    List<Object[]> obtenerEstadisticas();

}
