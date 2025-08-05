package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.repositories.OrdenCompraRepository;
import com.programacion.avanzada.servicios.interfaces.OrdenCompraService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrdenCompraServiceImpl implements OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;

    protected OrdenCompraServiceImpl() {
        this(null);
    }

    @Inject
    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    public Optional<OrdenCompra> findById(Integer id) {
        return ordenCompraRepository.findOptionalBy(id);
    }

    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.findAll();
    }
}
