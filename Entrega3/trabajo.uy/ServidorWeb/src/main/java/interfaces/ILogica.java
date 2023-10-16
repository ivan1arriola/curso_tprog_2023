package interfaces;

import java.time.LocalDate;
import java.util.Set;

import javabeans.PaqueteBean;
import javabeans.UsuarioBean;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public interface ILogica {
	
	void cargarDatos();
	
	//Iniciar sesion
	boolean validarCredenciales(String identificador, String contraseña);
	
	//Usuario
	UsuarioBean obtenerDatosUsuario(String nickname);
	void modificarDatosUsuario(String nickname, UsuarioBean usuario);
	Set<UsuarioBean> listarUsuarios();
	Set<String> listarNicknamesUsuario();
	
	
	
	
	void altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  
			String descripcion,  DTHorario horario,  float remun,  String ciu,  
			DepUY dep,  LocalDate fechaA,  Set<String> keys,  
			EstadoOL estado,  String img,  String paquete);
	
	void compraPaquetes(String nickname, String paquete, LocalDate now, int valor);
	
	
	
	Set<String> listarKeywords();
	
	/* Lista los nombres de los paquetes comprados por la empresa nickname */
	Set<String> listarPaquetesDeEmpresa(String nickname);
	
	Set<String> listarTipoDePublicaciones();
	
    void altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido;
    void altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido;

    PaqueteBean obtenerDatosPaquete(String paquete);

	
    
    


}
