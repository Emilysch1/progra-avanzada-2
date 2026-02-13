package com.programacion.avanzada.prueba;

import com.programacion.avanzada.prueba.db.Project;
import com.programacion.avanzada.prueba.repository.ProjectRepository;
import com.programacion.avanzada.prueba.servicios.interfaces.ServicioConsultas;
import com.programacion.avanzada.prueba.servicios.interfaces.ServicioImportarDatos;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.time.LocalDateTime;

public class PruebaMain {
    public static void main(String[] args) throws Exception {
        try (var container = SeContainerInitializer.newInstance()
                .initialize()) {

            var servicio = container.select(ServicioImportarDatos.class).get();
            servicio.importar();

            //--
            var repo1 = container.select(ProjectRepository.class).get();

            var p1 = repo1.findById(202);
            System.out.println(p1.get());

            //--
            var p2 = Project.builder()
                    .name("pp")
                    .created(LocalDateTime.now())
                    .version(1)
                    .build();

            repo1.save(p2,
                    () -> System.out.println("Creado correctamente"),
                    ex -> ex.printStackTrace()
            );

            //--
            var servicioConsulta = container.select(ServicioConsultas.class).get();

            var datos1 = servicioConsulta.taskStatus();

            datos1.forEach(System.out::println);
        }
    }
}
