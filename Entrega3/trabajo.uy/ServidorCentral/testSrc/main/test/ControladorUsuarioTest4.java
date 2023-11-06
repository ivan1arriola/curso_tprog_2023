package main.test;


import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioNoEncontrado;
import logica.clases.OfertaLaboral;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.OfertaLaboralHandler;
import logica.Fabrica;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ControladorUsuarioTest4 {

    // testear DATATYPES en general

    // testear DATATYPES de oferta laboral
    @Test
    void testPARAUSUARIo() {
        Fabrica fabri = Fabrica.getInstance();
        ICtrlUsuario ICU = fabri.getICtrlUsuario();
        ICtrlOferta ICO = fabri.getICtrlOferta();
        


    }
}

