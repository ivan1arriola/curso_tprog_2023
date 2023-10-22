package logica.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ManejadorImagenes {
	
	private static final String PREFIX_ORIGINAL = "http://tprogdatostarea2.infinityfreeapp.com";
    private static final String PREFIX_NUEVO = "https://raw.githubusercontent.com/ivan1arriola/tprogImagenes/main";
    
    
    public static byte[] descargarImagen(String imageUrl, String identificador) throws IOException {

		String link = ManejadorImagenes.getDirectUrl(imageUrl);
		link = cambiarURLAlternativo(link);
		
		String[] parts = link.split("/");
		int length = parts.length;
		String subcarpeta = parts[length - 2];
		String nombreImagen = ManejadorImagenes.generateImageCode(identificador);
		String tipoImagen = "jpg";
	
		String ubicacion = getUbicacionImagenes() + "/" + subcarpeta + "/" + nombreImagen + "." + tipoImagen;
	    File imagenFile = new File(ubicacion);

	    if (imagenFile.exists()) {
	        return cargarImagenDesdeArchivo(imagenFile);
	    } else {
	        byte[] imagen = descargarImagenDeInternet(link);
	        guardarImagenEnArchivo(subcarpeta, nombreImagen, tipoImagen, imagen);
	        return imagen;
	    }
	}
    
    private static byte[] cargarImagenDesdeArchivo(File imagenFile) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(imagenFile);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }
    
    private static byte[] descargarImagenDeInternet(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        byte[] imagen;
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (InputStream in = connection.getInputStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            imagen = out.toByteArray();
        }

        return imagen;
    }
	
	private static String cambiarURLAlternativo(String imageUrl) {
        String nuevaURL = imageUrl.replace(PREFIX_ORIGINAL, PREFIX_NUEVO);
        return nuevaURL;
    }

	public static String getUbicacionImagenes() {
	    String ubicacion = System.getProperty("ubicacion_recursos");
	    if (ubicacion == null) {
	        ubicacion = System.getProperty("user.dir") + "/resources";
	    }
	    return ubicacion;
	}

	public static String generateImageCode(String nombreImagen) {
	    // Reemplaza caracteres no permitidos en nombres de archivo
	    nombreImagen = nombreImagen.replaceAll("[^a-zA-Z0-9_]", "_");

	    // Elimina puntos duplicados
	    nombreImagen = nombreImagen.replaceAll("\\.+", ".");

	    // Elimina puntos al principio o al final del nombre de archivo
	    nombreImagen = nombreImagen.replaceAll("^\\.|\\.$", "");

	    return nombreImagen;
	}




	public static void guardarImagenEnArchivo(String subcarpeta, String nombreImagen, String tipoImagen, byte[] imagenBytes) {
	    String ubicacionBase = getUbicacionImagenes();
	    String ubicacion = ubicacionBase + "/" + subcarpeta + "/" + generateImageCode(nombreImagen) + "." + tipoImagen;

	    File archivo = new File(ubicacion);

	    if (!archivo.exists()) {
	        File carpeta = archivo.getParentFile();

	        if (!carpeta.exists()) {
	            if (carpeta.mkdirs()) {
	                System.out.println("Carpeta creada exitosamente: " + carpeta.getAbsolutePath());
	            } else {
	                System.err.println("Error al crear la carpeta: " + carpeta.getAbsolutePath());
	                return;
	            }
	        }

	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
	            fileOutputStream.write(imagenBytes);
	            fileOutputStream.close();

	            System.out.println("Imagen guardada exitosamente en " + ubicacion);
	        } catch (IOException e) {
	            System.err.println("Error al guardar la imagen: " + e.getMessage());
	        }
	    } else {
	        System.out.println("La ubicación del archivo ya existe: " + ubicacion);
	    }
	}



	public static String getDirectUrl(String shortUrl) {
	    try {
	        URL url = new URL(shortUrl);
	        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	
	        // Configurar la solicitud
	        httpURLConnection.setInstanceFollowRedirects(false); // Deshabilitar redirecciones automáticas
	        httpURLConnection.setRequestMethod("GET");
	
	        // Realizar la solicitud
	        int responseCode = httpURLConnection.getResponseCode();
	
	        // Verificar si hay redirección
	        if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
	            return httpURLConnection.getHeaderField("Location");
	        } else {
	            return shortUrl;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
