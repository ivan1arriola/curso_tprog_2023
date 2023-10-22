package javabeans;

import java.util.ArrayList;
import java.util.Set;

import logica.enumerados.TipoUsuario;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioBeanServidor {
	
	private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private String imagen;
    private String descripcion;
    private String url;
    private DateBean fechaNac;
    private String nacionalidad;
    private ArrayList<OfertaLaboralBeanServidor> ofertasLaborales;
    private ArrayList<PostulacionBean> postulaciones;
    private TipoUsuario tipo;
    
    private Set<PaqueteBeanServidor> paquetes;
    
    private String error;
    
    
    public UsuarioBeanServidor() {
        this.nickname = null;
        this.correoElectronico = null;
        this.apellido = null;
        this.nombre = null;
        this.contrasenia = null;
        this.descripcion = null;
        this.url = null;
        this.fechaNac = null;
        this.nacionalidad = null;
        this.setOfertasLaborales(null);
        this.tipo = null;
        this.paquetes = null;
        
        this.error = null;
    }
    
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public DateBean getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(DateBean fechaNac) {
		this.fechaNac = fechaNac;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Set<PaqueteBeanServidor> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Set<PaqueteBeanServidor> paquetes) {
		this.paquetes = paquetes;
	}

	public ArrayList<OfertaLaboralBeanServidor> getOfertasLaborales() {
		return ofertasLaborales;
	}

	public void setOfertasLaborales(ArrayList<OfertaLaboralBeanServidor> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}

	public ArrayList<PostulacionBean> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(ArrayList<PostulacionBean> postulaciones) {
		this.postulaciones = postulaciones;
	}


    
    

}
