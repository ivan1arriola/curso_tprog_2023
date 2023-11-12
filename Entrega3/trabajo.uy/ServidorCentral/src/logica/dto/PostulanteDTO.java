package logica.dto;

//import jakarta.persistence.CascadeType;
//import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.Lob;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.DiscriminatorColumn;
//import jakarta.persistence.DiscriminatorType;

import java.time.LocalDate;

@Entity
@Table(name = "Postulantes")
@DiscriminatorValue("Postulante")
public class PostulanteDTO extends UsuarioDTO{

    @Column(nullable = false)
    private String nacionalidad;
    @Column(nullable = false)
    private LocalDate fechanacimiento;

    public PostulanteDTO(){
        super();
    }

    public PostulanteDTO(String nickname,  String email,  String nombre,  String apellido,  String nacionalidad,  LocalDate fechanacimiento) {
        super(nickname,  email,  nombre,  apellido);
        this.nacionalidad = nacionalidad;
        this.fechanacimiento = fechanacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
