package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsPostulante extends DTOfertaExtendidoSinPConK {
  private DTPostulacion datospostulacion
	 
public DTOfertaExtendidoConKeywordsPostulante(String nombre,  String descripcion,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY departamento,  String ciudad,  EstadoOL estado,  byte[] img,  Set<String> keys,  DTPostulacion posts) {
  super(nombre,  descripcion,  fechaA,  cost,  remu,  horario,  departamento,  ciudad,  estado,  img,  keys);
  datos_postulacion = posts;
}
	  
// Getters y setters
public DTPostulacion getDatosPostulacion() {
  return datospostulacion;
}

}
