package co.eam.colpensiones.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudad {

    private Integer idCiudad;
    private Integer idDepartamento;
    private String nombreCiudad;
}
