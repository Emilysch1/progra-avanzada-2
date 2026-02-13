package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.repositorios.interfaces.ProductoRepository;
import com.programacion.avanzada.servicios.interfaces.ClientesServicio;
import com.programacion.avanzada.servicios.interfaces.ProductosServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@ApplicationScoped
public class ProductosServicioImpl implements ProductosServicio {

    final ProductoRepository productoRepository;

    @Inject
    public ProductosServicioImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Future<Optional<Producto>> findById(Integer id) {
        //version 2
        return CompletableFuture.supplyAsync(
                ()-> productoRepository.findById(id)
        );
    }
}
