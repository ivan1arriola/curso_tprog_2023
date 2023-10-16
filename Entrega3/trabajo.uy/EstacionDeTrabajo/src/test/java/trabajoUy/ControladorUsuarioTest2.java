package trabajoUy;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;


import trabajoUy.logica.clases.Postulante;

import trabajoUy.logica.datatypes.DTEmpresa;
import trabajoUy.logica.datatypes.DTHora;
import trabajoUy.logica.datatypes.DTHorario;
import trabajoUy.logica.datatypes.DTOfertaLaboral;
import trabajoUy.logica.datatypes.DTPaquete;
import trabajoUy.logica.datatypes.DTPostulante;
import trabajoUy.logica.datatypes.DTTipoOferta;
import trabajoUy.logica.datatypes.DTUsuario;
import trabajoUy.logica.enumerados.DepUY;
import trabajoUy.logica.enumerados.EstadoOL;
import trabajoUy.logica.interfaces.ICtrlOferta;
import trabajoUy.logica.interfaces.ICtrlUsuario;
import trabajoUy.logica.manejadores.UsuarioHandler;
import trabajoUy.logica.utils.Fabrica;
import trabajoUy.excepciones.ExcepcionTipoOfertaNoExistente;

import java.util.Arrays;

public class ControladorUsuarioTest2 {
	
	// --------------- Testeo Keyowords ---------------
	@Test
	void keywordTest() {
		Fabrica fabri = Fabrica.getInstance();
		ICtrlUsuario ICU = fabri.getICtrlUsuario();
		ICtrlOferta ICO = fabri.getICtrlOferta();

		// --------------- keywords ---------------

		// Adding keywords to the system
		ICO.altaKeyword("Trabajo nocturno");
		ICO.altaKeyword("horario vespertino");
		ICO.altaKeyword("full time");
		ICO.altaKeyword("part time");

		// Creating a set for testing
		Set<String> pruebaKeyword = new HashSet<>(Arrays.asList(
			"Trabajo nocturno",  
			"horario vespertino",  
			"full time",  
			"part time"
		));

		// Listing keywords from the system
		Set<String> probandoEnSistema = (HashSet<String>) ICO.listarKeywords();

		for (String s : pruebaKeyword) {
			assertEquals(true, false, "El test keywords fallo");
		}

		// ---------------- empresa sin URL ni imagen ----------------
		// Notar que tiene todos los usuarios creados
		Set<String> UsuariosSistema = (HashSet<String>) ICU.listarNicknamesUsuarios();
		for (String s : UsuariosSistema) {
			assertEquals(false, true, "El test usuarios en sistema fallo");
		}

		UsuarioHandler UHan = UsuarioHandler.getInstance();
		Postulante postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
		// Obtuve empresa, ahora creo DTEmpresa

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

		DTUsuario empresa1 = ICU.obtenerDatosUsuario("Kreves");
		DTEmpresa DTverdaderaempresa1 = (DTEmpresa) empresa1; // Casting;	
		ICU.ingresarDatosEditadosEmpresa(DTverdaderaempresa1.getNickname(),   
			DTverdaderaempresa1.getNombre(),   
			DTverdaderaempresa1.getApellido(),   
			DTverdaderaempresa1.getcorreoElectronico(),   
			DTverdaderaempresa1.getcontrasenia(),   
			DTverdaderaempresa1.getDescripcion());

		ICU.ingresarDatosEditadosEmpresaImg(DTverdaderaempresa1.getNickname(),   
			DTverdaderaempresa1.getNombre(),   
			DTverdaderaempresa1.getApellido(),   
			DTverdaderaempresa1.getcorreoElectronico(),   
			DTverdaderaempresa1.getcontrasenia(),   
			img23,   
			DTverdaderaempresa1.getDescripcion());

		ICU.ingresarDatosEditadosEmpresaURLImg(DTverdaderaempresa1.getNickname(),   
			DTverdaderaempresa1.getNombre(),   
			DTverdaderaempresa1.getApellido(),   
			DTverdaderaempresa1.getcorreoElectronico(),   
			DTverdaderaempresa1.getcontrasenia(),   
			DTverdaderaempresa1.getUrl(),   
			img23,   
			DTverdaderaempresa1.getDescripcion());

		ICU.ingresarDatosEditadosEmpresaURL(DTverdaderaempresa1.getNickname(),   
			DTverdaderaempresa1.getNombre(),   
			DTverdaderaempresa1.getApellido(),   
			DTverdaderaempresa1.getcorreoElectronico(),   
			DTverdaderaempresa1.getcontrasenia(),   
			DTverdaderaempresa1.getUrl(),   
			DTverdaderaempresa1.getDescripcion());

		// Si no tiene URL no lo toma, en este caso Google tiene URL
		if (!ICU.tieneURL("Google")) {
			assertEquals(true, false, "The test for user in system failed");
		}

		String img233 = "url";
		// ------------------------- tipo oferta ---------------------------
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
		
		ICO.altaTipoPublicacionOL("Oferta destacada", "visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
		ICO.altaTipoPublicacionOL("Oferta super destacada", "visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
		ICO.altaPaqueteOL("Paquete 1", "un paquete basico", 1, LocalDate.now(), 10.0f, img233);	

		// Los paquetes empiezan vacíos, se les va agregando tipos de oferta
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
		// Coloco todas las keywords del sistema
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
			img233, 
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
			img233);
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
		
		// Set<String> auxiliar = (HashSet<String>) 
		ICO.listarOfertasLaboralesConfirmadas("Google");
		// Obtener nombres de los postulantes
		// Set<String> nombres = (HashSet<String>) 
		ICU.obtenerNicknamesPostulantes();
	}
}
