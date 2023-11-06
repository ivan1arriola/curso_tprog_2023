package main.test;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.OfertaLaboralNoEncontrada;
import excepciones.TipoUsuarioNoValido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral6 {
	@Test
	public void Test1() {
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
        System.out.println("################## Test 6 ##################");
        // ============================================
		  // testeo cosas random
		  
		  String nickname = "Google";
		  String nombre11 = "analista";
		  String descripcion11 = "Analista de sistemas";
		  DTHora hora1111 = new DTHora(8, 0);
		  DTHora hora21 = new DTHora(1, 0);
		  DTHorario horario111 = new DTHorario(hora1111, hora21);
		  float remuneracion11 = 1000;
		  String ciudad111 = "Montevideo";
		  DepUY dep111 = DepUY.Montevideo;
		  LocalDate fechaA1 = LocalDate.of(2020, 12, 12);

	      List<String> pruebaKeyword11 = new ArrayList<>(Arrays.asList(
	              "Trabajo nocturno",
	              "horario vespertino",
	              "full time",
	              "part time"
	      ));
	
	      EstadoOL estado111 = EstadoOL.Ingresada;
	      String img111 = "url";
	      String paquete1 = "Paquete 1";
	      // public boolean altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  String descripcion,  DTHorario horario,  float remun,  String ciu,  DepUY dep,  LocalDate FechaA,  List<String> keys,  EstadoOL estado,  byte[] img,  String paquete) throws ExceptionUsuarioNoEncontrado,  ExceptionEmpresaInvalida{
		  try {
			ICU.altaOfertaLaboral(nickname, "Oferta normal", nombre11, descripcion11, horario111, remuneracion11, ciudad111, dep111, fechaA1, pruebaKeyword11, estado111, img111.getBytes(), paquete1);
		} catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida | ExceptionRemuneracionOfertaLaboralNegativa
				| ExceptionPaqueteNoVigente | ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			Set<String> nuevo = 
      ICU.listarPostulantesDeOfertas(nickname, nombre11);
//			Set<String> holaaaa = 
      try {
			ICU.listarKeywords("analista");
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//			DTUsuario gogogo = 
      try {
			ICU.obtenerDatosUsuarioEspecial("Google", "Google");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			gogogo = 
      try {
			ICU.obtenerDatosUsuarioEspecial("Google", "Kreves");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//			boolean sefs = 
      try {
			ICU.existePostulacion("ASwatzenegger", "analista");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      String nick = "ASwatzenegger";
      String curriculumVitae = "CV";
      String motivacion1111111 = "motivacion";
      LocalDate fecha1 =  LocalDate.now();
      String URLDocExtras11 = "www.google.com";
      OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
      Map<String, OfertaLaboral> mapa = OLH.obtener();
      OfertaLaboral oferta = mapa.get("analista");
//			Postulacion nueva = 
      try {
			ICU.crearPostulacion(nick, curriculumVitae, motivacion1111111, fecha1, URLDocExtras11, oferta, "link video");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try {
			ICU.modificarPostulacion("analista", nick, "CV asad", "quiero DORMIR");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICU.obtenerDatosPostulacionW("ASwatzenegger", "analista");
		} catch (ExceptionUsuarioNoEncontrado | TipoUsuarioNoValido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
      try {
			ICU.hayPostulacionW("ASwatzenegger", "analista");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try {
			ICU.listarOfertasLaboralesConfirmadas("Google");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try {
          ICU.listarOfertasLaborales("Google");
      } catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
//
////			Set<String> nuevamente = 
//      ICO.listarOfertasLaboralesKeywords("Trabajo nocturno");
//      // esto es para crear una postulacion de verdad
//      String nick113 = "LeonardoVinchi";
//      try {
//			ICO.altaPostulacion("analista", nick113, "CV", "descripccion interesante", "wwww.Linkedin.com/usuario", LocalDate.of(2020, 12, 12), "link video");
//		} catch (OfertaLaboralNoEncontrada | ExceptionUsuarioNoEncontrado e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////			nuevamente =  
//      ICO.listarPaquetes();
//
////			nuevamente = 
//      ICO.listarTipoDePublicaciones();
//
////			Set<DTOfertaExtendido> nuevoOfertaEX = 
//      ICO.listarOfertasLaboralesConfirmadas();
//
//      ICO.paqueteComprado("Paquete 1");
//
////			DTOfertaExtendidoSinPConK nuevaExsinpconk = 
//      try {
//			ICO.infoOfertaLaboralEmpresa("Google", "analista");
//		} catch (OfertaLaboralNoEncontrada | ExceptionUsuarioNoEncontrado e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//      String img33 = "url";
//      try {
//			ICO.altaPaqueteOL("Paquete 2", "un paquete bueno", 1, LocalDate.now(), 10.0f, img33.getBytes());
//		} catch (ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//      // los paquetes empiezan vacios,    se les va agregando tipos de oferta
//
//      try {
//			ICO.agregarTipoOfertaPaq("Paquete 2", "Oferta normal", 20);
//		} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      try {
//			ICO.agregarTipoOfertaPaq("Paquete 2", "Oferta destacada", 12);
//		} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//      try {
//			ICO.listarOfertasLaboralesIngresadas("Google");
//		} catch (ExceptionUsuarioNoEncontrado e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//      ICO.listarEmpresas();
//      ICO.listarPostulantes();
////			
//
////			DTOfertaExtendido EXTRA = 
//      try {
//			ICO.obtenerOfertaLaboral("analista");
//		} catch (OfertaLaboralNoEncontrada e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////			DTOfertaExtendidoSinPConK extremo = 
//      try {
//			ICO.infoOfertaLaboralPostulante("Arnold", "analista");
//		} catch (OfertaLaboralNoEncontrada e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
