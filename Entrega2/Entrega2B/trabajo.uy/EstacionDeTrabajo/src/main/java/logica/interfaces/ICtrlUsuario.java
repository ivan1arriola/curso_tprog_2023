package main.java.logica.interfaces;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;

import main.java.excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import main.java.excepciones.ExceptionCostoPaqueteNoNegativo;
import main.java.excepciones.ExceptionDescuentoInvalido;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionPaqueteNoVigente;
import main.java.excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.clases.OfertaLaboral;
import main.java.logica.clases.Postulacion;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;



public interface ICtrlUsuario {
    
    public abstract boolean 
    altaEmpresaURL(String nick,  String contraseña,  String nombre, 
    		String apellido,  String mail,  String desc,  String URL)
    throws ExceptionUsuarioCorreoRepetido,  
    ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido;

    public abstract boolean altaEmpresa(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc) throws ExceptionUsuarioCorreoRepetido,  ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido;
    
    public abstract boolean altaPostulante(String nick,  String contraseña,  String nombre, 
    		String apellido,  String mail,  LocalDate fechanac,  String nacionalidad) 
    	    throws 
    	    ExceptionUsuarioNickYCorreoRepetidos,  ExceptionUsuarioNickRepetido,  ExceptionUsuarioCorreoRepetido;
    
    public abstract Set<String> listarEmpresas();
    
    public abstract DTOfertaExtendido consultaOfertaLaboral(String nombre);
    
    public abstract DTUsuario obtenerDatosUsuario(String nick);
    
    public abstract Set<String> listarNicknamesUsuarios();
    
    public abstract boolean existePostulacion(String nickname,  String nombre);
    
    public abstract Postulacion crearPostulacion(String nick,  String curriculumVitae,  String motivacion, 
    		LocalDate fecha,  String URLDocExtras,  OfertaLaboral OferLab);
    
    public abstract Set<String> obtenerNicknamesPostulantes();
    
    // public abstract void ingresarDatosEditados(String nickname,  String nombre,  String apellido); // NO EXISTE
    
    
    // altaOfertaLaboral tendria q estar en ICtrlOfertaLaboral
    public abstract boolean altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  
    		String descripcion,  DTHorario horario,  float remun,  String ciu,  DepUY dep,  
    		LocalDate FechaA,  List<String> keys,  EstadoOL estado,  byte[] img,  String paquete) 
    				throws ExceptionUsuarioNoEncontrado,   ExceptionEmpresaInvalida, ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
    
    public abstract Set<String> listarOfertasLaborales(String nickname_e) 
    		throws ExceptionEmpresaInvalida,  ExceptionUsuarioNoEncontrado;
    
    
    
    // -------------------------------------------------------------------------------------
    // ################################  NUEVAS OPERACIONES ################################ 
    // -------------------------------------------------------------------------------------
    
    public abstract Set<String> listarKeywords(String nombre_oferta);
    
    public abstract 
    DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname,  String nick); // PRONTA
    
    public abstract DTUsuario obtenerDatosUsuarioVisitantes(String nick); // PRONTA
    
    // public abstract void cerrarSesion(String nickname); // PRONTA
    
    public abstract 
    DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta); // PRONTA
    
    public abstract 
    DTPostulacion obtenerDatosPostulacionW(String postulante_nick,  String ofer); // PRONTA
    
    public abstract 
    DTPaquete obtenerDatosPaquete(String paq); // PRONTA
    

    public abstract boolean validarCredenciales(String identificador,  String contraseña); // NUEVA OPERACION que reemplaza las 2 anteriores
    
    public abstract void ingresarDatosEditadosPostulanteImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String imagen,  LocalDate fechanac,  String nacionalidad); // PRONTA
    
    public abstract void ingresarDatosEditadosPostulante(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  LocalDate fechanac,  String nacionalidad); // PRONTA
    
    public abstract void ingresarDatosEditadosEmpresaURL(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String URL,  String descripcion); // PRONTA
    
    public abstract void ingresarDatosEditadosEmpresa(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String descripcion); // PRONTA
    
    public abstract void ingresarDatosEditadosEmpresaURLImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String URL,  String imagen,  String descripcion); // PRONTA
    
    public abstract void ingresarDatosEditadosEmpresaImg(String nickname,  String nombre,  String apellido,  String correo,  String contraseña,  String imagen,  String descripcion); // PRONTA
 
    public abstract boolean tieneURL(String nickname);  // PRONTA 
    
    public abstract boolean hayPostulacionW(String postulante_nick,  String ofer); // PRONTA
    
    public abstract boolean altaEmpresaURLyImagen(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc,  String URL,  String imagen); // PRONTA
     
    public abstract boolean altaPostulanteImagen(String nick,  String contraseña,  String nombre,  String apellido,  LocalDate fechanac,  String mail,  String nacionalidad,  String imagen); // PRONTA
     
    public abstract boolean altaEmpresaImagen(String nick,  String contraseña,  String nombre,  String apellido,  String mail,  String desc,  String imagen); // PRONTA
     
    public abstract Set<String> listarPostulantesDeOfertas(String nickname_e,  String oferta); 
    
    public abstract Set<String> listarOfertasLaboralesConfirmadas(String nickname_e);  // PRONTA
    
    public abstract boolean modificarPostulacion(String nombre,  String nick,  String cvAbreviado,  String motivacion); // PRONTA
    
    /** devuelve una lista de todos los nombres de las ofertas laborales a la cual Postulante nickname se postuló **/
    public abstract Set<String> listarPostulacionesPostulante(String nickname);
    
    public abstract boolean existeUsuarioConNickname(String nickname);
    
    public abstract boolean existeUsuarioConEmail(String correo);
    
}
