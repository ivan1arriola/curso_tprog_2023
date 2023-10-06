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
    private HashSet<InfoCompraOferta> infoCompraOfertas;

    // constructor
    public InfoCompra (LocalDate fechaCompra, Paquete pack,float costo,Empresa empres,HashSet<InfoCompraOferta> ICO) {
        // atributos
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez()); // fechaCompra + paq.Validez
        this.costo = costo;
        // relaciones
        this.empres = empres;
        this.paquete = pack;
        this.infoCompraOfertas = ICO;
    }

    // Getters
    public LocalDate getfCompra() {return fechaCompra;}
    public Paquete getPaquete() {return paquete;}
    public LocalDate getFechaVencimiento() {return fechaVencimiento;}
    public Empresa getEmpresa() {return empres;}
    public float getCosto() {return costo;}
    public InfoCompraOferta getICO() {return infoCompraOfertas;}


    // Setters
    public void setfCompra(LocalDate fCompra) {this.fechaCompra = fCompra;}
    public void setPaquete(Paquete paquete) {this.paquete = paquete;}      
    public void setFechaVencimiento(LocalDate fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}
    public void setEmpresa(Empresa empres) {this.empres = empres;}
    public void setCosto(float costo) {this.costo = costo;} 
    public void setICO(HashSet<InfoCompraOferta> ICO) {this.infoCompraOfertas = ICO;}

    // Metodos
    public DTCompraPaquetes obtenerDatosPaquete() {
        DTCompraPaquetes nuevacompra = new DTCompraPaquetes(paquete.getNombre(), fechaCompra, fechaVencimiento);
        return nuevacompra;
    }
}
