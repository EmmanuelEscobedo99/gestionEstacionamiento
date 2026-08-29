package com.emmanuelescobedo.gestionestacionamiento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private LocalDateTime fechaRegistro;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private List<Vehiculo> vehiculos;

    public Usuario() {
    }

    public Usuario(List<Vehiculo> vehiculos, LocalDateTime fechaRegistro, Rol rol, String telefono, String password, String email, String apellido, String nombre, Long codeUsuario) {
        this.vehiculos = vehiculos;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.telefono = telefono;
        this.password = password;
        this.email = email;
        this.apellido = apellido;
        this.nombre = nombre;
        this.codeUsuario = codeUsuario;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodeUsuario() {
        return codeUsuario;
    }

    public void setCodeUsuario(Long codeUsuario) {
        this.codeUsuario = codeUsuario;
    }
}
