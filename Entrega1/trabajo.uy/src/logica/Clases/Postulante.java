package logica.Clases;

import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;

import java.time.LocalDate;
import java.util.HashSet;
import logica.Clases.OfertaLaboral;


public class Postulante extends Usuario{

    private LocalDate fecha_nac;
    private String nacionalidad;
    private HashSet<Postulacion> postulaciones;


    public Postulante(String nickname, String nombre, String apellido, String correo_electronico, LocalDate fecha_nac, String nacionalidad) {
        super(nickname, nombre, apellido, correo_electronico);
        this.nacionalidad = nacionalidad;
        this.postulaciones = new HashSet<Postulacion>();
        
        if (LocalDate.now().isBefore(fecha_nac)) {throw new IllegalArgumentException("Fecha de nacimiento debe ser posterior a fecha actual");}
        else { this.fecha_nac = fecha_nac; }
    }

    public boolean esEmpresa() {
        return false;
    }

    /* + obtenerDatosUsuario(nick : String): DTUsuario */

    public DTUsuario obtenerDatosUsuario() {
    	DTPostulante postul = new DTPostulante(this.getNickname(), this.getCorreo_electronico(), this.getApellido(), this.getNombre(), fecha_nac, nacionalidad);
        return postul;
    }

    /* + crearPostulacion (cv : String, motivacion: String, fecha : DTFecha) : Postulacion */

    public Postulacion crearPostulacion (String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
    	
    	if (fecha.isBefore(OferLab.getFecha_de_alta())) { throw new IllegalArgumentException("Postulación debe ser posterior al Alta de Oferta");}
    	else { 
    		Postulacion p = new Postulacion(this, cv, motivacion, fecha, URLDocExtras, OferLab);
    	
        postulaciones.add(p);
        return p;
    	}
    }

    /* + existePostulacion(nombre : String) : Bool */
    public boolean existePostulacion(String nombre) {
        for (Postulacion postulacion : postulaciones) {
            String nombreOferta = postulacion.obtenerNombreOfertaLaboral();
            if (nombreOferta.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
    	if (fecha_nac.isAfter(LocalDate.now())) { throw new IllegalArgumentException("Fecha de nacimiento debe ser anterior a fecha actual"); 
    	} else { 
        this.fecha_nac = fecha_nac;
    	}
        
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public HashSet<String> listarOfertasLaborales(){
        HashSet<String> lista = new HashSet<String>();
        
        if(postulaciones!=null) {
	        for( Postulacion p : postulaciones){
	            lista.add(p.obtenerNombreOfertaLaboral());
	        }
        }

        return lista;
    }
}
