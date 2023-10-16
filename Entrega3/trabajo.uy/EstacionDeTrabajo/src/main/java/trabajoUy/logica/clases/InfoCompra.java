package trabajoUy.logica.clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import trabajoUy.logica.datatypes.DTCantTO;
import trabajoUy.logica.datatypes.DTCompraPaquetes;
import trabajoUy.logica.manejadores.TipoOfertaHandler;


public class InfoCompra {
    private LocalDate fechaCompra;
    private LocalDate fechaVencimiento; // fechaCompra + paq.Validez
    private float costo;
    private Paquete paquete;
    private Empresa empres;
    private Set<InfoCompraOferta> infoCompraOfertas;

    // constructor
    public InfoCompra(LocalDate fechaCompra,   float costo,   Paquete pack,   Empresa empres,  Set<DTCantTO> conjuntoS) {
        // atributos
        this.fechaCompra = fechaCompra;
        this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez()); // fechaCompra + paq.Validez
        this.costo = costo;
        // relaciones
        this.empres = empres;
        this.paquete = pack;
        Set<InfoCompraOferta> infoCompraOfertas = new HashSet<>();
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        for (DTCantTO elemento : conjuntoS) {
            TipoOferta tipoOfer = TOH.buscar(elemento.getNombre());
            InfoCompraOferta ico = new InfoCompraOferta(tipoOfer,  elemento.getCantidad());
            infoCompraOfertas.add(ico);
        }
    }

    // Getters
    public LocalDate getfCompra()  {
    	return fechaCompra;
    }
    
    public Paquete getPaquete() {
    	return paquete;
    }
    
    public LocalDate getFechaVencimiento() {
    	return fechaVencimiento;
    }
    
    public Empresa getEmpresa() {
    	return empres;
    
    }
    
    public float getCosto() {
    	return costo;
    }
    
    public Set<InfoCompraOferta> getICO() {
    	return infoCompraOfertas;
    }


    // Setters
    public void setfCompra(LocalDate fCompra) {
    	this.fechaCompra = fCompra;
    
    }
    
    public void setPaquete(Paquete paquete) {
    	this.paquete = paquete;
    }      
    
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
    	this.fechaVencimiento = fechaVencimiento;
    }
    
    public void setEmpresa(Empresa empres) {
    	this.empres = empres;
    }
    
    public void setCosto(float costo) {
    	this.costo = costo;
    } 
    
    public void setICO(Set<InfoCompraOferta> ICO) {
    	this.infoCompraOfertas = ICO;
    }

    // Metodos
    public DTCompraPaquetes obtenerDatosPaquete() {
        DTCompraPaquetes nuevacompra = new DTCompraPaquetes(paquete.getNombre(),   fechaCompra,   fechaVencimiento);
        return nuevacompra;
    }
}
