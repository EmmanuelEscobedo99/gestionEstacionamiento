package com.emmanuelescobedo.gestionestacionamiento.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEspacio;
    private String numero;
    private Enum tipo;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name = "estacionamiento_id")
    private Estacionamiento estacionamiento;

    @OneToMany(mappedBy = "espacio")
    private List<EntradaSalida> entradasSalidas;
}
