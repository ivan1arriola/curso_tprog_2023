package main.java.presentacion;

import javax.swing.JInternalFrame;

import main.java.excepciones.UsuarioNoExisteException;
import main.java.logica.Interfaces.ICtrlUsuario;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;


/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ModificarDatosDeUsuarioEmpresa extends JInternalFrame {
	// Controlador de usuarios que se utilizará para las acciones del JFrame
		private JInternalFrame ADU;
	    private ICtrlUsuario icu;
	    private JButton btnCancelar;
	    private JLabel lblIngreseNombre;
	    
	    private AltaDePostulante AltaDePostulanteInternalFrame;
	    private AltaDeEmpresa AltaDeEmpresaInternalFrame;
	    private JTextField nombreNuevo;
	    private JTextField nombreActual;
	    private JTextField apellidoNuevo;
	    private JTextField apellidoActual;
	    private JTextField correoNuevo;
	    private JLabel lblNewLabel_3;
	    private JTextField correoActual;
	    private JLabel lblNewLabel_4;
	    private JTextField passNuevo;
	    private JTextField passActual;
	    private JTextField confirmPassNuevo;
	    private JLabel lblNewLabel_5;
	    private JLabel lblNewLabel_6;
	    private JLabel lblNewLabel_7;
	    private JTextField ulrNuevo;
	    private JTextField urlActual;
	    private JLabel lblNewLabel_8;
	    private JTextArea descripcionNuevo;
	    private JTextArea descripcionActual;


	    
	    
	    /**
	     * Create the frame.
	     */
    public ModificarDatosDeUsuarioEmpresa(ICtrlUsuario icu, DTEmpresa empresa) {
    	// Se inicializa con el controlador de usuarios
        // Fabrica fabrica = Fabrica.getInstance();
        // icu = fabrica.getICtrlUsuario();

        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(10, 40, 507, 423);
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Modifique los datos de la empresa: " + empresa.getNickname() );
        lblIngreseNombre.setBounds(12, 12, 375, 34);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
                
        
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 88, 81, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datos Actuales");
		lblNewLabel_1.setBounds(354, 58, 109, 25);
		getContentPane().add(lblNewLabel_1);
		
		nombreNuevo = new JTextField();
		nombreNuevo.setBounds(179, 91, 123, 20);
		getContentPane().add(nombreNuevo);
		nombreNuevo.setColumns(10);
		
		nombreActual = new JTextField();
		nombreActual.setBounds(343, 91, 120, 20);
		nombreActual.setEditable(false);
		getContentPane().add(nombreActual);
		nombreActual.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido: ");
		lblNewLabel_2.setBounds(10, 124, 81, 14);
		getContentPane().add(lblNewLabel_2);
		
		apellidoNuevo = new JTextField();
		apellidoNuevo.setColumns(10);
		apellidoNuevo.setBounds(179, 122, 123, 20);
		getContentPane().add(apellidoNuevo);
		
		apellidoActual = new JTextField();
		apellidoActual.setColumns(10);
		apellidoActual.setBounds(343, 122, 120, 20);
		apellidoActual.setEditable(false);
		getContentPane().add(apellidoActual);
		
		correoNuevo = new JTextField();
		correoNuevo.setColumns(10);
		correoNuevo.setBounds(179, 155, 123, 20);
		getContentPane().add(correoNuevo);
		
		lblNewLabel_3 = new JLabel("E-mail: ");
		lblNewLabel_3.setBounds(10, 157, 70, 14);
		getContentPane().add(lblNewLabel_3);
		
		correoActual = new JTextField();
		correoActual.setColumns(10);
		correoActual.setEditable(false);
		correoActual.setBounds(343, 154, 120, 20);
		getContentPane().add(correoActual);
		
		lblNewLabel_4 = new JLabel("Contraseña:");
		lblNewLabel_4.setBounds(10, 186, 97, 17);
		getContentPane().add(lblNewLabel_4);
		
		passNuevo = new JTextField();
		passNuevo.setColumns(10);
		passNuevo.setBounds(179, 186, 123, 20);
		getContentPane().add(passNuevo);
		
		passActual = new JTextField();
		passActual.setColumns(10);
		passActual.setEditable(false);
		passActual.setBounds(343, 185, 120, 20);
		getContentPane().add(passActual);
		
		confirmPassNuevo = new JTextField();
		confirmPassNuevo.setColumns(10);
		confirmPassNuevo.setBounds(179, 215, 123, 20);
		getContentPane().add(confirmPassNuevo);
		
		lblNewLabel_5 = new JLabel("Confirmar contraseña: ");
		lblNewLabel_5.setBounds(10, 219, 163, 14);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Descripción:");
		lblNewLabel_6.setBounds(10, 256, 111, 14);
		getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("URL:");
		lblNewLabel_7.setBounds(10, 334, 111, 14);
		getContentPane().add(lblNewLabel_7);
		
		ulrNuevo = new JTextField();
		ulrNuevo.setColumns(10);
		ulrNuevo.setBounds(179, 332, 123, 20);
		getContentPane().add(ulrNuevo);
		
		urlActual = new JTextField();
		urlActual.setColumns(10);
		urlActual.setEditable(false);
		urlActual.setBounds(343, 332, 120, 20);
		getContentPane().add(urlActual);
		
		descripcionNuevo = new JTextArea();
		descripcionNuevo.setBounds(179, 247, 123, 69);
		getContentPane().add(descripcionNuevo);
		
		descripcionActual = new JTextArea(); 
		descripcionActual.setBounds(343, 247, 128, 69);
		descripcionActual.setEditable(false);
		getContentPane().add(descripcionActual);
		
		
		// SETEO LOS DATOS DEL USUARUO QUE YA ESTAN EN EL SISTEMA
		DTUsuario dtuser = (DTUsuario) icu.obtenerDatosUsuario(empresa.getNickname()); // obtengo los datos genericos
		nombreActual.setText(dtuser.getNombre());
		apellidoActual.setText(dtuser.getApellido());
		correoActual.setText(dtuser.getCorreo_electronico());
		passActual.setText(dtuser.getContraseña());
		
		DTEmpresa dtEmpr = (DTEmpresa)  dtuser; // obtengo los datos especificos
		urlActual.setText(dtEmpr.getUrl());
		descripcionActual.setText(dtEmpr.getUrl());

		
		
		 // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
		btnCancelar = new JButton("Cerrar");
		btnCancelar.setBounds(330, 358, 111, 25);
		btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        setVisible(false);
		    }
		});
		getContentPane().add(btnCancelar);
	
		JButton modificarBtn = new JButton("Modificar");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
       		String nombre = nombreNuevo.getText();
       		String apellido = apellidoNuevo.getText();
       		String correo = correoNuevo.getText();
       		String pass = passNuevo.getText();
       		String confirmPass = confirmPassNuevo.getText();
       		String descripcion = descripcionNuevo.getText();
       		String url = ulrNuevo.getText();

       		
       		if(nombre.isEmpty() || apellido.isEmpty() ||  correo.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() ||  descripcion.isEmpty() ) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "Todos los campos son obligatorios excepto la URL.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		}
       		else if (!pass.equals(confirmPass)) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "El campo contraseña y confirmar contraseña no son iguales.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		}
       		else if(!nombre.matches("[a-zA-Z]+$")) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "El nombre solo puede contener letras.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		}
       		else if(!apellido.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "El apellido solo puede contener letras.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		}
       		else if(!correo.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "No sigue el formato que debe tener correo, por favor ingrese un correo valido.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		} 
       		else if(!descripcion.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "La descripcion solo puede contener letras.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
       		}
       		
       		else {
           		if (!url.isEmpty() ) {
	               	icu.ingresarDatosEditadosEmpresaURL(empresa.getNickname(), nombre, apellido, correo, pass, url, descripcion);
           		}
           		else { 
	               	icu.ingresarDatosEditadosEmpresa(empresa.getNickname(), nombre, apellido, correo, pass, descripcion);
           		}
           		
           		JOptionPane.showMessageDialog(ModificarDatosDeUsuarioEmpresa.this, "Los datos se han modificado exitosamente", "Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
               	setVisible(false);
               	limpiarFormulario();
       		}
		}
		});
		modificarBtn.setBounds(74, 359, 111, 23);
		getContentPane().add(modificarBtn);
		
		lblNewLabel_8 = new JLabel("Modifcar");
		lblNewLabel_8.setBounds(214, 58, 88, 25);
		getContentPane().add(lblNewLabel_8);
		
		
		
}
    
    private void limpiarFormulario() {
    	nombreNuevo.setText("");
    	apellidoNuevo.setText("");
    	correoNuevo.setText("");
    	passNuevo.setText("");
    	confirmPassNuevo.setText("");
    	descripcionNuevo.setText("");
    	ulrNuevo.setText("");
    	
    	nombreActual.setText("");
        apellidoActual.setText("");
        correoActual.setText("");
        passActual.setText("");
        descripcionActual.setText("");
        urlActual.setText("");

        }
}