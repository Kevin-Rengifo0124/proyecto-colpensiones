package co.eam.colpensiones.servicio.impl;

import co.eam.colpensiones.config.Config;
import co.eam.colpensiones.entidad.Cotizante;
import co.eam.colpensiones.entidad.Solicitud;
import co.eam.colpensiones.enums.EstadoSolicitud;
import co.eam.colpensiones.enums.TipoCaracterizacion;
import co.eam.colpensiones.enums.TipoFondo;
import co.eam.colpensiones.repositorio.cache.SuperCache;
import co.eam.colpensiones.repositorio.csv.impl.SolicitudDao;
import co.eam.colpensiones.repositorio.queue.IQueue;
import co.eam.colpensiones.servicio.Gestionable;
import co.eam.colpensiones.util.ArchivoUtil;
import co.eam.colpensiones.util.FechaUtil;

import java.time.LocalDate;
import java.util.LinkedList;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GestionSolicitud implements Gestionable {

  private final IQueue<Solicitud> colaCotizantes;

  private final SuperCache cache;

  private final SolicitudDao solicitudDao;

  private final GestionCaracterizacion gestionCaracterizacion;

  public GestionSolicitud(SuperCache cache, SolicitudDao solicitudDao, GestionCaracterizacion gestionCaracterizacion,
      IQueue<Solicitud> colaCotizantes) {
    this.cache = cache;
    this.solicitudDao = solicitudDao;
    this.gestionCaracterizacion = gestionCaracterizacion;
    this.colaCotizantes = colaCotizantes;
  }

  @Override
  public void procesarSolicitud() {

    gestionCaracterizacion.procesarSolicitud();

    LinkedList<Solicitud> solicitudes = cache.obtenerDatosPorArchivo("solicitudes");
    if (solicitudes == null) {
      log.info("No hay nuevas solicitudes para procesar.");
      return;
    }

    int fila = 1;
    int indiceColumnaEstado = 10;
    for (Solicitud solicitud : solicitudes) {
      revisarSolicitud(solicitud);

      if (EstadoSolicitud.APROBADA.equals(solicitud.getEstado())) {
        colaCotizantes.enQueue(solicitud);
      }

      // Primera validación, validación solicitante inhabilitado
      validarCotizanteInhabilitado(solicitud);

      // Segunda validación: validación solicitante embargado
      validarCotizanteEmbargado(solicitud);

      // Actualiza la columna estado de la solicitud
      solicitudDao.modificarEstadoSolicitud(fila++, indiceColumnaEstado, solicitud.getEstado().getValor());
    }

    trasladarArchivoSolicitudProcesada();
    mostrarListasNegras();
  }

  private void revisarSolicitud(Solicitud solicitud) {
    // Si el solicitante está en la lista negra o es pre-pensionado, se rechaza directamente
    if (solicitud.getEnListaNegra() || solicitud.getEsPrePensionado()) {
      solicitud.setEstado(EstadoSolicitud.RECHAZADA);
      return;
    }

    // Si pertenece a una institución pública, validar esa institución
    if (solicitud.getPerteneceInstitucionPublica()) {
      validarInstitucionPublica(solicitud);
    } else {
      // Si no pertenece a una institución pública, se valida su nacimiento y residencia
      validarNacimientoYResidencia(solicitud);
    }
  }

  private void validarInstitucionPublica(Solicitud solicitud) {
    switch (solicitud.getTipoInstitucion().toLowerCase()) {
      case "armada", "minsalud", "mininterior":
        solicitud.setEstado(EstadoSolicitud.APROBADA);
        break;
      case "policia":
        solicitud.setEstado(solicitud.getEdad() > 18 ? EstadoSolicitud.APROBADA : EstadoSolicitud.RECHAZADA);
        break;
      default:
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);
    }
  }

  private void validarNacimientoYResidencia(Solicitud solicitud) {
    Set<String> ciudadesConResidenciaRestrigida = Set.of("Bogota", "Medellin", "Cali");

    // Verificar si la ciudad de nacimiento y residencia están dentro de las ciudades restringidas
    if (ciudadesConResidenciaRestrigida.contains(solicitud.getCiudad()) &&
        ciudadesConResidenciaRestrigida.contains(solicitud.getCiudadResidencia())) {
      solicitud.setEstado(EstadoSolicitud.RECHAZADA);
      return;
    }

    validarRegimenPrimaMedia(solicitud);
  }

  private void validarRegimenPrimaMedia(Solicitud solicitud) {
    int edad = solicitud.getEdad();

    if (edad > 60) {
      solicitud.setEstado(EstadoSolicitud.APROBADA);
      return;
    }

    // Si la edad está entre 18 y 60
    if (edad > 18 && edad <= 60) {
      // Validación según el fondo de origen
      validarFondo(solicitud);
    }

    // Condición: No ha alcanzado edad para aplicar al Régimen de Prima Media
    if (solicitud.getEdad() > 18 && solicitud.getEdad() <= 60) {

      if (solicitud.getFondoOrigen().toLowerCase().equals(TipoFondo.PRIVADO.getValor())) {
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);

      }else if(solicitud.getFondoOrigen().toLowerCase().equals(TipoFondo.PUBLICO.getValor())) {
        solicitud.setEstado(EstadoSolicitud.APROBADA);
      }

    } else if (solicitud.getEdad() > 60) {
      // Condición: Alcanzó la edad para aplicar al RPM
      solicitud.setEstado(EstadoSolicitud.APROBADA);
    }
  }

  private void validarFondo(Solicitud solicitud) {
    String fondoOrigen = solicitud.getFondoOrigen().toLowerCase();

    // Verifica el tipo de fondo de origen y actualiza el estado de la solicitud
    if (fondoOrigen.equals(TipoFondo.PRIVADO.getValor())) {
      solicitud.setEstado(EstadoSolicitud.RECHAZADA);
    } else if (fondoOrigen.equals(TipoFondo.PUBLICO.getValor())) {
      solicitud.setEstado(EstadoSolicitud.APROBADA);
    } else {
      log.warn("Fondo de origen no reconocido para la solicitud con ID: {}", solicitud.getIdSolicitud());
    }
  }

  private boolean validarCaracterizacionCotizante(Solicitud solicitud, TipoCaracterizacion tipoCaracterizacion) {

    LinkedList<Cotizante> cotizantes = cache.obtenerDatosPorArchivo("cotizantes");
    for (Cotizante cotizante : cotizantes) {
      if (cotizante != null && cotizante.getNumeroDocumento() != null) {
        if (solicitud.getNumeroDocumento().equals(cotizante.getNumeroDocumento()) &&
            tipoCaracterizacion.equals(cotizante.getCaracterizacion().getTipoCaracterizacion())) {
          return true;
        }
      }
    }
    return false;
  }

  private void validarCotizanteInhabilitado(Solicitud solicitud) {
    LinkedList<Cotizante> listaNegraCotizantesInhabilitados = cache.obtenerDatosPorArchivo("cotizantesInhabilitados");
    if (listaNegraCotizantesInhabilitados == null) {
      listaNegraCotizantesInhabilitados = new LinkedList<>();
    }

    // Primera validación, validación solicitante inhabilitado
    if (EstadoSolicitud.RECHAZADA.equals(solicitud.getEstado()) &&
            validarCaracterizacionCotizante(solicitud, TipoCaracterizacion.INHABILITAR)) {
      listaNegraCotizantesInhabilitados.add(solicitud.getCotizante());
    }

    if (!cache.existeArchivoEnCache("cotizantesInhabilitados")) {
      cache.agregarArchivoACache("cotizantesInhabilitados", listaNegraCotizantesInhabilitados);
    }
  }

  private void validarCotizanteEmbargado(Solicitud solicitud) {
    LinkedList<Cotizante> listaNegraCotizantesEmbargados = cache.obtenerDatosPorArchivo("cotizantesEmbargados");
    if (listaNegraCotizantesEmbargados == null) {
      listaNegraCotizantesEmbargados = new LinkedList<>();
    }

    if (EstadoSolicitud.APROBADA.equals(solicitud.getEstado()) &&
            validarCaracterizacionCotizante(solicitud, TipoCaracterizacion.EMBARGAR)) {
      listaNegraCotizantesEmbargados.add(solicitud.getCotizante());
    }

    if (!cache.existeArchivoEnCache("cotizantesEmbargados")) {
      cache.agregarArchivoACache("cotizantesEmbargados", listaNegraCotizantesEmbargados);
    }
  }

  private void trasladarArchivoSolicitudProcesada() {
    String rutaArchivo = Config.RUTA_ARCHIVO_SOLICITUDES_PROCESADAS + FechaUtil.formatearFecha(LocalDate.now());
    ArchivoUtil.crearDirectorio(rutaArchivo);
    ArchivoUtil.moverArchivo(Config.RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO + "/solicitud.csv", rutaArchivo + "/solicitud.csv");
  }

  private void mostrarListasNegras() {
    System.out.println("================= LISTA NEGRA DE COTIZANTES INHABILITADOS =================");
    cache.imprimirLista("cotizantesInhabilitados");

    System.out.println("\n\n================= LISTA NEGRA DE COTIZANTES EMBARGADOS =================");
    cache.imprimirLista("cotizantesEmbargados");
  }
}
