package main.java.logica.Clases;
import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Manejadores.TipoOfertaHandler;

public class InfoCompra {
 private LocalDate fechaCompra;
 private LocalDate fechaVencimiento; // fechaCompra + paq.Validez
 private Paquete paquete;
 private HashSet<InfoCompraOferta> infocompraofertas;
 
 public InfoCompra (LocalDate fechaCompra, Paquete pack) {
	 this.paquete = pack;
	 this.fechaCompra = fechaCompra;
	 this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez());
 }

 public InfoCompra(LocalDate fa, float costo, LocalDate plusDays, HashSet<DTCantTO> S) {
	TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
    for (DTCantTO s : S) {
    	TipoOferta to = TOH.buscar(s.getNombre());
    	InfoCompraOferta ico = new InfoCompraOferta(to,s.getCantidad());
    }
 }

public LocalDate getfCompra() {
	 return fechaCompra;
 }
 
 public LocalDate getfVencimiento() {
	 return fechaVencimiento;
 }
 
 public Paquete getPaquete() {
	 return paquete;
 }
 
}
