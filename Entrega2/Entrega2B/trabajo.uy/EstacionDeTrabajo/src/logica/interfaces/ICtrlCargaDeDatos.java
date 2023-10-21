package logica.interfaces;

import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionValidezNegativa;

public interface ICtrlCargaDeDatos {
	public abstract void cargarDatos() throws ExcepcionKeywordVacia, ExceptionValidezNegativa;
}
