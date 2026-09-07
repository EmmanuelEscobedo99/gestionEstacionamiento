package com.emmanuelescobedo.gestionestacionamiento.dto;

public record EstadisticasPagoDTO(
        Long totalPagos,
        Double totalRecaudado,
        Double promedioPago,
        Double pagoMayor,
        Double pagoMenor
) {
}
