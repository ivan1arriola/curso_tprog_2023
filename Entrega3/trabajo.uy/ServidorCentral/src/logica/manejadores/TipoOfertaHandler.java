package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.TipoOferta;

public class TipoOfertaHandler {
	private static TipoOfertaHandler tOfertaHandler = null;
	private Map<String,  TipoOferta> tipoOfertas;
	
	
	private TipoOfertaHandler() {
		tipoOfertas = new HashMap<>();
	} //Constructor privado,  inicializa colección de ofertas
	
	
	public static TipoOfertaHandler getInstance() {
		if (tOfertaHandler == null) {
			tOfertaHandler = new TipoOfertaHandler();
		} 
		return tOfertaHandler;
	}
	
    public boolean existe(String nombre) {
        return tipoOfertas.containsKey(nombre);
    }
	
	public void agregar(TipoOferta tipoOferta) {
        tipoOfertas.put(tipoOferta.getNombre(),  tipoOferta);
    } 
    
    public TipoOferta buscar(String nombre) {
    	return tipoOfertas.get(nombre);
    } 
    
    public Map<String,  TipoOferta> obtener() {
        return tipoOfertas;
    }
}
