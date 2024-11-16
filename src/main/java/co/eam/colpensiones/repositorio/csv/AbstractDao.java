package co.eam.colpensiones.repositorio.csv;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<ClaseEntidad, TipoId> implements InterfaceDao<ClaseEntidad, TipoId> {

    private Class<ClaseEntidad> claseEntidad;
    protected GestorDeEntidad gestorDeEntidad;

    @SuppressWarnings("unchecked")
    public AbstractDao(String rutaArchivo) throws IOException {
        this.claseEntidad = (Class<ClaseEntidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.gestorDeEntidad = new GestorDeEntidad(rutaArchivo);
    }

    @Override
    public List<ClaseEntidad> obtenerTodosLosDatos() {
        return gestorDeEntidad.obtenerDatosDeEntidad(this.claseEntidad);
    }
}
