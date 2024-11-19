package co.eam.colpensiones.modeloEntidades;

public class Pais {

    private Integer idPais;
    private String nombrePais;

    public Pais() {
        this.idPais = null;
        this.nombrePais = null;
    }

    public Pais(Integer idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "idPais=" + idPais +
                ", nombrePais='" + nombrePais + '\'' +
                '}';
    }
}
