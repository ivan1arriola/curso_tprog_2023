package main.java.excepciones;

import java.io.Serializable;

public class ExceptionDescuentoInvalido extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionDescuentoInvalido(String string) {
        super(string);
    }
}
