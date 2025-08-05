package com.programacion.avanzada.servicios.inter;

import java.util.concurrent.Future;

public interface ServicioCargaDatos {

    Future<Boolean> importSingers();
    void importAlbums();
}
