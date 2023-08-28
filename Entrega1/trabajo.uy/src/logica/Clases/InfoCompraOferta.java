package logica.Clases;

import java.util.ArrayList;

public class InfoCompraOferta {
	private Integer cant_restante;

	public InfoCompraOferta(Integer can_res) { this.cant_restante = can_res; } //Constructor
	public Integer getCant_res() { return cant_restante; }
	public void setCant_res(Integer cant_rest) { cant_restante = cant_rest; }
	
}