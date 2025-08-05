package com.programacion.avanzada.servicios.interfaces;

import com.programacion.avanzada.dto.ComprasMaximoPrecioDto;
import com.programacion.avanzada.dto.TotalComprasDto;

import java.util.List;

public interface ConsultasService {
    List<ComprasMaximoPrecioDto> precioMaximoPorCliente( );
    List<TotalComprasDto> totalProductosComprados( );
}
