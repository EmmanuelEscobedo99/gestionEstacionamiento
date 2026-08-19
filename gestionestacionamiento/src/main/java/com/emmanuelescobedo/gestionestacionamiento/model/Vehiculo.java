package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.*;

import java.util.List;

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
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "vehiculo")
    private List<EntradaSalida> entradasSalidas;
}
