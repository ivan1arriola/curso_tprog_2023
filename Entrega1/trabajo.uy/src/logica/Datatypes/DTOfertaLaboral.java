package logica.Datatypes;

import java.time.LocalDate;

import logica.Enumerados.DepUY;

public class DTOfertaLaboral {
	private String nombre;
	private String descripcion;
	private LocalDate fecha_de_alta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	
	public DTOfertaLaboral(String nomb, String desc, LocalDate fechaA, float c, float r, DTHorario h, DepUY dep, String ciu) {
		nombre = nomb;
		descripcion = desc;
		fecha_de_alta = fechaA;
		costo = c;
		remuneracion = r;
		horario = h;
		departamento = dep;
		ciudad = ciu;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public LocalDate getFechaDeAlta() {
		return fecha_de_alta;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public float getRemuneracion() {
		return remuneracion;
	}
	
	public DTHorario getHorario() {
		return horario;
	}
	
	public DepUY getDepartamento() {
		return departamento;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
    @Override
    public String toString() {
        return 	nombre + " - " + descripcion + "\n" + fecha_de_alta + "\n" + costo + " - " + remuneracion + " - " + horario + "\n" + departamento +","+ciudad;
    }
}
