package main.test;



import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.Keyword;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral3 {
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
        KeywordHandler keywordHandler = KeywordHandler.getInstance();
        OfertaLaboralHandler.setBaseDatos(entityManager);
        PaqueteHandler.setBaseDatos(entityManager);
        TipoOfertaHandler.setBaseDatos(entityManager);
        UsuarioHandler.setBaseDatos(entityManager);
        // ============================================
        System.out.println("################## Test 3 ##################");
        // ============================================
        // Testeo de Keywords
        // ============================================
     // Obtaining user data with a nickname (testing DTUsuario)
        String nickname = "ASwatzenegger";
        String password = "contraseNaSeguraCreeme";
        String nombre = "Arnold";
        String apellido = "Schwarzenegger";
        String correo = "Arnold@Skynet.com";
        LocalDate fechaNacimiento = LocalDate.of(1947,    7,    30);
        String nacionalidad = "Austriaco";
        DTUsuario usu1 = null;
		try {
			usu1 = ICU.obtenerDatosUsuario("ASwatzenegger");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

        assertEquals("El test usu1 fallo",    true,    result1);
     // ------------------- postulante con imagen -------------------
        nickname = "LeonardoVinchi";
        password = "LaContrasenaMasSeguraDelMundo";
        nombre = "Leonardo";
        apellido = "Da Vinci";
        correo = "Leo@vinchi.com";
        fechaNacimiento = LocalDate.of(1452,    4,    15);
        nacionalidad = "Italiano";

        String img = "url";

        //	 boolean b = 
        try {
			ICU.altaPostulanteImagen(nickname,    password,    nombre,    apellido,    fechaNacimiento,    correo,    nacionalidad,    img.getBytes());
		} catch (ExceptionFechaInvalida | ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // ------------------- datatypes usuario para postulante con imagen -------------------

        // Obtaining user data with a nickname (testing DTUsuario)
        DTUsuario usu2 = null;
		try {
			usu2 = ICU.obtenerDatosUsuario("LeonardoVinchi");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        boolean result2 = usu2.getNickname().equals(nickname)
                &&
                usu2.getNombre().equals(nombre)
                &&
                usu2.getApellido().equals(apellido)
                &&
                usu2.getcorreoElectronico().equals(correo)
                &&
                usu2.getcontrasenia().equals(password);
        assertEquals("El test usu2 fallo",    true,    result2);
     // ---------------- empresa sin url ni imagen ----------------
        String nickname1 = "Kreves";
        String password1 = "Pass";
        String nombre1 = "Keanu";
        String apellido1 = "Reeves";
        String correo1 = "K2@gmail.com";
        String descripcion = "Vendemos lapices.";


            // boolean b =
            try {
				ICU.altaEmpresa(nickname1,    password1,    nombre1,    apellido1,    correo1,    descripcion);
			} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
					| ExceptionUsuarioNickRepetido | ErrorAgregarUsuario e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  

        // ------------------- datatypes usuario para postulante -------------------
        // se obtiene con nickname,     notar que estoy probando DTUsuario
        DTUsuario usu3 = null;
		try {
			usu3 = ICU.obtenerDatosUsuario("Kreves");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean result = usu3.getNickname().equals(nickname)
                &&
                usu3.getNombre().equals(nombre)
                &&
                usu3.getApellido().equals(apellido)
                &&
                usu3.getcorreoElectronico().equals(correo)
                &&
                usu3.getcontrasenia().equals(password);
        assertEquals("El test usu3 fallo",    result,    false); // si result es true se creo el usuario

        // ------------------ empresa con url ------------------
        nickname = "Google";
        password = "Password";
        nombre = "Larry";
        apellido = "Page";
        correo = "Larry@hotmail.com";
        descripcion = "Vendemos informacion.";
        String url = "www.google.com";

        
//		        boolean b = 
            try {
				ICU.altaEmpresaURL(nickname,    password,    nombre,    apellido,    correo,    descripcion,    url);
			} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
					| ExceptionUsuarioNickRepetido | ErrorAgregarUsuario e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

     // ----------------- dataTypes empresa -----------------
        // se obtiene con nickname,     notar que estoy probando DTUsuario
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        try {
			UHan.buscarCorreo("Larry@hotmail.com");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Empresa empresa1 = null;
		try {
			empresa1 = (Empresa) UHan.buscarNick("Google");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // obtuve empresa,     ahora creo DTEmpresa
        DTUsuario DTempresa1 = empresa1.obtenerDatosUsuario();
        DTEmpresa DTverdaderoEmpresa1 = (DTEmpresa) DTempresa1; // Casting
        boolean result21 = DTempresa1.getNickname().equals(nickname)
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
        assertEquals("El test usu4 fallo",    result21,    true);


        // ----------------- empresa con imagen ------------------
        nickname = "Apple";
        password = "Password";
        nombre = "Steve";
        apellido = "Jobs";
        correo = "Steve@aplle.com";
        descripcion = "Vendemos telefonos.";

        // imagen
        String img3 = "url";
        boolean booly = false;
		try {
			booly = ICU.altaEmpresaImagen(nickname,    password,    nombre,    apellido,    correo,    descripcion,    img3.getBytes());
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Empresa empresa2 = null;
		try {
			empresa2 = (Empresa) UHan.buscarNick("Apple");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DTUsuario DTempresa2 = empresa2.obtenerDatosUsuario();
        DTEmpresa DTverdaderoEmpresa2 = (DTEmpresa) DTempresa2; // Casting
        assertEquals("El test usu2 fallo",    true,    result21);
        byte[] imagen4 = DTverdaderoEmpresa2.getImagen();


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
        String img4 = "url";
//		boolean b2 = 
        try {
			ICU.altaEmpresaURLyImagen(nickname,    password,    nombre,    apellido,    correo,    descripcion,    url,    img4.getBytes());
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // ----------------- dataTypes empresa -----------------
        // se obtiene con nickname,     notar que estoy probando DTUsuario
        Empresa empresa3 = null;
		try {
			empresa3 = (Empresa) UHan.buscarNick("Amazon");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // obtuve empresa,     ahora creo DTEmpresa
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
        assertEquals("El test usu4 fallo",    result4,    true);

        byte[] imagen5 = DTverdaderoEmpresa3.getImagen();
     // ------------------- ver empresa en el sistema -------------------
        // si no esta uno aborta
        Set<String> EmpresaSistema = (HashSet<String>) ICU.listarEmpresas();
        boolean krevesFound = false;
        boolean googleFound = false;
        boolean appleFound = false;
        boolean amazonFound = false;

        for (String s : EmpresaSistema) {
        //            System.out.println("The value of myVariable is: " + s);
            
            if (s.equals("Kreves")) {
                krevesFound = true;
            } else if (s.equals("Google")) {
                googleFound = true;
            } else if (s.equals("Apple")) {
                appleFound = true;
            } else if (s.equals("Amazon")) {
                amazonFound = true;
            }
        }

        if (!krevesFound || !googleFound || !appleFound || !amazonFound) {
            assertEquals("El test empresa en sistema fallo",    false,    true);
        }
     // ------------------- ver usuarios en el sistema -------------------
        // si no esta uno aborta
        Set<String> UsuariosSistema = (HashSet<String>) ICU.listarNicknamesUsuarios();
        boolean krevesFound1 = false;
        boolean googleFound1 = false;
        boolean appleFound1 = false;
        boolean amazonFound1 = false;
        boolean aswatzeneggerFound = false;
        boolean leonardoVinchiFound = false;

        for (String s : UsuariosSistema) {
            if (s.equals("Kreves")) {
                krevesFound1 = true;
            } else if (s.equals("Google")) {
                googleFound1 = true;
            } else if (s.equals("Apple")) {
                appleFound1 = true;
            } else if (s.equals("Amazon")) {
                amazonFound1 = true;
            } else if (s.equals("ASwatzenegger")) {
                aswatzeneggerFound = true;
            } else if (s.equals("LeonardoVinchi")) {
                leonardoVinchiFound = true;
            }
        }

        if (!krevesFound1 || !googleFound1 || !appleFound1 || !amazonFound1 || !aswatzeneggerFound || !leonardoVinchiFound) {
            assertEquals("El test usuarios en sistema fallo",    false,    true);
        }
        // ------------------------------- validar credenciales --------------------------
        try {
			booly = ICU.validarCredenciales("Bezo@porBezo.com",    "Password");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals("El test validarCredenciales fallo",    true,    booly);
        try {
			booly = ICU.validarCredenciales("Larry@hotmail.com",    "Pass");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (booly) {
            assertEquals("El test validarCredenciales fallo",    true,    booly);
        }
        nickname = "Amazon";
        try {
			booly = ICU.validarCredenciales(nickname,    "Password");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals("El test validarCredenciales fallo",    true,    booly);
        try {
			booly = ICU.validarCredenciales(nickname,    "Pass");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (booly) {
            assertEquals("El test validarCredenciales fallo",    true,    booly);
        }
        // ============================================
        
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
