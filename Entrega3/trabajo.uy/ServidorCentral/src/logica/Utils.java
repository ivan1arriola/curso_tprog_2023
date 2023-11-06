package logica;

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
    private static final String DEFAULT_UBICACION = System.getProperty("user.home") + File.separator + ".trabajoUy";

    private static final String CONFIG_FOLDER = System.getProperty("user.home") + File.separator + ".trabajoUy";

    public static String getUbicacionImagenes() {
        return DEFAULT_UBICACION;
    }

    private static void crearDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + rutaDirectorio);
            } else {
                System.err.println("No se pudo crear el directorio: " + rutaDirectorio);
            }
        }
    }

    Map<String, String[]> usuarioCSV = new HashMap<>();
    Map<String, String[]> empresaCSV = new HashMap<>();
    Map<String, String[]> postulanteCSV = new HashMap<>();
    Map<String, String[]> keywordsCSV = new HashMap<>();
    Map<String, String[]> ofertasLaboralesCSV = new HashMap<>();
    Map<String, String[]> ofertasLaboralesKeyCSV = new HashMap<>();
    Map<String, String[]> paquetesCSV = new HashMap<>();
    Map<String, String[]> paquetesCompraCSV = new HashMap<>();
    Map<String, String[]> postulacionesCSV = new HashMap<>();
    Map<String, String[]> tipoPublicacionCSV = new HashMap<>();
    Map<String, String[]> tipoPubPaquetesCSV = new HashMap<>();


    public Map<String, String[]> getUsuarioCSV() {
        return usuarioCSV;
    }

    public void setUsuarioCSV(Map<String, String[]> usuarioCSV) {
        this.usuarioCSV = usuarioCSV;
    }

    public Map<String, String[]> getEmpresaCSV() {
        return empresaCSV;
    }

    public void setEmpresaCSV(Map<String, String[]> empresaCSV) {
        this.empresaCSV = empresaCSV;
    }

    public Map<String, String[]> getPostulanteCSV() {
        return postulanteCSV;
    }

    public void setPostulanteCSV(Map<String, String[]> postulanteCSV) {
        this.postulanteCSV = postulanteCSV;
    }

    public Map<String, String[]> getKeywordsCSV() {
        return keywordsCSV;
    }

    public void setKeywordsCSV(Map<String, String[]> keywordsCSV) {
        this.keywordsCSV = keywordsCSV;
    }

    public Map<String, String[]> getOfertasLaboralesCSV() {
        return ofertasLaboralesCSV;
    }

    public void setOfertasLaboralesCSV(Map<String, String[]> ofertasLaboralesCSV) {
        this.ofertasLaboralesCSV = ofertasLaboralesCSV;
    }

    public Map<String, String[]> getOfertasLaboralesKeyCSV() {
        return ofertasLaboralesKeyCSV;
    }

    public void setOfertasLaboralesKeyCSV(Map<String, String[]> ofertasLaboralesKeyCSV) {
        this.ofertasLaboralesKeyCSV = ofertasLaboralesKeyCSV;
    }

    public Map<String, String[]> getPaquetesCSV() {
        return paquetesCSV;
    }

    public void setPaquetesCSV(Map<String, String[]> paquetesCSV) {
        this.paquetesCSV = paquetesCSV;
    }

    public Map<String, String[]> getPaquetesCompraCSV() {
        return paquetesCompraCSV;
    }

    public void setPaquetesCompraCSV(Map<String, String[]> paquetesCompraCSV) {
        this.paquetesCompraCSV = paquetesCompraCSV;
    }

    public Map<String, String[]> getPostulacionesCSV() {
        return postulacionesCSV;
    }

    public void setPostulacionesCSV(Map<String, String[]> postulacionesCSV) {
        this.postulacionesCSV = postulacionesCSV;
    }

    public Map<String, String[]> getTipoPublicacionCSV() {
        return tipoPublicacionCSV;
    }

    public void setTipoPublicacionCSV(Map<String, String[]> tipoPublicacionCSV) {
        this.tipoPublicacionCSV = tipoPublicacionCSV;
    }

    public Map<String, String[]> getTipoPubPaquetesCSV() {
        return tipoPubPaquetesCSV;
    }

    public void setTipoPubPaquetesCSV(Map<String, String[]> tipoPubPaquetesCSV) {
        this.tipoPubPaquetesCSV = tipoPubPaquetesCSV;
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

    }

    public String[] obtenerUsuario(String clave) {
        return usuarioCSV.get(clave);
    }

    // Obtener empresa por clave (nombre de empresa)
    public String[] obtenerEmpresa(String clave) {
        return empresaCSV.get(clave);
    }

    // Obtener postulante por clave (nombre de postulante)
    public String[] obtenerPostulante(String clave) {
        return postulanteCSV.get(clave);
    }

    // Obtener datos de keywords por clave
    public String[] obtenerKeyword(String clave) {
        return keywordsCSV.get(clave);
    }

    // Obtener datos de ofertas laborales por clave (nombre de la oferta)
    public String[] obtenerOfertaLaboral(String clave) {
        return ofertasLaboralesCSV.get(clave);
    }

    // Obtener datos de ofertas laborales por clave (nombre de la oferta)
    public String[] obtenerOfertaLaboralKey(String clave) {
        return ofertasLaboralesKeyCSV.get(clave);
    }

    // Obtener datos de paquetes por clave
    public String[] obtenerPaquete(String clave) {
        return paquetesCSV.get(clave);
    }

    // Obtener datos de paquetes de compra por clave
    public String[] obtenerPaqueteCompra(String clave) {
        return paquetesCompraCSV.get(clave);
    }

    // Obtener datos de postulaciones por clave
    public String[] obtenerPostulacion(String clave) {
        return postulacionesCSV.get(clave);
    }

    // Obtener datos de tipo de publicación por clave
    public String[] obtenerTipoPublicacion(String clave) {
        return tipoPublicacionCSV.get(clave);
    }

    // Obtener datos de tipo de publicación de paquetes por clave
    public String[] obtenerTipoPublicacionPaquete(String clave) {
        return tipoPubPaquetesCSV.get(clave);
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

    public static String generateImageCode(String input) {
        // Reemplaza caracteres no válidos con guiones bajos (_)
        String sanitizedInput = input.replaceAll("[^a-zA-Z0-9.-]", "_");

        // Limita la longitud del nombre de archivo a un valor razonable
        int maxFileNameLength = 255; // Establece la longitud máxima deseada
        if (sanitizedInput.length() > maxFileNameLength) {
            sanitizedInput = sanitizedInput.substring(0, maxFileNameLength);
        }

        return sanitizedInput;
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


    public boolean altaOfertaLaboralForzado(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA, List<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida, ExceptionRemuneracionOfertaLaboralNegativa, NoExistePaquete {
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
