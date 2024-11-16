package co.eam.colpensiones.modelo;

public class Ciudad {

    private Integer idCiudad;
    private Integer idDepartamento;
    private String nombreCiudad;

    public Ciudad() {
        this.idCiudad = null;
        this.idDepartamento = null;
        this.nombreCiudad = null;
    }

    public Ciudad(Integer idCiudad, Integer idDepartamento, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.idDepartamento = idDepartamento;
        this.nombreCiudad = nombreCiudad;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", idDepartamento=" + idDepartamento +
                ", nombreCiudad='" + nombreCiudad + '\'' +
                '}';
    }
}
