package com.emmanuelescobedo.gestionestacionamiento.model;

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
    private Enum estado;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "espacio_id")
    private Espacio espacio;

    @OneToOne(mappedBy = "entradaSalida")
    private Pago pago;
}
