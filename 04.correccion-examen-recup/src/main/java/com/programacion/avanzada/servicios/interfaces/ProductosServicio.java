package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.db.Producto;

import java.util.Optional;
import java.util.concurrent.Future;

public interface ProductosServicio {
    Future<Optional<Producto>> findById(Integer id);
}
