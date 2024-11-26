package co.eam.colpensiones.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCaracterizacion {

    EMBARGAR("Embargar"),
    INHABILITAR("Inhabilitar");

    private String valor;
}
