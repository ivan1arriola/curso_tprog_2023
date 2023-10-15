package main.java.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTHora;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

import static org.junit.jupiter.api.Assertions.assertFalse;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioNickRepetido;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ctrlOfertaTest {

    private ICtrlOferta ctrlOferta;

    @BeforeEach
    void setUp() {
        ctrlOferta = Fabrica.getInstance().getICtrlOferta();
    }

    @Test
    void darDeAltaTipoPublicacionExitoso() {
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        boolean resultado = ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);

        assertTrue(resultado,  "El alta debería ser exitosa");
    }

    @Test
    void darDeAltaTipoPublicacionFallaNombreVacio() {
        // Caso donde el nombre está vacío
        String nombre = "";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaDescripcionVacia() {
        // Caso donde la descripción está vacía
        String nombre = "NombreTipoPublicacion";
        String descripcion = "";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaExposicionInvalida() {
        // Caso donde la exposición es menor que 1
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 0;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaDuracionInvalida() {
        // Caso donde la duración es menor que 1
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 0;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaCostoNegativo() {
        // Caso donde el costo es negativo
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = -10.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaFechaAltaNula() {
        // Caso donde la fecha de alta es nula
        String nombre = "NombreTipoPublicacion";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = null;

        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });
    }

    @Test
    void darDeAltaTipoPublicacionFallaNombreExistente() {
        // Caso donde ya existe una oferta con el mismo nombre
        String nombre = "NombreExistente";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);

        // Agregar una oferta con el mismo nombre
        boolean resultado1 = ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);

        // Intentar agregar otra oferta con el mismo nombre debería lanzar una excepción
        assertThrows(IllegalArgumentException.class,  () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        });

        assertTrue(resultado1,  "El alta debería ser exitosa para la primera oferta");
    }
    
    
    
    @Test
    void existeOfertaExistente() {
        /*String nombreOferta = "OfertaExistente";
        String descripcion = "Descripción de la oferta";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);*/

        String empresaN = "Apple Com.";
    	ICtrlUsuario ICU = Fabrica.getInstance().getICtrlUsuario();
    	try {
			ICU.altaEmpresa("Apple Com.",  "Steve",  "Jobs",  "stevejobs1@hotmail.com",  "Apple Co.",  "Vendemos celulares caros pero buenos.");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
				| ExceptionUsuarioNickRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        String tipoO = "NombreTipoPublicacion";
        String nombreOferta = "Ofertita";
        String desc = "que te importa";
        LocalDate fechaAlta = LocalDate.of(2023,  9,  2);
       
        DTHora horaDesde = new DTHora(9,  0);  
        DTHora horaHasta = new DTHora(17,  30); 
        DTHorario horario = new DTHorario(horaDesde,  horaHasta);
        float remun = 500;
        String ciu = "mdeo";
        DepUY depto = DepUY.Salto;
        Set<String> keys =  new HashSet<>();
        
        
        // Agregar una oferta utilizando la función altaTipoPublicacionOL
        //boolean resultadoAlta = ctrlOferta.altaTipoPublicacionOL(nombreOferta,  descripcion,  exposicion,  duracion,  costo,  fechaAlta);
        
        String  img233 = "url";
		
        
        boolean resultadoAlta = ctrlOferta.altaOfertaLaboral(empresaN,  
        													 tipoO, 
        													 nombreOferta, 
        													 desc, 
        													 horario, 
        													 remun, 
        													 ciu, 
        													 depto, 
        													 fechaAlta, 
        													 keys, 
        													 EstadoOL.Confirmada, 
        													 img233, 
        													 null);

        
       // Verificar que el alta haya sido exitosa
        assertTrue(resultadoAlta,  "El alta de la oferta debería ser exitosa");

        // Verificar que la oferta exista utilizando existeOferta
        boolean resultado = ctrlOferta.existeOferta(nombreOferta);

        assertTrue(resultado,  "La oferta debería existir");
    }
    
    @Test
    void testExisteOfertaNoExistente() {
        String nombreOferta = "OfertaNoExistente";

        // Verificar que la oferta no exista utilizando existeOferta
        boolean resultado = ctrlOferta.existeOferta(nombreOferta);

        assertFalse(resultado,  "La oferta no debería existir");
    }

}







