
package com.programacion.avanzada.prueba.repository;

import com.programacion.avanzada.prueba.db.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@ApplicationScoped
public class UsuarioRepository implements RepositoryBase<Usuario, Integer>{
    private final EntityManagerFactory emf;
    private final ExecutorService executor;

    @Inject
    public UsuarioRepository(EntityManagerFactory emf, ExecutorService executor) {
        this.emf = emf;
        this.executor = executor;
    }

    public Future<Usuario> findById( Integer id ) {
        CompletableFuture<Usuario> future = new CompletableFuture<>();

        executor.submit(()->{
            var obj = emf.callInTransaction(em->em.find(Usuario.class, id));
            future.complete(obj);
        });

        return future;
    }

    public void save(Usuario project, Runnable onOk, Consumer<Exception> onError) {
        executor.submit(()->{
            try {
                emf.runInTransaction(em->{
                    em.persist(project);
                    onOk.run();
                });
            }
            catch(Exception ex) {
                onError.accept(ex);
            }
        });
    }

    @Override
    public void delete(Integer id, Runnable onOk, Consumer<Exception> onError) {
        executor.submit(()->{
            try {
                emf.runInTransaction(em -> {
                    var obj = em.getReference(Usuario.class, id);
                    em.remove(obj);
                    onOk.run();
                });
            }
            catch(Exception ex) {
                onError.accept(ex);
            }
        });
    }

    @Override
    public void update(Integer id, Usuario newObj, Runnable onOk, Consumer<Exception> onError) {
        executor.submit(()->{
            try {
                emf.runInTransaction(em -> {
                    em.merge(newObj);
                    onOk.run();
                });
            }
            catch(Exception ex) {
                onError.accept(ex);
            }
        });
    }
}
