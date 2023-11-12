	package main.java.logica.Datatypes;
	import java.util.Set;
public class DTPaquete {
	private String nombre;
	private float costo;
	private float descuento;
	private int validez;
	private String descripcion;
	private Set<DTCantTO> tiposDePub;
	
	public DTPaquete(String nomb, float c, float d, int v, String desc, Set<DTCantTO> tdp) {
		nombre = nomb;
		costo = c;
		descuento = d;
		validez = v;
		descripcion = desc;
		tiposDePub = tdp;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public int getValidez() {
	    return validez;
	}

	public String getDescripcion() {
	    return descripcion;
	}

	public Set<DTCantTO> getTiposDePub() {
	    return tiposDePub;
	}
}
