package com.programacion.avanzada.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
public class JpaConfig {
    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        System.out.println("****Inicializando EntityManagerFactory");
        emf = jakarta.persistence.Persistence.createEntityManagerFactory("avanzada");
    }

    @Produces
    public EntityManager entityManager() {
        System.out.println("****Creando EntityManager");
        return emf.createEntityManager();
    }

    void closeEntityManager(@Disposes EntityManager entitymanager) {
        System.out.println("****Cerrando EntityManager");
        if(entitymanager != null && entitymanager.isOpen()) {
            entitymanager.close();
        }
    }
}
