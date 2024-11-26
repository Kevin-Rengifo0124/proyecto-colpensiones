package co.eam.colpensiones.repositorio.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Slf4j
public class SuperCache<String, ClaseEntidad> {

    private Map<String, LinkedList<ClaseEntidad>> cacheArchivos;

    public SuperCache() {
        cacheArchivos = new HashMap<>();
    }

    public void agregarArchivoACache(String nombreArchivo, LinkedList<ClaseEntidad> datosArchivo) {
        cacheArchivos.put(nombreArchivo, datosArchivo);
    }

    public LinkedList<ClaseEntidad> obtenerDatosPorArchivo(String nombreArchivo) {
        return cacheArchivos.get(nombreArchivo);
    }

    public boolean existeArchivoEnCache(String nombreArchivo) {
        return cacheArchivos.containsKey(nombreArchivo);
    }

    public Map<String, LinkedList<ClaseEntidad>> obtenerTodosLosArchivos() {
        return cacheArchivos;
    }

    public void imprimirLista(String nombreArchivo) {
        LinkedList<ClaseEntidad> lista = obtenerDatosPorArchivo(nombreArchivo);

        if (lista != null) {
            for (ClaseEntidad claseEntidad : lista) {
                System.out.println(claseEntidad);
            }
        }

    }
}
