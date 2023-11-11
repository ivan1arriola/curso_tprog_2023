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

    @Column(nullable = false, columnDefinition = "CLOB")
    private String descripcion;

    private String sitio_web;

    public EmpresaDTO() {
        super();
    }

    public EmpresaDTO(String nickname, String email, String nombre, String apellido, String descripcion, String sitio_web) {
        super(nickname, email, nombre, apellido);
        this.descripcion = descripcion;
        this.sitio_web = sitio_web;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitio_web() {
        return sitio_web;
    }

    public void setSitio_web(String sitio_web) {
        this.sitio_web = sitio_web;
    }
}
