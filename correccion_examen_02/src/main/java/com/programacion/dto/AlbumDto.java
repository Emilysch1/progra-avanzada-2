package com.programacion.dto;

import java.time.LocalDate;

public record AlbumDto(String title, LocalDate releaseDate, String singerName) {
}
