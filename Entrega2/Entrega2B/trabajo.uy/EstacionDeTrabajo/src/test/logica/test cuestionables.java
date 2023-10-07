package test.logica;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.Controladores.CtrlOferta;
import main.java.logica.Controladores.CtrlUsuario;
import main.java.logica.Datatypes.DTUsuario;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTHora;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;
import main.java.logica.Fabrica;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class FabricaTest {

	// --------------- testear agregar varias keywords ---------------
	@Test
	void altaKeywordTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		boolean b = ICO.altaKeyword("Trabajo nocturno");
		boolean b = ICO.altaKeyword("horario vespertino");
		boolean b = ICO.altaKeyword("full time");
		boolean b = ICO.altaKeyword("part time");
		boolean b = ICO.altaKeyword("horario matutino");
		boolean b = ICO.altaKeyword("especialista en java");
		boolean b = ICO.altaKeyword("especialista en c++");
		boolean b = ICO.altaKeyword("especialista en c#");
		boolean b = ICO.altaKeyword("especialista en python");
		boolean b = ICO.altaKeyword("especialista en javascript");
		boolean b = ICO.altaKeyword("especialista en php");

		HashSet<String> keywords = ICO.listarKeywords();
		
		assertEquals(true, keywords.contains("Trabajo nocturno")); // Se dio de alta la keyword
		assertEquals(true, keywords.contains("horario vespertino")); // Se dio de alta la keyword
		assertEquals(true, keywords.contains("full time")); // Se dio de alta la keyword
		assertEquals(true, keywords.contains("part time")); // Se dio de alta la keyword
	}
	// ----------------------------------------------------------------

	// --------------- testear agregar varias empresas ---------------
	@Test
	void altaEmpresaTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		byte[] img = imagen.getBytes();
				
		try {
			// construir con imagen y url
			boolean b = ICU.altaEmpresa("Microsoft", "Bill", "Gates", "bilie@gmail.com","contraseNaSeguraCreeme", img, "Vendemos software.", "www.microsoft.com");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

		try {
			// construir con imagen sin url
			boolean b = ICU.altaEmpresa("Google", "Larry", "Page", "LarryYesguapo@hotmail.com","contraseNaSeguraCreeme", img, "Vendemos informacion.");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

		try {
			// construir con url sin imagen
			boolean b = ICU.altaEmpresa("keanu", "reeves", "Ken@revees.com","1234567890", "Vendemos peliculas.", "www.kenau.com");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

		try {
			// construir sin imagen ni url
			boolean b = ICU.altaEmpresa("arnold", "schwarzenegger", "an@terminator.com","1234567890", "Skynet.");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

	}
	// ----------------------------------------------------------------

	// --------------- testear agregar varios postulantes ---------------
	@Test
	void altaPostulanteTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		byte[] img = imagen.getBytes();
			

		try {
			boolean b = ICU.altaPostulante("AnHopkins", "Anthony", "Hopkins", "ANHop@Hannibal.com", LocalDate.now(), "Inglaterra", img);
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

		try {
			boolean b = ICU.altaPostulante("LeoDicapri", "Leonardo", "DiCaprio", "Leo@Lous14.com", LocalDate.now(), "EEUU");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			e.printStackTrace();
		}

	}
	// ----------------------------------------------------------------

	// --------------- testear agregar varias Tipos ofertas laborales ---------------
	@Test
	void TipoOfertatest() {
		// TipoOferta(String nombre, LocalDate fechaAlta, float costo, int duracion, int exposicion, String descripcion) 
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		TipoOferta nuevaOferta = new TipoOferta("Oferta normal", LocalDate.now(), 100.0f, 1, 19, "Feliz Navidad");
		TipoOferta nuevaOferta2 = new TipoOferta("Oferta lechuga", LocalDate.now(), 1000.0f, 2, 19, "Feliz Año Nuevo"); 
		TipoOferta nuevaOferta3 = new TipoOferta("Oferta Especial Queso", LocalDate.now(), 100000.0f, 12, 190, "MUZA CONN FRITAS");

		HashSet<OfertaPaquete> oferPaq = new HashSet<OfertaPaquete>();
		oferPaq.add(new OfertaPaquete(nuevaOferta, 20));
		oferPaq.add(new OfertaPaquete(nuevaOferta2, 12));
		oferPaq.add(new OfertaPaquete(nuevaOferta3, 1));

		HashSet<OfertaPaquete> oferPaq2 = new HashSet<OfertaPaquete>();	
		oferPaq2.add(new OfertaPaquete(nuevaOferta, 10));
		oferPaq2.add(new OfertaPaquete(nuevaOferta2, 5));
	}
	// ------------------------------------------------------------------------------

	// ------------------------- paquete -----------------------
	@Test
	void altaPaqueteTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		// imagen
		byte[] img = paquet.getBytes();
	
		Paquete paquete = new Paquete("paqueteBasico", "Comun", 1, LocalDate.now(), 10, img);
		Paquete paquete2 = new Paquete("paqueteQuesoPremium", "muzarella", 1, LocalDate.now(), 30, img);	
		paquete.setOfertaPaquete(oferPaq2);
		paquete2.setOfertaPaquete(oferPaq);
	}
	// ----------------------------------------------------------------


	// ------------------------- Oferta Laboral -----------------------
	@Test
	void altaOfertaLaboralTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		// lista de keywords
		List<Keyword> atrkeywords = new ArrayList<Keyword>();
		atrkeywords.add("Trabajo nocturno");
		atrkeywords.add("horario vespertino");
		atrkeywords.add("full time");
		atrkeywords.add("part time");
		// imagen
		byte[] img = OfertaLabora.getBytes();
			
		// fecha
		LocalDate fecha = LocalDate.of(2023, 8, 9);

		//hora
		DTHora hora = new DTHora(8, 0);

		// testeo con imagen y paquete
		OfertaLaboral OF1 = new OfertaLaboral(atrkeywords,nuevaOferta2,"investigador IA", "Investigador de IA", "Montevideo", DepUY.MONTEVIDEO,hora, 1000000.0f, fecha,  LocalDate.now(), img, paquete);

		// testeo con imagen sin paquete
		OfertaLaboral OF2 = new OfertaLaboral(atrkeywords,nuevaOferta2,"disenador Web", "diseno web pagina", "maldonado", DepUY.MALDONADO,hora, 100000.0f, fecha,  LocalDate.now(), img);

		// testeo sin imagen con paquete
		OfertaLaboral OF3 = new OfertaLaboral(atrkeywords,nuevaOferta2,"Tester", "tester para IA", "Montevideo", DepUY.MONTEVIDEO,hora, 100000.0f, fecha,  LocalDate.now(), paquete);

		//	testeo sin imagen ni paquete
		OfertaLaboral OF4 = new OfertaLaboral(atrkeywords,nuevaOferta2,"Manejador de bases", "para base de datos", "Montevideo", DepUY.MONTEVIDEO,hora, 100000.0f, fecha,  LocalDate.now());
	}
	// ----------------------------------------------------------------

	// ---------------- testear agregar postulaciones -----------------
	@Test
	void altaPostulacionTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		ICtrlOferta ICO = f.getICtrlOferta();

		byte[] img = imagen.getBytes();
			
		Postulacion(Postulante p, String CV, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab) {

		LocalDate fecha = LocalDate.of(2001, 8, 9);
		ICO.altaTipoPublicacionOL("Comun", "Una presencia comun.", 1, 19, 5000.0f, LocalDate.now())

		}
	\\ ----------------------------------------------------------------	

}
