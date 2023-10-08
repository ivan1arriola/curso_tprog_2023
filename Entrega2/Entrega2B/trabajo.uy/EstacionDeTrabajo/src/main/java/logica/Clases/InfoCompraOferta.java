package main.java.logica.Clases;

// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
import java.util.HashSet;

public class InfoCompraOferta {
	private int cant_restante;
	private HashSet<TipoOferta> tipoOfertas;
	
	public InfoCompraOferta(TipoOferta t, int can_res) { 
		this.cant_restante = can_res;
		tipoOfertas.add(t);
	} //Constructor
	
	public int getCant_res() {
		return cant_restante; 
	}
	
	public void setCant_res(int cant_rest) { 
		cant_restante = cant_rest;
	}
	
}