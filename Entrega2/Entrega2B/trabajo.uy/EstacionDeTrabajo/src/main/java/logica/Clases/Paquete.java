package main.java.logica.Clases;
import java.util.Iterator;
import java.util.Set;


import java.time.LocalDate;
import java.util.HashSet;

import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Manejadores.TipoOfertaHandler;
//import main.java.logica.Clases.TipoOferta;

public class Paquete {
    // Atributos
    private String nombre;
    private String descripcion;
    private float costo;
    private float descuento;
    private int validez;
    private LocalDate fechaAlta;
    private byte[] imagen;
    // Relaciones
    private HashSetSet<OfertaPaquete> oferPaq;
    private HashSetSet<InfoCompra> infCompraAsociada;

    // Constructor
    public Paquete(String nombre, String descripcion, int validez, LocalDate fecha, float descuento,byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaAlta = fecha;
        this.descuento = descuento;
        this.validez = validez;
        this.imagen = imagen;
        oferPaq = new HashSet<OfertaPaquete>(); // tengo cuantos paquetes tienen
        
        // Inicialización
        TipoOfertaHandler tOfertaHandler = TipoOfertaHandler.getInstance();
        

        float Costo = 0; 
        
        for (OfertaPaquete OfertaAnalizar : oferPaq) {
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO();
            String nombreOferta = DTcantaux.getNombre();
            int cantidadTotal = DTcantaux.getCantidad();
            TipoOferta  TO = tOfertaHandler.buscar(nombreOferta);
            float CostoTO = TO.getCosto();
            Costo = Costo + CostoTO*cantidadTotal;
        }
        
        Costo = (float) (Costo-(Costo*descuento*0.01));
        
        this.costo = Costo;
        
        this.infCompraAsociada = null; //empieza null, despues se cambia 
        
    }

    	// GETTERS
    public String getNombre() 						{ return nombre; }
    public String getDescripcion() 					{ return descripcion; }
    public float getCosto() 						{ return costo; }
    public float getDescuento()						{ return descuento; }
    public int getValidez() 						{ return validez; }
    public byte[] getImagen()						{ return imagen; }
    public Set<OfertaPaquete> getOfertaPaquete() 	{ return oferPaq; }
    public Set<InfoCompra> getInfoCompra()          { return infCompraAsociada; }
    public LocalDate getfechaAlta()                 { return fechaAlta;    }

    // SETTERS
    public void setNombre(String nombre) 						{ this.nombre = nombre; }
    public void setDescripcion(String descripcion) 				{ this.descripcion = descripcion; }
    public void setCosto(float costo) 							{ this.costo = costo; }
    public void setDuracion(float descuento) 					{ this.descuento = descuento; }
    public void setValidez(int validez) 						{ this.validez = validez; }
    public void setOfertaPaquete(HashSet<OfertaPaquete> oferPaq) 	{ 
    if (this.infCompraAsociada  == null) {
            this.oferPaq = oferPaq;
        }
    }
    public void setInfoCompra(HashSet<InfoCompra> InfoCom)       	{ this.infCompraAsociada = InfoCom; }



    // OPERACIONES
    public void crearOfertaPaquete(TipoOferta tipoO, int cantidad) {
        OfertaPaquete ofpaq = new OfertaPaquete(tipoO, cantidad);
        oferPaq.add(ofpaq);
    }

    public DTPaquete getDTPaquete() {
        Set<DTCantTO> individual = new HashSet<>();
        Iterator<OfertaPaquete> it = oferPaq.iterator(); 
        while (it.hasNext()) { 
            OfertaPaquete actual = it.next();
            individual.add(actual.getDTCantTO());
        }
        
        DTPaquete dtpaq = new DTPaquete(nombre, costo, descuento, validez, descripcion, individual, fechaAlta);
        return dtpaq;
    }
    
    public HashSet<DTCantTO> obtenerDTSCantTO(){
        // devolver cantidad y nombre de cada paquete
        HashSet<DTCantTO> SetNuevo = new HashSet<DTCantTO>();
        for (OfertaPaquete OfertaAnalizar : oferPaq) {
            // Por cada oferta paquete, obtengo su DTCantTO y lo agrego al SetNuevo
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO();
            SetNuevo.add(DTcantaux);

        }
        return SetNuevo;
    }
}
