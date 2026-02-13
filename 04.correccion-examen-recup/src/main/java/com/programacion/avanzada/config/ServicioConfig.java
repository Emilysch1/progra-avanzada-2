package com.programacion.avanzada.config;

import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.servicios.impl.ServicioConsultaClienteProductosImpl;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultaClienteProductos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ServicioConfig {

    @Produces
    ServicioConsultaClienteProductos servicioConsultas(ClienteRepository clienteRepository) {
        return new ServicioConsultaClienteProductosImpl(clienteRepository);
    }
}
