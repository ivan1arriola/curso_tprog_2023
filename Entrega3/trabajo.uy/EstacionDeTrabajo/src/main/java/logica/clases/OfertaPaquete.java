package main.java.logica.clases;

import main.java.logica.datatypes.DTCantTO;

public class OfertaPaquete {
 private int cantidad;
 private TipoOferta tOferta;
 
 public OfertaPaquete(TipoOferta ofer,  int cant) {
	 tOferta = ofer;
	 cantidad = cant;
 }
 
 public DTCantTO getDTCantTO() {
	 DTCantTO dtCant = new DTCantTO(tOferta.getNombre(),  cantidad);
	 return dtCant;
 }
 
} 
