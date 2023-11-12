package excepciones;

import java.io.Serializable;

public class TipoUsuarioNoValido extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public TipoUsuarioNoValido(String string) {
        super(string);
    }


}