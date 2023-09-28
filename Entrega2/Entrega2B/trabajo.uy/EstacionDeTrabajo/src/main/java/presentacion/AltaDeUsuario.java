package main.java.presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Interfaces.ICtrlUsuario;
import main.java.presentacion.componentes.IAceptarCancelar;
import main.java.presentacion.componentes.InfoUsuario;
import main.java.presentacion.componentes.PanelBotonesAceptarCancelar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaDeUsuario extends JInternalFrame implements IAceptarCancelar {

    private static final long serialVersionUID = 4993106703293072618L;
    private ICtrlUsuario ctrlUsuario;
    private JLabel lblSeleccionar;
    private JLabel lblCrearEmpresa;
    private JLabel lblCrearPostulante;
    private JPanel principal;

    private PanelBotonesAceptarCancelar panelAceptarCancelar;
	private JButton btnEmpresa;
	private JButton btnPostulante;
	
	private InfoUsuario formularioUsuario;
	private JPanel opciones;
	private boolean esEmpresa;

    public AltaDeUsuario(JFrame gu, ICtrlUsuario icu) {
        ctrlUsuario = Fabrica.getInstance().getICtrlUsuario();

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Usuario");
        setBounds(10, 40, 408, 186);
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

        //Contanedor principal
        principal = new JPanel();
        getContentPane().add(principal);
        principal.setLayout(new BorderLayout(0, 0));

        
        lblSeleccionar = new JLabel("Seleccione si es un postulante o una empresa:");
        lblSeleccionar.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblSeleccionar.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblCrearEmpresa = new JLabel("Crear Empresa");
        lblCrearPostulante = new JLabel("Crear Postulante");
        
        principal.add(lblSeleccionar, BorderLayout.NORTH);

        // Botones de Aceptar - Cancelar
        panelAceptarCancelar = new PanelBotonesAceptarCancelar();
        panelAceptarCancelar.setListener(this);
        panelAceptarCancelar.setAceptarVisible(false);
        principal.add(panelAceptarCancelar, BorderLayout.SOUTH);

        // Opciones Empresa - Postulante
        opciones = new JPanel();
        opciones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        principal.add(opciones, BorderLayout.CENTER);

        btnEmpresa = new JButton("Empresa");
        opciones.add(btnEmpresa);
        btnEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearEmpresa();
            }
        });

        btnPostulante = new JButton("Postulante");
        opciones.add(btnPostulante);
        btnPostulante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	crearPostulante();
            }
        });
        
        // Formulario de Usuario
        formularioUsuario = new InfoUsuario();
        
        
    }


    @Override
    public void onAceptar() {
        try {
            if (esEmpresa) {
                DTEmpresa empresa = (DTEmpresa) formularioUsuario.getDTUsuario();
                String contrasenia = formularioUsuario.getContrasenia();
                if (empresa.getUrl() == null || empresa.getUrl().isEmpty()) {
                    ctrlUsuario.altaEmpresa(
                        empresa.getNickname(),
                        empresa.getNombre(),
                        empresa.getApellido(),
                        empresa.getCorreo_electronico(),
                        empresa.getNombreEmpresa(),
                        empresa.getDescripcion()
                    );
                } else {
                    ctrlUsuario.altaEmpresaURL(
                        empresa.getNickname(),
                        empresa.getNombre(),
                        empresa.getApellido(),
                        empresa.getCorreo_electronico(),
                        empresa.getNombreEmpresa(),
                        empresa.getDescripcion(),
                        empresa.getUrl()
                    );
                }
            } else {
                DTPostulante postulante = (DTPostulante) formularioUsuario.getDTUsuario();
                String contrasenia = formularioUsuario.getContrasenia();
                ctrlUsuario.altaPostulante(
                    postulante.getNickname(),
                    postulante.getNombre(),
                    postulante.getApellido(),
                    postulante.getCorreo_electronico(),
                    postulante.getFecha_nac(),
                    postulante.getNacionalidad()
                );
            }
            JOptionPane.showMessageDialog(this, "El usuario se ha creado con éxito.", "Alta de Usuario", JOptionPane.INFORMATION_MESSAGE);
            onCancelar();
        } catch (Exception e) {
            // Manejar la excepción adecuadamente, por ejemplo, mostrar un mensaje de error.
            JOptionPane.showMessageDialog(this, "Error al crear el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    @Override
    public void onCancelar() {
        setVisible(false);
        reiniciar();
    }
    
    public void reiniciar() {
    	formularioUsuario.reiniciarFormulario();
    	
    	panelAceptarCancelar.setAceptarVisible(false);
    	
    	principal.remove(formularioUsuario);
    	principal.remove(lblCrearPostulante);
    	principal.remove(lblCrearEmpresa);
    	
    	principal.add(opciones, BorderLayout.CENTER);    	
    	principal.add(lblSeleccionar, BorderLayout.NORTH);
    	
    	
    }
    
    private void crearEmpresa() {
    	
    	panelAceptarCancelar.setAceptarVisible(true);
    	principal.remove(opciones);
        principal.add(formularioUsuario, BorderLayout.CENTER);
        formularioUsuario.modoAltaEmpresa();
        esEmpresa = true;
        
        principal.remove(lblSeleccionar);
        principal.add(lblCrearEmpresa, BorderLayout.NORTH);
    	
    }
    
    private void crearPostulante() {
    	panelAceptarCancelar.setAceptarVisible(true);
    	principal.remove(opciones);
        principal.add(formularioUsuario, BorderLayout.CENTER);
        formularioUsuario.modoAltaPostulante();
        esEmpresa = false;
        
        principal.remove(lblSeleccionar);
        principal.add(lblCrearPostulante, BorderLayout.NORTH);
    }
}
