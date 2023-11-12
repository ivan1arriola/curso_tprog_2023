package logica.clases;


import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;

public class InfoCompraOferta {
	private int cantRestante;
	private TipoOferta tipoOferta;
	
	public InfoCompraOferta(TipoOferta tipoOfer,  int canres) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
		if (canres >= 0) {
			cantRestante = canres;
			tipoOferta = tipoOfer;
			System.out.println("Se ha creado un InfoCompraOferta. - " + tipoOfer.getNombre() + " - "+ canres);
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
		if (cantrest >= 0) {
			cantRestante = cantrest;
		} else {
			throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
		}
		
	}
	
	public void settipoOfertas(TipoOferta tipoOfer) {
		tipoOferta = tipoOfer;
	}
}