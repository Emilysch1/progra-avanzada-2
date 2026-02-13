package com.programacion.avanzada.repositorios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositorios.interfaces.ProductoRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProductoRepositoryImpl implements ProductoRepository  {
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

    public ProductoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Producto.class, id));
    }

    @Override
    public List<Producto> findAll() {
        return entityManager.createQuery("select o from Producto o", Producto.class)
                .getResultList();
    }

    @Override
    public void create(Producto obj) {
        runInTransaction((em)->{
            em.persist(obj);
        });
    }

    @Override
    public void upadate(Producto obj) {
        runInTransaction((em)->{
            var savedObj = em.find(Producto.class, obj.getId());
            savedObj.setNombre(obj.getNombre());
            savedObj.setPrecio(obj.getPrecio());
        });
    }

    @Override
    public void delete(Integer id) {
        runInTransaction((em)->{
            var obj = em.getReference(Cliente.class, id);
            em.persist(obj);
        });
    }
}
