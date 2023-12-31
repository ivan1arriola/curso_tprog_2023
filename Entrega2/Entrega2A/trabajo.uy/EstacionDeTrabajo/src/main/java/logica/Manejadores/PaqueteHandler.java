package main.java.logica.Manejadores;
import java.util.*;

import main.java.logica.Clases.Paquete;


public class PaqueteHandler {
	private static PaqueteHandler instancia = null;
	private HashMap<String, Paquete> paq;
	
	
	private PaqueteHandler() { paq = new HashMap<String, Paquete>(); } 
	
	
	public static PaqueteHandler getInstance() {
		if (instancia == null) { instancia = new PaqueteHandler(); } 
		return instancia;
	}
	
	public void agregar(Paquete p) { paq.put(p.getNombre(),p); };
    
    public Map<String, Paquete> obtener() { return paq; }
    
	public boolean existe(String nombre) { return paq.containsKey(nombre); }
    
	
    public Paquete buscar(String nombre) {
    	if (!paq.containsKey(nombre)) {
            throw new IllegalArgumentException("Paquete no encontrado");
        }
        return paq.get(nombre);
        }


    
}
