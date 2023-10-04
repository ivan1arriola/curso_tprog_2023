package main.java.logica.Datatypes;

public class DTEmpresa extends DTUsuario {
	
    private String nombreEmpresa;
    private String descripcion;
    private String url;


    public DTEmpresa(String nickname, String correo_electronico, String apellido, String nombre, String nombreEmpresa, String descripcion, String url, byte[] imagen) {
        super(nickname, correo_electronico, apellido, nombre, imagen);
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.url = url;
    }


	public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }
}
