package main.test;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.UsuarioHandler;

import java.util.Set;


public class ControladorUsuarioTest {

	// ------------------- testear alta postulante -------------------

	@Test
	void altaPostulanteTest() {
	    Fabrica fabricaControlador = Fabrica.getInstance();
	    ICtrlUsuario ICU = fabricaControlador.getICtrlUsuario();
	  
	    // ------------------- postulante sin imagen -------------------
	    String nickname = "ASwatzenegger";
	    String password = "contraseNaSeguraCreeme";
	    String nombre = "Arnold";
	    String apellido = "Schwarzenegger";
	    String correo = "Arnold@Skynet.com";
	    LocalDate fechaNacimiento = LocalDate.of(1947,  7,  30);
	    String nacionalidad = "Austriaco";
	  
	    try {
	        //boolean bandera = 
	        ICU.altaPostulante(nickname,  password,  nombre,  apellido,  correo,  fechaNacimiento,  nacionalidad);
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

	 // Obtaining user data with a nickname (testing DTUsuario)
	 DTUsuario usu1 = ICU.obtenerDatosUsuario("ASwatzenegger");

	 boolean result1 = usu1.getNickname().equals(nickname) 
			 		   &&
	                   usu1.getNombre().equals(nombre) 
	                   &&
	                   usu1.getApellido().equals(apellido)
	                   &&
	                   usu1.getcorreoElectronico().equals(correo) 
	                   &&
	                   usu1.getImagen() == null 
	                   &&
	                   usu1.getcontrasenia().equals(password);

	 assertEquals("El test usu1 fallo",  true,  result1);
	 
	 // ------------------- postulante con imagen -------------------

	 nickname = "LeonardoVinchi";
	 password = "LaContrasenaMasSeguraDelMundo";
	 nombre = "Leonardo";
	 apellido = "Da Vinci";
	 correo = "Leo@vinchi.com";
	 fechaNacimiento = LocalDate.of(1452,  4,  15);
	 nacionalidad = "Italiano";

	 String img = "url";

//	 boolean b = 
	 ICU.altaPostulanteImagen(nickname,  password,  nombre,  apellido,  fechaNacimiento,  correo,  nacionalidad,  img);
	 
	// ------------------- datatypes usuario para postulante con imagen -------------------

	 // Obtaining user data with a nickname (testing DTUsuario)
	 DTUsuario usu2 = ICU.obtenerDatosUsuario("LeonardoVinchi");

	 boolean result2 = usu2.getNickname().equals(nickname)
			 	       &&
	                   usu2.getNombre().equals(nombre) 
	                   &&
	                   usu2.getApellido().equals(apellido) 
	                   &&
	                   usu2.getcorreoElectronico().equals(correo) 
	                   &&
	                   usu2.getcontrasenia().equals(password);
	 assertEquals("El test usu2 fallo", true, result2);
   }
	
  @Test
  void altaEmpresaTest() throws ExceptionUsuarioNickYCorreoRepetidos {
		    Fabrica fabricaControlador = Fabrica.getInstance();
		    ICtrlUsuario ICU = fabricaControlador.getICtrlUsuario();
		    //ICtrlOferta ICO = f.getICtrlOferta();

		    // ---------------- empresa sin url ni imagen ----------------
		    String nickname = "Kreves";
		    String password = "Pass";
		    String nombre = "Keanu";
		    String apellido = "Reeves";
		    String correo = "K2@gmail.com";
		    String descripcion = "Vendemos armas.";

		    try {
		        // boolean b = 
		        ICU.altaEmpresa(nickname,  password,  nombre,  apellido,  correo,  descripcion);
		    } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
		        e.printStackTrace();
		    } catch (ExceptionUsuarioNickRepetido e) {
		        e.printStackTrace();
		    } catch (ExceptionUsuarioCorreoRepetido e) {
		        e.printStackTrace();
		    }

		    // ------------------- datatypes usuario para postulante -------------------
		    // se obtiene con nickname,  notar que estoy probando DTUsuario
		    DTUsuario usu3 = ICU.obtenerDatosUsuario("Kreves");
		    boolean result = usu3.getNickname().equals(nickname) 
		    		&&
		            usu3.getNombre().equals(nombre) 
		            &&
		            usu3.getApellido().equals(apellido) 
		            &&
		            usu3.getcorreoElectronico().equals(correo) 
		            &&
		            usu3.getcontrasenia().equals(password);
		    assertEquals("El test usu3 fallo", result, true);
		    
		    // ------------------ empresa con url ------------------
		    nickname = "Google";
		    password = "Password";
		    nombre = "Larry";
		    apellido = "Page";
		    correo = "Larry@hotmail.com";
		    descripcion = "Vendemos informacion.";
		    String url = "www.google.com";

		    try {
//		        boolean b = 
		        ICU.altaEmpresaURL(nickname,  password,  nombre,  apellido,  correo,  descripcion,  url);
		    } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
		        e.printStackTrace();
		    } catch (ExceptionUsuarioNickRepetido e) {
		        e.printStackTrace();
		    } catch (ExceptionUsuarioCorreoRepetido e) {
		        e.printStackTrace();
		    }

		    // ----------------- dataTypes empresa -----------------
		    // se obtiene con nickname,  notar que estoy probando DTUsuario
		    UsuarioHandler UHan = UsuarioHandler.getInstance();
		    UHan.buscarCorreo("Larry@hotmail.com");
			Empresa empresa1 = (Empresa) UHan.buscarNick("Google");
			// obtuve empresa,  ahora creo DTEmpresa
			DTUsuario DTempresa1 = empresa1.obtenerDatosUsuario();
			DTEmpresa DTverdaderoEmpresa1 = (DTEmpresa) DTempresa1; // Casting
			boolean result2 = DTempresa1.getNickname().equals(nickname) 
					&&
					DTverdaderoEmpresa1.getNombre().equals(nombre) 
					&&
		            DTverdaderoEmpresa1.getApellido().equals(apellido) 
		            &&
		            DTverdaderoEmpresa1.getcorreoElectronico().equals(correo) 
		            &&
		            DTverdaderoEmpresa1.getcontrasenia().equals(password) 
		            &&
		            DTverdaderoEmpresa1.getDescripcion().equals(descripcion) 
		            &&
		            DTverdaderoEmpresa1.getUrl().equals(url);
		assertEquals("El test usu4 fallo",  result2,  true);


		    // ----------------- empresa con imagen ------------------
		    nickname = "Apple";
		    password = "Password";
		    nombre = "Steve";
		    apellido = "Jobs";
		    correo = "Steve@aplle.com";
		    descripcion = "Vendemos telefonos.";
		    
		    // imagen
		    String img3 = "url";
		boolean booly = ICU.altaEmpresaImagen(nickname,  password,  nombre,  apellido,  correo,  descripcion,  img3);

		Empresa empresa2 = (Empresa) UHan.buscarNick("Apple");
		DTUsuario DTempresa2 = empresa2.obtenerDatosUsuario();
		DTEmpresa DTverdaderoEmpresa2 = (DTEmpresa) DTempresa2; // Casting
		assertEquals("El test usu2 fallo",  true,  result2);
		String imagen4 = DTverdaderoEmpresa2.getImagen();
		

		boolean result3 = DTempresa2.getNickname().equals(nickname) 
				        &&
				DTverdaderoEmpresa2.getNombre().equals(nombre) 
				&&
				DTverdaderoEmpresa2.getApellido().equals(apellido) 
				&&
				DTverdaderoEmpresa2.getcorreoElectronico().equals(correo) 
				&&
				DTverdaderoEmpresa2.getcontrasenia().equals(password) 
				&&
				DTverdaderoEmpresa2.getDescripcion().equals(descripcion);
		// ----------------- empresa con url e imagen ------------------
		nickname = "Amazon";
		password = "Password";
		nombre = "Jeff";
		apellido = "Bezos";
		correo = "Bezo@porBezo.com";
		descripcion = "Vendemos libros.";
		url = "www.amazon.com";
		// imagen
		String  img4 = "url";
//		boolean b2 = 
		ICU.altaEmpresaURLyImagen(nickname,  password,  nombre,  apellido,  correo,  descripcion,  url,  img4);

		// ----------------- dataTypes empresa -----------------
		// se obtiene con nickname,  notar que estoy probando DTUsuario
		Empresa empresa3 = (Empresa) UHan.buscarNick("Amazon");
		// obtuve empresa,  ahora creo DTEmpresa
		DTUsuario DTempresa3 = empresa3.obtenerDatosUsuario();
		DTEmpresa DTverdaderoEmpresa3 = (DTEmpresa) DTempresa3; // Casting
		boolean result4 = DTempresa3.getNickname().equals(nickname) 
				&&
				DTverdaderoEmpresa3.getNombre().equals(nombre) 
				&&
				DTverdaderoEmpresa3.getApellido().equals(apellido) 
				&&
				DTverdaderoEmpresa3.getcorreoElectronico().equals(correo) 
				&&
				DTverdaderoEmpresa3.getcontrasenia().equals(password) 
				&&
				DTverdaderoEmpresa3.getDescripcion().equals(descripcion) 
				&&
				DTverdaderoEmpresa3.getUrl().equals(url);
		assertEquals("El test usu4 fallo",  result4,  true);

		String imagen5 = DTverdaderoEmpresa3.getImagen();    
		
		// ------------------- ver empresa en el sistema -------------------
		// si no esta uno aborta
		Set<String> EmpresaSistema = (HashSet<String>) ICU.listarEmpresas();
		for (String s : EmpresaSistema) {
			if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon")) {
				assertEquals("El test empresa en sistema fallo",  false,  true);
			}
		}

			
		
		// ------------------- ver usuarios en el sistema -------------------
		// si no esta uno aborta
		Set<String> UsuariosSistema = (HashSet<String>) ICU.listarNicknamesUsuarios();
		for (String s : UsuariosSistema) {
			if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon") && !s.equals("ASwatzenegger") && !s.equals("LeonardoVinchi")) {
				assertEquals("El test usuarios en sistema fallo",  false,  true);
			}
		}
		booly = ICU.validarCredenciales("Bezo@porBezo.com",  "Password");
		assertEquals("El test validarCredenciales fallo",  true,  booly);
		booly = ICU.validarCredenciales("Larry@hotmail.com", "Pass");
		if (booly) {
			assertEquals("El test validarCredenciales fallo",  true,  booly);
		}
		nickname = "Amazon";
		booly = ICU.validarCredenciales(nickname,  "Password");
		assertEquals("El test validarCredenciales fallo",  true,  booly);
		booly = ICU.validarCredenciales(nickname, "Pass");
		if (booly) {
			assertEquals("El test validarCredenciales fallo",  true,  booly);
		}
		
	}

  }
