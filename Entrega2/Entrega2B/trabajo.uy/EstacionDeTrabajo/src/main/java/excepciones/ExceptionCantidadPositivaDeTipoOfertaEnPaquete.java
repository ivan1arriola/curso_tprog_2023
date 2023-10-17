package main.java.excepciones;

import java.io.Serializable;

public class ExceptionCantidadPositivaDeTipoOfertaEnPaquete extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionCantidadPositivaDeTipoOfertaEnPaquete(String string) {
        super(string);
    }
}
