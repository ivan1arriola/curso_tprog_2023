package main.java.logica.controladores;


import java.time.format.DateTimeFormatter; 
import java.util.List;
import main.java.excepciones.ExcepcionKeywordVacia;
import main.java.excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import main.java.excepciones.ExceptionDescuentoInvalido;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.excepciones.ExceptionValidezNegativa;
import main.java.logica.Fabrica;
import main.java.logica.Utils;
import main.java.logica.datatypes.DTHorario; 
import main.java.logica.enumerados.DepUY; 
import main.java.logica.enumerados.EstadoOL; 
import main.java.logica.interfaces.ICtrlCargaDeDatos; 
import main.java.logica.interfaces.ICtrlOferta; 
import main.java.logica.interfaces.ICtrlUsuario;

import java.io.IOException;
import java.time.LocalDate;

public class CtrlCargaDeDatos implements ICtrlCargaDeDatos {
	
	ICtrlOferta ctrlOferta;
	ICtrlUsuario ctrlUsuario;
	public Utils utils;

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
	
	

	 
	
	
	 private void cargarUsuarios() {
		    utils.readCSV("/main/datos/Usuarios.csv", fields -> {
		        String user = fields[0];
		        String tipo = fields[1];

		        if (tipo.equals("P")) {
		            cargarUsuarioPostulante(user, fields);
		        } else if (tipo.equals("E")) {
		            cargarUsuarioEmpresa(user, fields);
		        }
		    });
		}

	 private void cargarUsuarioPostulante(String user, String[] fields) {
		    utils.readCSV("/main/datos/Usuarios-Postulantes.csv", fields1 -> {
		        String user1 = fields1[0];

		        if (user.equals(user1)) {
		            String dateString = fields1[1];
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		            LocalDate localDate = LocalDate.parse(dateString, formatter);

		            ctrlUsuario.altaPostulanteImagen(fields[2], fields[6], fields[3], fields[4], localDate, fields[5], fields1[2], null);
		        }
		    });
		}

	 private void cargarUsuarioEmpresa(String user, String[] fields) {
		    utils.readCSV("/main/datos/Usuarios-Empresas.csv", fields2 -> {
		        String user2 = fields2[0];

		        if (user.equals(user2)) {
		            try {
		                if (fields2.length == 2) {
		                    ctrlUsuario.altaEmpresaImagen(fields[2], fields[6], fields[3], fields[4], fields[5], fields2[1], null);
		                } else {
		                    ctrlUsuario.altaEmpresaURLyImagen(fields[2], fields[6], fields[3], fields[4], fields[5], fields2[1], fields2[2], null);
		                }
		            } catch (IllegalArgumentException e) {
		                e.printStackTrace();
		            }
		        }
		    });
		}



	private void cargarTipoPublicacion() {
		    utils.readCSV("/main/datos/TipoPublicacion.csv", fields -> {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		        LocalDate fechaLocal = LocalDate.parse(fields[6], formatter);

		        ctrlOferta.altaTipoPublicacionOL(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Float.valueOf(fields[5]), fechaLocal);
		    });
		}

	
	private void cargarKeywords() {
		    utils.readCSV("/main/datos/Keywords.csv", fields -> {
		        try {
		            ctrlOferta.altaKeyword(fields[1]);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    });
		}

	
	private void cargarPaquetes() {
		    utils.readCSV("/main/datos/Paquetes.csv", fields -> {
		        try {
		            byte[] imagen = utils.descargarImagen(fields[7]);
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		            LocalDate fecha = LocalDate.parse(fields[5], formatter);
		            ctrlOferta.altaPaqueteOL(fields[1], fields[2], Integer.parseInt(fields[3].split(" ")[0]), fecha, Float.valueOf(fields[4]), imagen);
		        } catch (IOException | NumberFormatException | ExceptionValidezNegativa | ExceptionDescuentoInvalido e) {
		            e.printStackTrace();
		        }
		    });
		}
	
	
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


	private void cargarTiposPublicacionPaquetes() {
	    utils.readCSV("/main/datos/TiposPublicacionPaquetes.csv", camposTiposPublicacion -> {
	        String paquete = utils.buscarPaquete(camposTiposPublicacion[1].substring(1));
	        String tipoPublicacion = utils.buscarTipoPublicacion(camposTiposPublicacion[2].substring(1));
	        int cantidad = Integer.parseInt(camposTiposPublicacion[3].substring(1));
	        try {
				ctrlOferta.agregarTipoOfertaPaq(paquete, tipoPublicacion, cantidad);
			} catch (ExceptionCantidadPositivaDeTipoOfertaEnPaquete e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	}



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