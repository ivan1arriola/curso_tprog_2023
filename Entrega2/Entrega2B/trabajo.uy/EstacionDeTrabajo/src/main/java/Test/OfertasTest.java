package main.java.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;
import org.junit.jupiter.api.BeforeAll;

public class OfertasTest {
	
	private static ICtrlUsuario ICU;
	private static ICtrlOferta ICO;
	private static Fabrica f;
	
	@BeforeAll
	static void setUp() {
		f = Fabrica.getInstance();
	    ICU = f.getICtrlUsuario();
	    ICO = f.getICtrlOferta();
        // Puedes realizar configuraciones adicionales aquí
    }

	@Test
	void altaPostulanteTest() {

	  
	    // ------------------- postulante sin imagen -------------------
	    String nickname = "NickPrueba";
	    String password = "notedigo";
	    String nombre = "Juan";
	    String apellido = "Perez";
	    String correo = "algunmail@mail.com";
	    LocalDate fechaNacimiento = LocalDate.of(1958,  4,  24);
	    String nacionalidad = "Ruso";
	  
	    try {
	        boolean b = ICU.altaPostulante(nickname,  password,  nombre,  apellido,  correo,  fechaNacimiento,  nacionalidad);
	    } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioNickRepetido e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioCorreoRepetido e) {
	        e.printStackTrace();
	    }
	    
	 DTUsuario usu1 = ICU.obtenerDatosUsuario("NickPrueba");

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
	 
	String offer = "EjemploOferta";
	String desc = "descripcion";
	int valido = 10;
	LocalDate fecha = LocalDate.of(1990,  6,  24);
	float descu = 10;
	
	 boolean boolPaquete = ICO.altaPaqueteOL(offer,  desc,   valido,  fecha, descu, null);
	 assertTrue(boolPaquete, "oferta ok");
	 
	assertThrows(IllegalArgumentException.class, () -> {
		 ICO.altaPaqueteOL("",  desc, -10,  fecha, descu, null); }); 
	 
   }
	
	@Test
	void altaPostulanteValido() {
	    	  
	    String nickname = "Otronick";
	    String password = "notedigo";
	    String nombre = "Juancito";
	    String apellido = "Perez";
	    String correo = "algunotromail@mailing.com";
	    LocalDate fechaNacimiento = LocalDate.of(1958,  4,  24);
	    String nacionalidad = "Ruso";
	  
	    try {
	        boolean b = ICU.altaPostulante(nickname,  password,  nombre,  apellido,  correo,  fechaNacimiento,  nacionalidad);
	    } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioNickRepetido e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioCorreoRepetido e) {
	        e.printStackTrace();
	    }
	    
		 
	String offer = "EjemploOferta";
	String desc = "descripcion";
	int valido = -10;
	LocalDate fecha = LocalDate.of(1990,  6,  24);
	float descu = 10;
	
	 
	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
		 ICO.altaPaqueteOL(offer,  desc, valido,  fecha, descu, null); });
		
	assertEquals("El argumento 'validez' debe ser mayor a 0.", exception.getMessage());

}
	
	@Test
	void altaPostulanteDescuInvalido() {
	    Fabrica f = Fabrica.getInstance();
	    
	    ICtrlOferta ICO = f.getICtrlOferta();
	  
	    		 
		String offer = "Ejemploito";
		String desc = "descripcioncita";
		int valido = 10;
		LocalDate fecha = LocalDate.of(1995,  6,  24);
		float descu = 120;
	
	 
	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
		 ICO.altaPaqueteOL(offer,  desc, valido,  fecha, descu, null); });
		
	assertEquals("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.", exception.getMessage());

	}
	
	
	@Test
	void altaPostulanteDesc() {
	   
		String offer = "Demasiado";
		String desc = "";
		int valido = 10;
		LocalDate fecha = LocalDate.of(1995,  6,  24);
		float descu = 15;
	
	 
	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
		 ICO.altaPaqueteOL(offer,  desc, valido,  fecha, descu, null); });
		
	assertEquals("El argumento 'descripcion' no puede ser vacío.", exception.getMessage());
	
	
	ICO.altaPaqueteOL(offer,  "algo", valido,  fecha, descu, null);
	IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
		ICO.altaPaqueteOL("Demasiado",  "una descripcion", valido,  fecha, descu, null); });

	assertEquals("El argumento 'nombre' ya existe en el sistema.", exception2.getMessage());
	
	}
	
	
	@Test
	void keysTest() {
	    Fabrica f = Fabrica.getInstance();
	    
	    ICtrlOferta ICO = f.getICtrlOferta();
	    boolean keys1 = ICO.altaKeyword("key1");
	    assertTrue(keys1, "Alta key ok"); 
	    
	    boolean keys2 = ICO.altaKeyword("key1");
	    assertFalse(keys2, "Alta key repetida");
		
	}
	
	@Test
	void compraPaqTest() {
		try {
			boolean empresita = ICU.altaEmpresa("Empresario", "tupass", "Pepito", "Gomez", "yoquese@hotmail.com", "geniales");
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioNickRepetido e) {
	        e.printStackTrace();
	    } catch (ExceptionUsuarioCorreoRepetido e) {
	        e.printStackTrace();
	    }
			
		boolean comprado = ICO.compraPaquetes("Empresario", "EjemploOferta");
		
		assertTrue(comprado, "Compra ok");
		 
		}
	
	

}
	
	
	
	
	

