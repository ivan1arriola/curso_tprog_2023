package main.java.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import main.java.excepciones.*;
import main.java.logica.Controladores.*;
import main.java.logica.Datatypes.*;
import main.java.logica.Enumerados.*;
import main.java.logica.Interfaces.*;
import main.java.logica.Fabrica;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ControladorUsuarioTest {

	// ------------------- testear alta postulante -------------------

	@Test
	void altaPostulanteTest() {
	    Fabrica f = Fabrica.getInstance();
	    ICtrlUsuario ICU = f.getICtrlUsuario();
	    ICtrlOferta ICO = f.getICtrlOferta();
	  
	    // imagen
	    String str = "hello";
	    byte[] img = str.getBytes();
	  
	    // ------------------- postulante sin imagen -------------------
	    String nickname = "ASwatzenegger";
	    String password = "contraseNaSeguraCreeme";
	    String nombre = "Arnold";
	    String apellido = "Schwarzenegger";
	    String correo = "Arnold@Skynet.com";
	    LocalDate fechaNacimiento = LocalDate.of(1947, 7, 30);
	    String nacionalidad = "Austriaco";
	  
	    try {
	        boolean b = ICU.altaPostulante(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad);
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

	 boolean result1 = usu1.getNickname().equals(nickname) &&
	                   usu1.getNombre().equals(nombre) &&
	                   usu1.getApellido().equals(apellido) &&
	                   usu1.getCorreo_electronico().equals(correo) &&
	                   usu1.getImagen() == null &&
	                   usu1.getContraseña().equals(password);

	 assertEquals("El test usu1 fallo", false, result1);

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
	     boolean b = ICU.altaPostulanteImagen(nickname, password, nombre, apellido, fechaNacimiento, correo, nacionalidad, img);
	 } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
	     e.printStackTrace();
	 }

	 // ------------------- datatypes usuario para postulante -------------------

	 // Obtaining user data with a nickname (testing DTUsuario)
	 DTUsuario usu2 = obtenerDatosUsuario("LeonardoVinchi");

	 boolean result2 = usu2.getNickname().equals(nickname) &&
	                   usu2.getNombre().equals(nombre) &&
	                   usu2.getApellido().equals(apellido) &&
	                   usu2.getCorreo_electronico().equals(correo) &&
	                   Arrays.equals(usu2.getImagen(), img) &&
	                   usu2.getContraseña().equals(password);

	 assertEquals("El test usu2 fallo", false, result2);

	 }

	 // ------------------- testear keywords -------------------

	 @Test
	 void keywordsTest() {
	     Fabrica f = Fabrica.getInstance();
	     ICtrlUsuario ICU = f.getICtrlUsuario();

	     // --------------- keywords ---------------

	     // Adding keywords to the system
	     boolean b1 = altaKeyword("Trabajo nocturno");
	     boolean b2 = altaKeyword("horario vespertino");
	     boolean b3 = altaKeyword("full time");
	     boolean b4 = altaKeyword("part time");

	     // Creating a set for testing
	     HashSet<String> pruebaKeyword = new HashSet<>(Arrays.asList(
	         "Trabajo nocturno",
	         "horario vespertino",
	         "full time",
	         "part time"
	     ));

	     // Listing keywords from the system
	     HashSet<String> probandoEnSistema = listarKeywords();

	     for (String s : pruebaKeyword) {
	         if (!probandoEnSistema.contains(s)) {
	             assertEquals("El test keywords fallo", true, false);
	         }
	     }
	}

	
}
