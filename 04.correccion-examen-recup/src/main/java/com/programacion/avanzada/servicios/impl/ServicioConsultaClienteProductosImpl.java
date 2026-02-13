package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.dto.ClienteProductos;
import com.programacion.avanzada.dto.ProductoCountDto;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultaClienteProductos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioConsultaClienteProductosImpl implements ServicioConsultaClienteProductos {

    final ClienteRepository clienteRepository;

    public ServicioConsultaClienteProductosImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteProductos> consultaLCientesProductos() {

        return clienteRepository.findAll()
                .stream()
                .map(cliente -> {
                    List<ProductoCountDto> productos = cliente.getOrdenCompras()
                            .stream()
                            .collect(Collectors.groupingBy(OrdenCompra::getProducto, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .map(it -> new ProductoCountDto(it.getKey().getNombre(), it.getValue()))
                            .sorted(Comparator.comparing(ProductoCountDto::producto))
                            .toList();

                    return new ClienteProductos(
                            cliente.getApellido(),
                            productos
                    );
                })
                .toList();
    }
}
