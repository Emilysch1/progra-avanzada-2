package com.programacion.avanzada.repositorios.impl;

import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.repositorios.interfaces.OrdenCompraRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class OrdenCompraRepositoryImpl implements OrdenCompraRepository {
    final EntityManager entityManager;

    void runInTransaction(Consumer<EntityManager> consumer) {
        var tx = entityManager.getTransaction();
        try {
            tx.begin();
            //operaciones
            consumer.accept(entityManager);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public OrdenCompraRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<OrdenCompra> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(OrdenCompra.class, id));
    }

    @Override
    public List<OrdenCompra> findAll() {
        return entityManager.createQuery("select o from OrdenCompra o", OrdenCompra.class)
                .getResultList();
    }

    @Override
    public void create(OrdenCompra obj) {
        runInTransaction((em)->{
            em.persist(obj);
        });
    }

    @Override
    public void upadate(OrdenCompra obj) {
        runInTransaction((em)->{
            var savedObj = em.find(OrdenCompra.class, obj.getId());

            savedObj.setPrecio(obj.getPrecio());
        });
    }

    @Override
    public void delete(Integer id) {
        runInTransaction((em)->{
            var obj = em.getReference(OrdenCompra.class, id);
            em.persist(obj);
        });
    }
}
