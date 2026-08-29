package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Estacionamiento;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEstacionamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionamientoService implements IEstacionamientoService{

    private final IEstacionamientoRepository estaRepo;

    public EstacionamientoService(IEstacionamientoRepository estaRepo) {
        this.estaRepo = estaRepo;
    }

    @Override
    public List<Estacionamiento> traerEstacionamientos() {
        return estaRepo.findAll();
    }

    @Override
    public Estacionamiento buscarEstacionamiento(Long codeEstacionamiento) {
        return estaRepo.findById(codeEstacionamiento).orElse(null);
    }

    @Override
    public Estacionamiento crearEstacionamiento(Estacionamiento estacionamiento) {

        if(estacionamiento == null) {
            return null;
        }

        return estaRepo.save(estacionamiento);
    }

    @Override
    public Estacionamiento editarEstacionamiento(Long codeEstacionamiento, Estacionamiento estacionamiento) {
        Estacionamiento estacionamientoEditar = buscarEstacionamiento(codeEstacionamiento);

        if(estacionamientoEditar == null || estacionamiento == null) {
            return null;
        }

        estacionamientoEditar.setNombre(estacionamiento.getNombre());
        estacionamientoEditar.setDireccion(estacionamiento.getDireccion());
        estacionamientoEditar.setCiudad(estacionamiento.getCiudad());
        estacionamientoEditar.setCapacidadTotal(estacionamiento.getCapacidadTotal());
        estacionamientoEditar.setTarifaHora(estacionamiento.getTarifaHora());
        estacionamientoEditar.setActivo(estacionamiento.isActivo());

        return estaRepo.save(estacionamientoEditar);
    }

    @Override
    public boolean eliminarEstacionamiento(Long codeEstacionamiento) {
        Estacionamiento estacionamientoEliminar = buscarEstacionamiento(codeEstacionamiento);

        if (estacionamientoEliminar == null) {
            return false;
        }

        estaRepo.delete(estacionamientoEliminar);
        return true;
    }
}
