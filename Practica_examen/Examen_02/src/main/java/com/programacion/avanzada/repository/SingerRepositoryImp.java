package com.programacion.avanzada.repository;

import com.programacion.avanzada.db.Singer;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class SingerRepositoryImp implements SingerRepository {

    @Inject
    EntityManager em;

    private void donTransaction(Runnable runnable) {
        try {
            em.getTransaction().begin();
            runnable.run();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Singer findById(Integer id) {
        return em.find(Singer.class, id);
    }

    @Override
    public List<Singer> findAll() {
        return em.createQuery("SELECT s FROM Singer s", Singer.class).getResultList();
    }

    @Transactional
    public void save(Singer singer) {
        donTransaction(() -> {
            em.persist(singer);
        });

    }

    @Override
    public void remove(Singer singer) {
        donTransaction(() -> {
            em.remove(singer);
        });

    }

    @Override
    public void remove(Integer singerId) {
        donTransaction(() -> {
            var obj = em.getReference(Singer.class, singerId);
            em.remove(obj);
        });
    }

    @Override
    public void update(Singer singer) {
        donTransaction(() -> {
            em.merge(singer);
        });
    }

}