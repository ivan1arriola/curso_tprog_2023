package excepciones;

import java.io.Serializable;

public class PersistirOfertaNoFinalizada extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public PersistirOfertaNoFinalizada(String string) {
        super(string);
    }
}
