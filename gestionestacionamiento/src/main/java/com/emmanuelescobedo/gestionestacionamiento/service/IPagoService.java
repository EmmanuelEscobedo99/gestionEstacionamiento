package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.dto.EstadisticasPagoDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionDiariaDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionMensualDTO;
import com.emmanuelescobedo.gestionestacionamiento.dto.RecaudacionMetodoDTO;
import com.emmanuelescobedo.gestionestacionamiento.model.Pago;

import java.util.List;

public interface IPagoService {

    //CRUD

    //READ
    List<Pago>traerPago();
    EstadisticasPagoDTO obtenerEstadisticas();
    List<RecaudacionDiariaDTO> obtenerRecaudacionDiaria();
    List<RecaudacionMensualDTO> obtenerRecaudacionMensual();
    List<RecaudacionMetodoDTO> obtenerRecaudacionMetodo();
    Pago buscarPago(Long codePago);
    //CREATE
    Pago crearPago(Pago pago);
    //UPDATE
    Pago editarPago(Long codePago, Pago pago);
    //DELETE
    boolean eliminarPago(Long codePago);
}
