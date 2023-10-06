package main.java.logica.Datatypes;

import java.time.LocalDate;

public class DTPostulante extends DTUsuario {

    private LocalDate fecha_nac;
    private String nacionalidad;

    public DTPostulante(String nickname, String correo_electronico, String apellido, String nombre, byte[] imagen, LocalDate fechaNac, String nacionalidad) {
        super(nickname, correo_electronico, apellido, nombre, nacionalidad, imagen);
        this.fecha_nac = fechaNac;
        this.nacionalidad = nacionalidad;
    }    

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }


}