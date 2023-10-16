package javabeans;

import java.time.LocalDate;

public class PostulacionBean {
    private String nombrePostulante;
    private LocalDate fecha;
    private String uRLDocExtras;
    private String cVitae;
    private String motivacion;

    public PostulacionBean() {
        this.nombrePostulante = null;
        this.fecha = null;
        this.uRLDocExtras = null;
        this.cVitae = null;
        this.motivacion = null;
    }

    public String getNombrePostulante() {
        return nombrePostulante;
    }

    public void setNombrePostulante(String nombrePostulante) {
        this.nombrePostulante = nombrePostulante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getURLDocExtras() {
        return uRLDocExtras;
    }

    public void setURLDocExtras(String uRLDocExtras) {
        this.uRLDocExtras = uRLDocExtras;
    }

    public String getCVitae() {
        return cVitae;
    }

    public void setCVitae(String cVitae) {
        this.cVitae = cVitae;
    }

    public String getMotivacion() {
        return motivacion;
    }

    public void setMotivacion(String motivacion) {
        this.motivacion = motivacion;
    }
}
