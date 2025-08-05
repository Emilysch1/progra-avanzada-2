package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.repositories.ClienteRepository;
import com.programacion.avanzada.servicios.interfaces.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    protected ClienteServiceImpl() {
        this(null);
    }

    @Inject
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findOptionalBy(id);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
