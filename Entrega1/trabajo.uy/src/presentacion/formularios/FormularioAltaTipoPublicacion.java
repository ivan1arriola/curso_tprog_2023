package presentacion.formularios;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presentacion.componentes.IFormulario;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class FormularioAltaTipoPublicacion extends JPanel implements IFormulario {

    private static final long serialVersionUID = 1L; //Ni idea que es pero me lo sugirio Eclipse
	private JTextField nombreField;
    private JTextArea descripcionTextArea;
    private JSpinner exposicionSpinner;
    private JSpinner duracionSpinner;
    private JFormattedTextField costoField;
    private JTextField fechaAltaField;
    private LocalDate fechaActual;

    public FormularioAltaTipoPublicacion() {
        setLayout(new GridLayout(7, 2, 15, 5));

        // Nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        add(nombreLabel);
        add(nombreField);

        // Descripción
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionTextArea = new JTextArea(5, 20);
        descripcionTextArea.setLineWrap(true);
        descripcionTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcionTextArea);
        add(descripcionLabel);
        add(scrollPane);

        // Exposición
        JLabel exposicionLabel = new JLabel("Exposición:");
        exposicionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        add(exposicionLabel);
        add(exposicionSpinner);

        // Duración en días
        JLabel duracionLabel = new JLabel("Duración en días:");
        duracionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        add(duracionLabel);
        add(duracionSpinner);

        // Costo
        JLabel costoLabel = new JLabel("Costo:");
        costoField = new JFormattedTextField(new DecimalFormat("#.##"));
        costoField.setHorizontalAlignment(SwingConstants.TRAILING);
        costoField.setValue(0.0);
        add(costoLabel);
        add(costoField);

        // Fecha de alta (No editable)
        JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
        fechaAltaField = new JTextField();
        fechaAltaField.setHorizontalAlignment(SwingConstants.TRAILING);
        fechaAltaField.setEditable(false);

        // Obtener la fecha actual
        fechaActual = LocalDate.now();

        // Formatear la fecha en el formato dd/mm/aaaa
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);

        // Establecer la fecha formateada en el campo de texto
        fechaAltaField.setText(fechaFormateada);

        add(fechaAltaLabel);
        add(fechaAltaField);
        
        nombreField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }
        });

       descripcionTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	descripcionTextArea.setBackground(UIManager.getColor("TextField.background"));
            }

            
			@Override
            public void removeUpdate(DocumentEvent e) {
				descripcionTextArea.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	descripcionTextArea.setBackground(UIManager.getColor("TextField.background"));
            }
        });
       
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
            return Float.parseFloat(costoField.getText());
        } catch (NumberFormatException ex) {
            return 0.0f; // Valor predeterminado en caso de error.
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
            nombreField.setBackground(Color.RED);
            valid = false;
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            nombreField.setBackground(UIManager.getColor("TextField.background"));
        }

        if (descripcion.isEmpty()) {
            descripcionTextArea.setBackground(Color.RED);
            valid = false;
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            descripcionTextArea.setBackground(UIManager.getColor("TextField.background"));
        }

        return valid;
    }

	@Override
	public void limpiar() {
	   	nombreField.setText("");
	    descripcionTextArea.setText("");
	    costoField.setText(""); 
	}

	

}

