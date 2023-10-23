package logica.datatypes;

import java.time.LocalDate;

public class DTPostulacion {
	private String nombrePostulante;
	private LocalDate fecha;
	private String uRLDocExtras;
	private String cVitae;
	private String motivacion;
	private String urlVideo;

	public 
	DTPostulacion(String nomb_p,  LocalDate fecha,  String URLDE,  String cVitae,  String motivacion, String urlVid) {
		nombrePostulante = nomb_p;
		this.fecha = fecha;
		uRLDocExtras = URLDE;
		this.cVitae = cVitae;
		this.motivacion = motivacion;
		urlVideo = urlVid;
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

}
