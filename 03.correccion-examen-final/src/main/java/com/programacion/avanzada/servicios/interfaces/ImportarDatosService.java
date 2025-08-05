package com.programacion.avanzada.servicios.interfaces;

import java.util.concurrent.Future;

public interface ImportarDatosService {
    Future<Integer> importarClientes();
    Future<Integer> importarProductos();
}
