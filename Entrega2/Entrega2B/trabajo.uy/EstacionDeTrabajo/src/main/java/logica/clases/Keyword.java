package main.java.logica.clases;
import main.java.excepciones.ExcepcionKeywordVacia;

public class Keyword {
	private String nombre;
	
	// constructor
	public Keyword(String nomb) throws ExcepcionKeywordVacia {
	    if (!nomb.isEmpty()) {
	        nombre = nomb;
	    } else {
	        throw new ExcepcionKeywordVacia("La palabra clave no puede ser vacía");
	    }
	}

	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nomb) {
		nombre = nomb;
	}
}
