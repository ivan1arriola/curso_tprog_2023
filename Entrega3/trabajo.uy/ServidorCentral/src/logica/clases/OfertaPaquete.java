package logica.clases;

import jakarta.persistence.*;
import logica.datatypes.DTCantTO;

@Entity
public class OfertaPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OfertaPaquete_id")
    private int id; // Se agrega un campo id como clave primaria

    private int cantidad;

    @Transient
    private TipoOferta tOferta; // Relación muchos a uno con TipoOferta

    public OfertaPaquete(TipoOferta ofer, int cant) {
        tOferta = ofer;
        cantidad = cant;
        System.out.println("Se ha creado un OfertaPaquete. - " + tOferta.getNombre() + " - " + cant);
    }

    public OfertaPaquete() {

    }

    public DTCantTO getDTCantTO() {
        return new DTCantTO(tOferta.getNombre(), cantidad);
    }

} 
