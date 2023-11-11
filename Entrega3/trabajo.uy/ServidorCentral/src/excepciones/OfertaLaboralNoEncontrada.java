package excepciones;

import java.io.Serializable;

public class OfertaLaboralNoEncontrada extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public OfertaLaboralNoEncontrada(String string) {
        super(string);
    }
}