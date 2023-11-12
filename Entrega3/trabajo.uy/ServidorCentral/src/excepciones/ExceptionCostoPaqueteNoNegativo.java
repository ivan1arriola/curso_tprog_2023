package excepciones;

import java.io.Serializable;

public class ExceptionCostoPaqueteNoNegativo extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionCostoPaqueteNoNegativo(String string) {
        super(string);
    }
}
