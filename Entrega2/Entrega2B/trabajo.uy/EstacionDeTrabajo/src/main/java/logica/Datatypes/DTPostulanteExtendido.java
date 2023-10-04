package main.java.logica.Datatypes;

import java.time.LocalDate;
import java.util.HashSet;

public class DTPostulanteExtendido extends DTPostulante {

	private HashSet<DTPostulacion> postulaciones;

    

    public HashSet<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }

    public DTPostulanteExtendido(String nickname, String correo_electronico, String apellido, String nombre, byte[] imagen, LocalDate fecha_nac, String nacionalidad, HashSet<DTPostulacion> posts) {
        super(nickname, correo_electronico, apellido, nombre, imagen, fecha_nac, nacionalidad);
        postulaciones = posts;
    }
}