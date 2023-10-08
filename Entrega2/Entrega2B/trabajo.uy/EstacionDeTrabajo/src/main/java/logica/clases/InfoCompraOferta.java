package main.java.logica.clases;

// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
import java.util.HashSet;
import java.util.Set;

public class InfoCompraOferta {
	private int cant_restante;
	private Set<TipoOferta> tipoOfertas;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int can_res) { 
		this.cant_restante = can_res;
		tipoOfertas.add(tipoOfer);
	} //Constructor
	
	public int getCant_res() {
		return cant_restante; 
	}
	
	public void setCant_res(int cant_rest) { 
		cant_restante = cant_rest;
	}
	
}