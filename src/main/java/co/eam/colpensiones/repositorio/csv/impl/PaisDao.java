package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Pais;

import java.io.IOException;

public class PaisDao extends AbstractDao<Pais, Integer> {

    private static final String NOMBRE_ARCHIVO = "/pais.csv";

    public PaisDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }
}
