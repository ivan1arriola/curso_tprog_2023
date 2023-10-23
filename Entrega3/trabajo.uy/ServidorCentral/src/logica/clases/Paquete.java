package logica.clases;

import java.util.Iterator;
import java.util.Set;

import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
import excepciones.ExceptionValidezNegativa;
import logica.Utils;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTPaquete;
import logica.manejadores.TipoOfertaHandler;

import java.time.LocalDate;
import java.util.HashSet;

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
    public Paquete(String nombre,   String descripcion,   int validez,   LocalDate fecha,   float descuento,  byte[] imagen) throws ExceptionValidezNegativa, ExceptionDescuentoInvalido {
    	if (descuento >= 0 && descuento <= 100) {
            if (validez >= 0) {
            	this.nombre = nombre;
            	this.descripcion = descripcion;
                this.fechaAlta = fecha;
                this.descuento = descuento;
                this.validez = validez;
                this.imagen = imagen;
                this.oferPaq = new HashSet<OfertaPaquete>();
                costo = 0;  
                this.infCompraAsociada = new HashSet<InfoCompra>(); //empieza null,   despues se cambia 
                
                System.out.println("Se ha creado un paquete. - " + nombre);
                Utils.guardarImagen("Paquetes", nombre , "jpg", imagen);
            } else {
            	throw new ExceptionValidezNegativa("La validez debe ser un número no negativo.");
            }
            
    	} else {
    		throw new ExceptionDescuentoInvalido("El descuento tiene que ser un número entre 0 y 100.");
    	}

        
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
    
    public void setCosto(float costo) throws ExceptionCostoPaqueteNoNegativo 	{
    	if (costo >= 0) {
    		this.costo = costo;
    	}
    	else {
    		throw new ExceptionCostoPaqueteNoNegativo("El costo de un paquete no puede ser negativo.");
    	}
    }
    
    public void setDescuento(float descuento) throws ExceptionDescuentoInvalido {
    	if (descuento >= 0 && descuento <= 100) {
    		this.descuento = descuento; 
    	} else {
    		throw new ExceptionDescuentoInvalido("El descuento tiene que ser un número entre 0 y 100.");
    	}
    	
    }
    
    public void setValidez(int validez) throws ExceptionValidezNegativa {
    	if (validez >= 0) {
    		this.validez = validez;
    	} else throw new ExceptionValidezNegativa("La validez debe ser un número no negativo.");
    }
    
    public void setOfertaPaquete(Set<OfertaPaquete> oferPaq) 	{ 
		float Costo = 0;
        this.oferPaq = oferPaq;
        // cambie oferta paquete,   cambie el precio del mismo
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
    public void crearOfertaPaquete(TipoOferta tipoO, int cantidad) throws ExceptionCantidadPositivaDeTipoOfertaEnPaquete {
    	if (cantidad > 0) {
            OfertaPaquete ofpaq = new OfertaPaquete(tipoO,   cantidad);
            Set<OfertaPaquete> OFERTASPAQUETES = this.getOfertaPaquete();
            OFERTASPAQUETES.add(ofpaq);
    		float Costo = 0;
            // cambie oferta paquete,   cambie el precio del mismo
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
        } else {
        	throw new ExceptionCantidadPositivaDeTipoOfertaEnPaquete("Un tipo de oferta en un paquete, incluye al menos 1 de ese tipo.");
        }
}


    public DTPaquete getDTPaquete() {
        Set<DTCantTO> individual = new HashSet<>();
        Iterator<OfertaPaquete> iterador = oferPaq.iterator(); 
        while (iterador.hasNext()) { 
            OfertaPaquete actual = iterador.next();
            individual.add(actual.getDTCantTO());
        }
        
        DTPaquete dtpaq = new DTPaquete(nombre,   costo,   descuento,   validez,   descripcion,   individual,   fechaAlta, imagen);
        return dtpaq;
    }
    
    public Set<DTCantTO> obtenerDTSCantTO(){
        // devolver cantidad y nombre de cada paquete
        Set<DTCantTO> SetNuevo = new HashSet<DTCantTO>();
        for (OfertaPaquete OfertaAnalizar : oferPaq) {
            // Por cada oferta paquete,   obtengo su DTCantTO y lo agrego al SetNuevo
            DTCantTO DTcantaux = OfertaAnalizar.getDTCantTO();
            SetNuevo.add(DTcantaux);

        }
        return SetNuevo;
    }
}
