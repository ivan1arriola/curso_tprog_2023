package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	private static final String NOMBRE_ARCHIVO = ".properties";

    private Properties properties;

    public Configuracion() throws NoExisteArchivoDeConfiguacion {
        cargarConfiguracion();
    }

    private void cargarConfiguracion() throws NoExisteArchivoDeConfiguacion {
        try {
            String home = System.getProperty("user.home");
            String rutaArchivo = home + File.separator + ".trabajoUy" + File.separator + NOMBRE_ARCHIVO;
            File archivo = new File(rutaArchivo);

            if (!archivo.exists()) {
                System.out.println("Lo siento, no se puede encontrar el archivo de configuración.");
                throw new NoExisteArchivoDeConfiguacion("Lo siento, no se puede encontrar el archivo de configuración.");
            }

            // Cargar las propiedades desde el archivo de configuración
            try (InputStream input = new FileInputStream(archivo)) {
                properties = new Properties();
                properties.load(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerValor(String clave) {
        return properties.getProperty(clave);
    }
}
