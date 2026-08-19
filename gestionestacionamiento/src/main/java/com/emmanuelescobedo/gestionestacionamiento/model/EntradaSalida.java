package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class EntradaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEntradaSalida;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private BigDecimal horasConsumidas;
    private BigDecimal totalPagar;
    private Enum estado;
}
