package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.OrdenCompra;

import java.util.Optional;
import java.util.concurrent.Future;

public interface OrdenesCompraServicio {
    Future<Optional<OrdenCompra>> findById(Integer id);
}
