package main.java.logica.Datatypes;
import java.time.LocalDate;

public class DTTipoOferta {
	private String nombre;
	private LocalDate fechaAlta;
	private float costo;
	private int  duracion;
	private int exposicion;
	private String descripcion;
	 
	public DTTipoOferta(String nombre, LocalDate fechaAlta, float costo, int duracion, int exposicion, String descripcion) {
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.costo = costo;
		this.duracion = duracion;
		this.exposicion = exposicion;
		this.descripcion = descripcion;
	}
	
	 // Getters and setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public int getExposicion() {
		return exposicion;
	}
	
	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
