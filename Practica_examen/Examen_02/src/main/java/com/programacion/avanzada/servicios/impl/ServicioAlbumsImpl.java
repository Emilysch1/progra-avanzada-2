package com.programacion.avanzada.servicios.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.programacion.avanzada.db.Singer;
import com.programacion.avanzada.dto.AlbumDto;
import com.programacion.avanzada.repository.AlbumRepository;
import com.programacion.avanzada.repository.SingerRepository;
import com.programacion.avanzada.servicios.inter.ServicioAlbums;
import jakarta.inject.Inject;

import java.util.List;

public class ServicioAlbumsImpl implements ServicioAlbums {

    @Inject
    AlbumRepository repoAlbums;

    @Inject
    SingerRepository repoSingers;

    @Inject
    private Cache<Integer, Singer> cache;

    public List<AlbumDto> listarAlbums() {
        var albums = repoAlbums.findAll();

        return albums.stream()
                .map(album -> {
                    Singer singer = repoSingers.findById(album.getSinger().getId());

                    var dto = new AlbumDto(album.getTitle(), album.getReleaseDate(), singer.getFirstName());

                    return dto;
                })
                .toList();
    }

    private Singer getCachedSinger(Integer id) {
        return cache.get(id, key -> repoSingers.findById(key));
    }

    public List<AlbumDto> listarAlbumsCache() {
        var albums = repoAlbums.findAll();

        return albums.stream()
                .map(album -> {
                    Singer singer = getCachedSinger(album.getSinger().getId());

                    var dto = new AlbumDto(album.getTitle(), album.getReleaseDate(), singer.getFirstName());

                    return dto;
                })
                .toList();
    }
}
