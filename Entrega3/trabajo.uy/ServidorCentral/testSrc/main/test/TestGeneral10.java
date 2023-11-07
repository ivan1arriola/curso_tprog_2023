package main.test;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionValidezNegativa;
import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.clases.TipoOferta;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
import logica.datatypes.DTUsuario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral10 {
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
        System.out.println("################## Test 10 ##################");
        // ============================================
        // -------------------------------------------
        String imagen ="hola";
        // -------------------------------------------
        float randomFloat = 10.0f;
        // -------------------------------------------
        LocalDate currentDate = LocalDate.now(); // Get the current date
        LocalDate SixDaysAgo = currentDate.minusDays(6); 
        LocalDate SevenDaysAgo = currentDate.minusDays(7); 
        // -------------------------------------------
        Paquete NuevoPaq = null;
		try {
			NuevoPaq = new  Paquete(
				    "Manteca Paquete",
				    "cremosa como nunca",
				    900,
				    SixDaysAgo,
				    randomFloat,
				    imagen.getBytes()
				);
		} catch (ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
        
        Empresa empresaNueva = new Empresa(
				    "Hidraulioocs",
				    "Fierro-motors",
				    "Le_bron-james",
				    "El@correo.com",
				    "1231254",
				    "hola".getBytes(),
				    "mejores biciclertas y motos",
				    "www.motos.com"
				);
        
        // ===============================
        // lista de keywords
        List<Keyword> myList = new ArrayList<>();
        Map<String, Keyword> nuevaKeyword = KeywordHandler.obtener();
        String keywordToObtain = "Trabajo nocturno";
        Keyword utopicKeyword = nuevaKeyword.get(keywordToObtain);
        String keywordToObtain1 = "full time";
        Keyword utopicKeyword1 = nuevaKeyword.get(keywordToObtain1);
        myList.add(utopicKeyword);
        myList.add(utopicKeyword1);
        // -------------------------------------------
        TipoOferta tipoofertaNuevo = null;
		try {
			tipoofertaNuevo = new TipoOferta(
				    "dentro Paquete manteca",
				    SevenDaysAgo,
				    randomFloat,
				    300,
				    300,
				    "es la sorpresa dentro manteca"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        // -------------------------------------------
        float randomFloat1 = 100000.0f;
        // -------------------------------------------
        DepUY dep111 = DepUY.Montevideo;
        // -------------------------------------------
        LocalDate currentDate11 = LocalDate.now(); // Get the current date
        LocalDate threeDaysAgo = currentDate11.minusDays(3); // Subtract 3 days
        LocalDate twoDaysAgo = currentDate11.minusDays(2); // Subtract 3 days
        // -------------------------------------------
        EstadoOL nunevoestado = EstadoOL.Confirmada;
        // -------------------------------------------
        DTHora hora12 = new DTHora(8, 0);
        DTHora hora22 = new DTHora(1, 0);
        DTHorario horario2 = new DTHorario(hora12, hora22);
        // -------------------------------------------
        String imagen1 ="hola";
        // crear oferta laboral
        OfertaLaboral OfertaLabolra = null;
		try {
			OfertaLabolra = new OfertaLaboral(
					true,
			        empresaNueva,
			        myList,
			        tipoofertaNuevo,
			        "Panqueqes",
			        "muy esponjosos",
			        "panquequeLandia",
			        dep111,
			        horario2,
			        randomFloat1,
			        threeDaysAgo,
			        nunevoestado,
			        imagen1.getBytes(),
			        NuevoPaq);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		// creo postulante
		String nickname = "Stallone";
		String password = "contraseNaSeguraCreeme";
		String nombre = "Sylvester";
		String apellido = "Stallone";
		String correo = "Sylvester@Rocky.com";
		LocalDate fechaNacimiento = LocalDate.of(1946, 7, 6); // Modify the birthdate accordingly
		String nacionalidad = "American"; // Modify the nationality as needed
		String imagen11 = "llllllllllll";
		Postulante nuevoPos = null;
		try {
			nuevoPos = new Postulante(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad,imagen11.getBytes());
		} catch (ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// --------------------------------------------
		DTUsuario datosUsuario = empresaNueva.obtenerDatosUsuarioEspecial("Hidraulioocs", "Hidraulioocs");
		// --------------------------------------------
		Postulacion nuevaPost = new Postulacion(nuevoPos,"buen curriculum vitae","estoy muy motivado", twoDaysAgo,"Stallone-Oficial", OfertaLabolra , "video impresionante"); 
		// setear postulacion manualmente a la oferta laboral, es decir aggregarla manualmente
		List<Postulacion> PostulacionList = new ArrayList<>();
		PostulacionList.add(nuevaPost);
		OfertaLabolra.setPostulaciones(PostulacionList);
        // ============================================
		DTOfertaExtendidoConKeywordsPostulante datos =  OfertaLabolra.infoOfertaLaboralPost(nickname);
		OfertaLabolra.marcadaFav();
		OfertaLabolra.desmarcadaFav();
		OfertaLabolra.setCantFav(9);
		OfertaLabolra.TienePosicion();
		OfertaLabolra.establecerPosicion("Stallone",1);
		OfertaLabolra.DevolverPosiciones();
		// --------------------------------------------
		List<String> nickList = new ArrayList<>();
		nickList.add("Stallone");
		try {
			empresaNueva.establecerPosicion("Panqueqes",nickList);
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
