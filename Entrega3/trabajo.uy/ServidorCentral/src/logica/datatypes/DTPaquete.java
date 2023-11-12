package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;
//import logica.servidor.adapter.SetDTCantTOAdapter;
//import logica.servidor.adapter.SetDTPostulacionAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
    private String nombre;
    private float costo;
    private float descuento;
    private int validez;
    private String descripcion;
    private List<DTCantTO> tiposDePub;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaA;
    private byte[] imagen;

    public DTPaquete(String nomb,  float cost,  float descuento,  int valid,  String desc,  Set<DTCantTO> tdp,  LocalDate fechaAlta,  byte[] imagen) {
        nombre = nomb;
        costo = cost;
        this.descuento = descuento;
        validez = valid;
        descripcion = desc;
        tiposDePub = new ArrayList<>(tdp) ;
        fechaA = fechaAlta;
        this.imagen = imagen;
    }


    public String getNombre() {
        return nombre;
    }

    public float getCosto() {
        return costo;
    }

    public float getDescuento() {
        return descuento;
    }

    public int getValidez() {
        return validez;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Set<DTCantTO> getTiposDePub() {
        return new HashSet<>(tiposDePub);
    }

    public LocalDate getFechaAlta() {
        return fechaA;
    }


    public byte[] getImagen() {
        return imagen;
    }

}
