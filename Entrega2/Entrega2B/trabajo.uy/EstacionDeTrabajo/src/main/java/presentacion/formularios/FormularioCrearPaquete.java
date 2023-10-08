/*package main.java.presentacion.formularios;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import main.java.presentacion.componentes.IFormulario;

public class FormularioCrearPaquete extends JPanel implements IFormulario {

	private static final long serialVersionUID = 1L;
	private JTextField nombreFieldText;
    private JTextArea descripcionTextField;
    private JSpinner periodoValidezSpinner;
    private JSpinner descuentoSpinner;

    public FormularioCrearPaquete() {
        // Configurar el diseño del panel
        setLayout(new GridLayout(4, 2, 10, 10));

        // Crear componentes
        JLabel lblNombre = new JLabel("Nombre:");
        nombreFieldText = new JTextField(20);
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        descripcionTextField = new JTextArea(5, 20);
        descripcionTextField.setLineWrap(true);
        descripcionTextField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descripcionTextField);
        
        
        JLabel lblPeriodoValidez = new JLabel("Período de validez (días):");
        periodoValidezSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        
        
        JLabel lblDescuento = new JLabel("Descuento (%):");
        descuentoSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        // Agregar componentes al panel
        add(lblNombre);
        add(nombreFieldText);
        
        add(lblDescripcion);
        add(scrollPane);
        
        add(lblPeriodoValidez);
        add(periodoValidezSpinner);
        add(lblDescuento);
        add(descuentoSpinner);
    }

    // Métodos para obtener valores de los campos
    public String getNombre() {
        return nombreFieldText.getText();
    }

    public String getDescripcion() {
        return descripcionTextField.getText();
    }

    public int getPeriodoValidez() {
        return (int) periodoValidezSpinner.getValue();
    }

    public int getDescuento() {
        return (int) descuentoSpinner.getValue();
    }

	@Override
	public boolean validar() {
		String nombre = getNombre();
	    String descripcion = getDescripcion();
	    int periodoValidez = getPeriodoValidez();
	    int descuento = getDescuento();
	    
	    // Verificar que ningún campo esté vacío
	    if (nombre.isEmpty() || descripcion.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Ningún campo puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Verificar que el período de validez sea un número entero mayor a 1
	    if (periodoValidez < 1) {
	        JOptionPane.showMessageDialog(this, "El período de validez debe ser un número entero mayor o igual a 1.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Verificar que el descuento sea un número real entre 0 y 100 inclusive
	    if (descuento < 0 || descuento > 100) {
	        JOptionPane.showMessageDialog(this, "El descuento debe ser un número real entre 0 y 100 inclusive.", "Error de validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Si todas las validaciones pasan, el formulario es válido
	    return true;
	}

	@Override
	public void limpiar() {
		nombreFieldText.setText("");
	    descripcionTextField.setText("");
	    periodoValidezSpinner.setValue(1);
	    descuentoSpinner.setValue(0);

	}

}
*/
