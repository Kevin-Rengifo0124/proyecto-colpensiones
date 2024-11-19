package co.eam.colpensiones.modeloEntidades;

import co.eam.colpensiones.modeloEnums.TipoCaracterizacion;

public class Caracterizacion {

    private Integer idCaracterizacion;
    private TipoCaracterizacion tipoCaracterizacion;

    public Caracterizacion() {
        this.idCaracterizacion = null;
        this.tipoCaracterizacion = null;
    }

    public Caracterizacion(Integer idCaracterizacion, TipoCaracterizacion tipoCaracterizacion) {
        this.idCaracterizacion = idCaracterizacion;
        this.tipoCaracterizacion = tipoCaracterizacion;
    }

    public Integer getIdCaracterizacion() {
        return idCaracterizacion;
    }

    public void setIdCaracterizacion(Integer idCaracterizacion) {
        this.idCaracterizacion = idCaracterizacion;
    }

    public TipoCaracterizacion getTipoCaracterizacion() {
        return tipoCaracterizacion;
    }

    public void setTipoCaracterizacion(TipoCaracterizacion tipoCaracterizacion) {
        this.tipoCaracterizacion = tipoCaracterizacion;
    }

    @Override
    public String toString() {
        return "Caracterizacion{" +
                "idCaracterizacion=" + idCaracterizacion +
                ", tipoCaracterizacion=" + tipoCaracterizacion +
                '}';
    }
}
