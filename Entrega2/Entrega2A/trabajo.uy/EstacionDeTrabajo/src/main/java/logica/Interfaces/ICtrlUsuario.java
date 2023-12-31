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
    
    public abstract void ingresarDatosEditados(String nickname, String nombre, String apellido);
    
    public abstract boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA,List<String> keys) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida;
    
    public abstract Set<String> listarOfertasLaborales(String nickname_e) throws ExceptionEmpresaInvalida, ExceptionUsuarioNoEncontrado;
}
