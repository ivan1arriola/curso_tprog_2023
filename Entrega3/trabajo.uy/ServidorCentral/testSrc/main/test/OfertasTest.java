package main.test;

import excepciones.*;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTTipoOferta;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlCargaDeDatos;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.Fabrica;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class OfertasTest {

    private static ICtrlUsuario ICU;
    private static ICtrlOferta ICO;
    private static ICtrlCargaDeDatos ICC;
    private static Fabrica fabri;

    @BeforeAll
    static void setUp() {
        fabri = Fabrica.getInstance();
        ICU = fabri.getICtrlUsuario();
        ICO = fabri.getICtrlOferta();
        ICC = fabri.getICtrlCargaDeDatos();
        // Puedes realizar configuraciones adicionales aquí
    }

    @Test
    void altaPostulanteTest() {


        // ------------------- postulante sin imagen -------------------
        String nickname = "NickPrueba";
        String password = "notedigo";
        String nombre = "Juan";
        String apellido = "Perez";
        String correo = "algunmail@mail.com";
        LocalDate fechaNacimiento = LocalDate.of(1958, 4, 24);
        String nacionalidad = "Ruso";

        try {
            ICU.altaPostulante(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad);
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioNickRepetido e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }

        DTUsuario usu1 = ICU.obtenerDatosUsuario("NickPrueba");

        boolean result1 = usu1.getNickname().equals(nickname)
                &&
                usu1.getNombre().equals(nombre)
                &&
                usu1.getApellido().equals(apellido)
                &&
                usu1.getcorreoElectronico().equals(correo)
                &&
                usu1.getImagen() == null
                &&
                usu1.getcontrasenia().equals(password);

        assertEquals("El test usu1 fallo", true, result1);

        String offer = "EjemploOferta";
        String desc = "descripcion";
        int valido = 10;
        LocalDate fecha = LocalDate.of(1990, 6, 24);
        float descu = 10;

        //boolean boolPaquete = ICO.altaPaqueteOL(offer,  desc,   valido,  fecha, descu, null);

        boolean boolPaquete = false;
        try {
            boolPaquete = ICO.altaPaqueteOL(offer, desc, valido, fecha, descu, null);
        } catch (ExceptionDescuentoInvalido exc) {
            exc.printStackTrace();
        } catch (ExceptionValidezNegativa exc) {
            exc.printStackTrace();
        }


        assertTrue(boolPaquete, "oferta ok");

        assertThrows(IllegalArgumentException.class, () -> {
            ICO.altaPaqueteOL("", desc, -10, fecha, descu, null);
        });

    }

    @Test
    void altaPostulanteValido() {

        String nickname = "Otronick";
        String password = "notedigo";
        String nombre = "Juancito";
        String apellido = "Perez";
        String correo = "algunotromail@mailing.com";
        LocalDate fechaNacimiento = LocalDate.of(1958, 4, 24);
        String nacionalidad = "Ruso";

        try {
            ICU.altaPostulante(nickname, password, nombre, apellido, correo, fechaNacimiento, nacionalidad);
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioNickRepetido e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }


        String offer = "EjemploOferta";
        String desc = "descripcion";
        int valido = -10;
        LocalDate fecha = LocalDate.of(1990, 6, 24);
        float descu = 10;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ICO.altaPaqueteOL(offer, desc, valido, fecha, descu, null);
        });

        assertEquals("El argumento 'validez' debe ser mayor a 0.", exception.getMessage());

    }

    @Test
    void altaPostulanteDescuInvalido() {
        Fabrica fabri = Fabrica.getInstance();

        ICtrlOferta ICO = fabri.getICtrlOferta();


        String offer = "Ejemploito";
        String desc = "descripcioncita";
        int valido = 10;
        LocalDate fecha = LocalDate.of(1995, 6, 24);
        float descu = 120;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ICO.altaPaqueteOL(offer, desc, valido, fecha, descu, null);
        });

        assertEquals("El argumento 'descuento' debe ser un porcentaje mayor o igual a 0 y menor o igual a 100.", exception.getMessage());

    }


    @Test
    void altaPostulanteDesc() {

        String offer = "Demasiadooooo";
        String desc = "";
        int valido = 10;
        LocalDate fecha = LocalDate.of(1995, 6, 24);
        float descu = 15;


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ICO.altaPaqueteOL(offer, desc, valido, fecha, descu, null);
        });

        assertEquals("El argumento 'descripcion' no puede ser vacío.", exception.getMessage());


        //ICO.altaPaqueteOL(offer,  "algo", valido,  fecha, descu, null);

        try {
            ICO.altaPaqueteOL(offer, "algo", valido, fecha, descu, null);
        } catch (ExceptionDescuentoInvalido exc) {
            exc.printStackTrace();
        } catch (ExceptionValidezNegativa exc) {
            exc.printStackTrace();
        }

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            ICO.altaPaqueteOL("Demasiado", "una descripcion", valido, fecha, descu, null);
        });

        assertEquals("El argumento 'nombre' ya existe en el sistema.", exception2.getMessage());

    }


    @Test
    void keysTest() {
        Fabrica fabri = Fabrica.getInstance();

        ICtrlOferta ICO = fabri.getICtrlOferta();
        boolean keys1 = ICO.altaKeyword("key1");
        assertTrue(keys1, "Alta key ok");

        boolean keys2 = ICO.altaKeyword("key1");
        assertFalse(keys2, "Alta key repetida");

    }

    @Test
    void compraPaqTest() {
        try {
            ICU.altaEmpresa("Empresario", "tupass", "Pepito", "Gomez", "yoquese@hotmail.com", "geniales");
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioNickRepetido e) {
            e.printStackTrace();
        } catch (ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }
        LocalDate atrfechaAlta6 = LocalDate.of(2020, 12, 12);


        boolean comprado = false;

        try {
            comprado = ICO.compraPaquetes("Empresario", "EjemploOferta", atrfechaAlta6, 0);
        } catch (ExceptionCompraPaqueteConValorNegativo exc) {
            exc.printStackTrace();
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            exc.printStackTrace();
        }


        assertTrue(comprado, "Compra ok");

    }

    @Test
    void cargandoTest() {
        ICC.cargarDatos();
        Set<String> empresas = ICO.listarEmpresas();
        boolean contiene = empresas.contains("ANTEL");
        assertTrue(contiene);

        Set<String> ingresadas = ICO.listarOfertasLaboralesIngresadas("ANTEL");
        boolean ingresoNoVacio = !ingresadas.isEmpty();
        assertTrue(ingresoNoVacio);

        ICO.aceptoOL("Content Manager");
        Set<DTOfertaExtendido> confirmadas = ICO.listarOfertasLaboralesConfirmadas();
        boolean esta = false;
        for (DTOfertaExtendido elem : confirmadas) {
            if (elem.getNombre().equals("Content Manager")) {
                esta = true;

            }
        }
        assertTrue(esta);

        Set<String> postu = ICO.listarPostulantes();
        assertTrue(postu.contains("lgarcia"));

        DTOfertaExtendido dtof = ICO.obtenerOfertaLaboral("Gerente de Proyecto");
        assertEquals(dtof.getCiudad(), "Montevidoe");


        try {
            ICO.altaPaqueteOL("Demasiado", "una descripcion", 10, LocalDate.of(1958, 10, 10), 90, null);
        } catch (ExceptionDescuentoInvalido exc) {
            exc.printStackTrace();
        } catch (ExceptionValidezNegativa exc) {
            exc.printStackTrace();
        }

        LocalDate atrfechaAlta6 = LocalDate.of(2023, 9, 2);


        //ICO.compraPaquetes("ANTEL", "Demasiado", atrfechaAlta6 , 0);

        try {
            ICO.compraPaquetes("ANTEL", "Demasiado", atrfechaAlta6, 0);
        } catch (ExceptionCompraPaqueteConValorNegativo exc) {
            exc.printStackTrace();
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            exc.printStackTrace();
        }


        assertTrue(ICO.paqueteComprado("Demasiado"));


        Set<String> keysLista = ICO.listarKeywords();
        assertTrue(keysLista.contains("Freelance"));

        DTTipoOferta dtoferta = null;

        try {
            dtoferta = ICO.obtenerDatosTO("Destacada");
        } catch (ExcepcionTipoOfertaNoExistente e) {
            e.printStackTrace();
        }

        assertEquals(dtoferta.getDescripcion(), "Destaca tu anuncio");


    }


}
	
	
	
	
	

