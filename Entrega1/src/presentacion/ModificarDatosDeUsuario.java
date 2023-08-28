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
import javax.swing.JScrollPane;

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
    private JComboBox comboBoxUsuarios;
    
    private JLabel nickLabel;
  
    private JLabel correoLabel;
    private JLabel nombreLabel;
    
    
    
  
    
    private JButton btnCerrar;
    private JTextArea adicional;
    private JScrollPane scrollPane;

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
        setBounds(30, 30, 539, 553);
        

        // Absolute layout
        getContentPane().setLayout(null);

        // JLabael nick
        nickLabel = new JLabel("Ingrese nickname:");
        nickLabel.setBounds(37, 60, 162, 14);
        getContentPane().add(nickLabel);

        // JTextField nick 
        nick = new JTextField();
        nick.setBounds(217, 53, 189, 30);
        getContentPane().add(nick);
        
        nombre_2 = new JTextField();
        nombre_2.setEnabled(false);
        nombre_2.setEditable(false);
        nombre_2.setBounds(217, 118, 285, 30);
        getContentPane().add(nombre_2);
        
        JLabel apellidoActualLabel = new JLabel("Apellido:");
        apellidoActualLabel.setBounds(38, 275, 65, 14);
        getContentPane().add(apellidoActualLabel);
        
        apellido_2 = new JTextField();
        apellido_2.setEnabled(false);
        apellido_2.setEditable(false);
        apellido_2.setBounds(216, 160, 286, 30);
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
            		
            		apellido_2.setEnabled(true);
            		nombre_2.setEnabled(true);
            		nombre_2.setEditable(true);
            		apellido_2.setEditable(true);
            		
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
                    	
                    	adicional.append("Fecha de Nacimiento: " +  formattedFecha+ "\n");
    	                adicional.append("Nacionalidad: " + postula.getNacionalidad()+ "\n");
    	                adicional.append("\n");
                    
                    }
            	}
            	else { // ver lo de nick
            		JOptionPane.showMessageDialog(nick, "No ha indicado un usuario válido.", "ERROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
            	}
            	
            }
       });

        
        
        btnBuscar.setBounds(417, 52, 85, 30);
        getContentPane().add(btnBuscar);

        // JLabel correo 
        correoLabel = new JLabel("Correo electrónico:");
        correoLabel.setBounds(38, 314, 162, 23);
        getContentPane().add(correoLabel);

        // JTextField apellidoActual
        apellidoActual = new JTextField();
        apellidoActual.setEditable(false);
        apellidoActual.setBounds(215, 268, 287, 30);
        getContentPane().add(apellidoActual);

        // JLabel nombre
        nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(38, 125, 65, 14);
        getContentPane().add(nombreLabel);
        
        
        
        // JTextField apellido 
        correoActual = new JTextField();
        correoActual.setEditable(false);
        correoActual.setBounds(215, 311, 287, 30);
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
        btnCerrar.setBounds(362, 470, 89, 23);
        getContentPane().add(btnCerrar);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(38, 167, 65, 14);
        getContentPane().add(apellidoLabel);
        
        nombreActual = new JTextField();
        nombreActual.setEditable(false);
        nombreActual.setBounds(215, 226, 287, 30);
        getContentPane().add(nombreActual);
        
        listaDeUsuarios = new JLabel("Lista de usuarios:");
        listaDeUsuarios.setBounds(38, 23, 162, 15);
        getContentPane().add(listaDeUsuarios);
        
        JLabel infoActual = new JLabel("Información actual ");
        infoActual.setBounds(217, 202, 180, 14);
        getContentPane().add(infoActual);
        
        JLabel nombreActualLabel = new JLabel("Nombre:");
        nombreActualLabel.setBounds(38, 234, 65, 14);
        getContentPane().add(nombreActualLabel);
        
                
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nuevoNombre = nombre_2.getText();
        		String nuevoApellido = apellido_2.getText();
        		String nickBuscar = nick.getText();
        		if(nuevoNombre.isEmpty() || nuevoApellido.isEmpty()) {
        			JOptionPane.showMessageDialog(ModificarDatosDeUsuario.this, "No puede haber campos vacíos.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
        		}
        		else if(!nuevoNombre.matches("[a-zA-Z]+$")) {
        			JOptionPane.showMessageDialog(ModificarDatosDeUsuario.this, "El nombre solo puede contener letras.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
        		}
        		else if(!nuevoApellido.matches("[a-zA-Z]+$")){
        			JOptionPane.showMessageDialog(ModificarDatosDeUsuario.this, "El apellido solo puede contener letras.", "ERRROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
        		}
        		else {
            		HashSet<String> nicks = icu.listarNicknamesUsuarios();
            		
            		if (nicks.contains(nickBuscar))  {
                		icu.ingresarDatosEditados(nickBuscar, nuevoNombre, nuevoApellido);
                		JOptionPane.showMessageDialog(ModificarDatosDeUsuario.this, "Los datos se han modificado exitosamente", "Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
                		setVisible(false);
                		limpiarFormulario();
            		}
            		else {
            			JOptionPane.showMessageDialog(nick, "No ha indicado un usuario válido.", "ERROR - Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
            		}
        		}

        		

        	}
        });
        btnAceptar.setBounds(66, 470, 89, 23);
        getContentPane().add(btnAceptar);
        
        comboBoxUsuarios = new JComboBox();
        comboBoxUsuarios.setBounds(218, 19, 285, 22);
        getContentPane().add(comboBoxUsuarios);
        
        JLabel lblNewLabel = new JLabel("Información adicional");
        lblNewLabel.setBounds(38, 358, 162, 14);
        getContentPane().add(lblNewLabel);
        
        adicional = new JTextArea();
        adicional.setEditable(false);
        adicional.setEnabled(false);
        adicional.setBounds(217, 352, 285, 79);
        getContentPane().add(adicional);
        
        JLabel lblModificarDatos = new JLabel("Modificar datos");
        lblModificarDatos.setBounds(217, 95, 141, 15);
        getContentPane().add(lblModificarDatos);


        //((scrollPane = new JScrollPane(adicional);
        //scrollPane.setBounds(164, 427, 285, -13);
        // getContentPane().add(scrollPane);
        
    }

    public void actualizar() {
        comboBoxUsuarios.removeAllItems(); // Limpiar los elementos actuales
        
        
        HashSet<String> nicks = controlUsr.listarNicknamesUsuarios();
        
        comboBoxUsuarios.addItem(" ");
        for (String nickname : nicks) {
            comboBoxUsuarios.addItem(nickname);
            
        }
        
        revalidate(); // Actualizar la interfaz gráfica
    }
    
    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
    	nick.setText("");
    	nombre_2.setText("");
    	apellido_2.setText("");
    	
    	nombreActual.setText("");
        apellidoActual.setText("");
        correoActual.setText("");
        adicional.setText("");
    }
}