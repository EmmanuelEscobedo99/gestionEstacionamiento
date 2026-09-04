package com.emmanuelescobedo.gestionestacionamiento.service;

import com.emmanuelescobedo.gestionestacionamiento.model.*;
import com.emmanuelescobedo.gestionestacionamiento.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @Mock
    private IEstacionamientoRepository estaRepo;
    @InjectMocks
    private EstacionamientoService estacionamientoService;

    @Mock
    private IEntradaSalidaRepository entrRepo;
    @InjectMocks
    private EntradaSalidaService entradaSalidaService;

    @Mock
    private IVehiculoRepository vehiRepo;
    @InjectMocks
    private VehiculoService vehiculoService;

    @Mock
    private IEspacioRepository espaRepo;
    @InjectMocks
    private EspacioService espacioService;

    @Mock
    private IPagoRepository pagoRepo;
    @InjectMocks
    private PagoService pagoService;

    @Mock
    private IUsuarioRepository usuaRepo;
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testEditarEstacionamiento_Success() {
        Estacionamiento existing = new Estacionamiento();
        existing.setCodeEstacionamiento(1L);
        existing.setNombre("Original");
        existing.setTarifaHora(BigDecimal.valueOf(10));

        Estacionamiento updated = new Estacionamiento();
        updated.setNombre("Actualizado");
        updated.setTarifaHora(BigDecimal.valueOf(20));

        when(estaRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(estaRepo.save(any(Estacionamiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Estacionamiento result = estacionamientoService.editarEstacionamiento(1L, updated);

        assertNotNull(result);
        assertEquals("Actualizado", result.getNombre());
        assertEquals(BigDecimal.valueOf(20), result.getTarifaHora());
        assertEquals(1L, result.getCodeEstacionamiento());
        verify(estaRepo).save(existing);
    }

    @Test
    void testEditarEstacionamiento_NotFound() {
        when(estaRepo.findById(99L)).thenReturn(Optional.empty());

        Estacionamiento result = estacionamientoService.editarEstacionamiento(99L, new Estacionamiento());
        assertNull(result);
    }

    @Test
    void testEditarEntradaSalida_NotFoundDoesNotNPE() {
        when(entrRepo.findById(99L)).thenReturn(Optional.empty());

        EntradaSalida result = entradaSalidaService.editarEntradaSalida(99L, new EntradaSalida());
        assertNull(result);
    }

    @Test
    void testEditarVehiculo_Success() {
        Vehiculo existing = new Vehiculo();
        existing.setCodeVehiculo(1L);
        existing.setColor("Rojo");

        Vehiculo updated = new Vehiculo();
        updated.setColor("Azul");
        updated.setMarca("Toyota");

        when(vehiRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(vehiRepo.save(any(Vehiculo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Vehiculo result = vehiculoService.editarVehiculo(1L, updated);

        assertNotNull(result);
        assertEquals("Azul", result.getColor());
        assertEquals("Toyota", result.getMarca());
    }

    @Test
    void testEspacioTipoGetterSetter() {
        Espacio espacio = new Espacio();
        espacio.setTipo(TipoEspacio.CAMIONETA);
        assertEquals(TipoEspacio.CAMIONETA, espacio.getTipo());
    }

    @Test
    void testUsuarioRol() {
        Usuario usuario = new Usuario();
        usuario.setRol(Rol.ADMIN);
        assertEquals(Rol.ADMIN, usuario.getRol());
    }

    @Test
    void testMetodoPagoAndEstadoEntrada() {
        Pago pago = new Pago();
        pago.setMetodoPago(MetodoPago.TARJETA_CREDITO);
        assertEquals(MetodoPago.TARJETA_CREDITO, pago.getMetodoPago());

        EntradaSalida es = new EntradaSalida();
        es.setEstado(EstadoEntrada.ACTIVO);
        assertEquals(EstadoEntrada.ACTIVO, es.getEstado());
    }
}
