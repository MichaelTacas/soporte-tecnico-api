package com.idat.soportetecnico.service.impl;

import com.idat.soportetecnico.model.Tecnico;
import com.idat.soportetecnico.service.TecnicoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final Map<Long, Tecnico> tecnicos = new HashMap<>();

    private Long contadorId = 1L;

    @Override
    public Tecnico registrar(Tecnico tecnico) {

        tecnico.setId(contadorId++);

        tecnicos.put(tecnico.getId(), tecnico);

        return tecnico;
    }

    @Override
    public List<Tecnico> listar() {

        return new ArrayList<>(tecnicos.values());
    }

    @Override
    public Tecnico buscarPorId(Long id) {

        Tecnico tecnico = tecnicos.get(id);

        if (tecnico == null) {
            throw new RuntimeException("Técnico no encontrado");
        }

        return tecnico;
    }

    @Override
    public Tecnico actualizar(Long id, Tecnico tecnico) {

        Tecnico tecnicoExistente = buscarPorId(id);

        tecnicoExistente.setNombre(tecnico.getNombre());
        tecnicoExistente.setEspecialidad(tecnico.getEspecialidad());
        tecnicoExistente.setDisponible(tecnico.isDisponible());

        tecnicos.put(id, tecnicoExistente);

        return tecnicoExistente;
    }

    @Override
    public void eliminar(Long id) {

        buscarPorId(id);

        tecnicos.remove(id);
    }

    @Override
    public List<Tecnico> listarDisponibles() {

        return tecnicos.values()
                .stream()
                .filter(Tecnico::isDisponible)
                .toList();
    }
}