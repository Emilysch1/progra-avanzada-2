package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositorios.ClienteRepository;
import com.programacion.avanzada.repositorios.OrdenCompraRepository;
import com.programacion.avanzada.repositorios.ProductoRepository;
import com.programacion.avanzada.servicios.dtos.ComprasMaximoPrecioDto;
import com.programacion.avanzada.servicios.dtos.TotalComprasDto;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultas4;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultas5;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServicioConsultas4Impl implements ServicioConsultas4 {
    @Inject
    OrdenCompraRepository ordenCompraRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ProductoRepository productoRepository;

    public List<ComprasMaximoPrecioDto> preciosMaximos() {
        var ret = new ArrayList<ComprasMaximoPrecioDto>();

        clienteRepository.findAll()
                .forEach(cliente -> {
                    List<OrdenCompra> ordenes = ordenCompraRepository.findByCliente(cliente.getId());

                    Map<Integer, OrdenCompra> tmp = new HashMap<>();

                    ordenes.forEach(it -> {
                        tmp.put(it.getProducto().getId(), it);
                    });

                    tmp.values()
                            .forEach(orden -> {
                                ret.add(new ComprasMaximoPrecioDto(
                                        cliente.getApellido(),
                                        orden.getProducto().getNombre(),
                                        orden.getPrecio()
                                ));
                            });
                });

        return ret;
    }

    public List<TotalComprasDto> totalCompras() {
        return ordenCompraRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(OrdenCompra::getProducto, Collectors.counting()))
                .entrySet()
                .stream()
                .map(it->new TotalComprasDto(it.getKey().getNombre(), it.getValue()) )
                .toList();
    }
}
