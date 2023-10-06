package model.Datatypes;

import java.util.HashSet;

public class DTEmpresaConCompras extends DTEmpresa {
	private HashSet<DTCompraPaquetes> compra_paquetes;
	 
	public DTEmpresaConCompras(String nick, String mail, String ap, String nombre, byte[] img, String desc, String URL, HashSet<DTOfertaExtendido> ols, HashSet<DTCompraPaquetes> dtcp) {
		super(nick, mail, ap, nombre, desc, URL, ols, img);
		compra_paquetes = dtcp;
	}
	// Getters y setters
	public HashSet<DTCompraPaquetes> getCompraPaquetes() {
		return compra_paquetes;
	}

}
