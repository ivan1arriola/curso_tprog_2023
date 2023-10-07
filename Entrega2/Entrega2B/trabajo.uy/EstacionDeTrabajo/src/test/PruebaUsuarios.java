package test.logica;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import main.java.excepciones.*;
import main.java.logica.Controladores.*;
import main.java.logica.Datatypes.*;
import main.java.logica.Enumerados.*;
import main.java.logica.Datatypes.*;
import main.java.logica.Interfaces.*;
import main.java.logica.Fabrica;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class PruebaUsuarios {
	// ------------------- testear alta postulante -------------------
	@Test
	void altaPostulanteTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		// imagen
		String str = "hello";
		byte[] img = str.getBytes();
		
		// ------------------- postulante sin imagen -------------------
		nickname = "ASwatzenegger";
		password = "contraseNaSeguraCreeme";
		nombre = "Arnold";
		apellido = "Schwarzenegger";
		correo = "Arnold@Skynet.com";
		fechaNacimiento = LocalDate.of(1947, 7, 30);
		nacionalidad = "Austriaco";
		try {
			boolean b = altaPostulante(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------- datatypes usuario para postulante -------------------
		// se obtiene con nickname, notar que estoy probando DTUsuario
		DTUsuario usu1 = obtenerDatosUsuario("ASwatzenegger");
		boolean result = 
			usu1.getNickname().equals(nickname) && 
			usu1.getNombre().equals(nombre) && 
			usu1.getApellido().equals(apellido) && 
			usu1.getCorreo_electronico().equals(correo) && 
			usu1.getImagen().equals(null) &&
			usu1.getContraseña().equals(password); 
		assertEquals("El test usu1 fallo", result, false);
		

		// ------------------- postulante con imagen -------------------
		nickname = "LeonardoVinchi";
		password = "LaContrasenaMasSeguraDelMundo";
		nombre = "Leonardo";
		apellido = "Da Vinci";
		correo = "Leo@vinchi.com";
		fechaNacimiento = LocalDate.of(1452, 4, 15);
		nacionalidad = "Italiano";
		String str = "MeEncantaPintar";
		byte[] img = str.getBytes();
		
		try {
			boolean b = altaPostulanteImagen(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad, img);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------- datatypes usuario para postulante -------------------
		// se obtiene con nickname, notar que estoy probando DTUsuario 
		DTUsuario usu2 = obtenerDatosUsuario("LeonardoVinchi");
		boolean result = 
			usu2.getNickname().equals(nickname) && 
			usu2.getNombre().equals(nombre) && 
			usu2.getApellido().equals(apellido) && 
			usu2.getCorreo_electronico().equals(correo) &&
			usu2.getImagen().equals(img) &&
			usu2.getContraseña().equals(password);
		assertEquals("El test usu2 fallo", result, false);	
	}

	// ------------------- testear keywords -------------------
	@Test
	void keywordsTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		// --------------- keywords ---------------
		// esto es colocarlo en el sistema
		boolean b = altaKeyword("Trabajo nocturno");
		boolean b = altaKeyword("horario vespertino");
		boolean b = altaKeyword("full time");
		boolean b = altaKeyword("part time");
		// esto es crear un set para la oferta
		HashSet<String> pruebaKeyword = new ArrayList<Keyword>(); 
		pruebaKeyword.add("Trabajo nocturno");
		pruebaKeyword.add("horario vespertino");
		pruebaKeyword.add("full time");
		pruebaKeyword.add("part time");
		// ----------------------------------------
		HashSet<String> probandoEnSistema = listarKeywords();
		for (String s : pruebaKeyword) {
			if (!probandoEnSistema.contains(s)) {
				assertEquals("El test keywords fallo", false, true);
			}
		}
	}

	// ------------------- testear alta empresa -------------------
	@Test
	void altaEmpresaTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		// imagen
		String str = "hello";
		byte[] img = str.getBytes();

		// imagen
		String str = "hello";
		byte[] img = str.getBytes();

		// ---------------- empresa sin url ni imagen ----------------
		nickname = "Kreves";
		password = "Pass";
		nombre = "Keanu";
		apellido = "Reeves";
		correo = "K2@gmail.com";
		descripcion = "Vendemos armas.";
		try {
			boolean b = altaEmpresa(nickname, password, nombre, apellido, correo, descripcion);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------- datatypes usuario para postulante -------------------
		// se obtiene con nickname, notar que estoy probando DTUsuario
		DTUsuario usu3 = obtenerDatosUsuario("LeonardoVinchi");
		boolean result = 
			usu3.getNickname().equals(nickname) && 
			usu3.getNombre().equals(nombre) && 
			usu3.getApellido().equals(apellido) && 
			usu3.getCorreo_electronico().equals(correo) &&
			usu3.getImagen().equals(img) &&
			usu3.getContraseña().equals(password);
		assertEquals("El test usu3 fallo", result, false);	

		// ------------------ empresa con url ------------------
		nickname = "Google";
		password = "Password";
		nombre = "Larry";
		apellido = "Page";
		correo = "Larry@hotmail.com";
		descripcion = "Vendemos informacion.";
		url = "www.google.com";
		try {
			boolean b = altaEmpresaURL(nickname, password, nombre, apellido, correo, descripcion, url);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ----------------- dataTypes empresa -----------------
		// se obtiene con nickname, notar que estoy probando DTUsuario
		DTEmpresa usu4 = obtenerDatosEmpresa("Google");
		boolean result = 
			usu4.getNickname().equals(nickname) && 
			usu4.getNombre().equals(nombre) && 
			usu4.getApellido().equals(apellido) && 
			usu4.getCorreo_electronico().equals(correo) &&
			usu4.getImagen().equals(img) &&
			usu4.getContraseña().equals(password) &&
			usu4.getDescripcion().equals(descripcion) &&
			usu4.getUrl().equals(url);
		assertEquals("El test usu4 fallo", result, false);
		// ----------------- empresa con imagen -----------------
		nickname = "Apple";
		password = "Password";
		nombre = "Steve";
		apellido = "Jobs";
		correo = "Steve@aplle.com";
		descripcion = "Vendemos telefonos.";
		try {
			boolean b = altaEmpresaImagen(nickname, password, nombre, apellido, correo, descripcion, img);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ----------------- dataTypes empresa -----------------
		// se obtiene con nickname, notar que estoy probando DTUsuario
		DTEmpresa usu5 = obtenerDatosEmpresa("Apple");
		boolean result = 
			usu5.getNickname().equals(nickname) && 
			usu5.getNombre().equals(nombre) && 
			usu5.getApellido().equals(apellido) && 
			usu5.getCorreo_electronico().equals(correo) &&
			usu5.getImagen().equals(img) &&
			usu5.getContraseña().equals(password) &&
			usu5.getDescripcion().equals(descripcion);
		assertEquals("El test usu5 fallo", result, false);

		// ----------------- empresa con url e imagen -----------------
		nickname = "Amazon";
		password = "Password";
		nombre = "Jeff";
		apellido = "Bezos";
		correo = "Bezo@porBezo.com";
		descripcion = "Vendemos libros.";
		url = "www.amazon.com";
		try {
			boolean b = altaEmpresaURLyImagen(nickname, password, nombre, apellido, correo, descripcion, url, img);
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ----------------- dataTypes empresa -----------------
		// se obtiene con nickname, notar que estoy probando DTUsuario
		DTEmpresa usu6 = obtenerDatosEmpresa("Amazon");
		boolean result = 
			usu6.getNickname().equals(nickname) && 
			usu6.getNombre().equals(nombre) && 
			usu6.getApellido().equals(apellido) && 
			usu6.getCorreo_electronico().equals(correo) &&
			usu6.getImagen().equals(img) &&
			usu6.getContraseña().equals(password) &&
			usu6.getDescripcion().equals(descripcion) &&
			usu6.getUrl().equals(url);
		assertEquals("El test usu6 fallo", result, false);
		// ------------------- ver empresa en el sistema -------------------
		// si no esta uno aborta
		HashSet<String> EmpresaSistema = listarEmpresas();
		for (String s : EmpresaSistema) {
			if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon")) {
				assertEquals("El test empresa en sistema fallo", false, true);
			}
		}
		// ------------------- ver usuarios en el sistema -------------------
		// si no esta uno aborta
		HashSet<String> UsuariosSistema = listarNicknamesUsuarios();
		for (String s : UsuariosSistema) {
			if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon") && !s.equals("ASwatzenegger") && !s.equals("LeonardoVinchi")) {
				assertEquals("El test usuarios en sistema fallo", false, true);
			}
		}	
	}

	// ------------------- testear validar credenciales -------------------
	@Test
	void validarCredencialesTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		String[] nicknames = {"Kreves", "Google", "Apple", "Amazon", "ASwatzenegger", "LeonardoVinchi"};
		String[] passwords = {"Pass", "Password", "Password", "Password", "contraseNaSeguraCreeme", "LaContrasenaMasSeguraDelMundo"};
		for (int i = 0; i < nicknames.length; i++) {
			boolean b = validarCredenciales(nicknames[i], passwords[i]);
			if (!b) {
				assertEquals("El test validar credenciales fallo", false, true);
			}
		}
	}

	// ------------------- testear editar datos y datatype postulante y empresa -------------------
	@Test
	void editarDatosTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		// ------------------- usuario en general -------------------
		nickname = "Kreves";
		nombre = "Keanu Charles";
		apellido = "Reeves";

		ingresarDatosEditados(nickname, nombre, apellido);

		DTUsuario usu1 = obtenerDatosUsuario("Kreves");
		boolean result = 
			usu1.getNickname().equals(nickname) && 
			usu1.getNombre().equals(nombre) && 
			usu1.getApellido().equals(apellido);
		assertEquals("El test usu1 fallo", result, false);}

		// ------------------- postulante sin imagen -------------------
		nickname = "ASwatzenegger";
		nombre = "Arnold Alois";
		apellido = "Schwarzenegger";
		correo = "anrny@swatzenneger.com"
		contraseña = "MehackeasTetiroSkynet";
		fechaNacimiento = LocalDate.of(1947, 7, 30);
		nacionalidad = "Austriaco";
		ingresarDatosEditadosPostulante(nickname, nombre, apellido, correo, contraseña, fechaNacimiento, nacionalidad);

		

		// ------------------- agregar imagen a postulante sin imagen -------------------
		nickname = "ASwatzenegger";
		nombre = "Arnold Alois";
		apellido = "Schwarzenegger";
		correo = "anrny@swatzenneger.com"
		contraseña = "MehackeasTetiroSkynet";
		fechaNacimiento = LocalDate.of(1947, 7, 30);
		nacionalidad = "Austriaco";
		String str = "MeEncantaMusculacion";
		byte[] img = str.getBytes();
		ingresarDatosEditadosPostulanteImg(nickname, nombre, apellido, correo, contraseña, img, fechaNacimiento, nacionalidad);

		DTUsuario usu3 = obtenerDatosUsuario("ASwatzenegger");
		boolean result = 
			usu3.getNickname().equals(nickname) && 
			usu3.getNombre().equals(nombre) && 
			usu3.getApellido().equals(apellido) && 
			usu3.getCorreo_electronico().equals(correo) &&
			usu3.getContraseña().equals(contraseña) &&
			usu3.getFecha_nacimiento().equals(fechaNacimiento) &&
			usu3.getNacionalidad().equals(nacionalidad) &&
			usu3.getImagen().equals(img);
		assertEquals("El test usu3 fallo", result, false);

		// ------------------- Postulante en general -------------------
		nickname = "LeonardoVinchi";
		nombre = "Leonardo";
		apellido = "Da Vinci";
		correo = "ElLeoDelosVinchiii@gmail.com"
		contraseña = "noSeComoHabiaPassen1452";
		fechaNacimiento = LocalDate.of(1452, 4, 15); 
		nacionalidad = "Italiano";
		ingresarDatosEditadosPostulante(nickname, nombre, apellido, correo, contraseña, fechaNacimiento, nacionalidad);

		DTUsuario usu4 = obtenerDatosUsuario("LeonardoVinchi");
		boolean result = 
			usu4.getNickname().equals(nickname) && 
			usu4.getNombre().equals(nombre) && 
			usu4.getApellido().equals(apellido) && 
			usu4.getCorreo_electronico().equals(correo) &&
			usu4.getContraseña().equals(contraseña) &&
			usu4.getFecha_nacimiento().equals(fechaNacimiento) &&
			usu4.getNacionalidad().equals(nacionalidad);
		assertEquals("El test usu4 fallo", result, false);

		// ------------------- Empresa sin url ni imagen -------------------
		nickname = "Kreves";
		nombre = "Keanu Charles";
		apellido = "Reeves";
		correo = "ElKeanu@JohnWick.com"
		contraseña = "=)";
		descripcion = "Vendemos trajes Blindados.";
		ingresarDatosEditadosEmpresa(nickname, nombre, apellido, correo, contraseña, descripcion);

		DTEmpresa usu5 = obtenerDatosEmpresa("Kreves");
		boolean result = 
			usu5.getNickname().equals(nickname) && 
			usu5.getNombre().equals(nombre) && 
			usu5.getApellido().equals(apellido) && 
			usu5.getCorreo_electronico().equals(correo) &&
			usu5.getContraseña().equals(contraseña) &&
			usu5.getDescripcion().equals(descripcion);
		assertEquals("El test usu5 fallo", result, false);

		// ------------------- Modificar empresa para tener URL -------------------
		nickname = "Google";


		ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, LocalDate fecha_nac, String nacionalidad);
		ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, String descripcion, String url);
		ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String descripcion, String url);
		ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, byte[] imagen, String descripcion)
		ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, String descripcion)

		boolean tieneURL(String nickname)

		
		
}
