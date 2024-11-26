package co.eam.colpensiones.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoSolicitud {

    GENERADA("Generada"),
    APROBADA("Aprobada"),
    RECHAZADA("Rechazada"),
    INHABILIDAR("Inhabilitar"),
    EMBARGADA("Embargada");

    private String valor;
}
