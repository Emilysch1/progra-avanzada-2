package com.programacion.avanzada.repositorios;

import com.programacion.avanzada.db.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
    Optional<Producto> findById(Integer id);
    List<Producto> findAll();
    void create(Producto obj);
}
