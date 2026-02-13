
package com.programacion.avanzada.prueba.repository;

import com.programacion.avanzada.prueba.db.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@ApplicationScoped
public class TaskRepository implements RepositoryBase<Task, Integer>{
    private final EntityManagerFactory emf;
    private final ExecutorService executor;

    @Inject
    public TaskRepository(EntityManagerFactory emf, ExecutorService executor) {
        this.emf = emf;
        this.executor = executor;
    }

    public Future<Task> findById( Integer id ) {
        CompletableFuture<Task> future = new CompletableFuture<>();

        executor.submit(()->{
            var obj = emf.callInTransaction(em->em.find(Task.class, id));
            future.complete(obj);
        });

        return future;
    }

    public void save(Task project, Runnable onOk, Consumer<Exception> onError) {
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
                    var obj = em.getReference(Task.class, id);
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
    public void update(Integer id, Task newObj, Runnable onOk, Consumer<Exception> onError) {
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
