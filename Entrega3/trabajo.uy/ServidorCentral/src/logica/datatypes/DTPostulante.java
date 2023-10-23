package logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

public class DTPostulante extends DTUsuario {

    private LocalDate fechanac;
    private String nacionalidad;

    public DTPostulante(String nickname,  String correo_electronico,  String apellido,  String nombre,  String contraseña,  byte[]  imagen,  LocalDate fechaNac,  String nacionalidad, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        super(nickname,  correo_electronico,  apellido,  nombre,  contraseña,  imagen, seguidos, seguidores);
        this.fechanac = fechaNac;
        this.nacionalidad = nacionalidad;
    }    

    public LocalDate getFechaNac() {
        return fechanac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }


}