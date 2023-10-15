package main.java.logica;

import main.java.logica.controladores.CtrlCargaDeDatos;
import main.java.logica.controladores.CtrlOferta;
import main.java.logica.controladores.CtrlUsuario;
//import main.java.logica.interfaces.ICtrlCargaDeDatos;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;


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
