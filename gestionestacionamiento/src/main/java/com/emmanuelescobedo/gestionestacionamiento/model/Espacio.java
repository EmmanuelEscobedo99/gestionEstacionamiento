package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEspacio;
    private String numero;
    private Enum tipo;
    private boolean disponible;
}
