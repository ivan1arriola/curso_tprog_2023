package main.java.logica.datatypes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

public class DTOfertaExtendidoConKeywords {
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
  private byte[] imagen; 
  private HashSet<String> keywords;
	
	
public DTOfertaExtendidoConKeywords(String nomb, String desc, LocalDate fechaA, float c, float r, DTHorario h, DepUY dep, String ciu, EstadoOL est, Set<DTPostulacion> post, byte[] img, HashSet<String> keys) {
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
  imagen = img;
  keywords = keys;
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
	
public DepUY getDepartamento()	{
  return departamento;
}
	
public String getCiudad() { 
  return ciudad;
}
	
public EstadoOL getEstado()	{
 return estado;
}
	
public Set<DTPostulacion> getPostulaciones() { 
  return postulaciones;
}
	
public byte[] getImagen() {
  return imagen;
}
	
public HashSet<String> getKeywords() {
  return keywords;
}

	
	
@Override
public String toString() {
      
  String texto = "Nombre: " + nombre + "\n" + "Descripción: " + descripcion + "\n" + "Fecha de alta: " + fecha_de_alta 
				     + "\n" + "Costo: " + (int) costo + "\n" + "Remuneración: " + (int) remuneracion + "\n" 
				     + "Horario de Entrada: " + horario.getDesde() + "\n" + "Horario de Salida: " + horario.getHasta() 
				     + "\n" + ciudad + ", " + departamento + "\n"  + "Estado: " + estado;

  return texto;
}

}
 