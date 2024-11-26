package co.eam.colpensiones.repositorio.csv.impl;

import co.eam.colpensiones.enums.EstadoSolicitud;
import co.eam.colpensiones.enums.TipoCaracterizacion;
import co.eam.colpensiones.enums.TipoDocumento;
import co.eam.colpensiones.util.LectorArchivosUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GestorDeEntidad {

    private Map<Integer, String> mapeoColumnas;
    protected String rutaArchivo;

    public GestorDeEntidad(String rutaArchivo) throws IOException {
        this.rutaArchivo = rutaArchivo;
        contruirMapeo();
    }

    public void contruirMapeo() throws IOException {
        this.mapeoColumnas = new HashMap<>();
        String[] primeraLinea = LectorArchivosUtil.leerPrimeraLineaCsv(this.rutaArchivo);

        for (int i = 0; i < primeraLinea.length; i++) {
            this.mapeoColumnas.put(i, primeraLinea[i]);
        }
    }

    public <ClaseEntidad> LinkedList<ClaseEntidad> obtenerDatosDeEntidad(Class<ClaseEntidad> claseEntidad) {

        LinkedList<ClaseEntidad> entidades = new LinkedList<>();
        try {

            LinkedList<String[]> lineas = LectorArchivosUtil.leerTodasLasLineasCsv(this.rutaArchivo);

            for (String[] linea : lineas) {

                ClaseEntidad instancia = claseEntidad.getDeclaredConstructor().newInstance();

                for (Map.Entry<Integer, String> itemMapa : this.mapeoColumnas.entrySet()) {
                    Field atributo = claseEntidad.getDeclaredField(itemMapa.getValue());
                    atributo.setAccessible(true);
                    String valor = linea[itemMapa.getKey()];

                    if (atributo.getType().equals(Integer.class) || atributo.getType().equals(int.class)) {
                        atributo.set(instancia, Integer.parseInt(valor));
                    } else if (atributo.getType().equals(Double.class) || atributo.getType().equals(double.class)) {
                        atributo.set(instancia, Double.parseDouble(valor));
                    } else if (atributo.getType().equals(Boolean.class) || atributo.getType().equals(boolean.class)) {
                        atributo.set(instancia, Boolean.parseBoolean(valor));
                    } else if (atributo.getType().equals(Long.class) || atributo.getType().equals(long.class)) {
                        atributo.set(instancia, Long.parseLong(valor));
                    } else if (atributo.getType().equals(TipoDocumento.class)) {
                        atributo.set(instancia, TipoDocumento.valueOf(valor));
                    } else if (atributo.getType().equals(TipoCaracterizacion.class)) {
                        atributo.set(instancia, TipoCaracterizacion.valueOf(valor));
                    } else if (atributo.getType().equals(EstadoSolicitud.class)) {
                        atributo.set(instancia, EstadoSolicitud.valueOf(valor.toUpperCase()));
                    } else {
                        atributo.set(instancia, valor);
                    }
                }

                entidades.add(instancia);
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return entidades;
    }
}
