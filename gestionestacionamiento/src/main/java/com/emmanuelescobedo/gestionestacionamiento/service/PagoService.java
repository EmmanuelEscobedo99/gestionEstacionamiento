package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.dto.EstadisticasPagoDTO;
import com.emmanuelescobedo.gestionestacionamiento.model.Pago;
import com.emmanuelescobedo.gestionestacionamiento.repository.IPagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService implements IPagoService{

    private final IPagoRepository pagoRepo;


    public PagoService(IPagoRepository pagoRepo) {
        this.pagoRepo = pagoRepo;
    }

    @Override
    public List<Pago> traerPago() {
        return pagoRepo.findAll().stream()
                .filter(p -> !p.isEliminado())
                .collect(Collectors.toList());
    }

    @Override
    public EstadisticasPagoDTO obtenerEstadisticas() {
        List<Object[]> filas = pagoRepo.obtenerEstadisticas();

        if (filas == null || filas.isEmpty()) {
            return new EstadisticasPagoDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        Object[] r = filas.get(0);
        return new EstadisticasPagoDTO(
                ((Number) r[0]).longValue(),
                ((Number) r[1]).doubleValue(),
                ((Number) r[2]).doubleValue(),
                ((Number) r[3]).doubleValue(),
                ((Number) r[4]).doubleValue()
        );
    }

    @Override
    public Pago buscarPago(Long codePago) {
        return pagoRepo.findById(codePago).orElse(null);
    }

    @Override
    public Pago crearPago(Pago pago) {
        if (pago == null){
            return null;
        }

        return pagoRepo.save(pago);
    }

    @Override
    public Pago editarPago(Long codePago, Pago pago) {
        Pago pagoEditar = buscarPago(codePago);

        if (pagoEditar == null || pago == null){
            return null;
        }

        pagoEditar.setMonto(pago.getMonto());
        pagoEditar.setFechaPago(pago.getFechaPago());
        pagoEditar.setMetodoPago(pago.getMetodoPago());
        if (pago.getEntradaSalida() != null) {
            pagoEditar.setEntradaSalida(pago.getEntradaSalida());
        }

        return pagoRepo.save(pagoEditar);
    }

    @Override
    public boolean eliminarPago(Long codePago) {
        Pago pagoEliminar = buscarPago(codePago);

        if (pagoEliminar == null){
            return false;
        }

        pagoEliminar.setEliminado(true);
        pagoRepo.save(pagoEliminar);
        return true;
    }
}
