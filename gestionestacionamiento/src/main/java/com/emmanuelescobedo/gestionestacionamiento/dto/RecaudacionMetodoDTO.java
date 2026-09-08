package com.emmanuelescobedo.gestionestacionamiento.dto;

public record RecaudacionMetodoDTO(
        String metodoPago,
        Long cantidadPagos,
        Double totalRecaudado
) {
}