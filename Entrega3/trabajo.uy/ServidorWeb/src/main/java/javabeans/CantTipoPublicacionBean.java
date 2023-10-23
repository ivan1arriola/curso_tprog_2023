package javabeans;

public class CantTipoPublicacionBean {
    private String nombre;
    private int cantidad;

    public CantTipoPublicacionBean() {
        // Constructor sin argumentos
        this.nombre = null;
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}