package main.java.presentacion;

import javax.swing.*;

import main.java.logica.Fabrica;
import main.java.logica.Interfaces.ICtrlUsuario;


import java.awt.GridBagLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class AltaDeUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
	private JInternalFrame ADU;
    private ICtrlUsuario icu;
    private JButton btnCancelar;
    private JButton btnEmpresa;
    private JButton btnPostulante;
    private JLabel lblIngreseNombre;
    private AltaDePostulante AltaDePostulanteInternalFrame;
    private AltaDeEmpresa AltaDeEmpresaInternalFrame;
    
    /**
     * Create the frame.
     */
    public AltaDeUsuario(JFrame gu, ICtrlUsuario icu) {
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
        setTitle("Alta de Usuario");
        setBounds(10, 40, 408, 186);
                                                                
        btnEmpresa = new JButton("Empresa");
        btnEmpresa.setBounds(26, 67, 125, 25);
        btnEmpresa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		AltaDeEmpresaInternalFrame.setVisible(true);
        	}
        });
        getContentPane().setLayout(null);
        
        lblIngreseNombre = new JLabel("Seleccione si es un postulante o una empresa:");
        lblIngreseNombre.setBounds(26, 12, 337, 56);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);
        getContentPane().add(btnEmpresa);
        
        btnPostulante = new JButton("Postulante");
        btnPostulante.setBounds(274, 67, 112, 25);
        btnPostulante.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		AltaDePostulanteInternalFrame.setVisible(true);
        	}
        });
        getContentPane().add(btnPostulante);
                
                        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
                        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
	btnCancelar = new JButton("Cerrar");
	btnCancelar.setBounds(90, 116, 207, 25);
	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        setVisible(false);
	    }
	});
	getContentPane().add(btnCancelar);
}
}
