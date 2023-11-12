package logica.Clases;
import java.time.LocalDate;

import logica.Datatypes.DTTipoOferta;

public class TipoOferta {
	private String nombre;
	private LocalDate fechaAlta;
	private float costo;
	private int duracion; 
	private int exposicion;
	private String descripcion;
	
	public TipoOferta(String nombre, LocalDate fechaAlta, float costo, int duracion, int exposicion, String descripcion) {
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.costo = costo;
        this.duracion = duracion;
        this.exposicion = exposicion;
        this.descripcion = descripcion;
    }
	
	// GETTERS
	public String getNombre() 		{ return nombre; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public float getCosto() 		{ return costo; }
    public int getDuracion() 		{ return duracion; }
    public int getExposicion() 		{ return exposicion;     }
    public String getDescripcion() 	{ return descripcion; }
    
    // SETTERS
    public void setNombre(String nombre) 			{ this.nombre = nombre; }
    public void setFechaAlta(LocalDate fechaAlta) 	{ this.fechaAlta = fechaAlta;     }
    public void setCosto(float costo) 				{ this.costo = costo; }
    public void setDuracion(int duracion) 			{ this.duracion = duracion; }
    public void setExposicion(int exposicion) 		{ this.exposicion = exposicion; }
    public void setDescripcion(String descripcion) 	{ this.descripcion = descripcion; }
	
    
	public DTTipoOferta obtenerDT(){ //getDTTipoOferta
		if (fechaAlta.isAfter(LocalDate.now())) { throw new IllegalArgumentException("Fecha de Alta no debe ser posterior a fecha actual") ;}
		if (costo < 0 ) { throw new IllegalArgumentException("Costo debe ser mayor o igual a 0"); }
		if (duracion<=0) { throw new IllegalArgumentException("Duración debe ser mayor o igual a 1 día"); }
		if (duracion<0) { throw new IllegalArgumentException("Exposicion debe ser mayor o igual a 0"); }
		
		DTTipoOferta dtTO = new DTTipoOferta(nombre, fechaAlta, costo, duracion, exposicion,descripcion);
		return dtTO;
	}
}
