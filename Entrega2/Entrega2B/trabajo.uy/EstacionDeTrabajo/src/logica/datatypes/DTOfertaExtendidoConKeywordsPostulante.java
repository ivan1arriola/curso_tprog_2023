package logica.datatypes;

import java.time.LocalDate;

import java.util.Set;

import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsPostulante extends DTOfertaExtendidoSinPConK {
  private DTPostulacion datospostulacion;
	 
public DTOfertaExtendidoConKeywordsPostulante(String nicknameEmpresa, String nombre,  String descripcion,   LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY departamento,  String ciudad,  EstadoOL estado,  byte[]  img,  Set<String> keys,  DTPostulacion posts) {
  super(nicknameEmpresa, nombre,  descripcion,  fechaA,  cost,  remu,  horario,  departamento,  ciudad,  estado,  img,  keys);
  datospostulacion = posts;
}
	  
// Getters y setters
public DTPostulacion getDatosPostulacion() {
  return datospostulacion;
}

}
