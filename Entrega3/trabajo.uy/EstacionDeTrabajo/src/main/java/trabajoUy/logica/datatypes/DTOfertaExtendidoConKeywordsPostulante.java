package trabajoUy.logica.datatypes;

import java.time.LocalDate;

import java.util.Set;

import trabajoUy.logica.enumerados.DepUY;
import trabajoUy.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsPostulante extends DTOfertaExtendidoSinPConK {
  private DTPostulacion datospostulacion;
	 
public DTOfertaExtendidoConKeywordsPostulante(String nombre,  String descripcion,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY departamento,  String ciudad,  EstadoOL estado,  String img,  Set<String> keys,  DTPostulacion posts) {
  super(nombre,  descripcion,  fechaA,  cost,  remu,  horario,  departamento,  ciudad,  estado,  img,  keys);
  datospostulacion = posts;
}
	  
// Getters y setters
public DTPostulacion getDatosPostulacion() {
  return datospostulacion;
}

}
