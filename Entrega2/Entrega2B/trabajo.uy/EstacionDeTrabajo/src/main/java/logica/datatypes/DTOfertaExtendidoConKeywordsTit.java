package main.java.logica.datatypes;

import java.time.LocalDate;

import java.util.Set;


import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsTit extends DTOfertaExtendidoSinPConK {
	private Set<String> postulaciones;
	private DTPaquete paq;

public DTOfertaExtendidoConKeywordsTit(String nomb,  String desc,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario, 
       DepUY dep,  String ciu,  EstadoOL estado,  byte[] img,  Set<String> keys,  DTPaquete paq,  Set<String> postulaciones) {
  super(nomb,  desc,  fechaA,  cost,  remu,  horario,  dep,  ciu,  estado,  img,  keys);
  this.postulaciones = postulaciones;
  this.paq = paq;
}
	
public DTPaquete getPaquete() {
  return paq;
}
	
public Set<String>  getpostulaciones() {
  return postulaciones;
}
}
