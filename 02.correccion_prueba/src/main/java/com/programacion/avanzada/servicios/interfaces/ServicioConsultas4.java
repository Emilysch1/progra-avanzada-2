package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.servicios.dtos.ComprasMaximoPrecioDto;
import com.programacion.avanzada.servicios.dtos.TotalComprasDto;

import java.util.List;

public interface ServicioConsultas4 {
    List<ComprasMaximoPrecioDto> preciosMaximos();
    List<TotalComprasDto> totalCompras();
}
