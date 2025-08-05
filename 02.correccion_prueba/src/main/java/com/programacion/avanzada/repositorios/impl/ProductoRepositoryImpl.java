package com.programacion.avanzada.repositorios.impl;

import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositorios.ProductoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoRepositoryImpl implements ProductoRepository {
    @Inject
    EntityManager em;

    @Override
    public Optional<Producto> findById(Integer id) {
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    @Override
    public List<Producto> findAll() {
        return em.createQuery("select p from Producto p", Producto.class)
                .getResultList();
    }

    @Override
    public void create(Producto obj) {
        em.getEntityManagerFactory()
                .runInTransaction(em -> {
                    em.persist(obj);
                });
    }
}
