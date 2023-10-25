package logica.servidor.bean;

import java.time.LocalDate;

public class DateBean {
    private int dia;
    private int mes;
    private int anio;

    public DateBean(){}
    public DateBean(LocalDate fechaNac) {
        setDia(fechaNac.getDayOfMonth());
        setMes(fechaNac.getMonthValue());
        setAnio(fechaNac.getYear());
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
