package logica.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Postulantes")
@DiscriminatorValue("Postulante")
public class PostulanteDTO extends UsuarioDTO{

    @Column(nullable = false)
    private String nacionalidad;
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;

    public PostulanteDTO(){
        super();
    }

    public PostulanteDTO(String nickname,  String email,  String nombre,  String apellido,  String nacionalidad,  LocalDate fecha_nacimiento) {
        super(nickname,  email,  nombre,  apellido);
        this.nacionalidad = nacionalidad;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
