package com.programacion.repository;

import com.programacion.db.Singer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SingerRepositoryImpl implements SingerRepository {
    @Inject
    EntityManager entityManager;

    private void doInTransaction(Runnable rd) {
        try {
            entityManager.getTransaction().begin();
            rd.run();
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public Singer findById(Integer id) {
        return entityManager.find(Singer.class, id);
    }

    public List<Singer> findAll() {
        return entityManager.createQuery("select o from Singer o", Singer.class).getResultList();
    }

    @Transactional
    public void save(Singer singer) {
        doInTransaction(() -> {
            entityManager.persist(singer);
        });
    }

    @Transactional
    public void remove(Singer singer) {
        doInTransaction(() -> {
            entityManager.remove(singer);
        });
    }

    @Transactional
    public void remove(Integer singerId) {
        doInTransaction(() -> {
            var obj = entityManager.getReference(Singer.class, singerId);
            entityManager.remove(obj);
        });
    }

    @Transactional
    public void update(Singer singer) {
        doInTransaction(() -> {
            entityManager.merge(singer);
        });
    }
}
