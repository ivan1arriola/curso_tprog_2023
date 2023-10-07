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
	// se testea las operaciones de los controladores
	// ------------------- testear alta empresa -------------------
	@Test
	void altaEmpresaTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		// imagen
		String str = "hello";
		byte[] img = str.getBytes();

		// empresa sin url ni imagen
		boolean b = altaEmpresa("Kreves","Pass", "Keanu","Reeves", "K2gmail.com", "Vendemos armas.");
		// empresa con url
		boolean b = altaEmpresaURL("Google", "Password", "Larry", "Page", "Larry@hotmail.com","Vendemos informacion.","www.google.com");
		// empresa con imagen
		boolean b = altaEmpresaImagen("Apple", "Password", "Steve", "Jobs", "Steve@aplle.com","Vendemos telefonos.", img);
		// empresa con url e imagen
		boolean b = altaEmpresaURLyImagen("Amazon", "Password", "Jeff", "Bezos", "Bezo@porBezo.com","Vendemos libros.", "www.amazon.com", img);
	}

	}

	// ------------------- testear alta oferta -------------------
	@Test
	void TestAltaOferta(){
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		ICtrlOferta ICO = f.getICtrlOferta();

		// imagen
		String str = "hello";
		byte[] img = str.getBytes()

		// --- keywords ---
		// esto es colocarlo en el sistema
		boolean b = altaKeyword("Trabajo nocturno");
		boolean b = altaKeyword("horario vespertino");
		boolean b = altaKeyword("full time");
		boolean b = altaKeyword("part time");
		// esto es crear un set para la oferta	
		List<Keyword> atrkeywords = new ArrayList<Keyword>();
		atrkeywords.add("Trabajo nocturno");
		atrkeywords.add("horario vespertino");
		atrkeywords.add("full time");
		atrkeywords.add("part time");
		// ---------------- 

		// --- tipo oferta ---
		boolean b = altaTipoPublicacionOL("Oferta normal","visibilidad normal", 1, 19, 100.0f, LocalDate.now());
		boolean b = altaTipoPublicacionOL("Oferta destacada","visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
		boolean b = altaTipoPublicacionOL("Oferta super destacada","visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
		// -------------------
		
		// --- paquete ---
		boolean b = altaPaqueteOL("Paquete 1","un paquete basico", 1, LocalDate.now(), 10.0f, img);	
		// los paquetes empiezan vacios, se les va agregando tipos de oferta
		// HashSet<OfertaPaquete> oferPaq = new HashSet<OfertaPaquete>();
		// oferPaq.add(new OfertaPaquete(nuevaOferta, 20));
		// oferPaq.add(new OfertaPaquete(nuevaOferta2, 12));
		// oferPaq.add(new OfertaPaquete(nuevaOferta3, 1))

		// como agergar tipos de oferta a un paquete?
		// con esta operacion
		agregarTipoOfertaPaq("Paquete 1", "Oferta normal",20);
		agregarTipoOfertaPaq("Paquete 1", "Oferta destacada",12);
		agregarTipoOfertaPaq("Paquete 1", "Oferta super destacada",1);

		// esto crea una nueva oferta laboral

	


		agregarTipoOfertaPaq(String paq, String TO, int cantidad)

		boolean b = altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA,List<String> keys)

		OfertaLaboral OF3 = new OfertaLaboral(atrkeywords,nuevaOferta2,"Tester", "tester para IA", "Montevideo", DepUY.MONTEVIDEO,hora, 100000.0f, fecha,  LocalDate.now(), paquete);
	}

	

	// ------------------- testear Postulacion -------------------
	@Test
	void postulacionTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		ICtrlOferta ICO = f.getICtrlOferta();

		Postulacion pos = crearPostulacion(String nick, String cv, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab);

	
	




}
