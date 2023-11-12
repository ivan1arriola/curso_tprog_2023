package main.test;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
//import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
//import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.NoExistePaquete;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
//import logica.clases.Keyword;
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


public class TestGeneral8 {
	@Test
	public void test1() {
		Fabrica fabri = Fabrica.getInstance();
        ICtrlUsuario ICU = fabri.getICtrlUsuario();
        ICtrlOferta ctrlOferta = fabri.getICtrlOferta();
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
        System.out.println("################## Test 8 ##################");
        // ============================================
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,    9,    2);

        boolean resultado = ctrlOferta.altaTipoPublicacionOL(nombre,    descripcion,    exposicion,    duracion,    costo,    fechaAlta);

        assertTrue(resultado,    "El alta debería ser exitosa");
        
        // Caso donde el nombre está vacío
        String nombre1 = "";
        String descripcion1 = "Descripción del tipo de publicación";
        int exposicion1 = 3;
        int duracion1 = 7;
        float costo1 = 100.0f;
        LocalDate fechaAlta1 = LocalDate.of(2023,    9,    2);

        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre1,    descripcion1,    exposicion1,    duracion1,    costo1,    fechaAlta1);
        });
        // Caso donde la descripción está vacía
        String nombre2 = "NombreTipoPublicacion";
        String descripcion2 = "";
        int exposicion2 = 3;
        int duracion2 = 7;
        float costo2 = 100.0f;
        LocalDate fechaAlta2 = LocalDate.of(2023,    9,    2);

        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre2,    descripcion2,    exposicion2,    duracion2,    costo2,    fechaAlta2);
        });
        
     // Caso donde la exposición es menor que 1
        String nombre3 = "NombreTipoPublicacion";
        String descripcion3 = "Descripción del tipo de publicación";
        int exposicion3 = 0;
        int duracion3 = 7;
        float costo3 = 100.0f;
        LocalDate fechaAlta3 = LocalDate.of(2023,    9,    2);

        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre3,    descripcion3,    exposicion3,    duracion3,    costo3,    fechaAlta3);
        });

     // Caso donde el costo es negativo
        String nombre4 = "NombreTipoPublicacion";
        String descripcion4 = "Descripción del tipo de publicación";
        int exposicion4 = 3;
        int duracion4 = 7;
        float costo4 = -10.0f;
        LocalDate fechaAlta4 = LocalDate.of(2023,    9,    2);

        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre4,    descripcion4,    exposicion4,    duracion4,    costo4,    fechaAlta4);
        });
        
     // Caso donde la fecha de alta es nula
        String nombre5 = "NombreTipoPublicacion";
        String descripcion5 = "Descripción del tipo de publicación";
        int exposicion5 = 3;
        int duracion5 = 7;
        float costo5 = 100.0f;
        LocalDate fechaAlta5 = null;

        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre5,    descripcion5,    exposicion5,    duracion5,    costo5,    fechaAlta5);
        });
        
     // Caso donde ya existe una oferta con el mismo nombre
        String nombre6 = "NombreExistente";
        String descripcion6 = "Descripción del tipo de publicación";
        int exposicion6 = 3;
        int duracion6 = 7;
        float costo6 = 100.0f;
        LocalDate fechaAlta6 = LocalDate.of(2023,    9,    2);

        // Agregar una oferta con el mismo nombre
        boolean resultado1 = ctrlOferta.altaTipoPublicacionOL(nombre6,    descripcion6,    exposicion6,    duracion6,    costo6,    fechaAlta6);
       

        // Intentar agregar otra oferta con el mismo nombre debería lanzar una excepción
        assertThrows(IllegalArgumentException.class,    () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,    descripcion,    exposicion,    duracion,    costo,    fechaAlta);
        });
        

        assertTrue(resultado1,    "El alta debería ser exitosa para la primera oferta");
        
        // ------------------------------------------------------------------------------------------
        

        String empresaN = "Apple Com.";
        try {
            try {
				ICU.altaEmpresa("Apple Com.",    "Steve",    "Jobs",    "stevejobs1@hotmail.com",    "Apple Co.",    "Vendemos celulares caros pero buenos.");
			} catch (ErrorAgregarUsuario e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
                 | ExceptionUsuarioNickRepetido e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String tipoO = "NombreTipoPublicacion";
        String nombreOferta = "Ofertita";
        String desc = "que te importa";
        LocalDate fechaAlta123 = LocalDate.of(2023,    9,    2);

        DTHora horaDesde = new DTHora(9,    0);
        DTHora horaHasta = new DTHora(17,    30);
        DTHorario horario = new DTHorario(horaDesde,    horaHasta);
        float remun = 500;
        String ciu = "mdeo";
        DepUY depto = DepUY.Salto;
        Set<String> keys = new HashSet<>();


        // Agregar una oferta utilizando la función altaTipoPublicacionOL
        //boolean resultadoAlta = ctrlOferta.altaTipoPublicacionOL(nombreOferta,     descripcion,     exposicion,     duracion,     costo,     fechaAlta);

        String img233 = "url";


        boolean resultadoAlta = false;
		try {
			resultadoAlta = ctrlOferta.altaOfertaLaboral(empresaN,   
			        tipoO,   
			        nombreOferta,   
			        desc,   
			        horario,   
			        remun,   
			        ciu,   
			        depto,   
			        fechaAlta,   
			        keys,   
			        EstadoOL.Confirmada,   
			        img233.getBytes(),   
			        null);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionUsuarioNoEncontrado | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        // Verificar que el alta haya sido exitosa
        assertTrue(resultadoAlta,    "El alta de la oferta debería ser exitosa");

        // Verificar que la oferta exista utilizando existeOferta
        boolean resultado1123 = ctrlOferta.existeOferta(nombreOferta);

        assertTrue(resultado1123,    "La oferta debería existir");
        
        String nombreOferta222 = "OfertaNoExistente";

        // Verificar que la oferta no exista utilizando existeOferta
        boolean resultado1123123 = ctrlOferta.existeOferta(nombreOferta222);

        assertFalse(resultado1123123,    "La oferta no debería existir");
        
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
