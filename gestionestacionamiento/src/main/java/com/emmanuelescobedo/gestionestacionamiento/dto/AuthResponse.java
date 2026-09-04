package com.emmanuelescobedo.gestionestacionamiento.dto;

import com.emmanuelescobedo.gestionestacionamiento.model.Rol;

public record AuthResponse(
        String token,
        String email,
        Rol rol,
        Long codeUsuario
) {
}
