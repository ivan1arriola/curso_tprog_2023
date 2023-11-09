package logica.interfaces;

import excepciones.*;
import jakarta.persistence.EntityManager;
import logica.clases.OfertaLaboral;
import logica.clases.Postulacion;
import logica.datatypes.*;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public interface ICtrlUsuario {

    public abstract boolean
    altaEmpresaURL(String nick, String contraseña, String nombre,
                   String apellido, String mail, String desc, String URL)
            throws ExceptionUsuarioCorreoRepetido,
            ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ErrorAgregarUsuario;

    public abstract boolean altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ErrorAgregarUsuario;

    public abstract boolean altaPostulante(String nick, String contraseña, String nombre,
                                           String apellido, String mail, LocalDate fechanac, String nacionalidad)
            throws
            ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido, ErrorAgregarUsuario, ExceptionFechaInvalida;

    public abstract Set<String> listarEmpresas();

    public abstract DTOfertaExtendido consultaOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada;

    public abstract DTUsuario obtenerDatosUsuario(String nick) throws ExceptionUsuarioNoEncontrado;

    public abstract Set<String> listarNicknamesUsuarios();

    public abstract boolean existePostulacion(String nickname, String nombre) throws ExceptionUsuarioNoEncontrado;

    public abstract Postulacion crearPostulacion(String nick, String curriculumVitae, String motivacion,
                                                 LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab, String video) throws ExceptionUsuarioNoEncontrado;

    public abstract Set<String> obtenerNicknamesPostulantes();

    // public abstract void ingresarDatosEditados(String nickname,  String nombre,  String apellido); // NO EXISTE


    // altaOfertaLaboral tendria q estar en ICtrlOfertaLaboral
    public abstract boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre,
                                              String descripcion, DTHorario horario, float remun, String ciu, DepUY dep,
                                              LocalDate FechaA, List<String> keys, EstadoOL estado, byte[] img, String paquete)
            throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida, ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, NoExistePaquete;

    public abstract Set<String> listarOfertasLaborales(String nickname_e)
            throws ExceptionEmpresaInvalida, ExceptionUsuarioNoEncontrado;


    // -------------------------------------------------------------------------------------
    // ################################  NUEVAS OPERACIONES ################################ 
    // -------------------------------------------------------------------------------------

    public abstract Set<String> listarKeywords(String nombre_oferta) throws OfertaLaboralNoEncontrada;

    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname, String nick) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract DTUsuario obtenerDatosUsuarioVisitantes(String nick) throws ExceptionUsuarioNoEncontrado; // PRONTA

    // public abstract void cerrarSesion(String nickname); // PRONTA

    public abstract DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) throws OfertaLaboralNoEncontrada; // PRONTA

    public abstract DTPostulacion obtenerDatosPostulacionW(String postulante_nick, String ofer) throws ExceptionUsuarioNoEncontrado, TipoUsuarioNoValido; // PRONTA

    public abstract DTPaquete obtenerDatosPaquete(String paq) throws NoExistePaquete; // PRONTA


    public abstract boolean validarCredenciales(String identificador, String contraseña) throws ExceptionUsuarioNoEncontrado; // NUEVA OPERACION que reemplaza las 2 anteriores

    public abstract void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, LocalDate fechanac, String nacionalidad) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, LocalDate fechanac, String nacionalidad) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, String descripcion) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String descripcion) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, byte[] imagen, String descripcion) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, String descripcion) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract boolean tieneURL(String nickname) throws ExceptionUsuarioNoEncontrado;  // PRONTA

    public abstract boolean hayPostulacionW(String postulante_nick, String ofer) throws ExceptionUsuarioNoEncontrado; // PRONTA

    public abstract boolean altaEmpresaURLyImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL, byte[] imagen) throws ErrorAgregarUsuario; // PRONTA

    public abstract boolean altaPostulanteImagen(String nick, String contraseña, String nombre, String apellido, LocalDate fechanac, String mail, String nacionalidad, byte[] imagen) throws ExceptionFechaInvalida, ErrorAgregarUsuario; // PRONTA

    public abstract boolean altaEmpresaImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, byte[] imagen) throws ErrorAgregarUsuario; // PRONTA

    public abstract Set<String> listarPostulantesDeOfertas(String nickname_e, String oferta);

    public abstract Set<String> listarOfertasLaboralesConfirmadas(String nickname_e) throws ExceptionUsuarioNoEncontrado;  // PRONTA

    public abstract boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) throws ExceptionUsuarioNoEncontrado; // PRONTA

    /**
     * devuelve una lista de todos los nombres de las ofertas laborales a la cual Postulante nickname se postuló
     **/
    public abstract Set<String> listarPostulacionesPostulante(String nickname) throws ExceptionUsuarioNoEncontrado;

    public abstract boolean existeUsuarioConNickname(String nickname);

    public abstract boolean existeUsuarioConEmail(String correo);

    public abstract void seguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo, ExceptionUsuarioNoEncontrado;

    public abstract void dejarDeseguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo, ExceptionUsuarioNoEncontrado;

    public abstract HashSet<String> obtenerSeguidoresUsuario(String nickname) throws ExceptionUsuarioNoEncontrado;
    
    public abstract HashSet<String> obtenerSeguidosUsuario(String nickname) throws ExceptionUsuarioNoEncontrado;

	public abstract LocalDate obtenerFechaDeCompra(String nickname_e, String paq) throws ExceptionUsuarioNoEncontrado;
    
	public abstract Set<DTOfertaExtendidoConKeywordsTit> listarOfertasLaboralesNoVigentesConfirmadas(String nickname_empresa)  throws ExceptionUsuarioNoEncontrado;
	
	public void finalizarOfertaLaboral(String nickname_empresa, String nombre_oferta) throws ExceptionUsuarioNoEncontrado;
}
