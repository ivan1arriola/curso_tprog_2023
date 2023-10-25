package logica.servidor;

import logica.servidor.bean.DateBean;
import logica.servidor.bean.DTOfertaLaboralCompleta;
import logica.servidor.bean.PostulacionBean;
import logica.servidor.enumeration.TipoUsuario;

import java.util.ArrayList;


public class UsuarioBean {
    private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private String imagen;
    private String descripcion;
    private String url;
    private DateBean fechaNac;
    private String nacionalidad;
    private ArrayList<DTOfertaLaboralCompleta> ofertasLaborales;
    private ArrayList<PostulacionBean> postulaciones;
    private TipoUsuario tipo;
    private ArrayList<UsuarioBean> seguidos;
    private ArrayList<UsuarioBean> seguidores;

    public UsuarioBean() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateBean getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(DateBean fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ArrayList<DTOfertaLaboralCompleta> getOfertasLaborales() {
        return ofertasLaborales;
    }

    public void setOfertasLaborales(ArrayList<DTOfertaLaboralCompleta> ofertasLaborales) {
        this.ofertasLaborales = ofertasLaborales;
    }

    public ArrayList<PostulacionBean> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(ArrayList<PostulacionBean> postulaciones) {
        this.postulaciones = postulaciones;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public ArrayList<UsuarioBean> getSeguidos() {
        return seguidos;
    }

    public void setSeguidos(ArrayList<UsuarioBean> seguidos) {
        this.seguidos = seguidos;
    }

    public ArrayList<UsuarioBean> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(ArrayList<UsuarioBean> seguidores) {
        this.seguidores = seguidores;
    }
}
