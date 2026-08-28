package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Vehiculo;

import java.util.List;

public interface IVehiculoService {

    //CRUD

    //READ
    List<Vehiculo> traerVehiculos();
    Vehiculo buscarVehiculos(Long codeVehiculo);

    //CREATE
    Vehiculo crearVehiculo(Vehiculo vehiculo);

    //UPDATE
    Vehiculo editarVehiculo(Long codeVehiculo, Vehiculo vehiculo);

    //DELETE
    boolean eliminarVehiculo(Long codeVehiculo);
}
