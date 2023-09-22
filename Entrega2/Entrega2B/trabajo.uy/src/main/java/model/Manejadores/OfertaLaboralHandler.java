package model.Manejadores;
import java.util.*;

import model.Clases.OfertaLaboral;


public class OfertaLaboralHandler {
	private static OfertaLaboralHandler OLHandler = null;
	private HashMap<String, OfertaLaboral> OfertasLaborales;
	
	private OfertaLaboralHandler() { OfertasLaborales = new HashMap<>(); } //Constructor privado, inicializa colección de ofertas laborales
	
	
	public static OfertaLaboralHandler getInstance() {
		if (OLHandler == null) {
			OLHandler = new OfertaLaboralHandler();
		} 
		return OLHandler;
	}
	
	public void agregar(OfertaLaboral tipoOfertaL) { OfertasLaborales.put(tipoOfertaL.getNombre(), tipoOfertaL); }
    public HashMap<String, OfertaLaboral> obtener() { return OfertasLaborales; }
	
    public boolean existe(String nombre) {
    	if (OfertasLaborales.containsKey(nombre)) {
    	    return true;
    	}
        return false;
    } 

    public OfertaLaboral buscar(String nombre) { return OfertasLaborales.get(nombre); }
    
}