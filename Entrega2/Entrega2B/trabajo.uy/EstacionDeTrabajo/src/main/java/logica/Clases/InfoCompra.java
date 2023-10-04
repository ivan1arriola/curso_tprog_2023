package main.java.logica.Clases;
import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Manejadores.TipoOfertaHandler;

import main.java.logica.Datatypes.DTCompraPaquetes;


public class InfoCompra {
 private LocalDate fechaCompra;
 private LocalDate fechaVencimiento; // fechaCompra + paq.Validez
 private float costo;
 private Paquete paquete;
 private Empresa empres;
 private InfoCompraOferta infoCompraOfertas;
 
 public InfoCompra (LocalDate fechaCompra, Paquete pack,float costo,Empresa empres,InfoCompraOferta ICO) {
	 this.paquete = pack;
	 this.fechaCompra = fechaCompra;
	 this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez());
	 this.empres = empres;
	 this.costo = costo;
	 this.infoCompraOfertas = ICO;
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

 public void setFechaCompra(LocalDate fechaCompra) {
     this.fechaCompra = fechaCompra;
 }

 public Paquete getPaquete() {
     return paquete;
 }

 public void setPaquete(Paquete paquete) {
     this.paquete = paquete;
 }

 public LocalDate getFechaVencimiento() {
     return fechaVencimiento;
 }

 public Empresa getEmpresa() {
     return empres;
 }

 public void setEmpresa(Empresa empres) {
     this.empres = empres;
 }

 public float getCosto() {
     return costo;
 }

 public void setCosto(float costo) {
     this.costo = costo;
 }

 public InfoCompraOferta getICO() {
     return infoCompraOfertas;
 }

 public void setICO(InfoCompraOferta ICO) {
     this.infoCompraOfertas = ICO;
 }
 ///-----------------------------------------------------------
 
 public DTCompraPaquetes obtenerDatosPaquete() {
	 DTCompraPaquetes nuevacompra = new DTCompraPaquetes(paquete.getNombre(), fechaCompra, fechaVencimiento);
	 return nuevacompra;
 }
}
