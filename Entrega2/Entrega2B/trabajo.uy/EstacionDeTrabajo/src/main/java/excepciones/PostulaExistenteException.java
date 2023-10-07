package main.java.excepciones;
import java.io.Serializable;

public class PostulaExistenteException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public PostulaExistenteException(String message) {
        super(message);
    }
}