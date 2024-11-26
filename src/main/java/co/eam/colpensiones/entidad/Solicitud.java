package co.eam.colpensiones.entidad;

import co.eam.colpensiones.enums.EstadoSolicitud;
import co.eam.colpensiones.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {

    private Integer idSolicitud;
    private Cotizante cotizante;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private Integer edad;
    private EstadoSolicitud estado;
    private String ciudad;
    private String fondoOrigen;
    private Boolean enListaNegra;
    private Boolean esPrePensionado;
    private Boolean perteneceInstitucionPublica;
    private String tipoInstitucion;
    private String ciudadResidencia;
}
