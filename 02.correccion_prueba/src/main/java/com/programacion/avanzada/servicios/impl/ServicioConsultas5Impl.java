package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.servicios.dtos.ComprasMaximoPrecioDto;
import com.programacion.avanzada.servicios.dtos.TotalComprasDto;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultas5;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioConsultas5Impl implements ServicioConsultas5 {
    @Inject
    EntityManager em;

    public List<ComprasMaximoPrecioDto> preciosMaximos() {
        String sql = " select o.cliente.apellido, o.producto.nombre, max(o.precio) " +
                " from OrdenCompra o" +
                " group by o.cliente.apellido, o.producto.nombre" +
                " order by o.cliente.apellido";

        return em.createQuery(sql, ComprasMaximoPrecioDto.class)
                .getResultList();
    }

    public List<TotalComprasDto> totalCompras() {
        String sql = " select  o.producto.nombre, count(o) " +
                " from OrdenCompra o" +
                " group by o.producto.nombre" +
                " order by o.producto.nombre";

        return em.createQuery(sql, TotalComprasDto.class)
                .getResultList();
    }
}
