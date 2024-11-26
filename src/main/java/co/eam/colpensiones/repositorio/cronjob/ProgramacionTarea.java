package co.eam.colpensiones.repositorio.cronjob;

import java.util.concurrent.TimeUnit;

public interface ProgramacionTarea {

  void ejecutarProceso(int retrasoInicial, int periodo, TimeUnit unidadTiempo);
}
