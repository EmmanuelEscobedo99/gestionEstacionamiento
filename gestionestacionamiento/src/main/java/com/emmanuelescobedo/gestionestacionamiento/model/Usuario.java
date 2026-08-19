package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private Enum rol;
    private LocalDateTime fechaRegistro;
}
