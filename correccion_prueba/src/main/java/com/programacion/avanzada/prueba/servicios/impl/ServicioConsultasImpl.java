package com.programacion.avanzada.prueba.servicios.impl;

import com.programacion.avanzada.prueba.servicios.dtos.TasksStatus;
import com.programacion.avanzada.prueba.servicios.dtos.TaskSummary;
import com.programacion.avanzada.prueba.servicios.interfaces.ServicioConsultas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

@ApplicationScoped
public class ServicioConsultasImpl implements ServicioConsultas {

    private final EntityManagerFactory emf;

    @Inject
    private ServicioConsultasImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<TasksStatus> taskStatus() {

        /*
        SELECT u.name, COUNT(t)
        FROM Usuario u JOIN u.tasks t
        WHERE t.complete IS NOT NULL
        GROUP BY u.name
        HAVING COUNT(t) > 5
        ORDER BY COUNT(t) DESC
         */
        String sql =
                """
                        select 
                            o.user.name,
                            count(o) 
                        from Task o
                        where
                            o.complete is not null
                        group by o.user.name
                        having count(o)>5
                        order by count(o) desc               
                        """;

        return emf.callInTransaction(em -> {
            return em.createQuery(sql, TasksStatus.class).getResultList();
        });
    }

    public List<TaskSummary> taskSummary() {
        String sql =
                """
                        SELECT t.title, p.name, u.name
                        FROM Task t JOIN t.project p JOIN t.user u
                        WHERE t.created > :fechaLimite
                        """;

        return emf.callInTransaction(em -> {
            return em.createQuery(sql, TaskSummary.class).getResultList();
        });
    }
}
