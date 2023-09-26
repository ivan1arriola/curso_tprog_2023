package main.java.logica.Interfaces;

import java.time.LocalDate;
import java.util.Set;

import main.java.logica.Datatypes.DTOfertaExtendidoConKeywords;
import main.java.logica.Datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTPostulacion;
import main.java.logica.Datatypes.DTUsuario;

public interface ICrtlUsuarioWeb {
	
	// Iniciar Sesión
	boolean validarCredenciales(String identificador, String contrasenia);
	
	// Alta de Usuario
	boolean altaEmpresaURLyImagen(
		String nick,
		String nombre,
		String ap,
		String mail,
		String contraseña,
		String desc,
		String URL,
		byte[] imagen
	);

	boolean altaEmpresaImagen(
		String nick,
		String nombre,
		String ap,
		String mail,
		String contraseña,
		String desc,
		byte[] imagen
	);

	boolean altaEmpresaURL(
		String nick,
		String nombre,
		String ap,
		String mail,
		String contraseña,
		String desc,
		String URL
	);

	boolean altaEmpresa(
		String nick,
		String nombre,
		String ap,
		String mail,
		String contraseña,
		String desc
	);

	boolean altaPostulanteImagen(
		String nick,
		String nombre,
		String apellido,
		LocalDate fecha_nac,
		String mail,
		String contraseña,
		String nacionalidad,
		byte[] imagen
	);

	boolean altaPostulante(
		String nick,
		String nombre,
		String apellido,
		LocalDate fecha_nac,
		String mail,
		String contraseña,
		String nacionalidad
	);
	
	// Consulta de Usuario
	Set<String> listarNicknamesUsuarios();
	DTUsuario obtenerDatosUsuarioVisitantes(String nick);
	DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname, String nick);
	Set<DTOfertaExtendidoSinPConK> infoOfertaLaboralVisitante(String nombre_oferta);
	Set<DTOfertaExtendidoConKeywords> infoOfertaLaboralVisitanteConKeys(String nombre_oferta);
	DTPaquete obtenerDatosPaquete(String paq);
	DTPostulacion obtenerDatosPostulacionW(String postulante_nick, String ofer);
	
	//Modificar Datos de Usuario
		void ingresarDatosEditadosEmpresaImg(
			String nickname,
			String nombre,
			String apellido,
			String contraseña,
			byte[] imagen,
			String descripcion
		);
		
		void ingresarDatosEditadosEmpresaURLImg(
			String nickname,
			String nombre,
			String apellido,
			String contraseña,
			byte[] imagen,
			String descripcion,
			String URL
		);
		
		void ingresarDatosEditadosEmpresa(
			String nickname,
			String nombre,
			String apellido,
			String contraseña,
			String descripcion
		);
		
		void ingresarDatosEditadosEmpresaURL(
			String nickname,
			String nombre,
			String apellido,
			String contraseña,
			String descripcion,
			String URL
		);
		
		boolean tieneURL(String nickname);
	
	
	
}

