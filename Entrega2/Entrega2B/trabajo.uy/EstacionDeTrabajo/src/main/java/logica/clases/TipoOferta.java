package main.java.logica.clases;

import java.time.LocalDate;

import main.java.logica.datatypes.DTTipoOferta;

public class TipoOferta {
    // Atributos
    private String nombre;
    private LocalDate fechaAlta;
    private float costo;
    private int duracion; 
    private int exposicion;
    private String descripcion;

    // Constructor
    public TipoOferta(String nombre,  LocalDate fechaAlta,  float costo,  int duracion,  int exposicion,  String descripcion) {
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        if (costo<0) {
        	throw new IllegalArgumentException("El costo debe ser mayor o igual a 0");
        }
        this.costo = costo;
        
        if (duracion<=0) {
        	throw new IllegalArgumentException("La duración debe ser mayor que 0 días");
        }
        
        this.duracion = duracion;
        
        if (exposicion<=0) {
        	throw new IllegalArgumentException("La exposición debe ser mayor que 0");
        }
        this.exposicion = exposicion;
        this.descripcion = descripcion;
    }

	// GETTERS
    public String getNombre() 		{ 
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
    
    public String getDescripcion() 	{
    	return descripcion;
    }
    
    // SETTERS
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public void setFechaAlta(LocalDate fechaAlta) {
    	this.fechaAlta = fechaAlta;
    }
    
    public void setCosto(float costo) {
    	if (costo<0) {
        	throw new IllegalArgumentException("El costo debe ser mayor o igual a 0");
        }
        this.costo = costo;
    }
    
    public void setDuracion(int duracion) {
    	if (duracion<=0) {
        	throw new IllegalArgumentException("La duración debe ser mayor que 0 días");
        }
        
        this.duracion = duracion;
    }
    
    public void setExposicion(int exposicion) {
    	if (exposicion<=0) {
        	throw new IllegalArgumentException("La exposición debe ser mayor que 0");
        }
    }
    
    public void setDescripcion(String descripcion) 	{
    	this.descripcion = descripcion;
    }
    
    public DTTipoOferta obtenerDT(){ //getDTTipoOferta
        DTTipoOferta dtTO = new DTTipoOferta(nombre,  fechaAlta,  costo,  duracion,  exposicion, descripcion);
        return dtTO;
    }
    
    public boolean estaVencida() {
    	LocalDate fechaActual = LocalDate.now();
    	return fechaAlta.plusDays(duracion).isBefore(fechaActual);
    }
}
