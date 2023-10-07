package main.java.excepciones;
import java.io.Serializable;

public class ExceptionUsuarioNickRepetido extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionUsuarioNickRepetido(String string) {
        super(string);
    }
}
