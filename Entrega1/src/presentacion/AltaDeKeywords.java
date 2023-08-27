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

public class AltaDeKeywords extends JInternalFrame {
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public AltaDeKeywords() {
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
        setTitle("Alta de Keywords");
        setBounds(30, 30, 477, 153);
        getContentPane().setLayout(null); //Absolute Layout
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        	}
        });
        btnAceptar.setBounds(63, 78, 117, 25);
        getContentPane().add(btnAceptar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(297, 78, 117, 25);
        getContentPane().add(btnCerrar);
        
        textField = new JTextField();
        textField.setBounds(109, 30, 346, 32);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre.setBounds(23, 38, 133, 15);
        getContentPane().add(lblNombre);
	}
}
