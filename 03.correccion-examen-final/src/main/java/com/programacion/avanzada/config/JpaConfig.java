package com.programacion.avanzada.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;

@ApplicationScoped
public class JpaConfig {
    @PersistenceUnitName("avanzada")
    @Inject
    private EntityManagerFactory emf;

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
