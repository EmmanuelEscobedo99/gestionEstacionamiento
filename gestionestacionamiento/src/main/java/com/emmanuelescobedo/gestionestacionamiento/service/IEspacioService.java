package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;

import java.util.List;


public interface IEspacioService {

    //METODOS CRUD

    //READ
    public List<Espacio> traerEspacios();
    Espacio buscarEspacio(Long codeEspacio);

    //CREATE
    Espacio crearEspacio(Espacio espacio);

    //UPDATE
    Espacio editarEspacio(Long codeEspacio, Espacio espacio);

    //DELETE
    boolean eliminarEspacio(Long codeEspacio);
}
