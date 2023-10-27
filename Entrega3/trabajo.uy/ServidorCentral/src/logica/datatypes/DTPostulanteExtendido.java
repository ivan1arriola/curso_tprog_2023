package logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

public class DTPostulanteExtendido extends DTPostulante {

    private Set<DTPostulacion> postulaciones;


    public DTPostulanteExtendido(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, byte[] imagen, LocalDate fechanac, String nacionalidad, Set<DTPostulacion> posts, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        super(nickname, correo_electronico, apellido, nombre, contraseña, imagen,
                fechanac, nacionalidad, seguidos, seguidores);
        postulaciones = posts;
    }

    public Set<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }
}