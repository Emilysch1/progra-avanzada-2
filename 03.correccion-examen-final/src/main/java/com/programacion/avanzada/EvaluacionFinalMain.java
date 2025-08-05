package com.programacion.avanzada;

import com.programacion.avanzada.repositories.ClienteRepository;
import com.programacion.avanzada.repositories.ProductoRepository;
import com.programacion.avanzada.servicios.interfaces.ConsultasService;
import com.programacion.avanzada.servicios.interfaces.ImportarDatosService;
import com.programacion.avanzada.servicios.interfaces.ProductoService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.concurrent.Future;

public class EvaluacionFinalMain {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            //cargar la informacion de la base de datos
            {
                var importarService = container.select(ImportarDatosService.class)
                        .get();

                Future<Integer> totalClientes = importarService.importarClientes();
                Future<Integer> totalProductos = importarService.importarProductos();

                // sincronizar: bloquear hasta que se complete la importacion
                totalProductos.get();
                totalClientes.get();
            }

            //--
            var productoRepo = container.select(ProductoRepository.class)
                    .get();
            System.out.println(productoRepo);

//            Producto p = new Producto();
//            p.setNombre("producto 2");
//            p.setPrecio(BigDecimal.valueOf(20.0));
//            productoRepo.create(p);
            //--
            var clienteRepo = container.select(ClienteRepository.class)
                    .get();
            System.out.println(clienteRepo);

//            Cliente c = new Cliente();
//            c.setNombre("cliente 2");
//            c.setApellido("apellido 2");
//            c.setDireccion("direccion 2");
//            clienteRepo.save(c);

            //--
            var servicioConsultas = container.select(ConsultasService.class).get();

            servicioConsultas.precioMaximoPorCliente()
                    .forEach(System.out::println);

            servicioConsultas.totalProductosComprados()
                    .forEach(System.out::println);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
