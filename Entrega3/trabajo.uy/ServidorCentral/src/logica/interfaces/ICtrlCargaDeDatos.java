package logica.interfaces;

import excepciones.ErrorAgregarUsuario;
import excepciones.ExcepcionKeywordVacia;
import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionValidezNegativa;

public interface ICtrlCargaDeDatos {
    public abstract void cargarDatos() throws ExcepcionKeywordVacia,  ExceptionValidezNegativa,  ExceptionFechaInvalida,  ErrorAgregarUsuario;
}
