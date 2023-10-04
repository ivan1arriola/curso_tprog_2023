package main.java.logica.Datatypes;

import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsPostulante extends DTOfertaExtendidoSinPConK {
	private DTPostulacion datos_postulacion;
	 
	public DTOfertaExtendidoConKeywordsPostulante(String nombre, String descripcion, LocalDate fechaA, float c, float remu, DTHorario horario, DepUY departamento, String ciudad, EstadoOL estado, byte[] img, HashSet<String> keys, DTPostulacion posts) {
		super(nombre, descripcion, fechaA, c, remu, horario, departamento, ciudad, estado, img, keys);
		datos_postulacion = posts;
	}
	  
	// Getters y setters
	public DTPostulacion getDatosPostulacion() {
		return datos_postulacion;
	}

}
