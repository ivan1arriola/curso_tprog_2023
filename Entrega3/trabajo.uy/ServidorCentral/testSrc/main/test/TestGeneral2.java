package main.test;



import java.time.LocalDate;
//import java.util.Map;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
//import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
//import logica.clases.Keyword;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
//import logica.manejadores.OfertaLaboralHandler;
//import logica.manejadores.PaqueteHandler;
//import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral2 {
	@Test
	public void test1() {
		Fabrica fabri = Fabrica.getInstance();
        ICtrlUsuario ICU = fabri.getICtrlUsuario();
        ICtrlOferta ICO = fabri.getICtrlOferta();
        CtrlCargaDeDatos ICC = fabri.getICtrlCargaDeDatos();
        // obtener handeler
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Set the EntityManager for the handlers
        KeywordHandler.setBaseDatos(entityManager);
//        KeywordHandler keywordHandler = KeywordHandler.getInstance();
//        OfertaLaboralHandler.setBaseDatos(entityManager);
//        PaqueteHandler.setBaseDatos(entityManager);
//        TipoOfertaHandler.setBaseDatos(entityManager);
        UsuarioHandler.setBaseDatos(entityManager);
        // ============================================
        System.out.println("################## Test 2 ##################");
        // ============================================
        // Testeo de Keywords
        // ============================================
     // ------------------- postulante sin imagen -------------------
        String nickname = "ASwatzenegger";
        String password = "contraseNaSeguraCreeme";
        String nombre = "Arnold";
        String apellido = "Schwarzenegger";
        String correo = "Arnold@Skynet.com";
        LocalDate fechaNacimiento = LocalDate.of(1947,    7,    30);
        String nacionalidad = "Austriaco";

        try {
			ICU.altaPostulante(nickname,    password,    nombre,    apellido,    correo,    fechaNacimiento,    nacionalidad);
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido
				| ErrorAgregarUsuario | ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
     // Obtaining user data with a nickname (testing DTUsuario)
        DTUsuario usu1 = null;
		try {
			usu1 = ICU.obtenerDatosUsuario("ASwatzenegger");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//        System.out.println("nombre: " + usu1.getNombre());
		//        System.out.println("apellido: " + usu1.getApellido());
		//        System.out.println("nickname: " + usu1.getNickname());
		//        System.out.println("correo: " + usu1.getcorreoElectronico());
		//        System.out.println("contrasenia: " + usu1.getcontrasenia());
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
