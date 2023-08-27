package logica.Manejadores;
import java.util.HashMap;
import logica.Clases.Keyword;


public class KeywordHandler {
	private static KeywordHandler instancia = null;
	private HashMap<String, Keyword> keys;
	
	
	private KeywordHandler() { keys = new HashMap<String, Keyword>(); } 
	
	public static KeywordHandler getInstance() {
		if (instancia == null) { instancia = new KeywordHandler(); } 
		return instancia;
	}
	
	public void agregar(Keyword key){
        if (key == null) {
            throw new IllegalArgumentException("La keyword a agregar no puede ser vacia");
        }
        keys.put(key.getNombre(), key);
    }
	
	public boolean existe(String key) {
		return keys.containsKey(key);
	}

    
    public HashMap<String, Keyword> obtener() { return keys; }
    
	// public boolean existe(String nombre) { return keys.containsKey(nombre); }
    
	
    // public Keyword buscar(String nombre) {
    //	if (!key.containsKey(nombre)) {
    //       throw new IllegalArgumentException("Keyword no encontrado");
    //   }
    //   return key.get(nombre);
    //   }
}
