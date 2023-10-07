package main.java.logica.Interfaces;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTOfertaExtendido;
import main.java.logica.Datatypes.DTOfertaExtendidoConKeywordsPostulante;
import main.java.logica.Datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.Datatypes.DTOfertaLaboral;
import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTPostulacion;
import main.java.logica.Datatypes.DTTipoOferta;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;

public interface ICtrlOferta {
	    public abstract Set<String> listarEmpresas();
		public abstract HashSet<String> listarTipoDePublicaciones();
		public abstract boolean existeOferta(String nombre_oferta);
		public abstract boolean altaTipoPublicacionOL(String nomb, String descripcion, int expo, int dur, float costo, LocalDate fechA);
		public abstract boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento, byte[] img);
		public abstract boolean altaKeyword(String key);
		public abstract boolean compraPaquetes(String nickname_e, String paq);
		public abstract boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, HashSet<String> keys, EstadoOL estado, byte[] img, String paquete);
		public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante, String nombre_oferta);
		public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa, String nombre_oferta);
		public abstract boolean altaPostulacion(String nombre, String nick, String cv, String motivacion, String URLDocE, LocalDate fecha);
		public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta);
		public abstract HashSet<String> listarOfertasLaboralesKeywords(String ks);
		public abstract boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion);
		public abstract DTPostulacion obtenerDatosPostulacionW(String nick, String ofer);
		public abstract HashSet<String> listarOfertasLaboralesConfirmadas(String nickname_e);
		public abstract HashSet<String> listarOfertasLaboralesIngresadas(String nickname_e);
		public abstract void rechazoOL(String nombre_oferta);
		public abstract void aceptoOL(String nombre_oferta);
		public abstract HashSet<String> listarPostulantes();
		public abstract DTOfertaExtendido obtenerOfertaLaboral(String nombre);
		public abstract void agregarTipoOfertaPaq(String paq, String TO, int cantidad);
		public abstract HashSet<String> listarPaquetes();
		public abstract DTPaquete obtenerDatosPaquete(String paq);
		public abstract DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente;
		public abstract HashSet<DTOfertaExtendido> listarOfertasLaboralesConfirmadas();
		
		// ESTÁN PERO NO EN EL DCD
		public abstract HashSet<String> listarKeywords();
		public abstract DTTipoOferta tipoOferta(String oferta);
		
		
		public abstract boolean paqueteComprado(String pack);

		//Necesaria para el caso de uso ""
}