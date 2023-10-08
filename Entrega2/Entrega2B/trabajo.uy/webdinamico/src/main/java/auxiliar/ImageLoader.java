package auxiliar;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageLoader {

}

// Guarda la imagen en un archivo temporal
File archivoImagen = new File(rutaTemporal, nombreArchivo);
try (FileOutputStream fos = new FileOutputStream(archivoImagen)) {
    fos.write(imagenUsuario);
} catch (IOException e) {
    e.printStackTrace();
}