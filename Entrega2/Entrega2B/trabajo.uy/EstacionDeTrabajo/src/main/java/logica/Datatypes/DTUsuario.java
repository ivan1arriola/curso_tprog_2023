package main.java.logica.Datatypes;

public class DTUsuario {
    private String nickname;
    private String correo_electronico;
    private String apellido;
    private String nombre;
    private String contraseña;
    private byte[] imagen;

    public DTUsuario(String nickname, String correo_electronico, String apellido, String nombre, String contraseña, byte[] img) {
        this.nickname = nickname;
        this.correo_electronico = correo_electronico;
        this.apellido = apellido; 
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.imagen = img;
    }

    @Override
    public String toString() {
        return nickname + " - " + nombre + " " + apellido + " " + imagen;
    }

    public String getNickname() 			{ return nickname; }
    public String getCorreo_electronico() 	{ return correo_electronico; }
    public String getApellido() 			{ return apellido; }
    public String getContraseña() 			{ return contraseña; }
    public String getNombre() 				{ return nombre; }  
    public byte[] getImagen() 				{ return imagen; }
    
    
}