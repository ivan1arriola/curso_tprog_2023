package logica.clases;

import logica.datatypes.DTCantTO;

public class OfertaPaquete {
    private int cantidad;
    private TipoOferta tOferta;

    public OfertaPaquete(TipoOferta ofer, int cant) {
        tOferta = ofer;
        cantidad = cant;
        System.out.println("Se ha creado un OfertaPaquete. - " + tOferta.getNombre() + " - " + cant);
    }

    public DTCantTO getDTCantTO() {
        DTCantTO dtCant = new DTCantTO(tOferta.getNombre(), cantidad);
        return dtCant;
    }

} 
