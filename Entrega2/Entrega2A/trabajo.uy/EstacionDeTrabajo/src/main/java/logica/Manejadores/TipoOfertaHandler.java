package main.java.logica.Manejadores;
import java.util.*;

import main.java.logica.Clases.TipoOferta;

public class TipoOfertaHandler {
	private static TipoOfertaHandler tOfertaHandler = null;
	private HashMap<String, TipoOferta> tipoOfertas;
	
	
	private TipoOfertaHandler() {
		tipoOfertas = new HashMap<>();
	} //Constructor privado, inicializa colección de ofertas
	
	
	public static TipoOfertaHandler getInstance() {
		if (tOfertaHandler==null) {tOfertaHandler = new TipoOfertaHandler();} 
		return tOfertaHandler;
	}
	
	public void agregar(TipoOferta tipoOferta) {
        tipoOfertas.put(tipoOferta.getNombre(),tipoOferta);
    }
    
    public HashMap<String, TipoOferta> obtener() {
        return tipoOfertas;
    }
	
    public boolean existe(String nombre) {
        return tipoOfertas.containsKey(nombre);
    }
    
    public TipoOferta buscar(String nombre) {
    	return tipoOfertas.get(nombre);
    } 
    
}
