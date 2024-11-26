package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Caracterizacion;

import java.io.IOException;

public class CaracterizacionDao extends AbstractDao<Caracterizacion, Integer> {

    private static final String NOMBRE_ARCHIVO = "/caracterizacion.csv";

    public CaracterizacionDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }
}
