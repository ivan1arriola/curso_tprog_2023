package logica.dto;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Empresas")
@DiscriminatorValue("Empresa")
public class EmpresaDTO extends UsuarioDTO {

    @Column(nullable = false,  columnDefinition = "CLOB")
    private String descripcion;

    private String sitioweb;

    public EmpresaDTO() {
        super();
    }

    public EmpresaDTO(String nickname,  String email,  String nombre,  String apellido,  String descripcion,  String sitio_web) {
        super(nickname,  email,  nombre,  apellido);
        this.descripcion = descripcion;
        this.sitioweb = sitioweb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitioweb() {
        return sitioweb;
    }

    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }
}
