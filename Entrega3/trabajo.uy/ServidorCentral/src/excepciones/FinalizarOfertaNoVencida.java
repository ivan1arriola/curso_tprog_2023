package excepciones;

import java.io.Serializable;

public class FinalizarOfertaNoVencida extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public FinalizarOfertaNoVencida(String string) {
        super(string);
    }


}
