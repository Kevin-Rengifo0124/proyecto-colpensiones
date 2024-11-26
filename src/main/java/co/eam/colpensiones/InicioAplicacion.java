package co.eam.colpensiones;

import static co.eam.colpensiones.config.Config.*;

import co.eam.colpensiones.entidad.Solicitud;
import co.eam.colpensiones.repositorio.archivos.ManejadorArchivo;
import co.eam.colpensiones.repositorio.archivos.impl.GestionArchivoCaracterizacion;
import co.eam.colpensiones.repositorio.archivos.impl.GestionArchivoSolicitud;
import co.eam.colpensiones.repositorio.cache.SuperCache;
import co.eam.colpensiones.repositorio.cronjob.ProgramacionTarea;
import co.eam.colpensiones.repositorio.cronjob.impl.RevisionSolicitud;
import co.eam.colpensiones.repositorio.cronjob.impl.ArchivoCaracterizacion;
import co.eam.colpensiones.repositorio.cronjob.impl.ArchivoSolicitud;
import co.eam.colpensiones.repositorio.csv.impl.SolicitudDao;
import co.eam.colpensiones.repositorio.queue.IQueue;
import co.eam.colpensiones.repositorio.queue.impl.Queue;
import co.eam.colpensiones.servicio.Gestionable;
import co.eam.colpensiones.servicio.impl.GestionCaracterizacion;
import co.eam.colpensiones.servicio.impl.GestionSolicitud;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class InicioAplicacion {

  private static final String TRASLADO_ARCHIVOS_SOLICITUD = "trasladoArchivosSolicitud";

  private static final String TRASLADO_ARCHIVOS_CARACTERIZACION = "trasladoArchivosCaracterizacion";

  private static final int RETRASO_INICIAL = 0;

  /**
   * Método principal para iniciar la aplicación.
   */
  public static void main(String[] args){
    SuperCache superCache = new SuperCache();
    ejecutarTareaTrasladoArchivos(TRASLADO_ARCHIVOS_CARACTERIZACION, superCache,1, TimeUnit.MINUTES);
    ejecutarTareaTrasladoArchivos(TRASLADO_ARCHIVOS_SOLICITUD, superCache,2, TimeUnit.MINUTES);
    ejecutarTareaGestionSolicitud(superCache, 1, TimeUnit.MINUTES);
  }

  private static void ejecutarTareaTrasladoArchivos(String tarea, SuperCache superCache, int periocidad, TimeUnit unidadTiempo) {
    ManejadorArchivo manejadorArchivo = obtenerManejadorArchivo(tarea, superCache);
    ProgramacionTarea tareaProgramada = obtenerTareaProgramada(tarea, manejadorArchivo);
    tareaProgramada.ejecutarProceso(RETRASO_INICIAL, periocidad, unidadTiempo);
  }

  private static ManejadorArchivo obtenerManejadorArchivo(String proceso, SuperCache cache) {
    switch (proceso) {
      case TRASLADO_ARCHIVOS_SOLICITUD:
        return new GestionArchivoSolicitud(cache);
      case TRASLADO_ARCHIVOS_CARACTERIZACION:
        return new GestionArchivoCaracterizacion(cache);
      default:
        throw new IllegalArgumentException("Tipo de proceso desconocido: " + proceso);
    }
  }

  private static ProgramacionTarea obtenerTareaProgramada(String proceso, ManejadorArchivo manejadorArchivo) {

    switch (proceso) {
      case TRASLADO_ARCHIVOS_SOLICITUD:
        return new ArchivoSolicitud(manejadorArchivo);
      case TRASLADO_ARCHIVOS_CARACTERIZACION:
        return new ArchivoCaracterizacion(manejadorArchivo);
      default:
        throw new IllegalArgumentException("Tipo de proceso desconocido: " + proceso);
    }
  }

  @SneakyThrows
  private static void ejecutarTareaGestionSolicitud(SuperCache cache, int periocidad, TimeUnit unidadTiempo) {
    IQueue<Solicitud> colaCotizantes = new Queue<>();
    SolicitudDao solicitudDao = new SolicitudDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    GestionCaracterizacion gestionCaracterizacion = new GestionCaracterizacion(cache);

    Gestionable gestionSolicitud = new GestionSolicitud(cache, solicitudDao, gestionCaracterizacion, colaCotizantes);
    ProgramacionTarea programacionTarea = new RevisionSolicitud(gestionSolicitud);
    programacionTarea.ejecutarProceso(RETRASO_INICIAL, periocidad, unidadTiempo);
  }
}
