package trabajoUy.excepciones;

import java.io.Serializable;

public class FaltaCvException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public FaltaCvException(String message) {
        super(message);
    }
}