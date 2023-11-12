package excepciones;

import java.io.Serializable;

public class ErrorAgregarUsuario extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public ErrorAgregarUsuario(String string) {
        super(string);
    }


}
