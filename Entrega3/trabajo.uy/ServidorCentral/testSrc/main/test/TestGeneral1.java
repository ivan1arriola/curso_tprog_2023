package main.test;



import java.util.Map;

import org.junit.Test;

import excepciones.ExcepcionKeywordVacia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Keyword;
import logica.controladores.CtrlCargaDeDatos;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral1 {
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
//        OfertaLaboralHandler.setBaseDatos(entityManager);
//        PaqueteHandler.setBaseDatos(entityManager);
//        TipoOfertaHandler.setBaseDatos(entityManager);
//        UsuarioHandler.setBaseDatos(entityManager);
        // ============================================
        System.out.println("################## Test 1 ##################");
        // ============================================
        // Testeo de Keywords
        // ============================================
        try {
			ICO.altaKeyword("Trabajo nocturno");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ICO.altaKeyword("horario vespertino");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ICO.altaKeyword("full time");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ICO.altaKeyword("part time");
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // ============================================
        // obtengo lista keyword
		//        Map<String, Keyword> temporalKeywords = null;
		//        try {
		//            temporalKeywords = KeywordHandler.obtener();
		//        } catch (Exception e) {
		//            // Handle the exception or log it
		//            e.printStackTrace();
		//        }
		//        if (temporalKeywords != null) {
		//            for (Map.Entry<String, Keyword> entry : temporalKeywords.entrySet()) {
		//                Keyword k = entry.getValue();
		//                System.out.println("======> " + k.getNombre());
		//            }
		//        }
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
