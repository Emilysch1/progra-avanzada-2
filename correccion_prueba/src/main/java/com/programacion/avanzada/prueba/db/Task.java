package com.programacion.avanzada.prueba.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    @Id
    @TableGenerator(
            name = "gen-task",
            table = "claves_primarias"
    )
    @GeneratedValue(generator = "gen-task")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Usuario user;

    @Column(length = 64)
    private String title;

    @Column(length = 128)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    private LocalDateTime created;

    private LocalDateTime complete;

    private Integer version;

    public static Task of(String[] it){
        return null;
    }
}
