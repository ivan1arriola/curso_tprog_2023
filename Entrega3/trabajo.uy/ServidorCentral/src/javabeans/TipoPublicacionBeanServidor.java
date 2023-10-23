package javabeans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TipoPublicacionBeanServidor {
	
	
	private String nombre;
	private DateBean fechaAlta;
	private float costo;
	private int  duracion;
	private int exposicion;
	private String descripcion;
	
	 public TipoPublicacionBeanServidor() {
	        this.nombre = null;
	        this.fechaAlta = null;
	        this.costo = 0.0f;
	        this.setDuracion(0);
	        this.exposicion = 0;
	        this.descripcion = null;
	    }
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DateBean getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(DateBean fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getExposicion() {
		return exposicion;
	}
	public void setExposicion(int exposicion) {
		this.exposicion = exposicion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getDuracion() {
		return duracion;
	}



	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
}
