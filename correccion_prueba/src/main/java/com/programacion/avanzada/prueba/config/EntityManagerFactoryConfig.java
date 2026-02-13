package com.programacion.avanzada.prueba.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class  EntityManagerFactoryConfig {
    private EntityManagerFactory emf;

    @PostConstruct
    void init() {
        emf = Persistence.createEntityManagerFactory("avanzada");
    }

    @Produces
    public EntityManagerFactory getEmf() {
        return emf;
    }
}
