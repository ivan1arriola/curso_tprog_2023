package logica.datatypes;

import java.time.LocalDate;
import java.util.Set;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;

public class DTPostulante extends DTUsuario {
    
    private LocalDate fechanac;
    private String nacionalidad;

    public DTPostulante(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, byte[] imagen, LocalDate fechaNac, String nacionalidad, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        super(nickname, correo_electronico, apellido, nombre, contraseña, imagen, seguidos, seguidores);
        this.fechanac = fechaNac;
        this.nacionalidad = nacionalidad;
    }

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFechaNac() {
        return fechanac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
}
