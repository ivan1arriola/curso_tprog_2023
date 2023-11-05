package logica.controladores;

import excepciones.*;
import logica.Fabrica;
import logica.Utils;
import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlCargaDeDatos;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CtrlCargaDeDatos implements ICtrlCargaDeDatos {

    ICtrlOferta ctrlOferta;
    ICtrlUsuario ctrlUsuario;
    Utils utils;

    public CtrlCargaDeDatos() {
        Fabrica fabrica = Fabrica.getInstance();
        ctrlUsuario = fabrica.getICtrlUsuario();
        ctrlOferta = fabrica.getICtrlOferta();
        utils = new Utils();
    }

    public void cargarDatos() throws ExcepcionKeywordVacia, ExceptionValidezNegativa {

        cargarUsuarios();
        cargarTipoPublicacion();
        cargarKeywords();
        cargarPaquetes();
        cargarOfertasLaborales();
        cargarPostulaciones();
        cargarTiposPublicacionPaquetes();
        cargarPaquetesCompras();
    }




    // Cargar Usuarios
    private void cargarUsuarios() {
        Map<String, String[]> usuarioCSV = utils.getUsuarioCSV();

        for (Map.Entry<String, String[]> entry : usuarioCSV.entrySet()) {
            String user = entry.getKey(); // Clave del mapa, que es el codigo del usuario
            String[] userData = entry.getValue(); // Datos de usuario

            String tipo = userData[1];
            boolean existe = ctrlUsuario.existeUsuarioConNickname(userData[2]);

            if (!existe && tipo.equals("P")) {
                cargarUsuarioPostulante(user, userData);
            } else if (!existe && tipo.equals("E")) {
                cargarUsuarioEmpresa(user, userData);
            }
        }
    }


    private void cargarUsuarioPostulante(String user, String[] usuariosCSV) {
        Map<String, String[]> postulanteCSV = utils.getPostulanteCSV();
        String[] postulanteData = postulanteCSV.get(user);

        if (postulanteData != null) {
            String dateString = postulanteData[1];
            LocalDate localDate = utils.obtenerFechaDesdeString(dateString, "d/M/yyyy");
            byte[] imagen = null;
            try {
                imagen = utils.descargarImagen(usuariosCSV[7]);
            } catch (IOException e) {
                // Manejar la excepción adecuadamente
                e.printStackTrace();
            }
            ctrlUsuario.altaPostulanteImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], localDate, usuariosCSV[5], postulanteData[2], imagen);
        }
    }


    private void cargarUsuarioEmpresa(String user, String[] usuariosCSV) {
        Map<String, String[]> empresasCSV = utils.getEmpresaCSV();

        String[] empresaData = empresasCSV.get(user);
        if (empresaData != null) {
            byte[] imagen = null;
            try {
                imagen = utils.descargarImagen(usuariosCSV[7]);
            } catch (Exception e) {
                // Manejar la excepción adecuadamente
                e.printStackTrace();
            }

            if (empresaData.length == 2) {
                try {
                    ctrlUsuario.altaEmpresaImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], usuariosCSV[5], empresaData[1], imagen);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else if (empresaData.length == 3) {
                try {
                    ctrlUsuario.altaEmpresaURLyImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], usuariosCSV[5], empresaData[1], empresaData[2], imagen);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Cargar Tipo de Publicación
    private void cargarTipoPublicacion() {
        Map<String, String[]> tipoPublicacionCSV = utils.getTipoPublicacionCSV();

        for (Map.Entry<String, String[]> entry : tipoPublicacionCSV.entrySet()) {
            String[] tipoPublicacionData = entry.getValue(); // Datos del tipo de publicación

            LocalDate fechaLocal = utils.obtenerFechaDesdeString(tipoPublicacionData[6], "d/M/yyyy");

            if (!TipoOfertaHandler.getInstance().existe(tipoPublicacionData[1])) {
                ctrlOferta.altaTipoPublicacionOL(tipoPublicacionData[1], tipoPublicacionData[2], Integer.parseInt(tipoPublicacionData[3]), Integer.parseInt(tipoPublicacionData[4]), Float.parseFloat(tipoPublicacionData[5]), fechaLocal);
            }
        }
    }


    // Cargar Palabras Clave
    private void cargarKeywords() {
        Map<String, String[]> keywordsCSV = utils.getKeywordsCSV();

        for (Map.Entry<String, String[]> entry : keywordsCSV.entrySet()) {
            String[] keywordData = entry.getValue(); // Datos de la palabra clave
            try {
                if (!KeywordHandler.getInstance().existe(keywordData[1])) {
                    ctrlOferta.altaKeyword(keywordData[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Cargar Paquetes
    private void cargarPaquetes() {
        Map<String, String[]> paquetesCSV = utils.getPaquetesCSV();

        for (Map.Entry<String, String[]> entry : paquetesCSV.entrySet()) {
            String[] paqueteData = entry.getValue(); // Datos del paquete

            try {
                byte[] imagen = utils.descargarImagen(paqueteData[7]);
                LocalDate fecha = utils.obtenerFechaDesdeString(paqueteData[5], "d/M/yyyy");

                if (!PaqueteHandler.getInstance().existe(paqueteData[1])) {
                    String[] splitDescuento = paqueteData[3].split(" ");
                    int descuento = Integer.parseInt(splitDescuento[0]);
                    ctrlOferta.altaPaqueteOL(paqueteData[1], paqueteData[2], descuento, fecha, Float.valueOf(paqueteData[4]), imagen);
                }
            } catch (IOException | NumberFormatException | ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
                e.printStackTrace();
            }
        }
    }


    // Cargar Ofertas Laborales
    private void cargarOfertasLaborales() {
        Map<String, String[]> ofertaLaboralCSV = utils.getOfertasLaboralesCSV();

        for (Map.Entry<String, String[]> entry : ofertaLaboralCSV.entrySet()) {
            String ofertaLaboralId = entry.getKey(); // Clave del mapa, que es el código de la oferta laboral
            String[] ofertaLaboralData = entry.getValue(); // Datos de la oferta laboral
            String ofertaNombre = ofertaLaboralData[1];
            try {
                String imageUrl = ofertaLaboralData[12];
                byte[] imagen;
                try {
                    imagen = utils.descargarImagen(imageUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                    imagen = null;
                }

                DTHorario horario = utils.obtenerHorario(ofertaLaboralData[5]);
                LocalDate fecha = utils.obtenerFechaDesdeString(ofertaLaboralData[9], "d/M/yyyy");
                String nickname_empresa = utils.buscarNicknameEnUsuarioCSV(ofertaLaboralData[7]);
                String tipodePublicacion = utils.buscarTipoPublicacion(ofertaLaboralData[8]);
                List<String> keys = utils.buscarPalabrasClave(ofertaLaboralId);

                // controlar caso rio negro
                DepUY dep = utils.obtenerDepUYDesdeNombre(ofertaLaboralData[3]);
                EstadoOL estado = utils.obtenerEstadoDesdeString(ofertaLaboralData[10]);
                String paq = ofertaLaboralData[11];

                if (paq.equals("Sin paquete")) {
                    paq = null;
                } else {
                    paq = utils.buscarPaquete(paq);
                }

                if (!ctrlOferta.existeOfertaLaboral(ofertaLaboralId)) {
                    utils.altaOfertaLaboralForzado(nickname_empresa, tipodePublicacion, ofertaNombre, ofertaLaboralData[2], horario, Float.valueOf(ofertaLaboralData[6]), ofertaLaboralData[4], dep, fecha, keys, estado, imagen, paq);
                }
            } catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida | NumberFormatException | ExceptionRemuneracionOfertaLaboralNegativa eune) {
                eune.printStackTrace();
            }
        }
    }


    // Cargar Tipos de Publicación en Paquetes
    private void cargarTiposPublicacionPaquetes() {
        Map<String, String[]> tiposPublicacionPaquetesCSV = utils.getTipoPubPaquetesCSV();

        for (Map.Entry<String, String[]> entry : tiposPublicacionPaquetesCSV.entrySet()) {
            String[] tipoPublicacionPaqueteData = entry.getValue(); // Datos del tipo de publicación en paquete

            String paquete = utils.buscarPaquete(tipoPublicacionPaqueteData[1].substring(1));
            String tipoPublicacion = utils.buscarTipoPublicacion(tipoPublicacionPaqueteData[2].substring(1));
            int cantidad = Integer.parseInt(tipoPublicacionPaqueteData[3].substring(1));

            try {
                ctrlOferta.agregarTipoOfertaPaq(paquete, tipoPublicacion, cantidad);
            } catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
                e.printStackTrace();
            }
        }
    }


    // Cargar Compras de Paquetes
    private void cargarPaquetesCompras() {
        Map<String, String[]> paquetesComprasCSV = utils.getPaquetesCompraCSV();

        for (Map.Entry<String, String[]> entry : paquetesComprasCSV.entrySet()) {
            String[] compraPaqueteData = entry.getValue(); // Datos de la compra de paquete

            String nickname_e = compraPaqueteData[1];
            String paq = compraPaqueteData[2];

            // Buscar el nickname en el archivo de Usuarios
            String nicknameEncontrado = utils.buscarNicknameEnUsuarioCSV(nickname_e);
            if (nicknameEncontrado != null) {
                nickname_e = nicknameEncontrado;
            }

            // Buscar el paquete en el archivo de Paquetes
            String paqueteEncontrado = utils.buscarPaquete(paq);
            if (paqueteEncontrado != null) {
                paq = paqueteEncontrado;
            }

            LocalDate fecha = utils.obtenerFechaDesdeString(compraPaqueteData[3], "d/M/yyyy");

            try {
                ctrlOferta.compraPaquetes(nickname_e, paq, fecha, Integer.parseInt(compraPaqueteData[4]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Cargar Postulaciones
    public void cargarPostulaciones() {
        Map<String, String[]> postulacionesCSV = utils.getPostulacionesCSV();

        for (Map.Entry<String, String[]> entry : postulacionesCSV.entrySet()) {
            String postulacionId = entry.getKey(); // Clave del mapa, que es el código de la postulación
            String[] postulacionData = entry.getValue(); // Datos de la postulación

            String usuario = utils.buscarNicknameEnUsuarioCSV(postulacionData[1]);
            String ofertaLaboral = utils.buscarOfertaLaboral(postulacionData[5]);

            // Obtener la fecha
            String fechaStr = postulacionData[4];
            LocalDate fecha = utils.obtenerFechaDesdeString(fechaStr, "d/M/yyyy");

            // No hay URLDocExtras, por eso el ""
            try {
                utils.altaPostulacionForzado(ofertaLaboral, usuario, postulacionData[2], postulacionData[3], "", fecha);// Manejar la excepción aquí si es necesario
            } catch (OfertaLaboralNoEncontrada e) {
                throw new RuntimeException(e);
            } catch (ExceptionUsuarioNoEncontrado e) {
                throw new RuntimeException(e);
            }
        }
    }

}
