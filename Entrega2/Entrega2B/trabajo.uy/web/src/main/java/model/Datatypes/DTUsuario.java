package model.Datatypes;

public class DTUsuario {
    private String nickname;
    private String correo_electronico;
    private String apellido;
    private String nombre;
    private String imagen;

    public DTUsuario(String nickname, String correo_electronico, String apellido, String nombre) {
        this.nickname = nickname;
        this.correo_electronico = correo_electronico;
        this.apellido = apellido;
        this.nombre = nombre;
    }
    
    public DTUsuario(String nickname, String correo_electronico, String apellido, String nombre, String imagen) {
        this.nickname = nickname;
        this.correo_electronico = correo_electronico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido + " " + imagen;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getImagen() {
    	return imagen;
    }
}