package presentacion;

import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.Interfaces.ICtrlUsuario;
import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ModificarDatosDeUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private ICtrlUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JLabel listaDeUsuarios;
    private JButton btnBuscar;
    private JTextField nick;
    
    private JTextField nombre;
    private JTextField apellido;
    private JTextField correoActual;
    private JTextField apellidoActual;
    private JTextField nombre_2;
    private JTextField apellido_2;
    private JTextField nombreActual;
    
    private JLabel nickLabel;
  
    private JLabel correoLabel;
    private JLabel nombreLabel;
    
    
    
  
    
    private JButton btnCerrar;
    private JTextArea adicional;

    /**
     * Create the frame.
     */
    public ModificarDatosDeUsuario(ICtrlUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc...
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(30, 30, 477, 505);

        // Absolute layout
        getContentPane().setLayout(null);

        // JLabael nick
        nickLabel = new JLabel("Ingrese nickname:");
        nickLabel.setBounds(38, 65, 125, 14);
        getContentPane().add(nickLabel);

        // JTextField nick 
        nick = new JTextField();
        nick.setBounds(164, 57, 189, 30);
        getContentPane().add(nick);
        
        nombre_2 = new JTextField();
        nombre_2.setEnabled(false);
        nombre_2.setBounds(164, 107, 285, 30);
        getContentPane().add(nombre_2);
        
        JLabel apellidoActualLabel = new JLabel("Apellido:");
        apellidoActualLabel.setBounds(38, 275, 65, 14);
        getContentPane().add(apellidoActualLabel);
        
        apellido_2 = new JTextField();
        apellido_2.setEnabled(false);
        apellido_2.setBounds(165, 147, 286, 30);
        getContentPane().add(apellido_2);

        // Un botón (JButton) con un evento asociado que permite buscar un usuario.
        // Dado que el código de registro tiene cierta complejidad, conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
        btnBuscar = new JButton("Buscar");
        btnBuscar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String nickBuscar = nick.getText(); // leo nick
            	HashSet<String> nicks = icu.listarNicknamesUsuarios(); // obtengo todos los nicks

        		
        		
            	if (nicks.contains(nickBuscar))  {
            		DTUsuario dtus = icu.obtenerDatosUsuario(nickBuscar); // obtengo los datos
            		nombreActual.setText(dtus.getNombre());
            		apellidoActual.setText(dtus.getApellido());
            		correoActual.setText(dtus.getCorreo_electronico());
            		
            		//apellido_2.setEnabled(true);
            		//nombre_2.setEnabled(true);
            		
            		String tipo;
            		if(dtus instanceof DTEmpresa) {
                    	tipo = "Empresa";
                    	DTEmpresa empresa = (DTEmpresa) dtus;
    	                adicional.append("Empresa: " + empresa.getNombreEmpresa()+ "\n"); 
    	                adicional.append("Descripcion: " + empresa.getDescripcion()+ "\n");
    	                adicional.append("URL:" + empresa.getUrl()+ "\n");
    	                adicional.append("\n");
                    } else {
                    	tipo="Postulante";
                    	DTPostulante postula = (DTPostulante) dtus;
                    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    	String formattedFecha = postula.getFecha_nac().format(formatter);
                    	
                    	adicional.append("Fecha de Nacimiento" + formattedFecha+ "\n");
    	                adicional.append("Nacionalidad" + postula.getNacionalidad()+ "\n");
    	                adicional.append("\n");
                    
                    }
            	}
            	else { // ver lo de nick
            		JOptionPane.showMessageDialog(nick, "...", "ERROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
            	}
            	
            }
       });

        
        
        btnBuscar.setBounds(366, 57, 85, 30);
        getContentPane().add(btnBuscar);

        // JLabel correo 
        correoLabel = new JLabel("Correo electrónico:");
        correoLabel.setBounds(38, 314, 118, 23);
        getContentPane().add(correoLabel);

        // JTextField apellidoActual
        apellidoActual = new JTextField();
        apellidoActual.setEditable(false);
        apellidoActual.setBounds(164, 267, 287, 30);
        getContentPane().add(apellidoActual);

        // JLabel nombre
        nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(38, 115, 65, 14);
        getContentPane().add(nombreLabel);
        
        
        
        // JTextField apellido 
        correoActual = new JTextField();
        correoActual.setEditable(false);
        correoActual.setBounds(164, 310, 287, 30);
        getContentPane().add(correoActual);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(347, 441, 89, 23);
        getContentPane().add(btnCerrar);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(38, 155, 65, 14);
        getContentPane().add(apellidoLabel);
        
        nombreActual = new JTextField();
        nombreActual.setEditable(false);
        nombreActual.setBounds(164, 226, 287, 30);
        getContentPane().add(nombreActual);
        
        listaDeUsuarios = new JLabel("Lista de usuarios:");
        listaDeUsuarios.setBounds(38, 23, 118, 15);
        getContentPane().add(listaDeUsuarios);
        HashSet<String> usernicks = icu.listarNicknamesUsuarios();
        for (String nickname : usernicks) {
           // comboBoxUsuarios.addItem(nickname);
        }
        
        
        
        JLabel infoActual = new JLabel("Información actual ");
        infoActual.setBounds(219, 209, 180, 14);
        getContentPane().add(infoActual);
        
        JLabel nombreActualLabel = new JLabel("Nombre:");
        nombreActualLabel.setBounds(38, 234, 65, 14);
        getContentPane().add(nombreActualLabel);
        
                
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nuevoNombre = nombre.getText();
        		String nuevoApellido = apellido.getText();
        		String nickBuscar = nick.getText();
        		icu.ingresarDatosEditados(nickBuscar, nuevoNombre, nuevoApellido);
        		limpiarFormulario();
        	}
        });
        btnAceptar.setBounds(67, 441, 89, 23);
        getContentPane().add(btnAceptar);
        
        JComboBox comboboxListar = new JComboBox();
        comboboxListar.setBounds(161, 19, 285, 22);
        getContentPane().add(comboboxListar);
        
        JLabel lblNewLabel = new JLabel("Información adicional");
        lblNewLabel.setBounds(38, 358, 111, 14);
        getContentPane().add(lblNewLabel);
        
        adicional = new JTextArea();
        adicional.setBounds(164, 351, 285, 79);
        getContentPane().add(adicional);
        
    }

    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
    	nick.setText("");
    	nombre.setText("");
    	apellido.setText("");
    	
    	nombreActual.setText("");
        apellidoActual.setText("");
        correoActual.setText("");
    }
}