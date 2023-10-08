package main.java.logica.Controladores;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import main.java.logica.Clases.OfertaLaboral;
import main.java.logica.Clases.Postulacion;
import main.java.logica.Clases.Paquete;
import main.java.logica.Clases.TipoOferta;
import main.java.logica.Clases.Usuario;
import main.java.logica.Clases.Empresa;
import main.java.logica.Clases.Keyword;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTPostulacion;
import main.java.logica.Datatypes.DTTipoOferta;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Manejadores.KeywordHandler;
import main.java.logica.Manejadores.OfertaLaboralHandler;
import main.java.logica.Manejadores.PaqueteHandler;
import main.java.logica.Manejadores.TipoOfertaHandler;
import main.java.logica.Manejadores.UsuarioHandler;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;

public class CtrlOferta implements ICtrlOferta {

	public CtrlOferta() {}
	
	public Set<String> listarEmpresas() {
		CtrlUsuario CU = new CtrlUsuario();
		return CU.listarEmpresas();
	}
	
	public HashSet<String> listarTipoDePublicaciones() {
		HashSet<String> res = new HashSet<>(); // PQ NO ME DEJA?
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		HashMap<String,TipoOferta> tipoOf = TOH.obtener();
		for (Entry<String, TipoOferta> entry : tipoOf.entrySet()) {
			res.add((entry.getValue().getNombre()));
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
			TipoOferta to = new TipoOferta(nomb, fechaA, costo, dur, expo, desc); 
			TOH.agregar(to);
			return true;
		}
	}

	// crear un paquete
	public boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento, byte[] imagen) {
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

		// Verificar si 'descuento' es un porcentaje válido (mayor a 0, menor o igual a 100)
		if (descuento < 0 || descuento > 100) {
			throw new IllegalArgumentException("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.");
		}

		PaqueteHandler PH = PaqueteHandler.getInstance();

		boolean existe = PH.existe(nombre);
		if(!existe) {
			Paquete p = new Paquete(nombre, descripcion, validez, fechaA, descuento, imagen);
			PH.agregar(p);
		}
		else {
			throw new IllegalArgumentException("El argumento 'nombre' ya existe en el sistema.");
		}
		
		return !existe;
	}

	public boolean altaKeyword(String key) {
		KeywordHandler KH = KeywordHandler.getInstance();
		boolean b = KH.existe(key);
		if (!b) {
			Keyword KEY = new Keyword(key);
			KH.agregar(KEY);
		}
		return !b;
	}
	
	public boolean compraPaquetes(String nickname_e, String paq) {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa e = (Empresa) UH.buscarNick(nickname_e);
		
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete paquete = PH.buscar(paq);
		
		return e.compraPaquetes(paquete);
	}
	
	public boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, HashSet<String> keys, EstadoOL estado, byte[] img, String paquete) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete paq = null;
		if (paquete != null) {
			paq = PH.buscar(paquete);
		}
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa e = (Empresa) UH.buscarNick(nickname_e);
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		boolean ofer = OLH.existe(nombre);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TipoOferta to = TOH.buscar(tipo);
		if(!ofer) {
			List<Keyword> ks = new ArrayList<Keyword>();
			KeywordHandler KH = KeywordHandler.getInstance();
			HashMap<String, Keyword> keyss = KH.obtener();
			for (Map.Entry<String, Keyword> entry : keyss.entrySet()) {
				if(keys.contains(entry.getKey())) {
					ks.add(entry.getValue());
				}
			}
			
			OfertaLaboral ol = e.altaOfertaLaboral(to, nombre, descripcion, horario, remun, ciu, dep, fechaA, ks, estado, img, paq);
			OLH.agregar(ol);
		}
		return !ofer;
	}

	public DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante, String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		boolean b = ol.existePostulacion(nombre_postulante);
		if(b) {
			return ol.infoOfertaLaboralPost(nombre_postulante);
		}
		else {
			return ol.infoOfertaLaboralVisitante();
		}
	}
	
	public DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa, String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa e = (Empresa) UH.buscarNick(nombre_empresa);
		boolean b = e.existeOfertaLaboral(nombre_oferta);
		DTOfertaExtendidoSinPConK auxiliar;
		if(b) {
			auxiliar = ol.infoOfertaLaboralPropietario();
		}
		else {
			auxiliar = ol.infoOfertaLaboralVisitante();
		}
		return auxiliar;
	}
	
	public boolean altaPostulacion(String nombre, String nick, String cv, String motivacion, String URLDocE, LocalDate fecha) {
		CtrlUsuario CU = new CtrlUsuario();
		boolean b = CU.existePostulacion(nick, nombre);
		if (!b) {
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral ol = OLH.buscar(nombre);
			Postulacion p = CU.crearPostulacion(nick, cv, motivacion, fecha, URLDocE, ol);
			ol.registrarPostulacion(p);
		}
		return !b;
	}
	
	public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		return ol.infoOfertaLaboralVisitante();
	}
	
	public HashSet<String> listarOfertasLaboralesKeywords(String ks){
		UsuarioHandler UH = UsuarioHandler.getInstance();
		HashMap<String,Usuario> usuarios = UH.obtenerNick();
		HashSet<String> res = new HashSet<String>();
		
		for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
			Usuario u = entry.getValue();
			if(u.esEmpresa()) {
				Empresa e = (Empresa) u;
				HashSet<String> S = e.listarOfertasLaboralesConfirmadasKeyword(ks);
				res.addAll(S);
			}
		}
		
		return res;
	}
	
	public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) {
		CtrlUsuario CU = new CtrlUsuario();
		return CU.modificarPostulacion(nombre, nick, cvAbreviado, motivacion);
	}
	
	public DTPostulacion obtenerDatosPostulacionW(String nick, String ofer) {
		CtrlUsuario CU = new CtrlUsuario();
		return CU.obtenerDatosPostulacionW(nick,ofer);
	}
	
	public HashSet<String> listarOfertasLaboralesConfirmadas(String nickname_e){
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa e = (Empresa) UH.buscarNick(nickname_e);
		return e.listarOfertasLaboralesConfirmadas();
	}
	
	public HashSet<DTOfertaExtendido> listarOfertasLaboralesConfirmadas() {
		HashSet<DTOfertaExtendido> res = new HashSet<DTOfertaExtendido>();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		HashMap<String,OfertaLaboral> ofertasLaborales = OLH.obtener();
		for (Map.Entry<String,OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            res.add(entry.getValue().obtenerDatosOferta());
        }
		return res;
	}
	
	public HashSet<String> listarOfertasLaboralesIngresadas(String nickname_e){
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Empresa e = (Empresa) UH.buscarNick(nickname_e);
		return e.listarOfertasLaboralesIngresadas();
	}
	
	public void rechazoOL(String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		ol.setEstado(EstadoOL.Rechazada);		
	}
	
	public void aceptoOL(String nombre_oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre_oferta);
		ol.setEstado(EstadoOL.Confirmada);
	}
	
	public HashSet<String> listarPostulantes(){
		CtrlUsuario CU = new CtrlUsuario();
		return CU.obtenerNicknamesPostulantes();
	}
	
	public DTOfertaExtendido obtenerOfertaLaboral(String nombre) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre);
		return ol.obtenerDatosOferta();
	}
	
	public void agregarTipoOfertaPaq(String paq, String TO, int cantidad) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete p = PH.buscar(paq);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TipoOferta tipoO = TOH.buscar(TO);
		p.crearOfertaPaquete(tipoO, cantidad);
	}
	
	public HashSet<String> listarPaquetes(){
		HashSet<String> res = new HashSet<>();
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Map<String, Paquete> paquetes = PH.obtener();
		
		for (Map.Entry<String, Paquete> entry : paquetes.entrySet()) {
			Paquete p = entry.getValue();
			res.add(p.getNombre());
		}
		
		return res;
	}
	
	public DTPaquete obtenerDatosPaquete(String paq) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete p = PH.buscar(paq);
		return p.getDTPaquete();
	}
	
	public DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente {
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		boolean b = TOH.existe(nombre);
		if(b) {
			TipoOferta tipoOfer = TOH.buscar(nombre);
			DTTipoOferta res = tipoOfer.obtenerDT();
			return res;
		}
		else {
			throw new ExcepcionTipoOfertaNoExistente("El tipo de publicación de oferta laboral indicado no existe.");
		}
		
	}
	
	
	
	// REVISAR EXCEPCIONES, NICK Y NOMBRE !!!!!
	// nombre es el nombre de la OfertaLaboral y nick el nickname del Usuario.
	
	public HashSet<String> listarKeywords(){
		HashSet<String> res = new HashSet<>();
		KeywordHandler KH = KeywordHandler.getInstance();
		HashMap<String,Keyword> keys = KH.obtener();
		for (Map.Entry<String, Keyword> entry : keys.entrySet()) {
			res.add(entry.getKey());
		}
		return res;
	}
	
	public DTTipoOferta tipoOferta(String oferta) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(oferta);
		TipoOferta tipoOferta = ol.getTipoOferta();
		DTTipoOferta res = tipoOferta.obtenerDT();
		return res;
	}
	
	
	public boolean paqueteComprado(String pack) {
		
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete paquet = PH.buscar(pack);

		if(paquet.getInfoCompra()==null) {return false; //nadie lo compro 
		} else {return true;
		//ya fue comprado
			}
	}
	
	
		
}
