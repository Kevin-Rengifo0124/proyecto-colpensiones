package co.eam.colpensiones.repositorio.csv;

import co.eam.colpensiones.modelo.Solicitud;

import java.io.IOException;

public class SolicitudDao extends AbstractDao<Solicitud, Integer> {
    public SolicitudDao() throws IOException {
        super("");
    }
}
