package trabajoUy.logica.clases;

import java.time.LocalDate;

import trabajoUy.logica.datatypes.DTPostulacion;


public class Postulacion {
    // atributos
    private LocalDate fecha;
    private String curriculumVitae;
    private String motivacion;
    private String uRLDocExtras;
    // relaciones
    private OfertaLaboral oferLab;
    private Postulante postulante;

    // constructor
    public Postulacion(Postulante postulante,   String curriculumVitae,   String motivacion,   LocalDate fecha,   String uRLDocExtras,   OfertaLaboral oferLab) {
        this.postulante = postulante;
        this.fecha = fecha;
        this.curriculumVitae = curriculumVitae;
        this.motivacion = motivacion;
        this.uRLDocExtras = uRLDocExtras;
        this.oferLab = oferLab;
    }


	// GETTERS
    public LocalDate getFecha() { 
    	return fecha;
    }
    
    public String getCV() {
    	return curriculumVitae;
    }
    
    public String getMotivacion() {
    	return motivacion;
    }
    
    public String getuRLDocExtras() {
    	return uRLDocExtras;
    }
    
    public OfertaLaboral getOfertaLaboral()	{
    	return oferLab;
    }
    
    public Postulante getPostulante() {
    	return postulante;
    }


    // SETTERS
    public void setFecha(LocalDate fecha) { 
    	this.fecha = fecha;
    } 
    
    public void setCV(String curriculumVitae) {
    	this.curriculumVitae = curriculumVitae;
    }	
     
    public void setMotivacion(String motivacion) {
    	this.motivacion = motivacion;
    }
     
    public void setuRLDocExtras(String uRLDocExtras) {
    	this.uRLDocExtras = uRLDocExtras;
    }
    
    public void setOfertaLaboral(OfertaLaboral oferLab)	{
    	this.oferLab = oferLab; 
    }
    
    public void setPostulante(Postulante postulante) {
    	this.postulante = postulante; 
    }
    
    // METODOS
    public DTPostulacion obtenerDT() {
        DTPostulacion dtpostu = new DTPostulacion(postulante.getNickname(),   fecha,   uRLDocExtras,   curriculumVitae,   motivacion);
        return dtpostu;
    } 

    public String obtenerNombreOfertaLaboral() { 
    	return oferLab.getNombre();
    }

    public void editarPostulacion(String cvAbreviado,  String motivacion) {
        this.curriculumVitae = cvAbreviado;
        this.motivacion = motivacion;
    }

    // dentro caso uso postulacion a oferta laboral
    public Boolean esPostulacion(String nombre){
        return oferLab.getNombre().equals(nombre); // retorna true si el nombre de la oferta es igual al nombre que se le pasa por parametro
    }

    public String obtenerNicknamePostulante() { 
    	return postulante.getNickname();
    }
}
