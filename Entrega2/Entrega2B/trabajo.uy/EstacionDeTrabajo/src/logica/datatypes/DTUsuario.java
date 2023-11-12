package logica.datatypes;

public class DTUsuario {
    private String nickname;
    private String correoElectronico;
    private String apellido;
    private String nombre;
    private String contrasenia;
    private byte[]  imagen;

    public DTUsuario(String nickname,  String correoElectronico,  String apellido,  String nombre,  String contrasenia,  byte[]  img) {
        this.nickname = nickname;
        this.correoElectronico = correoElectronico;
        this.apellido = apellido; 
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.imagen = img;
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
    
    
}