package main.java.logica;

import main.java.logica.Controladores.CtrlCargaDeDatos;
import main.java.logica.Controladores.CtrlOferta;
import main.java.logica.Controladores.CtrlUsuario;
import main.java.logica.Interfaces.ICtrlCargaDeDatos;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;


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
