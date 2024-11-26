package co.eam.colpensiones.entidad;

import co.eam.colpensiones.enums.TipoCaracterizacion;
import co.eam.colpensiones.enums.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Caracterizacion {

    private Integer IdCaracterizacion;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private TipoCaracterizacion tipoCaracterizacion;
}
