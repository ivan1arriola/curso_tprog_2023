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

/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class ModificarDatosDeUsuarioV2 extends JInternalFrame {
	// Controlador de usuarios que se utilizará para las acciones del JFrame
		private JInternalFrame ADU;
	    private ICtrlUsuario icu;
	    private JButton btnCancelar;
	    private JLabel lblIngreseNombre;
	    private JComboBox<String> listarUsuarios;
	    
	    private AltaDePostulante AltaDePostulanteInternalFrame;
	    private AltaDeEmpresa AltaDeEmpresaInternalFrame;
	    
	    
	    
	    /**
	     * Create the frame.
	     */
    public ModificarDatosDeUsuarioV2(ICtrlUsuario icu) {
    	// Se inicializa con el controlador de usuarios
        Fabrica fabrica = Fabrica.getInstance();
        icu = fabrica.getICtrlUsuario();

        AltaDePostulanteInternalFrame = new AltaDePostulante(icu);
        // AltaDePostulanteInternalFrame.setSize(386, 312);
        AltaDePostulanteInternalFrame.setLocation(5, 0);
        AltaDePostulanteInternalFrame.setVisible(false);
        // getContentPane().setLayout(null);
        gu.getContentPane().add(AltaDePostulanteInternalFrame);
        
        AltaDeEmpresaInternalFrame = new AltaDeEmpresa(icu);
        // AltaDeEmpresaInternalFrame.setSize(360, 168);
        AltaDeEmpresaInternalFrame.setLocation(38, 63);
        AltaDeEmpresaInternalFrame.setVisible(false);
        gu.getContentPane().add(AltaDeEmpresaInternalFrame);
        
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(10, 40, 408, 186);
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Seleccione un usuario al cual desee modificarle los datos");
        lblIngreseNombre.setBounds(53, 11, 274, 34);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
                
         // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
         // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
		btnCancelar = new JButton("Cerrar");
		btnCancelar.setBounds(220, 120, 111, 25);
		btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        setVisible(false);
		    }
		});
		getContentPane().add(btnCancelar);
	
		JButton modificarBtn = new JButton("Modificar");
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		modificarBtn.setBounds(63, 121, 111, 23);
		getContentPane().add(modificarBtn);
	
		listarUsuarios = new JComboBox<>();
		listarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
            		
	                    HashSet<String> nicks = icu.listarNicknamesUsuarios();
	
	                    String selectedUsuario = (String) listarUsuarios.getSelectedItem();
	                    
	                    if (listarUsuarios.getSelectedIndex() != -1 && listarUsuarios.getSelectedIndex() != 0 ) { 
	                    	
	    	                DTUsuario dtusr = icu.obtenerDatosUsuario(selectedUsuario);
	    	                String tipo;
	    	                if(dtusr instanceof DTEmpresa) {
	    	                	tipo = "Empresa";
	    	                }
	    	                else {
	    	                	tipo = "Postulante";
	    	                }
	                    } 
                    } catch (Exception ex) {
                    System.err.println("Error al obtener los usuarios");
                }
				
			}
		});
		listarUsuarios.setBounds(63, 56, 264, 22);
		getContentPane().add(listarUsuarios);
		
}
    public void actualizar() {
    	listarUsuarios.removeAllItems(); 
        
        //comboBoxOfertas.removeAllItems(); 
        HashSet<String> usuario = icu.listarEmpresas();
        List<String> empresasSorted = new ArrayList<>(empresas);
        Collections.sort(empresasSorted, String.CASE_INSENSITIVE_ORDER);
        listarUsuarios.addItem(" ");
        for (String nickname : empresasSorted) {
        	
        	listarUsuarios.addItem(nickname);

        }

        revalidate(); // Actualizar la interfaz gráfica
    }
		
}