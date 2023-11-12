package main.java.logica.Controladores;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

import main.java.logica.Clases.OfertaLaboral;
import main.java.logica.Clases.Postulacion;
import main.java.logica.Clases.Paquete;
import main.java.logica.Clases.TipoOferta;
import main.java.logica.Clases.Keyword;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTTipoOferta;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Manejadores.KeywordHandler;
import main.java.logica.Manejadores.OfertaLaboralHandler;
import main.java.logica.Manejadores.PaqueteHandler;
import main.java.logica.Manejadores.TipoOfertaHandler;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;

public class CtrlOferta implements ICtrlOferta{

	public CtrlOferta() {};
	
	public Set<String> listarEmpresas(){
		CtrlUsuario CU = new CtrlUsuario();
		return CU.listarEmpresas();
	}
	
	public HashSet<String> listarTipoDePublicaciones(){
		HashSet<String> res = new HashSet<>(); // PQ NO ME DEJA?
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		HashMap<String,TipoOferta> tipoOf = TOH.obtener();
		for (Entry<String, TipoOferta> entry : tipoOf.entrySet()) {
			res.add((entry.getValue().getNombre()));
        }

		return res;
	}
	
	public boolean existeOferta(String nombre_oferta){
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		return OLH.existe(nombre_oferta);
	}
	
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

	public boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento) {
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
			Paquete p = new Paquete(nombre, descripcion, validez, fechaA, descuento);
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
	
	public DTPaquete obtenerDatosPaquete(String paq) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete p = PH.buscar(paq);
		return p.getDTPaquete();
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

	
	public void agregarTipoOfertaPaq(String paq, String TO, int cantidad) {
		PaqueteHandler PH = PaqueteHandler.getInstance();
		Paquete p = PH.buscar(paq);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TipoOferta tipoO = TOH.buscar(TO);
		p.crearOfertaPaquete(tipoO, cantidad);
	}
	
	public DTOfertaExtendido obtenerOfertaLaboral(String nombre) {
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OfertaLaboral ol = OLH.buscar(nombre);
		return ol.obtenerDatosOferta();
	}
	
	// REVISAR EXCEPCIONES, NICK Y NOMBRE !!!!!
	// nombre es el nombre de la OfertaLaboral y nick el nickname del Usuario.
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
	
	public HashSet<String> listarPostulantes(){
		CtrlUsuario CU = new CtrlUsuario();
		return CU.obtenerNicknamesPostulantes();
	}
	
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
}
