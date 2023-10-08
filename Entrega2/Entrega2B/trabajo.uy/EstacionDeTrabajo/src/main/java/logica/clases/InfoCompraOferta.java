package main.java.logica.clases;


import java.util.HashSet;
import java.util.Set;

public class InfoCompraOferta {
	private int cantRestante;
	private Set<TipoOferta> tipoOfertas;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int canres) { 
		this.cantRestante = canres;
		Set<TipoOferta> tipoOfertas = new HashSet<>();
		tipoOfertas.add(tipoOfer);
	} //Constructor
	
	public int getCantres() {
		return cantRestante; 
	}
	
	public void setCantres(int cantrest) { 
		cantRestante = cantrest;
	}
	
}