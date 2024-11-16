package co.eam.colpensiones.modelo;

public class Departamento {

    private Integer idDepartamento;
    private Integer idPais;
    private String nombreDepartamento;

    public Departamento() {
        this.idDepartamento = null;
        this.idPais = null;
        this.nombreDepartamento = null;
    }

    public Departamento(Integer idDepartamento, Integer idPais, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.idPais = idPais;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepartamento=" + idDepartamento +
                ", idPais=" + idPais +
                ", nombreDepartamento='" + nombreDepartamento + '\'' +
                '}';
    }
}
