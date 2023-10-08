package main.java.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import main.java.logica.clases.Empresa;
import main.java.logica.clases.OfertaLaboral;
import main.java.logica.clases.Postulacion;
import main.java.logica.clases.Postulante;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTHora;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTTipoOferta;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;
import main.java.logica.manejadores.UsuarioHandler;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.logica.Fabrica;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

public class ControladorUsuarioTest2 {
		
		// --------------- Testeo Keyowords ---------------
		@Test
		void keywordTest() {
			Fabrica f = Fabrica.getInstance();
			ICtrlUsuario ICU = f.getICtrlUsuario();
			ICtrlOferta ICO = f.getICtrlOferta();

			// --------------- keywords ---------------

			// Adding keywords to the system
			boolean b1 = ICO.altaKeyword("Trabajo nocturno");
			boolean b2 = ICO.altaKeyword("horario vespertino");
			boolean b3 = ICO.altaKeyword("full time");
			boolean b4 = ICO.altaKeyword("part time");

			// Creating a set for testing
			HashSet<String> pruebaKeyword = new HashSet<>(Arrays.asList(
				"Trabajo nocturno",
				"horario vespertino",
				"full time",
				"part time"
			));

			// Listing keywords from the system
			HashSet<String> probandoEnSistema = ICO.listarKeywords();

			for (String s : pruebaKeyword) {
				if (!probandoEnSistema.contains(s)) {
					assertEquals("El test keywords fallo", true, false);
				}
			}
		

			// ---------------- empresa sin url ni imagen ----------------
		    // notar que tiene todos los usuarios creados
		    HashSet<String> UsuariosSistema = ICU.listarNicknamesUsuarios();
			for (String s : UsuariosSistema) {
				if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon") && !s.equals("ASwatzenegger") && !s.equals("LeonardoVinchi")) {
					assertEquals("El test usuarios en sistema fallo", false, true);
				}
			
			}

			UsuarioHandler UH = UsuarioHandler.getInstance();
			Postulante postulante1 = (Postulante) UH.buscarNick("LeonardoVinchi");
			// obtuve empresa, ahora creo DTEmpresa
			
			ICU.ingresarDatosEditadosPostulante(postulante1.getNickname(), 
												postulante1.getNombre(), 
												postulante1.getApellido(), 
												postulante1.getCorreo_electronico(), 
												postulante1.getContraseña(), 
												postulante1.getFecha_nac(),
												postulante1.getNacionalidad());
												
			DTUsuario DTpostulante1 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi","LeonardoVinchi");
			DTPostulante DTverdaderopostulante1 = (DTPostulante) DTpostulante1; // Casting;

			ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante1.getNickname(), 
														DTverdaderopostulante1.getNombre(), 
														DTverdaderopostulante1.getApellido(), 
														DTverdaderopostulante1.getCorreo_electronico(), 
														DTverdaderopostulante1.getContraseña(), 
														DTverdaderopostulante1.getFecha_nac(), 
														DTverdaderopostulante1.getNacionalidad());	
				
			String str = "MeEncantaLaMOZARELLA";
			byte[] img23 = str.getBytes();
			
			ICU.ingresarDatosEditadosPostulanteImg(DTverdaderopostulante1.getNickname(), 
													DTverdaderopostulante1.getNombre(), 
													DTverdaderopostulante1.getApellido(), 
													DTverdaderopostulante1.getCorreo_electronico(), 
													DTverdaderopostulante1.getContraseña(), 
													img23, 
													DTverdaderopostulante1.getFecha_nac(), 
													DTverdaderopostulante1.getNacionalidad());
			postulante1 = (Postulante) UH.buscarNick("LeonardoVinchi");
			DTUsuario DTpostulante12 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi","ASwatzenegger");
			DTPostulante DTverdaderopostulante12 = (DTPostulante) DTpostulante12; // Casting;

			ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante12.getNickname(), 
														DTverdaderopostulante12.getNombre(), 
														DTverdaderopostulante12.getApellido(), 
														DTverdaderopostulante12.getCorreo_electronico(), 
														DTverdaderopostulante12.getContraseña(), 
														DTverdaderopostulante12.getFecha_nac(), 
														DTverdaderopostulante12.getNacionalidad());	
			
			DTUsuario empresa1 = ICU.obtenerDatosUsuario("Kreves");
			DTEmpresa DTverdaderaempresa1 = (DTEmpresa) empresa1; // Casting;	
			ICU.ingresarDatosEditadosEmpresa(DTverdaderaempresa1.getNickname(), 
												DTverdaderaempresa1.getNombre(), 
												DTverdaderaempresa1.getApellido(), 
												DTverdaderaempresa1.getCorreo_electronico(), 
												DTverdaderaempresa1.getContraseña(), 
												DTverdaderaempresa1.getDescripcion());
			
			ICU.ingresarDatosEditadosEmpresaImg(DTverdaderaempresa1.getNickname(), 
												DTverdaderaempresa1.getNombre(), 
												DTverdaderaempresa1.getApellido(), 
												DTverdaderaempresa1.getCorreo_electronico(), 
												DTverdaderaempresa1.getContraseña(), 
												img23, 
												DTverdaderaempresa1.getDescripcion());

			ICU.ingresarDatosEditadosEmpresaURLImg(DTverdaderaempresa1.getNickname(), 
													DTverdaderaempresa1.getNombre(), 
													DTverdaderaempresa1.getApellido(), 
													DTverdaderaempresa1.getCorreo_electronico(), 
													DTverdaderaempresa1.getContraseña(), 
													DTverdaderaempresa1.getUrl(), 
													img23, 
													DTverdaderaempresa1.getDescripcion());

			ICU.ingresarDatosEditadosEmpresaURL(DTverdaderaempresa1.getNickname(), 
												DTverdaderaempresa1.getNombre(), 
												DTverdaderaempresa1.getApellido(), 
												DTverdaderaempresa1.getCorreo_electronico(), 
												DTverdaderaempresa1.getContraseña(), 
												DTverdaderaempresa1.getUrl(), 
												DTverdaderaempresa1.getDescripcion());
			// si no tiene url no lo toma, en este caso google tiene url
			if (!ICU.tieneURL("Google")){
				assertFalse("The test for user in system failed", true);
			}
			

			String str1 = "MeEncantaLaMOZARELLA11111";
			byte[] img233 = str1.getBytes();
			// ------------------------- tipo oferta ---------------------------
			boolean booleano;
			booleano = ICO.altaTipoPublicacionOL("Oferta normal","visibilidad normal", 1, 19, 100.0f, LocalDate.now());
			try {
				DTTipoOferta tipo = ICO.obtenerDatosTO("Oferta normal");
			} catch (ExcepcionTipoOfertaNoExistente e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			booleano = ICO.altaTipoPublicacionOL("Oferta destacada","visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
			booleano = ICO.altaTipoPublicacionOL("Oferta super destacada","visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
			
			booleano = ICO.altaPaqueteOL("Paquete 1","un paquete basico", 1, LocalDate.now(), 10.0f, img233);	

			// los paquetes empiezan vacios, se les va agregando tipos de oferta

			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta normal",20);
			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta destacada",12);
			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta super destacada",1);
		    
			DTPaquete nuevo = ICO.obtenerDatosPaquete("Paquete 1");
			
			// coloco todas keywors del sistema
			HashSet<String> listaKeywords = ICO.listarKeywords();
			
			DTHora hora1 = new DTHora(8, 0);
			DTHora hora2 = new DTHora(1, 0);
			DTHorario horario = new DTHorario(hora1,hora2);
			String TIPOOFERTASELECCIONADA = "Oferta normal";
			
			ICO.altaOfertaLaboral("Google", 
								  TIPOOFERTASELECCIONADA, 
								  "investigador IA", 
								  "Investigador de IA",
								  horario, 
								  100222.0f, 
								  "montevideo1", 
								  DepUY.Montevideo, 
								  LocalDate.now(),
								  listaKeywords,
								  EstadoOL.Confirmada,
								  img233,
								  "Paquete 1");
			
			
			HashSet<String> auxiliar = ICO.listarOfertasLaboralesConfirmadas("Google");
			// obtener nombres de los postulantes
			HashSet<String> nombres = ICU.obtenerNicknamesPostulantes();
			
			
//			boolean existePostulacion(String nickname, String nombre) {
//				UsuarioHandler UH = UsuarioHandler.getInstance();
//				Postulante p = (Postulante) UH.buscarNick(nickname);
//				if(p != null) 
//					return p.existePostulacion(nombre);
//				else
//					// throw new IllegalArgumentException("Usuario " + nick + " no existe");
//					return false;
//			}
//
//			public Postulacion crearPostulacion(String nick, String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) 
		}
		    
}

