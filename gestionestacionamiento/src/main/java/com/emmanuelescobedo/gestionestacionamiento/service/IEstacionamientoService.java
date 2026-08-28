package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Estacionamiento;

import java.util.List;

public interface IEstacionamientoService {

    //METODOS CRUD

    //READ
    List<Estacionamiento> traerEstacionamientos();
    Estacionamiento buscarEstacionamiento(Long codeEstacionamiento);

    //CREATE
    Estacionamiento crearEstacionamiento(Estacionamiento estacionamiento);

    //UPDATE
    Estacionamiento editarEstacionamiento(Long codeEstacionamiento, Estacionamiento estacionamiento);

    //DELETE
    boolean eliminarEstacionamiento(Long codeEstacionamiento);
}
