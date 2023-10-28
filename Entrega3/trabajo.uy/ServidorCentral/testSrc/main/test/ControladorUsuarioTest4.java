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
        String nickname = "Google";
        String nombre = "analista";
        String descripcion = "Analista de sistemas";
        DTHora hora1 = new DTHora(8, 0);
        DTHora hora2 = new DTHora(1, 0);
        DTHorario horario = new DTHorario(hora1, hora2);
        float remuneracion = 1000;
        String ciudad = "Montevideo";
        DepUY dep = DepUY.Montevideo;
        LocalDate fechaA = LocalDate.of(2020, 12, 12);

        List<String> pruebaKeyword1 = new ArrayList<>(Arrays.asList(
                "Trabajo nocturno",
                "horario vespertino",
                "full time",
                "part time"
        ));

        EstadoOL estado = EstadoOL.Ingresada;
        String img = "url";
        String paquete = "Paquete 1";
        // public boolean altaOfertaLaboral(String nickname_e,  String tipo,  String nombre,  String descripcion,  DTHorario horario,  float remun,  String ciu,  DepUY dep,  LocalDate FechaA,  List<String> keys,  EstadoOL estado,  byte[] img,  String paquete) throws ExceptionUsuarioNoEncontrado,  ExceptionEmpresaInvalida{
        try {
            ICU.altaOfertaLaboral(nickname, "Oferta normal", nombre, descripcion, horario, remuneracion, ciudad, dep, fechaA, pruebaKeyword1, estado, img, paquete);
        } catch (ExceptionUsuarioNoEncontrado e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExceptionEmpresaInvalida e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//			Set<String> nuevo = 
        ICU.listarPostulantesDeOfertas(nickname, nombre);
//			Set<String> holaaaa = 
        ICU.listarKeywords("analista");

//			DTUsuario gogogo = 
        ICU.obtenerDatosUsuarioEspecial("Google", "Google");
//			gogogo = 
        ICU.obtenerDatosUsuarioEspecial("Google", "Kreves");

//			boolean sefs = 
        ICU.existePostulacion("ASwatzenegger", "analista");

        String nick = "ASwatzenegger";
        String curriculumVitae = "CV";
        String motivacion = "motivacion";
        LocalDate fecha = LocalDate.of(2020, 12, 12);
        String URLDocExtras = "www.google.com";
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        Map<String, OfertaLaboral> mapa = OLH.obtener();
        OfertaLaboral oferta = mapa.get("analista");
//			Postulacion nueva = 
        ICU.crearPostulacion(nick, curriculumVitae, motivacion, fecha, URLDocExtras, oferta);
        ICU.modificarPostulacion("analista", nick, "CV asad", "quiero DORMIR");
        ICU.obtenerDatosPostulacionW("ASwatzenegger", "analista");
        ICU.hayPostulacionW("ASwatzenegger", "analista");
        ICU.listarOfertasLaboralesConfirmadas("Google");
        try {
            ICU.listarOfertasLaborales("Google");
        } catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//			Set<String> nuevamente = 
        ICO.listarOfertasLaboralesKeywords("Trabajo nocturno");
        // esto es para crear una postulacion de verdad
        String nick113 = "LeonardoVinchi";
        ICO.altaPostulacion("analista", nick113, "CV", "descripccion interesante", "wwww.Linkedin.com/usuario", LocalDate.of(2020, 12, 12));

//			nuevamente =  
        ICO.listarPaquetes();

//			nuevamente = 
        ICO.listarTipoDePublicaciones();

//			Set<DTOfertaExtendido> nuevoOfertaEX = 
        ICO.listarOfertasLaboralesConfirmadas();

        ICO.paqueteComprado("Paquete 1");

//			DTOfertaExtendidoSinPConK nuevaExsinpconk = 
        ICO.infoOfertaLaboralEmpresa("Google", "analista");

        String img33 = "url";
        ICO.altaPaqueteOL("Paquete 2", "un paquete bueno", 1, LocalDate.now(), 10.0f, img33);

        // los paquetes empiezan vacios,    se les va agregando tipos de oferta

        ICO.agregarTipoOfertaPaq("Paquete 2", "Oferta normal", 20);
        ICO.agregarTipoOfertaPaq("Paquete 2", "Oferta destacada", 12);

        ICO.listarOfertasLaboralesIngresadas("Google");
        ICO.listarEmpresas();
        ICO.listarPostulantes();
//			

//			DTOfertaExtendido EXTRA = 
        ICO.obtenerOfertaLaboral("analista");

//			DTOfertaExtendidoSinPConK extremo = 
        ICO.infoOfertaLaboralPostulante("Arnold", "analista");


    }
}

