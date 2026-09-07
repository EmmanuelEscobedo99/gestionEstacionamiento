package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEspacioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspacioService implements IEspacioService{

    private final IEspacioRepository espaRepo;

    public EspacioService(IEspacioRepository espaRepo) {
        this.espaRepo = espaRepo;
    }

    @Override
    public List<Espacio> traerEspacios() {
        return espaRepo.findAll().stream()
                .filter(e -> !e.isEliminado())
                .collect(Collectors.toList());
    }

    @Override
    public Espacio buscarEspacio(Long codeEspacio) {
        return espaRepo.findById(codeEspacio).orElse(null);
    }

    @Override
    public Espacio crearEspacio(Espacio espacio) {

        if(espacio == null) {
            return null;
        }

        return espaRepo.save(espacio);
    }

    @Override
    public Espacio editarEspacio(Long codeEspacio, Espacio espacio) {

        Espacio espacioEditar = buscarEspacio(codeEspacio);

        if(espacioEditar == null || espacio == null) {
            return null;
        }

        espacioEditar.setNumero(espacio.getNumero());
        espacioEditar.setTipo(espacio.getTipo());
        espacioEditar.setDisponible(espacio.isDisponible());
        if(espacio.getEstacionamiento() != null) {
            espacioEditar.setEstacionamiento(espacio.getEstacionamiento());
        }

        return espaRepo.save(espacioEditar);
    }

    @Override
    public boolean eliminarEspacio(Long codeEspacio) {

        Espacio espacioEliminar = buscarEspacio(codeEspacio);

        if (espacioEliminar == null){
            return false;
        }

        espacioEliminar.setEliminado(true);
        espaRepo.save(espacioEliminar);
        return true;
    }
}
