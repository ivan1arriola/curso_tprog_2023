package logica.Datatypes;

import java.time.LocalDate;

public class DTPostulacion {
	private LocalDate fecha;
	private String URLDocExtras;
	private String CV;
	private String motivacion;

	public DTPostulacion(LocalDate f, String URLDE, String CV, String motivacion) {
		fecha = f;
		URLDocExtras = URLDE;
		this.CV = CV;
		this.motivacion = motivacion;
	}
	
    public LocalDate getFecha() 	{ return fecha; }
    public String getURLDocExtras() { return URLDocExtras; }
    public String getCV() 			{ return CV; }
    public String getMotivacion() 	{ return motivacion; }

}
