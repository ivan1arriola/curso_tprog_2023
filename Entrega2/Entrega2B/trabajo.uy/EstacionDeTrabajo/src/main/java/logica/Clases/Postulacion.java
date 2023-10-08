package main.java.logica.clases;

import java.time.LocalDate;

import main.java.logica.datatypes.DTPostulacion;


public class Postulacion {
    // atributos
    private LocalDate fecha;
    private String CV;
    private String motivacion;
    private String URLDocExtras;
    // relaciones
    private OfertaLaboral OferLab;
    private Postulante p;

    // constructor
    public Postulacion(Postulante p, String CV, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
        this.p = p;
        this.fecha = fecha;
        this.CV = CV;
        this.motivacion = motivacion;
        this.URLDocExtras = URLDocExtras;
        this.OferLab = OferLab;
    }


	// GETTERS
    public LocalDate     getFecha() { 
    	return fecha;
    }
    
    public String 	     getCV() {
    	return CV;
    }
    
    public String 	     getMotivacion() {
    	return motivacion;
    }
    
    public String 	     getURLDocExtras() {
    	return URLDocExtras;
    }
    
    public OfertaLaboral getOfertaLaboral()	{
    	return OferLab;
    }
    
    public Postulante    getPostulante() {
    	return p;
    }


    // SETTERS
    public void setFecha(LocalDate fecha) { 
    	this.fecha = fecha;
    } 
    
    public void setCV(String CV) {
    	this.CV = CV;
    }	
     
    public void setMotivacion(String motivacion) {
    	this.motivacion = motivacion;
    }
     
    public void setURLDocExtras(String URLDocExtras) {
    	this.URLDocExtras = URLDocExtras;
    }
    
    public void setOfertaLaboral(OfertaLaboral OferLab)	{
    	this.OferLab = OferLab; 
    }
    
    public void setPostulante(Postulante p) {
    	this.p = p; 
    }
    
    // METODOS
    public DTPostulacion obtenerDT() {
        DTPostulacion dtpostu = new DTPostulacion(p.getNickname(), fecha, URLDocExtras, CV, motivacion);
        return dtpostu;
    } 

    public String obtenerNombreOfertaLaboral() { 
    	return OferLab.getNombre();
    }

    public void editarPostulacion(String cvAbreviado,String motivacion) {
        this.CV = cvAbreviado;
        this.motivacion = motivacion;
    }

    // dentro caso uso postulacion a oferta laboral
    public Boolean esPostulacion(String nombre){
        return OferLab.getNombre().equals(nombre); // retorna true si el nombre de la oferta es igual al nombre que se le pasa por parametro
    }

    public String obtenerNicknamePostulante() { 
    	return p.getNickname();
    }
}
