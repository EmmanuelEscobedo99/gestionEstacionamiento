package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @OneToMany(mappedBy = "usuario")
    private List<Vehiculo> vehiculos;
}
