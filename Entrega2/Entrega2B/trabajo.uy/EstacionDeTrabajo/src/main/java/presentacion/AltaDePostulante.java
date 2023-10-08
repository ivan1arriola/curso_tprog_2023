package main.java.presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.logica.interfaces.ICtrlUsuario;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class AltaDePostulante extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private ICtrlUsuario ICU;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfNacionalidad;
    private JLabel Nombre;
    private JLabel Apellido;
    private JLabel Nacionalidad;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JTextField tfNickname;
    private JLabel Nickname;
    private JTextField tfMes;
    private JTextField tfDia;
    private JLabel lblIngreseCi_1;
    private JLabel año;
    private JLabel mes;
    private JLabel dia; 
    private JTextField tfAnio;
    private JLabel mail;
    private JTextField tfMail;
    private JPasswordField passF;
    private JPasswordField pFR;

    /**
     * Create the frame.
     */
    public AltaDePostulante(ICtrlUsuario icu) {
        // Se inicializa con el controlador de usuarios
        ICU = icu;

        // Propiedades del JInternalFrame como dimensión,  posición dentro del frame, 
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Postulante");
        setBounds(10,  40,  461,  393);
        getContentPane().setLayout(null);
        
        Nickname = new JLabel("Nickname:");
        Nickname.setBounds(162,  12,  73,  15);
        Nickname.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nickname);
        
        tfNickname = new JTextField();
        tfNickname.setBounds(242,  10,  201,  19);
        tfNickname.setColumns(10);
        getContentPane().add(tfNickname);
                                                                        
        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el nombre del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        Nombre = new JLabel("Nombre:");
        Nombre.setBounds(99,  41,  136,  25);
        Nombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombre);

        // Una campo de texto (JTextField) para ingresar el nombre del usuario. 
        // Por defecto es posible ingresar cualquier string.
        tfNombre = new JTextField();
        tfNombre.setBounds(242,  41,  201,  25);
        getContentPane().add(tfNombre);
        tfNombre.setColumns(10);
                                                                
        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el apellido del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        Apellido = new JLabel("Apellido:");
        Apellido.setBounds(99,  76,  136,  19);
        Apellido.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Apellido);
                                                        
        // Una campo de texto (JTextField) para ingresar el apellido del usuario. 
        // Por defecto es posible ingresar cualquier string.
        tfApellido = new JTextField();
        tfApellido.setBounds(242,  76,  201,  19);
        getContentPane().add(tfApellido);
        tfApellido.setColumns(10);
                                                                                                                                                                                
        mail = new JLabel("Correo electrónico:");
        mail.setBounds(99,  107,  136,  15);
        mail.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(mail);
        
        tfMail = new JTextField();
        tfMail.setBounds(242,  107,  201,  19);
        tfMail.setToolTipText("");
        tfMail.setColumns(10);
        getContentPane().add(tfMail);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        Nacionalidad = new JLabel("Nacionalidad:");
        Nacionalidad.setBounds(99,  179,  136,  19);
        Nacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nacionalidad);

        // Una campo de texto (JTextField) para ingresar la cédula del usuario. 
        // Por defecto es posible ingresar cualquier string.
        // Al campo se le incluye un Tooltip que,  al pasar el mouse por encima,  despliega un mensaje.
        tfNacionalidad = new JTextField();
        tfNacionalidad.setBounds(242,  179,  201,  19);
        tfNacionalidad.setToolTipText("");
        tfNacionalidad.setColumns(10);
        getContentPane().add(tfNacionalidad);

		lblIngreseCi_1 = new JLabel("Fecha de nacimiento");
		lblIngreseCi_1.setBounds(158,  210,  146,  15);
		lblIngreseCi_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblIngreseCi_1);
		
		año = new JLabel("Año:");
		año.setBounds(203,  235,  32,  15);
		año.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(año);
		
		tfAnio = new JTextField();
		tfAnio.setBounds(242,  233,  201,  19);
		tfAnio.setColumns(10);
		getContentPane().add(tfAnio);
		
		mes = new JLabel("Mes:");
		mes.setBounds(203,  266,  34,  15);
		mes.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(mes);
		
		tfMes = new JTextField();
		tfMes.setBounds(242,  264,  201,  19);
		tfMes.setColumns(10);
		getContentPane().add(tfMes);
		
		dia = new JLabel("Dia:");
		dia.setBounds(203,  297,  28,  15);
		dia.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(dia);
		
		tfDia = new JTextField();
		tfDia.setBounds(242,  295,  201,  19);
		tfDia.setColumns(10);
		getContentPane().add(tfDia);
        
        // Un botón (JButton) con un evento asociado que permite registrar el usuario.
        // Dado que el código de registro tiene cierta complejidad,  conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(12,  324,  223,  25);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        getContentPane().add(btnAceptar);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario,  se invoca un método reutilizable para ello. 
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(244,  324,  195,  25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        
	    getContentPane().add(btnCancelar);
	    
	    JLabel Contraseña = new JLabel("Contraseña:");
	    Contraseña.setHorizontalAlignment(SwingConstants.RIGHT);
	    Contraseña.setBounds(99,  134,  136,  19);
	    getContentPane().add(Contraseña);
	    
	    JLabel ReContraseña = new JLabel("Confirmación de contraseña:");
	    ReContraseña.setHorizontalAlignment(SwingConstants.RIGHT);
	    ReContraseña.setBounds(10,  158,  225,  19);
	    getContentPane().add(ReContraseña);
	    
	    passF = new JPasswordField();
	    passF.setBounds(242,  134,  201,  19);
	    getContentPane().add(passF);
	    
	    pFR = new JPasswordField();
	    pFR.setBounds(242,  158,  201,  19);
	    getContentPane().add(pFR);
    }

    // Este método es invocado al querer registrar un usuario,  funcionalidad
    // provista por la operación del sistem registrarUsuario().
    // Previamente se hace una verificación de los campos,  particularmente que no sean vacíos
    // y que la cédula sea un número. 
    // Tanto en caso de que haya un error (de verificación o de registro) o no,  se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
    	String nicknameU = tfNickname.getText();
        String nombreU = tfNombre.getText();
        String apellidoU = tfApellido.getText();
        String correoU = tfMail.getText();
        String anioU = tfAnio.getText();
        String mesU = tfMes.getText();
        String diaU = tfDia.getText();
        String nacionalidadU = this.tfNacionalidad.getText();
        String contraseña = this.passF.getText();
        String recontraseña = this.pFR.getText();

        if (checkFormulario()) {
            	LocalDate fechaU = LocalDate.of(Integer.parseInt(anioU),  Integer.parseInt(mesU),  Integer.parseInt(diaU));
	        	
            	try {
	        		boolean exito = ICU.altaPostulante(nicknameU,  contraseña,  nombreU,  apellidoU,  correoU,  fechaU,  nacionalidadU);
	        		JOptionPane.showMessageDialog(this,  "El usuario se ha creado con éxito.",  "Alta de Postulante",  JOptionPane.INFORMATION_MESSAGE);
	        	} catch (ExceptionUsuarioNickRepetido|ExceptionUsuarioNickYCorreoRepetidos|ExceptionUsuarioCorreoRepetido e) {
	        		JOptionPane.showMessageDialog(this,  e.getMessage(),  "ERROR - Alta de Empresa",  JOptionPane.ERROR_MESSAGE);
	            }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }

    // Permite validar la información introducida en los campos e indicar
    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java,  
    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
    // en el campo de la cédula,  o mostrando un mensaje de error apenas el foco pasa a otro campo.
    private boolean checkFormulario() {
    	String nicknameU = this.tfNickname.getText();
        String nombreU = this.tfNombre.getText();
        String apellidoU = this.tfApellido.getText();
        String correoU = this.tfMail.getText();
        String anioU = this.tfAnio.getText();
        String mesU = this.tfMes.getText();
        String diaU = this.tfDia.getText();
        String nacionalidadU = this.tfNacionalidad.getText();
        String contraseña = this.passF.getText();
        String recontraseña = this.pFR.getText();

        if (nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || correoU.isEmpty() || anioU.isEmpty() || mesU.isEmpty() || diaU.isEmpty() || nacionalidadU.isEmpty()) {
            JOptionPane.showMessageDialog(this,  "No puede haber campos vacíos.",  "ERRROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!nombreU.matches("[\\p{L}]+$")) {
        	JOptionPane.showMessageDialog(this,  "El nombre indicado se compone de carácteres que no son letras.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        if (!apellidoU.matches("[a-zA-Z]+$")) {
        	JOptionPane.showMessageDialog(this,  "El apellido indicado se compone de carácteres que no son letras.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        if (!correoU.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2, }$")) {
        	JOptionPane.showMessageDialog(this,  "El correo electrónico indicado no es válido.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        if (!contraseña.equals(recontraseña)) {
        	JOptionPane.showMessageDialog(this,  "La contraseña no coincide con su confirmación.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        if (!nacionalidadU.matches("[a-zA-Z]+$")) {
        	JOptionPane.showMessageDialog(this,  "La nacionalidad indicada se compone de carácteres que no son letras.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        try {
            Integer.parseInt(anioU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,  "El año debe ser un numero.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Integer.parseInt(mesU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,  "El mes debe ser un numero.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (Integer.parseInt(mesU) < 1 || Integer.parseInt(mesU) > 12) {
            JOptionPane.showMessageDialog(this,  "El mes debe ser un numero entre 1 y 12.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Integer.parseInt(diaU);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,  "El dia debe ser un numero.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            LocalDate fecha = LocalDate.of(Integer.parseInt(anioU),  Integer.parseInt(mesU),  Integer.parseInt(diaU));
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(this,  "El día no es válido para el mes y año.",  "ERROR - Alta de Postulante",  JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen,  sino que simplemente 
    // se ocultan,  por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
    	tfNickname.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        tfMail.setText("");
        passF.setText("");
        pFR.setText("");
        tfAnio.setText("");
        tfMes.setText("");
        tfDia.setText("");
        tfNacionalidad.setText("");
    }
}