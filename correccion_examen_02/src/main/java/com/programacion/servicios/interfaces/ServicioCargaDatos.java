package com.programacion.servicios.interfaces;

import java.util.concurrent.Future;

public interface ServicioCargaDatos {
    Future<Boolean> importSingers();
    void importAlbums();
}
