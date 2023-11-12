package excepciones;

import java.io.Serializable;

public class ExceptionCompraPaqueteConValorNegativo extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionCompraPaqueteConValorNegativo(String string) {
        super(string);
    }
}
