package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulante extends DTUsuario {

    private LocalDate fechanac;
    private String nacionalidad;

    public DTPostulante(String nickname,  String correo_electronico,  String apellido,  String nombre,  String contraseña,  byte[]  imagen,  LocalDate fechaNac,  String nacionalidad) {
        super(nickname,  correo_electronico,  apellido,  nombre,  contraseña,  imagen);
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