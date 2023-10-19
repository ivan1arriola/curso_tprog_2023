package main.java.logica.interfaces;

import main.java.excepciones.ExcepcionKeywordVacia;
import main.java.excepciones.ExceptionValidezNegativa;

public interface ICtrlCargaDeDatos {
	public abstract void cargarDatos() throws ExcepcionKeywordVacia, ExceptionValidezNegativa;
}
