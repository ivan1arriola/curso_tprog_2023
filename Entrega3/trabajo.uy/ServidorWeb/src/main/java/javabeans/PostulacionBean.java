package javabeans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostulacionBean {
    private String nicknamePostulante;
    private LocalDate fecha;
    private String uRLDocExtras;
    private String cVitae;
    private String motivacion;
	private String nombreOfertaLaboral;
	private String estado;

    public PostulacionBean() {
        this.setNicknamePostulante(null);
        this.nombreOfertaLaboral = null;
        this.fecha = null;
        this.uRLDocExtras = null;
        this.cVitae = null;
        this.motivacion = null;
    }



    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getFechaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return fecha.format(formatter);
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

	public String getNombreOfertaLaboral() {
		return nombreOfertaLaboral;
	}

	public void setNombreOfertaLaboral(String nombreOfertaLaboral) {
		this.nombreOfertaLaboral = nombreOfertaLaboral;
	}



	public String getNicknamePostulante() {
		return nicknamePostulante;
	}



	public void setNicknamePostulante(String nicknamePostulante) {
		this.nicknamePostulante = nicknamePostulante;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}
}
