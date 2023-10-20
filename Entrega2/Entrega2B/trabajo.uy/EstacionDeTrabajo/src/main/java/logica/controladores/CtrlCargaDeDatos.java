package main.java.logica.controladores;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;
import main.java.excepciones.*;
import main.java.logica.Fabrica;
import main.java.logica.Utils;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlCargaDeDatos;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

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
        utils.readCSV("/main/datos/Usuarios.csv", usuariosCSV -> {
            String user = usuariosCSV[0];
            String tipo = usuariosCSV[1];

            if (tipo.equals("P")) {
                cargarUsuarioPostulante(user, usuariosCSV);
            } else if (tipo.equals("E")) {
                cargarUsuarioEmpresa(user, usuariosCSV);
            }
        });
    }

    private void cargarUsuarioPostulante(String user, String[] usuariosCSV) {
        utils.readCSV("/main/datos/Usuarios-Postulantes.csv", postulantesCSV -> {
            String user1 = postulantesCSV[0];

            if (user.equals(user1)) {
                String dateString = postulantesCSV[1];
                LocalDate localDate = utils.obtenerFechaDesdeString(dateString, "d/M/yyyy");
                byte[] imagen = null;
				try {
					imagen = utils.descargarImagen(usuariosCSV[7]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                ctrlUsuario.altaPostulanteImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], localDate, usuariosCSV[5], postulantesCSV[2], imagen);
            }
        });
    }

    private void cargarUsuarioEmpresa(String user, String[] usuariosCSV) {
        utils.readCSV("/main/datos/Usuarios-Empresas.csv", empresasCSV -> {
            String user2 = empresasCSV[0];
            byte[] imagen = null;
            try {
            	imagen = utils.descargarImagen(usuariosCSV[7]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (user.equals(user2)) {
                try {
                    if (empresasCSV.length == 2) {
                        ctrlUsuario.altaEmpresaImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], usuariosCSV[5], empresasCSV[1], imagen);
                    } else {
                        ctrlUsuario.altaEmpresaURLyImagen(usuariosCSV[2], usuariosCSV[6], usuariosCSV[3], usuariosCSV[4], usuariosCSV[5], empresasCSV[1], empresasCSV[2], imagen);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Cargar Tipo de Publicación
    private void cargarTipoPublicacion() {
        utils.readCSV("/main/datos/TipoPublicacion.csv", tipoPublicacionCSV -> {
            LocalDate fechaLocal = utils.obtenerFechaDesdeString(tipoPublicacionCSV[6], "d/M/yyyy");
            ctrlOferta.altaTipoPublicacionOL(tipoPublicacionCSV[1], tipoPublicacionCSV[2], Integer.parseInt(tipoPublicacionCSV[3]), Integer.parseInt(tipoPublicacionCSV[4]), Float.valueOf(tipoPublicacionCSV[5]), fechaLocal);
        });
    }

    // Cargar Palabras Clave
    private void cargarKeywords() {
        utils.readCSV("/main/datos/Keywords.csv", keywordsCSV -> {
            try {
                ctrlOferta.altaKeyword(keywordsCSV[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Cargar Paquetes
    private void cargarPaquetes() {
        utils.readCSV("/main/datos/Paquetes.csv", paquetesCSV -> {
            try {
                byte[] imagen = utils.descargarImagen(paquetesCSV[7]);
                LocalDate fecha = utils.obtenerFechaDesdeString(paquetesCSV[5], "d/M/yyyy");
                ctrlOferta.altaPaqueteOL(paquetesCSV[1], paquetesCSV[2], Integer.parseInt(paquetesCSV[3].split(" ")[0]), fecha, Float.valueOf(paquetesCSV[4]), imagen);
            } catch (IOException | NumberFormatException | ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
                e.printStackTrace();
            }
        });
    }

    // Cargar Ofertas Laborales
    private void cargarOfertasLaborales() {
        utils.readCSV("/main/datos/OfertasLaborales.csv", ofertaLaboralCSV -> {
            String imageUrl = ofertaLaboralCSV[12];
            byte[] imagen;
            try {
                imagen = utils.descargarImagen(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                imagen = null;
            }

            DTHorario horario = utils.obtenerHorario(ofertaLaboralCSV[5]);
            LocalDate fecha = utils.obtenerFechaDesdeString(ofertaLaboralCSV[9], "d/M/yyyy");
            String nickname_empresa = utils.buscarNicknameEnUsuarioCSV(ofertaLaboralCSV[7]);
            String tipodePublicacion = utils.buscarTipoPublicacion(ofertaLaboralCSV[8]);
            List<String> keys = utils.buscarPalabrasClave(ofertaLaboralCSV[0]);

            DepUY dep = utils.obtenerDepUYDesdeNombre(ofertaLaboralCSV[3]);
            EstadoOL estado = utils.obtenerEstadoDesdeString(ofertaLaboralCSV[10]);
            String paq = ofertaLaboralCSV[11];

            if (ofertaLaboralCSV[11].equals("Sin paquete")) {
                paq = null;
            } else {
                paq = utils.buscarPaquete(ofertaLaboralCSV[11]);
            }

            try {
                utils.altaOfertaLaboralForzado(nickname_empresa, tipodePublicacion, ofertaLaboralCSV[1], ofertaLaboralCSV[2], horario, Float.valueOf(ofertaLaboralCSV[6]), ofertaLaboralCSV[4], dep, fecha, keys, estado, imagen, paq);
            } catch (ExceptionUsuarioNoEncontrado eune) {
                eune.printStackTrace();
            } catch (ExceptionEmpresaInvalida eei) {
                eei.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ExceptionRemuneracionOfertaLaboralNegativa e) {
                e.printStackTrace();
            }
        });
    }

    // Cargar Tipos de Publicación en Paquetes
    private void cargarTiposPublicacionPaquetes() {
        utils.readCSV("/main/datos/TiposPublicacionPaquetes.csv", camposTiposPublicacion -> {
            String paquete = utils.buscarPaquete(camposTiposPublicacion[1].substring(1));
            String tipoPublicacion = utils.buscarTipoPublicacion(camposTiposPublicacion[2].substring(1));
            int cantidad = Integer.parseInt(camposTiposPublicacion[3].substring(1));
            try {
                ctrlOferta.agregarTipoOfertaPaq(paquete, tipoPublicacion, cantidad);
            } catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
                e.printStackTrace();
            }
        });
    }

    // Cargar Compras de Paquetes
    private void cargarPaquetesCompras() {
        utils.readCSV("/main/datos/PaquetesCompras.csv", camposCompras -> {
            String nickname_e = camposCompras[1];
            String paq = camposCompras[2];

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

            LocalDate fecha = utils.obtenerFechaDesdeString(camposCompras[3], "d/M/yyyy");

            try {
                ctrlOferta.compraPaquetes(nickname_e, paq, fecha, Integer.parseInt(camposCompras[4]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Cargar Postulaciones
    public void cargarPostulaciones() {
        utils.readCSV("/main/datos/Postulaciones.csv", camposPostulaciones -> {
            String usuario = utils.buscarNicknameEnUsuarioCSV(camposPostulaciones[1]);
            String ofertaLaboral = utils.buscarOfertaLaboral(camposPostulaciones[5]);

            // Obtener la fecha
            String fechaStr = camposPostulaciones[4];
            LocalDate fecha = utils.obtenerFechaDesdeString(fechaStr, "d/M/yyyy");

            // No hay URLDocExtras, por eso el ""
            if (utils.altaPostulacionForzado(ofertaLaboral, usuario, camposPostulaciones[2], camposPostulaciones[3], "", fecha)) {
                // EXCEPCIÓN
            }
        });
    }
}
