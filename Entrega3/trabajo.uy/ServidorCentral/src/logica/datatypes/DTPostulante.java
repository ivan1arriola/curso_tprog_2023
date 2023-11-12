package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
//import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulante extends DTUsuario {


    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechanac;
    private String nacionalidad;
    private Set<DTOfertaExtendido> favs;

    public DTPostulante(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, byte[] imagen, LocalDate fechaNac, String nacionalidad, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores, Set<DTOfertaExtendido> favs) {
        super(nickname, correo_electronico, apellido, nombre, contraseña, imagen, seguidos, seguidores);
        this.fechanac = fechaNac;
        this.nacionalidad = nacionalidad;
        this.favs = favs;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFechaNac() {
        return fechanac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

	public Set<DTOfertaExtendido> getFavs() {
		return favs;
	}

	public void setFavs(Set<DTOfertaExtendido> favs) {
		this.favs = favs;
	}
}
