package logica.clases;

import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionValidezNegativa;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTCompraPaquetes;
import logica.manejadores.TipoOfertaHandler;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class InfoCompra {
    private LocalDate fechaCompra;
    private LocalDate fechaVencimiento; // fechaCompra + paq.Validez
    private float costo;
    private Paquete paquete;
    private Empresa empres;
    private Set<InfoCompraOferta> infoCompraOfertas;

    // constructor
    public InfoCompra(LocalDate fechaCompra, float costo, Paquete pack, Empresa empres, Set<DTCantTO> conjuntoS) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa {
        // atributos
        if (costo >= 0) {
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
                InfoCompraOferta ico = new InfoCompraOferta(tipoOfer, elemento.getCantidad());
                infoCompraOfertas.add(ico);
            }

            System.out.println("Se ha creado un InfoCompra." + pack.getNombre() + " - " + empres.getNickname());
        } else {
            throw new ExceptionValidezNegativa("La validez debe ser un número no negativo.");
        }

    }

    // Getters
    public LocalDate getfCompra() {
        return fechaCompra;
    }

    // Setters
    public void setfCompra(LocalDate fCompra) {
        this.fechaCompra = fCompra;

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

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public Set<InfoCompraOferta> getICO() {
        return infoCompraOfertas;
    }

    public void setICO(Set<InfoCompraOferta> ICO) {
        this.infoCompraOfertas = ICO;
    }

    // Metodos
    public DTCompraPaquetes obtenerDatosPaquete() {
        DTCompraPaquetes nuevacompra = new DTCompraPaquetes(paquete.getNombre(), fechaCompra, fechaVencimiento);
        return nuevacompra;
    }

    public boolean estaVencido() {
        return fechaVencimiento.isBefore(LocalDate.now());
    }
}
