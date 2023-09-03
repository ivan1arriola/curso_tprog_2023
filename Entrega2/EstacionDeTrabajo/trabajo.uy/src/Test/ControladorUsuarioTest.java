package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import logica.Fabrica;
import logica.Clases.Postulacion;
import logica.Clases.Postulante;
import logica.Clases.TipoOferta;
import logica.Clases.Keyword;
import logica.Clases.OfertaLaboral;
import logica.Controladores.CtrlOferta;
import logica.Datatypes.DTHorario;
import logica.Datatypes.DTOfertaExtendido;
import logica.Datatypes.DTPaquete;
import logica.Datatypes.DTTipoOferta;
import logica.Datatypes.DTOfertaLaboral;
import logica.Datatypes.DTPostulacion;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;
import logica.Datatypes.DTCantTO;
import logica.Enumerados.DepUY;
import logica.Interfaces.ICtrlOferta;
import logica.Interfaces.ICtrlUsuario;
import logica.Manejadores.OfertaLaboralHandler;
import logica.Manejadores.UsuarioHandler;
import logica.Datatypes.DTHora;
import logica.Datatypes.DTHorario;

public class ControladorUsuarioTest {

	@Test
	void OfertasLaborales() {
		
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		ICtrlOferta ICO = f.getICtrlOferta();
		
		// crea postulante nick1 - Se espera que de de alta correctamente
		try {
			ICU.altaPostulante("nick1", "nick1", "nick1", "nick1@nick.com", LocalDate.now(), "nick1@nick.com");
		} catch (ExceptionUsuarioNickYCorreoRepetidos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			boolean b = ICU.altaEmpresa("Apple Com.", "Steve", "Jobs", "stevejobs1@hotmail.com", "Apple Co.", "Vendemos celulares caros pero buenos.");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LocalDate fecha = LocalDate.of(2001, 8, 9);
		ICO.altaTipoPublicacionOL("Comun", "Una presencia comun.", 1, 19, 5000.0f, LocalDate.now());
		DTHora desde = new DTHora(18,30);
		DTHora hasta = new DTHora(0,0);
		DTHorario horario = new DTHorario(desde,hasta);
		List<String> keys = new ArrayList<>();
		try {
			DTTipoOferta tipoOfer = ICO.obtenerDatosTO("Comun");
			assertEquals(tipoOfer.getDescripcion(), "Una presencia comun.");
			assertEquals(tipoOfer.getExposicion(), 1);
			assertEquals(tipoOfer.getDuracion(), 19);
			assertEquals(tipoOfer.getCosto(), 5000.0f);
			assertEquals(tipoOfer.getFechaAlta(), LocalDate.now());
		} catch (ExcepcionTipoOfertaNoExistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(ICO.listarTipoDePublicaciones().contains("Comun"));
		
		
		try {
			boolean b1 = ICU.altaOfertaLaboral("Apple Com.", "Comun", "Ingenieros", "Un trabajo duro", horario, 90000.0f, "Montevideo", DepUY.Montevideo, fecha,keys);
			DTOfertaExtendido datosOfertaLaboral =  ICU.consultaOfertaLaboral("Ingenieros");
			assertEquals(datosOfertaLaboral.getCiudad(),"Montevideo");
			assertEquals(datosOfertaLaboral.getDepartamento(),DepUY.Montevideo);
			assertEquals(datosOfertaLaboral.getNombre(),"Ingenieros");
			assertEquals(datosOfertaLaboral.getHorario(),horario);
			assertEquals(datosOfertaLaboral.getDescripcion(),"Un trabajo duro");
			assertEquals(datosOfertaLaboral.getRemuneracion(), 90000.0f);
			assertEquals(datosOfertaLaboral.getPostulaciones().size(), 0);
			assertEquals(datosOfertaLaboral.getFechaDeAlta(), fecha);
			assertEquals(datosOfertaLaboral.toString(), "Nombre: " + "Ingenieros" + "\n" + "Descripción: " + "Un trabajo duro" + "\n" + "Fecha de alta: " + fecha + "\n" + "Costo: " + "5000.0" + "\n" + "Remuneración: " + "90000.0" + "\n" + "Horario de Entrada: " + horario.getDesde() + "\n" + "Horario de Salida: " + horario.getHasta() + "\n" + DepUY.Montevideo + "," + "Montevideo");
			
			OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
			OfertaLaboral ol = OLH.buscar("Ingenieros");
			
			ol.setCiudad("Madrid");
			ol.setNombre("Abogados");
			ol.setDescripcion("Se rescatan personas de la carcel.");
			ol.setDepartamento(DepUY.Maldonado);
			DTHora desde1 = new DTHora(2,30);
			DTHora hasta1 = new DTHora(4,50);
			DTHorario horario1 = new DTHorario(desde1,hasta1);
			ol.setHorario(horario1);
			ol.setRemuneracion(10000.0f);
			ol.setFecha_de_alta(LocalDate.of(1922, 3, 4));
			ol.setCosto(1000.0f);
			List<Postulacion> posts = new ArrayList<>();
			ol.setPostulaciones(posts);
			List<Keyword> ks = new ArrayList<>();
			ol.setTipoOferta(null);
			ol.setKeywords(ks);
			
			assertEquals(ol.getCiudad(),"Madrid");
			assertEquals(ol.getDepartamento(),DepUY.Maldonado);
			assertEquals(ol.getNombre(),"Abogados");
			assertEquals(ol.getHorario(),horario1);
			assertEquals(ol.getDescripcion(),"Se rescatan personas de la carcel.");
			assertEquals(ol.getRemuneracion(), 10000.0f);
			assertEquals(ol.getPostulaciones().size(), 0);
			assertEquals(ol.getFecha_de_alta(), LocalDate.of(1922, 3, 4));
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionEmpresaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(ICO.existeOferta("Ingenieros"));
		
		Set<String> ofertaslaborales;
		try {
			ofertaslaborales = ICU.listarOfertasLaborales("Apple Com.");
			assertEquals(1, ofertaslaborales.size());
		} catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Set<String> empresas = ICO.listarEmpresas();
		assertTrue(empresas.contains("Apple Com."));
		
		
		ICO.altaPaqueteOL("PaqueteBomba", "Es muy bueno", 10, LocalDate.now(), 80);
		
		DTPaquete paquete = ICO.obtenerDatosPaquete("PaqueteBomba");
		assertEquals(paquete.getDescripcion(), "Es muy bueno");
		assertEquals(paquete.getNombre(), "PaqueteBomba");
		assertEquals(paquete.getDescuento(), 80);
		assertEquals(paquete.getValidez(), 10);
		assertEquals(paquete.getTiposDePub().size(), 0);
		
		HashSet<String> paquetes = ICO.listarPaquetes();
		
		assertTrue(paquetes.contains("PaqueteBomba"));
		
		ICO.agregarTipoOfertaPaq("PaqueteBomba", "Comun", 100);
		paquete = ICO.obtenerDatosPaquete("PaqueteBomba");
		assertEquals(paquete.getTiposDePub().size(), 1);
		
		
		try {
			boolean b1 = ICU.altaOfertaLaboral("Apple Com.", "Comun", "Feliz", "Un trabajo duro", horario, 90000.0f, "Montevideo", DepUY.Montevideo, fecha,keys);
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionEmpresaInvalida e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean b = ICO.altaPostulacion("Feliz", "nick1", "Soy bueno en todo", "quiero aprender","www.fan.com" ,LocalDate.of(2023, 8, 27));
		
		UsuarioHandler UH = UsuarioHandler.getInstance();
		Postulante postnick1 = (Postulante) UH.buscarNick("nick1");
		assertEquals(postnick1.listarOfertasLaborales().size(), 1);
		
		boolean b2 = ICO.altaPostulacion("Feliz", "nick1", "Soy bueno en todo", "quiero aprender","www.fan.com" ,LocalDate.of(2023, 8, 27));
		
		
		
		assertFalse(b2);
		assertTrue(ICU.existePostulacion("nick1", "Feliz"));
		
        assertThrows(ExcepcionTipoOfertaNoExistente.class, () -> { ICO.obtenerDatosTO("sadasdasd"); });
		
        boolean b3 = ICO.altaKeyword("Trabajo nocturno");
        
        // No se si deberia devolver true o false
        assertFalse(b3);
	}
	
	@Test
	void datosGenerales () {
		
		
		UsuarioHandler UH = UsuarioHandler.getInstance();
		OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
		
		// Busca valores que no deberian estar cargados
		OfertaLaboral ol = OLH.buscar("Feliz"); // se espera un null
		Postulante gaussError = (Postulante) UH.buscarNick("gaussito"); // se espera un null
		
		//Se crea Postulante gaussito
		Postulante gauss = new Postulante("gaussito", "Gauss", "Apellido", "gau@gauss.edu.uy", LocalDate.now(), "al");
		
		Postulacion p = new Postulacion(gauss, "Soy buen matematico", "Tengo mucho interes en forma parte.", LocalDate.of(2000, 4, 27), "gauss.com", ol);
		assertEquals(p.getCV(), "Soy buen matematico");
		assertEquals(p.getMotivacion(), "Tengo mucho interes en forma parte.");
		assertEquals(p.getFecha(), LocalDate.of(2000, 4, 27));
		assertEquals(p.getURLDocExtras(), "gauss.com");
		assertEquals(p.getOfertaLaboral().getNombre(), "Feliz");
		
		p.setCV("Cauchy es mejor");
		p.setMotivacion("Me gusta la trigonometria.");
		p.setFecha(LocalDate.of(1223, 5, 6));
		p.setURLDocExtras("gausstriunafara.com");
		p.setOfertaLaboral(ol);
		
		assertEquals(p.getCV(), "Cauchy es mejor");
		assertEquals(p.getMotivacion(), "Me gusta la trigonometria.");
		assertEquals(p.getFecha(), LocalDate.of(1223, 5, 6));
		assertEquals(p.getURLDocExtras(), "gausstriunafara.com");
		assertEquals(p.getOfertaLaboral().getNombre(), "Feliz");
		
		assertEquals(gauss.listarOfertasLaborales().size(), 0); // No se le asocio ninguna Oferta Laboral, aunque pareciera en 184 pero no.
		
		gauss.setNickname("gaussElGrande");
		gauss.setCorreo_electronico("gauss1@hotmail.com");
		gauss.setFecha_nac(LocalDate.of(1777, 5, 13));
		gauss.setNacionalidad("Polaco");
		
		assertEquals(gauss.getNickname(), "gaussElGrande");
		assertEquals(gauss.getCorreo_electronico(), "gauss1@hotmail.com");
		assertEquals(gauss.getFecha_nac(), LocalDate.of(1777, 5, 13));
		assertEquals(gauss.getNacionalidad(),"Polaco");
		
	}
	
	@Test
	void probarDT() {
		
		
		DTHora desde = new DTHora(12,0);
		DTHora hasta = new DTHora(19,0);
		assertEquals(desde.getHora(),12);
		assertEquals(desde.getMinutos(),0);
		assertEquals(desde.toString(), String.format("%02d:%02d", 12, 0));
		
		DTHorario horario = new DTHorario(desde, hasta);
		assertEquals(horario.getDesde(), desde);
		assertEquals(horario.getHasta(), hasta);
		
		DTOfertaLaboral dtol = new DTOfertaLaboral("McDonalds", "Una cajita feliz", LocalDate.of(1990, 5, 12), 1000.0f, 10000.0f, horario, DepUY.Montevideo, "Montevideo");
		assertEquals(dtol.getNombre(), "McDonalds");
		assertEquals(dtol.getDescripcion(), "Una cajita feliz");
		assertEquals(dtol.getFechaDeAlta(), LocalDate.of(1990, 5, 12));
		assertEquals(dtol.getCosto(), 1000.0f);
		assertEquals(dtol.getRemuneracion(), 10000.0f);
		assertEquals(dtol.getHorario(), horario);
		assertEquals(dtol.getDepartamento(), DepUY.Montevideo);
		assertEquals(dtol.getCiudad(), "Montevideo");
		assertEquals(dtol.toString(), "McDonalds" + " - " + "Una cajita feliz" + "\n" + LocalDate.of(1990, 5, 12) + "\n" + 1000.0f + " - " + 10000.0f + " - " + horario + "\n" + DepUY.Montevideo +","+"Montevideo");
		
		DTPostulacion postulacion = new DTPostulacion("gauss", LocalDate.of(1970,5,18), "postul.com", "Tengo mucha cancha", "Muy motivado");
		assertEquals(postulacion.getPostulante(), "gauss");
		assertEquals(postulacion.getFecha(), LocalDate.of(1970,5,18));
		assertEquals(postulacion.getURLDocExtras(), "postul.com");
		assertEquals(postulacion.getCV(), "Tengo mucha cancha");
		assertEquals(postulacion.getMotivacion(), "Muy motivado");
		
		DTTipoOferta tipoOferta = new DTTipoOferta("Premium", LocalDate.of(2005,3,9), 1000.0f, 10, 5, "Muy buen tipo");
		assertEquals(tipoOferta.getNombre(), "Premium");
		tipoOferta.setNombre("Extra Premium");
		tipoOferta.setFechaAlta(LocalDate.of(2003,3,9));
		tipoOferta.setCosto(2000.0f);
		tipoOferta.setExposicion(50);
		tipoOferta.setDuracion(10);
		tipoOferta.setDescripcion("Muy muy buen tipo");
		
		assertEquals(tipoOferta.getNombre(), "Extra Premium");
		assertEquals(tipoOferta.getFechaAlta(), LocalDate.of(2003,3,9));
		assertEquals(tipoOferta.getCosto(), 2000.0f);
		assertEquals(tipoOferta.getExposicion(), 50);
		assertEquals(tipoOferta.getDuracion(), 10);
		assertEquals(tipoOferta.getDescripcion(), "Muy muy buen tipo");
		DTPostulante dtpo = new DTPostulante("mine", "mine@gmail.com", "main", "matilde", LocalDate.of(1703,1,19), "Español");
		assertEquals(dtpo.getFecha_nac(), LocalDate.of(1703,1,19));
		assertEquals(dtpo.toString(), "Fecha Nacimiento:" + LocalDate.of(1703,1,19) + ", nacionalidad='" + "Español" + '\n');
	
		DTUsuario user = new DTUsuario("polo", "polaquito@gmail.com", "gutierrez", "pedro");
		assertEquals(user.toString(), "polo" + " - " + "pedro" + " " + "gutierrez");
		
		DTCantTO dtco = new DTCantTO("Comun", 10);
		assertEquals(dtco.getNombre(), "Comun");
		assertEquals(dtco.getCantidad(), 10);
		dtco.setNombre("Premium");
		dtco.setCantidad(100);
		assertEquals(dtco.getNombre(), "Premium");
		assertEquals(dtco.getCantidad(), 100);
	}

	
}
