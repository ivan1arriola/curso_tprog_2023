package main.java.presentacion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.interfaces.ICtrlUsuario;

import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AltaDeEmpresa extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private ICtrlUsuario icUsuario;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JLabel nombre1;
    private JLabel apellido;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JTextField textFieldNickname;
    private JLabel nickname;
    private JTextField textFieldDescripcion;
    private JTextField textFieldURL;
    private JLabel contrasenia;
    private JLabel descripcion; 
    private JLabel uRL;
    private JLabel correoElectronico;
    private JTextField textFieldCorreoElectronico;
    private JPasswordField passF;
    private JPasswordField pFR;
    private JLabel nombreEmpresa1;;

    /**
     * Create the frame.
     */
    public AltaDeEmpresa(ICtrlUsuario icu) {
        // Se inicializa con el controlador de usuarios
        icUsuario = icu;

        // Propiedades del JInternalFrame como dimensión,  posición dentro del frame, 
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Empresa");
        setBounds(10,  40,  434,  305);
        getContentPane().setLayout(null);
                                                                                
        nickname = new JLabel("Nickname:");
        nickname.setBounds(141,  12,  73,  13); 
        nickname.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(nickname);
        
        textFieldNickname = new JTextField(); 
        textFieldNickname.setBounds(232,  9, 180,  18);
        textFieldNickname.setColumns(10);
        getContentPane().add(textFieldNickname);

        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el nombre del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        nombre1 = new JLabel("Nombre:");
        nombre1.setBounds(51,  36,  163,  25);
        nombre1.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(nombre1);

        // Una campo de texto (JTextField) para ingresar el nombre del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(232,  39,  180,  20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
		// el apellido del usuario. El texto está alineado horizontalmente a la derecha para
		// que quede casi pegado al campo de texto.
		apellido = new JLabel("Apellido:");
		apellido.setBounds(51,  71,  163,  19);
		apellido.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(apellido);
		
		// Una campo de texto (JTextField) para ingresar el apellido del usuario. 
		// Por defecto es posible ingresar cualquier string.
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(232,  71,  180,  19);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
                                                                                                                
		correoElectronico = new JLabel("Correo electrónico:");
		correoElectronico.setBounds(78,  102,  136,  15);
		correoElectronico.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(correoElectronico);
		
		textFieldCorreoElectronico = new JTextField();
		textFieldCorreoElectronico.setBounds(232,  100,  180,  19);
		textFieldCorreoElectronico.setToolTipText("");
		textFieldCorreoElectronico.setColumns(10);
		getContentPane().add(textFieldCorreoElectronico);
		
		contrasenia = new JLabel("Contraseña:");
		contrasenia.setBounds(41,  129,  173,  17);
		contrasenia.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(contrasenia);
		
		descripcion = new JLabel("Descripción:");
		descripcion.setBounds(127,  180,  87,  15);
		descripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(descripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(232,  178,  180,  19);
		textFieldDescripcion.setColumns(10);
		getContentPane().add(textFieldDescripcion);
	    
	    uRL = new JLabel("URL:");
	    uRL.setBounds(182,  207,  32,  15);
	    uRL.setHorizontalAlignment(SwingConstants.RIGHT);
	    getContentPane().add(uRL);
	    
	    textFieldURL = new JTextField();
	    textFieldURL.setBounds(232,  205,  180,  19);
	    textFieldURL.setColumns(10);
	    getContentPane().add(textFieldURL);
		
        // Un botón (JButton) con un evento asociado que permite registrar el usuario.
	    // Dado que el código de registro tiene cierta complejidad,  conviene delegarlo
	    // a otro método en lugar de incluirlo directamente de el método actionPerformed 
	    btnAceptar = new JButton("Aceptar");
	    btnAceptar.setBounds(10,  236,  191,  25);
	    btnAceptar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	 try {
					cmdRegistroUsuarioActionPerformed(arg0);
				} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos
						| ExceptionUsuarioNickRepetido e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });
	    getContentPane().add(btnAceptar);
		
	    // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
	    // Dado que antes de cerrar se limpia el formulario,  se invoca un método reutilizable para ello. 
	    btnCancelar = new JButton("Cancelar");
	    btnCancelar.setBounds(232,  236,  180,  25);
	    btnCancelar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evento) {
	            limpiarFormulario();
	            setVisible(false);
	        }
	    });
	    getContentPane().add(btnCancelar);
	    
	    passF = new JPasswordField();
	    passF.setBounds(232,  128,  180,  18);
	    getContentPane().add(passF);
	    
	    pFR = new JPasswordField();
	    pFR.setBounds(232,  153,  180,  17);
	    getContentPane().add(pFR);
	    
	    nombreEmpresa1 = new JLabel("Confirmación de contraseña:");
	    nombreEmpresa1.setHorizontalAlignment(SwingConstants.RIGHT);
	    nombreEmpresa1.setBounds(0,  153,  214,  17);
	    getContentPane().add(nombreEmpresa1);
	    }

	    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
	        // TODO Auto-generated method stub
	
	        // Obtengo datos de los controles Swing
	    	String nicknameU = this.textFieldNickname.getText();
	        String nombreU = this.textFieldNombre.getText();
	        String apellidoU = this.textFieldApellido.getText();
	        String correoelectronicoU = this.textFieldCorreoElectronico.getText();
	        String descripcionU = this.textFieldDescripcion.getText();
	        String urlU = this.textFieldURL.getText();
	        String contraseña = this.passF.getText();
	        String recontraseña = this.pFR.getText();
	        
	        int resCheck = checkFormulario();
	        if (resCheck == 1) {
	        	try {
	        		icUsuario.altaEmpresa(nicknameU,  nombreU,  apellidoU,  correoelectronicoU,  contraseña,  descripcionU);
	                limpiarFormulario();
	                setVisible(false);
	        		JOptionPane.showMessageDialog(this,  "El usuario se ha creado con éxito.",  "Registrar Usuario",  JOptionPane.INFORMATION_MESSAGE);
	        	} catch (ExceptionUsuarioCorreoRepetido|ExceptionUsuarioNickYCorreoRepetidos|ExceptionUsuarioNickRepetido e) {
	        		JOptionPane.showMessageDialog(this,  e.getMessage(),  "ERROR - Alta de Empresa",  JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        else if (resCheck == 2) {
	        	try {
	                limpiarFormulario();
	                setVisible(false);
	        		icUsuario.altaEmpresaURL(nicknameU,  nombreU,  apellidoU,  correoelectronicoU,  contraseña,  descripcionU,  urlU);  
	        		JOptionPane.showMessageDialog(this,  "El usuario se ha creado con éxito.",  "Registrar Usuario",  JOptionPane.INFORMATION_MESSAGE);
	        		limpiarFormulario();
	        		setVisible(false);
            	} catch (ExceptionUsuarioNickRepetido evento) {
	        		JOptionPane.showMessageDialog(this,  "El nickname ya se encuentra en el sistema.",  "Alta de Postulante",  JOptionPane.INFORMATION_MESSAGE);

	        		// JOptionPane.showMessageDialog(this,  e.getMessage(),  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
	            } catch (ExceptionUsuarioCorreoRepetido evento1) {
	        		JOptionPane.showMessageDialog(this,  "El correo ya se encuentra en el sistema.",  "Alta de Postulante",  JOptionPane.INFORMATION_MESSAGE);

	            } catch (ExceptionUsuarioNickYCorreoRepetidos evento2) {
	        		JOptionPane.showMessageDialog(this,  "El nickname y el correo ya se encuentra en el sistema.",  "Alta de Postulante",  JOptionPane.INFORMATION_MESSAGE);

	            }        	
	        }
            // Limpio el internal frame antes de cerrar la ventana

	    }
	
	    // Permite validar la información introducida en los campos e indicar
	    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
	    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java,  
	    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
	    // en el campo de la cédula,  o mostrando un mensaje de error apenas el foco pasa a otro campo.
	    private int checkFormulario() {
	    	String nicknameU = this.textFieldNickname.getText();
	        String nombreU = this.textFieldNombre.getText();
	        String apellidoU = this.textFieldApellido.getText();
	        String correoelectronicoU = this.textFieldCorreoElectronico.getText();
	        String descripcionU = this.textFieldDescripcion.getText();
	        String urlU = this.textFieldURL.getText();
	        String contraseña = this.passF.getText();
	        String recontraseña = this.pFR.getText();
	        
	
	        if (nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || correoelectronicoU.isEmpty() || descripcionU.isEmpty()) {
	            JOptionPane.showMessageDialog(this,  "Existen campos distintos al URL que son vacíos.",  "ERROR - Alta de Empresa",  JOptionPane.ERROR_MESSAGE);
	            return 0;
	        }

	        if (!nombreU.matches("^[\\p{L} ]+$")) {
	        	JOptionPane.showMessageDialog(this,  "El nombre indicado se compone de carácteres que no son letras.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
	        	return 0;
	        }
	        
	        if (!apellidoU.matches("[\\p{L} ]+$")) {
	        	JOptionPane.showMessageDialog(this,  "El apellido indicado se compone de carácteres que no son letras.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
	        	return 0;
	        }
	        
	        if (!correoelectronicoU.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
	        	JOptionPane.showMessageDialog(this,  "El correo electrónico indicado no es válido.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
	        	return 0;
	        }
	        
	        if (!contraseña.equals(recontraseña)) {
	        	JOptionPane.showMessageDialog(this,  "La contraseña no coincide con su confirmación.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
	        	return 0;
	        }
	        
	        if (urlU.isEmpty()) {
	        	return 1;
	        }
	        else {
	        	return 2;
	        }
	    }
	
	    // Permite borrar el contenido de un formulario antes de cerrarlo.
	    // Recordar que las ventanas no se destruyen,  sino que simplemente 
	    // se ocultan,  por lo que conviene borrar la información para que 
	    // no aparezca al mostrarlas nuevamente.
	    private void limpiarFormulario() {
	    	textFieldNickname.setText("");
	    	textFieldNombre.setText("");
	        textFieldApellido.setText("");
	        textFieldCorreoElectronico.setText("");
	        passF.setText("");
	        pFR.setText("");
	        textFieldDescripcion.setText("");
	        textFieldURL.setText("");
	    }
}