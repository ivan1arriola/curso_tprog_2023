package main.java.presentacion;

import javax.swing.*;
//import java.awt.*;


import java.util.HashSet;
import java.util.Set;

//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.util.HashSet;
import java.awt.event.ActionEvent;
//import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JList;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;

//import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Datatypes.DTPaquete;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;
//import main.java.logica.Manejadores.PaqueteHandler;
//import main.java.logica.Manejadores.TipoOfertaHandler;

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
        PaquetesVisualizar.setBounds(190, 27, 298, 24);
        getContentPane().add(PaquetesVisualizar);    
        listadoTipoPub = new JComboBox<String>();
        listadoTipoPub.setBounds(190, 63, 298, 24);
        getContentPane().add(listadoTipoPub);

        PaquetesVisualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(PaquetesVisualizar.getSelectedIndex()!=-1 && PaquetesVisualizar.getSelectedIndex()!=0) {
        			
        			String paqElegido =  (String) PaquetesVisualizar.getSelectedItem();
        	        HashSet<String> publicaciones = ICO.listarTipoDePublicaciones();
        	        DTPaquete dtpaq = ICO.obtenerDatosPaquete(paqElegido);
        	        Set<DTCantTO> tiposAgregados = dtpaq.getTiposDePub();
          	        HashSet<String> tipoNoAgregado =new HashSet<>();;
        	        
        	        for (String publi : publicaciones) {
        	            // Verificar si el tipo está contenido en tiposAgregados
        	            boolean encontrado = false;
        	            for (DTCantTO dtTipo : tiposAgregados) {
        	                if (publi.equals(dtTipo.getNombre())) {
        	                    encontrado = true;
        	                    break;  // Terminar bucle si se encuentra una coincidencia
        	                }
        	            }

        	            // Agregar al tipoNoAgregado si no está en tiposAgregados
        	            if (!encontrado) {
        	            	tipoNoAgregado.add(publi);
        	            }
        	        }
        	        
        	        listadoTipoPub.addItem("");
        	        for (String element : tipoNoAgregado) {
        	    		listadoTipoPub.addItem(element);
        	    	}
        
        		}
        		
        	}
        });

           
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String text = CantidadMostrar.getText();
                String op1 =  (String) PaquetesVisualizar.getSelectedItem();
                String op2 =  (String) listadoTipoPub.getSelectedItem();
                if (!op1.equals("") && !op2.equals("")) {
                	try {
                		if(text.isEmpty()) {
                			JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "El campo cantidad no puede ser vacío.", "ERROR - Agregar Tipo de Publicación de Oferta Labora", JOptionPane.ERROR_MESSAGE);
                		}
                		else {
                			int valor = Integer.parseInt(text);
                			if(valor <= 0) {
                				JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "El campo cantidad debe ser un número positivo.", "ERROR - Agregar Tipo de Publicación de Oferta Labora", JOptionPane.ERROR_MESSAGE);
                			} else {
                                ICO.agregarTipoOfertaPaq(op1, op2,valor);
                                JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "Se ha vinculado el tipo de publicacion a la Oferta Laboral", "Agregar Tipo de Publicación de Oferta Labora", JOptionPane.INFORMATION_MESSAGE);
                                setVisible(false);		
                			}
                		}
                	} catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this, "Ingrese por favor un número", "ERROR - Agregar Tipo de Publicación de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    }
    	        }
                
        	}
        });
        btnNewButton_1.setBounds(190, 161, 117, 25);
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
        setBounds(30, 30, 530, 250);
        getContentPane().setLayout(null);
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Paquetes");
        lblNewJgoodiesTitle.setBounds(10, 32, 130, 15);
        getContentPane().add(lblNewJgoodiesTitle);
        
        JLabel lblIngresoCI_1 = new JLabel("Cantidad:");
        lblIngresoCI_1.setBounds(10, 105, 170, 15);
        getContentPane().add(lblIngresoCI_1);
        
        CantidadMostrar = new JTextField();
        CantidadMostrar.setColumns(10);
        CantidadMostrar.setBounds(190, 100, 298, 24);
        getContentPane().add(CantidadMostrar);
        
        JLabel lblIngresoCI_1_1 = new JLabel("Tipo publicacion:");
        lblIngresoCI_1_1.setBounds(10, 68, 170, 15);
        getContentPane().add(lblIngresoCI_1_1);
        
        JButton btnNewButton_1 = new JButton("Cerrar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	limpiarFormulario();
            	dispose(); // cierra ventana
            }
        });
        btnNewButton_1.setBounds(371, 161, 117, 25);
        getContentPane().add(btnNewButton_1);
        
        
    }
    
    public void actualizar() {
    	
    	HashSet<String> paquetes = ico.listarPaquetes();
    	//quedarse con los no comprados
    	PaquetesVisualizar.addItem("");// casilla vacia
    	for (String element1 : paquetes) {
    		
    		if(!ico.paqueteComprado(element1)) {
    		   	//si nadie lo compro queda disponible	
    			PaquetesVisualizar.addItem(element1);
    		}
    	}
    	
    	
    	/*HashSet<String> tiposDePub = ico.listarTipoDePublicaciones();
    	    	
    	for (String element : tiposDePub) {
    		listadoTipoPub.addItem(element);
    	}*/
    	

    }
    
    private void limpiarFormulario() {
    	CantidadMostrar.setText(""); 
    }
}