package presentacion;

import javax.swing.*;
import java.awt.*;


import java.util.HashSet;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import logica.Fabrica;
import logica.Datatypes.DTPaquete;
import logica.Interfaces.ICtrlOferta;
import logica.Interfaces.ICtrlUsuario;
import logica.Manejadores.PaqueteHandler;
import logica.Manejadores.TipoOfertaHandler;

public class AgregarTipodePublicacióndeOfertaLaboral extends JInternalFrame {
    private JTextField CantidadMostrar;
    private ICtrlUsuario icu;
    private ICtrlOferta ico;
    private JComboBox<String> listadoTipoPub;
    private JComboBox<String> PaquetesVisualizar;
    /**
     * Create the application.
     */
    public AgregarTipodePublicacióndeOfertaLaboral(ICtrlOferta ICO,ICtrlUsuario ICU) {
    	icu = ICU;
    	ico = ICO;
        initialize();
        
        PaquetesVisualizar = new JComboBox<String>();
        PaquetesVisualizar.addItem("");// casilla vacia
        PaquetesVisualizar.setBounds(280, 27, 298, 24);
        getContentPane().add(PaquetesVisualizar);
        
        HashSet<String> publicaciones = ICO.listarTipoDePublicaciones();
        listadoTipoPub = new JComboBox<String>();
        listadoTipoPub.addItem("");// casilla vacia
        for (String nombre : publicaciones) {
        	listadoTipoPub.addItem(nombre);
        }
        listadoTipoPub.setBounds(280, 86, 298, 24);
        getContentPane().add(listadoTipoPub);
        
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String text = CantidadMostrar.getText();
                String op1 =  (String) PaquetesVisualizar.getSelectedItem();
                String op2 =  (String) listadoTipoPub.getSelectedItem();
                if (op1 != "" && op2 != "") {
                	try {
                        int valor = text.isEmpty() ? 0 : Integer.parseInt(text);
                        ICO.agregarTipoOfertaPaq(op1, op2,valor);
                        JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "Se ha vinculado el tipo de publicacion a la Oferta Laboral", "Alta de Postulante", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "Ingrese por favor un numero", "Alta de Postulante", JOptionPane.ERROR_MESSAGE);
                    }
    	        }
                setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(122, 202, 117, 25);
        getContentPane().add(btnNewButton_1);
       
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
        setTitle("Agregar Tipo de Publicación de Oferta Laboral");
        setBounds(30, 30, 642, 305);
        getContentPane().setLayout(null);
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Paquetes");
        lblNewJgoodiesTitle.setBounds(27, 32, 130, 15);
        getContentPane().add(lblNewJgoodiesTitle);
        
        JLabel lblIngresoCI_1 = new JLabel("Cantidad:");
        lblIngresoCI_1.setBounds(27, 143, 170, 15);
        getContentPane().add(lblIngresoCI_1);
        
        CantidadMostrar = new JTextField();
        CantidadMostrar.setColumns(10);
        CantidadMostrar.setBounds(280, 139, 298, 24);
        getContentPane().add(CantidadMostrar);
        
        JLabel lblIngresoCI_1_1 = new JLabel("Tipo publicacion:");
        lblIngresoCI_1_1.setBounds(27, 91, 170, 15);
        getContentPane().add(lblIngresoCI_1_1);
        
        JButton btnNewButton_1 = new JButton("Cerrar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	limpiarFormulario();
            	dispose(); // cierra ventana
            }
        });
        btnNewButton_1.setBounds(461, 202, 117, 25);
        getContentPane().add(btnNewButton_1);
        
        
    }
    
    public void actualizar() {
    	HashSet<String> tiposDePub = ico.listarTipoDePublicaciones();
    	HashSet<String> paquetes = ico.listarPaquetes();
    	
    	for (String element : tiposDePub) {
    		listadoTipoPub.addItem(element);
    	}
    	
    	for (String element1 : paquetes) {
    		PaquetesVisualizar.addItem(element1);
    	}
    	
    }
    
    private void limpiarFormulario() {
    	CantidadMostrar.setText(""); 
    }
}