package logica.controladores;

import excepciones.AsignarOrdenAOfertaFinalizada;
import excepciones.AsignarOrdenAOfertaNoVencida;
import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
//import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
import excepciones.NoExistePaquete;
import excepciones.OfertaLaboralNoEncontrada;
import logica.Fabrica;
import logica.persistencia.TrabajoUyHistoricoManager;
import logica.Utils;
import logica.clases.Empresa;
import logica.clases.OfertaLaboral;
import logica.clases.Postulacion;
import logica.clases.Postulante;
import logica.datatypes.DTHorario;
import logica.dto.EmpresaDTO;
import logica.dto.OfertaLaboralDTO;
import logica.dto.PostulacionDTO;
import logica.dto.PostulanteDTO;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlCargaDeDatos;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;

import java.io.IOException;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Set;

public class CtrlCargaDeDatos implements ICtrlCargaDeDatos {

    private ICtrlOferta ctrlOferta;
    private ICtrlUsuario ctrlUsuario;
    private Utils utils;

    private OfertaLaboralHandler ofertaLaboralHandler;

    public CtrlCargaDeDatos() {
        Fabrica fabrica = Fabrica.getInstance();
        ctrlUsuario = fabrica.getICtrlUsuario();
        ctrlOferta = fabrica.getICtrlOferta();
        ofertaLaboralHandler = OfertaLaboralHandler.getInstance();
        utils = new Utils();
    }

    public void cargarDatos() {

        cargarUsuarios();
        cargarTipoPublicacion();
        cargarKeywords();
        cargarPaquetes();
        cargarOfertasLaborales();
        cargarPostulaciones();
        cargarTiposPublicacionPaquetes();
        cargarPaquetesCompras();
        cargarOfertasFavoritasPostulantes();
        try {
            cargarResultadoPostulaciones();
        } catch (AsignarOrdenAOfertaNoVencida | AsignarOrdenAOfertaFinalizada | OfertaLaboralNoEncontrada e) {
            throw new RuntimeException(e);
        }
        persistirOfertasFinalizadas();
    }

    private void persistirOfertasFinalizadas() {
        OfertaLaboralHandler ofertaLaboralHandler = OfertaLaboralHandler.getInstance();
        Map<String,  OfertaLaboral> ofertas = ofertaLaboralHandler.obtener();

        TrabajoUyHistoricoManager trabajoUyHistoricoManager = new TrabajoUyHistoricoManager();

        for (Map.Entry<String,  OfertaLaboral> entry : ofertas.entrySet()){
            OfertaLaboral ofertaLaboral = entry.getValue();
            if (ofertaLaboral.getEstado().equals(EstadoOL.Finalizada)){
				TrabajoUyHistoricoManager THM = TrabajoUyHistoricoManager.getInstance();
				Empresa empresa = ofertaLaboral.getEmpresaPublicadora();
				EmpresaDTO empresatransformado = (EmpresaDTO) empresa.getDTO();
				if ( THM.obtenerUsuarioDT(empresatransformado.getNickname()) == null) {
					THM.guardarEmpresa(empresatransformado);
				} else {
					empresatransformado =  (EmpresaDTO) THM.obtenerUsuarioDT(empresatransformado.getNickname());
				}
				
				OfertaLaboralDTO oferta_a_guardar = ofertaLaboral.getDTO();
				THM.guardarOfertaFinalizada(oferta_a_guardar, empresatransformado);     
				
				 List<Postulacion> postulacionesPersistir = ofertaLaboral.getPostulaciones();
				for (Postulacion postulacion :postulacionesPersistir) {
					Postulante postulante = postulacion.getPostulante();
					PostulanteDTO postulantetransformado = (PostulanteDTO) postulante.getDTO();
					if ( THM.obtenerUsuarioDT(postulantetransformado.getNickname()) == null) {
				    	THM.guardarPostulante(postulantetransformado);
				    } else {
				    	postulantetransformado =  (PostulanteDTO) THM.obtenerUsuarioDT(postulantetransformado.getNickname());
				       }
					PostulacionDTO postulacionTransformada = postulacion.getDTO(oferta_a_guardar);
					THM.guardarPostulacion(postulacionTransformada, postulantetransformado);
				}
				THM.cerrarBaseDatos();
            }
        }
    }

    private void cargarOfertasFavoritasPostulantes() {
        Map<String,  String[]> ofertaFavoritasCSV = utils.getPostulantesOfertaLaboralFavoritas();
        Map<String,  String[]> postulantes = utils.getUsuarioCSV();
        Map<String,  String[]> ofertas = utils.getOfertasLaboralesCSV();

        for (Map.Entry<String,  String[]> entry : ofertaFavoritasCSV.entrySet()) {
            String clavePostulante = entry.getValue()[1];
            String claveOferta = entry.getValue()[2];

            String nicknamePostulante = postulantes.get(clavePostulante)[2];
            String nombreOferta = ofertas.get(claveOferta)[1];

            try {
                ctrlOferta.marcarFavorita(nicknamePostulante,  nombreOferta);
            } catch (ExceptionUsuarioNoEncontrado | OfertaLaboralNoEncontrada e) {
                System.err.println(e.getMessage());
            }
        }
    }


    // Cargar Usuarios
    private void cargarUsuarios() {
        Map<String,  String[]> usuarioCSV = utils.getUsuarioCSV();
        Map<String,  String[]> seguidoresCSV = utils.getSeguidoresCSV();

        for (Map.Entry<String,  String[]> entry : usuarioCSV.entrySet()) {
            String user = entry.getKey(); // Clave del mapa,  que es el codigo del usuario
            String[] userData = entry.getValue(); // Datos de usuario

            String tipo = userData[1];
            boolean existe = ctrlUsuario.existeUsuarioConNickname(userData[2]);

            if (!existe && tipo.equals("P")) {
                try {
                    cargarUsuarioPostulante(user,  userData);
                } catch (ExceptionFechaInvalida e) {
                    System.err.println("No se pudo agregar el usuario " + userData[2] + ". Fecha invalida");
                } catch (ErrorAgregarUsuario e) {
                    System.err.println("No se pudo agregar el usuario " + userData[2]);
                }
            } else if (!existe && tipo.equals("E")) {
                cargarUsuarioEmpresa(user,  userData);
            }
        }

        // cargar seguidores
        for (Map.Entry<String,  String[]> entry : seguidoresCSV.entrySet()){
            String claveUsuarioSeguidor = entry.getValue()[1];
            String claveUsuarioSeguido = entry.getValue()[2];

            String nicknameUsuarioSeguidor = usuarioCSV.get(claveUsuarioSeguidor)[2];
            String nicknameUsuarioSeguido = usuarioCSV.get(claveUsuarioSeguido)[2];

            try {
                ctrlUsuario.seguirUsuario(nicknameUsuarioSeguidor,  nicknameUsuarioSeguido);
            } catch (ExceptionUsuarioSeSigueASiMismo e) {
                System.err.println("Usuario se intento seguir a si mismo -" + nicknameUsuarioSeguidor);
            } catch (ExceptionUsuarioNoEncontrado e) {
                System.err.println(nicknameUsuarioSeguido + " - " + nicknameUsuarioSeguidor + " - No se encontro");
            }
        }


    }


    private void cargarUsuarioPostulante(String user,  String[] usuariosCSV) throws ExceptionFechaInvalida,  ErrorAgregarUsuario {
        Map<String,  String[]> postulanteCSV = utils.getPostulanteCSV();
        String[] postulanteData = postulanteCSV.get(user);

        if (postulanteData != null) {
            String dateString = postulanteData[1];
            LocalDate localDate = utils.obtenerFechaDesdeString(dateString,  "d/M/yyyy");
            byte[] imagen = null;
            try {
                imagen = utils.descargarImagen(usuariosCSV[7]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ctrlUsuario.altaPostulanteImagen(usuariosCSV[2],  usuariosCSV[6],  usuariosCSV[3],  usuariosCSV[4],  localDate,  usuariosCSV[5],  postulanteData[2],  imagen);
        }
    }


    private void cargarUsuarioEmpresa(String user,  String[] usuariosCSV) {
        Map<String,  String[]> empresasCSV = utils.getEmpresaCSV();

        String[] empresaData = empresasCSV.get(user);
        if (empresaData != null) {
            byte[] imagen = null;
            try {
                imagen = utils.descargarImagen(usuariosCSV[7]);
            } catch (IOException e) {
                // Manejar la excepción adecuadamente
                e.printStackTrace();
            }

            if (empresaData.length == 2) {
                try {
                    ctrlUsuario.altaEmpresaImagen(usuariosCSV[2],  usuariosCSV[6],  usuariosCSV[3],  usuariosCSV[4],  usuariosCSV[5],  empresaData[1],  imagen);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (ErrorAgregarUsuario e) {
                    throw new RuntimeException(e);
                }
            } else if (empresaData.length == 3) {
                try {
                    ctrlUsuario.altaEmpresaURLyImagen(usuariosCSV[2],  usuariosCSV[6],  usuariosCSV[3],  usuariosCSV[4],  usuariosCSV[5],  empresaData[1],  empresaData[2],  imagen);
                } catch (IllegalArgumentException | ErrorAgregarUsuario e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Cargar Tipo de Publicación
    private void cargarTipoPublicacion() {
        Map<String,  String[]> tipoPublicacionCSV = utils.getTipoPublicacionCSV();

        for (Map.Entry<String,  String[]> entry : tipoPublicacionCSV.entrySet()) {
            String[] tipoPublicacionData = entry.getValue(); // Datos del tipo de publicación

            LocalDate fechaLocal = utils.obtenerFechaDesdeString(tipoPublicacionData[6],  "d/M/yyyy");

            if (!TipoOfertaHandler.getInstance().existe(tipoPublicacionData[1])) {
                ctrlOferta.altaTipoPublicacionOL(tipoPublicacionData[1],  tipoPublicacionData[2],  Integer.parseInt(tipoPublicacionData[3]),  Integer.parseInt(tipoPublicacionData[4]),  Float.parseFloat(tipoPublicacionData[5]),  fechaLocal);
            }
        }
    }


    // Cargar Palabras Clave
    private void cargarKeywords() {
        Map<String,  String[]> keywordsCSV = utils.getKeywordsCSV();

        for (Map.Entry<String,  String[]> entry : keywordsCSV.entrySet()) {
            String[] keywordData = entry.getValue(); // Datos de la palabra clave
            try {
                if (!KeywordHandler.getInstance().existe(keywordData[1])) {
                    ctrlOferta.altaKeyword(keywordData[1]);
                }
            } catch (ExcepcionKeywordVacia exc) {
                exc.printStackTrace();
            }
        }
    }


    // Cargar Paquetes
    private void cargarPaquetes() {
        Map<String,  String[]> paquetesCSV = utils.getPaquetesCSV();

        for (Map.Entry<String,  String[]> entry : paquetesCSV.entrySet()) {
            String[] paqueteData = entry.getValue(); // Datos del paquete

            try {
                byte[] imagen = utils.descargarImagen(paqueteData[7]);
                LocalDate fecha = utils.obtenerFechaDesdeString(paqueteData[5],  "d/M/yyyy");

                if (!PaqueteHandler.getInstance().existe(paqueteData[1])) {
                    String[] splitDescuento = paqueteData[3].split(" ");
                    int descuento = Integer.parseInt(splitDescuento[0]);
                    ctrlOferta.altaPaqueteOL(paqueteData[1],  paqueteData[2],  descuento,  fecha,  Float.valueOf(paqueteData[4]),  imagen);
                }
            } catch (IOException | NumberFormatException | ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
                e.printStackTrace();
            }
        }
    }


    // Cargar Ofertas Laborales
    private void cargarOfertasLaborales() {
        Map<String,  String[]> ofertaLaboralCSV = utils.getOfertasLaboralesCSV();

        for (Map.Entry<String,  String[]> entry : ofertaLaboralCSV.entrySet()) {
            String ofertaLaboralId = entry.getKey(); // Clave del mapa,  que es el código de la oferta laboral
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
                LocalDate fecha = utils.obtenerFechaDesdeString(ofertaLaboralData[9],  "d/M/yyyy");
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
                    utils.altaOfertaLaboralForzado(Integer.parseInt(ofertaLaboralData[13]),nickname_empresa,  tipodePublicacion,  ofertaNombre,  ofertaLaboralData[2],  horario,  Float.parseFloat(ofertaLaboralData[6]),  ofertaLaboralData[4],  dep,  fecha,  keys,  estado,  imagen,  paq);
                    
                }
            } catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida | NumberFormatException | ExceptionRemuneracionOfertaLaboralNegativa eune) {
                eune.printStackTrace();
            } catch (NoExistePaquete e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Cargar Tipos de Publicación en Paquetes
    private void cargarTiposPublicacionPaquetes() {
        Map<String,  String[]> tiposPublicacionPaquetesCSV = utils.getTipoPubPaquetesCSV();

        for (Map.Entry<String,  String[]> entry : tiposPublicacionPaquetesCSV.entrySet()) {
            String[] tipoPublicacionPaqueteData = entry.getValue(); // Datos del tipo de publicación en paquete

            String paquete = utils.buscarPaquete(tipoPublicacionPaqueteData[1].substring(1));
            String tipoPublicacion = utils.buscarTipoPublicacion(tipoPublicacionPaqueteData[2].substring(1));
            int cantidad = Integer.parseInt(tipoPublicacionPaqueteData[3].substring(1));

            try {
                ctrlOferta.agregarTipoOfertaPaq(paquete,  tipoPublicacion,  cantidad);
            } catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
                e.printStackTrace();
            } catch (NoExistePaquete e) {
                throw new RuntimeException(e);
            }
        }
    }


    // Cargar Compras de Paquetes
    private void cargarPaquetesCompras() {
        Map<String,  String[]> paquetesComprasCSV = utils.getPaquetesCompraCSV();

        for (Map.Entry<String,  String[]> entry : paquetesComprasCSV.entrySet()) {
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

            LocalDate fecha = utils.obtenerFechaDesdeString(compraPaqueteData[3],  "d/M/yyyy");

            try {
                ctrlOferta.compraPaquetes(nickname_e,  paq,  fecha,  Integer.parseInt(compraPaqueteData[4]));
            } catch (ExceptionCompraPaqueteConValorNegativo exc) {
                exc.printStackTrace();
            } catch (NumberFormatException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExceptionValidezNegativa exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			} catch (ExceptionUsuarioNoEncontrado exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			} catch (NoExistePaquete exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
        }
    }


    // Cargar Postulaciones
    public void cargarPostulaciones() {
        Map<String,  String[]> postulacionesCSV = utils.getPostulacionesCSV();

        for (Map.Entry<String,  String[]> entry : postulacionesCSV.entrySet()) {
            String postulacionId = entry.getKey(); // Clave del mapa,  que es el código de la postulación
            String[] postulacionData = entry.getValue(); // Datos de la postulación

            String usuario = utils.buscarNicknameEnUsuarioCSV(postulacionData[1]);
            String ofertaLaboral = utils.buscarOfertaLaboral(postulacionData[5]);

            // Obtener la fecha
            String fechaStr = postulacionData[4];
            LocalDate fecha = utils.obtenerFechaDesdeString(fechaStr,  "d/M/yyyy");

            // No hay URLDocExtras,  por eso el ""
            try {
                utils.altaPostulacionForzado(ofertaLaboral,  usuario,  postulacionData[2],  postulacionData[3],  "",  fecha, postulacionData[6]); // Manejar la excepción aquí si es necesario
            } catch (OfertaLaboralNoEncontrada e) {
                throw new RuntimeException(e);
            } catch (ExceptionUsuarioNoEncontrado e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cargarResultadoPostulaciones() throws AsignarOrdenAOfertaNoVencida,  AsignarOrdenAOfertaFinalizada,  OfertaLaboralNoEncontrada {
        Map<String,  String[]> resultadosCSV = utils.getResultadosPostulacionCSV();
        Map<String,  String[]> usuariosCSV = utils.getUsuarioCSV();
        Map<String,  String[]> ofertasCSV = utils.getOfertasLaboralesCSV();

        Map<String,  List<String>> ordenesEnOferta = new HashMap<>();

        for (Map.Entry<String,  String[]> entry : resultadosCSV.entrySet()) {
            String identificadorOferta = entry.getValue()[1];
            String identificadorPostulante = entry.getValue()[2];

            String nombreOfertaFinalizada = ofertasCSV.get(identificadorOferta)[1];
            String nicknamePostulante = usuariosCSV.get(identificadorPostulante)[2];
            int lugarPostulante = Integer.parseInt(entry.getValue()[3]);

            // Verificamos si ya existe una entrada para la oferta en el mapa
            if (!ordenesEnOferta.containsKey(nombreOfertaFinalizada)) {
                ordenesEnOferta.put(nombreOfertaFinalizada,  new ArrayList<>());
            }

            // Añadimos el nickname al list en el orden indicado
            List<String> nicknames = ordenesEnOferta.get(nombreOfertaFinalizada);
            while (nicknames.size() <= lugarPostulante) {
                nicknames.add(null);
            }
            nicknames.set(lugarPostulante,  nicknamePostulante);
        }




        for (Map.Entry<String,  List<String>> entry : ordenesEnOferta.entrySet()) {
            String nombreOferta = entry.getKey();
            List<String> nicknames = entry.getValue();
            // Elimina el primer elemento de la lista si la lista tiene al menos un elemento
            if (!nicknames.isEmpty()) {
                nicknames.remove(0); // Termina de armar las listas que necesito
            }

            OfertaLaboral ofertaLaboral = ofertaLaboralHandler.buscar(nombreOferta);
            EstadoOL estadoOferta = ofertaLaboral.getEstado();
            ofertaLaboral.setEstado(EstadoOL.Confirmada);
            ofertaLaboral.establecerPosicion(nicknames);
            ofertaLaboral.setEstado(estadoOferta);




        }





    }




}


