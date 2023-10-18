package main.java.logica.interfaces;

import main.java.excepciones.ExcepcionKeywordVacia;

public interface ICtrlCargaDeDatos {
	public abstract void cargarDatos() throws ExcepcionKeywordVacia;
}
