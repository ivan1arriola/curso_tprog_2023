package main.java.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

import main.java.logica.clases.Empresa;
import main.java.logica.clases.InfoCompra;
import main.java.logica.clases.InfoCompraOferta;
import main.java.logica.clases.OfertaLaboral;
import main.java.logica.clases.Paquete;
import main.java.logica.clases.Postulacion;
import main.java.logica.clases.Postulante;
import main.java.logica.clases.TipoOferta;
import main.java.logica.clases.Usuario;
import main.java.logica.datatypes.DTCantTO;
import main.java.logica.datatypes.DTCompraPaquetes;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTHora;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoConKeywords;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTTipoOferta;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;
import main.java.logica.manejadores.OfertaLaboralHandler;
import main.java.logica.manejadores.PaqueteHandler;
import main.java.logica.manejadores.TipoOfertaHandler;
import main.java.logica.manejadores.UsuarioHandler;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.Fabrica;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ControladorUsuarioTest5 {
		
		@Test
		void TestPARAUSUARIo(){
			Fabrica f = Fabrica.getInstance();
		    ICtrlUsuario ICU = f.getICtrlUsuario();
		    ICtrlOferta ICO = f.getICtrlOferta();
			
		    // ---------------------------- creo Postulante ---------------------------- 
			String nickname2 = "ReneDescartes";
			String password2 = "LaContrasenaMasSeguraDelMundo";
			String nombre2 = "Rene";
			String apellido2 = "Descartes";
			String correo2 = "CogitoErgo@Zum.com";
			LocalDate fechaNacimiento2 = LocalDate.of(1596,  3,  31);
			String nacionalidad2 = "Frances";
			String imagen2 = "MeEncantaPensar";
			byte[] img2 = imagen2.getBytes();
			ICU.altaPostulanteImagen(nickname2,  password2,  nombre2,  apellido2,  fechaNacimiento2,  correo2,  nacionalidad2,  img2);
			// ---------------------------- creo Empresa ----------------------------
			String nickname = "The Clouds";
			String password = "LaContrasenaMasSeguraDelMundo";
			String nombre = "Socrates";
			String apellido = "of Grece";
			String correo = "Socrates@gmail.com";
			String descripcion = "I know that I know nothing.";
			String imagen ="made by Aristophanes";
			byte[] img = imagen.getBytes();
			ICU.altaEmpresaImagen(nickname,password,  nombre,  apellido, correo ,descripcion,  img);
			// ---------------------------- creo TipoOferta ----------------------------
			ICO.altaTipoPublicacionOL("Oferta salada",  "visibilidad salada",   199,   190,   102340.0f,   LocalDate.now());
			ICO.altaTipoPublicacionOL("Oferta dulce",  "visibilidad dulce",   199,   290,   102340.0f,   LocalDate.now());
			ICO.altaTipoPublicacionOL("Oferta proteica",  "visibilidad proteica",   199,   1908,   1020340.0f,   LocalDate.now());	
			// ---------------------------- creo Paquete ----------------------------
			String str1 = "MeEncantaElQuesoSemiduro";
			byte[] img33 = str1.getBytes();
			ICO.altaPaqueteOL("Paquete 7",  "un paquete espectacular, con mucho queso",   1,   LocalDate.now(),   10.0f,   img33);
			// los paquetes empiezan vacios,   se les va agregando tipos de oferta
			ICO.agregarTipoOfertaPaq("Paquete 7",   "Oferta salada",  20);
			ICO.agregarTipoOfertaPaq("Paquete 7",   "Oferta dulce",  12);
			ICO.agregarTipoOfertaPaq("Paquete 7",   "Oferta proteica",  1);
			// ---------------------------- empresa Compra Paquete? ----------------------------
			// tengo que obtener el controlador de TipoOferta para obtener el tipo de oferta
			// y luego comprar el paquete
			TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
			Map<String, TipoOferta> mapa = TOH.obtener();
			String keyToLookup = "Oferta salada";
			TipoOferta ofertaLaboralSalada = mapa.get(keyToLookup);
			// esta es la manera de decir en el sistema compre un paquete
			//InfoCompra(LocalDate fechaCompra,   float costo,   Paquete pack,   Empresa empres,  Set<DTCantTO> conjuntoS)
			// la idea es que si compra un paquete entonces genera automaticamente infoCompraOferta indicando la compra por adentro de un paquete
			// haciendose con operacion InfoCompraOferta ununuunu = new InfoCompraOferta(ofertaLaboralSalada,3);
			// esto indica se compro, notar que no hay caso de uso para comprar paquete
			// todo esto no esta armado
			// seria asi el orden
			InfoCompraOferta ununuunu = new InfoCompraOferta(ofertaLaboralSalada,3);
			DTCantTO nuevamente = new DTCantTO("Oferta salada",3);
			HashSet<DTCantTO> hashSet = new HashSet<>();
	        hashSet.add(nuevamente);
			LocalDate fechaCompra = LocalDate.now();
			float costo = 10.0f;
			// obtener paquete
			PaqueteHandler PH = PaqueteHandler.getInstance();
			Map<String,  Paquete> mapaPaquete = PH.obtener();
			keyToLookup = "Paquete 7";
			Paquete Pack = mapaPaquete.get(keyToLookup);
			// obtener empresa
			UsuarioHandler UH = UsuarioHandler.getInstance();
			Map<String,  Usuario> mapaUsuario = UH.obtenerNick();
			keyToLookup = "The Clouds";
			Empresa empres = (Empresa) mapaUsuario.get(keyToLookup);
			InfoCompra nueva = new InfoCompra(fechaCompra,   costo,   Pack,   empres,  hashSet);
			// --------------------- compro paquete, ahora si ------------------------
			String nickname22 = "The Clouds";
			ICO.compraPaquetes(nickname22,"Paquete 7");		    
			
			// ---------------------------- Empresa Crea Trabajo ---------------------------- 
			nickname22 = "The Clouds";	
			String nombre22 = "pensador";
			String descripcion2 = "Pensador de sistemas";
			DTHora hora12 = new DTHora(8, 0);
			DTHora hora22 = new DTHora(1, 0);
			DTHorario horario2 = new DTHorario(hora12, hora22);
			float remuneracion2 = 1;
			String ciudad2 = "Montevideo";
			DepUY dep2 = DepUY.Montevideo;
			LocalDate fechaA2 = LocalDate.of(2020, 12, 12);

			List<String> pruebaKeyword1 = new ArrayList<>(Arrays.asList(
			    "Trabajo nocturno",
			    "horario vespertino",
			    "full time",
			    "part time"
			));

			EstadoOL estado = EstadoOL.Ingresada;
			String paquete = "Paquete 7";
			try {
				ICU.altaOfertaLaboral(nickname22, "Oferta salada", nombre22, descripcion2, horario2, remuneracion2, ciudad2, dep2, fechaA2, pruebaKeyword1, estado, img, paquete);
			} catch (ExceptionUsuarioNoEncontrado e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (ExceptionEmpresaInvalida e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}	
			DTTipoOferta nuevoTO = ICO.tipoOferta(nombre22); 	
			// -----------------------------------------------------------
			 DTOfertaExtendidoSinPConK nuevo = ICU.infoOfertaLaboralVisitante("pensador");
			 nuevo.getCosto();
			 
		}	
}
