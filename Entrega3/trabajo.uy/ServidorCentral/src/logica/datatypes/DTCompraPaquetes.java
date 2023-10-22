package logica.datatypes;

import java.time.LocalDate;
// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
// import java.util.Set; NO SE USA (CHECKSTYLE)

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraPaquetes {
	
	private String nombre;
	private LocalDate fechacompra;
	private LocalDate fechavencimiento;
	
	public DTCompraPaquetes(String nomb,  LocalDate fechacomp,  LocalDate fechavenc) {
		nombre = nomb;
		fechacompra = fechacomp;
		fechavencimiento = fechavenc;
	}

	public String getNombre() {
		return nombre;
	}
	
	public LocalDate getFechaCompra() {
		return fechacompra;
	}
	
	public LocalDate getFechaVencimiento() { 
		return fechavencimiento;
	}
}
