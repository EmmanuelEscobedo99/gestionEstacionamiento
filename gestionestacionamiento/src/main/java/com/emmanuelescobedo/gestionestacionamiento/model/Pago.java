package com.emmanuelescobedo.gestionestacionamiento.model;

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
    private Enum metodoPago;

    @OneToOne
    @JoinColumn(name = "entrada_salida_id")
    private EntradaSalida entradaSalida;

}
