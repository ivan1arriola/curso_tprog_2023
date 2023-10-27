package logica.clases;
import excepciones.ExcepcionKeywordVacia;

@Entity
public class Keyword {
    @Id
    private String nombre;
	// constructor
	public Keyword(String nomb) throws ExcepcionKeywordVacia {
	    if (!nomb.isEmpty()) {
	        nombre = nomb;
	        System.out.println("Se ha creado una Keyword." + nomb);
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
