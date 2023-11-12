package excepciones;

import java.io.Serializable;

public class NoExistePaquete extends Exception implements Serializable {
    private static final long serialVersionUID = 12;

    public NoExistePaquete(String string) {
        super(string);
    }


}