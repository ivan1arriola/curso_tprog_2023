package main.java.logica.controladores;

import java.time.LocalDate;

import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import main.java.logica.clases.Empresa;
import main.java.logica.clases.InfoCompra;
import main.java.logica.clases.Keyword;
import main.java.logica.clases.OfertaLaboral;
import main.java.logica.clases.Paquete;
import main.java.logica.clases.Postulacion;
import main.java.logica.clases.TipoOferta;
import main.java.logica.clases.Usuario;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTTipoOferta;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.manejadores.KeywordHandler;
import main.java.logica.manejadores.OfertaLaboralHandler;
import main.java.logica.manejadores.PaqueteHandler;
import main.java.logica.manejadores.TipoOfertaHandler;
import main.java.logica.manejadores.UsuarioHandler;
import main.java.excepciones.ExcepcionKeywordVacia;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import main.java.excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import main.java.excepciones.ExceptionCompraPaqueteConValorNegativo;
import main.java.excepciones.ExceptionCostoPaqueteNoNegativo;
import main.java.excepciones.ExceptionDescuentoInvalido;
import main.java.excepciones.ExceptionDuracionNegativa;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionExpoNegativa;
import main.java.excepciones.ExceptionFechaInvalida;
import main.java.excepciones.ExceptionPaqueteNoVigente;
import main.java.excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import main.java.excepciones.ExceptionValidezNegativa;

public class CtrlOferta implements ICtrlOferta{

	public CtrlOferta() {}
	
	public Set<String> listarEmpresas(){
		CtrlUsuario CtrlUser = new CtrlUsuario();
		return CtrlUser.listarEmpresas();
	}
	
	public Set<String> listarTipoDePublicaciones(){
		Set<String> res = new HashSet<>(); // PQ NO ME DEJA?
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		Map<String,   TipoOferta> tipoOf = TOH.obtener();
		for (Entry<String,   TipoOferta> entry : tipoOf.entrySet()) {
			res.add(entry.getValue().getNombre());
		}

		return res;
	}
	
	public boolean existeOferta(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		return OLH.existe(nombre_oferta);
	}

	// crear un tipoOferta	
	public boolean altaTipoPublicacionOL(String nomb,   String desc,   int expo,   int dur,   float costo,   LocalDate fechaA) {
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
				tipoOfer = new TipoOferta(nomb,   fechaA,   costo,   dur,   expo,   desc);
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
	public boolean altaPaqueteOL(String nombre,   String descripcion,   int validez,   LocalDate fechaA,   float descuento,   byte[] imagen) throws ExceptionValidezNegativa, ExceptionDescuentoInvalido {
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
			Paquete paq = new Paquete(nombre,   descripcion,   validez,   fechaA,   descuento,   imagen);
			PaqueteH.agregar(paq);
		}
		else {
			throw new IllegalArgumentException("El argumento 'nombre' ya existe en el sistema.");
		}
		
		return !existe;
	}

	public boolean altaKeyword(String key) throws ExcepcionKeywordVacia {
		KeywordHandler KeywordH = KeywordHandler.getInstance();
		boolean existe = KeywordH.existe(key);
		if (!existe) {
			Keyword KEY = new Keyword(key);
			KeywordH.agregar(KEY);
		}
		return !existe;
	}
	
	public boolean compraPaquetes(String nickname_e,  String paq, LocalDate fecha, int valor) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
		
		PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
		Paquete paquete = PaqueteH.buscar(paq);
		
		return empresa.compraPaquetes(paquete, fecha, valor);
	}
	
	public boolean altaOfertaLaboral(String nickname_e,   String tipo,   String nombre,   String descripcion,   DTHorario horario,   float remun,   String ciu,   DepUY dep,   LocalDate fechaA,   Set<String> keys,   EstadoOL estado,   byte[] img,   String paquete) throws ExceptionRemuneracionOfertaLaboralNegativa {
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
			Map<String,   Keyword> keyss = KeywordH.obtener();
			for (Map.Entry<String,   Keyword> entry : keyss.entrySet()) {
				if (keys.contains(entry.getKey())) {
					keywords.add(entry.getValue());
				}
			}
			
			OfertaLaboral oferLab;
			try {
				oferLab = empresa.altaOfertaLaboral(tipoOfer,   nombre,   descripcion,   horario,   remun,   ciu,   dep,   fechaA,   keywords,   estado,   img,   paq);
				OLH.agregar(oferLab);
			} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
					| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido | ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return !ofer;
	}

	public DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante,   String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
		boolean existe = oferLab.existePostulacion(nombre_postulante);
		if (existe) {
			return oferLab.infoOfertaLaboralPost(nombre_postulante);
		}
		else {
			return oferLab.infoOfertaLaboralVisitante();
		}
	}
	
	public DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa,   String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nombre_empresa);
		boolean existe = empresa.existeOfertaLaboral(nombre_oferta);
		DTOfertaExtendidoSinPConK auxiliar;
		if (existe) {
			auxiliar = oferLab.infoOfertaLaboralPropietario();
		}
		else {
			auxiliar = oferLab.infoOfertaLaboralVisitante();
		}
		return auxiliar;
	}
	
	public boolean altaPostulacion(String nombre,   String nick,   String curriculumVitae,   String motivacion,   String URLDocE,   LocalDate fecha) {
		CtrlUsuario CtrllUser = new CtrlUsuario();
		boolean existe = CtrllUser.existePostulacion(nick,   nombre);
		if (!existe) {
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral oferLab = OLH.buscar(nombre);
			Postulacion postulacion = CtrllUser.crearPostulacion(nick,   curriculumVitae,   motivacion,   fecha,   URLDocE,   oferLab);
			try {
				oferLab.registrarPostulacion(postulacion);
			} catch (ExceptionFechaInvalida e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return !existe;
	}
	
	public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
		return oferLab.infoOfertaLaboralVisitante();
	}
	
	public Set<String> listarOfertasLaboralesKeywords(String keywords){
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Map<String,  Usuario> usuarios = UsuarioH.obtenerNick();
		Set<String> res = new HashSet<String>();
		
		for (Map.Entry<String,   Usuario> entry : usuarios.entrySet()) {
			Usuario user = entry.getValue();
			if (user.esEmpresa()) {
				Empresa empresa = (Empresa) user;
				Set<String> conjuntoS = empresa.listarOfertasLaboralesConfirmadasKeyword(keywords);
				res.addAll(conjuntoS);
			}
		}
		
		return res;
	}
	

	
	public boolean modificarPostulacion(String nombre,   String nick,   String cvAbreviado,   String motivacion) {
		CtrlUsuario CtrlUser = new CtrlUsuario();
		return CtrlUser.modificarPostulacion(nombre,   nick,   cvAbreviado,   motivacion);
	}
	
	public DTPostulacion obtenerDatosPostulacionW(String nick,   String ofer) {
		CtrlUsuario CtrlUser = new CtrlUsuario();
		return CtrlUser.obtenerDatosPostulacionW(nick,  ofer);
	}
	
	public Set<String> listarOfertasLaboralesConfirmadas(String nickname_e){
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
		return empresa.listarOfertasLaboralesConfirmadas();
	}
	
	public Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadas() {
		Set<DTOfertaExtendido> res = new HashSet<DTOfertaExtendido>();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		Map<String,  OfertaLaboral> ofertasLaborales = OLH.obtener();
		for (Map.Entry<String,  OfertaLaboral> entry : ofertasLaborales.entrySet()) {
			if (entry.getValue().getEstado() == EstadoOL.Confirmada) {
				res.add(entry.getValue().obtenerDatosOferta());
			}	
        }
		return res;
	}
	
	public Set<String> listarOfertasLaboralesIngresadas(String nickname_e){
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
		return empresa.listarOfertasLaboralesIngresadas();
	}
	
	public void rechazoOL(String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
		oferLab.setEstado(EstadoOL.Rechazada);		
	}
	
	public void aceptoOL(String nombre_oferta) {
		OfertaLaboralHandler OferLabH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OferLabH.buscar(nombre_oferta);
		oferLab.setEstado(EstadoOL.Confirmada);
	}
	
	public Set<String> listarPostulantes(){
		CtrlUsuario CtrlUser = new CtrlUsuario();
		return CtrlUser.obtenerNicknamesPostulantes();
	}
	
	public DTOfertaExtendido obtenerOfertaLaboral(String nombre) {
		OfertaLaboralHandler OferLabH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OferLabH.buscar(nombre);
		return oferLab.obtenerDatosOferta();
	}
	
	public void agregarTipoOfertaPaq(String paq,   String TipoOfer,   int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete {
		PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
		Paquete paquete = PaqueteH.buscar(paq);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TipoOferta tipoO = TOH.buscar(TipoOfer);
		paquete.crearOfertaPaquete(tipoO,   cantidad);
	}
	
	public Set<String> listarPaquetes(){
		Set<String> res = new HashSet<>();
		PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
		Map<String,   Paquete> paquetes = PaqueteH.obtener();
		
		for (Map.Entry<String,   Paquete> entry : paquetes.entrySet()) {
			Paquete paq = entry.getValue();
			res.add(paq.getNombre());
		}
		
		return res;
	}
	
	public DTPaquete obtenerDatosPaquete(String paq) {
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
		}
		else {
			throw new ExcepcionTipoOfertaNoExistente("El tipo de publicación de oferta laboral indicado no existe.");
		}
		
	}
	
	
	
	// REVISAR EXCEPCIONES,   NICK Y NOMBRE !!!!!
	// nombre es el nombre de la OfertaLaboral y nick el nickname del Usuario.
	
	public Set<String> listarKeywords(){
		Set<String> res = new HashSet<>();
		KeywordHandler KeywordH = KeywordHandler.getInstance();
		Map<String,  Keyword> keys = KeywordH.obtener();
		for (Map.Entry<String,   Keyword> entry : keys.entrySet()) {
			res.add(entry.getKey());
		}
		return res;
	}
	
	// notar que esta operacion es le paso el nombrre de la oferta laboral y tretorna el tipo de oferta laboral
	public DTTipoOferta tipoOferta(String oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral oferLab = OLH.buscar(oferta);
		TipoOferta tipoOferta = oferLab.getTipoOferta();
		DTTipoOferta res = tipoOferta.obtenerDT();
		return res;
	}
	
	
	public boolean paqueteComprado(String pack) {
		
		PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
		Paquete paquet = PaqueteH.buscar(pack);

		if (paquet.getInfoCompra().isEmpty()) {//nadie lo compro 

			return false; 
		} else {//ya fue comprado

			return true;

		}
	}

	@Override
	public Set<String> listarComprasPaquete(String nicknameEmpresa) {
		Empresa empresa = (Empresa) UsuarioHandler.getInstance().buscarNick(nicknameEmpresa);
		Set<InfoCompra> infoCompras = empresa.getInfoCompmras();
		Set<String> nombresPaquetes =  new HashSet<String>();		
		for (InfoCompra infoCompra : infoCompras) {
			
			nombresPaquetes.add(infoCompra.getPaquete().getNombre());
		}
		return nombresPaquetes;
	}

	@Override
	public Set<String> listarTodasLasOfertasLaborales(String nickname_e) {
		UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
		Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
		return empresa.listarOfertasLaborales();
	}
	
	public Set<String> listarPaquetesNoVencidos(String nickname_e) throws ExceptionEmpresaInvalida{
		UsuarioHandler uHan = UsuarioHandler.getInstance();
		Empresa emp = (Empresa) uHan.buscarNick(nickname_e);
		
		if (emp != null) {
			return emp.listarPaquetesNoVencidos();
		} else {
			throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
		}

	}
	
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
	
	public boolean existeOfertaLaboral(String nombre_ofer) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		return OLH.existe(nombre_ofer);
	}
}
