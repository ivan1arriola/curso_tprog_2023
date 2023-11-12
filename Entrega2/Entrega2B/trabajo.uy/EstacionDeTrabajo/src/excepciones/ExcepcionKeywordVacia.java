package excepciones;

import java.io.Serializable;

public class ExcepcionKeywordVacia extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public ExcepcionKeywordVacia(String string) {
        super(string);
    }
}
