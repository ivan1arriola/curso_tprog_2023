package main.java.logica.interfaces;

import java.time.LocalDate;
import java.util.Set;
import java.util.Set;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTTipoOferta;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public interface ICtrlOferta {
	    public abstract Set<String> listarEmpresas();
	    
		public abstract Set<String> listarTipoDePublicaciones();
		
		public abstract boolean existeOferta(String nombre_oferta);
		
		public abstract boolean altaTipoPublicacionOL(String nomb,  String descripcion,  int expo,  int dur,  float costo,  LocalDate fechA);
		
		public abstract boolean altaPaqueteOL(String nombre,  String descripcion,  int validez,  LocalDate fechaA,  float descuento,  byte[] img);
		
		public abstract boolean altaKeyword(String key);
		
		public abstract boolean compraPaquetes(String nickname_e,  String paq);
		
		public abstract boolean 
		altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  
				String descripcion,  DTHorario horario,  float remun,  String ciu,  
				DepUY dep,  LocalDate fechaA,  Set<String> keys,  
				EstadoOL estado,  byte[] img,  String paquete);
		
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
		
		public abstract Set<String> listarOfertasLaboralesIngresadas(String nickname_e);
		
		public abstract void rechazoOL(String nombre_oferta);
		
		public abstract void aceptoOL(String nombre_oferta);
		
		public abstract Set<String> listarPostulantes();
		
		public abstract DTOfertaExtendido obtenerOfertaLaboral(String nombre);
		
		public abstract void 
		agregarTipoOfertaPaq(String paquete,  String TipoOferta,  int cantidad);
		
		public abstract Set<String> listarPaquetes();
		
		public abstract DTPaquete obtenerDatosPaquete(String paq);
		
		public abstract DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente;
		
		
		
		// ESTÁN PERO NO EN EL DCD
		public abstract Set<String> listarKeywords();
		
		public abstract DTTipoOferta tipoOferta(String oferta);
		
		public abstract boolean paqueteComprado(String pack);

		//Necesaria para el caso de uso ""
}