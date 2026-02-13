package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.db.Cliente;

import java.util.Optional;
import java.util.concurrent.Future;

public interface ClientesServicio {
    Future<Optional<Cliente>> findById(Integer id);
}
