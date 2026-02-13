package com.programacion.avanzada.prueba.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class ExecutorConfig {

    private ExecutorService executor;

    @PostConstruct
    void init() {
        int cores = Runtime.getRuntime().availableProcessors();

        executor = Executors.newFixedThreadPool(cores);
    }

    @PreDestroy
    public void stop() {
        // Importante: Cerrar el pool cuando la app se detenga
        executor.shutdown();
    }

    @Produces
    @ApplicationScoped
    public ExecutorService produceExecutor() {
        return executor;
    }
}
