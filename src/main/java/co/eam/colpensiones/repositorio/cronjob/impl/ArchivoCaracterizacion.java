package co.eam.colpensiones.repositorio.cronjob.impl;

import static co.eam.colpensiones.config.Config.RUTA_ARCHIVO_CARACTERIZACIONES_ENTRANTES;
import static co.eam.colpensiones.config.Config.RUTA_ARCHIVO_CARACTERIZACIONES_EN_PROCESAMIENTO;

import co.eam.colpensiones.repositorio.archivos.ManejadorArchivo;
import co.eam.colpensiones.repositorio.cronjob.ProgramacionTarea;
import co.eam.colpensiones.util.ArchivoUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArchivoCaracterizacion implements ProgramacionTarea {

  private ManejadorArchivo manejadorArchivo;

  public ArchivoCaracterizacion(ManejadorArchivo manejadorArchivo) {
   this.manejadorArchivo = manejadorArchivo;
  }

  @Override
  public void ejecutarProceso(int retrasoInicial, int periodo, TimeUnit unidadTiempo) {

    log.info("Inicia proceso de traslado archivos [Caracterizaciones]");

    var programador = Executors.newSingleThreadScheduledExecutor();

    Runnable tarea = () -> {
      try {

        log.info("En proceso traslado archivos [Caracterizaciones]...");

        ArchivoUtil.moverArchivos(
            RUTA_ARCHIVO_CARACTERIZACIONES_ENTRANTES,
            RUTA_ARCHIVO_CARACTERIZACIONES_EN_PROCESAMIENTO);

        manejadorArchivo.leerArchivosSolicitud();

        log.info("Proceso traslado archivos [Caracterizaciones] completado");

      } catch (Exception e) {
        log.error("Error al procesar los archivos de [Caracterizaciones]: {}", e.getMessage());
        e.printStackTrace();
      }
    };

    programador.scheduleAtFixedRate(tarea, retrasoInicial, periodo, unidadTiempo);
  }
}
