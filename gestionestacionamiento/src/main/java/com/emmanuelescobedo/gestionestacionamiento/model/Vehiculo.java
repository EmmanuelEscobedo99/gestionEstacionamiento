package com.emmanuelescobedo.gestionestacionamiento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("vehiculos")
    private Usuario usuario;

    @OneToMany(mappedBy = "vehiculo")
    @JsonIgnoreProperties("vehiculo")
    private List<EntradaSalida> entradasSalidas;

    public Vehiculo() {
    }

    public Vehiculo(Long codeVehiculo, List<EntradaSalida> entradasSalidas, Usuario usuario, String tipo, String color, String modelo, String marca, String placas) {
        this.codeVehiculo = codeVehiculo;
        this.entradasSalidas = entradasSalidas;
        this.usuario = usuario;
        this.tipo = tipo;
        this.color = color;
        this.modelo = modelo;
        this.marca = marca;
        this.placas = placas;
    }

    public Long getCodeVehiculo() {
        return codeVehiculo;
    }

    public void setCodeVehiculo(Long codeVehiculo) {
        this.codeVehiculo = codeVehiculo;
    }

    public List<EntradaSalida> getEntradasSalidas() {
        return entradasSalidas;
    }

    public void setEntradasSalidas(List<EntradaSalida> entradasSalidas) {
        this.entradasSalidas = entradasSalidas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }
}
