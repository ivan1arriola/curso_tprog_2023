package presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;
import presentacion.componentes.AceptarListener;
import presentacion.componentes.CancelarListener;
import presentacion.componentes.PanelBotones;

import javax.swing.SwingConstants;

public class AltaTipoPublicaciónOfertaLaboral extends JInternalFrame implements AceptarListener, CancelarListener {
	
	private static final long serialVersionUID = 1L;
	Fabrica fabrica = Fabrica.getInstance();
    ICtrlOferta ctrlOferta = fabrica.getICtrlOferta();

    private JTextField nombreField;
    private JTextArea descripcionTextArea;
    private JSpinner exposicionSpinner;
    private JSpinner duracionSpinner;
    private JFormattedTextField costoField;
    private JTextField fechaAltaField;
    
    
    public AltaTipoPublicaciónOfertaLaboral() {
    	setIconifiable(true);
    	setMaximizable(true);
    	setResizable(true);
    	setClosable(true);
    	 setTitle("Alta de Tipo de publicación de Oferta Laboral");
         setBounds(100, 100, 800, 600);
         
         JPanel mainPanel = new JPanel();
         getContentPane().add(mainPanel, BorderLayout.CENTER);
         mainPanel.setLayout(new BorderLayout(0, 0));
         mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
         
         
         
         //

         // Formulario
         JPanel formularioPanel = new JPanel();
         mainPanel.add(formularioPanel, BorderLayout.CENTER);
         
         

         // Campos de formulario
         // Nombre 
         JLabel nombreLabel = new JLabel("Nombre:");
         nombreField = new JTextField();
         formularioPanel.setLayout(new GridLayout(7, 2, 15, 5));
         formularioPanel.add(nombreLabel);
         formularioPanel.add(nombreField);

         // Descripcion
         JLabel descripcionLabel = new JLabel("Descripción:");
         descripcionTextArea = new JTextArea(5, 20); // 5 filas y 20 columnas
         descripcionTextArea.setLineWrap(true); // Habilitar el ajuste automático de línea
         descripcionTextArea.setWrapStyleWord(true); // Ajustar palabras completas al cambiar de línea
         JScrollPane scrollPane = new JScrollPane(descripcionTextArea); // Agregar un JScrollPane si es necesario
         formularioPanel.add(descripcionLabel);
         formularioPanel.add(scrollPane); // Agregar el JTextArea al formularioPanel


         // Campo de Exposición
         JLabel exposicionLabel = new JLabel("Exposición:");
         exposicionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
         formularioPanel.add(exposicionLabel);
         formularioPanel.add(exposicionSpinner);

         // Campo de Duración en días
         JLabel duracionLabel = new JLabel("Duración en días:");
         duracionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
         formularioPanel.add(duracionLabel);
         formularioPanel.add(duracionSpinner);

         // Campo de Costo
         JLabel costoLabel = new JLabel("Costo:");
         costoField = new JFormattedTextField(new DecimalFormat("#.##"));
         costoField.setHorizontalAlignment(SwingConstants.TRAILING);
         costoField.setValue(0.0);
         formularioPanel.add(costoLabel);
         formularioPanel.add(costoField);

         // Campo de Fecha de alta (No editable)
         JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
         fechaAltaField = new JTextField();
         fechaAltaField.setHorizontalAlignment(SwingConstants.TRAILING);
         fechaAltaField.setEditable(false);

         // Obtener la fecha actual
         LocalDate fechaActual = LocalDate.now();
         
         // Formatear la fecha en el formato dd/mm/aaaa
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String fechaFormateada = fechaActual.format(formatter);

         // Establecer la fecha formateada en el campo de texto
         fechaAltaField.setText(fechaFormateada);

         formularioPanel.add(fechaAltaLabel);
         formularioPanel.add(fechaAltaField);




         
         
         // Crea el PanelBotones
         PanelBotones botonesPanel = new PanelBotones();
         mainPanel.add(botonesPanel, BorderLayout.SOUTH);

         // Configura los Listener de aceptar y cancelar
         botonesPanel.setAceptarListener(this);
         botonesPanel.setCancelarListener(this);

        
        
    
        
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
    
    private void limpiarFormulario() {
    	nombreField.setText("");
    	descripcionTextArea.setText("");
    	costoField.setText("");
    }

	@Override
	public void onCancelar() {
		// Logica para boton Cancelar
		limpiarFormulario();
		setVisible(false);
		
	}

	@Override
	public void onAceptar() {
        // Lógica para el botón "Aceptar"
        String nombre = nombreField.getText().trim();
        String descripcion = descripcionTextArea.getText().trim();
        LocalDate fechaAlta = LocalDate.now(); // Puedes obtener la fecha actual directamente

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

        int exposicion = (int) exposicionSpinner.getValue();
        int duracion = (int) duracionSpinner.getValue();

        float costo = 0.0f;
        try {
            costo = Float.parseFloat(costoField.getText());
        } catch (NumberFormatException ex) {
            valid = false;
            JOptionPane.showMessageDialog(null, "El costo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (valid) {
            try {
                boolean res = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
                System.out.print(res);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Tipo de publicación de oferta laboral creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
