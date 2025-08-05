package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositories.ProductoRepository;
import com.programacion.avanzada.servicios.interfaces.ProductoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    protected ProductoServiceImpl() {
        this(null);
    }

    @Inject
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Optional<Producto> findById(Integer id) {
        return productoRepository.findOptionalBy(id);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
}
