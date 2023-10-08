package main.java.logica.datatypes;

import java.time.LocalDate;
// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
// import java.util.Set; NO SE USA (CHECKSTYLE)

public class DTCompraPaquetes {
	
	private String nombre;
	private LocalDate fechacompra;
	private LocalDate fechavencimiento;
	
	public DTCompraPaquetes(String nomb,  LocalDate fechacomp,  LocalDate fechavenc) {
		nombre = nomb;
		fechacompra = fechacomp;
		fechavencimiento = fechavenc;
	}

	String getNombre() {
		return nombre;
	}
	
	LocalDate getFechaCompra() {
		return fechacompra;
	}
	
	LocalDate getFechaVencimiento() { 
		return fechavencimiento;
	}
}
