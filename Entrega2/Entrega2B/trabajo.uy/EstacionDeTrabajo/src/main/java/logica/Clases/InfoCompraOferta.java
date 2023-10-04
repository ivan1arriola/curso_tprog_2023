package main.java.logica.Clases;

import java.util.Set;


public class InfoCompraOferta {
	
	private Integer cant_restante;
	private Set<TipoOferta> ofertas;



	public InfoCompraOferta(Integer can_res, Set<TipoOferta> ofertas) { 
		this.cant_restante = can_res;
		this.ofertas = ofertas;
	}

	public Integer getCant_res() { 
		return cant_restante; 
	}
	
	public void setCant_res(Integer cant_rest) {
		cant_restante = cant_rest;
	}

	public Set<TipoOferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(Set<TipoOferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Integer getCant_restante() {
		return cant_restante;
	}

	public void setCant_restante(Integer cant_restante) {
		this.cant_restante = cant_restante;
	}
	
	
	
}