package logica.Clases;

import java.time.LocalDate;


import logica.Datatypes.DTPostulacion;


public class Postulacion {
	private LocalDate fecha;
	private String CV;
	private String motivacion;
	private String URLDocExtras;
	private OfertaLaboral OferLab;


	
	public Postulacion(String CV, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
        this.fecha = fecha;
        this.CV = CV;
        this.motivacion = motivacion;
        this.URLDocExtras = URLDocExtras;
        this.OferLab = OferLab;
    }


	// GETTERS
	public LocalDate     getFecha() 		{ return fecha; }
    public String 	     getCV() 			{ return CV; }
    public String 	     getMotivacion() 	{ return motivacion; }
    public String 	     getURLDocExtras()	{ return URLDocExtras; }
    public OfertaLaboral getOfertaLaboral()	{ return OferLab; }


    // SETTERS
    public void setNombre(LocalDate fecha) 			 	{ this.fecha = fecha; }
    public void setCV(String CV) 		   			 	{ this.CV = CV; }	
    public void setMotivacion(String motivacion)    	{ this.motivacion = motivacion; }
    public void setURLDocExtras(String URLDocExtras) 	{ this.URLDocExtras = URLDocExtras; }
    public void setOfertaLaboral(OfertaLaboral OferLab)	{ this.OferLab = OferLab; }
	
    
    public DTPostulacion getDTPostulacion() {
    	DTPostulacion dtpostu = new DTPostulacion(fecha, URLDocExtras, CV, motivacion);
    	return dtpostu;
    }
    
    
	public String obtenerNombreOfertaLaboral() { return OferLab.getNombre(); }
}
