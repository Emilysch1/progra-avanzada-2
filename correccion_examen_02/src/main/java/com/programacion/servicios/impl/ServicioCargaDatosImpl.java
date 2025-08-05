package com.programacion.servicios.impl;

import com.programacion.db.Album;
import com.programacion.db.Singer;
import com.programacion.repository.AlbumRepository;
import com.programacion.repository.SingerRepository;
import com.programacion.servicios.interfaces.ServicioCargaDatos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@ApplicationScoped
public class ServicioCargaDatosImpl implements ServicioCargaDatos {
    @Inject
    SingerRepository repoSinger;

    @Inject
    AlbumRepository repoAlbum;

    public void importSingersInternal() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();

            Path path = Paths.get(classLoader.getResource("singers.csv").toURI());

            Stream<String> lines = Files.lines(path);

            lines
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(data -> {

                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        Singer singer = new Singer();

                        singer.setFirstName(data[1]);
                        singer.setLastName(data[2]);
                        var date = format.parse(data[3]);
                        singer.setBirthDate(LocalDate.from(date));

                        return singer;
                    })
                    .forEach(obj -> {
                        //entityManager.persist();
                        repoSinger.save(obj);
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Transactional
    public Future<Boolean> importSingers() {

        return CompletableFuture.supplyAsync(() -> {
            importSingersInternal();

            return true;
        });

    }

    @Transactional
    public void importAlbums() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            Path path = Paths.get(classLoader.getResource("albums.csv").toURI());

            Stream<String> lines = Files.lines(path);

            lines
                    .skip(1)
                    .parallel()
                    .map(line -> line.split(";"))
                    .map(data -> {

                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        //buscar el Singer
                        Singer singer = new Singer();
                        singer.setId(Integer.parseInt(data[1]));

                        Album album = new Album();

                        album.setTitle(data[2]);
                        album.setSinger(singer);
                        var date = format.parse(data[3]);
                        album.setReleaseDate(LocalDate.from(date));

                        return album;
                    })
                    .forEach(obj -> {
                        repoAlbum.save(obj);
                    });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
