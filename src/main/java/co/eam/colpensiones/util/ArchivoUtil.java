package co.eam.colpensiones.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArchivoUtil {

  public static void moverArchivos(String rutaOrigen, String rutaDestino) throws IOException {
    File carpetaOrigen = new File(rutaOrigen);
    File carpetaDestino = new File(rutaDestino);

    //Si la carpeta destino NO existe, entonces se crea.
    if (!carpetaOrigen.exists()) {
      carpetaOrigen.mkdir();
    }

    // Se obtiene todos los archivos en la carpeta "SolicitudesEntrantes"
    File[] archivos = carpetaOrigen.listFiles();
    if (archivos != null) {

      for (File archivo : archivos) {
        if (archivo.isFile()) {
          Path origen = archivo.toPath();
          Path destino = new File(carpetaDestino, archivo.getName()).toPath();

          // Mover los archivos a la carpeta destino
          Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        }
      }
    }
  }

  public static void modificarCampoCSV(String archivoCsv, int filaIndice, int columnaIndice, String nuevoValor)
      throws IOException, CsvException {

    File archivo = new File(archivoCsv);
    if (!archivo.exists()) {
      return;
    }

    try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
      List<String[]> registros = reader.readAll();

      if (filaIndice < 0 || filaIndice >= registros.size()) {
        throw new IllegalArgumentException("Índice de fila fuera de rango");
      }

      String[] fila = registros.get(filaIndice);
      if (columnaIndice < 0 || columnaIndice >= fila.length) {
        throw new IllegalArgumentException("Índice de columna fuera de rango");
      }

      fila[columnaIndice] = nuevoValor;

      try (CSVWriter writer = new CSVWriter(new FileWriter(archivoCsv))) {
        writer.writeAll(registros);
      }
    }
  }

  public static void crearDirectorio(String rutaDirectorio) {
    Path path = Paths.get(rutaDirectorio);
    if (!Files.exists(path)) {

      try {

        Files.createDirectories(path);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void moverArchivo(String rutaOrigen, String rutaDestino) {
    Path origen = Paths.get(rutaOrigen);
    Path destino = Paths.get(rutaDestino);

    try {
      Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException e) {
      log.error("Error al mover el archivo: {}", e.getMessage());
    }
  }
}
