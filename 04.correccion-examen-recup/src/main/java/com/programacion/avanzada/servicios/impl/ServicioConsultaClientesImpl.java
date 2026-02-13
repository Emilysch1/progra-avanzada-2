package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.dto.ClienteTotalCompras;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultaClientes;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ServicioConsultaClientesImpl implements ServicioConsultaClientes {
    final ClienteRepository clienteRepository;

    @Inject
    public ServicioConsultaClientesImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteTotalCompras totalCompras(EntityManager entityManager, Integer clienteId) {

        var cliente = entityManager.find(Cliente.class, clienteId);

        var query = entityManager.createQuery(
                "select count(o) " +
                        " from OrdenCompra o " +
                        " where o.cliente.id = :clienteId ",
                Long.class);

        query.setParameter("clienteId", clienteId);

        var total = query.getSingleResult();

        return new ClienteTotalCompras(cliente.getNombreCompleto(), total.intValue());
    }

    @Override
    public List<ClienteTotalCompras> consultar(Integer c1, Integer c2, Integer c3) {
        try {
            CompletableFuture<ClienteTotalCompras> total1 = CompletableFuture.supplyAsync(() -> {
                var em = CDI.current().select(EntityManager.class).get();

                return totalCompras(em, c1);
            });

            CompletableFuture<ClienteTotalCompras> total2 = CompletableFuture.supplyAsync(() -> {
                var em = CDI.current().select(EntityManager.class).get();

                return totalCompras(em, c2);
            });

            //ejecutar la 3ra consulta en el thread principal
            var em = CDI.current().select(EntityManager.class).get();
            var total3 = totalCompras(em, c3);

            CompletableFuture.allOf(total1, total2)
                    .join();

            return List.of(total1.get(), total2.get(), total3);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar los totales de compras", e);
        }
    }
}
