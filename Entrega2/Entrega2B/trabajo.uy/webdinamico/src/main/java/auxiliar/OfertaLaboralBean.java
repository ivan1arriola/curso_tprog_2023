package auxiliar;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class OfertaLaboralBean {
	private String nombre;
	private String descripcion;
	private LocalDate fechaDeAlta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado;
	private Set<DTUsuario> postulantes;
	private byte[] imagen; 
	private String paq;
	private DTPaquete paquete;
	private Set<String> keywords;
	private boolean mostrarPostulantesYPaquetes;

	
	public OfertaLaboralBean() {
        this.nombre = null;
        this.descripcion = null;
        this.fechaDeAlta = null;
        this.costo = 0.0f;
        this.remuneracion = 0.0f;
        this.horario = null;
        this.departamento = null;
        this.ciudad = null;
        this.estado = null;
        this.imagen = null;
        this.paq = null;
        this.paquete = null;
        this.keywords = new HashSet<String>();
        this.postulantes = new HashSet<DTUsuario>();
        this.mostrarPostulantesYPaquetes = false;
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

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(float remuneracion) {
        this.remuneracion = remuneracion;
    }

    public DTHorario getHorario() {
        return horario;
    }

    public void setHorario(DTHorario horario) {
        this.horario = horario;
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

    public EstadoOL getEstado() {
        return estado;
    }

    public void setEstado(EstadoOL estado) {
        this.estado = estado;
    }



    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getPaq() {
        return paq;
    }

    public void setPaq(String paq) {
        this.paq = paq;
    }

    public DTPaquete getPaquete() {
        return paquete;
    }

    public void setPaquete(DTPaquete paquete) {
        this.paquete = paquete;
    }


	public Set<String> getKeywords() {
		return keywords;
	}


	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}




	public Set<DTUsuario> getPostulantes() {
		return postulantes;
	}


	public void setPostulantes(Set<DTUsuario> postulantes) {
		this.postulantes = postulantes;
	}





	public boolean getMostrarPostulantesYPaquetes() {
		return mostrarPostulantesYPaquetes;
	}


	public void setMostrarPostulantesYPaquetes(boolean mostrarPostulantesYPaquetes) {
		this.mostrarPostulantesYPaquetes = mostrarPostulantesYPaquetes;
	}




}
