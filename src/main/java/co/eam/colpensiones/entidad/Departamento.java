package co.eam.colpensiones.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    private Integer idDepartamento;
    private Integer idPais;
    private String nombreDepartamento;
}
