package main.test;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.AsignarOrdenAOfertaFinalizada;
import excepciones.AsignarOrdenAOfertaNoVencida;
import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
import excepciones.FinalizarOfertaNoVencida;
import excepciones.FinalizarOfertaYaFinalizada;
import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.InfoCompra;
import logica.clases.InfoCompraOferta;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.clases.OfertaPaquete;
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.clases.TipoOferta;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTCantTO;
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
            
		Paquete NuevoPaq2 = null;
		try {
			NuevoPaq2 = new  Paquete(
				    "Manteca Paquete",   
				    "cremosa como nunca",   
				    900,   
				    SixDaysAgo,   
				    -3452.0f,   
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
        Map<String,    Keyword> nuevaKeyword = KeywordHandler.obtener();
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
		
		TipoOferta tipoofertaNuevo2 = null;
		try {
			tipoofertaNuevo2 = new TipoOferta(
				    "2222222 dentro Paquete manteca",   
				    SevenDaysAgo,   
				    3452.0f,   
				    300,   
				    300,   
				    "es la sorpresa dentro manteca 22222222"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		TipoOferta tipoofertaNuevo24 = null;
		try {
			tipoofertaNuevo24 = new TipoOferta(
				    "2222222 dentro Paquete manteca",   
				    SevenDaysAgo,   
				    -3452.0f,   
				    300,   
				    300,   
				    "es la sorpresa dentro manteca 22222222"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		TipoOferta tipoofertaNuevo244 = null;
		try {
			tipoofertaNuevo244 = new TipoOferta(
				    "2222222 dentro Paquete manteca",   
				    SevenDaysAgo,   
				    3452.0f,   
				    -300,   
				    300,   
				    "es la sorpresa dentro manteca 22222222"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		TipoOferta tipoofertaNuevo2454 = null;
		try {
			tipoofertaNuevo2454 = new TipoOferta(
				    "2222222 dentro Paquete manteca",   
				    SevenDaysAgo,   
				    3452.0f,   
				    300,   
				    -300,   
				    "es la sorpresa dentro manteca 22222222"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// ======================================================================
		// paquete ---> oferta paquete ---> TipoOferta
		OfertaPaquete OfertaPaqueteNueva = null;
		try {
			OfertaPaqueteNueva = new OfertaPaquete(tipoofertaNuevo,   44);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OfertaPaquete OfertaPaqueteNueva2 = null;
		try {
			OfertaPaqueteNueva2= new OfertaPaquete(tipoofertaNuevo,   -1);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<OfertaPaquete> OLA = new HashSet<>();
		OLA.add(OfertaPaqueteNueva);
		TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
		TOH.agregar(tipoofertaNuevo);
		NuevoPaq.setOfertaPaquete(OLA);
		OfertaPaqueteNueva.getDTCantTO();
		
		// ======================================================================
		        
		
        // -------------------------------------------
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
        DTHora hora12 = new DTHora(8,    0);
        DTHora hora22 = new DTHora(1,    0);
        DTHorario horario2 = new DTHorario(hora12,    hora22);
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
		LocalDate fechaNacimiento = LocalDate.of(1946,    7,    6); // Modify the birthdate accordingly
		String nacionalidad = "American"; // Modify the nationality as needed
		String imagen11 = "llllllllllll";
		Postulante nuevoPos = null;
		try {
			nuevoPos = new Postulante(nickname,    password,    nombre,    apellido,    correo,    fechaNacimiento,    nacionalidad,   imagen11.getBytes());
		} catch (ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsuarioHandler UHan = UsuarioHandler.getInstance();
		try {
			UHan.agregar(nuevoPos);
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ===============================================
		// Create a Postulante with the name "Bruce Willis1"
		String nickname5 = "Willis1";
		String password5= "securePassword123";
		String nombre5 = "Bruce";
		String apellido5 = "Willis1";
		String correo5 = "Bruce@DieHard.com";
		LocalDate fechaNacimiento52 = LocalDate.of(1975, 10, 31); // Modify the birthdate accordingly
		String nacionalidad5 = "American"; // Modify the nationality as needed
		String imagen115 = "llllllllllll";
		Postulante nuevoPos5 = null;
		try {
		    nuevoPos5 = new Postulante(nickname5,   password5,   nombre5,   apellido5,   correo5,   fechaNacimiento52,   nacionalidad5,   imagen115.getBytes());
		} catch (ExceptionFechaInvalida e) {
		    e.printStackTrace();
		}
		try {
			UHan.agregar(nuevoPos5);
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICU.seguirUsuario("Willis1",   "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ICU.dejarDeseguirUsuario("Willis1",  "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			ICU.seguirUsuario("Willis1",   "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			ICU.obtenerSeguidoresUsuario("Stallone");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			ICU.obtenerSeguidosUsuario("Willis1");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		LocalDate fechaNacimiento5 = LocalDate.of(1955,   3,   19); // Modify the birthdate accordingly
		try {
		    nuevoPos5 = new Postulante(nickname5,   password5,   nombre5,   apellido5,   correo5,   fechaNacimiento5,   nacionalidad5,   imagen115.getBytes());
		} catch (ExceptionFechaInvalida e) {
		    e.printStackTrace();
		}
//		obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual,   String UsuarioQueSeHaceConsulta)
		// --------------------------------------------
		DTUsuario datosUsuario = empresaNueva.obtenerDatosUsuarioEspecial("Hidraulioocs",    "Hidraulioocs");
		// --------------------------------------------
		Postulacion nuevaPost = new Postulacion(nuevoPos,   "buen curriculum vitae",   "estoy muy motivado",    twoDaysAgo,   "Stallone-Oficial",    OfertaLabolra ,    "video impresionante"); 
		// setear postulacion manualmente a la oferta laboral,    es decir aggregarla manualmente
		List<Postulacion> PostulacionList = new ArrayList<>();
		PostulacionList.add(nuevaPost);
		OfertaLabolra.setPostulaciones(PostulacionList);
        // ============================================
		DTOfertaExtendidoConKeywordsPostulante datos =  OfertaLabolra.infoOfertaLaboralPost(nickname);
		OfertaLabolra.marcadaFav();
		OfertaLabolra.desmarcadaFav();
		OfertaLabolra.setCantFav(9);
		// --------------------------------------------
		List<String> nickList = new ArrayList<>();
		nickList.add("Stallone");
		try {
			OfertaLabolra.establecerPosicion(nickList);
		} catch (AsignarOrdenAOfertaFinalizada | AsignarOrdenAOfertaNoVencida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICO.establecerPosiciones(OfertaLabolra.getNombre(),nickList);
		} catch (ExceptionUsuarioNoEncontrado | OfertaLaboralNoEncontrada | AsignarOrdenAOfertaFinalizada
				| AsignarOrdenAOfertaNoVencida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ============================================
		TipoOferta tipoofertaNuevo3= null;
		try {
			tipoofertaNuevo3 = new TipoOferta(
				    "dentro Paquete manteca version 2",   
				    SevenDaysAgo,   
				    randomFloat,   
				    300,   
				    300,   
				    "mitad calorias,    mismo sabor"
				);
		} catch (ExceptionCostoPaqueteNoNegativo | ExceptionDuracionNegativa | ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			OfertaLabolra.setTipoOferta(tipoofertaNuevo3);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		OfertaLaboral OfertaLabolra2 = null;
		
		try {
			OfertaLabolra2 = empresaNueva.altaOfertaLaboralImagenPaquete(
				tipoofertaNuevo,   
			    "matambre",   
			    "a la leche",   
			    horario2,   
			    randomFloat1,   
			    "MuUruguay",   
			    dep111,   
			    threeDaysAgo,   
			    myList,   
			    nunevoestado,   
			    "qlqllqlqe".getBytes(),   
			    NuevoPaq
			);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		OfertaLaboral OfertaLabolra222 = null;
		
		try {
			OfertaLabolra222 = empresaNueva.altaOfertaLaboralImagenPaquete(
				tipoofertaNuevo,   
			    "matambre",   
			    "a la leche",   
			    horario2,   
			    -randomFloat1,   
			    "MuUruguay",   
			    dep111,   
			    threeDaysAgo,   
			    myList,   
			    nunevoestado,   
			    "qlqllqlqe".getBytes(),   
			    NuevoPaq
			);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		empresaNueva.listarOfertasLaboralesNoVigentesConfirmadas();
		OfertaLaboral OfertaLabolra3 = null;
		try {
			OfertaLabolra3 = empresaNueva.altaOfertaLaboralImagen(
					tipoofertaNuevo,   
				    "asado de tira",   
				    "a punto",   
				    horario2,   
				    randomFloat1,   
				    "MuUruguay",   
				    dep111,   
				    threeDaysAgo,   
				    myList,   
				    nunevoestado,   
				    "qlqllqlqe".getBytes()
				);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		OfertaLaboral OfertaLabolra4 = null;
		try {
			OfertaLabolra4 = empresaNueva.altaOfertaLaboralForzado(
					tipoofertaNuevo,   
				    "anana con piza",   
				    "algo sobrenatural",   
				    horario2,   
				    randomFloat1,   
				    "MuUruguay",   
				    dep111,   
				    threeDaysAgo,   
				    myList,   
				    nunevoestado,   
				    "qlqllqlqe".getBytes(),   
				    NuevoPaq
				);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		Set<OfertaLaboral> OL = new HashSet<>();
		OL.add(OfertaLabolra);
		empresaNueva.setofertasLaborales(OL);
		empresaNueva.obtenerPostulacionesOfertaLaboral("Panqueqes");
		// ============================================
		try {
			OfertaLabolra.setPaquete(NuevoPaq);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.setFechaAlta(twoDaysAgo);
		} catch (ExceptionPaqueteNoVigente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.setCosto(randomFloat);
		} catch (ExceptionCostoPaqueteNoNegativo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.setRemuneracion(randomFloat);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OfertaLabolra.setCiudad("aaaaaaaaaaaaaaaaaaA");
		} catch (ExceptionCiudadInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OfertaLabolra.obtenerDatosPostulacion(empresaNueva.getNickname());
		// ============================================
		empresaNueva.listarPaquetesNoVencidos();
		// ============================================
		ICO.listarOfertasLaboralesConfirmadasYNoVencidasString();
		ICO.listarOfertasLaboralesConfirmadasYNoVencidas();
		try {
			UHan.agregar(nuevoPos);
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		try {
			ICU.seguirUsuario("Willis1",   "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICU.dejarDeseguirUsuario("Willis1",  "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICU.seguirUsuario("Willis1",   "Stallone");
		} catch (ExceptionUsuarioSeSigueASiMismo | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICU.obtenerSeguidoresUsuario("Stallone");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICU.obtenerSeguidosUsuario("Willis1");
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		OLH.agregar(OfertaLabolra2);
		OLH.agregar(OfertaLabolra);
		try {
			ICO.finalizarOfertaLaboral(OfertaLabolra2.getNombre());
		} catch (OfertaLaboralNoEncontrada | FinalizarOfertaNoVencida | FinalizarOfertaYaFinalizada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICO.marcarFavorita(nuevoPos.getNickname(),   OfertaLabolra2.getNombre());
		} catch (ExceptionUsuarioNoEncontrado | OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICO.desmarcarFavorita(nuevoPos.getNickname(),   OfertaLabolra2.getNombre());
		} catch (ExceptionUsuarioNoEncontrado | OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ICO.listarComprasPaquete(empresaNueva.getNickname());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ============================================
		try {
			OfertaLabolra2.registrarPostulacionForzado(nuevaPost);
		} catch (ExceptionFechaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OfertaLabolra2.setCantVisitas(90);
		DepUY dep4346534 = DepUY.Colonia;
		OfertaLabolra2.setDepartamento(dep4346534);
		OfertaLabolra2.setDescripcion("LALALALALAL");
		OfertaLabolra2.setEmpresaPublicadora(empresaNueva);
		OfertaLabolra2.setId(OfertaLabolra2.getId());
		OfertaLabolra2.setKeywords(myList);
		OfertaLabolra2.setNombre(nombre);
		// ============================================
		
		// paquete ---> oferta paquete ---> TipoOferta
				OfertaPaquete OfertaPaqueteNueva21 = null;
				try {
					OfertaPaqueteNueva21 = new OfertaPaquete(tipoofertaNuevo2,   0);
				} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OLA.add(OfertaPaqueteNueva21);
//				TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
				TOH.agregar(tipoofertaNuevo2);
				NuevoPaq.setOfertaPaquete(OLA);
				OfertaPaqueteNueva.getDTCantTO();
		
		OfertaLaboral OfertaLabolra12344 = null;
		try {
			OfertaLabolra12344 = new OfertaLaboral(
					true,   
			        empresaNueva,   
			        myList,   
			        tipoofertaNuevo2,   
			        "Panqueqes",   
			        "muy esponjosos",   
			        "panquequeLandia",   
			        dep111,   
			        horario2,   
			        -3452.0f,   
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
		InfoCompra nuevaCompra = null;
		try {
			nuevaCompra = new InfoCompra(threeDaysAgo,   -3452.0f,   NuevoPaq,   empresaNueva,  null);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa | ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============================================
		try {
			ICU.listarOfertasLaboralesNoVigentesConfirmadas(empresaNueva.getNickname());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ========
		InfoCompraOferta InfoCompraNuevo = null;
		try {
			InfoCompraNuevo = new InfoCompraOferta(tipoofertaNuevo,   20);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DTCantTO nuevopaquete = new DTCantTO(tipoofertaNuevo.getNombre(),  23);
		Set<DTCantTO> conjuntoS = new HashSet<>();
		conjuntoS.add(nuevopaquete);
		try {
			nuevaCompra = new InfoCompra(threeDaysAgo,   3452.0f,   NuevoPaq,   empresaNueva,  conjuntoS);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa | ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ICU.obtenerFechaDeCompra(empresaNueva.getNickname(),   NuevoPaq.getNombre());
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// modificar lo de arriba si es necesario
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
