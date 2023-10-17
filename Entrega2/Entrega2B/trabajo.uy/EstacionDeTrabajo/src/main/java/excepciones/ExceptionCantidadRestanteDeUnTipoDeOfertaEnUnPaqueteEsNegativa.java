package main.java.excepciones;

import java.io.Serializable;

public class ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa(String string) {
        super(string);
    }
}
