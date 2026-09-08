package com.emmanuelescobedo.gestionestacionamiento.dto;

public record RecaudacionMensualDTO(
        Integer anio,
        Integer mes,
        Long pagos,
        Double totalRecaudado
) {
}