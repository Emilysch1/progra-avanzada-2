package com.programacion.avanzada.repositories;

import com.programacion.avanzada.db.Cliente;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface ClienteRepository extends EntityRepository<Cliente, Integer> {
}
