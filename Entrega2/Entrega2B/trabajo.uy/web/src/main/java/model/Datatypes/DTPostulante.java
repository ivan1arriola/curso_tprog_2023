package model.Datatypes;

import java.time.LocalDate;

public class DTPostulante extends DTUsuario {

    private LocalDate fecha_nac;
    private String nacionalidad;

    

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public DTPostulante(String nickname, String correo_electronico, String apellido, String nombre, String imagen, LocalDate fecha_nac2, String nacionalidad) {
        super(nickname, correo_electronico, apellido, nombre, imagen);
        this.fecha_nac = fecha_nac2;
        this.nacionalidad = nacionalidad;
    }
}