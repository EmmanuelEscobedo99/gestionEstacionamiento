package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Pago;

import java.util.List;

public interface IPagoService {

    //CRUD

    //READ
    List<Pago>traerPago();
    Pago buscarPago(Long codePago);
    //CREATE
    Pago crearPago(Pago pago);
    //UPDATE
    Pago editarPago(Long codePago, Pago pago);
    //DELETE
    boolean eliminarPago(Long codePago);
}
