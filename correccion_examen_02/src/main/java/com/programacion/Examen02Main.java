package com.programacion;

import com.programacion.servicios.interfaces.ServicioAlbums;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Examen02Main {
    public static void main(String[] args) throws Exception {
        SeContainer container = SeContainerInitializer.newInstance()
                .initialize();

//        var servicioCargaDatos = container.select(ServicioCargaDatos.class)
//                .get();
//
//        Future<Boolean> ret = servicioCargaDatos.importSingers();
//        //bloquear el hilo principal hasta que se complete la carga de datos
//        ret.get();
//
//        servicioCargaDatos.importAlbums();

        ServicioAlbums servicio = container.select(ServicioAlbums.class)
                .get();

        var albums = servicio.listarAlbumsCache();

        System.out.format("+-------------+---------------------+---------------------+%n");
        System.out.format("| Album title | Album release date  | Singer full name    |%n");
        System.out.format("+-------------+---------------------+---------------------+%n");
        albums.stream()
                .map(album->
                        String.format("| %-11s | %19s | %-19s |", album.title(), album.releaseDate(), album.singerName())
                )
                .forEach(System.out::println);
        System.out.format("+-------------+---------------------+---------------------+%n");
    }
}
