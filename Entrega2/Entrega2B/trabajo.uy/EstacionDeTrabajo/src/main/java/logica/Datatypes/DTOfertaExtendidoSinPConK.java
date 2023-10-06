package main.java.logica.Datatypes;

import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;

public class DTOfertaExtendidoSinPConK extends {
	private String nombre;
	private String descripcion;
	private LocalDate fechaAlta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado;
	private byte[] imagen;
	private HashSet<String> keywords;
	
	public DTOfertaExtendidoSinPConK(String n, String desc, LocalDate fechaA, float c, float r, DTHorario h, DepUY dep, String ciu, EstadoOL e, byte[] img, HashSet<String> keys){
		nombre = n;
		descripcion = desc;
		fechaAlta = fechaA;
		costo = c;
		remuneracion = r;
		horario = h;
		departamento = dep;
		ciudad = ciu;
		imagen = img;
		keywords = keys;
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
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public HashSet<String> getKeywords() {
		return keywords;
	}
	
	
}
 