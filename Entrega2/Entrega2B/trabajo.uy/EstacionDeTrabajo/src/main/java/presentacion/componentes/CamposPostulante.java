package main.java.presentacion.componentes;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CamposPostulante extends JPanel implements IFormulario {
    private static final long serialVersionUID = -4855817509680138235L;

    
    // Campos específicos para el tipo Postulante
    private JTextField fechaNacimientoField;
    private JTextField nacionalidadField;

    // Etiquetas para los campos de Postulante
    private JLabel lblFechaNacimiento;
    private JLabel lblNacionalidad;

    public CamposPostulante() {
    	setGridLayout(2, 2);

        lblFechaNacimiento = new JLabel("Fecha de Nacimiento* (dd-MM-yyyy):");
        fechaNacimientoField = new JTextField();

        lblNacionalidad = new JLabel("Nacionalidad*:");
        nacionalidadField = new JTextField();

        add(lblFechaNacimiento);
        add(fechaNacimientoField);
        add(lblNacionalidad);
        add(nacionalidadField);
    }

    public void setGridLayout(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
    }


    public void setEditable(boolean habilitar) {
        fechaNacimientoField.setEditable(habilitar);
        nacionalidadField.setEditable(habilitar);
    }

    
    
    @Override
    public boolean validar() {
    	
    	String fechaNacimiento = fechaNacimientoField.getText().trim();
        String nacionalidad = nacionalidadField.getText().trim();
    	// Validar campos específicos para Postulante
        if (fechaNacimiento.isEmpty()) {
            throw new IllegalArgumentException("El campo de Fecha de Nacimiento no puede estar vacío.");
        }
        // Validar el formato de fecha "dd/mm/aaaa"
        if (!fechaNacimiento.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("El campo de Fecha de Nacimiento debe estar en el formato dd/mm/aaaa.");
        }
       
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        LocalDate fechaActual = LocalDate.now();

        if (fechaNacimientoDate.isAfter(fechaActual)) {
            throw new IllegalArgumentException("La fecha de nacimiento debe ser anterior o igual a la fecha actual.");
        }
        
        if (nacionalidad.isEmpty()) {
            throw new IllegalArgumentException("El campo de Nacionalidad no puede estar vacío.");
        }
        
        return true;
    }

    @Override
    public void limpiar() {
        fechaNacimientoField.setText("");
        nacionalidadField.setText("");
    }
    
    public void setCampos(LocalDate localDate, String nacionalidad) {
        if (localDate != null) {
            fechaNacimientoField.setText(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            fechaNacimientoField.setText("");
        }
        nacionalidadField.setText(nacionalidad != null ? nacionalidad : "");
    }

    
        
    public LocalDate getFechaNacimiento() {
        String fechaNacimientoStr = fechaNacimientoField.getText().trim();

        // Verificar que la fecha de nacimiento no esté vacía antes de intentar la conversión
        if (fechaNacimientoStr.isEmpty()) {
            return null; // O podrías lanzar una excepción si lo prefieres
        }

        try {
            return LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El campo de Fecha de Nacimiento no tiene un formato de fecha válido (dd-MM-yyyy).");
        }
    }


    public String getNacionalidad() {
        return nacionalidadField.getText().trim();
    }

        
}
