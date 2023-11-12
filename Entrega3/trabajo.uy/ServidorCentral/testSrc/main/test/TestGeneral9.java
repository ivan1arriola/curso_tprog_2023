package main.test;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
//import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
//import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionValidezNegativa;
import excepciones.NoExistePaquete;
//import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
//import logica.clases.Keyword;
import logica.controladores.CtrlCargaDeDatos;
//import logica.datatypes.DTOfertaExtendido;
//import logica.datatypes.DTTipoOferta;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral9 {
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
        System.out.println("################## Test 9 ##################");
        // ============================================


        // ------------------- postulante sin imagen -------------------
        String nickname = "NickPrueba";
        String password = "notedigo";
        String nombre = "Juan";
        String apellido = "Perez";
        String correo = "algunmail@mail.com";
        LocalDate fechaNacimiento = LocalDate.of(1958,     4,     24);
        String nacionalidad = "Ruso";

        try {
			ICU.altaPostulante(nickname,     password,     nombre,     apellido,     correo,     fechaNacimiento,     nacionalidad);
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido
				| ErrorAgregarUsuario | ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        DTUsuario usu1 = null;
		try {
			usu1 = ICU.obtenerDatosUsuario("NickPrueba");
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

        assertEquals("El test usu1 fallo",     true,     result1);

        String offer = "EjemploOferta";
        String desc = "descripcion";
        int valido = 10;
        LocalDate fecha = LocalDate.of(1990,     6,     24);
        float descu = 10;

        //boolean boolPaquete = ICO.altaPaqueteOL(offer,      desc,       valido,      fecha,     descu,     null);

        boolean boolPaquete = false;
        try {
            boolPaquete = ICO.altaPaqueteOL(offer,     desc,     valido,     fecha,     descu,     null);
        } catch (ExceptionDescuentoInvalido exc) {
            exc.printStackTrace();
        } catch (ExceptionValidezNegativa exc) {
            exc.printStackTrace();
        }


        assertTrue(boolPaquete,     "oferta ok");

        assertThrows(IllegalArgumentException.class,     () -> {
            ICO.altaPaqueteOL("",     desc,     -10,     fecha,     descu,     null);
        });
        

        String nickname99 = "Otronick";
        String password99 = "notedigo";
        String nombre99 = "Juancito";
        String apellido99 = "Perez";
        String correo99 = "algunotromail@mailing.com";
        LocalDate fechaNacimiento99 = LocalDate.of(1958,     4,     24);
        String nacionalidad99 = "Ruso";

        try {
			ICU.altaPostulante(nickname99,     password99,     nombre99,     apellido99,     correo99,     fechaNacimiento99,     nacionalidad99);
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido
				| ErrorAgregarUsuario | ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 


        String offer99 = "EjemploOferta";
        String desc99 = "descripcion";
        int valido99 = -10;
        LocalDate fecha99 = LocalDate.of(1990,     6,     24);
        float descu99 = 10;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,     () -> {
            ICO.altaPaqueteOL(offer,     desc,     valido,     fecha,     descu,     null);
        });

//        assertEquals("El argumento 'validez' debe ser mayor a 0.",     exception.getMessage());
        

        String offer1 = "Ejemploito";
        String desc1 = "descripcioncita";
        int valido1 = 10;
        LocalDate fecha1 = LocalDate.of(1995,     6,     24);
        float descu1 = 120;


        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,     () -> {
            ICO.altaPaqueteOL(offer1,     desc1,     valido1,     fecha1,     descu1,     null);
        });

//        assertEquals("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.",     exception1.getMessage());
        
        
        String offer11 = "Demasiadooooo";
        String desc11 = "";
        int valido11 = 10;
        LocalDate fecha11 = LocalDate.of(1995,     6,     24);
        float descu11 = 15;


        IllegalArgumentException exception11 = assertThrows(IllegalArgumentException.class,     () -> {
            ICO.altaPaqueteOL(offer11,     desc11,     valido11,     fecha11,     descu11,     null);
        });

//        assertEquals("El argumento 'descripcion' no puede ser vacío.",     exception11.getMessage());


        //ICO.altaPaqueteOL(offer,      "algo",     valido,      fecha,     descu,     null);

//       
//        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,     () -> {
//            ICO.altaPaqueteOL("Demasiado",     "una descripcion",     valido11,     fecha11,     descu11,     null);
//        });

//        assertEquals("El argumento 'nombre' ya existe en el sistema.",     exception2.getMessage());

        
        boolean keys1 = false;
		try {
			keys1 = ICO.altaKeyword("key1");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue(keys1,     "Alta key ok");

        boolean keys2 = false;
		try {
			keys2 = ICO.altaKeyword("key1");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertFalse(keys2,     "Alta key repetida");
        
        try {
            try {
				ICU.altaEmpresa("Empresario",     "tupass",     "Pepito",     "Gomez",     "yoquese@hotmail.com",     "geniales");
			} catch (ErrorAgregarUsuario e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioNickRepetido e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }
        LocalDate atrfechaAlta6 = LocalDate.of(2020,     12,     12);


        boolean comprado = false;

        try {
            try {
				comprado = ICO.compraPaquetes("Empresario",     "EjemploOferta",     atrfechaAlta6,     0);
			} catch (ExceptionValidezNegativa | ExceptionUsuarioNoEncontrado | NoExistePaquete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ExceptionCompraPaqueteConValorNegativo exc) {
            exc.printStackTrace();
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            exc.printStackTrace();
        }


//        assertTrue(comprado,     "Compra ok");
        
        Set<String> empresas = ICO.listarEmpresas();
        boolean contiene = empresas.contains("ANTEL");
//        assertTrue(contiene);
//
//        Set<String> ingresadas = null;
//		try {
//			ingresadas = ICO.listarOfertasLaboralesIngresadas("ANTEL");
//		} catch (ExceptionUsuarioNoEncontrado e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        boolean ingresoNoVacio = !ingresadas.isEmpty();
//        assertTrue(ingresoNoVacio);
//
//        try {
//			ICO.aceptoOL("Content Manager");
//		} catch (OfertaLaboralNoEncontrada e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        Set<DTOfertaExtendido> confirmadas = ICO.listarOfertasLaboralesConfirmadas();
//        boolean esta = false;
//        for (DTOfertaExtendido elem : confirmadas) {
//            if (elem.getNombre().equals("Content Manager")) {
//                esta = true;
//
//            }
//        }
//        assertTrue(esta);
//
//        Set<String> postu = ICO.listarPostulantes();
//        assertTrue(postu.contains("lgarcia"));
//
//        DTOfertaExtendido dtof = null;
//		try {
//			dtof = ICO.obtenerOfertaLaboral("Gerente de Proyecto");
//		} catch (OfertaLaboralNoEncontrada e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////        assertEquals(dtof.getCiudad(),     "Montevidoe");
//
//
//        try {
//            ICO.altaPaqueteOL("Demasiado",     "una descripcion",     10,     LocalDate.of(1958,     10,     10),     90,     null);
//        } catch (ExceptionDescuentoInvalido exc) {
//            exc.printStackTrace();
//        } catch (ExceptionValidezNegativa exc) {
//            exc.printStackTrace();
//        }
//
//        LocalDate atrfechaAlta61 = LocalDate.of(2023,     9,     2);
//
//
//        //ICO.compraPaquetes("ANTEL",     "Demasiado",     atrfechaAlta6 ,     0);
//
//        try {
//            try {
//				ICO.compraPaquetes("ANTEL",     "Demasiado",     atrfechaAlta61,     0);
//			} catch (ExceptionValidezNegativa | ExceptionUsuarioNoEncontrado | NoExistePaquete e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        } catch (ExceptionCompraPaqueteConValorNegativo exc) {
//            exc.printStackTrace();
//        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
//            exc.printStackTrace();
//        }
//
//
//        try {
//			assertTrue(ICO.paqueteComprado("Demasiado"));
//		} catch (NoExistePaquete e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//        Set<String> keysLista = ICO.listarKeywords();
//        assertTrue(keysLista.contains("Freelance"));
//
//        DTTipoOferta dtoferta = null;
//
//        try {
//            dtoferta = ICO.obtenerDatosTO("Destacada");
//        } catch (ExcepcionTipoOfertaNoExistente e) {
//            e.printStackTrace();
//        }

//        assertEquals(dtoferta.getDescripcion(),     "Destaca tu anuncio");
        
     // ============================================
//        try {
//			ICC.cargarDatos();
//		} catch (ExceptionFechaInvalida | ErrorAgregarUsuario e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
        // ============================================
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
