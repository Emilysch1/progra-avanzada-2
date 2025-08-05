package com.programacion.avanzada.repositories;

import com.programacion.avanzada.db.OrdenCompra;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends EntityRepository<OrdenCompra, Integer> {
    @Query("select o from OrdenCompra o where o.cliente.id = ?1 order by o.producto.id, o.precio asc")
    List<OrdenCompra> findByCliente(Integer clienteId);
}
