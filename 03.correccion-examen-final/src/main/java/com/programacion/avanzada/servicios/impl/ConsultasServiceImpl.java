package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.dto.ComprasMaximoPrecioDto;
import com.programacion.avanzada.dto.TotalComprasDto;
import com.programacion.avanzada.servicios.interfaces.ConsultasService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ConsultasServiceImpl implements ConsultasService {
    private final EntityManager entityManager;

    protected ConsultasServiceImpl() {
        this(null);
    }

    @Inject
    public ConsultasServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ComprasMaximoPrecioDto> precioMaximoPorCliente() {
        String sql = " select o.cliente.apellido, o.producto.nombre, max(o.precio) " +
                " from OrdenCompra o" +
                " group by o.cliente.apellido, o.producto.nombre" +
                " order by o.cliente.apellido";

        return entityManager.createQuery(sql, ComprasMaximoPrecioDto.class)
                .getResultList();
    }

    @Override
    public List<TotalComprasDto> totalProductosComprados() {
        String sql = " select  o.cliente.apellido, count(o) " +
                " from OrdenCompra o" +
                " group by o.cliente.apellido" +
                " order by o.cliente.apellido";

        return entityManager.createQuery(sql, TotalComprasDto.class)
                .getResultList();
    }
}
