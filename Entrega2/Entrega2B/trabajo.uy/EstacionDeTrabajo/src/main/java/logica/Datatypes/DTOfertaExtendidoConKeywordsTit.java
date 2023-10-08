package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.clases.Paquete;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywordsTit extends DTOfertaExtendidoSinPConK {
	private HashSet<String> postulaciones;
	private Paquete paq;

	public DTOfertaExtendidoConKeywordsTit(String n, String desc, LocalDate fechaA, float c, float r, DTHorario h,
			DepUY dep, String ciu, EstadoOL e, byte[] img, HashSet<String> keys,Paquete paq,HashSet<String> postulaciones) {
		super(n, desc, fechaA, c, r, h, dep, ciu, e, img, keys);
		this.postulaciones = postulaciones;
		this.paq = paq;
	}
	
	public Paquete getPaquete() {
		return paq;
	}
	
    public HashSet<String>  getpostulaciones() {
    	return postulaciones;
    }
}
