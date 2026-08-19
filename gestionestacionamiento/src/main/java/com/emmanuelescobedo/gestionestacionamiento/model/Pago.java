package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codePago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private Enum metodoPago;

}
