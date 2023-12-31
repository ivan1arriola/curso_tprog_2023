package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Keyword;


public class KeywordHandler {
	private static KeywordHandler instancia = null;
	private Map<String,  Keyword> keys;
	
	private KeywordHandler() { 
		keys = new HashMap<String,  Keyword>(); 
	} // obtener instancia con getInstance() 
	
	public static KeywordHandler getInstance() {
		if (instancia == null) { 
			instancia = new KeywordHandler();
		} 
		return instancia;
	}
	
	public void agregar(Keyword key) {
		if (key == null) {
	      throw new IllegalArgumentException("La keyword a agregar no puede ser vacia");
		}
		keys.put(key.getNombre(),  key);
	}
	
	public boolean existe(String key) {
		return keys.containsKey(key);
	}

	public Map<String,  Keyword> obtener() {
		return keys;
	}
}
