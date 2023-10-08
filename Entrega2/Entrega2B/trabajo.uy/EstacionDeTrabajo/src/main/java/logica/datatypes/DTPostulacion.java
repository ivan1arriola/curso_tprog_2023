package main.java.logica.datatypes;

import java.time.LocalDate;

public class DTPostulacion {
	private String nombrePostulante;
	private LocalDate fecha;
	private String uRLDocExtras;
	private String cVitae;
	private String motivacion;

	public 
	DTPostulacion(String nomb_p, LocalDate f, String URLDE, String cVitae, String motivacion) {
		nombrePostulante = nomb_p;
		fecha = f;
		uRLDocExtras = URLDE;
		this.cVitae = cVitae;
		this.motivacion = motivacion;
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
    
    public String getcVitae() {
    	return cVitae;
    }
    
    public String getMotivacion() {
    	return motivacion;
    }

}
