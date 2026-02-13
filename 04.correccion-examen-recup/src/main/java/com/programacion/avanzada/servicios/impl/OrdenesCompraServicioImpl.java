package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.repositorios.interfaces.OrdenCompraRepository;
import com.programacion.avanzada.servicios.interfaces.OrdenesCompraServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@ApplicationScoped
public class OrdenesCompraServicioImpl implements OrdenesCompraServicio {

    final OrdenCompraRepository ordenCompraRepository;

    @Inject
    public OrdenesCompraServicioImpl(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    @Override
    public Future<Optional<OrdenCompra>> findById(Integer id) {
        //version 1
        var future = new CompletableFuture<Optional<OrdenCompra>>();

        CompletableFuture.runAsync(() -> {
            var cliente = ordenCompraRepository.findById(id);

            future.complete(cliente);
        });

        return future;
    }
}
