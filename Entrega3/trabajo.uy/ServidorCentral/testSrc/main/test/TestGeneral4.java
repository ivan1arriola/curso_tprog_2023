package main.test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ExcepcionKeywordVacia;
import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionValidezNegativa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Keyword;
import logica.clases.Postulante;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaLaboral;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulante;
import logica.datatypes.DTTipoOferta;
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
        Postulante postulante1 = null;
		try {
			postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // obtuve empresa,   ahora creo DTEmpresa
		// Subtract three days
        LocalDate threeDaysBefore = postulante1.getFechaNac().minusDays(3);
        try {
			ICU.ingresarDatosEditadosPostulante(postulante1.getNickname(),
			        postulante1.getNombre(),
			        postulante1.getApellido(),
			        postulante1.getcorreoElectronico(),
			        postulante1.getcontrasenia(),
			        threeDaysBefore,
			        postulante1.getNacionalidad());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        DTUsuario DTpostulante1 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi", "LeonardoVinchi");
        DTPostulante DTverdaderopostulante1 = (DTPostulante) DTpostulante1; // Casting;

        try {
			ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante1.getNickname(),
			        DTverdaderopostulante1.getNombre(),
			        DTverdaderopostulante1.getApellido(),
			        DTverdaderopostulante1.getcorreoElectronico(),
			        DTverdaderopostulante1.getcontrasenia(),
			        DTverdaderopostulante1.getFechaNac(),
			        DTverdaderopostulante1.getNacionalidad());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String img23 = "url";

        try {
			ICU.ingresarDatosEditadosPostulanteImg(DTverdaderopostulante1.getNickname(),
			        DTverdaderopostulante1.getNombre(),
			        DTverdaderopostulante1.getApellido(),
			        DTverdaderopostulante1.getcorreoElectronico(),
			        DTverdaderopostulante1.getcontrasenia(),
			        img23.getBytes(),
			        DTverdaderopostulante1.getFechaNac(),
			        DTverdaderopostulante1.getNacionalidad());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DTUsuario DTpostulante12 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi", "ASwatzenegger");
        DTPostulante DTverdaderopostulante12 = (DTPostulante) DTpostulante12; // Casting;

        try {
			ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante12.getNickname(),
			        DTverdaderopostulante12.getNombre(),
			        DTverdaderopostulante12.getApellido(),
			        DTverdaderopostulante12.getcorreoElectronico(),
			        DTverdaderopostulante12.getcontrasenia(),
			        DTverdaderopostulante12.getFechaNac(),
			        DTverdaderopostulante12.getNacionalidad());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	

        DTUsuario empresa1 = null;
		try {
			empresa1 = ICU.obtenerDatosUsuario("Kreves");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DTEmpresa DTverdaderaempresa1 = (DTEmpresa) empresa1; // Casting;
        try {
			ICU.ingresarDatosEditadosEmpresa(DTverdaderaempresa1.getNickname(),
			        DTverdaderaempresa1.getNombre(),
			        DTverdaderaempresa1.getApellido(),
			        DTverdaderaempresa1.getcorreoElectronico(),
			        DTverdaderaempresa1.getcontrasenia(),
			        DTverdaderaempresa1.getDescripcion());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ICU.ingresarDatosEditadosEmpresaImg(DTverdaderaempresa1.getNickname(),
			        DTverdaderaempresa1.getNombre(),
			        DTverdaderaempresa1.getApellido(),
			        DTverdaderaempresa1.getcorreoElectronico(),
			        DTverdaderaempresa1.getcontrasenia(),
			        img23.getBytes(),
			        DTverdaderaempresa1.getDescripcion());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ICU.ingresarDatosEditadosEmpresaURLImg(DTverdaderaempresa1.getNickname(),
			        DTverdaderaempresa1.getNombre(),
			        DTverdaderaempresa1.getApellido(),
			        DTverdaderaempresa1.getcorreoElectronico(),
			        DTverdaderaempresa1.getcontrasenia(),
			        DTverdaderaempresa1.getUrl(),
			        img23.getBytes(),
			        DTverdaderaempresa1.getDescripcion());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ICU.ingresarDatosEditadosEmpresaURL(DTverdaderaempresa1.getNickname(),
			        DTverdaderaempresa1.getNombre(),
			        DTverdaderaempresa1.getApellido(),
			        DTverdaderaempresa1.getcorreoElectronico(),
			        DTverdaderaempresa1.getcontrasenia(),
			        DTverdaderaempresa1.getUrl(),
			        DTverdaderaempresa1.getDescripcion());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // si no tiene url no lo toma,   en este caso google tiene url
        try {
			if (!ICU.tieneURL("Google")) {
			    assertFalse("The test for user in system failed", true);
			}
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        // ------------------------- tipo oferta ---------------------------
		//			boolean booleano;
		String img233 = "url";
        ICO.altaTipoPublicacionOL("Oferta normal", "visibilidad normal", 1, 19, 100.0f, LocalDate.now());
        DTTipoOferta tipoOfertaDT = null;
        try {
            tipoOfertaDT = ICO.obtenerDatosTO("Oferta normal");
        } catch (ExcepcionTipoOfertaNoExistente e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tipoOfertaDT.getNombre();
        tipoOfertaDT.getFechaAlta();
        tipoOfertaDT.getCosto();
        tipoOfertaDT.getDuracion();
        tipoOfertaDT.getExposicion();
        tipoOfertaDT.getDescripcion();
//			booleano = 
        ICO.altaTipoPublicacionOL("Oferta destacada", "visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
        ICO.altaTipoPublicacionOL("Oferta super destacada", "visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
        try {
			ICO.altaPaqueteOL("Paquete 1", "un paquete basico", 1, LocalDate.now(), 10.0f, img233.getBytes());
		} catch (ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // los paquetes empiezan vacios,   se les va agregando tipos de oferta

        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta normal", 20);
        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta destacada", 12);
        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta super destacada", 1);

        DTPaquete nuevo = ICO.obtenerDatosPaquete("Paquete 1");
        nuevo.getCosto();
        nuevo.getDescripcion();
        nuevo.getDescuento();
        nuevo.getFechaAlta();
        nuevo.getNombre();
        nuevo.getTiposDePub();
        nuevo.getValidez();
        // coloco todas keywors del sistema
        Set<String> listaKeywords = (HashSet<String>) ICO.listarKeywords();

        DTHora hora1 = new DTHora(8, 0);
        DTHora hora2 = new DTHora(1, 0);
        DTHorario horario = new DTHorario(hora1, hora2);
        String TIPOOFERTASELECCIONADA = "Oferta normal";


        String Nick = "Google";
        String desc = "investigador IA";
        String titulo = "Investigador de IA";
        String ciudad = "montevideo1";
        DepUY departamento = DepUY.Montevideo;
        LocalDate fecha = LocalDate.now();
        float sueldo = 100222.0f;
        String paquete = "Paquete 1";
        EstadoOL estado = EstadoOL.Confirmada;
        ICO.altaOfertaLaboral(Nick,
                TIPOOFERTASELECCIONADA,
                desc,
                titulo,
                horario,
                sueldo,
                ciudad,
                departamento,
                fecha,
                listaKeywords,
                estado,
                img233.getBytes(),
                paquete);

        DTOfertaLaboral temporal3 = new DTOfertaLaboral("Google",
                "investigador IA",
                LocalDate.now(),
                100222.0f,
                100222.0f,
                horario,
                departamento,
                ciudad,
                estado,
                img233.getBytes(),
                0);
        temporal3.getCiudad();
        temporal3.getCosto();
        temporal3.getDepartamento();
        temporal3.getDescripcion();
        temporal3.getestado();
        temporal3.getFechaDeAlta();
        temporal3.getHorario();
        temporal3.getImagen();
        temporal3.getNombre();
        temporal3.getRemuneracion();
        temporal3.toString();
//			Set<String> auxiliar = (HashSet<String>) 
        ICO.listarOfertasLaboralesConfirmadas("Google");
        // obtener nombres de los postulantes
//			Set<String> nombres = (HashSet<String>) 
        ICU.obtenerNicknamesPostulantes();
		// ============================================
        
        entityManager.close();
        entityManagerFactory.close();
	}
}
