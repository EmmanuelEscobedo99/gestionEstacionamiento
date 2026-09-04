package com.emmanuelescobedo.gestionestacionamiento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser valido")
        String email,

        @NotBlank(message = "La contrasena es obligatoria")
        String password
) {
}
