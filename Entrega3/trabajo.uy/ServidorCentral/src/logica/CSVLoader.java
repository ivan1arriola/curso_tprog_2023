package logica;

import java.io.InputStream;

public class CSVLoader {

    public static InputStream getInputStream(String nombreArchivo) {
        // Usa la clase ClassLoader para cargar el archivo desde la misma carpeta que la clase
        ClassLoader classLoader = CSVLoader.class.getClassLoader();
        return classLoader.getResourceAsStream(nombreArchivo);
    }
}
