package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.SetAdapter;

import java.util.Set;

@XmlSeeAlso({DTPostulante.class, DTEmpresa.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuario {
    private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private byte[] imagen;

    @XmlJavaTypeAdapter(SetAdapter.class)
    private Set<DTUsuarioSinInfoSocial> seguidos;

    @XmlJavaTypeAdapter(SetAdapter.class)
    private Set<DTUsuarioSinInfoSocial> seguidores;

    public DTUsuario(String nickname, String correoElectronico, String apellido, String nombre, String contrasenia, byte[] img, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        this.nickname = nickname;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.imagen = img;
        this.seguidos = seguidos;
        this.seguidores = seguidores;
    }

    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido + " " + imagen;
    }

    public String getNickname() {
        return nickname;
    }

    public String getcorreoElectronico() {
        return correoElectronico;
    }

    public String getApellido() {
        return apellido;
    }

    public String getcontrasenia() {
        return contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }


    public Set<DTUsuarioSinInfoSocial> getSeguidos() {
        return seguidos;
    }

    public Set<DTUsuarioSinInfoSocial> getSeguidores() {
        return seguidores;
    }


}