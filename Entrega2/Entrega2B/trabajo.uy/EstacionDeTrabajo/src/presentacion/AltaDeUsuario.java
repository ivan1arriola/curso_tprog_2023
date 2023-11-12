package presentacion;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logica.Fabrica;
import logica.interfaces.ICtrlUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class AltaDeUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
	
    private ICtrlUsuario icUsuario;
    private JButton btnCancelar;
    private JButton btnEmpresa;
    private JButton btnPostulante;
    private JLabel lblIngreseNombre;
    private AltaDePostulante altaDePostulanteInternalFrame;
    private AltaDeEmpresa altaDeEmpresaInternalFrame;
    
    /**
     * Create the frame.
     */
    public AltaDeUsuario(JFrame gui,  ICtrlUsuario icUsuario) {
        // Se inicializa con el controlador de usuarios
        Fabrica fabrica = Fabrica.getInstance();
        icUsuario = fabrica.getICtrlUsuario();

        altaDePostulanteInternalFrame = new AltaDePostulante(icUsuario);
        // altaDePostulanteInternalFrame.setSize(386,  312);
        altaDePostulanteInternalFrame.setLocation(5,  0);
        altaDePostulanteInternalFrame.setVisible(false);
        // getContentPane().setLayout(null);
        gui.getContentPane().add(altaDePostulanteInternalFrame);
        
        altaDeEmpresaInternalFrame = new AltaDeEmpresa(icUsuario);
        // altaDeEmpresaInternalFrame.setSize(360,  168);
        altaDeEmpresaInternalFrame.setLocation(38,  63);
        altaDeEmpresaInternalFrame.setVisible(false);
        gui.getContentPane().add(altaDeEmpresaInternalFrame);
        
        // Propiedades del JInternalFrame como dimensión,  posición dentro del frame, 
        // etc.

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Usuario");
        setBounds(10,  40,  408,  186);
                                                                
        btnEmpresa = new JButton("Empresa");
        btnEmpresa.setBounds(26,  67,  125,  25);
        btnEmpresa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		setVisible(false);
        		altaDeEmpresaInternalFrame.setVisible(true);
        	}
        });
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Seleccione si es un postulante o una empresa:");
        lblIngreseNombre.setBounds(26,  12,  337,  56);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
        getContentPane().add(btnEmpresa);
        
        btnPostulante = new JButton("Postulante");
        btnPostulante.setBounds(274,  67,  112,  25);
        btnPostulante.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		setVisible(false);
        		altaDePostulanteInternalFrame.setVisible(true);
        	}
        });
        getContentPane().add(btnPostulante);
                
                        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
                        // Dado que antes de cerrar se limpia el formulario,  se invoca un método reutilizable para ello. 
	btnCancelar = new JButton("Cerrar");
	btnCancelar.setBounds(90,  116,  207,  25);
	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evento) {
	        setVisible(false);
	    }
	});
	getContentPane().add(btnCancelar);
}
}
