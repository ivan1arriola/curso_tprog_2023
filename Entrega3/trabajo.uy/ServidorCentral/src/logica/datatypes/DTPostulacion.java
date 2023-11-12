package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;

import java.time.LocalDate;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulacion {
    private String nombrePostulante;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fecha;
    private String uRLDocExtras;
    private String cVitae;
    private String motivacion;
    private String urlVideo;
    private Integer clasificacion;
    private String fechaResu;

    public DTPostulacion(String nomb_p, LocalDate fecha, String URLDE, String cVitae, String motivacion, String urlVid, Integer clasif, String fechaR) {
        nombrePostulante = nomb_p;
        this.fecha = fecha;
        uRLDocExtras = URLDE;
        this.cVitae = cVitae;
        this.motivacion = motivacion;
        urlVideo = urlVid;
        setClasificacion(clasif);
        fechaResu = fechaR;
    }

    public String getPostulante() {
        return nombrePostulante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getuRLDocExtras() {
        return uRLDocExtras;
    }

    public String geturlVideo() {
        return urlVideo;
    }

    public String getcVitae() {
        return cVitae;
    }

    public String getMotivacion() {
        return motivacion;
    }

	public Integer getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Integer clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getFechaResu() {
		return fechaResu;
	}

	public void setFechaResu(String fechaResu) {
		this.fechaResu = fechaResu;
	}

}
