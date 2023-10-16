package main.java.logica.datatypes;

import java.time.LocalDate;

import java.util.Set;

import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoSinPConK  {
	private String nombre;
	private String descripcion;
	private LocalDate fechaAlta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado;
	private String imagen;
	private Set<String> keywords;
	private String nicknameEmpresaPublicadora;
	
	public DTOfertaExtendidoSinPConK( String nicknameEmpresa, String nomb,  String desc,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY dep,  String ciu,  EstadoOL estado,  String img,  Set<String> keys) {
		// no es subclase de DTOfertaExtendido,  es una version sin postulaciones
		nombre = nomb;
		descripcion = desc;
		fechaAlta = fechaA;
		costo = cost;
		remuneracion = remu;
		this.horario = horario;
		departamento = dep;
		ciudad = ciu;
		imagen = img;
		this.estado = estado;
		keywords = keys;
		nicknameEmpresaPublicadora = nicknameEmpresa;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public float getRemuneracion() {
		return remuneracion;
	}
	
	public DTHorario getHorario() {
		return horario;
	}
	
	public DepUY getDepartamento() {
		return departamento;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public EstadoOL getEstado() {
		return estado;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public Set<String> getKeywords() {
		return keywords;
	}

	public String getNicknameEmpresaPublicadora() {
		return nicknameEmpresaPublicadora;
	}
	
	
}
 