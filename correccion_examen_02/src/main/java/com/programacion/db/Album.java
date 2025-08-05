package com.programacion.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="album")
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    private String title;

    @Column(name="release_date")
    private LocalDate releaseDate;
}
