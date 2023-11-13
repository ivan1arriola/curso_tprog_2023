package logica.controladores;

import excepciones.AsignarOrdenAOfertaFinalizada;
import excepciones.AsignarOrdenAOfertaNoVencida;
//import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
//import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
//import excepciones.ExceptionUsuarioCorreoRepetido;
//import excepciones.ExceptionUsuarioNickRepetido;
//import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
//import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
//import excepciones.FaltaCvException;
//import excepciones.FaltaMotivaException;
import excepciones.FinalizarOfertaNoVencida;
import excepciones.FinalizarOfertaYaFinalizada;
import excepciones.NoExistePaquete;
import excepciones.NoHayOrdenDefinidoDePostulantes;
import excepciones.OfertaLaboralNoEncontrada;
//import excepciones.PostulaExistenteException;
import excepciones.TipoUsuarioNoValido;
//import excepciones.UsuarioNoExisteException;
//import excepciones.ErrorAgregarUsuario;
//import excepciones.ExceptionCiudadInvalida;
//import excepciones.ExceptionUsuarioCorreoRepetido;
//import excepciones.ExceptionUsuarioNickRepetido;
//import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
//import excepciones.ExceptionUsuarioSeSigueASiMismo;
//import excepciones.FaltaCvException;
//import excepciones.FaltaMotivaException;
//import excepciones.PostulaExistenteException;
//import excepciones.UsuarioNoExisteException;

import logica.clases.Empresa;
import logica.clases.InfoCompra;
//import logica.clases.InfoCompraOferta;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
//import logica.clases.OfertaPaquete;
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.clases.TipoOferta;
import logica.clases.Usuario;

//import logica.datatypes.DTCantTO;
//import logica.datatypes.DTCompraPaquetes;
//import logica.datatypes.DTEmpresa;
//import logica.datatypes.DTEmpresaConCompras;
//import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
//import logica.datatypes.DTOfertaExtendidoConKeywords;
//import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
//import logica.datatypes.DTOfertaExtendidoConKeywordsTit;
import logica.datatypes.DTOfertaExtendidoSinPConK;
//import logica.datatypes.DTOfertaLaboral;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulacion;
//import logica.datatypes.DTPostulante;
//import logica.datatypes.DTPostulanteExtendido;
import logica.datatypes.DTTipoOferta;
import logica.dto.EmpresaDTO;
//import logica.datatypes.DTUsuario;
//import logica.datatypes.DTUsuarioSinInfoSocial;
import logica.dto.OfertaLaboralDTO;
import logica.dto.PostulacionDTO;
import logica.dto.PostulanteDTO;
//import logica.dto.UsuarioDTO;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;
import logica.persistencia.TrabajoUyHistoricoManager;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

public class CtrlOferta implements ICtrlOferta {

    private final ICtrlUsuario ctrlUsuario;
    private UsuarioHandler usuarioHandler;
    private final PaqueteHandler paqueteHandler;
    private final KeywordHandler keywordHandler;
    private final OfertaLaboralHandler ofertaLaboralHandler;
    private final TipoOfertaHandler tipoOfertaHandler;
    //private KeywordHandler keywordH;

    public CtrlOferta() {
        usuarioHandler = UsuarioHandler.getInstance();
        paqueteHandler = PaqueteHandler.getInstance();
        keywordHandler = KeywordHandler.getInstance();
        ofertaLaboralHandler = OfertaLaboralHandler.getInstance();
        tipoOfertaHandler = TipoOfertaHandler.getInstance();

        ctrlUsuario = new CtrlUsuario();
    }

    public Set<String> listarEmpresas() {
        CtrlUsuario CtrlUser = new CtrlUsuario();
        return CtrlUser.listarEmpresas();
    }

    public Set<String> listarTipoDePublicaciones() {
        Set<String> res = new HashSet<>(); // PQ NO ME DEJA?
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        Map<String,  TipoOferta> tipoOf = TOH.obtener();
        for (Entry<String,  TipoOferta> entry : tipoOf.entrySet()) {
            res.add(entry.getValue().getNombre());
        }

        return res;
    }

    public boolean existeOferta(String nombre_oferta) {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        return OLH.existe(nombre_oferta);
    }

    // crear un tipoOferta
    public boolean altaTipoPublicacionOL(String nomb,  String desc,  int expo,  int dur,  float costo,  LocalDate fechaA) {
        if (nomb == null || nomb.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        if (desc == null || desc.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }

        if (expo < 1) {
            throw new IllegalArgumentException("La exposición debe ser al menos 1.");
        }

        if (dur < 1) {
            throw new IllegalArgumentException("La duración debe ser al menos 1 día.");
        }

        if (costo < 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo.");
        }

        if (fechaA == null) {
            throw new IllegalArgumentException("La fecha de alta no puede ser nula.");
        }

        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();


        boolean existe = TOH.existe(nomb);

        if (existe) {
            throw new IllegalArgumentException("Ya existe una oferta con ese nombre");
        } else {
            TipoOferta tipoOfer;
            try {
                tipoOfer = new TipoOferta(nomb,  fechaA,  costo,  dur,  expo,  desc);
                TOH.agregar(tipoOfer);
                return true;
            } catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

        }
    }

    // crear un paquete
    public boolean altaPaqueteOL(String nombre,  String descripcion,  int validez,  LocalDate fechaA,  float descuento,  byte[] imagen) throws ExceptionValidezNegativa,  ExceptionDescuentoInvalido {
        // Verificar si el argumento 'nombre' es vacío
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El argumento 'nombre' no puede ser vacío.");
        }

        // Verificar si el argumento 'descripcion' es vacío
        if (descripcion.isEmpty()) {
            throw new IllegalArgumentException("El argumento 'descripcion' no puede ser vacío.");
        }

        // Verificar si 'validez' es mayor a 0
        if (validez <= 0) {
            throw new IllegalArgumentException("El argumento 'validez' debe ser mayor a 0.");
        }

        // Verificar si 'descuento' es un porcentaje válido (mayor a 0,    menor o igual a 100)
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.");
        }


        boolean existe = paqueteHandler.existe(nombre);
        if (!existe) {
            Paquete paq = new Paquete(nombre,  descripcion,  validez,  fechaA,  descuento,  imagen);
            paqueteHandler.agregar(paq);
        } else {
            throw new IllegalArgumentException("El argumento 'nombre' ya existe en el sistema.");
        }

        return !existe;
    }

    public boolean altaKeyword(String key) throws ExcepcionKeywordVacia {
        if (key == null || key.trim().isEmpty()) {
            throw new ExcepcionKeywordVacia("La palabra clave no puede estar vacía.");
        }


        boolean existe = keywordHandler.existe(key);

        if (!existe) {
            Keyword keyword;
            keyword = new Keyword(key);
            keywordHandler.agregar(keyword);
        }

        return !existe;
    }

    public boolean compraPaquetes(String nickname_e,  String paq,  LocalDate fecha,  int valor) throws ExceptionCompraPaqueteConValorNegativo,  ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa,  ExceptionValidezNegativa,  ExceptionUsuarioNoEncontrado,  NoExistePaquete {
        Empresa empresa = (Empresa) usuarioHandler.buscarNick(nickname_e);

        Paquete paquete = paqueteHandler.buscar(paq);

        paqueteHandler.actualizarPaquete(paquete);

        return empresa.compraPaquetes(paquete,  fecha,  valor);
    }

    public boolean altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  String descripcion,  DTHorario horario,  float remun,  String ciu,  DepUY dep,  LocalDate fechaA,  Set<String> keys,  EstadoOL estado,  byte[] img,  String paquete) throws ExceptionRemuneracionOfertaLaboralNegativa,  ExceptionUsuarioNoEncontrado,  NoExistePaquete,  ExceptionPaqueteNoVigente,  ExceptionCostoPaqueteNoNegativo,  ExceptionDescuentoInvalido,  ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {

        Paquete paq = null;
        if (paquete != null) {
            paq = paqueteHandler.buscar(paquete);
        }
        Empresa empresa = (Empresa) usuarioHandler.buscarNick(nickname_e);

        boolean ofer = ofertaLaboralHandler.existe(nombre);
        TipoOferta tipoOfer = tipoOfertaHandler.buscar(tipo);
        if (!ofer) {
            List<Keyword> keywords = new ArrayList<Keyword>();
            Map<String,  Keyword> keyss = KeywordHandler.obtener();
            for (Entry<String,  Keyword> entry : keyss.entrySet()) {
                if (keys.contains(entry.getKey())) {
                    keywords.add(entry.getValue());
                }
            }

            OfertaLaboral oferLab;
            
            oferLab = empresa.altaOfertaLaboral(tipoOfer,  nombre,  descripcion,  horario,  remun,  ciu,  dep,  fechaA,  keywords,  estado,  img,  paq);
            ofertaLaboralHandler.agregar(oferLab);
           

        }
        return !ofer;
    }


    public DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante,  String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre_oferta);
        boolean existe = ofertaLaboral.existePostulacion(nombre_postulante);
        if (existe) {
            return ofertaLaboral.infoOfertaLaboralPost(nombre_postulante);
        } else {
            return ofertaLaboral.infoOfertaLaboralVisitante();
        }
    }

    public DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa,  String nombre_oferta) throws OfertaLaboralNoEncontrada,  ExceptionUsuarioNoEncontrado {
        OfertaLaboral oferLab = ofertaLaboralHandler.buscar(nombre_oferta);
        Empresa empresa = (Empresa) usuarioHandler.buscarNick(nombre_empresa);
        boolean existe = empresa.existeOfertaLaboral(nombre_oferta);
        DTOfertaExtendidoSinPConK auxiliar;
        if (existe) {
            auxiliar = oferLab.infoOfertaLaboralPropietario();
        } else {
            auxiliar = oferLab.infoOfertaLaboralVisitante();
        }
        return auxiliar;
    }

    public boolean altaPostulacion(String nombre,  String nick,  String curriculumVitae,  String motivacion,  String URLDocE,  LocalDate fecha,  String video) throws OfertaLaboralNoEncontrada,  ExceptionUsuarioNoEncontrado,  ExceptionFechaInvalida {
        boolean existe = ctrlUsuario.existePostulacion(nick,  nombre);
        if (!existe) {
            OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre);
            Postulacion postulacion = ctrlUsuario.crearPostulacion(nick,  curriculumVitae,  motivacion,  fecha,  URLDocE,  ofertaLaboral,  video);
            ofertaLaboral.registrarPostulacion(postulacion);
            ofertaLaboralHandler.actualizar(ofertaLaboral);
        }
        return !existe;
    }
	
	  
	/*public boolean altaPostulacionVideo(String nombre,    String nick,    String curriculumVitae,    String motivacion,    String URLDocE,    LocalDate fecha,  String urlVideo) {
		CtrlUsuario CtrllUser = new CtrlUsuario();
		boolean existe = CtrllUser.existePostulacion(nick,    nombre);
		if (!existe) {
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral oferLab = OLH.buscar(nombre);
			Postulacion postulacion = CtrllUser.crearPostulacion(nick,    curriculumVitae,    motivacion,    fecha,    URLDocE,    oferLab,  urlVideo);
			try {
				oferLab.registrarPostulacion(postulacion);
			} catch (ExceptionFechaInvalida e) {
				e.printStackTrace();
			}
		}
		return !existe;
	}*/

    public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre_oferta);
        return ofertaLaboral.infoOfertaLaboralVisitante();
    }

    public Set<String> listarOfertasLaboralesKeywords(String keywords) {
        Map<String,  Usuario> usuarios = usuarioHandler.obtenerNick();
        Set<String> res = new HashSet<String>();

        for (Map.Entry<String,  Usuario> entry : usuarios.entrySet()) {
            Usuario user = entry.getValue();
            if (user.esEmpresa()) {
                Empresa empresa = (Empresa) user;
                Set<String> conjuntoS = empresa.listarOfertasLaboralesConfirmadasKeyword(keywords);
                res.addAll(conjuntoS);
            }
        }

        return res;
    }


    public boolean modificarPostulacion(String nombre,  String nick,  String cvAbreviado,  String motivacion) throws ExceptionUsuarioNoEncontrado {
        return ctrlUsuario.modificarPostulacion(nombre,  nick,  cvAbreviado,  motivacion);
    }

    public DTPostulacion obtenerDatosPostulacionW(String nick,  String nombreOferta) throws ExceptionUsuarioNoEncontrado,  TipoUsuarioNoValido {
        return ctrlUsuario.obtenerDatosPostulacionW(nick,  nombreOferta);
    }

    public Set<String> listarOfertasLaboralesConfirmadas(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        Empresa empresa = (Empresa) usuarioHandler.buscarNick(nickname_e);
        return empresa.listarOfertasLaboralesConfirmadas();
    }

    public Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadas() {
        Set<DTOfertaExtendido> res = new HashSet<DTOfertaExtendido>();
        Map<String,  OfertaLaboral> ofertasLaborales = ofertaLaboralHandler.obtener();
        for (Map.Entry<String,  OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            if (entry.getValue().getEstado() == EstadoOL.Confirmada) {
                res.add(entry.getValue().obtenerDatosOferta());
            }
        }
        return res;
    }

    public Set<String> listarOfertasLaboralesIngresadas(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        Empresa empresa = (Empresa) usuarioHandler.buscarNick(nickname_e);
        return empresa.listarOfertasLaboralesIngresadas();
    }

    public void rechazoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre_oferta);
        ofertaLaboral.setEstado(EstadoOL.Rechazada);
    }

    public void aceptoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre_oferta);
        ofertaLaboral.setEstado(EstadoOL.Confirmada);
    }

    public Set<String> listarPostulantes() {
        return ctrlUsuario.obtenerNicknamesPostulantes();
    }

    public DTOfertaExtendido obtenerOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada {
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre);
        return ofertaLaboral.obtenerDatosOferta();
    }

    public void agregarTipoOfertaPaq(String paq,  String tipoOferta,  int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete,  NoExistePaquete {
        Paquete paquete = paqueteHandler.buscar(paq);
        TipoOferta tipoO = tipoOfertaHandler.buscar(tipoOferta);
        paquete.crearOfertaPaquete(tipoO,  cantidad);
        paqueteHandler.actualizarPaquete(paquete);
    }

    public Set<String> listarPaquetes() {
        Set<String> res = new HashSet<>();
        Map<String,  Paquete> paquetes = paqueteHandler.obtener();

        for (Map.Entry<String,  Paquete> entry : paquetes.entrySet()) {
            Paquete paq = entry.getValue();
            res.add(paq.getNombre());
        }

        return res;
    }

    public DTPaquete obtenerDatosPaquete(String paq) throws NoExistePaquete {
        Paquete paquete = paqueteHandler.buscar(paq);
        return paquete.getDTPaquete();
    }

    public DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente {
        boolean existe = tipoOfertaHandler.existe(nombre);
        if (existe) {
            TipoOferta tipoOferta = tipoOfertaHandler.buscar(nombre);
            return tipoOferta.obtenerDT();
        } else {
            throw new ExcepcionTipoOfertaNoExistente("El tipo de publicación de oferta laboral indicado no existe.");
        }

    }


    public Set<String> listarKeywords() {
    	Map<String,  Keyword> yourMap = keywordHandler.obtener();
    	Set<String> keySet = new HashSet<>();
        for (Map.Entry<String,  Keyword> entry : yourMap.entrySet()) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    // notar que esta operacion es le paso el nombrre de la oferta laboral y tretorna el tipo de oferta laboral
    public DTTipoOferta tipoOferta(String oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(oferta);
        TipoOferta tipoOferta = oferLab.getTipoOferta();
        DTTipoOferta res = tipoOferta.obtenerDT();
        return res;
    }


    public boolean paqueteComprado(String pack) throws NoExistePaquete {

        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paquet = PaqueteH.buscar(pack);

        //nadie lo compro
        //ya fue comprado
        return !paquet.getInfoCompra().isEmpty();
    }

    @Override
    public Set<String> listarComprasPaquete(String nicknameEmpresa) throws ExceptionUsuarioNoEncontrado {
        Empresa empresa = (Empresa) UsuarioHandler.getInstance().buscarNick(nicknameEmpresa);
        Set<InfoCompra> infoCompras = empresa.getInfoCompmras();
        Set<String> nombresPaquetes = new HashSet<String>();
        for (InfoCompra infoCompra : infoCompras) {

            nombresPaquetes.add(infoCompra.getPaquete().getNombre());
        }
        return nombresPaquetes;
    }

    @Override
    public Set<String> listarTodasLasOfertasLaborales(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
        return empresa.listarOfertasLaborales();
    }

    @Override
    public Set<String> listarPaquetesNoVencidos(String nickname_e) throws ExceptionEmpresaInvalida,  ExceptionUsuarioNoEncontrado {
        UsuarioHandler uHan = UsuarioHandler.getInstance();
        Empresa emp = (Empresa) uHan.buscarNick(nickname_e);

        if (emp != null) {
            return emp.listarPaquetesNoVencidos();
        } else {
            throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
        }

    }

    @Override
    public Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadasYNoVencidas() {
        Set<DTOfertaExtendido> res = new HashSet<DTOfertaExtendido>();
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        Map<String,  OfertaLaboral> ofertasLaborales = OLH.obtener();
        for (Map.Entry<String,  OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            if (entry.getValue().getEstado() == EstadoOL.Confirmada && !entry.getValue().estaVencida()) {
                res.add(entry.getValue().obtenerDatosOferta());
            }
        }
        return res;
    }

    @Override
    public Set<String> listarOfertasLaboralesConfirmadasYNoVencidasString() {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();

        Set<String> res = new HashSet<>();
        Map<String,  OfertaLaboral> ofertasLaborales = OLH.obtener();

        for (Map.Entry<String,  OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            EstadoOL estadoOferta = entry.getValue().getEstado();
            boolean estaVencida = entry.getValue().estaVencida();
            if (estadoOferta == EstadoOL.Confirmada && !estaVencida) {
                res.add(entry.getValue().obtenerDatosOferta().getNombre());
            }
        }
        return res;
    }

    @Override
    public boolean existeOfertaLaboral(String nombre_ofer) {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        return OLH.existe(nombre_ofer);
    }


    @Override
    public void marcarFavorita(String nick_postulante,  String nomb_oferta) throws ExceptionUsuarioNoEncontrado,  OfertaLaboralNoEncontrada {

        usuarioHandler = UsuarioHandler.getInstance();
    	Postulante postulante = (Postulante) usuarioHandler.buscarNick(nick_postulante);
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral ofertaLaboral = OLH.buscar(nomb_oferta);
        postulante.marcarFavorita(ofertaLaboral);
        ofertaLaboral.marcadaFav();
        System.out.println(nick_postulante + "marco como oferta laboral favorita a " + nomb_oferta);


    }

    @Override
    public void desmarcarFavorita(String nick_postulante,  String nomb_oferta) throws ExceptionUsuarioNoEncontrado,  OfertaLaboralNoEncontrada {
    	    	Postulante postulante = (Postulante) usuarioHandler.buscarNick(nick_postulante);
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral ofertaLaboral = OLH.buscar(nomb_oferta);
        postulante.desmarcarFavorita(ofertaLaboral);
        ofertaLaboral.desmarcadaFav();
    }

    public void establecerPosiciones(String nombre_oferta,  List<String> nicksPostulante) throws AsignarOrdenAOfertaFinalizada,  OfertaLaboralNoEncontrada,  AsignarOrdenAOfertaNoVencida {
        OfertaLaboralHandler ofertaLaboralHandler = OfertaLaboralHandler.getInstance();
        OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombre_oferta);
        ofertaLaboral.establecerPosicion(nicksPostulante);

        System.out.println("Se establecio un orden de postulantes : " + nicksPostulante.toString());

    }

    
    @Override
    public List<String> devolverOrdenPostulantes(String nombre_oferta) throws OfertaLaboralNoEncontrada,  NoHayOrdenDefinidoDePostulantes {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral oferta = OLH.buscar(nombre_oferta);
        return oferta.getOrdenPostulantes();
    }

    @Override
    public void descartarOrdenPostulantes(String nombreOferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferta = OLH.buscar(nombreOferta);
        oferta.descartarOrden();
    }


    @Override
    public void finalizarOfertaLaboral(String nombre_oferta) throws OfertaLaboralNoEncontrada,  FinalizarOfertaNoVencida,  FinalizarOfertaYaFinalizada {
    	OfertaLaboralHandler ofertaLaboralHandler = OfertaLaboralHandler.getInstance();
    	OfertaLaboral ofertal = ofertaLaboralHandler.buscar(nombre_oferta);
        if (!ofertal.estaVencida()){
            throw new FinalizarOfertaNoVencida("No se puede finalizar una oferta que no este vencida");
        }
        ofertal.finalizarOferta();
        // obtengo la instancia de la segunda base de datos
        TrabajoUyHistoricoManager THM = TrabajoUyHistoricoManager.getInstance();
        // primero persistir la empresa si no esta persistida ya
        Empresa empresa = ofertal.getEmpresaPublicadora();
        EmpresaDTO empresatransformado = (EmpresaDTO) empresa.getDTO();
        // veo si dicha empresa ya esta persistida o no
        if ( THM.obtenerUsuarioDT(empresatransformado.getNickname()) == null) {
        //  System.out.println("se persiste el usuario");
        	THM.guardarEmpresa(empresatransformado);
        } else {
        	empresatransformado =  (EmpresaDTO) THM.obtenerUsuarioDT(empresatransformado.getNickname());
        //  System.out.println(" la empresa tiene id " + empresatransformado.getId());
        }
        
        // persistir en memoria la oferta laboral
        OfertaLaboralDTO oferta_a_guardar = ofertal.getDTO();
        THM.guardarOfertaFinalizada(oferta_a_guardar, empresatransformado);     
        
        // Persistir las postulaciones
    	 List<Postulacion> postulacionesPersistir = ofertal.getPostulaciones();
 		for (Postulacion postulacion :postulacionesPersistir) {
 			// primero presistir los usuarios de la postulacion
 			// para eso veo si el postulante ya esta ingresado o no
 			Postulante postulante = postulacion.getPostulante();
 			PostulanteDTO postulantetransformado = (PostulanteDTO) postulante.getDTO();
 			if ( THM.obtenerUsuarioDT(postulantetransformado.getNickname()) == null) {
 	        	THM.guardarPostulante(postulantetransformado);
 	        } else {
 	        	postulantetransformado =  (PostulanteDTO) THM.obtenerUsuarioDT(postulantetransformado.getNickname());
 	           //  System.out.println(" la empresa tiene id " + empresatransformado.getId());
 	           }
            // luego la postulacion en si
 			PostulacionDTO postulacionTransformada = postulacion.getDTO(oferta_a_guardar);
 			THM.guardarPostulacion(postulacionTransformada, postulantetransformado);
 		}
		THM.cerrarBaseDatos();
    }
    
    @Override
    public void aumentarVisita(String nombre_oferta) throws OfertaLaboralNoEncontrada {
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral olab = OLH.buscar(nombre_oferta);
    	olab.setCantVisitas(olab.getCantVisitas()+1);
    }
    
    @Override
    public String obtenerTipoPubOfertaLaboral(String nomb_oferta) throws OfertaLaboralNoEncontrada  {
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral olab = OLH.buscar(nomb_oferta);
    	return olab.getTipoOferta().getNombre();
    }

    @Override
    public boolean hayOrdenDefinido(String nombreOferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferta = OLH.buscar(nombreOferta);

        return oferta.isHayOrdenDefinido();
    }

}

