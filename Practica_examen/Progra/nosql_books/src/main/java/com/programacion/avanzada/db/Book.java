package com.programacion.avanzada.db;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.math.BigDecimal;
import java.util.List;

@Entity
public record Book (

    @Id String isbn,
    @Column String title,
    @Column BigDecimal price,
    @Column List<Author> authors


){
}
