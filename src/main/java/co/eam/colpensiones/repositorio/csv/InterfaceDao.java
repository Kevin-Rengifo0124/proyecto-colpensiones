package co.eam.colpensiones.repositorio.csv;

import java.util.List;

public interface InterfaceDao<ClaseEntidad, TipoId> {

    public List<ClaseEntidad> obtenerTodosLosDatos();
}
