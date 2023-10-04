package main.java.logica.Datatypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class DTCompraPaquetes {
	
	private String nombre;
	private LocalDate fecha_compra;
	private LocalDate fecha_vencimiento;
	
	DTCompraPaquetes(String nomb, LocalDate fecha_comp, LocalDate fecha_venc){
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
