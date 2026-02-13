package com.programacion.avanzada.prueba.servicios.impl;

import com.programacion.avanzada.prueba.db.Project;
import com.programacion.avanzada.prueba.db.Task;
import com.programacion.avanzada.prueba.db.Usuario;
import com.programacion.avanzada.prueba.servicios.ImportarTask;
import com.programacion.avanzada.prueba.servicios.interfaces.ServicioImportarDatos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;

import java.util.concurrent.ExecutorService;

@ApplicationScoped
public class ServicioImportarDatosImpl implements ServicioImportarDatos {

    public static final String FILE_PROJECTS = "src/main/resources/data/proyectos.csv";
    public static final String FILE_TASKS = "src/main/resources/data/tareas.csv";
    public static final String FILE_USERS = "src/main/resources/data/usuarios.csv";

    @Inject
    private EntityManagerFactory emf;

    @Inject
    private  ExecutorService executor;

    @Override
    public void importar() {
        try {
            var f1 = executor.submit( new ImportarTask<Project>(FILE_PROJECTS, Project::of, emf) );
            var f2 = executor.submit( new ImportarTask<Task>(FILE_TASKS, Task::of, emf));
            var f3 = executor.submit( new ImportarTask<Usuario>(FILE_USERS, Usuario::of, emf));

            f1.get();
            f2.get();
            f3.get();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
