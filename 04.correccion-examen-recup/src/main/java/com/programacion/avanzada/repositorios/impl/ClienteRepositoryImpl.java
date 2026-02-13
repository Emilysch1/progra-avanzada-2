package com.programacion.avanzada.repositorios.impl;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ClienteRepositoryImpl implements ClienteRepository {

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

    public ClienteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Cliente.class, id));
    }

    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("select o from Cliente o", Cliente.class)
                .getResultList();
    }

    @Override
    public void create(Cliente obj) {
        runInTransaction((em) -> {
            em.persist(obj);
        });
    }

    @Override
    public void upadate(Cliente obj) {
        runInTransaction((em) -> {
            var savedObj = em.find(Cliente.class, obj.getId());
            savedObj.setNombre(obj.getNombre());
            savedObj.setApellido(obj.getApellido());
            savedObj.setDireccion(obj.getDireccion());
        });
    }

    @Override
    public void delete(Integer id) {
        runInTransaction((em) -> {
            var obj = em.getReference(Cliente.class, id);
            em.persist(obj);
        });
    }
}
