package logica.manejadores;


import java.util.HashMap;
import java.util.Map;

import logica.clases.OfertaLaboral;


public class OfertaLaboralHandler {
	private static OfertaLaboralHandler OLHandler = null;
	private Map<String,  OfertaLaboral> ofertasLaborales;;
	
	private OfertaLaboralHandler() { 
		ofertasLaborales = new HashMap<>();
	} //Constructor privado,  inicializa colección de ofertas laborales
	
	
	public static OfertaLaboralHandler getInstance() {
		if (OLHandler == null) {
			OLHandler = new OfertaLaboralHandler();
		} 
		return OLHandler;
	}
	
	public void agregar(OfertaLaboral tipoOfertaL) {
		ofertasLaborales.put(tipoOfertaL.getNombre(),  tipoOfertaL); 
	}
	
    public Map<String,  OfertaLaboral> obtener() {
    	return ofertasLaborales;
    }
	
    public boolean existe(String nombre) {
    	if (ofertasLaborales.containsKey(nombre)) {
    	    return true;
    	}
        return false;
    } 

    public OfertaLaboral buscar(String nombre) {
    	return ofertasLaborales.get(nombre);
    }
    
}