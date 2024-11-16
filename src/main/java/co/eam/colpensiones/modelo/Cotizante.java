package co.eam.colpensiones.modelo;

public class Cotizante {
    private Integer idCotizante;
    private Integer idCaracterizacion;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private int edad;
    private String caracterizacion;

    public Cotizante() {
        this.idCotizante = null;
        this.idCaracterizacion = null;
        this.tipoDocumento = null;
        this.numeroDocumento = null;
        this.nombreCompleto = null;
        this.edad = 0;
        this.caracterizacion = null;
    }

    public Cotizante(Integer idCotizante, Integer idCaracterizacion, String tipoDocumento, String numeroDocumento, String nombreCompleto, int edad, String caracterizacion) {
        this.idCotizante = idCotizante;
        this.idCaracterizacion = idCaracterizacion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.caracterizacion = caracterizacion;
    }

    public Integer getIdCotizante() {
        return idCotizante;
    }

    public void setIdCotizante(Integer idCotizante) {
        this.idCotizante = idCotizante;
    }

    public Integer getIdCaracterizacion() {
        return idCaracterizacion;
    }

    public void setIdCaracterizacion(Integer idCaracterizacion) {
        this.idCaracterizacion = idCaracterizacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(String caracterizacion) {
        this.caracterizacion = caracterizacion;
    }

    @Override
    public String toString() {
        return "Cotizante{" +
                "idCotizante=" + idCotizante +
                ", idCaracterizacion=" + idCaracterizacion +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", caracterizacion='" + caracterizacion + '\'' +
                '}';
    }
}
