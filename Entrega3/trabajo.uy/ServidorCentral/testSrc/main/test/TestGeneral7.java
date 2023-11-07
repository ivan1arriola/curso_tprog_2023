package main.test;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionDuracionNegativa;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionExpoNegativa;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionValidezNegativa;
import excepciones.NoExistePaquete;
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
import logica.clases.Paquete;
import logica.clases.Postulacion;
import logica.clases.TipoOferta;
import logica.clases.Usuario;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
import logica.datatypes.DTOfertaExtendidoSinPConK;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral7 {
	@Test
	public void Test1() {
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
        System.out.println("################## Test 7 ##################");
        // ============================================
     // ---------------------------- creo Postulante ----------------------------
        String nickname2 = "ReneDescartes";
        String password2 = "LaContrasenaMasSeguraDelMundo";
        String nombre2 = "Rene";
        String apellido2 = "Descartes";
        String correo2 = "CogitoErgo@Zum.com";
        LocalDate fechaNacimiento2 = LocalDate.of(1596, 3, 31);
        String nacionalidad2 = "Frances";
        String img2 = "url";
        try {
			ICU.altaPostulanteImagen(nickname2, password2, nombre2, apellido2, fechaNacimiento2, correo2, nacionalidad2, img2.getBytes());
		} catch (ExceptionFechaInvalida | ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // ---------------------------- creo Empresa ----------------------------
        String nickname = "The Clouds";
        String password = "LaContrasenaMasSeguraDelMundo";
        String nombre = "Socrates";
        String apellido = "of Grece";
        String correo = "Socrates@gmail.com";
        String descripcion = "I know that I know nothing.";
        String img = "url";
        try {
			ICU.altaEmpresaImagen(nickname, password, nombre, apellido, correo, descripcion, img.getBytes());
		} catch (ErrorAgregarUsuario e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // ---------------------------- creo TipoOferta ----------------------------
        ICO.altaTipoPublicacionOL("Oferta salada", "visibilidad salada", 199, 190, 102340.0f, LocalDate.now());
        ICO.altaTipoPublicacionOL("Oferta dulce", "visibilidad dulce", 199, 290, 102340.0f, LocalDate.now());
        ICO.altaTipoPublicacionOL("Oferta proteica", "visibilidad proteica", 199, 1908, 1020340.0f, LocalDate.now());
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        TOH.buscar("Oferta salada");

        // ---------------------------- creo Paquete ----------------------------
        String img33 = "url";
        try {
			ICO.altaPaqueteOL("Paquete 7", "un paquete espectacular,  con mucho queso", 1, LocalDate.now(), 10.0f, img33.getBytes());
		} catch (ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // los paquetes empiezan vacios,    se les va agregando tipos de oferta
        try {
			ICO.agregarTipoOfertaPaq("Paquete 7", "Oferta salada", 20);
		} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ICO.agregarTipoOfertaPaq("Paquete 7", "Oferta dulce", 12);
		} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ICO.agregarTipoOfertaPaq("Paquete 7", "Oferta proteica", 1);
		} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // ---------------------------- empresa Compra Paquete? ----------------------------
        // tengo que obtener el controlador de TipoOferta para obtener el tipo de oferta
        // y luego comprar el paquete
        TipoOfertaHandler TOHr = TipoOfertaHandler.getInstance();
        Map<String, TipoOferta> mapa = TOHr.obtener();
        String keyToLookup = "Oferta salada";
        TipoOferta ofertaLaboralSalada = mapa.get(keyToLookup);
        ofertaLaboralSalada.getCosto();
        ofertaLaboralSalada.getDescripcion();
        ofertaLaboralSalada.getDuracion();
        ofertaLaboralSalada.getExposicion();
        ofertaLaboralSalada.getFechaAlta();
        ofertaLaboralSalada.getNombre();
        try {
			ofertaLaboralSalada.setCosto(0);
		} catch (ExceptionCostoPaqueteNoNegativo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ofertaLaboralSalada.setDescripcion("visibilidad salada");
        try {
			ofertaLaboralSalada.setDuracion(190);
		} catch (ExceptionDuracionNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ofertaLaboralSalada.setExposicion(199);
		} catch (ExceptionExpoNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ofertaLaboralSalada.setFechaAlta(LocalDate.now());
        ofertaLaboralSalada.setNombre("Oferta salada");
        // esta es la manera de decir en el sistema compre un paquete
        //InfoCompra(LocalDate fechaCompra,    float costo,    Paquete pack,    Empresa empres,   Set<DTCantTO> conjuntoS)
        // la idea es que si compra un paquete entonces genera automaticamente infoCompraOferta indicando la compra por adentro de un paquete
        // haciendose con operacion InfoCompraOferta ununuunu = new InfoCompraOferta(ofertaLaboralSalada, 3) ;
        // esto indica se compro,  notar que no hay caso de uso para comprar paquete
        // todo esto no esta armado
        // seria asi el orden
        try {
			new InfoCompraOferta(ofertaLaboralSalada, 3);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DTCantTO nuevamente = new DTCantTO("Oferta salada", 3);
        Set<DTCantTO> hashSet = new HashSet<>();
        hashSet.add(nuevamente);
        LocalDate fechaCompra = LocalDate.now();
        float costo = 10.0f;
        // obtener paquete
        PaqueteHandler PHan = PaqueteHandler.getInstance();
        Map<String, Paquete> mapaPaquete = PHan.obtener();
        keyToLookup = "Paquete 7";
        Paquete Pack = mapaPaquete.get(keyToLookup);
        // obtener empresa
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Map<String, Usuario> mapaUsuario = UHan.obtenerNick();
        keyToLookup = "The Clouds";
        Empresa empres = (Empresa) mapaUsuario.get(keyToLookup);
        InfoCompra nueva = null;
		try {
			nueva = new InfoCompra(fechaCompra, costo, Pack, empres, hashSet);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa | ExceptionValidezNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // getters y setters
        nueva.getCosto();
        nueva.getEmpresa();
        nueva.getfCompra();
        nueva.getFechaVencimiento();
        nueva.getICO();
        nueva.getPaquete();
        nueva.obtenerDatosPaquete();
        nueva.setCosto(10.0f);
        nueva.setEmpresa(empres);
        nueva.setfCompra(LocalDate.now());
        nueva.setFechaVencimiento(LocalDate.now());
        nueva.setICO(nueva.getICO());
        nueva.setPaquete(Pack);

        // mas constructores para oferta laboral
        List<Keyword> myList = new ArrayList<>();
        try {
			myList.add(new Keyword("manzana"));
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			myList.add(new Keyword("banana"));
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			myList.add(new Keyword("frutilla"));
		} catch (ExcepcionKeywordVacia e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        TipoOferta atrtOferta = ofertaLaboralSalada;
        String atrnombre6 = "pensador";
        String atrdescripcion6 = "Pensador de sistemas";
        String atrciudad6 = "Montevideo";
        DepUY atrdepartamento6 = DepUY.Montevideo;
        LocalDate atrfechaAlta6 = LocalDate.of(2020, 12, 12);
        EstadoOL estadoNuevo6 = EstadoOL.Ingresada;
        float atrremuneracion = 1;
        DTHora horaSalada = new DTHora(8, 0);
        DTHora horaSalada2 = new DTHora(1, 0);
        DTHorario horarioSal = new DTHorario(horaSalada, horaSalada2);
        // constructor sin paquete y sin imagen
        try {
			new OfertaLaboral(null, myList,
			        atrtOferta,
			        atrnombre6,
			        atrdescripcion6,
			        atrciudad6,
			        atrdepartamento6,
			        horarioSal,
			        atrremuneracion,
			        atrfechaAlta6,
			        estadoNuevo6);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			// constructor sin imagen pero con paquete	
        try {
			new OfertaLaboral(null, myList,
			        atrtOferta,
			        atrnombre6,
			        atrdescripcion6,
			        atrciudad6,
			        atrdepartamento6,
			        horarioSal,
			        atrremuneracion,
			        atrfechaAlta6,
			        estadoNuevo6,
			        Pack);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // constructor sin imagen ni paquete
        try {
			new OfertaLaboral(null, myList,
			        atrtOferta,
			        atrnombre6,
			        atrdescripcion6,
			        atrciudad6,
			        atrdepartamento6,
			        horarioSal,
			        atrremuneracion,
			        atrfechaAlta6,
			        estadoNuevo6);
		} catch (ExceptionRemuneracionOfertaLaboralNegativa | ExceptionPaqueteNoVigente
				| ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // --------------------- compro paquete,  ahora si ------------------------
        String nickname22 = "The Clouds";
        try {
			ICO.compraPaquetes(nickname22, "Paquete 7", atrfechaAlta6, 0);
		} catch (ExceptionCompraPaqueteConValorNegativo | ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa
				| ExceptionValidezNegativa | ExceptionUsuarioNoEncontrado | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			ICU.altaOfertaLaboral(nickname22, "Oferta salada", nombre22, descripcion2, horario2, remuneracion2, ciudad2, dep2, fechaA2, pruebaKeyword1, estado, img.getBytes(), paquete);
		} catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida | ExceptionRemuneracionOfertaLaboralNegativa
				| ExceptionPaqueteNoVigente | ExceptionCostoPaqueteNoNegativo | ExceptionDescuentoInvalido
				| ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa | NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        try {
			ICO.tipoOferta(nombre22);
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // -----------------------------------------------------------
        DTOfertaExtendidoSinPConK nuevo = null;
//		try {
//			nuevo = ICU.infoOfertaLaboralVisitante("pensador");
//		} catch (OfertaLaboralNoEncontrada e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        nuevo.getCosto();
        try {
			ICU.consultaOfertaLaboral("pensador");
		} catch (OfertaLaboralNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ICU.obtenerDatosPaquete(paquete);
		} catch (NoExistePaquete e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			ICU.obtenerDatosUsuarioVisitantes(nickname22);
		} catch (ExceptionUsuarioNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // ------------------------------------------------------------------------------------------
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        Map<String, OfertaLaboral> mapaOL = OLH.obtener();
        keyToLookup = "pensador";
        OfertaLaboral OLpensador = mapaOL.get(keyToLookup);
        // ------------------------------------------------------------------------------------------
        String nick = "ReneDescartes";
        String curriculumVitae = "Soy un pensador";
        String motivacion = "Me gusta pensar";
        LocalDate fecha = LocalDate.of(2020, 12, 12);
        String URLDocExtras = "www.google.com";
        Postulacion Ultima = null;
//		try {
//			Ultima = ICU.crearPostulacion(nick, curriculumVitae, motivacion, fecha, URLDocExtras, OLpensador,"direccion video");
//		} catch (ExceptionUsuarioNoEncontrado e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        Ultima.getCV();
//        Ultima.getFecha();
//        Ultima.getMotivacion();
//        Ultima.getOfertaLaboral();
//        Ultima.getPostulante();
//        Ultima.getuRLDocExtras();
//        Ultima.setCV(Ultima.getCV());
//        Ultima.setFecha(LocalDate.of(2020, 12, 12));
//        Ultima.setMotivacion("Me gusta pensar");
//        Ultima.setOfertaLaboral(OLpensador);
//        Ultima.setPostulante(Ultima.getPostulante());
//        Ultima.setuRLDocExtras("www.google.com");
//        Ultima.esPostulacion(nombre22);
//        Ultima.editarPostulacion(URLDocExtras, motivacion);
        // ------------------------------- operaciones oferta laboral  ---------------------------
//
//        OLpensador.getCiudad();
//        OLpensador.getCosto();
//        OLpensador.getDepartamento();
//        OLpensador.getDescripcion();
//        OLpensador.getEstado();
//        OLpensador.getFechaAlta();
//        OLpensador.getHorario();
//        OLpensador.getImagen();
//        OLpensador.getKeywords();
//        OLpensador.getNombre();
//        OLpensador.getPaquete();
//        OLpensador.getPostulaciones();
//        OLpensador.getRemuneracion();
//        OLpensador.getTipoOferta();
//        try {
//			OLpensador.setCiudad("Montevideo");
//		} catch (ExceptionCiudadInvalida e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			OLpensador.setCosto(1);
//		} catch (ExceptionCostoPaqueteNoNegativo e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        OLpensador.setDepartamento(DepUY.Montevideo);
//        OLpensador.setDescripcion("Pensador de sistemas");
//        OLpensador.setEstado(EstadoOL.Ingresada);
//        try {
//			OLpensador.setFechaAlta(LocalDate.of(2020, 12, 12));
//		} catch (ExceptionPaqueteNoVigente e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        OLpensador.setHorario(horario2);
//        OLpensador.setImagen(img.getBytes());
//        OLpensador.setKeywords(OLpensador.getKeywords());
//        OLpensador.setNombre("pensador");
//        try {
//			OLpensador.setPaquete(Pack);
//		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			OLpensador.setRemuneracion(OLpensador.getRemuneracion());
//		} catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        try {
//			OLpensador.setTipoOferta(ofertaLaboralSalada);
//		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        //  -------------------- agrego a oferta laborla la postulacion -----------------
//        List<Postulacion> stringList = new ArrayList<>();
//        stringList.add(Ultima);
//        OLpensador.setPostulaciones(stringList);
//        // ojo dice nombre,  pero es con nickname
//        DTOfertaExtendidoConKeywordsPostulante auxi9 = OLpensador.infoOfertaLaboralPost("ReneDescartes");
//        auxi9.getCiudad();
//        auxi9.getCosto();
//        auxi9.getDatosPostulacion();
//        auxi9.getDepartamento();
//        auxi9.getDescripcion();
//        auxi9.getEstado();
//        auxi9.getFechaAlta();
//        auxi9.getHorario();
//        auxi9.getImagen();
//        auxi9.getKeywords();
//        auxi9.getNombre();
//        auxi9.getRemuneracion();
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
