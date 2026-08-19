package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

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
    @OneToMany(mappedBy = "estacionamiento")
    private List<Espacio> espacios;
}
