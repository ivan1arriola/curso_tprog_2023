package model.Datatypes;

public class DTHorario {
	private DTHora desde;
	private DTHora hasta;
	
	public DTHorario(DTHora d, DTHora h) {
		desde = d;
		hasta = h;
	}
	
	public DTHora getDesde() {
		return desde;
	}
	
	public DTHora getHasta() {
		return hasta;
	}
	
	@Override
    public String toString() {
        return desde.toString() + " - " + hasta.toString();
    }
	
	
} 

