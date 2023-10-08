package main.java.logica.clases;

// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
import java.util.HashSet;
import java.util.Set;

public class InfoCompraOferta {
	private int cantRestante;
	private Set<TipoOferta> tipoOfertas;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int can_res) { 
		this.cantRestante = can_res;
		tipoOfertas.add(tipoOfer);
	} //Constructor
	
	public int getCant_res() {
		return cantRestante; 
	}
	
	public void setCant_res(int cant_rest) { 
		cantRestante = cant_rest;
	}
	
}