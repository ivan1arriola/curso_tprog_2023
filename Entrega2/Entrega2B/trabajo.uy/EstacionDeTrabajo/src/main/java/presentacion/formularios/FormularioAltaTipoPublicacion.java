package main.java.presentacion.formularios;

import javax.swing.*;
import main.java.presentacion.componentes.IFormulario;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class FormularioAltaTipoPublicacion extends JPanel implements IFormulario {

    private static final long serialVersionUID = 1L; 
	private JTextField nombreField;
    private JTextArea descripcionTextArea;
    private JSpinner exposicionSpinner;
    private JSpinner duracionSpinner;
    private JSpinner costoField;
    private JTextField fechaAltaField;
    private LocalDate fechaActual;

    public FormularioAltaTipoPublicacion() {
        setLayout(new GridBagLayout()); 
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        addField("Nombre:", nombreField = new JTextField(), constraints);
        
        constraints.gridy++;
        addField("Descripción:", createScroll(descripcionTextArea = createTextArea(5, 20)), constraints);
        
        constraints.gridy++;
        addField("Exposición:", exposicionSpinner = createSpinner(1, 1, 100, 1), constraints);
        
        constraints.gridy++;
        addField("Duración en días:", duracionSpinner = createSpinner(1, 1, Integer.MAX_VALUE, 1), constraints);
        
        constraints.gridy++;
        addField("Costo:", costoField = createCostoField(), constraints);
        
        constraints.gridy++;
        addField("Fecha de alta:", fechaAltaField = createFechaAltaField(), constraints);

        fechaActual = LocalDate.now();
        setearFecha(fechaActual);
        
        limpiar();
    }
    
    private void addField(String labelText, JComponent component, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        add(label, constraints);
        constraints.gridx++;
        constraints.weightx = 1.0; // Alinear componentes en el eje X
        add(component, constraints);
        constraints.gridx = 0; // Restablecer la posición X
        constraints.gridy++; // Moverse a la siguiente fila
        constraints.weightx = 0.0; // Restablecer el peso X
    }
    
    private JScrollPane createScroll(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    }
    
    private JTextArea createTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
    
    private JSpinner createSpinner(int value, int min, int max, int step) {
        return new JSpinner(new SpinnerNumberModel(value, min, max, step));
    }

    private JSpinner createCostoField() {
        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        JSpinner spinner = new JSpinner(model);
        
        // Alinea el valor del Spinner a la derecha
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        editor.getTextField().setHorizontalAlignment(SwingConstants.RIGHT);
        
        return spinner;
    }


    private JTextField createFechaAltaField() {
        JTextField field = new JTextField();
        field.setHorizontalAlignment(SwingConstants.TRAILING);
        field.setEditable(false);
        return field;
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public String getDescripcion() {
        return descripcionTextArea.getText();
    }

    public int getExposicion() {
        return (int) exposicionSpinner.getValue();
    }

    public int getDuracion() {
        return (int) duracionSpinner.getValue();
    }

    public float getCosto() {
        try {
            return (Float) costoField.getValue();
        } catch (NumberFormatException ex) {
            return 0.0f; 
        }
    }

    public LocalDate getFechaAlta() {
        return fechaActual;
    }

    public void limpiarFormulario() {
        nombreField.setText("");
        descripcionTextArea.setText("");
        exposicionSpinner.setValue(1);
        duracionSpinner.setValue(1);
        costoField.setValue(0.0);
    }
    
    @Override
    public boolean validar() {
        String nombre = getNombre();
        String descripcion = getDescripcion();
        boolean valid = true;

        if (nombre.isEmpty()) {
            showErrorDialog("El nombre no puede estar vacío.");
            valid = false;
        }

        if (descripcion.isEmpty()) {
            showErrorDialog("La descripción no puede estar vacía.");
            valid = false;
        }

        return valid;
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    

    
    private void setearFecha(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = date.format(formatter);
        fechaAltaField.setText(fechaFormateada);
    }


	@Override
	public void limpiar() {
	   	nombreField.setText("");
	    descripcionTextArea.setText("");
	}

	

}

