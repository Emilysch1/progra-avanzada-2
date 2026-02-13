package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.dto.ClienteTotalCompras;

import java.util.List;

public interface ServicioConsultaClientes {
    List<ClienteTotalCompras> consultar(Integer c1, Integer c2, Integer c3);
}
