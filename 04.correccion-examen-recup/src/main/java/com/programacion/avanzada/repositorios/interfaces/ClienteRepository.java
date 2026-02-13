package com.programacion.avanzada.repositorios.interfaces;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.dto.ClienteTotalCompras;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findById(Integer id);
    List<Cliente> findAll();
    void create(Cliente obj);
    void upadate(Cliente obj);
    void delete(Integer id);
}
