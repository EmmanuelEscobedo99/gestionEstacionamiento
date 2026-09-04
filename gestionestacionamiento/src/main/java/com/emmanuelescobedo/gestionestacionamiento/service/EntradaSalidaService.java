package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;
import com.emmanuelescobedo.gestionestacionamiento.model.Espacio;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEntradaSalidaRepository;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEspacioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaSalidaService implements IEntradaSalidaService{

    private final IEntradaSalidaRepository entrRepo;
    private final IEspacioRepository espaRepo;

    public EntradaSalidaService(IEntradaSalidaRepository entrRepo, IEspacioRepository espaRepo) {
        this.entrRepo = entrRepo;
        this.espaRepo = espaRepo;
    }

    private void sincronizarEspacio(EntradaSalida entradaSalida) {
        if (entradaSalida.getEspacio() == null || entradaSalida.getEspacio().getCodeEspacio() == null) {
            return;
        }

        Espacio espacio = espaRepo.findById(entradaSalida.getEspacio().getCodeEspacio()).orElse(null);
        if (espacio == null) {
            return;
        }

        boolean libre = entradaSalida.getFechaSalida() != null;
        espacio.setDisponible(libre);
        espaRepo.save(espacio);
    }

    @Override
    public List<EntradaSalida> traerEntradaSalida() {
        return entrRepo.findAll();
    }

    @Override
    public EntradaSalida buscarEntradaSalida(Long codeEntradaSalida) {
        return entrRepo.findById(codeEntradaSalida).orElse(null);
    }

    @Override
    public EntradaSalida crearEntradaSalida(EntradaSalida entradaSalida) {
        if (entradaSalida == null) {
            return null;
        }

        EntradaSalida guardada = entrRepo.save(entradaSalida);
        sincronizarEspacio(entradaSalida);

        return guardada;
    }

    @Override
    public EntradaSalida editarEntradaSalida(Long codeEntradaSalida, EntradaSalida entradaSalida) {
        EntradaSalida entradaSalidaEditar = buscarEntradaSalida(codeEntradaSalida);

        if (entradaSalidaEditar == null || entradaSalida == null){
            return null;
        }

        entradaSalidaEditar.setPago(entradaSalida.getPago());
        entradaSalidaEditar.setEspacio(entradaSalida.getEspacio());
        entradaSalidaEditar.setVehiculo(entradaSalida.getVehiculo());
        entradaSalidaEditar.setEstado(entradaSalida.getEstado());
        entradaSalidaEditar.setTotalPagar(entradaSalida.getTotalPagar());
        entradaSalidaEditar.setHorasConsumidas(entradaSalida.getHorasConsumidas());
        entradaSalidaEditar.setFechaSalida(entradaSalida.getFechaSalida());
        entradaSalidaEditar.setFechaEntrada(entradaSalida.getFechaEntrada());

        EntradaSalida guardada = entrRepo.save(entradaSalidaEditar);
        sincronizarEspacio(entradaSalidaEditar);

        return guardada;
    }

    @Override
    public boolean eliminarEntradaSalida(Long codeEntradaSalida) {
        EntradaSalida entradaSalida = buscarEntradaSalida(codeEntradaSalida);

        if (entradaSalida == null){
            return false;
        }

        entrRepo.delete(entradaSalida);
        return true;
    }
}
