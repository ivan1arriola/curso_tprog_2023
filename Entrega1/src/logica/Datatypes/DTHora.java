package logica.Datatypes;

public class DTHora {
	private int hora;
	private int minutos;
	
	public DTHora(int h, int m) {
		hora = h;
		minutos = m;
	}
	
	private int getHora() {
		return hora;
	}
	
	private int getMinutos() {
		return minutos;
	}
	
	public String toString() { 
		return String.format("%02d:%02d", getHora(), getMinutos());
	}
}


