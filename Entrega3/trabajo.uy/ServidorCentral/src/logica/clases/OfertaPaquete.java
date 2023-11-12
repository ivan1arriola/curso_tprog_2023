package logica.clases;

import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import jakarta.persistence.CascadeType;
//import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.Lob;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
//import jakarta.persistence.InheritanceType;
//import jakarta.persistence.DiscriminatorColumn;
//import jakarta.persistence.DiscriminatorType;
import logica.datatypes.DTCantTO;

@Entity
public class OfertaPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iden; // Se agrega un campo id como clave primaria

    private int cantidad;

//    @Transient
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipoOferta_id")
    private TipoOferta tOferta; // Relación muchos a uno con TipoOferta

    public OfertaPaquete(TipoOferta ofer,  int cant) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
	    if (cant >= 0) {
	        tOferta = ofer;
	        cantidad = cant;
	        System.out.println("Se ha creado un OfertaPaquete. - " + tOferta.getNombre() + " - " + cant);
	    } else {
	        throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
	    }
    }
    
    public OfertaPaquete() {

    }

    public DTCantTO getDTCantTO() {
        DTCantTO respuesta = new DTCantTO(tOferta.getNombre(),  this.cantidad);
        return respuesta;
    }

} 
