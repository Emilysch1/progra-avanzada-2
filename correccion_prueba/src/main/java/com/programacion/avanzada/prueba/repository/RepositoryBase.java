package com.programacion.avanzada.prueba.repository;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface RepositoryBase<T,ID> {
    Future<T> findById(ID id );
    void save(T obj, Runnable onOk, Consumer<Exception> onError);
    void delete(ID id, Runnable onOk, Consumer<Exception> onError);
    void update(ID id, T newObj, Runnable onOk, Consumer<Exception> onError);
}
