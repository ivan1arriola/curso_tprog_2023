package logica.interfaces;

import java.time.LocalDate;
import java.util.Set;

import excepciones.ExcepcionKeywordVacia;
import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionValidezNegativa;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTOfertaExtendidoSinPConK;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulacion;
import logica.datatypes.DTTipoOferta;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

public interface ICtrlOferta {
	    public abstract Set<String> listarEmpresas();
	    
		public abstract Set<String> listarTipoDePublicaciones();
		
		public abstract boolean existeOferta(String nombre_oferta);
		
		public abstract boolean altaTipoPublicacionOL(String nomb,  String descripcion,  int expo,  int dur,  float costo,  LocalDate fechA);
		
		public abstract boolean altaPaqueteOL(String nombre,  String descripcion,  int validez,  LocalDate fechaA,  float descuento,  byte[]  img) throws ExceptionValidezNegativa, ExceptionDescuentoInvalido;
		
		public abstract boolean altaKeyword(String key) throws ExcepcionKeywordVacia;
		
		public abstract boolean compraPaquetes(String nickname_e,  String paq, LocalDate fecha, int valor) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa;
		
		public abstract boolean 
		altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  
				String descripcion,  DTHorario horario,  float remun,  String ciu,  
				DepUY dep,  LocalDate fechaA,  Set<String> keys,  
				EstadoOL estado,  byte[]  img,  String paquete) throws ExceptionRemuneracionOfertaLaboralNegativa;
		
		public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante,  String nombre_oferta);
		
		public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa,  String nombre_oferta);
		
		public abstract boolean altaPostulacion(String nombre,  String nick,  String curriculumVitae,  String motivacion,  String URLDocE,  LocalDate fecha);
		
		public abstract 
		DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta);
		
		public abstract Set<String> listarOfertasLaboralesKeywords(String keywords);
		
		
		
		public abstract boolean modificarPostulacion(String nombre,  String nick,  String cvAbreviado,  String motivacion);
		
		public abstract DTPostulacion obtenerDatosPostulacionW(String nick,  String ofer);
		
		public abstract Set<String> 
			listarOfertasLaboralesConfirmadas(String nickname_e);
		
		public abstract Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadas();
		
		public abstract Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadasYNoVencidas();
		
		public abstract Set<String> listarOfertasLaboralesIngresadas(String nickname_e);
		
		public abstract Set<String> listarTodasLasOfertasLaborales(String nickname_e);
		

		
		public abstract void rechazoOL(String nombre_oferta);
		
		public abstract void aceptoOL(String nombre_oferta);
		
		public abstract Set<String> listarPostulantes();
		
		public abstract DTOfertaExtendido obtenerOfertaLaboral(String nombre);
		
		public abstract void 
		agregarTipoOfertaPaq(String paquete,  String TipoOferta,  int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
		
		public abstract Set<String> listarPaquetes();
		
		public abstract Set<String> listarPaquetesNoVencidos(String nickname_e) throws ExceptionEmpresaInvalida;	
		
		public abstract DTPaquete obtenerDatosPaquete(String paq);
		
		public abstract DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente;
		
		
		
		// ESTÁN PERO NO EN EL DCD
		public abstract Set<String> listarKeywords();
		
		public abstract DTTipoOferta tipoOferta(String oferta);
		
		public abstract boolean paqueteComprado(String pack);

		//Necesaria para el caso de uso ""
		
		
		// Operacion que para un nickname de empresa devuelve un set de String con los nombres de los paquetes comprados
		public abstract Set<String> listarComprasPaquete(String nicknameEmpresa);
		
		public abstract boolean existeOfertaLaboral(String nombre_ofer);
}