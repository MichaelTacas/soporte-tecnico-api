package com.idat.soportetecnico.service.impl;

import com.idat.soportetecnico.exception.RecursoNoEncontradoException;
import com.idat.soportetecnico.model.EstadoSolicitud;
import com.idat.soportetecnico.model.Prioridad;
import com.idat.soportetecnico.model.Solicitud;
import com.idat.soportetecnico.service.SolicitudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final Map<Long, Solicitud> solicitudes = new HashMap<>();

    private Long contadorId = 1L;

    @Override
    public Solicitud registrar(Solicitud solicitud) {

        solicitud.setId(contadorId++);
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);

        solicitudes.put(solicitud.getId(), solicitud);

        return solicitud;
    }

    @Override
    public List<Solicitud> listar() {
        return new ArrayList<>(solicitudes.values());
    }

    @Override
    public Solicitud buscarPorId(Long id) {

        Solicitud solicitud = solicitudes.get(id);

        if (solicitud == null) {
            throw new RecursoNoEncontradoException(
                    "Solicitud no encontrada"
            );
        }

        return solicitud;
    }

    @Override
    public Solicitud actualizar(Long id, Solicitud solicitud) {

        Solicitud solicitudExistente = buscarPorId(id);

        solicitudExistente.setTitulo(solicitud.getTitulo());
        solicitudExistente.setDescripcion(solicitud.getDescripcion());
        solicitudExistente.setPrioridad(solicitud.getPrioridad());
        solicitudExistente.setEstado(solicitud.getEstado());
        solicitudExistente.setCliente(solicitud.getCliente());
        solicitudExistente.setTecnico(solicitud.getTecnico());

        solicitudes.put(id, solicitudExistente);

        return solicitudExistente;
    }

    @Override
    public void eliminar(Long id) {

        buscarPorId(id);

        solicitudes.remove(id);
    }

    @Override
    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {

        return solicitudes.values()
                .stream()
                .filter(solicitud -> solicitud.getEstado() == estado)
                .toList();
    }

    @Override
    public List<Solicitud> buscarPorPrioridad(Prioridad prioridad) {

        return solicitudes.values()
                .stream()
                .filter(solicitud -> solicitud.getPrioridad() == prioridad)
                .toList();
    }
}