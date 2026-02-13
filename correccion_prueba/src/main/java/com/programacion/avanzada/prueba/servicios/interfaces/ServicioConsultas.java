package com.programacion.avanzada.prueba.servicios.interfaces;

import com.programacion.avanzada.prueba.servicios.dtos.TaskSummary;
import com.programacion.avanzada.prueba.servicios.dtos.TasksStatus;

import java.util.List;

public interface ServicioConsultas {
    List<TasksStatus> taskStatus();
    List<TaskSummary> taskSummary();
}
