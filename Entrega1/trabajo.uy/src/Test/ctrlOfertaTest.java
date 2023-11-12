package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        boolean resultado = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);

        assertTrue(resultado, "El alta debería ser exitosa");
    }

    @Test
    void darDeAltaTipoPublicacionFallaNombreVacio() {
        // Caso donde el nombre está vacío
        String nombre = "";
        String descripcion = "Descripción del tipo de publicación";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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

        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
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
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        // Agregar una oferta con el mismo nombre
        boolean resultado1 = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);

        // Intentar agregar otra oferta con el mismo nombre debería lanzar una excepción
        assertThrows(IllegalArgumentException.class, () -> {
            ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
        });

        assertTrue(resultado1, "El alta debería ser exitosa para la primera oferta");
    }
    
    
    
    @Test
    void existeOfertaExistente() {
        String nombreOferta = "OfertaExistente";
        String descripcion = "Descripción de la oferta";
        int exposicion = 3;
        int duracion = 7;
        float costo = 100.0f;
        LocalDate fechaAlta = LocalDate.of(2023, 9, 2);

        // Agregar una oferta utilizando la función altaTipoPublicacionOL
        boolean resultadoAlta = ctrlOferta.altaTipoPublicacionOL(nombreOferta, descripcion, exposicion, duracion, costo, fechaAlta);

        // Verificar que el alta haya sido exitosa
        assertTrue(resultadoAlta, "El alta de la oferta debería ser exitosa");

        // Verificar que la oferta exista utilizando existeOferta
        boolean resultado = ctrlOferta.existeOferta(nombreOferta);

        assertTrue(resultado, "La oferta debería existir");
    }
    
    @Test
    void existeOfertaNoExistente() {
        String nombreOferta = "OfertaNoExistente";

        // Verificar que la oferta no exista utilizando existeOferta
        boolean resultado = ctrlOferta.existeOferta(nombreOferta);

        assertFalse(resultado, "La oferta no debería existir");
    }
}