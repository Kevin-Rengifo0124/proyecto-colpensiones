package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Ciudad;

import java.io.IOException;

public class CiudadDao extends AbstractDao<Ciudad, Integer> {

    private static final String NOMBRE_ARCHIVO = "/ciudad.csv";

    public CiudadDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }
}
