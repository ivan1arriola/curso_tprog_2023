package main.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import excepciones.ExcepcionTipoOfertaNoExistente;
import logica.Fabrica;
import logica.clases.Postulante;
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
import logica.manejadores.UsuarioHandler;

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
			//boolean bool1 = 
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
				if (!probandoEnSistema.contains(s)) {
					assertEquals("El test keywords fallo",   true,   false);
				}
			}
		

			// ---------------- empresa sin url ni imagen ----------------
		    // notar que tiene todos los usuarios creados
		    Set<String> UsuariosSistema = (HashSet<String>) ICU.listarNicknamesUsuarios();
			for (String s : UsuariosSistema) {
				if (!s.equals("Kreves") && !s.equals("Google") && !s.equals("Apple") && !s.equals("Amazon") && !s.equals("ASwatzenegger") && !s.equals("LeonardoVinchi")) {
					assertEquals("El test usuarios en sistema fallo",   false,   true);
				}
			
			}

			UsuarioHandler UHan = UsuarioHandler.getInstance();
			Postulante postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
			// obtuve empresa,   ahora creo DTEmpresa
			
			ICU.ingresarDatosEditadosPostulante(postulante1.getNickname(),   
												postulante1.getNombre(),   
												postulante1.getApellido(),   
												postulante1.getcorreoElectronico(),   
												postulante1.getcontrasenia(),   
												postulante1.getFechaNac(),  
												postulante1.getNacionalidad());
												
			DTUsuario DTpostulante1 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi",  "LeonardoVinchi");
			DTPostulante DTverdaderopostulante1 = (DTPostulante) DTpostulante1; // Casting;

			ICU.ingresarDatosEditadosPostulante(DTverdaderopostulante1.getNickname(),   
														DTverdaderopostulante1.getNombre(),   
														DTverdaderopostulante1.getApellido(),   
														DTverdaderopostulante1.getcorreoElectronico(),   
														DTverdaderopostulante1.getcontrasenia(),   
														DTverdaderopostulante1.getFechaNac(),   
														DTverdaderopostulante1.getNacionalidad());	
				
			String  img23 = "url";
			
			ICU.ingresarDatosEditadosPostulanteImg(DTverdaderopostulante1.getNickname(),   
													DTverdaderopostulante1.getNombre(),   
													DTverdaderopostulante1.getApellido(),   
													DTverdaderopostulante1.getcorreoElectronico(),   
													DTverdaderopostulante1.getcontrasenia(),   
													img23,   
													DTverdaderopostulante1.getFechaNac(),   
													DTverdaderopostulante1.getNacionalidad());
			postulante1 = (Postulante) UHan.buscarNick("LeonardoVinchi");
			DTUsuario DTpostulante12 = postulante1.obtenerDatosUsuarioEspecial("LeonardoVinchi",  "ASwatzenegger");
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
			// si no tiene url no lo toma,   en este caso google tiene url
			if (!ICU.tieneURL("Google")) {
				assertFalse("The test for user in system failed",   true);
			}
			

			String img233 = "url";
			// ------------------------- tipo oferta ---------------------------
//			boolean booleano;
			ICO.altaTipoPublicacionOL("Oferta normal",  "visibilidad normal",   1,   19,   100.0f,   LocalDate.now());
			DTTipoOferta tipoOfertaDT=null;
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
			ICO.altaTipoPublicacionOL("Oferta destacada",  "visibilidad destacada",   1,   19,   100.0f,   LocalDate.now());
			ICO.altaTipoPublicacionOL("Oferta super destacada",  "visibilidad super destacada",   1,   19,   100.0f,   LocalDate.now());
			ICO.altaPaqueteOL("Paquete 1",  "un paquete basico",   1,   LocalDate.now(),   10.0f,   img233);	

			// los paquetes empiezan vacios,   se les va agregando tipos de oferta

			ICO.agregarTipoOfertaPaq("Paquete 1",   "Oferta normal",  20);
			ICO.agregarTipoOfertaPaq("Paquete 1",   "Oferta destacada",  12);
			ICO.agregarTipoOfertaPaq("Paquete 1",   "Oferta super destacada",  1);
		    
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
			
			DTHora hora1 = new DTHora(8,   0);
			DTHora hora2 = new DTHora(1,   0);
			DTHorario horario = new DTHorario(hora1,  hora2);
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

			DTOfertaLaboral temporal3 = new DTOfertaLaboral( "Google",  
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
//			Set<String> auxiliar = (HashSet<String>) 
			ICO.listarOfertasLaboralesConfirmadas("Google");
			// obtener nombres de los postulantes
//			Set<String> nombres = (HashSet<String>) 
			ICU.obtenerNicknamesPostulantes();
			
			
//			boolean existePostulacion(String nickname,   String nombre) {
//				UsuarioHandler UHan = UsuarioHandler.getInstance();
//				Postulante p = (Postulante) UHan.buscarNick(nickname);
//				if (p != null) 
//					return p.existePostulacion(nombre);
//				else
//					// throw new IllegalArgumentException("Usuario " + nick + " no existe");
//					return false;
//			}
//
//			public Postulacion crearPostulacion(String nick,   String cv,   String motivacion,   LocalDate fecha,   String URLDocExtras,   OfertaLaboral OferLab) 
		}
		    
}

