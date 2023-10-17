package main.java.logica.clases;

import java.time.LocalDate;

import main.java.logica.datatypes.DTTipoOferta;
import main.java.excepciones.ExceptionCostoPaqueteNoNegativo;
import main.java.excepciones.ExceptionDuracionNegativa;
import main.java.excepciones.ExceptionExpoNegativa;

public class TipoOferta {
    // Atributos
    private String nombre;
    private LocalDate fechaAlta;
    private float costo;
    private int duracion; 
    private int exposicion;
    private String descripcion;

    // Constructor
    public TipoOferta(String nombre,  LocalDate fechaAlta,  float costo,  int duracion,  int exposicion,  String descripcion) throws ExceptionCostoPaqueteNoNegativo, ExceptionDuracionNegativa, ExceptionExpoNegativa{
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        try {
	        if (costo<0) {
	        	throw new ExceptionCostoPaqueteNoNegativo("El costo debe ser mayor o igual a 0");
	        }
	        this.costo = costo;
	        
	        if (duracion<=0) {
	        	throw new ExceptionDuracionNegativa("La duración debe ser mayor que 0 días");
	        }
	        this.duracion = duracion;
	        
	        if (exposicion<=0) {
	        	throw new ExceptionExpoNegativa("La exposición debe ser mayor que 0");
	        }
	        this.exposicion = exposicion;
	        
        } catch (ExceptionCostoPaqueteNoNegativo exc) {
        	 System.err.println("Error en costo del paquete: " + exc.getMessage());
        	 throw exc;
        } catch (ExceptionDuracionNegativa exc) {
        	System.err.println("Error en duración: " + exc.getMessage());
       	 	throw exc;
        } catch (ExceptionExpoNegativa exc) {
        	System.err.println("Error en exposición: " + exc.getMessage());
       	 	throw exc;
        }
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
    
    public void setCosto(float costo) throws ExceptionCostoPaqueteNoNegativo  {
    	try {
	        if (costo<0) {
	        	throw new ExceptionCostoPaqueteNoNegativo("El costo debe ser mayor o igual a 0");
	        }
	        this.costo = costo;
        } catch (ExceptionCostoPaqueteNoNegativo exc) {
        	 System.err.println("Error en costo del paquete: " + exc.getMessage());
        	 throw exc;
        }
    }
    
    public void setDuracion(int duracion) throws ExceptionDuracionNegativa {
    	try {
	        if (duracion<=0) {
	        	throw new ExceptionDuracionNegativa("La duración debe ser mayor que 0 días");
	        }
	        this.duracion = duracion;
        } catch (ExceptionDuracionNegativa exc) {
        	System.err.println("Error en duración: " + exc.getMessage());
       	 	throw exc;
        	
        }
    }
    
    public void setExposicion(int exposicion) throws ExceptionExpoNegativa {
    	try {
	        if (exposicion<=0) {
	        	throw new ExceptionExpoNegativa("La exposición debe ser mayor que 0");
	        }
	        this.exposicion = exposicion;
        } catch (ExceptionExpoNegativa exc) {
        	System.err.println("Error en exposición: " + exc.getMessage());
       	 	throw exc;
        	
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
