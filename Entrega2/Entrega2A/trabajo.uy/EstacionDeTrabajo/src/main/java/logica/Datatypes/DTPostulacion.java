package main.java.logica.Datatypes;

import java.time.LocalDate;

public class DTPostulacion {
	private String nombrePostulante;
	private LocalDate fecha;
	private String URLDocExtras;
	private String CV;
	private String motivacion;

	public DTPostulacion(String nomb_p, LocalDate f, String URLDE, String CV, String motivacion) {
		nombrePostulante = nomb_p;
		fecha = f;
		URLDocExtras = URLDE;
		this.CV = CV;
		this.motivacion = motivacion;
	}
	
	public String getPostulante() 	{ return nombrePostulante; }
    public LocalDate getFecha() 	{ return fecha; }
    public String getURLDocExtras() { return URLDocExtras; }
    public String getCV() 			{ return CV; }
    public String getMotivacion() 	{ return motivacion; }

}
