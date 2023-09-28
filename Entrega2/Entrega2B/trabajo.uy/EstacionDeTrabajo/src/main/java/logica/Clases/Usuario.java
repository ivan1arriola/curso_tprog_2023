package main.java.logica.Clases;

import java.util.Set;

import main.java.logica.Datatypes.DTUsuario;

public abstract class Usuario {
	
	private String nickname;
    private String nombre;
    private String apellido;
    private String correo_electronico;
    
    private String contrasenia;


    //Getters


    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    // Setters


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public Usuario(String nickname, String nombre, String apellido, String correo_electronico) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido; 
        this.correo_electronico = correo_electronico;
    }

    // Operaciones

    public abstract boolean esEmpresa();

    public abstract DTUsuario obtenerDatosUsuario();
    
    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido;
    }
    
    public abstract Set<String> listarOfertasLaborales();

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
    
}
