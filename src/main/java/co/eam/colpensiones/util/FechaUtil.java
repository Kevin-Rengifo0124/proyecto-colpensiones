package co.eam.colpensiones.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaUtil {

  public static String formatearFecha(LocalDate fechaActual){
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy_MM_dd");
    return fechaActual.format(formato);
  }
}
