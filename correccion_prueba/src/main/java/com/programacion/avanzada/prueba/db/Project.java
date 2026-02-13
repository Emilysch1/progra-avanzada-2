package com.programacion.avanzada.prueba.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @TableGenerator(
            name = "gen-project",
            table = "claves_primarias"

    )

    @GeneratedValue(generator = "gen-project")
    private Integer id;

    @Column(length = 64)
    private String name;

    private Integer version;

    private LocalDateTime created;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public static Project of(String[] it){
        String nombre = it[0];
        Integer version = Integer.valueOf(it[1]);

        return Project.builder()
                .name(nombre)
                .version(version)
                .created(LocalDateTime.now())
                .build();
    }
}
