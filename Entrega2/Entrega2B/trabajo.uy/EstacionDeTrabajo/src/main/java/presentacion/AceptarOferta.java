package main.java.presentacion; 


import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List; 
import java.util.Set; 

import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JFrame; 
import javax.swing.JInternalFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.ScrollPaneConstants; 
import javax.swing.SwingConstants; 
import java.awt.event.ActionListener; 
import java.awt.GridBagConstraints; 

import java.awt.event.ActionEvent; 

import main.java.logica.datatypes.DTOfertaExtendido; 

import main.java.logica.interfaces.ICtrlOferta; 
import main.java.logica.interfaces.ICtrlUsuario; 


public class AceptarOferta extends JInternalFrame {
	private ICtrlOferta controlOferta; 
	private ICtrlUsuario controlUsuario; 
	private JComboBox<String> comboBoxEmpresa; 
	private JComboBox<String> comboBoxOfertas; 
	//private JTextArea infoOferta; 
	private JTextArea ofertaDetalle; 
    
	
    public AceptarOferta(ICtrlOferta ICO,  ICtrlUsuario ICU) {
    	controlUsuario = ICU; 
    	controlOferta = ICO; 
    	setResizable(true); 
        //setIconifiable(true); 
        setMaximizable(true); 
        setClosable(true); 
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
        setTitle("Aceptar o Rechazar Oferta"); 
        setBounds(30,   30,   395,   259);
        GridBagConstraints gbc = new GridBagConstraints();
      	
    	comboBoxEmpresa = new JComboBox<>(); 
    	comboBoxEmpresa.setBounds(156, 16, 209, 24);
    	comboBoxEmpresa.setEnabled(true); 
    	comboBoxEmpresa.setEditable(false); 
    	
    	comboBoxEmpresa.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evento) {

    			comboBoxOfertas.removeAllItems(); 
    			
    				try {
	                if (comboBoxEmpresa.getSelectedIndex() != -1 && comboBoxEmpresa.getSelectedIndex() != 0) {
	                    String selectedEmpresa = (String) comboBoxEmpresa.getSelectedItem(); 
	                    //Set<String> ofertasEmpresa = controlUsuario.listarOfertasLaborales(selectedEmpresa); 
	                    comboBoxOfertas.removeAllItems(); 
	                    
	                    Set<String> ofertasIngresadas = controlOferta.listarOfertasLaboralesIngresadas(selectedEmpresa); 
	                    
	                    if (ofertasIngresadas.isEmpty()) {
	
	                        JOptionPane.showMessageDialog(AceptarOferta.this,   "No hay ofertas de esta empresa",   "Mensaje",   JOptionPane.INFORMATION_MESSAGE); 
	                    } 
	                    else {
	                    	
	                    	List<String> ofertasSorted = new ArrayList<>(ofertasIngresadas); 
		                    Collections.sort(ofertasSorted,   String.CASE_INSENSITIVE_ORDER); 
		                    comboBoxOfertas.addItem(" "); 
		                    for (String offer : ofertasSorted) {
		                    	
		                        comboBoxOfertas.addItem(offer); 

		                    }
	                        
	                    }
	                }
	                
    				} catch (NullPointerException exc) {
    					System.err.println("Error al obtener las ofertas laborales"); } 
	                
	                
    		}
    	});      
    	getContentPane().setLayout(null);
    	
    	JLabel lblEmpre = new JLabel("Empresa:"); 
    	lblEmpre.setBounds(15, 23, 66, 15);
    	lblEmpre.setHorizontalAlignment(SwingConstants.LEFT);
    	getContentPane().add(lblEmpre);
    	getContentPane().add(comboBoxEmpresa); 
    	
    	
    	JLabel lblNewLabel = new JLabel("Oferta:");
    	lblNewLabel.setBounds(15, 57, 51, 15);
      	getContentPane().add(lblNewLabel); 
      	
      	//COMBOBOX OFERTA
      	comboBoxOfertas = new JComboBox<>(); 
      	comboBoxOfertas.setBounds(156, 50, 209, 24);
      	comboBoxOfertas.setEditable(false); 
      	comboBoxOfertas.setEnabled(true);
      	getContentPane().add(comboBoxOfertas); 
      	
      	JLabel lblOfertaDetalle = new JLabel("Detalles de Oferta:");
      	lblOfertaDetalle.setBounds(15, 104, 136, 15);
      	getContentPane().add(lblOfertaDetalle); 
  	
      	
      	ofertaDetalle = new JTextArea(); 
      	ofertaDetalle.setEditable(false); 
      	ofertaDetalle.setLineWrap(true); 
        ofertaDetalle.setWrapStyleWord(true); 
        
      	
      	JScrollPane infoScrollPane = new JScrollPane(ofertaDetalle); 
      	infoScrollPane.setBounds(156,   89,   209,   72); 
        infoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        infoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(infoScrollPane); 
        
        JButton Confirmar = new JButton("Confirmar");
        Confirmar.setBounds(48, 185, 103, 25);
        getContentPane().add(Confirmar); 
        
        Confirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento3) {
        		
        		
        		try {
	        		if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem(); 
	                    controlOferta.aceptoOL(selectedOferta); 
	                    JOptionPane.showMessageDialog(null,   "Oferta confirmada",   "Aceotar o Rechazar Oferta",   JOptionPane.INFORMATION_MESSAGE); 
	                    
	        		}
        		} catch (IllegalArgumentException ex) {
        			JOptionPane.showMessageDialog(null,   "Error al confirmar" + ex.getMessage(),   "Error",   JOptionPane.ERROR_MESSAGE); 
        		}
        		
        		limpiarFormulario();
        		dispose();
        	}
        }); 
        
        JButton Rechazar = new JButton("Rechazar");
        Rechazar.setBounds(265, 185, 100, 25);
        getContentPane().add(Rechazar); 
        
        
        
        Rechazar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento2) {
        		
        		try {
        		
	        		if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem(); 
	                    controlOferta.rechazoOL(selectedOferta);
	                    JOptionPane.showMessageDialog(null,   "Oferta rechazada",   "Aceotar o Rechazar Oferta",   JOptionPane.INFORMATION_MESSAGE); 
	               	}
        		
        		} catch (IllegalArgumentException ex) {
        			JOptionPane.showMessageDialog(null,   "Error al rechazar" + ex.getMessage(),   "Error",   JOptionPane.ERROR_MESSAGE); 
        		}
        		limpiarFormulario(); 
        		dispose(); 
        		
        		
        	}
        }); 
        
        
        comboBoxOfertas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent evento1) {

    			ofertaDetalle.setText(""); 
    			

	                if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem(); 

	                    DTOfertaExtendido dto = ICO.obtenerOfertaLaboral(selectedOferta); 
	                	ofertaDetalle.append(dto.toString()); 
	                	ofertaDetalle.setCaretPosition(0);      
	 
	                }

	                
	                
    		}
    	});    
                
        
    

}
    
    private void limpiarFormulario() {
        ofertaDetalle.setText(""); 
    }
    
    public void actualizar() {
        comboBoxEmpresa.removeAllItems();  
      
        //comboBoxOfertas.removeAllItems();  
        Set<String> empresas = controlUsuario.listarEmpresas(); 
        List<String> empresasSorted = new ArrayList<>(empresas); 
        Collections.sort(empresasSorted,   String.CASE_INSENSITIVE_ORDER); 
        comboBoxEmpresa.addItem(" "); 
        for (String nickname : empresasSorted) {
        	
            comboBoxEmpresa.addItem(nickname); 

        }

        revalidate();  // Actualizar la interfaz gráfica
    }
    
}
