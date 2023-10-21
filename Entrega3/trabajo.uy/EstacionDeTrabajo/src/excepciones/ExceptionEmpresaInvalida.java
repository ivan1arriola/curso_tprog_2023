package excepciones;

import java.io.Serializable;

public class ExceptionEmpresaInvalida extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionEmpresaInvalida(String string) {
        super(string);
    }
}
