package logica.datatypes;


import java.util.Set;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.SetAdapter;

public class DTEmpresa extends DTUsuario {
	
    private String descripcion;
    private String url;
    
    @XmlJavaTypeAdapter(SetAdapter.class)
    private Set<DTOfertaExtendido> ofertasLaborales;


    public DTEmpresa(String nickname,  String correo_electronico,  String apellido,  String nombre,  String contraseña,  String descripcion,  String url,  Set<DTOfertaExtendido> dtOfertas,  byte[] imagen, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        super(nickname,  correo_electronico,  apellido,  nombre,  contraseña,  imagen, seguidos, seguidores);
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
