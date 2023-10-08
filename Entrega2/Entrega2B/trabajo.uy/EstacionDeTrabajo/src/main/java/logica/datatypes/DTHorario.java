package main.java.logica.datatypes;

public class DTHorario {
	private DTHora desde;
	private DTHora hasta;
	
	public DTHorario(DTHora desde,  DTHora hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}
	
	public DTHora getDesde() {
		return desde;
	}
	
	public DTHora getHasta() {
		return hasta;
	}
} 

