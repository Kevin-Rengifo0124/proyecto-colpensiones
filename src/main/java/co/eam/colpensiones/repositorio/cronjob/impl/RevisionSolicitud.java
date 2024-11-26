package co.eam.colpensiones.repositorio.cronjob.impl;

import co.eam.colpensiones.repositorio.cronjob.ProgramacionTarea;
import co.eam.colpensiones.servicio.Gestionable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RevisionSolicitud implements ProgramacionTarea {

  private Gestionable gestion;

  public RevisionSolicitud(Gestionable gestion) {
    this.gestion = gestion;
  }

  @Override
  public void ejecutarProceso(int retrasoInicial, int periodo, TimeUnit unidadTiempo) {

    log.info("Iniciando proceso revisión solicitudes");

    var programador = Executors.newSingleThreadScheduledExecutor();

    Runnable tarea = () -> {
      try {

        log.info("En proceso revisión solicitudes...");

        gestion.procesarSolicitud();

        log.info("Proceso revisión solicitudes completado");

      } catch (Exception e) {
        log.error("Error al procesar la revisión solicitudes: {}", e.getMessage());
        e.printStackTrace();
      }
    };

    programador.scheduleAtFixedRate(tarea, retrasoInicial, periodo, unidadTiempo);
  }
}
