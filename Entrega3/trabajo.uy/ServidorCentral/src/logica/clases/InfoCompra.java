package logica.clases;

import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import excepciones.ExceptionValidezNegativa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTCompraPaquetes;
import logica.manejadores.TipoOfertaHandler;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class InfoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iden; // Se agrega un campo id como clave primaria

    private LocalDate fechaCompra;
    private LocalDate fechaVencimiento;
    private float costo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paquete_id")
    private Paquete paquete;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Empresa empresa; // Relación muchos a uno con Empresa

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<InfoCompraOferta> infoCompraOfertas; // Relación uno a muchos con InfoCompraOferta

    // constructor
    public InfoCompra(LocalDate fechaCompra, float costo, Paquete pack, Empresa empres, Set<DTCantTO> conjuntoS) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa {
        // atributos
        if (Float.compare(costo, 0) >= 0) {
            this.fechaCompra = fechaCompra;
            this.fechaVencimiento = this.fechaCompra.plusDays(pack.getValidez()); // fechaCompra + paq.Validez
            this.costo = costo;
            // relaciones
            this.empresa = empres;
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

    public InfoCompra() {

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
        return empresa;

    }

    public void setEmpresa(Empresa empres) {
        this.empresa = empres;
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
