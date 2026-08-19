package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeVehiculo;
    private String placas;
    private String marca;
    private String modelo;
    private String color;
    private String tipo;
}
