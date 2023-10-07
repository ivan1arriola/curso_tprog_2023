package main.java.excepciones;
import java.io.Serializable;

public class ExceptionUsuarioCorreoRepetido extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionUsuarioCorreoRepetido(String string) {
        super(string);
    }
}