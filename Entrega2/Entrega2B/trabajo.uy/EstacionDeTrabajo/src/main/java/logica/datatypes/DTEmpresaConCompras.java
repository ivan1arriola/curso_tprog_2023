package main.java.logica.datatypes;

import java.util.HashSet;

public class DTEmpresaConCompras extends DTEmpresa {
	private HashSet<DTCompraPaquetes> compraPaquetes;
	 
	public DTEmpresaConCompras(String nick, String mail, String ap, String nombre, String contraseña, byte[] img, String desc, String URL, HashSet<DTOfertaExtendido> ols, HashSet<DTCompraPaquetes> dtcp) {
		super(nick, mail, ap, nombre, contraseña, desc, URL, ols, img);
		compraPaquetes = dtcp;
	}
	
	// Getters y setters
	public HashSet<DTCompraPaquetes> getCompraPaquetes() {
		return compraPaquetes;
	}

}
