package main.java.logica.Clases;

import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;

import java.time.LocalDate;
import java.util.HashSet;
import main.java.logica.Clases.OfertaLaboral;


public class Postulante extends Usuario{

    private LocalDate fecha_nac;
    private String nacionalidad;
    private HashSet<Postulacion> postulaciones;


    public Postulante(String nickname, String contrasena, String nombre, String apellido, String correo_electronico, LocalDate fecha_nac, String nacionalidad,byte[] img) {
    	super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.postulaciones = new HashSet<Postulacion>();
    }
    
    public Postulante(String nickname, String contraseña, String nombre, String apellido, String correo_electronico, LocalDate fecha_nac, String nacionalidad) {
        super(nickname, nombre, apellido, correo_electronico, contraseña);
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.postulaciones = new HashSet<Postulacion>();
    }

    public boolean esEmpresa() {
        return false;
    }

    /* + obtenerDatosUsuario(nick : String): DTUsuario */

    public DTUsuario obtenerDatosUsuario() {
    	DTPostulante postul = new DTPostulante(this.getNickname(), this.getCorreo_electronico(), this.getApellido(), this.getNombre(),this.getImagen(), fecha_nac, nacionalidad);
        return postul;
    }

    /* + crearPostulacion (cv : String, motivacion: String, fecha : DTFecha) : Postulacion */

    public Postulacion crearPostulacion (String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
        Postulacion p = new Postulacion(this, cv, motivacion, fecha, URLDocExtras, OferLab);
        postulaciones.add(p);
        return p;
    }

    public HashSet<String> obtenerPostulantesDeOferta(String oferta){
        HashSet<String> lista = new HashSet<String>();
        if(postulaciones != null) {
            for (Postulacion p : postulaciones){
                if (p.obtenerNombreOfertaLaboral().equals(oferta)) {
                    lista.add(getNombre()); 
                    return lista;
                }
            }
        }
        return lista;
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
//    
//    public boolean editarPostulacion(nombre : String, cvAbreviado: String, motivacion: String) {
//    	
//    }
    
    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
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

	@Override
	public DTUsuario obtenerDatosUsuarioEspecial() {
		// FALTA SE ARREGLE GIT Y ESTÁ EN LOS DE MATI
		return null;
	}
}
