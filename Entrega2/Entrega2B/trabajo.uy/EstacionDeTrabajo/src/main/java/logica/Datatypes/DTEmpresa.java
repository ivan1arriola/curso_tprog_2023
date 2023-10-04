package main.java.logica.Datatypes;

import java.util.Set;

public class DTEmpresa extends DTUsuario {
	
    private String descripcion;
    private String url;
    private Set<DTOfertaLaboral> ofertasLaborales; 


    public DTEmpresa(String nickname, String correo_electronico, String apellido, String nombre, String descripcion, String url, Set<DTOfertaLaboral> dtOfertas, byte[] imagen) {
        super(nickname, correo_electronico, apellido, nombre, imagen);
        this.descripcion = descripcion;
        this.url = url;
        this.ofertasLaborales = dtOfertas;
    }
    


	public Set<DTOfertaLaboral> getOfertasLaborales() {
        return ofertasLaborales;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }
}
