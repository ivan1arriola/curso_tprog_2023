package trabajoUy.excepciones;

import java.io.Serializable;

public class ExceptionUsuarioNickYCorreoRepetidos extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionUsuarioNickYCorreoRepetidos(String string) {
        super(string);
    }
}
