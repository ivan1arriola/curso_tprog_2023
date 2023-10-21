package logica.clases;

import java.util.Set;

import logica.datatypes.DTUsuario;

public abstract class Usuario {
	
	private String nickname;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasenia;
    private byte[] imagen;

    //Getters
    public String getNickname() {
    	return nickname;
    }
    
    public String getNombre() 	{ 
    	return nombre;  
    }
    
    public String getApellido() { 
    	return apellido;
    }
    
    public String getcorreoElectronico() { 
    	return correoElectronico;
    }
    
	public String getcontrasenia() { 
		return contrasenia;
	}
	
	public byte[] getImagen() { 
		return imagen;
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
    
    public void setCorreoElectronico(String correoElectronico) {
    	this.correoElectronico = correoElectronico;
    }
    
    public void setContrasenia(String contrasenia) {
    	this.contrasenia = contrasenia;
    }
    
    public void setImagen(byte[] imagen) {
    	this.imagen = imagen;
    }

    public Usuario(String nickname, String nombre, String apellido, String correo_electronico, String contrasenia) {
        this(nickname, nombre, apellido, correo_electronico, contrasenia, null);
    }

    public Usuario(String nickname, String nombre, String apellido, String correo_electronico, String contrasenia, byte[] imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correo_electronico;
        this.contrasenia = contrasenia;
        this.imagen = imagen;
        
        System.out.println("Se ha creado un usuario. - " + nickname);
    }


    // OPERACIONES

    public abstract boolean esEmpresa();

    public abstract DTUsuario obtenerDatosUsuario();
    
    
    // corregido,  se pasan mas parametros para la ejecucion
    // para visitantes colocar en usuario registrado actual 'nada'
    public abstract DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual,  String UsuarioQueSeHaceConsulta); // operacion implementada en las subclases


    // NO ESTA EN EL DCD
    public abstract Set<String> listarOfertasLaborales();
    
    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido;
    }
    
}
