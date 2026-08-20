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

    public Espacio() {
    }

    public Espacio(Long codeEspacio, List<EntradaSalida> entradasSalidas, Estacionamiento estacionamiento, boolean disponible, Enum tipo, String numero) {
        this.codeEspacio = codeEspacio;
        this.entradasSalidas = entradasSalidas;
        this.estacionamiento = estacionamiento;
        this.disponible = disponible;
        this.tipo = tipo;
        this.numero = numero;
    }

    public Long getCodeEspacio() {
        return codeEspacio;
    }

    public void setCodeEspacio(Long codeEspacio) {
        this.codeEspacio = codeEspacio;
    }

    public List<EntradaSalida> getEntradasSalidas() {
        return entradasSalidas;
    }

    public void setEntradasSalidas(List<EntradaSalida> entradasSalidas) {
        this.entradasSalidas = entradasSalidas;
    }

    public Estacionamiento getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Estacionamiento estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
