package main.java.logica.clases;


import java.util.HashSet;
import java.util.Set;

import main.java.excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;

public class InfoCompraOferta {
	private int cantRestante;
	private TipoOferta tipoOferta;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int canres) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
		if(canres >= 0) {
			cantRestante = canres;
			tipoOferta = tipoOfer;
		} else {
			throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
		}

	} //Constructor
	
	public int getCantres() {
		return cantRestante; 
	}
	
	public TipoOferta gettipoOfertas(){
		return tipoOferta;
	}
	
	public void setCantres(int cantrest) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
		if(cantrest >= 0) {
			cantRestante = cantrest;
		} else {
			throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
		}
		
	}
	
	public void settipoOfertas(TipoOferta tipoOfer) {
		tipoOferta = tipoOfer;
	}
}