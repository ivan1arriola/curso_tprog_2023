package excepciones;

import java.io.Serializable;

public class ExceptionDuracionNegativa extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionDuracionNegativa(String string) {
        super(string);
    }
}
