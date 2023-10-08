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
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public int getExposicion() {
		return exposicion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
