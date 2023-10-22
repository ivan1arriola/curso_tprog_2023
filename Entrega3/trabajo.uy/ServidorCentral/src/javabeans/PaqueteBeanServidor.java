package javabeans;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class PaqueteBeanServidor {
    private String nombre;
    private float costo;
    private float descuento;
    private int validez;
    private String descripcion;
    private ArrayList<CantTipoPublicacionBeanServidor> tiposDePub;
    private DateBean fechaA;
    private String imagen;

    public PaqueteBeanServidor() {
        // Constructor que inicializa todos los atributos en null o 0 (según el tipo)
        this.nombre = null;
        this.costo = 0.0f;
        this.descuento = 0.0f;
        this.validez = 0;
        this.descripcion = null;
        this.setTiposDePub(null);
        this.setFechaA(null);
        this.imagen = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

	public ArrayList<CantTipoPublicacionBeanServidor> getTiposDePub() {
		return tiposDePub;
	}

	public void setTiposDePub(ArrayList<CantTipoPublicacionBeanServidor> tiposDePub) {
		this.tiposDePub = tiposDePub;
	}

	public DateBean getFechaA() {
		return fechaA;
	}

	public void setFechaA(DateBean fechaA) {
		this.fechaA = fechaA;
	}
}
