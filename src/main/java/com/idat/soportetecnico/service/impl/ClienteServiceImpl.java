package com.idat.soportetecnico.service.impl;

import com.idat.soportetecnico.exception.RecursoNoEncontradoException;
import com.idat.soportetecnico.model.Cliente;
import com.idat.soportetecnico.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final Map<Long, Cliente> clientes = new HashMap<>();

    private Long contadorId = 1L;

    @Override
    public Cliente registrar(Cliente cliente) {

        cliente.setId(contadorId++);

        clientes.put(cliente.getId(), cliente);

        return cliente;
    }

    @Override
    public List<Cliente> listar() {

        return new ArrayList<>(clientes.values());
    }

    @Override
    public Cliente buscarPorId(Long id) {

        Cliente cliente = clientes.get(id);

        if (cliente == null) {
            throw new RecursoNoEncontradoException(
                    "Cliente no encontrado"
            );
        }

        return cliente;
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {

        Cliente clienteExistente = buscarPorId(id);

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setEmpresa(cliente.getEmpresa());

        clientes.put(id, clienteExistente);

        return clienteExistente;
    }

    @Override
    public void eliminar(Long id) {

        buscarPorId(id);

        clientes.remove(id);
    }
}