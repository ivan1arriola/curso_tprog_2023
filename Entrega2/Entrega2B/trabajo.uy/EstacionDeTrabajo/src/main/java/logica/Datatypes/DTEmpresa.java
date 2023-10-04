package main.java.logica.Datatypes;

import java.util.HashSet;
import java.util.Set;

public class DTEmpresa extends DTUsuario {
	
    private String descripcion;
    private String url;
    private Set<DTOfertaExtendido> ofertasLaborales; 


    public DTEmpresa(String nickname, String correo_electronico, String apellido, String nombre, String descripcion, String url, HashSet<DTOfertaExtendido> dtOfertas, byte[] imagen) {
        super(nickname, correo_electronico, apellido, nombre, imagen);
        this.descripcion = descripcion;
        this.url = url;
        this.ofertasLaborales = dtOfertas;
    }

	public Set<DTOfertaExtendido> getOfertasLaborales() {
        return ofertasLaborales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }
}
