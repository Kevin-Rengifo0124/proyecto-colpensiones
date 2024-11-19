package co.eam.colpensiones.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GestorFechas {

    public static String obtenerFechaActual() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String obtenerFechaAyer() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
}
