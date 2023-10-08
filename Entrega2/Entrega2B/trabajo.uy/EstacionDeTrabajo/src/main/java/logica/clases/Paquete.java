package main.java.logica.clases;

import java.util.Iterator;
import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;

//import main.java.logica.Clases.TipoOferta;
import main.java.logica.datatypes.DTCantTO;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.manejadores.TipoOfertaHandler;

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
    private Set<OfertaPaquete> oferPaq;
    private Set<InfoCompra> infCompraAsociada;

    // Constructor
    public Paquete(String nombre, String descripcion, int validez, LocalDate fecha, float descuento,byte[] imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaAlta = fecha;
        this.descuento = descuento;
        this.validez = validez;
        this.imagen = imagen;
        this.oferPaq = new HashSet<OfertaPaquete>();
        costo = 0;  
        this.infCompraAsociada = new HashSet<InfoCompra>(); //empieza null, despues se cambia 
        
    }

    	// GETTERS
    public String getNombre() {
    	return nombre; 
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public float getCosto() {
    	return costo;
    }
    
    public float getDescuento()	{
    	return descuento;
    }
    
    public int getValidez() {
    	return validez;
    }
    
    public byte[] getImagen() {
    	return imagen; 
    }
    
    public Set<OfertaPaquete> getOfertaPaquete() {
    	return oferPaq;
    }
    
    public Set<InfoCompra> getInfoCompra() {
    	return infCompraAsociada;
    }
    
    public LocalDate getfechaAlta()  {
    	return fechaAlta;
    }

    // SETTERS
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }
    
    public void setCosto(float costo) 	{
    	this.costo = costo;
    }
    
    public void setDuracion(float descuento) {
    	this.descuento = descuento; 
    }
    
    public void setValidez(int validez) {
    	this.validez = validez;
    }
    
    public void setOfertaPaquete(Set<OfertaPaquete> oferPaq) 	{ 
		float Costo = 0;
        this.oferPaq = oferPaq;
        // cambie oferta paquete, cambie el precio del mismo
        for (OfertaPaquete OfertaAnalizar : oferPaq) {
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO(); // obtengo cantidad y nombre de cada paquete
            String nombreOferta = DTcantaux.getNombre(); // nombre lo uso para buscar
            int cantidadTotal = DTcantaux.getCantidad();
            TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
			TipoOferta TipoOfer = TOH.buscar(nombreOferta);
            float CostoTO = TipoOfer.getCosto(); // obtuve precio de la oferta
            Costo = Costo + CostoTO*cantidadTotal;
        }
        
        Costo = (float) (Costo-(Costo*descuento*0.01)); 
        this.costo = Costo;
    }
    
    public void setInfoCompra(Set<InfoCompra> InfoCom) {
    	this.infCompraAsociada = InfoCom;
    }



    // OPERACIONES
    public void crearOfertaPaquete(TipoOferta tipoO, int cantidad) {
        OfertaPaquete ofpaq = new OfertaPaquete(tipoO, cantidad);
        Set<OfertaPaquete> OFERTASPAQUETES = this.getOfertaPaquete();
        OFERTASPAQUETES.add(ofpaq);
		float Costo = 0;
        // cambie oferta paquete, cambie el precio del mismo
        for (OfertaPaquete OfertaAnalizar : OFERTASPAQUETES) {
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO(); // obtengo cantidad y nombre de cada paquete
            String nombreOferta = DTcantaux.getNombre(); // nombre lo uso para buscar
            int cantidadTotal = DTcantaux.getCantidad();
            TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
			TipoOferta TipoOfer = TOH.buscar(nombreOferta);
            float CostoTO = TipoOfer.getCosto(); // obtuve precio de la oferta
            Costo = Costo + CostoTO*cantidadTotal;
        }
            
        Costo = (float) (Costo-(Costo*descuento*0.01)); 
        this.costo = Costo;
    }

    public DTPaquete getDTPaquete() {
        Set<DTCantTO> individual = new HashSet<>();
        Iterator<OfertaPaquete> iterador = oferPaq.iterator(); 
        while (iterador.hasNext()) { 
            OfertaPaquete actual = iterador.next();
            individual.add(actual.getDTCantTO());
        }
        
        DTPaquete dtpaq = new DTPaquete(nombre, costo, descuento, validez, descripcion, individual, fechaAlta);
        return dtpaq;
    }
    
    public Set<DTCantTO> obtenerDTSCantTO(){
        // devolver cantidad y nombre de cada paquete
        Set<DTCantTO> SetNuevo = new HashSet<DTCantTO>();
        for (OfertaPaquete OfertaAnalizar : oferPaq) {
            // Por cada oferta paquete, obtengo su DTCantTO y lo agrego al SetNuevo
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO();
            SetNuevo.add(DTcantaux);

        }
        return SetNuevo;
    }
}
