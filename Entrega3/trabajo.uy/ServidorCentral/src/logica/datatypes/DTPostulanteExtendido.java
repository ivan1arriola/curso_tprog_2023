package logica.datatypes;

import java.time.LocalDate;

import java.util.Set;

public class DTPostulanteExtendido extends DTPostulante {

	private Set<DTPostulacion> postulaciones;

    

    public Set<DTPostulacion> getPostulaciones() {
        return postulaciones;
    } 

    public DTPostulanteExtendido(String nickname,  String correo_electronico,  String apellido,  String nombre,  String contraseña,  byte[]  imagen,  LocalDate fechanac,  String nacionalidad,  Set<DTPostulacion> posts) {
        super(nickname,  correo_electronico,  apellido,  nombre,  contraseña,  imagen,  
        	  fechanac,  nacionalidad);
        postulaciones = posts;
    }
}