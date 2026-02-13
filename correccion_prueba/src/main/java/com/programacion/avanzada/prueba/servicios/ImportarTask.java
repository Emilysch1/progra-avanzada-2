package com.programacion.avanzada.prueba.servicios;

import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class ImportarTask<T> implements Runnable {
    public static final String BASE_PATH = "C:/git/CLASES_2025-2026/prog-avanzada2/correccion_prueba/";

    private final String fileName;
    private final Function<String[], T> fn;
    private final EntityManagerFactory emf;

    public ImportarTask(String fileName, Function<String[], T> fn, EntityManagerFactory emf) {
        this.fileName = fileName;
        this.fn = fn;
        this.emf = emf;
    }

    private void save(T it) {
        emf.runInTransaction(em->{
            em.persist(it);
        });
    }

    public void run() {

        try {
            Path ruta = Paths.get(BASE_PATH + fileName);

            Files.lines(ruta)
                    .map(linea -> linea.split(","))
                    .map(it -> fn.apply(it))
                    .forEach(it -> {
                        save(it);
                    });

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
