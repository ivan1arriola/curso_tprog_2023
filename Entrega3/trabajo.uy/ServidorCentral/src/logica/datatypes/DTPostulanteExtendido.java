package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import logica.servidor.adapter.SetDTCantTOAdapter;
import logica.servidor.adapter.SetDTPostulacionAdapter;

import java.time.LocalDate;
import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulanteExtendido extends DTPostulante {


    @XmlJavaTypeAdapter(SetDTPostulacionAdapter.class)
    private Set<DTPostulacion> postulaciones;


    public DTPostulanteExtendido(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, byte[] imagen, LocalDate fechanac, String nacionalidad, Set<DTPostulacion> posts, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores, Set<DTOfertaExtendido> favs) {
        super(nickname, correo_electronico, apellido, nombre, contraseña, imagen,
                fechanac, nacionalidad, seguidos, seguidores, favs);
        postulaciones = posts;
    }

    public Set<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }
}