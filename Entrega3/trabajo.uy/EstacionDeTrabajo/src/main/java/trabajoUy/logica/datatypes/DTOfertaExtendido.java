package trabajoUy.logica.datatypes;

import java.time.LocalDate;
import java.util.Set;

import trabajoUy.logica.enumerados.DepUY;
import trabajoUy.logica.enumerados.EstadoOL;


public class DTOfertaExtendido {
  private String nombre;
  private String descripcion;
  private LocalDate fechaDeAlta;
  private float costo;
  private float remuneracion;
  private DTHorario horario;
  private DepUY departamento;
  private String ciudad;
  private EstadoOL estado;
  private Set<DTPostulacion> postulaciones;
  private String imagen; 
  private String paq;
	
	
public DTOfertaExtendido(String nomb,  String desc,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY dep,  String ciu,  EstadoOL est,  Set<DTPostulacion> post,  String img,  String paquete) {
  nombre = nomb;
  descripcion = desc;
  fechaDeAlta = fechaA;
  costo = cost;
  remuneracion = remu;
  this.horario = horario;
  departamento = dep;
  ciudad = ciu;
  estado = est;
  postulaciones = post;
  imagen = img;
  paq = paquete;
}
	
public String getNombre() {
  return nombre;
}
	
public String getDescripcion() {
  return descripcion;
}
	
public LocalDate getFechaDeAlta() {
  return fechaDeAlta;
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
	
public EstadoOL getEstado() {
  return estado; 
}
	
public Set<DTPostulacion> getPostulaciones() {
  return postulaciones;
}
	
public String getImagen() {
  return imagen;
}
	
public String getPaquete() {
  return paq;
}
	
	@Override
public String toString() {
  String texto = "Nombre: " + nombre + "\n" + "Descripción: " + descripcion + "\n"  
		  		+ "Fecha de alta: " + fechaDeAlta + "\n" + "Costo: " 
		  		+ (int) costo + "\n" + "Remuneración: " + (int) remuneracion + "\n" 
		  		+ "Horario de Entrada: " + horario.getDesde() + "\n" 
		  		+ "Horario de Salida: " + horario.getHasta() + "\n" + ciudad
		  		+ ",  " + departamento + "\n"  + "Estado: " + estado;

  return texto;
}
	
}
//