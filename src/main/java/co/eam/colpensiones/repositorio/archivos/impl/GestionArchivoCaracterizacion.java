package co.eam.colpensiones.repositorio.archivos.impl;

import static co.eam.colpensiones.config.Config.RUTA_ARCHIVO_CARACTERIZACIONES_EN_PROCESAMIENTO;

import co.eam.colpensiones.entidad.Caracterizacion;
import co.eam.colpensiones.repositorio.archivos.ManejadorArchivo;
import co.eam.colpensiones.repositorio.cache.SuperCache;
import co.eam.colpensiones.repositorio.csv.impl.CaracterizacionDao;
import java.io.IOException;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GestionArchivoCaracterizacion implements ManejadorArchivo {

  private final SuperCache cache;

  public GestionArchivoCaracterizacion(SuperCache cache) {
    this.cache = cache;
  }

  @Override
  public void leerArchivosSolicitud() {
    try {

      cache.agregarArchivoACache("caracterizaciones", leerArchivoCaracterizacion());

    } catch (IOException e) {
      log.error("Error al leer los archivos de caracterización: " + e.getMessage());
    }
  }

  private LinkedList<Caracterizacion> leerArchivoCaracterizacion() throws IOException {
    CaracterizacionDao caracterizacionDao = new CaracterizacionDao(RUTA_ARCHIVO_CARACTERIZACIONES_EN_PROCESAMIENTO);
    return caracterizacionDao.obtenerTodosLosDatos();
  }
}
