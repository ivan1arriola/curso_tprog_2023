package main.java.logica.datatypes;

import java.util.HashSet;
import java.util.Set;

public class DTEmpresaConCompras extends DTEmpresa {
	private Set<DTCompraPaquetes> compraPaquetes;
	 
	public DTEmpresaConCompras(String nick, String mail, String apellido, String nombre, String contraseña, byte[] img, String desc, String URL, Set<DTOfertaExtendido> ols, Set<DTCompraPaquetes> dtcp) {
		super(nick, mail, apellido, nombre, contraseña, desc, URL, ols, img);
		compraPaquetes = dtcp;
	}
	
	// Getters y setters
	public Set<DTCompraPaquetes> getCompraPaquetes() {
		return compraPaquetes;
	}

}
