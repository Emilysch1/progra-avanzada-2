package com.programacion.servicios.interfaces;

import com.programacion.dto.AlbumDto;

import java.util.List;

public interface ServicioAlbums {
    List<AlbumDto> listarAlbums();
    List<AlbumDto> listarAlbumsCache();
}
