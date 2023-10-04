package main.java.logica.Interfaces;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.Clases.OfertaLaboral;
import main.java.logica.Clases.Postulacion;
import main.java.logica.Datatypes.*;
import main.java.logica.Enumerados.DepUY;


/**
 * @author TProg2017
 *
 */
public interface ICtrlUsuario {
    
    public abstract boolean altaEmpresaURL(String nick, String nombre, String apellido, String mail, String nombreE, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido;

    public abstract boolean altaEmpresa(String nick, String nombre, String apellido, String mail, String nombreE, String desc) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido;
    
    public abstract boolean altaPostulante(String nick, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido;
    
    public abstract HashSet<String>listarEmpresas();
    
    public abstract DTOfertaExtendido consultaOfertaLaboral(String nombre);
    
    public abstract DTUsuario obtenerDatosUsuario(String nick);
    
    public abstract HashSet<String> listarNicknamesUsuarios();
    
    public abstract boolean existePostulacion(String nickname, String nombre);
    
    public abstract Postulacion crearPostulacion(String nick, String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab);
    
    public abstract HashSet<String> obtenerNicknamesPostulantes();
    
    // public abstract void ingresarDatosEditados(String nickname, String nombre, String apellido); // NO EXISTE
    
    
    // altaOfertaLaboral tendria q estar en ICtrlOfertaLaboral
    public abstract boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA,List<String> keys) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida;
    
    public abstract Set<String> listarOfertasLaborales(String nickname_e) throws ExceptionEmpresaInvalida, ExceptionUsuarioNoEncontrado;
    
    
    
    // NUEVAS OPERACIONES
    
    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname, String nick);
    
    public abstract DTUsuario obtenerDatosUsuarioVisitantes(String nick);
    
    public abstract void cerrarSesion(String nickname);
    
    public abstract HashSet<DTOfertaExtendidoSinPConK> infoOfertaLaboralVisitante(String nombre_oferta);
    
    public abstract DTPostulacion obtenerDatosPostulacionW(String postulante_nick, String ofer);
    
    public abstract DTPaquete obtenerDatosPaquete(String paq);
    
    public abstract boolean  iniciarSesionCorreo(String email, String contrasenia);
    
    public abstract boolean iniciarSesionNickname(String nickname, String contrasenia);
    
    public abstract void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, LocalDate fecha_nac, String nacionalidad);
    
    public abstract void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, LocalDate fecha_nac, String nacionalidad);
    
    public abstract void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, String descripcion);
    
    public abstract void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, String descripcion);
    
    public abstract void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, String descripcion);
    
    public abstract void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String contraseña, byte[] imagen, String descripcion);

    public abstract boolean tieneURL(String nickname); 
    
    public abstract boolean hayPostulacionW(String postulante_nick, String ofer);
    
    public abstract void altaEmpresaURLyImagen(String nick, String nombre, String ap, String mail, String contraseña, String desc, String URL, byte[] imagen);
     
    public abstract void altaPostulanteImagen(String nick, String nombre, String apellido, LocalDate fecha_nac, String mail, String contraseña, String nacionalidad, byte[] imagen);
    
    public abstract void altaEmpresaImagen(String nick, String nombre, String ap, String mail, String contraseña, String desc, byte[] imagen);
    
    public abstract HashSet<String> listarPostulantesDeOfertas(String nickname_e, String oferta);
    
    public abstract HashSet<String> listarOfertasLaboralesConfirmadas();
    
    public abstract boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion);
    
}
