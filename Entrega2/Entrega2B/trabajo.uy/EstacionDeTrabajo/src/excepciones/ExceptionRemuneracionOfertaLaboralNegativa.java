package excepciones;

import java.io.Serializable;

public class ExceptionRemuneracionOfertaLaboralNegativa extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionRemuneracionOfertaLaboralNegativa(String string) {
        super(string);
    }
}
