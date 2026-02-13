package com.programacion.avanzada;

import com.programacion.avanzada.db.Cliente;
import com.programacion.avanzada.db.OrdenCompra;
import com.programacion.avanzada.db.Producto;
import com.programacion.avanzada.repositorios.interfaces.ClienteRepository;
import com.programacion.avanzada.repositorios.interfaces.OrdenCompraRepository;
import com.programacion.avanzada.repositorios.interfaces.ProductoRepository;
import com.programacion.avanzada.servicios.interfaces.ClientesServicio;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultaClienteProductos;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultaClientes;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.math.BigDecimal;

public class EvaluacionRecuperacionMain {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            //--p.2
            var clienteRepo = container.select(ClienteRepository.class).get();
            var productoRepo = container.select(ProductoRepository.class).get();
            var ordenCompraRepo = container.select(OrdenCompraRepository.class).get();

            boolean insert = false;

            if(insert) {

                var c1 = new Cliente("Nombre1", "Apellido1", "Direccion1");
                var c2 = new Cliente("Nombre2", "Apellido2", "Direccion2");
                clienteRepo.create(c1);
                clienteRepo.create(c2);
                clienteRepo.create(new Cliente("Nombre3", "Apellido3", "Direccion3"));

                var p1 = new Producto("producto1", BigDecimal.valueOf(10));
                var p2 = new Producto("producto2", BigDecimal.valueOf(20));
                productoRepo.create(p1);
                productoRepo.create(p2);

                ordenCompraRepo.create(new OrdenCompra(c1, p1, BigDecimal.valueOf(100)));
                ordenCompraRepo.create(new OrdenCompra(c1, p1, BigDecimal.valueOf(200)));
                ordenCompraRepo.create(new OrdenCompra(c1, p2, BigDecimal.valueOf(200)));

                ordenCompraRepo.create(new OrdenCompra(c2, p1, BigDecimal.valueOf(10)));
            }

            //--p.3
            var clienteseServicio = container.select(ClientesServicio.class).get();

            System.out.println("Cliente con ID 1: " + clienteseServicio.findById(1).get());

            //--p.4
            var servicioConsultas = container.select(ServicioConsultaClienteProductos.class).get();

            var clienteProductos = servicioConsultas.consultaLCientesProductos();
            clienteProductos.forEach(System.out::println);

            //--p.5
            var servicioConsultaClientes = container.select(ServicioConsultaClientes.class).get();
            var totalCompras = servicioConsultaClientes.consultar(1, 2, 3);
            totalCompras.forEach(System.out::println);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
