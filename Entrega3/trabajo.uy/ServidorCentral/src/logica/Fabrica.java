package logica;

import logica.controladores.CtrlCargaDeDatos;
import logica.controladores.CtrlOferta;
import logica.controladores.CtrlUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;


public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {

    }

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public ICtrlUsuario getICtrlUsuario() {
        return new CtrlUsuario();
    }

    public ICtrlOferta getICtrlOferta() {
        return new CtrlOferta();
    }

    public CtrlCargaDeDatos getICtrlCargaDeDatos() {
        return new CtrlCargaDeDatos();
    }
}
