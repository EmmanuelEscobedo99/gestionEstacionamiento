package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEstacionamiento;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Integer capacidadTotal;
    private BigDecimal tarifaHora;
    private boolean activo;
}
