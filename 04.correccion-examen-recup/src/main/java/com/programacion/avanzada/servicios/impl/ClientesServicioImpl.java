package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.servicios.interfaces.ClientesServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@ApplicationScoped
public class ClientesServicioImpl implements ClientesServicio {

    final ClienteRepository clienteRepository;

    @Inject
    public ClientesServicioImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Future<Optional<Cliente>> findById(Integer id) {
        //version 1
        var future = new CompletableFuture<Optional<Cliente>>();

        CompletableFuture.runAsync(() -> {
            var cliente = clienteRepository.findById(id);

            future.complete(cliente);
        });

        return future;
    }
}
