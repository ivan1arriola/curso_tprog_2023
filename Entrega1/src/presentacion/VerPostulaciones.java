package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import logica.Clases.Usuario;

public class VerPostulaciones extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public VerPostulaciones(String nombre_oferta) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Ver postulaciones");
        setBounds(30, 30, 678, 285);
        getContentPane().setLayout(null);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(338, 216, 117, 25);
        getContentPane().add(btnCerrar);
        
        JComboBox<Usuario> comboBoxUsuarios = new JComboBox<Usuario>();
        comboBoxUsuarios.setBounds(186, 12, 455, 28);
        getContentPane().add(comboBoxUsuarios);
        
        JLabel lblPostulaciones = new JLabel("Lista de postulaciones:");
        lblPostulaciones.setBounds(12, 19, 170, 15);
        getContentPane().add(lblPostulaciones);
        
        JLabel lblIngresoCI_1_2_1 = new JLabel("Fecha:");
        lblIngresoCI_1_2_1.setBounds(12, 59, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1);
        
        JLabel lblIngresoCI_1_2_1_1 = new JLabel("URLDocExtras:");
        lblIngresoCI_1_2_1_1.setBounds(12, 102, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1);
        
        JLabel lblIngresoCI_1_2_1_1_1 = new JLabel("Descripción:");
        lblIngresoCI_1_2_1_1_1.setBounds(12, 143, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1);
        
        JLabel lblIngresoCI_1_2_1_1_1_1 = new JLabel("CV:");
        lblIngresoCI_1_2_1_1_1_1.setBounds(12, 186, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(186, 52, 455, 30);
        getContentPane().add(textField);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(186, 95, 455, 30);
        getContentPane().add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setBounds(186, 136, 455, 30);
        getContentPane().add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setBounds(186, 178, 455, 30);
        getContentPane().add(textField_3);

        
	}
}
