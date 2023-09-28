package main.java.presentacion.componentes;

import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;

public interface IFormularioUsuario extends IFormulario {
		
	void modoAltaEmpresa();
	void modoAltaPostulante();
	
	void mostrarEmpresa(DTEmpresa empresa);
	void mostrarPostulante(DTPostulante postulante);
	
	void modificarEmpresa(DTEmpresa empresa);
	void modificarPostulante(DTPostulante postulante);
	
	String getContrasenia();
	DTUsuario getDTUsuario();
	
	void reiniciarFormulario();

	void modificarEmpresa(DTEmpresa empresa, String contrasenia);
	void modificarPostulante(DTPostulante postulante, String contrasenia);

}
