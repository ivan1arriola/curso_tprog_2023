package logica.Datatypes;
import logica.Enumerados.EstadoOL;
import java.time.LocalDate;
import logica.Enumerados.DepUY;
import java.util.Set;


public class DTOfertaExtendido {
	private String nombre;
	private String descripcion;
	private LocalDate fecha_de_alta;
	private float costo;
	private float remuneracion;
	private DTHorario horario;
	private DepUY departamento;
	private String ciudad;
	private EstadoOL estado;
	private Set<DTPostulacion> postulaciones;
	
	
	public DTOfertaExtendido(String nomb, String desc, LocalDate fechaA, float c, float r, DTHorario h, DepUY dep, String ciu, EstadoOL est, Set<DTPostulacion> post) {
		nombre = nomb;
		descripcion = desc;
		fecha_de_alta = fechaA;
		costo = c;
		remuneracion = r;
		horario = h;
		departamento = dep;
		ciudad = ciu;
		estado = est;
		postulaciones = post;
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
	
	public String getCiudad() { return ciudad;	}
	
	public EstadoOL getEstado() { return estado; }
	
	
	public Set<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}
	
	@Override 
    public String toString() {
        
		String texto = "Nombre: " + nombre + "\n" +
				"Descripción: " + descripcion + "\n" +
				"Fecha de alta: " + fecha_de_alta + "\n" +
				"Costo: " + (int)costo + "\n" +
				"Remuneración: " + (int)remuneracion + "\n" +
				"Horario de Entrada: " + horario.getDesde() + "\n" +
				"Horario de Salida: " + horario.getHasta() + "\n" +
				ciudad + ", "+departamento + "\n" + 
				"Estado: " + estado;

		return texto;
    }
	
}
//