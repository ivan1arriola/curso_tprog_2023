package logica;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionValidezNegativa;
import logica.clases.Empresa;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.controladores.CtrlOferta;
import logica.controladores.CtrlUsuario;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;

public class Utils {
	
	private static final String PREFIX_ORIGINAL = "http://tprogdatostarea2.infinityfreeapp.com";
    private static final String PREFIX_NUEVO = "https://raw.githubusercontent.com/ivan1arriola/tprogImagenes/main";
    

	
	public Utils() {
	}
	
	public static String getUbicacionImagenes() {
        // Aquí puedes leer la configuración específica del proyecto
        // y obtener la ubicación de la carpeta "resources" de acuerdo con esa configuración.
        // Por ejemplo, puedes leer una propiedad del sistema o un archivo de configuración.
        // En este ejemplo, se usa una propiedad del sistema como ejemplo.
        String ubicacion = System.getProperty("ubicacion_recursos");
        
        // Si la propiedad no está definida, utiliza una ubicación por defecto.
        if (ubicacion == null) {
            ubicacion = System.getProperty("user.dir") + "/resources";
        }
        
        return ubicacion;
    }
	
	public static String generateImageCode(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            
            // Convierte el hash en una representación hexadecimal
            String code = String.format("%064x", new BigInteger(1, digest));
            
            return code;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static void guardarImagen(String subcarpeta, String nombreImagen, String tipoImagen, byte[] imagenBytes) {
	    try {
	        String ubicacion = getUbicacionImagenes() + "/" + subcarpeta + "/" + generateImageCode(nombreImagen) + "." +tipoImagen;
	        FileOutputStream fileOutputStream = new FileOutputStream(ubicacion);
	        fileOutputStream.write(imagenBytes);
	        fileOutputStream.close();
	        
	        System.out.println("Imagen guardada exitosamente en " + ubicacion);
	    } catch (IOException e) {
	        System.err.println("Error al guardar la imagen: " + e.getMessage());
	    }
	}

	
	
	public DTHorario obtenerHorario(String horarioStr) {
	    String[] desdeHasta = horarioStr.split(" - ");
	    String[] horaDesde = desdeHasta[0].split(":");
	    String[] horaHasta = desdeHasta[1].split(":");

	    int horaDesdeInt = Integer.parseInt(horaDesde[0]);
	    int minDesdeInt = Integer.parseInt(horaDesde[1]);
	    int horaHastaInt = Integer.parseInt(horaHasta[0]);
	    int minHastaInt = Integer.parseInt(horaHasta[1]);

	    DTHora desde = new DTHora(horaDesdeInt, minDesdeInt);
	    DTHora hasta = new DTHora(horaHastaInt, minHastaInt);

	    return new DTHorario(desde, hasta);
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
	
	private String cambiarURLAlternativo(String imageUrl) {
        String nuevaURL = imageUrl.replace(PREFIX_ORIGINAL, PREFIX_NUEVO);
        return nuevaURL;
    }
	
	public byte[] descargarImagen(String imageUrl) throws IOException {
		String link = getDirectUrl(imageUrl);
		link = cambiarURLAlternativo(link);
		
		
	    URL url = new URL(link);
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
	
	public void readCSV(String filePath, Consumer<String[]> rowProcessor) {
        try (
        		InputStream inputStream2 = this.getClass().getResourceAsStream(filePath);
	       	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream2))
        		) {
            reader.readLine(); // Leer y descartar la primera línea (cabecera) si es necesario
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                rowProcessor.accept(fields); // Procesar la fila utilizando la interfaz funcional
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public EstadoOL obtenerEstadoDesdeString(String nombreEstado) {
        switch (nombreEstado) {
            case "Confirmada":
                return EstadoOL.Confirmada;
            case "Ingresada":
                return EstadoOL.Ingresada;
            case "Rechazada":
                return EstadoOL.Rechazada;
            default:
                return null; // Otra acción si el nombre del estado no coincide con ninguno
        }
    }
	
	public LocalDate obtenerFechaDesdeString(String fechaStr, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return LocalDate.parse(fechaStr, formatter);
    }
	
	public DepUY obtenerDepUYDesdeNombre(String nombreDepartamento) {
	    switch (nombreDepartamento) {
	        case "Artigas":
	            return DepUY.Artigas;
	        case "Salto":
	            return DepUY.Salto;
	        case "Paysandú":
	            return DepUY.Paysandú;
	        case "Rionegro":
	            return DepUY.RioNegro;
	        case "Soriano":
	            return DepUY.Soriano;
	        case "Colonia":
	            return DepUY.Colonia;
	        case "Rivera":
	            return DepUY.Rivera;
	        case "Tacuarembo":
	            return DepUY.Tacuarembo;
	        case "Durazno":
	            return DepUY.Durazno;
	        case "Flores":
	            return DepUY.Flores;
	        case "Florida":
	            return DepUY.Florida;
	        case "Sanjosé":
	            return DepUY.SanJosé;
	        case "Canelones":
	            return DepUY.Canelones;
	        case "Montevideo":
	            return DepUY.Montevideo;
	        case "Cerrolargo":
	            return DepUY.CerroLargo;
	        case "Treintaytres":
	            return DepUY.TreintaYTres;
	        case "Lavalleja":
	            return DepUY.Lavalleja;
	        case "Rocha":
	            return DepUY.Rocha;
	        case "Maldonado":
	            return DepUY.Maldonado;
	        default:
	            return null; // Otra acción si el nombre del departamento no coincide con ninguno
	    }
	    
	    

	}

	
	
	public String buscarNicknameEnUsuarioCSV(String codigoUsuario) {
	    final String[] nickname = { null }; // Variable para almacenar el nickname encontrado
	    readCSV("/datos/Usuarios.csv", usuariosCSV -> {
	        if (usuariosCSV[0].equals(codigoUsuario)) {
	            nickname[0] = usuariosCSV[2];
	        }
	    });
	    return nickname[0]; // Devuelve el nickname encontrado
	}

	public String buscarTipoPublicacion(String codigoOfertaLaboral) {
	    final String[] tipoPublicacion = { null }; // Variable para almacenar el tipo de publicación encontrado
	    readCSV("/datos/TipoPublicacion.csv", tipoPublicacionCSV -> {
	        if (tipoPublicacionCSV[0].equals(codigoOfertaLaboral)) {
	            tipoPublicacion[0] = tipoPublicacionCSV[1];
	        }
	    });
	    return tipoPublicacion[0]; // Devuelve el tipo de publicación encontrado
	}

	public List<String> buscarPalabrasClave(String codigoOfertaLaboral) {
	    final List<String> keys = new ArrayList<>(); // Lista para almacenar las palabras clave encontradas
	    readCSV("/datos/OfertasLaboralesKeywords.csv", ofertasLaboralesKeywordsCSV -> {
	        if (ofertasLaboralesKeywordsCSV[0].equals(codigoOfertaLaboral)) {
	            String keyss = ofertasLaboralesKeywordsCSV[1];
	            String[] kss = keyss.split(",  ");
	            for (int i = 0; i < kss.length; i++) {
	                final String keyword = kss[i];
	                readCSV("/datos/Keywords.csv", campos9 -> {
	                    if (keyword.equals(campos9[0])) {
	                        keys.add(campos9[1]);
	                    }
	                });
	            }
	        }
	    });
	    return keys; // Devuelve la lista de palabras clave encontradas
	}

	public String buscarPaquete(String paq) {
	    final String[] paquete = { null }; // Variable para almacenar el paquete encontrado
	    readCSV("/datos/Paquetes.csv", campos15 -> {
	        if (paq.equals(campos15[0])) {
	            paquete[0] = campos15[1];
	        }
	    });
	    return paquete[0]; // Devuelve el paquete encontrado
	}

	public boolean altaOfertaLaboralForzado(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA, List<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionUsuarioNoEncontrado,   ExceptionEmpresaInvalida, ExceptionRemuneracionOfertaLaboralNegativa{
		List<Keyword> keywords = new ArrayList<>();
		
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		KeywordHandler KeywordH = KeywordHandler.getInstance();
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		
		Map<String,  Keyword> keyw = KeywordH.obtener();
		for (Map.Entry<String,   Keyword> entry : keyw.entrySet()) {
			if (keys.contains(entry.getKey())) {
				keywords.add(entry.getValue());
			}
		}
		
		if (UsuarioH.existeNick(nickname_e)) {
			Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
			
			if (empresa != null) {
				CtrlOferta CtrlOfer = new CtrlOferta();
				boolean ofer = CtrlOfer .existeOferta(nombre);
				if (!ofer) {
					PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
					Paquete paq;
					if (paquete != null) {
						paq = PaqueteH.buscar(paquete);
					}
					else { 
						paq = null;
					}
					
					OfertaLaboral oferL;
					try {
						oferL = empresa.altaOfertaLaboralForzado(TOH.buscar(tipo),   nombre,   descripcion,   horario,   remun,   ciu,   dep,   FechaA,   keywords,   estado,   img,   paq);
						OLH.agregar(oferL);
					} catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionPaqueteNoVigente e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionCostoPaqueteNoNegativo e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExceptionDescuentoInvalido e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				return !ofer;
			}
			else {
				throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
			}
		}
		else {
			throw new ExceptionUsuarioNoEncontrado("No existe un usuario con el nickname indicado.");
		}
	
		
	}
	
	public String buscarOfertaLaboral(String codigoOfertaLaboral) {
	    final String[] ofertaLaboral = { null }; // Variable para almacenar la oferta laboral encontrada
	    readCSV("/datos/OfertasLaborales.csv", camposOfertasLaborales -> {
	        if (camposOfertasLaborales[0].equals(codigoOfertaLaboral)) {
	            ofertaLaboral[0] = camposOfertasLaborales[1];
	        }
	    });
	    return ofertaLaboral[0]; // Devuelve la oferta laboral encontrada
	}


	public Postulacion crearPostulacionForzado(String nick, String curriculumVitae, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		
		Postulante postulante = (Postulante) UsuarioH.buscarNick(nick);
		if (postulante == null) { 
			throw new IllegalArgumentException("Usuario " + nick + " no existe"); }
		try {
			return postulante.crearPostulacionForzado(curriculumVitae,   motivacion,   fecha,   URLDocExtras,   OferLab);
		} catch (ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean altaPostulacionForzado(String nombre, String nick, String curriculumVitae, String motivacion, String URLDocE, LocalDate fecha) {
		CtrlUsuario CtrllUser = new CtrlUsuario();
		boolean existe = CtrllUser.existePostulacion(nick,   nombre);
		if (!existe) {
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral oferLab = OLH.buscar(nombre);
			Postulacion postulacion = crearPostulacionForzado(nick,   curriculumVitae,   motivacion,   fecha,   URLDocE,   oferLab);
			try {
				oferLab.registrarPostulacionForzado(postulacion);
			} catch (ExceptionFechaInvalida e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return !existe;
	}



}
