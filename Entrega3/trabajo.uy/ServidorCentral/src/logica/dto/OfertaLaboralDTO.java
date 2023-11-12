package logica.dto;

import jakarta.persistence.*;
import logica.enumerados.DepUY;

import java.time.LocalDate;

@Entity
@Table(name = "Ofertas_finalizadas", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
})
public class OfertaLaboralDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long iden;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false)
    private Float remuneracion;

    @Column(nullable = false)
    private DepUY departamento;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String tipo_publicacion;

    @Column(nullable = false)
    private LocalDate fecha_alta;
    @Column(nullable = false)
    private LocalDate fecha_baja;
    @Column(nullable = false)
    private float costo;
    private String paquete;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_empresa")
    private EmpresaDTO empresa;

    public OfertaLaboralDTO(String nombre, String descripcion, String horario, Float remuneracion, DepUY departamento, String ciudad, String tipo_publicacion, LocalDate fecha_alta, LocalDate fecha_baja, float costo, String paquete, EmpresaDTO empresa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.remuneracion = remuneracion;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.tipo_publicacion = tipo_publicacion;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
        this.costo = costo;
        this.paquete = paquete;
        // para empresa
        this.empresa = empresa;
    }

    public OfertaLaboralDTO(String nombre, String descripcion, String horario, Float remuneracion, DepUY departamento, String ciudad, String tipo_publicacion, LocalDate fecha_alta, LocalDate fecha_baja, float costo, EmpresaDTO empresa) {
        this( nombre, descripcion, horario, remuneracion, departamento, ciudad, tipo_publicacion, fecha_alta, fecha_baja, costo, null, empresa);
    }

    public OfertaLaboralDTO(String nombre, String descripcion, String horario, Float remuneracion, DepUY departamento, String ciudad, String tipo_publicacion, LocalDate fecha_alta, LocalDate fecha_baja, float costo, String paquete) {
        this( nombre, descripcion, horario, remuneracion, departamento, ciudad, tipo_publicacion, fecha_alta, fecha_baja, costo, paquete, null);
    }

    public OfertaLaboralDTO() {

    }


    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    // esta es la operacion
    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }


    public Long getId() {
        return iden;
    }

    public void setId(Long iden) {
        this.iden = iden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(Float remuneracion) {
        this.remuneracion = remuneracion;
    }

    public DepUY getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepUY departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
