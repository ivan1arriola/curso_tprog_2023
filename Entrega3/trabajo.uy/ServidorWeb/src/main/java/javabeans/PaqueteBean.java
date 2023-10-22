package javabeans;

import java.util.HashSet;
import java.util.Set;

import utils.Convertidor;
import webservice.CantTipoPublicacionBeanServidor;
import webservice.DateBean;
import webservice.PaqueteBeanServidor;

import java.time.LocalDate;

public class PaqueteBean {
    private String nombre;
    private float costo;
    private float descuento;
    private int validez;
    private String descripcion;
    private Set<CantTipoPublicacionBean> tiposDePub;
    private LocalDate fechaA;
    private String imagen;

    public PaqueteBean() {
        // Constructor que inicializa todos los atributos en null o 0 (según el tipo)
        this.nombre = null;
        this.costo = 0.0f;
        this.descuento = 0.0f;
        this.validez = 0;
        this.descripcion = null;
        this.tiposDePub = null;
        this.fechaA = null;
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

    public Set<CantTipoPublicacionBean> getTiposDePub() {
        return tiposDePub;
    }

    public void setTiposDePub(Set<CantTipoPublicacionBean> tiposDePub) {
        this.tiposDePub = tiposDePub;
    }

    public LocalDate getFechaAlta() {
        return fechaA;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaA = fechaAlta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public static PaqueteBean convertFromServidor(PaqueteBeanServidor servidorBean) {
        PaqueteBean paqueteBean = new PaqueteBean();
        
        paqueteBean.setNombre(servidorBean.getNombre());
        paqueteBean.setCosto(servidorBean.getCosto());
        paqueteBean.setDescuento(servidorBean.getDescuento());
        paqueteBean.setValidez(servidorBean.getValidez());
        paqueteBean.setDescripcion(servidorBean.getDescripcion());
        paqueteBean.setImagen(servidorBean.getImagen());

        // Convierte la lista de tiposDePub
        Set<CantTipoPublicacionBean> tiposDePub = new HashSet<>();
        if (servidorBean.getTiposDePub() != null) {
            for (CantTipoPublicacionBeanServidor tipo : servidorBean.getTiposDePub()) {
                tiposDePub.add(CantTipoPublicacionBean.convertFromServidor(tipo));
            }
        }
        paqueteBean.setTiposDePub(tiposDePub);

        // Convierte la fechaA
        if (servidorBean.getFechaA() != null) {
            paqueteBean.setFechaAlta( Convertidor.toLocalDate(servidorBean.getFechaA()));
        }

        return paqueteBean;
    }

	public static PaqueteBean fromPaqueteBeanServidor(PaqueteBeanServidor paquete) {
		// TODO Auto-generated method stub
		return null;
	}
}
