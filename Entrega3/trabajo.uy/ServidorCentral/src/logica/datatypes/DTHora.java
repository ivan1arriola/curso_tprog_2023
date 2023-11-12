package logica.datatypes;

public class DTHora {
    private int hora;
    private int minutos;

    public DTHora(int hora, int min) {
        this.hora = hora;
        minutos = min;
    }

    public int getHora() {
        return hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public String toString() {
        return String.format("%02d:%02d", getHora(), getMinutos());
    }
}   
 
 
  