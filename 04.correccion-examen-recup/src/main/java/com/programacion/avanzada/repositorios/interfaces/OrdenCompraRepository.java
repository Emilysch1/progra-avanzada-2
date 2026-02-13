package com.programacion.avanzada.repositorios.interfaces;

import com.programacion.avanzada.db.OrdenCompra;

import java.util.List;
import java.util.Optional;

public interface OrdenCompraRepository {
    Optional<OrdenCompra> findById(Integer id);
    List<OrdenCompra> findAll();
    void create(OrdenCompra obj);
    void upadate(OrdenCompra obj);
    void delete(Integer id);
}
