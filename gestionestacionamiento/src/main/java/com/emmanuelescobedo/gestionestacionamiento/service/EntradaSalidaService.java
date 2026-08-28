package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.EntradaSalida;
import com.emmanuelescobedo.gestionestacionamiento.repository.IEntradaSalidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaSalidaService implements IEntradaSalidaService{

    private final IEntradaSalidaRepository entrRepo;

    public EntradaSalidaService(IEntradaSalidaRepository entrRepo) {
        this.entrRepo = entrRepo;
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

        return entrRepo.save(entradaSalida);
    }

    @Override
    public EntradaSalida editarEntradaSalida(Long codeEntradaSalida, EntradaSalida entradaSalida) {
        EntradaSalida entradaSalidaEditar = buscarEntradaSalida(codeEntradaSalida);

        if (entradaSalida == null){
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

        return entrRepo.save(entradaSalidaEditar);
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
