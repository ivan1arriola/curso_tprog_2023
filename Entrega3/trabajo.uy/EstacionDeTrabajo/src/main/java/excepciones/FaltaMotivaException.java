package main.java.excepciones;

import java.io.Serializable;

public class FaltaMotivaException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public FaltaMotivaException(String message) {
        super(message);
    }
}