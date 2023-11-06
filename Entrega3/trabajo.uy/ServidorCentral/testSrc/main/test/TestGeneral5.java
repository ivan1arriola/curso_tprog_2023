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
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionValidezNegativa;
import excepciones.OfertaLaboralNoEncontrada;
import excepciones.TipoUsuarioNoValido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTCompraPaquetes;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTOfertaExtendidoConKeywords;
import logica.datatypes.DTOfertaExtendidoSinPConK;
import logica.datatypes.DTPostulacion;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral5 {
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
        System.out.println("################## Test 5 ##################");
        // ============================================
        String nombrePostulante = "Juan";
        LocalDate fechaPostulacion = LocalDate.of(2020, 12, 12);
        String URLDocExtras = "www.google.com";
        String CVitae = "CV";
        String motivacion = "motivacion";
        String urlVid = "www.algo";
        DTPostulacion post = new DTPostulacion(nombrePostulante, fechaPostulacion, URLDocExtras, CVitae, motivacion,urlVid);

        // Getters
        post.getPostulante();
        post.getFecha();
        post.getuRLDocExtras();
        post.getcVitae();
        post.getMotivacion();

        String nombreOL = "Administrador Google";
        String desripcion = "puesto importante";
        LocalDate fechaA = LocalDate.of(2020, 12, 12);
        float costo = 1000;
        float remuneracion = 2000;
        DTHora hora1 = new DTHora(8, 0);
        DTHora hora2 = new DTHora(1, 0);
        DTHorario horario = new DTHorario(hora1, hora2);
        // ------------------------------------------
//			int num1 = 
        hora1.getHora();
//			num1 = 
        hora1.getMinutos();
        hora1.toString();
        // ------------------------------------------
        hora1 = horario.getDesde();
        hora1 = horario.getHasta();


        DepUY dep = DepUY.Montevideo;
        String ciudad = "Montevideo";
        EstadoOL estado = EstadoOL.Ingresada;
        Set<DTPostulacion> ColPost = new HashSet<DTPostulacion>();
        ColPost.add(post);
        String img = "url";
        String paquete = "paquete";
        DTOfertaExtendido OfEx = new DTOfertaExtendido("Mc-Donalsd", nombreOL,
                desripcion,
                fechaA,
                costo,
                remuneracion,
                horario,
                dep,
                ciudad,
                estado,
                ColPost,
                img.getBytes(),
                paquete,
                0);
     // Getters
        OfEx.getNombre();
        OfEx.getDescripcion();
        OfEx.getFechaDeAlta();
        OfEx.getCosto();
        OfEx.getRemuneracion();
        OfEx.getHorario();
        OfEx.getDepartamento();
        OfEx.getCiudad();
        OfEx.getEstado();
        OfEx.getCosto();
        OfEx.getImagen();
        OfEx.getPaquete();
        OfEx.toString();
//			Set<DTPostulacion> temp = (HashSet<DTPostulacion>) 
        OfEx.getPostulaciones();

        String nombre = "Asesor";
        String descripcion = "Asesoramiento";
        LocalDate fecha = LocalDate.of(2020, 12, 12);
        float costo1 = 1000;
        float remuneracion1 = 2000;
        DTHora hora11 = new DTHora(8, 0);
        DTHora hora22 = new DTHora(1, 0);
        DTHorario horario1 = new DTHorario(hora11, hora22);
        DepUY dep1 = DepUY.Montevideo;
        String ciudad1 = "Montevideo";
        EstadoOL estado1 = EstadoOL.Ingresada;
        Set<DTPostulacion> ColPost1 = new HashSet<DTPostulacion>();
        ColPost1.add(post);
        String img1 = "url";
        Set<String> pruebaKeyword = new HashSet<>(Arrays.asList(
                "Trabajo nocturno",
                "horario vespertino",
                "full time",
                "part time"
        ));
        DTOfertaExtendidoConKeywords OfEx1 = new DTOfertaExtendidoConKeywords(nombre,
                descripcion,
                fecha,
                costo1,
                remuneracion1,
                horario1,
                dep1,
                ciudad1,
                estado1,
                ColPost1,
                img1.getBytes(),
                pruebaKeyword,
                0);
        // Getters
        OfEx1.getNombre();
        OfEx1.getDescripcion();
        OfEx1.getFechaDeAlta();
        OfEx1.getCosto();
        OfEx1.getRemuneracion();
        OfEx1.getHorario();
        OfEx1.getDepartamento();
        OfEx1.getCiudad();
        OfEx1.getEstado();
        OfEx1.getCosto();
        OfEx1.getImagen();
        OfEx1.toString();
//			Set<DTPostulacion> temp1 = 
        OfEx1.getPostulaciones();
//			Set<String> temp2 = (HashSet<String>) 
        OfEx1.getKeywords();

        // testear DATATYPES Oferta Extendidos
        String nombre1 = "Asesor";
        String descripcion1 = "Asesoramiento";
        LocalDate fechaPostulacion1 = LocalDate.of(2020, 12, 12);
        float costo2 = 1000;
        float remuneracion2 = 2000;
        DTHora hora111 = new DTHora(8, 0);
        DTHora hora222 = new DTHora(1, 0);
        DTHorario horario11 = new DTHorario(hora111, hora222);
        DepUY dep11 = DepUY.Montevideo;
        String ciudad11 = "Montevideo";
        EstadoOL estado11 = EstadoOL.Ingresada;
        Set<DTPostulacion> ColPost11 = new HashSet<DTPostulacion>();
        ColPost11.add(post);
        String img11 = "url";
        Set<String> pruebaKeyword1 = new HashSet<>(Arrays.asList(
                "Trabajo nocturno",
                "horario vespertino",
                "full time",
                "part time"
        ));
        DTOfertaExtendidoSinPConK OfEx11 = new DTOfertaExtendidoSinPConK("empresa", nombre1,
                descripcion1,
                fechaPostulacion1,
                costo2,
                remuneracion2,
                horario11,
                dep11,
                ciudad11,
                estado11,
                img11.getBytes(),
                pruebaKeyword1,0);
        // Getters
        OfEx11.getNombre();
        OfEx11.getDescripcion();
        OfEx11.getCosto();
        OfEx11.getRemuneracion();
        OfEx11.getHorario();
        OfEx11.getDepartamento();
        OfEx11.getCiudad();
        OfEx11.getEstado();
        OfEx11.getCosto();
        OfEx11.getImagen();
        OfEx11.toString();
        OfEx11.getFechaAlta();
//			Set<String> temp22 = (HashSet<String>) 
        OfEx11.getKeywords();

        // DTCompraPaquete
        String cualquiera = "nombvre";
        LocalDate fechaA28 = LocalDate.of(2020, 12, 12);
        DTCompraPaquetes reciboPaquete = new DTCompraPaquetes(cualquiera, fechaA28, fechaA28);
        reciboPaquete.getNombre();
        reciboPaquete.getFechaCompra();
        reciboPaquete.getFechaVencimiento();

		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
