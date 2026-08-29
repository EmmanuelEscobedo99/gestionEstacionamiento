package com.emmanuelescobedo.gestionestacionamiento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codePago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @OneToOne
    @JoinColumn(name = "entrada_salida_id")
    @JsonIgnoreProperties("pago")
    private EntradaSalida entradaSalida;

    public Pago() {
    }

    public Pago(Long codePago, EntradaSalida entradaSalida, MetodoPago metodoPago, LocalDateTime fechaPago, BigDecimal monto) {
        this.codePago = codePago;
        this.entradaSalida = entradaSalida;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Long getCodePago() {
        return codePago;
    }

    public void setCodePago(Long codePago) {
        this.codePago = codePago;
    }

    public EntradaSalida getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(EntradaSalida entradaSalida) {
        this.entradaSalida = entradaSalida;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
