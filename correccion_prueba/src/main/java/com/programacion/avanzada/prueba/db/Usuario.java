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
public class Usuario {
    @Id
    @TableGenerator(
            name = "gen-user",
            table = "claves_primarias"
    )
    @GeneratedValue(generator = "gen-user")
    private Integer id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String passwd;

    private LocalDateTime created;

    private Integer version;

    @OneToMany(mappedBy = "user")
    List<Task> tasks;

    public static Usuario of(String[] it) {
        String name = it[0];
        String passwd = it[1];

        return Usuario.builder()
                .name(name)
                .passwd(passwd)
                .version(1)
                .created(LocalDateTime.now())
                .build();
    }
}
