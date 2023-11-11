package excepciones;

import java.io.Serializable;

public class NoHayOrdenDefinidoDePostulantes extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public NoHayOrdenDefinidoDePostulantes(String string) {
        super(string);
    }


}