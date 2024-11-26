package co.eam.colpensiones.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoFondo {

  PUBLICO("fondo publico"),
  PRIVADO("fondo privado");

  private String valor;
}
