package com.emmanuelescobedo.gestionestacionamiento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Espacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEspacio;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoEspacio tipo;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "estacionamiento_id")
    @JsonIgnoreProperties("espacios")
    private Estacionamiento estacionamiento;

    @OneToMany(mappedBy = "espacio")
    @JsonIgnoreProperties("espacio")
    private List<EntradaSalida> entradasSalidas;

    public Espacio() {
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

    public TipoEspacio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEspacio tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
