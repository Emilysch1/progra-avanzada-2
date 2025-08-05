package com.programacion.avanzada.servicios.inter;

import com.programacion.avanzada.dto.AlbumDto;

import java.util.List;

public interface ServicioAlbums {

    List<AlbumDto> listarAlbums();
    List<AlbumDto> listarAlbumsCache();
}
