package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEspacioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioService implements IEspacioService{

    private final IEspacioRepository espaRepo;

    public EspacioService(IEspacioRepository espaRepo) {
        this.espaRepo = espaRepo;
    }

    @Override
    public List<Espacio> traerEspacios() {
        return espaRepo.findAll();
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

        if(espacioEditar == null) {
            return null;
        }

        espacioEditar.setNumero(espacio.getNumero());
        espacioEditar.setTipo(espacio.getTipo());
        espacioEditar.setDisponible(espacio.isDisponible());

        return espaRepo.save(espacioEditar);
    }

    @Override
    public boolean eliminarEspacio(Long codeEspacio) {

        Espacio espacioEliminar = buscarEspacio(codeEspacio);

        if (espacioEliminar == null){
            return false;
        }

        espaRepo.delete(espacioEliminar);
        return true;
    }
}
