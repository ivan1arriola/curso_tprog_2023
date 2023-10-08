package main.java.logica.datatypes;

import java.time.LocalDate;
// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
// import java.util.Set; NO SE USA (CHECKSTYLE)

public class DTCompraPaquetes {
	
	private String nombre;
	private LocalDate fecha_compra;
	private LocalDate fecha_vencimiento;
	
	public DTCompraPaquetes(String nomb, LocalDate fecha_comp, LocalDate fecha_venc){
		nombre = nomb;
		fecha_compra = fecha_comp;
		fecha_vencimiento = fecha_venc;
	}

	String getNombre() {
		return nombre;
	}
	
	LocalDate getFechaCompra() {
		return fecha_compra;
	}
	
	LocalDate getFechaVencimiento() { 
		return fecha_vencimiento;
	}
}
