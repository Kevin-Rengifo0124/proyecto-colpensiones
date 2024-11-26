package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.repositorio.csv.InterfaceDao;
import co.eam.colpensiones.util.ArchivoUtil;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDao<ClaseEntidad, TipoId> implements InterfaceDao<ClaseEntidad, TipoId> {

    private Class<ClaseEntidad> claseEntidad;

    protected GestorDeEntidad gestorDeEntidad;

    @Getter
    protected final String rutaArchivo;

    @SuppressWarnings("unchecked")
    public AbstractDao(String rutaArchivo) throws IOException {
        this.rutaArchivo = rutaArchivo;
        this.claseEntidad = (Class<ClaseEntidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.gestorDeEntidad = new GestorDeEntidad(rutaArchivo);
    }

    @Override
    public LinkedList<ClaseEntidad> obtenerTodosLosDatos() {
        return gestorDeEntidad.obtenerDatosDeEntidad(this.claseEntidad);
    }

    @Override
    public void modificarEstadoSolicitud(int fila, int columna, String estado) {
        try {

            ArchivoUtil.modificarCampoCSV(rutaArchivo, fila, columna, estado);

        } catch (Exception e) {
            log.error("Error al modificar el campo en la fila {} de la columna {}. Error: {}", fila, columna, e.getMessage(), e);
            throw new RuntimeException("Error al modificar el archivo CSV.", e);
        }
    }
}
