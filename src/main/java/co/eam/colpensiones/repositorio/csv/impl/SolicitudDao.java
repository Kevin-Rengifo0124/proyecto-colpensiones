package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Solicitud;
import co.eam.colpensiones.util.ArchivoUtil;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolicitudDao extends AbstractDao<Solicitud, Integer> {

    private static final String NOMBRE_ARCHIVO = "/solicitud.csv";

    public SolicitudDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }

    @Override
    public void modificarEstadoSolicitud(int fila, int columna, String estado) {
        try {

            ArchivoUtil.modificarCampoCSV(getRutaArchivo(), fila, columna, estado);

        } catch (Exception e) {
            log.error("Error al modificar el estado en la fila {} de la columna {}. Error: {}", fila, columna, e.getMessage(), e);
            throw new RuntimeException("Error al modificar el archivo CSV.", e);
        }
    }
}
