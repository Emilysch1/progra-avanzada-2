package com.programacion.avanzada.dto;

import java.util.List;

public record ClienteProductos(String cliente, List<ProductoCountDto> productos) {
}
