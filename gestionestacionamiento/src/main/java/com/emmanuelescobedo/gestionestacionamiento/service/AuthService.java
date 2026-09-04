package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.config.JwtUtil;
import com.emmanuelescobedo.gestionestacionamiento.dto.AuthResponse;
import com.emmanuelescobedo.gestionestacionamiento.dto.LoginRequest;
import com.emmanuelescobedo.gestionestacionamiento.dto.RegisterRequest;
import com.emmanuelescobedo.gestionestacionamiento.model.Rol;
import com.emmanuelescobedo.gestionestacionamiento.model.Usuario;
import com.emmanuelescobedo.gestionestacionamiento.repository.IUsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final IUsuarioRepository usuaRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthService(
            IUsuarioRepository usuaRepo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            CustomUserDetailsService customUserDetailsService
    ) {
        this.usuaRepo = usuaRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (usuaRepo.existsByEmail(request.email())) {
            throw new RuntimeException("El email ya esta registrado: " + request.email());
        }

        Rol rol = request.rol() != null ? request.rol() : Rol.CLIENTE;

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setApellido(request.apellido());
        usuario.setEmail(request.email());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setTelefono(request.telefono());
        usuario.setRol(rol);
        usuario.setFechaRegistro(LocalDateTime.now());

        Usuario saved = usuaRepo.save(usuario);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(saved.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, saved.getEmail(), saved.getRol(), saved.getCodeUsuario());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        Usuario usuario = customUserDetailsService.loadUsuarioByEmail(request.email());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token, usuario.getEmail(), usuario.getRol(), usuario.getCodeUsuario());
    }
}
