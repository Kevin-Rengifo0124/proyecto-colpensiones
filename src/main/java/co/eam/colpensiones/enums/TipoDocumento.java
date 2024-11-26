package co.eam.colpensiones.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {

    CC("CC"),
    TI("TI"),
    CE("CE");

    private String valor;
}
