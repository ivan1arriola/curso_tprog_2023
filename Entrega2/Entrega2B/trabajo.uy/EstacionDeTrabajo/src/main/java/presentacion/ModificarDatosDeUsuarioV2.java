package main.java.presentacion;

import javax.swing.JInternalFrame;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlUsuario;
import main.java.logica.Fabrica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
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


@SuppressWarnings("serial")
public class ModificarDatosDeUsuarioV2 extends JInternalFrame {
	// Controlador de usuarios que se utilizará para las acciones del JFrame
	    private ICtrlUsuario icu; 
	    private JButton btnCancelar;
	    private JLabel lblIngreseNombre;
	    private JComboBox<String> listarUsuarios;
	    private ModificarDatosDeUsuarioEmpresa MDUE;
	    private ModificarDatosDeUsuarioPostulante MDUP;
	    
	

    public ModificarDatosDeUsuarioV2(JFrame gui,  ICtrlUsuario icu) {
    	// Se inicializa con el controlador de usuarios
        // Fabrica fabrica = Fabrica.getInstance();
        // icu = fabrica.getICtrlUsuario();
    	
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(10,  40,  457,  187);
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Seleccione un usuario al cual desee modificarle los datos");
        lblIngreseNombre.setBounds(12,  11,  417,  34);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
                
        
	
		listarUsuarios = new JComboBox<>();
		listarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
				try {            		
	                    Set<String> nicks =  icu.listarNicknamesUsuarios();
	                    
                } catch (IllegalArgumentException ex) {
                    	System.err.println("Error al obtener los usuarios");
                }
				
			}
		});
		listarUsuarios.setBounds(63,  56,  325,  22);
		getContentPane().add(listarUsuarios);
		
		 // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario,  se invoca un método reutilizable para ello. 
		btnCancelar = new JButton("Cerrar");
		btnCancelar.setBounds(277,  109,  111,  25);
		btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evento) {
		        setVisible(false);
		    }
		});
		getContentPane().add(btnCancelar);
	
		JButton modificarBtn = new JButton("Ir a modificar");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				
                String selectedUsuario = (String) listarUsuarios.getSelectedItem(); // agarro el usuario
				DTUsuario dtus = icu.obtenerDatosUsuario(selectedUsuario); // obtengo los datos
				
				// El combobox no esta vacio y el usuario es un POSTULANTE
				if (listarUsuarios.getSelectedIndex() != -1 && listarUsuarios.getSelectedIndex() != 0  && !(dtus instanceof DTEmpresa)) { 
					DTPostulante dtpostu = (DTPostulante) dtus;
					MDUP = new ModificarDatosDeUsuarioPostulante(icu,  dtpostu);
					gui.getContentPane().add(MDUP);
					setVisible(false);
					MDUP.setVisible(true);
	                // modificarUser.toFront();
				} 
				
				// El combobox no esta vacio y el usuario es una EMPRESA	
				else if (listarUsuarios.getSelectedIndex() != -1 && listarUsuarios.getSelectedIndex() != 0  && (dtus instanceof DTEmpresa)) {
					DTEmpresa dtempre = (DTEmpresa) dtus;
					MDUE = new ModificarDatosDeUsuarioEmpresa(icu,  dtempre);
					gui.getContentPane().add(MDUE);
					setVisible(false);
					MDUE.setVisible(true);
	                //modificarUser.toFront();
				}
			}
		});
		modificarBtn.setBounds(63,  110,  146,  23);
		getContentPane().add(modificarBtn);
	
		
}
    
    
    public void actualizar() {
    	Fabrica fabrica = Fabrica.getInstance();
    	icu = fabrica.getICtrlUsuario();
    	
    	listarUsuarios.removeAllItems(); 
        
        //comboBoxOfertas.removeAllItems(); 
        Set<String> usuario = icu.listarNicknamesUsuarios();
        List<String> usuariosOrdenados = new ArrayList<>(usuario);
        Collections.sort(usuariosOrdenados,  String.CASE_INSENSITIVE_ORDER);
        listarUsuarios.addItem(" ");
        for (String nickname : usuariosOrdenados) {
        	listarUsuarios.addItem(nickname);
        }

        revalidate(); // Actualizar la interfaz gráfica
    }
		
}