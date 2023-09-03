package logica.Clases;
import java.time.LocalDate;

public class InfoCompra {
 private LocalDate fechaCompra;
 private LocalDate fechaVencimiento; // fechaCompra + paq.Validez
 private Paquete paquete;
 
 public InfoCompra (LocalDate fechaCompra, Paquete pack) {
	 this.paquete = pack;
	 this.fechaCompra = fechaCompra;
	 this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez());
 }
 
 public LocalDate getfCompra() {
	 return fechaCompra;
 }
 
 public LocalDate getfVencimiento() {
	 return fechaVencimiento;
 }
 
}
