package presentacion;

import java.util.ArrayList;
import java.util.Collections;
//import java.awt.*;
import java.util.HashSet;
import java.util.List;
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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTPaquete;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

@SuppressWarnings("serial")
public class AgregarTipodePublicacióndeOfertaLaboral extends JInternalFrame {
    private JTextField cantidadMostrar;
    private ICtrlOferta ico;
    private JComboBox<String> listadoTipoPub;
    private JComboBox<String> paquetesVisualizar;
    //private JComboBox<String> paquetesVisualizar;
    
    /**
     * Create the application.
     */
    public AgregarTipodePublicacióndeOfertaLaboral(ICtrlOferta ICO,  ICtrlUsuario ICU) {
    	
    	ico = ICO;
        initialize();
           
        paquetesVisualizar = new JComboBox<String>();
        paquetesVisualizar.setBounds(190,   27,   298,   24);
        getContentPane().add(paquetesVisualizar);    
        listadoTipoPub = new JComboBox<String>();
        listadoTipoPub.setBounds(190,   63,   298,   24);
        getContentPane().add(listadoTipoPub); 

        paquetesVisualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		
        		if (paquetesVisualizar.getSelectedIndex() != -1 && paquetesVisualizar.getSelectedIndex() != 0) {
        			
        			listadoTipoPub.removeAllItems();
        			String paqElegido =  (String) paquetesVisualizar.getSelectedItem();
        	        Set<String> publicaciones = ICO.listarTipoDePublicaciones();
        	        DTPaquete dtpaq = ICO.obtenerDatosPaquete(paqElegido);
        	        Set<DTCantTO> publiAgregados = dtpaq.getTiposDePub();
          	        Set<String> publiNoAgregados = new HashSet<>();
        	        
          	        List<String> publiSorted = new ArrayList<>(publicaciones);
                    Collections.sort(publiSorted,  String.CASE_INSENSITIVE_ORDER);
        		   
                                      
                    for (String elem : publiSorted) {
                    	boolean encontrado = false;
                    	for (DTCantTO dtTipo : publiAgregados) {
                    		if (elem.equals(dtTipo.getNombre())) {
                    			encontrado = true;
                    		}
                    	}
                    	if (!encontrado) {
                    		publiNoAgregados.add(elem);
                    	}
                	}
                    
                    
                    listadoTipoPub.addItem("");
                    for (String element : publiNoAgregados) {
        	    		listadoTipoPub.addItem(element);
        	    	}
 
        
        		}
        		
        	}
        });

           
        JButton btnNewButton_1 = new JButton("Aceptar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String text = cantidadMostrar.getText();
                String op1 =  (String) paquetesVisualizar.getSelectedItem();
                String op2 =  (String) listadoTipoPub.getSelectedItem();
                if (paquetesVisualizar.getSelectedIndex() != -1 && paquetesVisualizar.getSelectedIndex() != 0
                		&&
                	listadoTipoPub.getSelectedIndex() != -1 && listadoTipoPub.getSelectedIndex() != 0) {
                	try {
                		if (text.isEmpty()) {
                			JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this,   "El campo cantidad no puede ser vacío.",   "ERROR - Agregar Tipo de Publicación de Oferta Labora",   JOptionPane.ERROR_MESSAGE);
                		}
                		else {
                			int valor = Integer.parseInt(text);
                			if (valor <= 0) {
                				JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this,   "El campo cantidad debe ser un número positivo.",   "ERROR - Agregar Tipo de Publicación de Oferta Labora",   JOptionPane.ERROR_MESSAGE);
                			} else {
                                ICO.agregarTipoOfertaPaq(op1, op2, valor);
                                JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this,   "Se ha vinculado el tipo de publicacion a la Oferta Laboral",   "Agregar Tipo de Publicación de Oferta Laboral",   JOptionPane.INFORMATION_MESSAGE);
                                setVisible(false);		
                			}
                		}
                	} catch (NumberFormatException | ExceptionCantidadPositivaDeTipoOfertaEnPaquete exc) {
                		
                        JOptionPane.showMessageDialog(AgregarTipodePublicacióndeOfertaLaboral.this,   "Ingrese por favor un número",   "ERROR - Agregar Tipo de Publicación de Oferta Laboral",   JOptionPane.ERROR_MESSAGE);
                	}
    	        }
                
        	}
        });
        btnNewButton_1.setBounds(190,   161,   117,   25);
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
        setBounds(30,   30,   530,   250);
        getContentPane().setLayout(null);
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Paquetes");
        lblNewJgoodiesTitle.setBounds(10,   32,   130,   15);
        getContentPane().add(lblNewJgoodiesTitle);
        
        JLabel lblIngresoCI_1 = new JLabel("Cantidad:");
        lblIngresoCI_1.setBounds(10,   105,   170,   15);
        getContentPane().add(lblIngresoCI_1);
        
        cantidadMostrar = new JTextField();
        cantidadMostrar.setColumns(10);
        cantidadMostrar.setBounds(190,   100,   298,   24);
        getContentPane().add(cantidadMostrar);
        
        JLabel lblIngresoCI_1_1 = new JLabel("Tipo publicacion:");
        lblIngresoCI_1_1.setBounds(10,   68,   170,   15);
        getContentPane().add(lblIngresoCI_1_1);
        
        JButton btnNewButton_1 = new JButton("Cerrar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	limpiarFormulario();
            	dispose(); // cierra ventana
            }
        });
        btnNewButton_1.setBounds(371,   161,   117,   25);
        getContentPane().add(btnNewButton_1);
        
        
    }
    
    public void actualizar() {
    	
    	paquetesVisualizar.removeAllItems();
    	paquetesVisualizar.setEnabled(true);
    	
		Set<String> packs = ico.listarPaquetes();
		List<String> packSorted = new ArrayList<>(packs);
        Collections.sort(packSorted,  String.CASE_INSENSITIVE_ORDER);
        
        //paquetesVisualizar.addItem("");
		
        /*for (String elem : packSorted) {
			
			paquetesVisualizar.addItem(elem);
		}*/

        	
    	//quedarse con los no comprados
    	paquetesVisualizar.addItem("");
    	for (String element1 : packSorted) {
    		
    		if (!ico.paqueteComprado(element1)) {
    		   	//si nadie lo compro queda disponible	
    			paquetesVisualizar.addItem(element1);
    		}
    	}
    	
    	
    	/*HashSet<String> tiposDePub = ico.listarTipoDePublicaciones();
    	    	
    	for (String element : tiposDePub) {
    		listadoTipoPub.addItem(element);
    	}*/
    	

    }
    
    private void limpiarFormulario() {
    	cantidadMostrar.setText(""); 
    }
}