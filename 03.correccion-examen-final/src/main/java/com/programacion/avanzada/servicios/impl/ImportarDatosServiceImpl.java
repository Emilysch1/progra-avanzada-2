package com.programacion.avanzada.servicios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.servicios.interfaces.ImportarDatosService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ImportarDatosServiceImpl implements ImportarDatosService {
    private final EntityManager entityManager;

    protected ImportarDatosServiceImpl() {
        this(null);
    }

    @Inject
    public ImportarDatosServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Future<Integer> importarClientes() {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        String csvFileName = "c:/data/clientes.csv"; // Cambiar al nombre correcto del archivo CSV

        CompletableFuture.runAsync(() -> {
            AtomicInteger total = new AtomicInteger(0);

            try {
                Files.lines(Path.of(csvFileName))
                        .map(it -> it.split(";"))
                        .forEach(it -> {
                            var cliente = new Cliente();
                            //cliente.setId(it[0]); <-- automatico
                            cliente.setNombre(it[1]);
                            cliente.setApellido(it[2]);
                            cliente.setDireccion(it[3]);

                            //entityManager.persist(cliente);
                            total.getAndIncrement();
                        });
            } catch (IOException e) {
                future.complete(total.get());
                throw new RuntimeException(e);
            }

            future.complete(total.get());
        });

        return future;
    }

    public Future<Integer> importarProductos() {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        String csvFileName = "c:/datos/productos.csv"; // Cambiar al nombre correcto del archivo CSV

        CompletableFuture.runAsync(() -> {
            AtomicInteger total = new AtomicInteger(0);

            try {
                Files.lines(Path.of(csvFileName))
                        .map(it -> it.split(";"))
                        .forEach(it -> {
                            var producto = new Producto();
                            //producto.setId(it[0]); <-- automatico
                            producto.setNombre(it[1]);
                            producto.setPrecio(BigDecimal.valueOf(Double.valueOf(it[2])));

                            //entityManager.persist(producto);
                            total.getAndIncrement();
                        });
            } catch (IOException e) {
                future.complete(total.get());
                throw new RuntimeException(e);
            }

            future.complete(total.get());
        });

        return future;
    }
}
