package model.Clases;
import java.util.Iterator;
import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTCantTO;

public class Paquete {
	private String nombre;
	private String descripcion;
	private float costo;
	private float descuento;
	private int validez;
	private LocalDate fechaAlta;
	private Set<OfertaPaquete> oferPaq;

	public Paquete(String nombre, String descripcion, int validez, LocalDate fecha, float descuento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaAlta = fecha;
        this.descuento = descuento;
        this.validez = validez;
        oferPaq = new HashSet<OfertaPaquete>();
    }
	
	
	// GETTERS
	public String getNombre() 						{ return nombre; }
    public String getDescripcion() 					{ return descripcion; }
    public float getCosto() 						{ return costo; }
    public float getDescuento()						{ return descuento; }
    public int getValidez() 						{ return validez; }
    public Set<OfertaPaquete> getOfertaPaquete() 	{ return oferPaq; }


    // SETTERS
    public void setNombre(String nombre) 						{ this.nombre = nombre; }
    public void setDescripcion(String descripcion) 				{ this.descripcion = descripcion; }
    public void setCosto(float costo) 							{ this.costo = costo; }
    public void setDuracion(float descuento) 					{ this.descuento = descuento; }
    public void setValidez(int validez) 						{ this.validez = validez; }
    public void setOfertaPaquete(Set<OfertaPaquete> oferPaq) 	{ this.oferPaq = oferPaq; }
	
    
    public DTPaquete getDTPaquete() {
    	Set<DTCantTO> individual = new HashSet<>();
    	Iterator<OfertaPaquete> it = oferPaq.iterator(); 
    	while (it.hasNext()) { 
    		OfertaPaquete actual = it.next();
    		individual.add(actual.getDTCantTO());
    	}
    	
    	DTPaquete dtpaq = new DTPaquete(nombre, costo, descuento, validez, descripcion, individual);
    	return dtpaq;
    }
    
    
	public void crearOfertaPaquete(TipoOferta tipoO, int cantidad) {
		OfertaPaquete ofpaq = new OfertaPaquete(tipoO, cantidad);
		oferPaq.add(ofpaq);
	}
}
