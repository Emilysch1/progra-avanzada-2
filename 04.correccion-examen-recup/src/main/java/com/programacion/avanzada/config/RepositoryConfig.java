package com.programacion.avanzada.config;

import com.programacion.avanzada.repositorios.impl.ClienteRepositoryImpl;
import com.programacion.avanzada.repositorios.impl.OrdenCompraRepositoryImpl;
import com.programacion.avanzada.repositorios.impl.ProductoRepositoryImpl;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.repositorios.interfaces.OrdenCompraRepository;
import com.programacion.avanzada.repositorios.interfaces.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class RepositoryConfig {

    @Produces
    ClienteRepository clienteRepository(EntityManager em) {
        return new ClienteRepositoryImpl(em);
    }

    @Produces
    ProductoRepository productoRepository(EntityManager em) {
        return new ProductoRepositoryImpl(em);
    }

    @Produces
    OrdenCompraRepository ordenCompraRepository(EntityManager em) {
        return new OrdenCompraRepositoryImpl(em);
    }
}
