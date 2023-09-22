package model.Interfaces;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import excepciones.ExcepcionTipoOfertaNoExistente;
import model.Datatypes.DTOfertaExtendido;
import model.Datatypes.DTPaquete;
import model.Datatypes.DTTipoOferta;

public interface ICtrlOferta {
		public abstract HashSet<String> listarTipoDePublicaciones();
		public abstract boolean existeOferta(String nombre_oferta);
		public abstract boolean altaTipoPublicacionOL(String nomb, String descripcion, int expo, int dur, float costo, LocalDate fechA);
		public abstract boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento);
		public abstract boolean altaKeyword(String key);
		public abstract DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente;
		public abstract DTPaquete obtenerDatosPaquete(String paq);
		public abstract HashSet<String> listarPaquetes();
		public abstract void agregarTipoOfertaPaq(String paq, String TO, int cantidad);
		public abstract DTOfertaExtendido obtenerOfertaLaboral(String nombre);
		public abstract boolean altaPostulacion(String nombre, String nick, String cv, String motivacion, String URLDocE, LocalDate fecha);
		public abstract HashSet<String> listarPostulantes();
		public abstract HashSet<String> listarKeywords();
		public abstract Set<String> listarEmpresas();
		public abstract DTTipoOferta tipoOferta(String oferta);
}