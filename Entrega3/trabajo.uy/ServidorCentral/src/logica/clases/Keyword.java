package logica.clases;

import excepciones.ExcepcionKeywordVacia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;

    // constructor
    public Keyword(String nombre) throws ExcepcionKeywordVacia {
        if (!nombre.isEmpty()) {
            this.nombre = nombre;
            System.out.println("Se ha creado una Keyword." + nombre);
        } else {
            throw new ExcepcionKeywordVacia("La palabra clave no puede ser vacía");
        }
    }

    public Keyword() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nomb) {
        nombre = nomb;
    }
}