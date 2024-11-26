package co.eam.colpensiones.repositorio.archivos.impl;

import static co.eam.colpensiones.config.Config.RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO;

import co.eam.colpensiones.entidad.Ciudad;
import co.eam.colpensiones.entidad.Cotizante;
import co.eam.colpensiones.entidad.Departamento;
import co.eam.colpensiones.entidad.Pais;
import co.eam.colpensiones.entidad.Solicitud;
import co.eam.colpensiones.repositorio.archivos.ManejadorArchivo;
import co.eam.colpensiones.repositorio.cache.SuperCache;
import co.eam.colpensiones.repositorio.csv.impl.CiudadDao;
import co.eam.colpensiones.repositorio.csv.impl.CotizanteDao;
import co.eam.colpensiones.repositorio.csv.impl.DepartamentoDao;
import co.eam.colpensiones.repositorio.csv.impl.PaisDao;
import co.eam.colpensiones.repositorio.csv.impl.SolicitudDao;
import java.io.IOException;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GestionArchivoSolicitud implements ManejadorArchivo {

  private final SuperCache cache;

  public GestionArchivoSolicitud(SuperCache cache) {
    this.cache = cache;
  }

  @Override
  public void leerArchivosSolicitud() {
    try {

      cache.agregarArchivoACache("paises", leerArchivoPaises());
      cache.agregarArchivoACache("departamentos", leerArchivoDepartamentos());
      cache.agregarArchivoACache("ciudades", leerArchivoCiudades());
      cache.agregarArchivoACache("cotizantes", leerArchivoCotizantes());
      cache.agregarArchivoACache("solicitudes", leerArchivoSolicitudes());

    } catch (IOException e) {
      log.error("Error al leer los archivos de solicitud: " + e.getMessage());
    }
  }

  private LinkedList<Pais> leerArchivoPaises() throws IOException {
    PaisDao paisDao = new PaisDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    return paisDao.obtenerTodosLosDatos();
  }

  private LinkedList<Departamento> leerArchivoDepartamentos() throws IOException {
    DepartamentoDao departamentoDao = new DepartamentoDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    return departamentoDao.obtenerTodosLosDatos();
  }

  private LinkedList<Ciudad> leerArchivoCiudades() throws IOException {
    CiudadDao ciudadDao = new CiudadDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    return ciudadDao.obtenerTodosLosDatos();
  }

  private LinkedList<Cotizante> leerArchivoCotizantes() throws IOException {
    CotizanteDao cotizanteDao = new CotizanteDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    return cotizanteDao.obtenerTodosLosDatos();
  }

  private LinkedList<Solicitud> leerArchivoSolicitudes() throws IOException {
    SolicitudDao solicitudDao = new SolicitudDao(RUTA_ARCHIVO_SOLICITUDES_EN_PROCESAMIENTO);
    return solicitudDao.obtenerTodosLosDatos();
  }
}
