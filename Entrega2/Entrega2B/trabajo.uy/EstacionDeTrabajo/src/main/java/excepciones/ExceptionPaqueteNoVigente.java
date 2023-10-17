package main.java.excepciones;

import java.io.Serializable;

public class ExceptionPaqueteNoVigente extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionPaqueteNoVigente(String string) {
        super(string);
    }
}
