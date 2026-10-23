package com.idat.soportetecnico.controller;

import com.idat.soportetecnico.dto.SolicitudRequest;
import com.idat.soportetecnico.dto.SolicitudResponse;
import com.idat.soportetecnico.model.Cliente;
import com.idat.soportetecnico.model.EstadoSolicitud;
import com.idat.soportetecnico.model.Prioridad;
import com.idat.soportetecnico.model.Solicitud;
import com.idat.soportetecnico.model.Tecnico;
import com.idat.soportetecnico.service.ClienteService;
import com.idat.soportetecnico.service.SolicitudService;
import com.idat.soportetecnico.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;
    private final ClienteService clienteService;
    private final TecnicoService tecnicoService;

    public SolicitudController(
            SolicitudService solicitudService,
            ClienteService clienteService,
            TecnicoService tecnicoService) {

        this.solicitudService = solicitudService;
        this.clienteService = clienteService;
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    public ResponseEntity<SolicitudResponse> registrar(
            @Valid @RequestBody SolicitudRequest request) {

        Cliente cliente = clienteService.buscarPorId(
                request.getClienteId()
        );

        Tecnico tecnico = null;

        if (request.getTecnicoId() != null) {
            tecnico = tecnicoService.buscarPorId(
                    request.getTecnicoId()
            );
        }

        Solicitud solicitud = new Solicitud();

        solicitud.setTitulo(request.getTitulo());
        solicitud.setDescripcion(request.getDescripcion());
        solicitud.setPrioridad(request.getPrioridad());
        solicitud.setCliente(cliente);
        solicitud.setTecnico(tecnico);

        Solicitud nuevaSolicitud =
                solicitudService.registrar(solicitud);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertirAResponse(nuevaSolicitud));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudResponse>> listar() {

        List<SolicitudResponse> respuestas =
                solicitudService.listar()
                        .stream()
                        .map(this::convertirAResponse)
                        .toList();

        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponse> buscarPorId(
            @PathVariable Long id) {

        Solicitud solicitud =
                solicitudService.buscarPorId(id);

        return ResponseEntity.ok(
                convertirAResponse(solicitud)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudRequest request) {

        Solicitud solicitudExistente =
                solicitudService.buscarPorId(id);

        Cliente cliente =
                clienteService.buscarPorId(
                        request.getClienteId()
                );

        Tecnico tecnico = null;

        if (request.getTecnicoId() != null) {
            tecnico = tecnicoService.buscarPorId(
                    request.getTecnicoId()
            );
        }

        Solicitud solicitud = new Solicitud();

        solicitud.setTitulo(request.getTitulo());
        solicitud.setDescripcion(request.getDescripcion());
        solicitud.setPrioridad(request.getPrioridad());

        // Conservamos el estado que ya tenía
        solicitud.setEstado(
                solicitudExistente.getEstado()
        );

        solicitud.setCliente(cliente);
        solicitud.setTecnico(tecnico);

        Solicitud actualizada =
                solicitudService.actualizar(id, solicitud);

        return ResponseEntity.ok(
                convertirAResponse(actualizada)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        solicitudService.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SolicitudResponse>> buscarPorEstado(
            @PathVariable EstadoSolicitud estado) {

        List<SolicitudResponse> respuestas =
                solicitudService.buscarPorEstado(estado)
                        .stream()
                        .map(this::convertirAResponse)
                        .toList();

        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<List<SolicitudResponse>> buscarPorPrioridad(
            @PathVariable Prioridad prioridad) {

        List<SolicitudResponse> respuestas =
                solicitudService.buscarPorPrioridad(prioridad)
                        .stream()
                        .map(this::convertirAResponse)
                        .toList();

        return ResponseEntity.ok(respuestas);
    }

    private SolicitudResponse convertirAResponse(
            Solicitud solicitud) {

        SolicitudResponse response =
                new SolicitudResponse();

        response.setId(solicitud.getId());
        response.setTitulo(solicitud.getTitulo());
        response.setDescripcion(
                solicitud.getDescripcion()
        );
        response.setPrioridad(
                solicitud.getPrioridad()
        );
        response.setEstado(
                solicitud.getEstado()
        );

        if (solicitud.getCliente() != null) {

            response.setClienteId(
                    solicitud.getCliente().getId()
            );

            response.setClienteNombre(
                    solicitud.getCliente().getNombre()
            );
        }

        if (solicitud.getTecnico() != null) {

            response.setTecnicoId(
                    solicitud.getTecnico().getId()
            );

            response.setTecnicoNombre(
                    solicitud.getTecnico().getNombre()
            );
        }

        return response;
    }
}