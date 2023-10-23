package logica.datatypes;

import java.time.LocalDate;

import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;


public class DTOfertaLaboral {
  private String nombre;
  private String descripcion;
  private LocalDate fechaDeAlta;
  private float costo;
  private float remuneracion;
  private DTHorario horario;
  private DepUY departamento;
  private String ciudad;
  private byte[]  imagen;
  private EstadoOL estado;
	
	
public DTOfertaLaboral(String nomb,  String desc,  LocalDate fechaA,  float cost,  float remu,  DTHorario horario,  DepUY dep,  String ciu,  EstadoOL estadoOL,  byte[]  imagenBytes) {
  nombre = nomb;
  descripcion = desc;
  fechaDeAlta = fechaA;
  costo = cost;
  remuneracion = remu;
  this.horario = horario;
  departamento = dep;
  ciudad = ciu;
  estado = estadoOL;
  imagen = imagenBytes;
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
	
public byte[]  getImagen() {
  return imagen;
}
	
public EstadoOL getestado() { 
  return estado;
}
	
	
@Override
public String toString() {

  return 	nombre + " - " + descripcion + "\n" + fechaDeAlta + "\n" + costo + " - " + remuneracion 
        		+ " - " + horario + "\n" + departamento + ", " + ciudad;

}
}
