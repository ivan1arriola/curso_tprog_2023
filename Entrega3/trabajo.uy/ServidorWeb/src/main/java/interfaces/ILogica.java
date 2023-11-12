package interfaces;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import logica.servidor.*;


public interface ILogica {

	void cargarDatos();

	/**
	 * Valida que las credenciales coincidan con un usuario en el sistema
	 **/
	boolean validarCredenciales(String identificador,  String contraseña) throws ExceptionUsuarioNoEncontrado_Exception;

	/**
	 * Devuelve los datos basicos del usuario. En caso de error devuelve un bean vacio con un mensaje de error
	 **/
	UsuarioBean obtenerDatosUsuario(String nickname);


	boolean estaFinalizada(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception;

	boolean estaFinalizadaConOrdenPostulantes(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception;

	void modificarDatosUsuario(String nickname,  UsuarioBean usuario);

	Set<UsuarioBean> listarUsuarios();

	Set<String> listarNicknamesUsuario();

	void compraPaquetes(String nickname,  String paquete,  LocalDate now,  int valor) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception,  ExceptionCompraPaqueteConValorNegativo_Exception,  ExceptionUsuarioNoEncontrado_Exception,  ExceptionValidezNegativa_Exception,  NoExistePaquete_Exception;


	Set<String> listarKeywords();

	/**
	 * Lista los nombres de los paquetes comprados por la empresa nickname
	 **/
	Set<String> listarPaquetesDeEmpresa(String nickname) throws ExceptionUsuarioNoEncontrado_Exception;

	Set<String> listarTipoDePublicaciones();


	/**
	 * Obtiene el PaqueteBean del paquete paquete
	 **/
	PaqueteBean obtenerDatosPaquete(String paquete) throws NoExistePaquete_Exception;

	/**
	 * Lista las ofertas confirmadas de la empresa nicknameParametro
	 **/
	Set<String> listarOfertasConfirmadasDeEmpresa(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception;

	/**
	 * Obtiene el OfertaLaboralBean de la oferta laboral nombre
	 **/
	OfertaLaboralBean obtenerDatosOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada_Exception;

	/**
	 * Lista todas las ofertas laborales de la empresa nicknameParametro
	 **/
	Set<String> listarOfertasLaboralesDeEmpresa(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception;

	/**
	 * Lista todas las ofertas laborales a las cual postulante se postuló
	 **/
	Set<String> listarPostulacionesDePostulante(String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception;

	/**
	 * Obtiene los datos de la postulacion de nicknameParametro a la oferta nombreOferta
	 **/
	PostulacionBean obtenerDatosPostulacion(String nombreOferta,  String nicknameParametro) throws ExceptionUsuarioNoEncontrado_Exception,  TipoUsuarioNoValido_Exception;

	/**
	 * A un OfertaLaboralBean ya existente le carga los datos de los Postulantes a la oferta y
	 * de el paquete utilizado para pagar la Oferta Laboral en caso de existir
	 **/
	//OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean,  String nombreOferta,  String empresaNickname);


	List<UsuarioBean> obtenerPostulantesDeOferta(String nombreOferta,  String empresaNIckname) throws OfertaLaboralNoEncontrada_Exception,  ExceptionUsuarioNoEncontrado_Exception;


	List<String> obtenerPostulantesDeOfertaString(String nombreOferta,  String empresaNickname) throws OfertaLaboralNoEncontrada_Exception,  ExceptionUsuarioNoEncontrado_Exception;
	PaqueteBean obtenerPaqueteDeOferta(String nombreOferta,  String empresaNickname) throws Exception;

	OfertaLaboralBean cargarDatosDePostulante(OfertaLaboralBean ofertaBean,  String postulanteNickname) throws Exception;

	/**
	 * Lista las ofertas laborales confirmadas del sistema
	 **/
	Set<OfertaLaboralBean> listarDatosOfertas() throws OfertaLaboralNoEncontrada_Exception;

	/**
	 * Lista las ofertas laborales confirmadas del sistema que tengan la keyword
	 **/
	Set<OfertaLaboralBean> buscarOfertasPorKeyword(String keyword) throws OfertaLaboralNoEncontrada_Exception;

	/**
	 * Lista las ofertas laborales confirmadas del sistema que tengan en el nombre el String consulta
	 **/
	Set<OfertaLaboralBean> buscarOfertasPorInput(String consulta) throws OfertaLaboralNoEncontrada_Exception,  ExceptionUsuarioNoEncontrado_Exception;

	/**
	 * Lista los datos de los paquetes
	 **/
	Set<PaqueteBean> obtenerPaquetes() throws NoExistePaquete_Exception;

	OfertaLaboralBean DatosOferta(String nombre_oferta) throws OfertaLaboralNoEncontrada_Exception;


	Set<DtTipoOferta> obtenerTipoOfertas();

	DtTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente_Exception;

	void altaEmpresa(String nickname,  String password,  String nombre,  String apellido,  String email,  String descripcionEmpresa,  String sitioWebEmpresa,  byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception,  ExceptionUsuarioCorreoRepetido_Exception,  ExceptionUsuarioNickYCorreoRepetidos_Exception,  ErrorAgregarUsuario_Exception;

	void altaPostulante(String nickname,  String password,  String nombre,  String apellido,  String email,  LocalDate parse,  String nacionalidad,  byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception,  ExceptionUsuarioCorreoRepetido_Exception,  ExceptionUsuarioNickYCorreoRepetidos_Exception,  ExceptionFechaInvalida_Exception,  ErrorAgregarUsuario_Exception;

	void modificarPostulante(String nickname,  String nombre,  String apellido,  String correo,  String password,  byte[] imagen,  LocalDate fecha,  String nacionalidad) throws ExceptionUsuarioNoEncontrado_Exception;

	void modificarEmpresa(String nickname,  String nombre,  String apellido,  String correo,  String password,  byte[] imagen,  String descripcion,  String enlace) throws ExceptionUsuarioNoEncontrado_Exception;

	PostulacionBean obtenerDatosPostulacionW(String nickname,  String nombreOferta) throws ExceptionUsuarioNoEncontrado_Exception,  TipoUsuarioNoValido_Exception;

	void altaPostulacion(String nombreOferta,  String nickname,  String curriculumAbreviado,  String motivacion,  String url,  LocalDate fecha,  String video) throws ExceptionUsuarioNoEncontrado_Exception,  OfertaLaboralNoEncontrada_Exception,  ExceptionFechaInvalida_Exception;


    boolean nicknameDisponible(String nickname);

	boolean emailDisponible (String email);

	HashSet<String> obtenerSeguidores(String nickname) throws ExceptionUsuarioNoEncontrado_Exception;

	HashSet<String> obtenerSeguidos(String nickname) throws ExceptionUsuarioNoEncontrado_Exception;

    void finalizarOferta(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception,  FinalizarOfertaNoVencida_Exception,  FinalizarOfertaYaFinalizada_Exception;

	boolean estaVigenteOferta(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception;
}