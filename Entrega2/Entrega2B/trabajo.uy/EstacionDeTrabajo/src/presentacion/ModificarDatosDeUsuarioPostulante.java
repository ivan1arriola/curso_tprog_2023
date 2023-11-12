package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.datatypes.DTPostulante;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import javax.swing.JList;
//import javax.swing.JScrollBar;
//import javax.swing.JComboBox;
//import javax.swing.JTextArea;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import javax.swing.JScrollPane;
//import javax.swing.JFormattedTextField;
//import javax.swing.text.MaskFormatter;



@SuppressWarnings("serial")
public class ModificarDatosDeUsuarioPostulante extends JInternalFrame {
	// Controlador de usuarios que se utilizará para las acciones del JFrame
	    //private ICtrlUsuario icUsuario;
	    private JButton btnCancelar;
	    private JLabel lblIngreseNombre;
	    
//	    private AltaDePostulante altaDePostulanteInternalFrame;
//	    private AltaDeEmpresa altaDeEmpresaInternalFrame;
	    private JTextField nombreNuevo;
	    private JTextField nombreActual;
	    private JTextField apellidoNuevo;
	    private JTextField apellidoActual;
	    private JTextField correoNuevo;
	    private JLabel lblNewLabel3;
	    private JTextField correoActual;
	    private JLabel lblNewLabel4;
	    private JPasswordField passNuevo;
	    private JTextField passActual;
	    private JPasswordField confirmPassNuevo;
	    private JLabel lblNewLabel5;
	    private JLabel lblNewLabel6;
	    private JLabel lblNewLabel7;
	    private JTextField nacionalidadNuevo;
	    private JTextField nacionalidadActual;
	    private JTextField fechaNuevo;
	    private JTextField fechaActual;
	    private JLabel lblNewLabel8;
	    
    public ModificarDatosDeUsuarioPostulante(ICtrlUsuario icUsuario,  DTPostulante postulante) {
    	// Se inicializa con el controlador de usuarios
        // Fabrica fabrica = Fabrica.getInstance();
        // icUsuario = fabrica.getICtrlUsuario();


        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(10,  40,  533,  394);
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Modifique los datos del postulante: " + postulante.getNickname());
        lblIngreseNombre.setBounds(30,  11,  418,  34);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
                
        
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10,  88,  81,  25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Datos Actuales");
		lblNewLabel1.setBounds(378,  54,  123,  25);
		getContentPane().add(lblNewLabel1);
		
		nombreNuevo = new JTextField();
		nombreNuevo.setBounds(222,  91,  123,  20);
		getContentPane().add(nombreNuevo);
		nombreNuevo.setColumns(10);
		
		nombreActual = new JTextField();
		nombreActual.setBounds(381,  91,  120,  20);
		nombreActual.setEditable(false);
		getContentPane().add(nombreActual);
		nombreActual.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("Apellido: ");
		lblNewLabel2.setBounds(10,  124,  46,  14);
		getContentPane().add(lblNewLabel2);
		
		apellidoNuevo = new JTextField();
		apellidoNuevo.setColumns(10);
		apellidoNuevo.setBounds(222,  122,  123,  20);
		getContentPane().add(apellidoNuevo);
		
		apellidoActual = new JTextField();
		apellidoActual.setColumns(10);
		apellidoActual.setBounds(381,  122,  120,  20);
		apellidoActual.setEditable(false);
		getContentPane().add(apellidoActual);
		
		correoNuevo = new JTextField();
		correoNuevo.setColumns(10);
		correoNuevo.setBounds(222,  155,  123,  20);
		getContentPane().add(correoNuevo);
		
		lblNewLabel3 = new JLabel("E-mail: ");
		lblNewLabel3.setBounds(10,  157,  70,  14);
		getContentPane().add(lblNewLabel3);
		
		correoActual = new JTextField();
		correoActual.setColumns(10);
		correoActual.setEditable(false);
		correoActual.setBounds(381,  153,  120,  20);
		getContentPane().add(correoActual);
		
		lblNewLabel4 = new JLabel("Contraseña:");
		lblNewLabel4.setBounds(10,  186,  161,  14);
		getContentPane().add(lblNewLabel4);
		
		passNuevo = new JPasswordField();
		passNuevo.setColumns(10);
		passNuevo.setBounds(222,  186,  123,  20);
		getContentPane().add(passNuevo);
		
		passActual = new JTextField();
		passActual.setColumns(10);
		passActual.setEditable(false);
		passActual.setBounds(381,  184,  120,  20);
		getContentPane().add(passActual);
		
		confirmPassNuevo = new JPasswordField();
		confirmPassNuevo.setColumns(10);
		confirmPassNuevo.setBounds(222,  215,  123,  20);
		getContentPane().add(confirmPassNuevo);
		
		lblNewLabel5 = new JLabel("Confirmar contraseña: ");
		lblNewLabel5.setBounds(10,  219,  175,  14);
		getContentPane().add(lblNewLabel5);
		
		lblNewLabel6 = new JLabel("Fecha de nacimiento: ");
		lblNewLabel6.setBounds(10,  256,  175,  14);
		getContentPane().add(lblNewLabel6);
		
		lblNewLabel7 = new JLabel("Nacionalidad:");
		lblNewLabel7.setBounds(10,  285,  111,  14);
		getContentPane().add(lblNewLabel7);
		
		nacionalidadNuevo = new JTextField();
		nacionalidadNuevo.setColumns(10);
		nacionalidadNuevo.setBounds(222,  283,  123,  20);
		getContentPane().add(nacionalidadNuevo);
		
		nacionalidadActual = new JTextField();
		nacionalidadActual.setColumns(10);
		nacionalidadActual.setEditable(false);
		nacionalidadActual.setBounds(381,  283,  120,  20);
		getContentPane().add(nacionalidadActual);
		
		fechaNuevo = new JTextField();
		fechaNuevo.setColumns(10);
		fechaNuevo.setBounds(222,  250,  123,  20);
		getContentPane().add(fechaNuevo);
		
		fechaActual = new JTextField();
		fechaActual.setColumns(10);
		fechaActual.setEditable(false);
		fechaActual.setBounds(381,  254,  120,  20);
		getContentPane().add(fechaActual);
		
		
		// SETEO LOS DATOS DEL USUARUO QUE YA ESTAN EN EL SISTEMA 
		DTUsuario dtuser = (DTUsuario) icUsuario.obtenerDatosUsuario(postulante.getNickname()); // obtengo los datos
		nombreActual.setText(dtuser.getNombre());
		apellidoActual.setText(dtuser.getApellido());
		correoActual.setText(dtuser.getcorreoElectronico());
		passActual.setText(dtuser.getcontrasenia());
		
		DTPostulante dtpost = (DTPostulante) dtuser;
		// Formatear la fecha de nacimiento del usuario y establecerla en fechaActual
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String fechaNacimientoFormateada = dtpost.getFechaNac().format(dateFormatter);
	    fechaActual.setText(fechaNacimientoFormateada);
		
		
		nacionalidadActual.setText(dtpost.getNacionalidad());
		
		
		 // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario,  se invoca un método reutilizable para ello. 
		btnCancelar = new JButton("Cerrar");
		btnCancelar.setBounds(390,  315,  111,  25);
		btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evento) {
		        setVisible(false);
		    }
		});
		getContentPane().add(btnCancelar);
	
		JButton modificarBtn = new JButton("Modificar");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
       		String nombre = nombreNuevo.getText();
       		String apellido = apellidoNuevo.getText();
       		String correo = correoNuevo.getText();
       		String pass = passNuevo.getText();
       		String confirmPass = confirmPassNuevo.getText();
       		String fecha = fechaNuevo.getText();
       		String nac = nacionalidadNuevo.getText();
       		

       		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       		
       		try {
                LocalDate parsearFecha = LocalDate.parse(fecha,  dateFormatter); // valida la fecha
           
       		
       		if (nombre.isEmpty() || apellido.isEmpty() ||  correo.isEmpty() || pass.isEmpty() || confirmPass.isEmpty() ||  fecha.isEmpty() || nac.isEmpty()) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "No puede haber campos vacíos.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		}
       		else if (!pass.equals(confirmPass)) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "El campo contraseña y confirmar contraseña no son iguales.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		}
       		else if (!nombre.matches("[a-zA-Z]+$")) {
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "El nombre solo puede contener letras.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		}
       		else if (!apellido.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "El apellido solo puede contener letras.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		}
       		else if (!correo.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "No sigue el formato que debe tener correo,  por favor ingrese un correo valido.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		} 
       		else if (!nac.matches("[a-zA-Z]+$")){
       			JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "El nacionalidad solo puede contener letras.",  "ERRROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
       		}
       		
       		else {
       		    icUsuario.ingresarDatosEditadosPostulante(postulante.getNickname(),  nombre,  apellido,  correo,  pass,  parsearFecha,  nac);
               	JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "Los datos se han modificado exitosamente",  "Modificar Datos de Usuario",  JOptionPane.INFORMATION_MESSAGE);
               	setVisible(false);
               	limpiarFormulario();

       		}
       	 } catch (DateTimeParseException ex) {
             JOptionPane.showMessageDialog(ModificarDatosDeUsuarioPostulante.this,  "La fecha ingresada no es válida.",  "ERROR - Modificar Datos de Usuario",  JOptionPane.ERROR_MESSAGE);
         }
		}
		});
		modificarBtn.setBounds(74,  324,  111,  23);
		getContentPane().add(modificarBtn);
		
		lblNewLabel8 = new JLabel("Modifcar");
		lblNewLabel8.setBounds(247,  57,  88,  25);
		getContentPane().add(lblNewLabel8);
		
}
    
    private void limpiarFormulario() {
    	nombreNuevo.setText("");
    	apellidoNuevo.setText("");
    	correoNuevo.setText("");
    	passNuevo.setText("");
    	confirmPassNuevo.setText("");
    	fechaNuevo.setText("");
    	nacionalidadNuevo.setText("");
    	
    	nombreActual.setText("");
        apellidoActual.setText("");
        correoActual.setText("");
        passActual.setText("");
        fechaActual.setText("");
        nacionalidadActual.setText("");

        }
}