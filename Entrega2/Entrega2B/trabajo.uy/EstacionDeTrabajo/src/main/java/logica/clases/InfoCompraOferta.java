package main.java.logica.clases;


import java.util.HashSet;
import java.util.Set;

public class InfoCompraOferta {
	private int cantRestante;
	private Set<TipoOferta> tipoOfertas;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int canres) { 
		cantRestante = canres;
		Set<TipoOferta> tipoOfertasNuevo = new HashSet<>();
		tipoOfertasNuevo.add(tipoOfer);
		tipoOfertas = tipoOfertasNuevo;
	} //Constructor
	
	public int getCantres() {
		return cantRestante; 
	}
	
	public Set<TipoOferta> gettipoOfertas(){
		return tipoOfertas;
	}
	
	public void setCantres(int cantrest) { 
		cantRestante = cantrest;
	}
	
	public void settipoOfertas( Set<TipoOferta> tipoOfertas) {
		this.tipoOfertas = tipoOfertas;
	}
}