package co.eam.colpensiones.servicio.impl;

import co.eam.colpensiones.entidad.Caracterizacion;
import co.eam.colpensiones.entidad.Cotizante;
import co.eam.colpensiones.entidad.Solicitud;
import co.eam.colpensiones.repositorio.cache.SuperCache;
import co.eam.colpensiones.servicio.Gestionable;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GestionCaracterizacion implements Gestionable {

  private final SuperCache cache;

  public GestionCaracterizacion(SuperCache cache) {
    this.cache = cache;
  }

  @Override
  public void procesarSolicitud() {

    LinkedList<Cotizante> cotizantes = cache.obtenerDatosPorArchivo("cotizantes");
    if (cotizantes == null) {
      return;
    }

    for (Cotizante cotizante : cotizantes) {
      mapearCaracterizacionCotizante(cotizante);
      mapearCotizanteSolicitud(cotizante);
    }
  }

  private void mapearCaracterizacionCotizante(Cotizante cotizante) {

    LinkedList<Caracterizacion> caracterizaciones =  cache.obtenerDatosPorArchivo("caracterizaciones");

    for (Caracterizacion caracterizacion : caracterizaciones) {
      if (caracterizacion.getNumeroDocumento().equals(cotizante.getNumeroDocumento())) {
        cotizante.setCaracterizacion(caracterizacion);
      }
    }
  }

  private void mapearCotizanteSolicitud(Cotizante cotizante) {

    LinkedList<Solicitud> solicitudes =  cache.obtenerDatosPorArchivo("solicitudes");

    for (Solicitud solicitud: solicitudes) {
      if (cotizante.getNumeroDocumento().equals(solicitud.getNumeroDocumento())) {
        solicitud.setCotizante(cotizante);
      }
    }
  }
}
