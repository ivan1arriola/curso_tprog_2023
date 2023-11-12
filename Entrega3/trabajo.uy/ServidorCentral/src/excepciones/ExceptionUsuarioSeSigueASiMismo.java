package excepciones;

import java.io.Serializable;

public class ExceptionUsuarioSeSigueASiMismo extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public ExceptionUsuarioSeSigueASiMismo(String string) {
        super(string);
    }
}
