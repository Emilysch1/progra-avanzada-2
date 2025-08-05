package com.programacion.avanzada;

import com.programacion.avanzada.repositorios.ClienteRepository;
import com.programacion.avanzada.repositorios.ProductoRepository;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultas4;
import com.programacion.avanzada.servicios.interfaces.ServicioConsultas5;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class PruebaParcialMain {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

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
            var servicio4 = container.select(ServicioConsultas4.class).get();

            servicio4.preciosMaximos()
                    .forEach(System.out::println);

            servicio4.totalCompras()
                    .forEach(System.out::println);

            //--
            var servicio5 = container.select(ServicioConsultas5.class).get();

            servicio5.preciosMaximos()
                    .forEach(System.out::println);

            servicio5.totalCompras()
                    .forEach(System.out::println);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
