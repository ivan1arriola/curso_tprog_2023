package logica.clases;


import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class InfoCompraOferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iden; // Se agrega un campo id como clave primaria

    private int cantRestante;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private TipoOferta tipoOferta; // Relación muchos a uno con TipoOferta

    public InfoCompraOferta(TipoOferta tipoOfer, int canres) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        if (canres >= 0) {
            cantRestante = canres;
            tipoOferta = tipoOfer;
            System.out.println("Se ha creado un InfoCompraOferta. - " + tipoOfer.getNombre() + " - " + canres);
        } else {
            throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
        }

    } //Constructor

    public InfoCompraOferta() {

    }

    public int getCantres() {
        return cantRestante;
    }

    public void setCantres(int cantrest) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        if (cantrest >= 0) {
            cantRestante = cantrest;
        } else {
            throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("La cantidad restante de un tipo de oferta en un paquete no puede ser negativa.");
        }

    }

    public TipoOferta gettipoOfertas() {
        return tipoOferta;
    }

    public void settipoOfertas(TipoOferta tipoOfer) {
        tipoOferta = tipoOfer;
    }
}