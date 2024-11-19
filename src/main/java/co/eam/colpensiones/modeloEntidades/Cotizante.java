package co.eam.colpensiones.modeloEntidades;

import co.eam.colpensiones.modeloEnums.TipoDocumento;

public class Cotizante {
    private Integer idCotizante;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private Caracterizacion caracterizacion;

    public Cotizante() {
        this.idCotizante = null;
        this.tipoDocumento = null;
        this.numeroDocumento = null;
        this.nombreCompleto = null;
        this.caracterizacion = null;
    }

    public Cotizante(Integer idCotizante, TipoDocumento tipoDocumento, String numeroDocumento, String nombreCompleto, Caracterizacion caracterizacion) {
        this.idCotizante = idCotizante;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.caracterizacion = caracterizacion;
    }

    public Integer getIdCotizante() {
        return idCotizante;
    }

    public void setIdCotizante(Integer idCotizante) {
        this.idCotizante = idCotizante;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
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

    public Caracterizacion getCaracterizacion() {
        return caracterizacion;
    }

    public void setCaracterizacion(Caracterizacion caracterizacion) {
        this.caracterizacion = caracterizacion;
    }

    @Override
    public String toString() {
        return "Cotizante{" +
                "idCotizante=" + idCotizante +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", caracterizacion=" + caracterizacion +
                '}';
    }
}
