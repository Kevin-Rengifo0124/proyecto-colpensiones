package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.entidad.Departamento;

import java.io.IOException;

public class DepartamentoDao extends AbstractDao<Departamento, Integer> {

    private static final String NOMBRE_ARCHIVO = "/departamento.csv";

    public DepartamentoDao(String rutaBaseArchivo) throws IOException {
        super(rutaBaseArchivo + NOMBRE_ARCHIVO);
    }
}
