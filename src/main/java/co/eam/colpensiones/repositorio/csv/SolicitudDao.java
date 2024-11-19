package co.eam.colpensiones.repositorio.csv;

import co.eam.colpensiones.modeloEntidades.Solicitud;

import java.io.IOException;

public class SolicitudDao extends AbstractDao<Solicitud, Integer> {
    public SolicitudDao() throws IOException {
        super("");
    }
}
