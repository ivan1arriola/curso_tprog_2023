package logica.controladores;

import excepciones.*;
import logica.clases.*;
import logica.datatypes.*;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.manejadores.*;

import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

public class CtrlOferta implements ICtrlOferta {

    public CtrlOferta() {
    }

    public Set<String> listarEmpresas() {
        CtrlUsuario CtrlUser = new CtrlUsuario();
        return CtrlUser.listarEmpresas();
    }

    public Set<String> listarTipoDePublicaciones() {
        Set<String> res = new HashSet<>(); // PQ NO ME DEJA?
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        Map<String, TipoOferta> tipoOf = TOH.obtener();
        for (Entry<String, TipoOferta> entry : tipoOf.entrySet()) {
            res.add(entry.getValue().getNombre());
        }

        return res;
    }

    public boolean existeOferta(String nombre_oferta) {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        return OLH.existe(nombre_oferta);
    }

    // crear un tipoOferta
    public boolean altaTipoPublicacionOL(String nomb, String desc, int expo, int dur, float costo, LocalDate fechaA) {
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
                tipoOfer = new TipoOferta(nomb, fechaA, costo, dur, expo, desc);
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
    public boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento, byte[] imagen) throws ExceptionValidezNegativa, ExceptionDescuentoInvalido {
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

        // Verificar si 'descuento' es un porcentaje válido (mayor a 0,   menor o igual a 100)
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.");
        }

        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();

        boolean existe = PaqueteH.existe(nombre);
        if (!existe) {
            Paquete paq = new Paquete(nombre, descripcion, validez, fechaA, descuento, imagen);
            PaqueteH.agregar(paq);
        } else {
            throw new IllegalArgumentException("El argumento 'nombre' ya existe en el sistema.");
        }

        return !existe;
    }

    public boolean altaKeyword(String key) throws ExcepcionKeywordVacia {
        if (key == null || key.trim().isEmpty()) {
            throw new ExcepcionKeywordVacia("La palabra clave no puede estar vacía.");
        }

        KeywordHandler keywordHandler = KeywordHandler.getInstance();
        boolean existe = keywordHandler.existe(key);

        if (!existe) {
            Keyword keyword;
            keyword = new Keyword(key);
            keywordHandler.agregar(keyword);
        }

        return !existe;
    }

    public boolean compraPaquetes(String nickname_e, String paq, LocalDate fecha, int valor) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa, ExceptionUsuarioNoEncontrado, NoExistePaquete {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);

        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paquete = PaqueteH.buscar(paq);

        PaqueteH.actualizarPaquete(paquete);

        return empresa.compraPaquetes(paquete, fecha, valor);
    }

    public boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, Set<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionUsuarioNoEncontrado, NoExistePaquete {
        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paq = null;
        if (paquete != null) {
            paq = PaqueteH.buscar(paquete);
        }
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        boolean ofer = OLH.existe(nombre);
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        TipoOferta tipoOfer = TOH.buscar(tipo);
        if (!ofer) {
            List<Keyword> keywords = new ArrayList<Keyword>();
            KeywordHandler KeywordH = KeywordHandler.getInstance();
            Map<String, Keyword> keyss = KeywordHandler.obtener();
            for (Map.Entry<String, Keyword> entry : keyss.entrySet()) {
                if (keys.contains(entry.getKey())) {
                    keywords.add(entry.getValue());
                }
            }

            OfertaLaboral oferLab;
            try {
                oferLab = empresa.altaOfertaLaboral(tipoOfer, nombre, descripcion, horario, remun, ciu, dep, fechaA, keywords, estado, img, paq);
                OLH.agregar(oferLab);
            } catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
                     | ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido |
                     ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return !ofer;
    }

    public DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante, String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
        boolean existe = oferLab.existePostulacion(nombre_postulante);
        if (existe) {
            return oferLab.infoOfertaLaboralPost(nombre_postulante);
        } else {
            return oferLab.infoOfertaLaboralVisitante();
        }
    }

    public DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa, String nombre_oferta) throws OfertaLaboralNoEncontrada, ExceptionUsuarioNoEncontrado {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nombre_empresa);
        boolean existe = empresa.existeOfertaLaboral(nombre_oferta);
        DTOfertaExtendidoSinPConK auxiliar;
        if (existe) {
            auxiliar = oferLab.infoOfertaLaboralPropietario();
        } else {
            auxiliar = oferLab.infoOfertaLaboralVisitante();
        }
        return auxiliar;
    }

    public boolean altaPostulacion(String nombre, String nick, String curriculumVitae, String motivacion, String URLDocE, LocalDate fecha, String video) throws OfertaLaboralNoEncontrada, ExceptionUsuarioNoEncontrado {
        CtrlUsuario CtrllUser = new CtrlUsuario();
        boolean existe = CtrllUser.existePostulacion(nick, nombre);
        if (!existe) {
            OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
            OfertaLaboral oferLab = OLH.buscar(nombre);
            Postulacion postulacion = CtrllUser.crearPostulacion(nick, curriculumVitae, motivacion, fecha, URLDocE, oferLab, video);
            try {
                oferLab.registrarPostulacion(postulacion);
                OLH.actualizar(oferLab); // actualizar oferta laboral
            } catch (ExceptionFechaInvalida e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return !existe;
    }
	
	  
	/*public boolean altaPostulacionVideo(String nombre,   String nick,   String curriculumVitae,   String motivacion,   String URLDocE,   LocalDate fecha, String urlVideo) {
		CtrlUsuario CtrllUser = new CtrlUsuario();
		boolean existe = CtrllUser.existePostulacion(nick,   nombre);
		if (!existe) {
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral oferLab = OLH.buscar(nombre);
			Postulacion postulacion = CtrllUser.crearPostulacion(nick,   curriculumVitae,   motivacion,   fecha,   URLDocE,   oferLab, urlVideo);
			try {
				oferLab.registrarPostulacion(postulacion);
			} catch (ExceptionFechaInvalida e) {
				e.printStackTrace();
			}
		}
		return !existe;
	}*/

    public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
        return oferLab.infoOfertaLaboralVisitante();
    }

    public Set<String> listarOfertasLaboralesKeywords(String keywords) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
        Set<String> res = new HashSet<String>();

        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario user = entry.getValue();
            if (user.esEmpresa()) {
                Empresa empresa = (Empresa) user;
                Set<String> conjuntoS = empresa.listarOfertasLaboralesConfirmadasKeyword(keywords);
                res.addAll(conjuntoS);
            }
        }

        return res;
    }


    public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) throws ExceptionUsuarioNoEncontrado {
        CtrlUsuario CtrlUser = new CtrlUsuario();
        return CtrlUser.modificarPostulacion(nombre, nick, cvAbreviado, motivacion);
    }

    public DTPostulacion obtenerDatosPostulacionW(String nick, String ofer) throws ExceptionUsuarioNoEncontrado, TipoUsuarioNoValido {
        CtrlUsuario CtrlUser = new CtrlUsuario();
        return CtrlUser.obtenerDatosPostulacionW(nick, ofer);
    }

    public Set<String> listarOfertasLaboralesConfirmadas(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
        return empresa.listarOfertasLaboralesConfirmadas();
    }

    public Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadas() {
        Set<DTOfertaExtendido> res = new HashSet<DTOfertaExtendido>();
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        Map<String, OfertaLaboral> ofertasLaborales = OLH.obtener();
        for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            if (entry.getValue().getEstado() == EstadoOL.Confirmada) {
                res.add(entry.getValue().obtenerDatosOferta());
            }
        }
        return res;
    }

    public Set<String> listarOfertasLaboralesIngresadas(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
        return empresa.listarOfertasLaboralesIngresadas();
    }

    public void rechazoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
        oferLab.setEstado(EstadoOL.Rechazada);
    }

    public void aceptoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OferLabH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OferLabH.buscar(nombre_oferta);
        oferLab.setEstado(EstadoOL.Confirmada);
    }

    public Set<String> listarPostulantes() {
        CtrlUsuario CtrlUser = new CtrlUsuario();
        return CtrlUser.obtenerNicknamesPostulantes();
    }

    public DTOfertaExtendido obtenerOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OferLabH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OferLabH.buscar(nombre);
        return oferLab.obtenerDatosOferta();
    }

    public void agregarTipoOfertaPaq(String paq, String TipoOfer, int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete, NoExistePaquete {
        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paquete = PaqueteH.buscar(paq);
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        TipoOferta tipoO = TOH.buscar(TipoOfer);
        paquete.crearOfertaPaquete(tipoO, cantidad);
        PaqueteH.actualizarPaquete(paquete);
    }

    public Set<String> listarPaquetes() {
        Set<String> res = new HashSet<>();
        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Map<String, Paquete> paquetes = PaqueteH.obtener();

        for (Map.Entry<String, Paquete> entry : paquetes.entrySet()) {
            Paquete paq = entry.getValue();
            res.add(paq.getNombre());
        }

        return res;
    }

    public DTPaquete obtenerDatosPaquete(String paq) throws NoExistePaquete {
        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paquete = PaqueteH.buscar(paq);
        return paquete.getDTPaquete();
    }

    public DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente {
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        boolean existe = TOH.existe(nombre);
        if (existe) {
            TipoOferta tipoOfer = TOH.buscar(nombre);
            DTTipoOferta res = tipoOfer.obtenerDT();
            return res;
        } else {
            throw new ExcepcionTipoOfertaNoExistente("El tipo de publicación de oferta laboral indicado no existe.");
        }

    }


    // REVISAR EXCEPCIONES,   NICK Y NOMBRE !!!!!
    // nombre es el nombre de la OfertaLaboral y nick el nickname del Usuario.

    public Set<String> listarKeywords() {
        Set<String> res = new HashSet<>();
        KeywordHandler KeywordH = KeywordHandler.getInstance();
        Map<String, Keyword> keys = KeywordH.obtener();
        for (Map.Entry<String, Keyword> entry : keys.entrySet()) {
            res.add(entry.getKey());
        }
        return res;
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

        if (paquet.getInfoCompra().isEmpty()) {//nadie lo compro

            return false;
        } else {//ya fue comprado

            return true;

        }
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
    public Set<String> listarPaquetesNoVencidos(String nickname_e) throws ExceptionEmpresaInvalida, ExceptionUsuarioNoEncontrado {
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
        Map<String, OfertaLaboral> ofertasLaborales = OLH.obtener();
        for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
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
        Map<String, OfertaLaboral> ofertasLaborales = OLH.obtener();

        for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
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
    public void marcarFavorita(String nick_postulante, String nomb_oferta) throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada {
    	
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Postulante postu = null;
		try {
			postu = (Postulante) UH.buscarNick(nick_postulante);

            OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
            OfertaLaboral ofer = null;
            try {
                ofer = OLH.buscar(nomb_oferta); 
                postu.marcarFavorita(ofer);
                ofer.marcadaFav();
            } catch (OfertaLaboralNoEncontrada e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @Override
    public void desmarcarFavorita(String nick_postulante, String nomb_oferta) throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	Postulante postu = null;
		try {
			postu = (Postulante) UH.buscarNick(nick_postulante);
            OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
            OfertaLaboral ofer = null;
            try {
                ofer = OLH.buscar(nomb_oferta);
                postu.desmarcarFavorita(ofer);
                ofer.desmarcadaFav();
            } catch (OfertaLaboralNoEncontrada e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public void establecerPosiciones(String nombre_oferta,String nombreEmpresa,List<String> nicksPostulante) throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada, ExisteOrdenFinalDePostulantes {
        if(HayOrdenFinal(nombre_oferta)) throw new ExisteOrdenFinalDePostulantes("No se reasignar el orden de postulantes");
        UsuarioHandler UH = UsuarioHandler.getInstance();
    	Empresa empresa = null;
        empresa = (Empresa) UH.buscarNick(nombreEmpresa);
        empresa.establecerPosicion(nombre_oferta,nicksPostulante);
    }
    
    // orden de las posiciones
    @Override
    public boolean HayOrdenFinal(String nombre_oferta) throws ExceptionUsuarioNoEncontrado {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral oferta = null;
		try {
			oferta = OLH.buscar(nombre_oferta);
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String nicknameEmpresa = oferta.getEmpresaPublicadora().getNickname();
    	Empresa empresa = null;
        empresa = (Empresa) UH.buscarNick(nicknameEmpresa);
        return empresa.HayOrden(nombre_oferta);
    }
    
    @Override
    public List<String> DevolverOrdenFinal(String nombre_oferta) throws ExceptionUsuarioNoEncontrado {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral oferta = null;
		try {
			oferta = OLH.buscar(nombre_oferta);
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String nicknameEmpresa = oferta.getEmpresaPublicadora().getNickname();
    	Empresa empresa = null;
        empresa = (Empresa) UH.buscarNick(nicknameEmpresa);
        return empresa.DevolverOrden(nombre_oferta);
    }
    
    @Override
    public void finalizarOfertaLaboral(String nombre_oferta) {
    	UsuarioHandler UH = UsuarioHandler.getInstance();
    	OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
    	OfertaLaboral oferta = null;
		try {
			oferta = OLH.buscar(nombre_oferta);
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String nicknameEmpresa = oferta.getEmpresaPublicadora().getNickname();
    	Empresa empresa = null;
        try {
			empresa = (Empresa) UH.buscarNick(nicknameEmpresa);
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	empresa.finalizarOfertaLaboral(nombre_oferta);
    }
}

