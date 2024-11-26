package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Cotizante;

import java.io.IOException;

public class CotizanteDao extends AbstractDao<Cotizante, Integer> {

    private static final String NOMBRE_ARCHIVO = "/cotizantes.csv";

    public CotizanteDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }
}
