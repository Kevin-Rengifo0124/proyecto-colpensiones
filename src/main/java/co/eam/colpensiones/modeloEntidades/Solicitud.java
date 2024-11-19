package co.eam.colpensiones.modeloEntidades;


import co.eam.colpensiones.modeloEnums.Estado;
import co.eam.colpensiones.modeloEnums.TipoDocumento;

public class Solicitud {

    private Integer idSolicitud;
    private Cotizante cotizante;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private Integer edad;
    private Estado estado;
    private String ciudad;
    private String fondoOrigen;
    private Boolean enListaNegra;
    private Boolean esPrePensionado;
    private Boolean perteneceInstitucionPublica;
    private String tipoInstitucion;
    private String ciudadResidencia;


    public Solicitud() {
        this.idSolicitud = null;
        this.cotizante = null;
        this.tipoDocumento = null;
        this.numeroDocumento = null;
        this.nombreCompleto = null;
        this.edad = null;
        this.estado = Estado.GENERADA;
        this.ciudad = null;
        this.fondoOrigen = null;
        this.enListaNegra = false;
        this.esPrePensionado = false;
        this.perteneceInstitucionPublica = false;
        this.tipoInstitucion = null;
        this.ciudadResidencia = null;

    }

    public Solicitud(Integer idSolicitud, Cotizante cotizante, TipoDocumento tipoDocumento, String numeroDocumento, String nombreCompleto, Integer edad, Estado estado, String ciudad, String fondoOrigen, boolean enListaNegra, boolean esPrePensionado, boolean perteneceInstitucionPublica, String tipoInstitucion, String ciudadResidencia) {
        this.idSolicitud = idSolicitud;
        this.cotizante = cotizante;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.estado = estado;
        this.ciudad = ciudad;
        this.fondoOrigen = fondoOrigen;
        this.enListaNegra = enListaNegra;
        this.esPrePensionado = esPrePensionado;
        this.perteneceInstitucionPublica = perteneceInstitucionPublica;
        this.tipoInstitucion = tipoInstitucion;
        this.ciudadResidencia = ciudadResidencia;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Cotizante getCotizante() {
        return cotizante;
    }

    public void setCotizante(Cotizante cotizante) {
        this.cotizante = cotizante;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        if (edad != null && edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }


    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFondoOrigen() {
        return fondoOrigen;
    }

    public void setFondoOrigen(String fondoOrigen) {
        this.fondoOrigen = fondoOrigen;
    }

    public Boolean getEnListaNegra() {
        return enListaNegra;
    }

    public void setEnListaNegra(Boolean enListaNegra) {
        this.enListaNegra = enListaNegra;
    }

    public Boolean getEsPrePensionado() {
        return esPrePensionado;
    }

    public void setEsPrePensionado(Boolean esPrePensionado) {
        this.esPrePensionado = esPrePensionado;
    }

    public Boolean getPerteneceInstitucionPublica() {
        return perteneceInstitucionPublica;
    }

    public void setPerteneceInstitucionPublica(Boolean perteneceInstitucionPublica) {
        this.perteneceInstitucionPublica = perteneceInstitucionPublica;
    }

    public String getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setTipoInstitucion(String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "idSolicitud=" + idSolicitud +
                ", cotizante=" + cotizante +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", edad=" + edad +
                ", estado=" + estado +
                ", ciudad='" + ciudad + '\'' +
                ", fondoOrigen='" + fondoOrigen + '\'' +
                ", enListaNegra=" + enListaNegra +
                ", esPrePensionado=" + esPrePensionado +
                ", perteneceInstitucionPublica=" + perteneceInstitucionPublica +
                ", tipoInstitucion='" + tipoInstitucion + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                '}';
    }
}
