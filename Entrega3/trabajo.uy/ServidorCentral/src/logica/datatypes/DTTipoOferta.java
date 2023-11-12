package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;
//import logica.servidor.adapter.SetDTCantTOAdapter;

import java.time.LocalDate;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipoOferta {
    private String nombre;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaAlta;
    private float costo;
    private int duracion;
    private int exposicion;
    private String descripcion;

    public DTTipoOferta(String nombre, LocalDate fechaAlta, float costo, int duracion, int exposicion, String descripcion) {
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.costo = costo;
        this.duracion = duracion;
        this.exposicion = exposicion;
        this.descripcion = descripcion;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public float getCosto() {
        return costo;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getExposicion() {
        return exposicion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
