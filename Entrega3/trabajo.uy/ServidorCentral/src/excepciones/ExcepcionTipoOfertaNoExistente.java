package excepciones;

import java.io.Serializable;

public class ExcepcionTipoOfertaNoExistente extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExcepcionTipoOfertaNoExistente(String string) {
        super(string);
    }
}
