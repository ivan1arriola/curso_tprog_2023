package main.java.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Test;



import main.java.logica.Manejadores.UsuarioHandler;
import main.java.logica.Fabrica;
import main.java.logica.Clases.Empresa;
import main.java.logica.Clases.Postulante;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;

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
			booleano = ICO.altaTipoPublicacionOL("Oferta destacada","visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
			booleano = ICO.altaTipoPublicacionOL("Oferta super destacada","visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
			
			booleano = ICO.altaPaqueteOL("Paquete 1","un paquete basico", 1, LocalDate.now(), 10.0f, img233);	

			// los paquetes empiezan vacios, se les va agregando tipos de oferta

			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta normal",20);
			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta destacada",12);
			ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta super destacada",1);
		    
		}
		    
}

