package co.eam.colpensiones.repositorio.csv;

import java.util.LinkedList;

public interface InterfaceDao<ClaseEntidad, TipoId> {

    LinkedList<ClaseEntidad> obtenerTodosLosDatos();

    void modificarEstadoSolicitud(int fila, int columna, String estado);
}
