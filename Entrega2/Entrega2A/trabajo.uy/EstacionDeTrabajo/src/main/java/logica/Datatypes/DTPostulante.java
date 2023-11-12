package main.java.logica.Datatypes;

import java.time.LocalDate;
import java.util.Date;

public class DTPostulante extends DTUsuario {

    private LocalDate fecha_nac;
    private String nacionalidad;

    @Override
    public String toString() {
        return "Fecha Nacimiento:" + fecha_nac +
                ", nacionalidad='" + nacionalidad + '\n';
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public DTPostulante(String nickname, String correo_electronico, String apellido, String nombre, LocalDate fecha_nac2, String nacionalidad) {
        super(nickname, correo_electronico, apellido, nombre);
        this.fecha_nac = fecha_nac2;
        this.nacionalidad = nacionalidad;
    }
}