package main.java.excepciones;

import java.io.Serializable;

public class ExceptionFechaInvalida extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionFechaInvalida(String string) {
        super(string);
    }
}
