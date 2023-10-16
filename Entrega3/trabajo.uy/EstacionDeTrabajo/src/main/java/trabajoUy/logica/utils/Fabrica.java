package trabajoUy.logica.utils;

import trabajoUy.logica.controladores.CtrlCargaDeDatos;
import trabajoUy.logica.controladores.CtrlOferta;
import trabajoUy.logica.controladores.CtrlUsuario;
//import trabajoUy.logica.interfaces.ICtrlCargaDeDatos;
import trabajoUy.logica.interfaces.ICtrlOferta;
import trabajoUy.logica.interfaces.ICtrlUsuario;


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
