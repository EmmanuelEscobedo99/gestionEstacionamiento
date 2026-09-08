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

    @Query(value = """
    SELECT metodo_pago, COUNT(*) AS cantidad_pagos, SUM(monto) AS total_recaudado
    FROM pago
    GROUP BY metodo_pago
    ORDER BY total_recaudado DESC
    """, nativeQuery = true)
    List<Object[]> recaudacionPorMetodoPago();

    @Query(value = """
    SELECT CAST(fecha_pago AS DATE) AS fecha, COUNT(*) AS pagos, SUM(monto) AS total
    FROM pago
    GROUP BY CAST(fecha_pago AS DATE)
    ORDER BY fecha DESC
    """, nativeQuery = true)
    List<Object[]> recaudacionPorDia();

    @Query(value = """
    SELECT EXTRACT(YEAR FROM fecha_pago) AS anio,
           EXTRACT(MONTH FROM fecha_pago) AS mes,
           COUNT(*) AS pagos,
           SUM(monto) AS total_recaudado
    FROM pago
    GROUP BY EXTRACT(YEAR FROM fecha_pago), EXTRACT(MONTH FROM fecha_pago)
    ORDER BY anio DESC, mes DESC
    """, nativeQuery = true)
    List<Object[]> recaudacionPorMes();

}
