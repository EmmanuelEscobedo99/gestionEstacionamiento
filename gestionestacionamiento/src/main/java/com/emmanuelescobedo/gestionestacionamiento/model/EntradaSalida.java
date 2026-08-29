package com.emmanuelescobedo.gestionestacionamiento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class EntradaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEntradaSalida;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private BigDecimal horasConsumidas;
    private BigDecimal totalPagar;
    @Enumerated(EnumType.STRING)
    private EstadoEntrada estado;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonIgnoreProperties("entradasSalidas")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "espacio_id")
    @JsonIgnoreProperties("entradasSalidas")
    private Espacio espacio;

    @OneToOne(mappedBy = "entradaSalida")
    @JsonIgnoreProperties("entradaSalida")
    private Pago pago;

    public EntradaSalida() {
    }

    public EntradaSalida(Long codeEntradaSalida, Pago pago, Espacio espacio, Vehiculo vehiculo, EstadoEntrada estado, BigDecimal totalPagar, BigDecimal horasConsumidas, LocalDateTime fechaSalida, LocalDateTime fechaEntrada) {
        this.codeEntradaSalida = codeEntradaSalida;
        this.pago = pago;
        this.espacio = espacio;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.totalPagar = totalPagar;
        this.horasConsumidas = horasConsumidas;
        this.fechaSalida = fechaSalida;
        this.fechaEntrada = fechaEntrada;
    }

    public Long getCodeEntradaSalida() {
        return codeEntradaSalida;
    }

    public void setCodeEntradaSalida(Long codeEntradaSalida) {
        this.codeEntradaSalida = codeEntradaSalida;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EstadoEntrada getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntrada estado) {
        this.estado = estado;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public BigDecimal getHorasConsumidas() {
        return horasConsumidas;
    }

    public void setHorasConsumidas(BigDecimal horasConsumidas) {
        this.horasConsumidas = horasConsumidas;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
