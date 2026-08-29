package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.Vehiculo;
import com.emmanuelescobedo.gestionestacionamiento.repository.IVehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService{

    private final IVehiculoRepository vehiRepo;

    public VehiculoService(IVehiculoRepository vehiRepo) {
        this.vehiRepo = vehiRepo;
    }

    @Override
    public List<Vehiculo> traerVehiculos() {
        return vehiRepo.findAll();
    }

    @Override
    public Vehiculo buscarVehiculos(Long codeVehiculo) {
        return vehiRepo.findById(codeVehiculo).orElse(null);
    }

    @Override
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null){
            return null;
        }

        return vehiRepo.save(vehiculo);
    }

    @Override
    public Vehiculo editarVehiculo(Long codeVehiculo, Vehiculo vehiculo) {
        Vehiculo vehiculoEditar = buscarVehiculos(codeVehiculo);

        if (vehiculoEditar == null || vehiculo == null){
            return null;
        }

        vehiculoEditar.setColor(vehiculo.getColor());
        vehiculoEditar.setPlacas(vehiculo.getPlacas());
        vehiculoEditar.setMarca(vehiculo.getMarca());
        vehiculoEditar.setModelo(vehiculo.getModelo());
        vehiculoEditar.setTipo(vehiculo.getTipo());
        if (vehiculo.getUsuario() != null) {
            vehiculoEditar.setUsuario(vehiculo.getUsuario());
        }

        return vehiRepo.save(vehiculoEditar);
    }

    @Override
    public boolean eliminarVehiculo(Long codeVehiculo) {
        Vehiculo vehiculoEliminar = buscarVehiculos(codeVehiculo);
        if (vehiculoEliminar == null){
            return false;
        }

        vehiRepo.delete(vehiculoEliminar);
        return true;
    }
}
