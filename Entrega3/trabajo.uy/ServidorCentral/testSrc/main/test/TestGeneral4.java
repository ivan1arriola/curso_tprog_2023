package main.test;



import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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


public class TestGeneral4 {
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
        System.out.println("################## Test 4 ##################");
        // ============================================
        // pruebo si efectivamente se persistieron keywords
        Set<String> pruebaKeyword = new HashSet<>(Arrays.asList(
                "Trabajo nocturno",
                "horario vespertino",
                "full time",
                "part time"
        ));

        // Listing keywords from the system
        Set<String> probandoEnSistema = (HashSet<String>) ICO.listarKeywords();

        for (String s : pruebaKeyword) {
            if (!probandoEnSistema.contains(s)) {
                assertEquals("El test keywords fallo", true, false);
            }
        }
     // ---------------- empresa sin url ni imagen ----------------
        // notar que tiene todos los usuarios creados
        
        boolean kreves2 = false;
        boolean google2 = false;
        boolean apple2 = false;
        boolean amazon2 = false;
        boolean aswatzenegger2 = false;
        boolean leonardoVinchi2 = false;

        Set<String> UsuariosSistema = (HashSet<String>) ICU.listarNicknamesUsuarios();
        for (String s : UsuariosSistema) {
            if (s.equals("Kreves")) {
                kreves2 = true;
            } else if (s.equals("Google")) {
                google2 = true;
            } else if (s.equals("Apple")) {
                apple2 = true;
            } else if (s.equals("Amazon")) {
                amazon2 = true;
            } else if (s.equals("ASwatzenegger")) {
                aswatzenegger2 = true;
            } else if (s.equals("LeonardoVinchi")) {
                leonardoVinchi2 = true;
            }
        }

        if (!kreves2 || !google2 || !apple2 || !amazon2 || !aswatzenegger2 || !leonardoVinchi2) {
            assertEquals("El test usuarios en sistema fallo", false, true);
        }
        // editar datos
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Postulante postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
        // obtuve empresa,   ahora creo DTEmpresa

        ICU.ingresarDatosEditadosPostulante(postulante1.getNickname(),
                postulante1.getNombre(),
                postulante1.getApellido(),
                postulante1.getcorreoElectronico(),
                postulante1.getcontrasenia(),
                postulante1.getFechaNac(),
                postulante1.getNacionalidad());

        DTUsuario DTpostulante1 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi", "LeonardoVinchi");
        DTPostulante DTverdaderopostulante1 = (DTPostulante) DTpostulante1; // Casting;

        ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante1.getNickname(),
                DTverdaderopostulante1.getNombre(),
                DTverdaderopostulante1.getApellido(),
                DTverdaderopostulante1.getcorreoElectronico(),
                DTverdaderopostulante1.getcontrasenia(),
                DTverdaderopostulante1.getFechaNac(),
                DTverdaderopostulante1.getNacionalidad());

        String img23 = "url";

        ICU.ingresarDatosEditadosPostulanteImg(DTverdaderopostulante1.getNickname(),
                DTverdaderopostulante1.getNombre(),
                DTverdaderopostulante1.getApellido(),
                DTverdaderopostulante1.getcorreoElectronico(),
                DTverdaderopostulante1.getcontrasenia(),
                img23,
                DTverdaderopostulante1.getFechaNac(),
                DTverdaderopostulante1.getNacionalidad());
        postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
        DTUsuario DTpostulante12 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi", "ASwatzenegger");
        DTPostulante DTverdaderopostulante12 = (DTPostulante) DTpostulante12; // Casting;

        ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante12.getNickname(),
                DTverdaderopostulante12.getNombre(),
                DTverdaderopostulante12.getApellido(),
                DTverdaderopostulante12.getcorreoElectronico(),
                DTverdaderopostulante12.getcontrasenia(),
                DTverdaderopostulante12.getFechaNac(),
                DTverdaderopostulante12.getNacionalidad());

		// ============================================
        
        entityManager.close();
        entityManagerFactory.close();
	}
}
