package main.java.logica.manejadores;


import java.util.HashMap;
import java.util.Map;

import main.java.logica.clases.OfertaLaboral;


public class OfertaLaboralHandler {
	private static OfertaLaboralHandler OLHandler = null;
	private Map<String, OfertaLaboral> OfertasLaborales;
	
	private OfertaLaboralHandler() { 
		OfertasLaborales = new HashMap<>();
	} //Constructor privado, inicializa colección de ofertas laborales
	
	
	public static OfertaLaboralHandler getInstance() {
		if (OLHandler == null) {
			OLHandler = new OfertaLaboralHandler();
		} 
		return OLHandler;
	}
	
	public void agregar(OfertaLaboral tipoOfertaL) {
		OfertasLaborales.put(tipoOfertaL.getNombre(), tipoOfertaL); 
	}
	
    public Map<String, OfertaLaboral> obtener() {
    	return OfertasLaborales;
    }
	
    public boolean existe(String nombre) {
    	if (OfertasLaborales.containsKey(nombre)) {
    	    return true;
    	}
        return false;
    } 

    public OfertaLaboral buscar(String nombre) {
    	return OfertasLaborales.get(nombre);
    }
    
}