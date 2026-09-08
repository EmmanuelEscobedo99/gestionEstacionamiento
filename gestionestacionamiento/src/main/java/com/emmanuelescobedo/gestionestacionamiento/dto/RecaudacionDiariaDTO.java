package com.emmanuelescobedo.gestionestacionamiento.dto;

public record RecaudacionDiariaDTO(
        String fecha,
        Long pagos,
        Double total
) {
}