package logica.interfaces;

import excepciones.AsignarOrdenAOfertaFinalizada;
import excepciones.AsignarOrdenAOfertaNoVencida;
//import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
//import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
//import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
//import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionEmpresaInvalida;
//import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
//import excepciones.ExceptionPaqueteNoVigente;
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
import excepciones.NoExistePaquete;
import excepciones.NoHayOrdenDefinidoDePostulantes;
import excepciones.OfertaLaboralNoEncontrada;
//import excepciones.PostulaExistenteException;
import excepciones.TipoUsuarioNoValido;
//import excepciones.UsuarioNoExisteException;
//import jakarta.persistence.EntityManager;

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
//import logica.datatypes.DTUsuario;
//import logica.datatypes.DTUsuarioSinInfoSocial;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ICtrlOferta {
    public abstract Set<String> listarEmpresas();

    public abstract Set<String> listarTipoDePublicaciones();

    public abstract boolean existeOferta(String nombre_oferta);

    public abstract boolean altaTipoPublicacionOL(String nomb, String descripcion, int expo, int dur, float costo, LocalDate fechA);

    public abstract boolean altaPaqueteOL(String nombre, String descripcion, int validez, LocalDate fechaA, float descuento, byte[] img) throws ExceptionValidezNegativa, ExceptionDescuentoInvalido;

    public abstract boolean altaKeyword(String key) throws ExcepcionKeywordVacia;

    public abstract boolean compraPaquetes(String nickname_e, String paq, LocalDate fecha, int valor) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa, ExceptionUsuarioNoEncontrado, NoExistePaquete;

    public abstract boolean
    altaOfertaLaboral(String nickname_e, String tipo, String nombre,
                      String descripcion, DTHorario horario, float remun, String ciu,
                      DepUY dep, LocalDate fechaA, Set<String> keys,
                      EstadoOL estado, byte[] img, String paquete) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionUsuarioNoEncontrado, NoExistePaquete;

    public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralPostulante(String nombre_postulante, String nombre_oferta) throws OfertaLaboralNoEncontrada;

    public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralEmpresa(String nombre_empresa, String nombre_oferta) throws OfertaLaboralNoEncontrada, ExceptionUsuarioNoEncontrado;

    public abstract boolean altaPostulacion(String nombre, String nick, String curriculumVitae, String motivacion, String URLDocE, LocalDate fecha, String video) throws OfertaLaboralNoEncontrada, ExceptionUsuarioNoEncontrado, ExceptionFechaInvalida;

    public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) throws OfertaLaboralNoEncontrada;

    public abstract Set<String> listarOfertasLaboralesKeywords(String keywords);


    public abstract boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) throws ExceptionUsuarioNoEncontrado;

    public abstract DTPostulacion obtenerDatosPostulacionW(String nick, String ofer) throws ExceptionUsuarioNoEncontrado, TipoUsuarioNoValido;

    public abstract Set<String>
    listarOfertasLaboralesConfirmadas(String nickname_e) throws ExceptionUsuarioNoEncontrado;

    public abstract Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadas();

    public abstract Set<DTOfertaExtendido> listarOfertasLaboralesConfirmadasYNoVencidas();

    public abstract Set<String> listarOfertasLaboralesIngresadas(String nickname_e) throws ExceptionUsuarioNoEncontrado;

    public abstract Set<String> listarTodasLasOfertasLaborales(String nickname_e) throws ExceptionUsuarioNoEncontrado;


    public abstract void rechazoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada;

    public abstract void aceptoOL(String nombre_oferta) throws OfertaLaboralNoEncontrada;

    public abstract Set<String> listarPostulantes();

    public abstract DTOfertaExtendido obtenerOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada;

    public abstract void
    agregarTipoOfertaPaq(String paquete, String TipoOferta, int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete, NoExistePaquete;

    public abstract Set<String> listarPaquetes();

    public abstract Set<String> listarPaquetesNoVencidos(String nickname_e) throws ExceptionEmpresaInvalida, ExceptionUsuarioNoEncontrado;

    public abstract DTPaquete obtenerDatosPaquete(String paq) throws NoExistePaquete;

    public abstract DTTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente;


    // ESTÁN PERO NO EN EL DCD
    public abstract Set<String> listarKeywords();

    public abstract DTTipoOferta tipoOferta(String oferta) throws OfertaLaboralNoEncontrada;

    public abstract boolean paqueteComprado(String pack) throws NoExistePaquete;

    //Necesaria para el caso de uso ""


    // Operacion que para un nickname de empresa devuelve un set de String con los nombres de los paquetes comprados
    public abstract Set<String> listarComprasPaquete(String nicknameEmpresa) throws ExceptionUsuarioNoEncontrado;

    Set<String> listarOfertasLaboralesConfirmadasYNoVencidasString();

    public abstract boolean existeOfertaLaboral(String nombre_ofer);

    public abstract void marcarFavorita(String nick_postulante, String nomb_oferta) throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada;
    
    public abstract void desmarcarFavorita(String nick_postulante, String nomb_oferta) throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada;




	public abstract void establecerPosiciones(String nombre_oferta, List<String> nickPostulante)
            throws ExceptionUsuarioNoEncontrado, OfertaLaboralNoEncontrada, AsignarOrdenAOfertaFinalizada, AsignarOrdenAOfertaNoVencida;

	public abstract List<String> devolverOrdenPostulantes(String nombre_oferta) throws OfertaLaboralNoEncontrada, NoHayOrdenDefinidoDePostulantes;


    public abstract void descartarOrdenPostulantes(String nombreOferta) throws OfertaLaboralNoEncontrada;
	public abstract void finalizarOfertaLaboral(String nombre_oferta) throws OfertaLaboralNoEncontrada, FinalizarOfertaNoVencida;

	public abstract void aumentarVisita(String nombre_oferta) throws OfertaLaboralNoEncontrada;
	
	public abstract String obtenerTipoPubOfertaLaboral(String nomb_oferta) throws OfertaLaboralNoEncontrada;

    boolean hayOrdenDefinido(String nombreOferta) throws OfertaLaboralNoEncontrada;
}