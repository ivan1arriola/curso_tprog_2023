package main.java.logica.datatypes;

import java.util.HashSet;
import java.util.Set;

public class DTEmpresa extends DTUsuario {
	
    private String descripcion;
    private String url;
    private Set<DTOfertaExtendido> ofertasLaborales;


    public DTEmpresa(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, String descripcion, String url, Set<DTOfertaExtendido> dtOfertas, byte[] imagen) {
        super(nickname, correo_electronico, apellido, nombre, contraseña, imagen);
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
