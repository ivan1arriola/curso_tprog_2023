package interfaces;

import java.time.LocalDate;
import java.util.Set;

import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import logica.servidor.*;


public interface ILogica {
	
	void cargarDatos();
	
	/** Valida que las credenciales coincidan con un usuario en el sistema **/
	boolean validarCredenciales(String identificador, String contraseña);
		
	/** Devuelve los datos basicos del usuario. En caso de error devuelve un bean vacio con un mensaje de error**/
	UsuarioBean obtenerDatosUsuario(String nickname);
	
	
	void modificarDatosUsuario(String nickname, UsuarioBean usuario);
	Set<UsuarioBean> listarUsuarios();
	Set<String> listarNicknamesUsuario();
	
	
	
	
	void altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  
			String descripcion,  String horario,  float remun,  String ciu,
			Departamento dep,  LocalDate fechaA,  Set<String> keys,  
			EstadoOfertaLaboral estado,  String img,  String paquete);
	
	void compraPaquetes(String nickname, String paquete, LocalDate now, int valor);
	
	
	
	Set<String> listarKeywords();
	
	/** Lista los nombres de los paquetes comprados por la empresa nickname **/
	Set<String> listarPaquetesDeEmpresa(String nickname);
	
	Set<String> listarTipoDePublicaciones();
	

    /** Obtiene el PaqueteBean del paquete paquete **/
    PaqueteBean obtenerDatosPaquete(String paquete);

    /** Lista las ofertas confirmadas de la empresa nicknameParametro **/
	Set<String> listarOfertasConfirmadasDeEmpresa(String nicknameParametro);

	/** Obtiene el OfertaLaboralBean de la oferta laboral nombre **/
	OfertaLaboralBean obtenerDatosOfertaLaboral(String nombre);

	/** Lista todas las ofertas laborales de la empresa nicknameParametro **/
	Set<String> listarOfertasLaboralesDeEmpresa(String nicknameParametro);

	/** Lista todas las ofertas laborales a las cual postulante se postuló **/
	Set<String> listarPostulacionesDePostulante(String nicknameParametro);

	/** Obtiene los datos de la postulacion de nicknameParametro a la oferta nombreOferta **/
	PostulacionBean obtenerDatosPostulacion(String nombreOferta, String nicknameParametro);
	
	/** A un OfertaLaboralBean ya existente le carga los datos de los Postulantes a la oferta y 
	 * de el paquete utilizado para pagar la Oferta Laboral en caso de existir **/
	//OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean, String nombreOferta, String empresaNickname);

	
	/**
	 * Carga los datos de los Postulantes a la oferta en un OfertaLaboralBean existente.
	 *
	 * @param ofertaBean      El objeto OfertaLaboralBean al que se le cargan los datos.
	 * @param empresaNickname El nickname de la empresa dueña de la oferta laboral
	 * @throws Exception 
	 */
	OfertaLaboralBean cargarPostulantes(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception;

	/**
	 * Carga los datos del paquete utilizado para pagar la Oferta Laboral en un OfertaLaboralBean existente,
	 * en caso de existir.
	 *
	 * @param ofertaBean      El objeto OfertaLaboralBean al que se le cargan los datos.
	 * @param empresaNickname El nickname de la empresa.
	 * @throws Exception 
	 */
	OfertaLaboralBean cargarPaquete(OfertaLaboralBean ofertaBean, String empresaNickname) throws Exception;
	
	OfertaLaboralBean cargarDatosDePostulante(OfertaLaboralBean ofertaBean, String postulanteNickname) throws Exception;

	/** Lista las ofertas laborales confirmadas del sistema **/
	Set<OfertaLaboralBean> listarDatosOfertas();

	/** Lista las ofertas laborales confirmadas del sistema que tengan la keyword **/
	Set<OfertaLaboralBean> buscarOfertasPorKeyword(String keyword);
	
	/** Lista las ofertas laborales confirmadas del sistema que tengan en el nombre el String consulta **/
	Set<OfertaLaboralBean> buscarOfertasPorInput(String consulta);

	/** Lista los datos de los paquetes **/
	Set<PaqueteBean> obtenerPaquetes();

	OfertaLaboralBean DatosOferta(String nombre_oferta);


	Set<DtTipoOferta> obtenerTipoOfertas();

	DtTipoOferta obtenerDatosTO(String nombre) throws ExcepcionTipoOfertaNoExistente_Exception;

	void altaEmpresa(String nickname, String password, String nombre, String apellido, String email, String descripcionEmpresa, String sitioWebEmpresa, byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception, ExceptionUsuarioCorreoRepetido_Exception, ExceptionUsuarioNickYCorreoRepetidos_Exception;

	void altaPostulante(String nickname, String password, String nombre, String apellido, String email, LocalDate parse, String nacionalidad, byte[] imagenBytes) throws ExceptionUsuarioNickRepetido_Exception, ExceptionUsuarioCorreoRepetido_Exception, ExceptionUsuarioNickYCorreoRepetidos_Exception;

	void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String password, byte[] imagen, LocalDate fecha, String nacionalidad);

	void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String password, String link, byte[] imagen, String descripcion);

	PostulacionBean obtenerDatosPostulacionW(String nickname, String nombreOferta);
}
