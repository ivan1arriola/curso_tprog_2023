package logica.datatypes;

import java.util.ArrayList;
import java.util.Set;


public class DTUsuarioCompleto {
    private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private byte[]  imagen;
    private ArrayList<DTUsuarioSinInfoSocial> seguidos;
    private ArrayList<DTUsuarioSinInfoSocial> seguidores;

    private String descripcion;
    private String url;
    private ArrayList<DTOfertaExtendido> ofertasLaborales;

    public DTUsuarioCompleto(){}
    public DTUsuarioCompleto(String nickname, String correoElectronico, String apellido, String nombre, String contrasenia, byte[]  img, ArrayList<DTUsuarioSinInfoSocial> seguidos, ArrayList<DTUsuarioSinInfoSocial> seguidores) {
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

    public byte[]  getImagen() {
    	return imagen;
    }

    public ArrayList<DTUsuarioSinInfoSocial>  getSeguidos() {
    	return seguidos;
    }

    public ArrayList<DTUsuarioSinInfoSocial>  getSeguidores() {
    	return seguidores;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public void setSeguidos(ArrayList<DTUsuarioSinInfoSocial> seguidos) {
        this.seguidos = seguidos;
    }

    public void setSeguidores(ArrayList<DTUsuarioSinInfoSocial> seguidores) {
        this.seguidores = seguidores;
    }
}