package javabeans;

import java.time.LocalDate;
import java.util.Set;

import enumeration.TipoUsuario;

public class UsuarioSinInfoSocialBean implements Comparable<UsuarioSinInfoSocialBean>{
	
	private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private String imagen;
    private String descripcion;
    private String url;
    private LocalDate fechaNac;
    private String nacionalidad;
    private Set<OfertaLaboralBean> ofertasLaborales;
    private Set<PostulacionBean> postulaciones;
    private TipoUsuario tipo;
    
    private Set<PaqueteBean> paquetes;
    
    private String error;
    
    
    public UsuarioSinInfoSocialBean() {
        this.nickname = null;
        this.correoElectronico = null;
        this.apellido = null;
        this.nombre = null;
        this.contrasenia = null;
        this.descripcion = null;
        this.url = null;
        this.fechaNac = null;
        this.nacionalidad = null;
        this.ofertasLaborales = null;
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
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
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
	public Set<OfertaLaboralBean> getOfertasLaborales() {
		return ofertasLaborales;
	}
	public void setOfertasLaborales(Set<OfertaLaboralBean> ofertasLaborales) {
		this.ofertasLaborales = ofertasLaborales;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Set<PostulacionBean> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(Set<PostulacionBean> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Set<PaqueteBean> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Set<PaqueteBean> paquetes) {
		this.paquetes = paquetes;
	}


	@Override
	public int compareTo(UsuarioSinInfoSocialBean otroBean) {
		return this.nombre.compareTo(otroBean.getNombre());
	}
}
