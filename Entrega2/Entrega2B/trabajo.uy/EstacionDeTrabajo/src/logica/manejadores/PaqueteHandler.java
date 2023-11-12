package logica.manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Paquete;

public class PaqueteHandler {
	private static PaqueteHandler instancia = null;
	private Map<String,  Paquete> paq;
	
	
	private PaqueteHandler() {
		paq = new HashMap<String,  Paquete>();
	} 
	
	public static PaqueteHandler getInstance() {
		if (instancia == null) {
			instancia = new PaqueteHandler();
		} 
		return instancia;
	}
	
	public boolean existe(String nombre) {
		return paq.containsKey(nombre);
	}
	
    public Paquete buscar(String nombre) {
    	if (!paq.containsKey(nombre)) {
            throw new IllegalArgumentException("Paquete no encontrado");
        }
        return paq.get(nombre);
    }
	
	public void agregar(Paquete paquete) { 
		paq.put(paquete.getNombre(),  paquete); 
	}
    
    public Map<String,  Paquete> obtener() {
    	return paq;
    }
    
	
    
	



    
}
