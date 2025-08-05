package com.programacion.avanzada.repositories;

import com.programacion.avanzada.db.Producto;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface ProductoRepository extends EntityRepository<Producto, Integer>  {
}
