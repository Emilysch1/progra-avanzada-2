package com.programacion.avanzada.db;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public record Author (

   @Id int id,
    @Column String name
){
}
