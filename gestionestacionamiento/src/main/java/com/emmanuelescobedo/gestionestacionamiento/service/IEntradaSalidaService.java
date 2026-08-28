package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;

import java.util.List;

public interface IEntradaSalidaService {

    //CRUD

    //READ
    List<EntradaSalida> traerEntradaSalida();
    EntradaSalida buscarEntradaSalida(Long codeEntradaSalida);

    //CREATE
    EntradaSalida crearEntradaSalida(EntradaSalida entradaSalida);

    //UPDATE
    EntradaSalida editarEntradaSalida(Long codeEntradaSalida, EntradaSalida entradaSalida);

    //DELETE
    EntradaSalida eliminarEntradaSalida(Long codeEntradaSalida);
}
