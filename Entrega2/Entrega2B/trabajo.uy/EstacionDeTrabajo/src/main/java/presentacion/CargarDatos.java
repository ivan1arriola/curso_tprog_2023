package main.java.presentacion;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;

import java.util.ArrayList;

import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTHora;
import main.java.logica.Datatypes.DTHorario;
import main.java.logica.Enumerados.DepUY;
import main.java.logica.Enumerados.EstadoOL;
import main.java.logica.Interfaces.ICtrlCargaDeDatos;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CargarDatos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICtrlUsuario ICU;
	private ICtrlOferta ICO;
	private ICtrlCargaDeDatos ICCD;
	/**
	 * Create the application.
	 */
	public CargarDatos(ICtrlUsuario icu, ICtrlOferta ico) {
        Fabrica fabrica = Fabrica.getInstance();
		ICCD = fabrica.getICtrlCargaDeDatos();
		ICU = icu;
		ICO = ico;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Cargar datos");
        setBounds(30, 30, 477, 153);
        getContentPane().setLayout(null); //Absolute Layout
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ICCD.cargarDatos();
                
                JOptionPane.showMessageDialog(CargarDatos.this, "Se han cargado los datos exitosamente.", "Carga de Datos", JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(false);
        	}
        });
        btnAceptar.setBounds(63, 78, 117, 25);
        getContentPane().add(btnAceptar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(297, 78, 117, 25);
        getContentPane().add(btnCerrar);
        
        JLabel lblSeleccioneAceptarPara = new JLabel("Seleccione aceptar para cargar los datos.");
        lblSeleccioneAceptarPara.setBounds(81, 28, 332, 15);
        getContentPane().add(lblSeleccioneAceptarPara);
	}
}
