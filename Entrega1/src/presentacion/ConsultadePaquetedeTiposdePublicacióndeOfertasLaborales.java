package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import logica.Fabrica;
import logica.Datatypes.DTTipoOferta;
import logica.Excepciones.ExcepcionTipoOferta;
import logica.Interfaces.ICtrlOferta;
import logica.Manejadores.PaqueteHandler;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField campoTipoDePublicacion;
	private JTextField fechaDeAlta;
	private JTextField costo;
	private JTextField duracion;
	private JTextField exposicion;
	private JTextField descripcion;

	/**
	 * Create the application.
	 */
	public ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Paquete de Tipos de Publicación de Ofertas Laborales");
        setBounds(30, 30, 688, 713);
        getContentPane().setLayout(null); //Absolute Layout
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String NombretipoDePub = campoTipoDePublicacion.getText(); // obtengo del campo asociado a "Tipo de publicación:"
        		
        		try {
        			DTTipoOferta DTO = ICO.obtenerDatosTO(NombretipoDePub);
        			
            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // definir formato de fecha
            		fechaDeAlta.setText(DTO.getFechaAlta().format(formatter)); // muestro la fecha
            		
            		costo.setText(String.valueOf(DTO.getCosto())); // muestro el costo
            		
            		duracion.setText(String.valueOf(DTO.getDuracion())); // muestro la duración
            		
            		exposicion.setText(String.valueOf(DTO.getExposicion())); // muestro la exposición
            		
            		descripcion.setText(DTO.getDescripcion());
            		
        		} catch(ExcepcionTipoOferta eto) {
        			JOptionPane.showMessageDialog(null, eto.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		}   		
        	}
        });
        btnAceptar.setBounds(33, 644, 117, 25);
        getContentPane().add(btnAceptar);
        
        JComboBox<PaqueteHandler> comboBoxPaqueteHandler = new JComboBox<PaqueteHandler>();
		comboBoxPaqueteHandler.setBounds(158, 32, 507, 25);
		getContentPane().add(comboBoxPaqueteHandler);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(548, 644, 117, 25);
        getContentPane().add(btnCerrar);
        
        JLabel lblNombre = new JLabel("Costo:");
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre.setBounds(33, 88, 133, 15);
        getContentPane().add(lblNombre);
        
        JLabel lblPaquete = new JLabel("Paquete:");
        lblPaquete.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPaquete.setBounds(33, 36, 133, 15);
        getContentPane().add(lblPaquete);
        
        JLabel lblCosto = new JLabel("Descuento:");
        lblCosto.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCosto.setBounds(33, 126, 117, 15);
        getContentPane().add(lblCosto);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(160, 87, 505, 19);
        getContentPane().add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBounds(160, 125, 507, 19);
        getContentPane().add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(160, 165, 507, 19);
        getContentPane().add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(160, 207, 507, 19);
        getContentPane().add(textField_3);
        
        JLabel lblCosto_1 = new JLabel("Validez:");
        lblCosto_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCosto_1.setBounds(33, 167, 133, 15);
        getContentPane().add(lblCosto_1);
        
        JLabel lblCosto_1_1 = new JLabel("Descripción:");
        lblCosto_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCosto_1_1.setBounds(33, 209, 133, 15);
        getContentPane().add(lblCosto_1_1);
        
        table = new JTable();
        table.setBounds(33, 275, 632, 75);
        getContentPane().add(table);
        
        JLabel lblCosto_1_1_1 = new JLabel("Información de los tipos de publicación y sus cantidades");
        lblCosto_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCosto_1_1_1.setBounds(103, 248, 562, 15);
        getContentPane().add(lblCosto_1_1_1);
        
        JLabel lblCosto_1_1_2 = new JLabel("Tipo de publicación:");
        lblCosto_1_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCosto_1_1_2.setBounds(33, 380, 185, 15);
        getContentPane().add(lblCosto_1_1_2);
        
        campoTipoDePublicacion = new JTextField();
        campoTipoDePublicacion.setBounds(215, 378, 282, 22);
        getContentPane().add(campoTipoDePublicacion);
        campoTipoDePublicacion.setColumns(10);
        
        JButton btnNewButton = new JButton("Ver información");
        btnNewButton.addActionListener(new ActionListener() {

        });
        btnNewButton.setBounds(508, 378, 157, 21);
        getContentPane().add(btnNewButton);
        
        JLabel lblNombre_1 = new JLabel("Fecha de alta:");
        lblNombre_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre_1.setBounds(33, 426, 133, 15);
        getContentPane().add(lblNombre_1);
        
        JLabel lblNombre_1_1 = new JLabel("Costo:");
        lblNombre_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre_1_1.setBounds(33, 468, 89, 15);
        getContentPane().add(lblNombre_1_1);
        
        fechaDeAlta = new JTextField();
        fechaDeAlta.setEditable(false);
        fechaDeAlta.setBounds(215, 420, 450, 30);
        getContentPane().add(fechaDeAlta);
        
        costo = new JTextField();
        costo.setEditable(false);
        costo.setBounds(215, 462, 450, 30);
        getContentPane().add(costo);
        
        duracion = new JTextField();
        duracion.setEditable(false);
        duracion.setBounds(215, 504, 450, 30);
        getContentPane().add(duracion);
        
        exposicion = new JTextField();
        exposicion.setEditable(false);
        exposicion.setBounds(215, 546, 450, 30);
        getContentPane().add(exposicion);
        
        JLabel lblNombre_1_1_1 = new JLabel("Duración:");
        lblNombre_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre_1_1_1.setBounds(33, 511, 89, 15);
        getContentPane().add(lblNombre_1_1_1);
        
        descripcion = new JTextField();
        descripcion.setEditable(false);
        descripcion.setBounds(215, 588, 450, 30);
        getContentPane().add(descripcion);
        
        JLabel lblNombre_1_1_1_1 = new JLabel("Exposición:");
        lblNombre_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre_1_1_1_1.setBounds(34, 553, 103, 15);
        getContentPane().add(lblNombre_1_1_1_1);
        
        JLabel lblNombre_1_1_1_1_1 = new JLabel("Descripción:");
        lblNombre_1_1_1_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre_1_1_1_1_1.setBounds(33, 595, 117, 15);
        getContentPane().add(lblNombre_1_1_1_1_1);

        
	}
}

	
