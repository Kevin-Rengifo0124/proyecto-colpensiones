package co.eam.colpensiones.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class LectorArchivosUtil {

    private static final String SEPARADOR = ",,";

    public static String[] leerPrimeraLineaCsv(String rutaArchivo) throws IOException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new String[0];
        }

        try (BufferedReader lector = new BufferedReader(
            new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String primeraLinea = lector.readLine();
            if (primeraLinea != null) {
                return primeraLinea.split(LectorArchivosUtil.SEPARADOR);
            }
        }

        return null;
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(String rutaArchivo) throws IOException {
        return leerTodasLasLineasCsv(rutaArchivo, true);
    }

    public static LinkedList<String[]> leerTodasLasLineasCsv(String rutaArchivo, boolean esSaltarPrimeraFila) throws IOException {
        LinkedList<String[]> filas = new LinkedList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return new LinkedList<>();
        }

        try (BufferedReader lector = new BufferedReader(
            new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))
        ) {
            String fila;
            boolean esPrimeraLinea = (esSaltarPrimeraFila) ? true : false;
            while ((fila = lector.readLine()) != null) {
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] infoColumnas = fila.split(LectorArchivosUtil.SEPARADOR);
                filas.add(infoColumnas);
            }
        }

        return filas;
    }
}
