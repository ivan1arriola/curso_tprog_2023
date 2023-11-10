package logica;

import java.net.MalformedURLException;
import excepciones.*;
import logica.clases.*;
import logica.controladores.CtrlOferta;
import logica.controladores.CtrlUsuario;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.manejadores.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Utils {

    private static final String PREFIX_ORIGINAL = "http://tprogdatostarea2.infinityfreeapp.com";
    private static final String PREFIX_NUEVO = "https://raw.githubusercontent.com/ivan1arriola/tprogImagenes/main";
    
//    private static final String proxyHost ="proxy.fing.edu.uy";
//    private static final int proxyPort = 3128; //TODO: no debe quedar hardcodeado en la version final
//    private static final Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
// 
    private static final Proxy proxy;
    
    static {
    	String proxyProperty = System.getProperty("http.proxyHost");
        //String proxyHost = System.getProperty("http.proxyHost");
        //String proxyPortString = System.getProperty("http.proxyPort");
        
    	if (proxyProperty != null && !proxyProperty.isEmpty()) {
    	    // Asignar el host
    	    String proxyHost = "proxy.fing.edu.uy";

    	    // Asignar el puerto (ten en cuenta que puedes necesitar convertirlo a un tipo numérico)
    	    int proxyPort = 3128;

    	           
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            
        } else {
            proxy = null;  // No se establece el proxy si no se encuentran las propiedades
        }
   
    }
    
    Map<String, String[]> usuarioCSV = new HashMap<>();
    Map<String, String[]> empresaCSV = new HashMap<>();
    Map<String, String[]> postulanteCSV = new HashMap<>();
    Map<String, String[]> seguidoresCSV = new HashMap<>();
    Map<String, String[]> tipoPubPaquetesCSV = new HashMap<>();
    Map<String, String[]> tipoPublicacionCSV = new HashMap<>();
    Map<String, String[]> resultadosPostulacionCSV = new HashMap<>();
    Map<String, String[]> postulantesOfertaLaboralFavoritas = new HashMap<>();
    Map<String, String[]> keywordsCSV = new HashMap<>();
    Map<String, String[]> ofertasLaboralesCSV = new HashMap<>();
    Map<String, String[]> ofertasLaboralesKeyCSV = new HashMap<>();
    Map<String, String[]> paquetesCSV = new HashMap<>();
    Map<String, String[]> paquetesCompraCSV = new HashMap<>();
    Map<String, String[]> postulacionesCSV = new HashMap<>();
    
  
    
   

    public Map<String, String[]> getSeguidoresCSV() {
        return seguidoresCSV;
    }

    public void setSeguidoresCSV(Map<String, String[]> seguidoresCSV) {
        this.seguidoresCSV = seguidoresCSV;
    }

    public Map<String, String[]> getResultadosPostulacionCSV() {
        return resultadosPostulacionCSV;
    }

    public void setResultadosPostulacionCSV(Map<String, String[]> resultadosPostulacionCSV) {
        this.resultadosPostulacionCSV = resultadosPostulacionCSV;
    }

    public Map<String, String[]> getPostulantesOfertaLaboralFavoritas() {
        return postulantesOfertaLaboralFavoritas;
    }

    public void setPostulantesOfertaLaboralFavoritas(Map<String, String[]> postulantesOfertaLaboralFavoritas) {
        this.postulantesOfertaLaboralFavoritas = postulantesOfertaLaboralFavoritas;
    }


    public Map<String, String[]> getUsuarioCSV() {
        return usuarioCSV;
    }

    public Map<String, String[]> getEmpresaCSV() {
        return empresaCSV;
    }


    public Map<String, String[]> getPostulanteCSV() {
        return postulanteCSV;
    }


    public Map<String, String[]> getKeywordsCSV() {
        return keywordsCSV;
    }


    public Map<String, String[]> getOfertasLaboralesCSV() {
        return ofertasLaboralesCSV;
    }



    public Map<String, String[]> getOfertasLaboralesKeyCSV() {
        return ofertasLaboralesKeyCSV;
    }


    public Map<String, String[]> getPaquetesCSV() {
        return paquetesCSV;
    }


    public Map<String, String[]> getPaquetesCompraCSV() {
        return paquetesCompraCSV;
    }



    public Map<String, String[]> getPostulacionesCSV() {
        return postulacionesCSV;
    }


    public Map<String, String[]> getTipoPublicacionCSV() {
        return tipoPublicacionCSV;
    }


    public Map<String, String[]> getTipoPubPaquetesCSV() {
        return tipoPubPaquetesCSV;
    }



    public Utils() {
        cargarSetsConCSV();
    }

    private void cargarSetsConCSV() {
        usuarioCSV = cargarCSV("Usuarios.csv");
        empresaCSV = cargarCSV("Usuarios-Empresas.csv");
        postulanteCSV = cargarCSV("Usuarios-Postulantes.csv");
        keywordsCSV = cargarCSV("Keywords.csv");
        ofertasLaboralesCSV = cargarCSV("OfertasLaborales.csv");
        ofertasLaboralesKeyCSV = cargarCSV("OfertasLaboralesKeywords.csv");
        paquetesCSV = cargarCSV("Paquetes.csv");
        paquetesCompraCSV = cargarCSV("PaquetesCompras.csv");
        postulacionesCSV = cargarCSV("Postulaciones.csv");
        tipoPublicacionCSV = cargarCSV("TipoPublicacion.csv");
        tipoPubPaquetesCSV = cargarCSV("TiposPublicacionPaquetes.csv");
        seguidoresCSV = cargarCSV("Usuarios-Seguidores.csv");
        resultadosPostulacionCSV = cargarCSV("ResultadoPostulacion.csv");
        postulantesOfertaLaboralFavoritas = cargarCSV("PostulantesOfertasLaboralesFavoritas.csv");
    }

    // Obtener datos de ofertas laborales por clave (nombre de la oferta)
    public String[] obtenerOfertaLaboral(String clave) {
        return ofertasLaboralesCSV.get(clave);
    }


    private Map<String, String[]> cargarCSV(String fileName) {
        Map<String, String[]> data = new HashMap<>();
        this.readCSV(fileName, stringsCSV -> {
            if (stringsCSV.length > 0) {
                data.put(stringsCSV[0], stringsCSV);
            }
        });
        return data;
    }


    public static String getDirectUrl(String shortUrl) {
    	 try {
             
//             // Crear la URL con el proxy
//             URL url = new URL(shortUrl);
//             HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
//             
//             // Configurar la solicitud
//             httpURLConnection.setInstanceFollowRedirects(false); // Deshabilitar redirecciones automáticas
//             httpURLConnection.setRequestMethod("GET");
// 
//             // Realizar la solicitud
//             int responseCode = httpURLConnection.getResponseCode();
//
//             // Verificar si hay redirección
//             if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
//                 String finalUrl = httpURLConnection.getHeaderField("Location");
//                 return finalUrl;
//             } else {
//                 return shortUrl;
//             }
    		 
    		 URL url = new URL(shortUrl);
             HttpURLConnection httpURLConnection;
             
             if (proxy != null) {
                 httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
             } else {
                 httpURLConnection = (HttpURLConnection) url.openConnection();
             }

             httpURLConnection.setInstanceFollowRedirects(false);
             httpURLConnection.setRequestMethod("GET");

             int responseCode = httpURLConnection.getResponseCode();

             if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                 String finalUrl = httpURLConnection.getHeaderField("Location");
                 return finalUrl;
             } else {
                 return shortUrl;
             }
               
         } catch (IOException e) {
             e.printStackTrace();
             // Manejar la excepción de manera más específica si es necesario
             return null;
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

    private String cambiarURLAlternativo(String imageUrl) {
        String nuevaURL = imageUrl.replace(PREFIX_ORIGINAL, PREFIX_NUEVO);
        return nuevaURL;
    }

    public byte[] descargarImagen(String imageUrl) throws IOException {
        String link = getDirectUrl(imageUrl);
        link = cambiarURLAlternativo(link);


        URL url = new URL(link);
        byte[] imagen;
        
        URLConnection connection;
        if (proxy==null) {
        	connection = url.openConnection();
        } else {
        	connection = url.openConnection(proxy);
        }
        
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
                InputStream inputStream = CSVLoader.getInputStream(filePath);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader)
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
                return DepUY.Paysandu;
            case "Río Negro":
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
                return DepUY.SanJose;
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
        String[] usuarioData = usuarioCSV.get(codigoUsuario);
        if (usuarioData != null && usuarioData.length > 2) {
            return usuarioData[2]; // Devuelve el nickname si se encuentra
        } else {
            return null; // Devuelve null si no se encuentra el usuario o el nickname
        }
    }


    public String buscarTipoPublicacion(String codigoOfertaLaboral) {
        String[] tipoPublicacionData = tipoPublicacionCSV.get(codigoOfertaLaboral);
        if (tipoPublicacionData != null && tipoPublicacionData.length > 0) {
            return tipoPublicacionData[1]; // Devuelve el tipo de publicación si se encuentra
        } else {
            return null; // Devuelve null si no se encuentra el tipo de publicación
        }
    }


    public List<String> buscarPalabrasClave(String codigoOfertaLaboral) {
        List<String> keys = new ArrayList<>(); // Lista para almacenar las palabras clave encontradas

        String[] ofertasLaboralesKeywordsCSV = ofertasLaboralesKeyCSV.get(codigoOfertaLaboral);

        if (ofertasLaboralesKeywordsCSV != null && ofertasLaboralesKeywordsCSV.length > 1) {
            String keywordsString = ofertasLaboralesKeywordsCSV[1];
            String[] keywordCodes = keywordsString.split(", ");

            for (String keywordCode : keywordCodes) {
                String[] keywordData = keywordsCSV.get(keywordCode);
                if (keywordData != null && keywordData.length > 0) {
                    keys.add(keywordData[1]);
                }
            }
        }

        return keys; // Devuelve la lista de palabras clave encontradas
    }



    public String buscarPaquete(String paq) {
        String[] paqueteData = paquetesCSV.get(paq);
        if (paqueteData != null && paqueteData.length > 0) {
            return paqueteData[1]; // Devuelve el paquete encontrado
        } else {
            return null; // Devuelve null si el paquete no se encuentra
        }
    }


    public void altaOfertaLaboralForzado(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA, List<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida, ExceptionRemuneracionOfertaLaboralNegativa, NoExistePaquete {
        List<Keyword> keywords = new ArrayList<>();

        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        KeywordHandler KeywordH = KeywordHandler.getInstance();
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();

        Map<String, Keyword> keyw = KeywordH.obtener();
        for (Map.Entry<String, Keyword> entry : keyw.entrySet()) {
            if (keys.contains(entry.getKey())) {
                keywords.add(entry.getValue());
            }
        }

        if (UsuarioH.existeNick(nickname_e)) {
            Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);

            if (empresa != null) {
                CtrlOferta CtrlOfer = new CtrlOferta();
                boolean ofer = CtrlOfer.existeOferta(nombre);
                if (!ofer) {
                    PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
                    Paquete paq;
                    if (paquete != null) {
                        paq = PaqueteH.buscar(paquete);
                    } else {
                        paq = null;
                    }

                    OfertaLaboral oferL;
                    try {
                        oferL = empresa.altaOfertaLaboralForzado(TOH.buscar(tipo), nombre, descripcion, horario, remun, ciu, dep, FechaA, keywords, estado, img, paq);
                        OLH.agregar(oferL);
                    } catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionCostoPaqueteNoNegativo |
                             ExceptionDescuentoInvalido | ExceptionPaqueteNoVigente e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            } else {
                throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
            }
        } else {
            throw new ExceptionUsuarioNoEncontrado("No existe un usuario con el nickname indicado.");
        }


    }

    public String buscarOfertaLaboral(String codigoOfertaLaboral) {
        String[] ofertaLaboralData = ofertasLaboralesCSV.get(codigoOfertaLaboral);
        if (ofertaLaboralData != null && ofertaLaboralData.length > 0) {
            return ofertaLaboralData[1]; // Devuelve la oferta laboral encontrada
        } else {
            return null; // Devuelve null si la oferta laboral no se encuentra
        }
    }



    public Postulacion crearPostulacionForzado(String nick, String curriculumVitae, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();

        Postulante postulante = (Postulante) UsuarioH.buscarNick(nick);
        if (postulante == null) {
            throw new IllegalArgumentException("Usuario " + nick + " no existe");
        }
        try {
            return postulante.crearPostulacionForzado(curriculumVitae, motivacion, fecha, URLDocExtras, OferLab, URLDocExtras);
        } catch (ExceptionValidezNegativa e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public boolean altaPostulacionForzado(String nombre, String nick, String curriculumVitae, String motivacion, String URLDocE, LocalDate fecha) throws OfertaLaboralNoEncontrada, ExceptionUsuarioNoEncontrado {
        CtrlUsuario CtrllUser = new CtrlUsuario();
        boolean existe = CtrllUser.existePostulacion(nick, nombre);
        if (!existe) {
            OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
            OfertaLaboral oferLab = OLH.buscar(nombre);
            Postulacion postulacion = crearPostulacionForzado(nick, curriculumVitae, motivacion, fecha, URLDocE, oferLab);
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
