package main.test;



import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
//import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.AsignarOrdenAOfertaFinalizada;
import excepciones.AsignarOrdenAOfertaNoVencida;
import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCiudadInvalida;
//import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
//import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
import excepciones.FinalizarOfertaNoVencida;
import excepciones.FinalizarOfertaYaFinalizada;
import excepciones.NoHayOrdenDefinidoDePostulantes;
import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.InfoCompra;
//import logica.clases.InfoCompra;
//import logica.clases.InfoCompraOferta;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.clases.OfertaPaquete;
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.clases.TipoOferta;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTCantTO;
//import logica.datatypes.DTCantTO;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
//import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
//import logica.datatypes.DTUsuario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral11 {
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
        System.out.println("################## Test 11 ##################");
        // ============================================
        // bug de keyword por lo menos una
        // 88888888888888888888888888888888888888888888888888888888888888888888888
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
				    "Manwqeuete",    
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
				    "Hidraqwerqweroocs",    
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
        
        KeywordHandler KWord = KeywordHandler.getInstance();
        String keywordToObtain = "FABULOSO TODO BRILLA";
        Keyword utopicKeyword = null;
		try {
			utopicKeyword = new Keyword(keywordToObtain);
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myList.add(utopicKeyword);
		utopicKeyword.getNombre();
		utopicKeyword.setNombre(keywordToObtain);
		KWord.agregar(utopicKeyword);
		KWord.existe(keywordToObtain);
		
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
		
		// ======================================================================
		// paquete ---> oferta paquete ---> TipoOferta
		OfertaPaquete OfertaPaqueteNueva = null;
		try {
			OfertaPaqueteNueva = new OfertaPaquete(tipoofertaNuevo,    44);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<OfertaPaquete> OPer = new HashSet<>();
		OPer.add(OfertaPaqueteNueva);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TOH.agregar(tipoofertaNuevo);
		NuevoPaq.setOfertaPaquete(OPer);
		
		
		// ======================================================================
        float randomFloat1 = 100.0f;
        // -------------------------------------------
        DepUY dep111 = DepUY.Montevideo;
        // -------------------------------------------
        LocalDate currentDate11 = LocalDate.now(); // Get the current date
        LocalDate threeDaysAgo = currentDate11.minusDays(3); // Subtract 3 days
        LocalDate twoDaysAgo = currentDate11.minusDays(2); // Subtract 3 days
        // -------------------------------------------
        EstadoOL nunevoestado = EstadoOL.Confirmada;
        // -------------------------------------------
        DTHora hora12 = new DTHora(8,     0);
        DTHora hora22 = new DTHora(1,     0);
        DTHorario horario2 = new DTHorario(hora12,     hora22);
        // -------------------------------------------
        String imagen1 ="hola";
        // crear oferta laboral
        OfertaLaboral OfertaLabolra = null;
        
        try {
			OfertaLabolra = new OfertaLaboral(
						empresaNueva,    
				        myList,    
				        tipoofertaNuevo,    
				        "Panawfewefawfwafes",    
				        "muy esponjosos",    
				        "panquequeLandia",    
				        dep111,    
				        horario2,    
				        randomFloat1,    
				        threeDaysAgo,    
				        nunevoestado,    
				        imagen1.getBytes(),    
				        NuevoPaq
				);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionCostoPaqueteNoNegativo
				| ExceptionDescuentoInvalido | ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      
	
		
		// creo postulante
		String nickname = "Stallonjhjklhjke";
		String password = "contraseNaSeguraCreeme";
		String nombre = "Sylvester";
		String apellido = "Stallone";
		String correo = "Sylvester@Rocky.com";
		LocalDate fechaNacimiento = LocalDate.of(1946,     7,     6); // Modify the birthdate accordingly
		String nacionalidad = "American"; // Modify the nationality as needed
		String imagen11 = "llllllllllll";
		Postulante nuevoPos = null;
		try {
			nuevoPos = new Postulante(nickname,     password,     nombre,     apellido,     correo,     fechaNacimiento,     nacionalidad,    imagen11.getBytes());
		} catch (ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Postulacion nuevaPost = new Postulacion(nuevoPos,    
				"buen curriculum vitae",    
				"estoy muy motivado",     
				twoDaysAgo,    
				"Stallone-Oficial",     
				OfertaLabolra ,     
				"video impresionante"); 
		// setear postulacion manualmente a la oferta laboral,     es decir aggregarla manualmente
		List<Postulacion> PostulacionList = new ArrayList<>();
		PostulacionList.add(nuevaPost);
		OfertaLabolra.setPostulaciones(PostulacionList);
		
		// IIIIIIIIIIIIIIIIIIIIIIIIIIIII HANDLERS IIIIIIIIIIIIIIIIIIIIIIIIIIIII
		UsuarioHandler UHan = UsuarioHandler.getInstance();
		try {
			UHan.agregar(nuevoPos);
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UHan.agregar(empresaNueva);
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OLH.agregar(OfertaLabolra);
		if (!OLH.existe(OfertaLabolra.getNombre())) {
            System.out.println("%%%%%%%%%%***********************************%%%%%%%%%%");
        }
		entityManager.close();
		entityManagerFactory.close();
        //-------------------
        entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUy");
        entityManager = entityManagerFactory.createEntityManager();
        // Set the EntityManager for the handlers
        KeywordHandler.setBaseDatos(entityManager);
        OfertaLaboralHandler.setBaseDatos(entityManager);
        PaqueteHandler.setBaseDatos(entityManager);
        TipoOfertaHandler.setBaseDatos(entityManager);
        UsuarioHandler.setBaseDatos(entityManager);
        
        // 88888888888888888888888888888888888888888888888888888888888888888888888
		
		
		List<String> nickList = new ArrayList<>();
		nickList.add("Stallonjhjklhjke");
		
		
		
		try {
			ICO.listarPaquetesNoVencidos(empresaNueva.getNickname());
		} catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// 00000000000000000000000000000000000000000000
		try {
			ICO.listarComprasPaquete(empresaNueva.getNickname());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICO.finalizarOfertaLaboral(OfertaLabolra.getNombre());
		} catch (OfertaLaboralNoEncontrada | FinalizarOfertaNoVencida | FinalizarOfertaYaFinalizada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICO.aumentarVisita(OfertaLabolra.getNombre());
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICO.tipoOferta(OfertaLabolra.getNombre());
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		nuevoPos.listarOfertasLaborales();
		nuevoPos.listarPostulaciones();
		Set<Postulacion> PostulacionesUsuario = new HashSet<>();
		PostulacionesUsuario.add(nuevaPost);
		nuevoPos.setPostulaciones(PostulacionesUsuario);
		nuevoPos.editarPostulacion(OfertaLabolra.getNombre(),   "nuevo curriculum",   "nueva motivacion");
		// ============================================
		nuevoPos.marcarFavorita(OfertaLabolra);
		nuevoPos.desmarcarFavorita(OfertaLabolra);
		Set<OfertaLaboral> OfertaLaborales = new HashSet<>();
		OfertaLaborales.add(OfertaLabolra);
		nuevoPos.setOfertasFavoritas(OfertaLaborales);
		nuevoPos.existePostulacion(OfertaLabolra.getNombre());
		// ????????????????????????????????????????????
		Postulante nuevoPos2 = null;
		try {
			nuevoPos2 = new Postulante("johnn",     password,     nombre,     apellido,     correo,     fechaNacimiento,     nacionalidad,    imagen11.getBytes());
		} catch (ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			nuevoPos2.crearPostulacionForzado("curiculum flamante",    
					"estyoy pereparado",    
					LocalDate.now(),    
					"documentos extras",    
					OfertaLabolra,    
					"el video imperdibvle");
		} catch (ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nuevoPos2.seguirUsuario(nuevoPos);
		nuevoPos.seguirUsuario(nuevoPos2);
		
		nuevoPos.obtenerDatosUsuario();
		nuevoPos.obtenerDatosUsuarioEspecial(nuevoPos.getNickname(),   nuevoPos.getNickname()); 
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		empresaNueva.seguirUsuario(nuevoPos);
		nuevoPos.seguirUsuario(empresaNueva);
		empresaNueva.obtenerDatosUsuarioEspecial(empresaNueva.getNickname(),   empresaNueva.getNickname());
		empresaNueva.obtenerDatosUsuario();
		// //////////////////////////////////////////////
		UHan.obtenerCorreo();
		// ============================================
		OfertaLabolra.getDTO();
		try {
			OfertaLabolra.setCosto(-3.14f);
		} catch (ExceptionCostoPaqueteNoNegativo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OfertaLabolra.setRemuneracion(-3.14f);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OfertaLabolra.setCiudad("@@@@");
		} catch (ExceptionCiudadInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.setFechaAlta(LocalDate.of(1547, 9, 29));
		} catch (ExceptionPaqueteNoVigente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.establecerPosicion(nickList);
		} catch (AsignarOrdenAOfertaFinalizada | AsignarOrdenAOfertaNoVencida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.getOrdenPostulantes();
		} catch (NoHayOrdenDefinidoDePostulantes e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OfertaLabolra.descartarOrden();
		
		try {
			OfertaLabolra.finalizarOferta();
		} catch (FinalizarOfertaNoVencida | FinalizarOfertaYaFinalizada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			empresaNueva.altaOfertaLaboral(tipoofertaNuevo,"asdoalnlmo","a",horario2,3.14f,"a",dep111,currentDate,myList,nunevoestado,"sert".getBytes(), NuevoPaq);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionRemuneracionOfertaLaboralNegativa
				| ExceptionPaqueteNoVigente | ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa
				| ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		DTCantTO nuevopaquete = new DTCantTO(tipoofertaNuevo.getNombre(),   23);
		Set<DTCantTO> conjuntoS = new HashSet<>();
		conjuntoS.add(nuevopaquete);
		InfoCompra aux = null;
		try {
			aux = new InfoCompra(currentDate,  3.14f, NuevoPaq, empresaNueva, conjuntoS);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa | ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<InfoCompra> conjuntoq = new HashSet<>();
		conjuntoq.add(aux);
		empresaNueva.setInofCompra(conjuntoq);
		
		empresaNueva.obtenerCantPaquetesEmpresa();
		
		try {
			empresaNueva.encontrarOfertaPorNombre("asdoalnlmo");
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nuevaPost.getClasificacion();
		nuevaPost.esPostulacion(nombre);
		nuevaPost.getCV();
		nuevaPost.getFecha();
		nuevaPost.getFechaResu();
		nuevaPost.getMotivacion();
		nuevaPost.getOfertaLaboral();
		nuevaPost.getPostulante();
		nuevaPost.getuRLDocExtras();
		nuevaPost.getuRLVideo();
		nuevaPost.obtenerDT();
		nuevaPost.setFecha(fechaNacimiento);
		nuevaPost.setCV(imagen11);
		nuevaPost.setFechaResu(imagen11);
		nuevaPost.setMotivacion(imagen11);
		nuevaPost.setuRLDocExtras(imagen11);
		nuevaPost.seturlVideo(nacionalidad);
		
		
		try {
			NuevoPaq.setCosto(randomFloat1);
		} catch (ExceptionCostoPaqueteNoNegativo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NuevoPaq.setDescripcion(imagen11);
		try {
			NuevoPaq.setDescuento(randomFloat1);
		} catch (ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NuevoPaq.setImagen(imagen11.getBytes());
		NuevoPaq.setNombre(nombre);
		try {
			NuevoPaq.setValidez(90);
		} catch (ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NuevoPaq.getCosto();
		NuevoPaq.getDescripcion();
		NuevoPaq.getDescuento();
		NuevoPaq.getDTPaquete();
		NuevoPaq.getfechaAlta();
		NuevoPaq.getImagen();
		NuevoPaq.getInfoCompra();
		NuevoPaq.getNombre();
		
		
		// --------------------------------------------
		
		OfertaLabolra.setEstado(nunevoestado);
		
		TipoOferta a = OfertaLabolra.getTipoOferta();
		try {
			a.setDuracion(1);
		} catch (ExceptionDuracionNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			OfertaLabolra.setFechaAlta(LocalDate.of(1, 9, 29));
		} catch (ExceptionPaqueteNoVigente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aaaaaaaa"+OfertaLabolra.estaVencida());
		OLH.actualizar(OfertaLabolra);
		try {
			ICO.finalizarOfertaLaboral(OfertaLabolra.getNombre());
		} catch (OfertaLaboralNoEncontrada | FinalizarOfertaNoVencida | FinalizarOfertaYaFinalizada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
