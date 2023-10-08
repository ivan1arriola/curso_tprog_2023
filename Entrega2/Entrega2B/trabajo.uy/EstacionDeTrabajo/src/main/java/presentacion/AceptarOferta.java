package main.java.presentacion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.enumerados.DepUY;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;


public class AceptarOferta extends JInternalFrame {
	private ICtrlOferta controlOferta;
	private ICtrlUsuario controlUsuario;
	private HashSet<String> empresas;
	private JComboBox<String> comboBoxEmpresa;
	private JComboBox<String> comboBoxOfertas;
	//private JTextArea infoOferta;
	private JTextArea ofertaDetalle;
    
	
    public AceptarOferta(ICtrlOferta ICO,ICtrlUsuario ICU) {
    	controlUsuario = ICU;
    	controlOferta = ICO;
    	setResizable(true);
        //setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Aceptar o Rechazar Oferta");
        setBounds(30, 30, 500, 285);
        
        
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
        GridBagConstraints gbc = new GridBagConstraints();
      	getContentPane().setLayout(gbl);
      	
    	comboBoxEmpresa = new JComboBox<>();
    	comboBoxEmpresa.setEnabled(true);
    	comboBoxEmpresa.setEditable(false);
    	
    	comboBoxEmpresa.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

    			comboBoxOfertas.removeAllItems();
    			
    				try {
	                if (comboBoxEmpresa.getSelectedIndex() != -1 && comboBoxEmpresa.getSelectedIndex() != 0) {
	                    String selectedEmpresa = (String) comboBoxEmpresa.getSelectedItem();
	                    //Set<String> ofertasEmpresa = controlUsuario.listarOfertasLaborales(selectedEmpresa);
	                    comboBoxOfertas.removeAllItems();
	                    
	                    HashSet<String> ofertasIngresadas = controlOferta.listarOfertasLaboralesIngresadas(selectedEmpresa);
	                    
	                    if (ofertasIngresadas.isEmpty()) {
	
	                        JOptionPane.showMessageDialog(AceptarOferta.this, "No hay ofertas de esta empresa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	                    } 
	                    else {
	                    	
	                    	List<String> ofertasSorted = new ArrayList<>(ofertasIngresadas);
		                    Collections.sort(ofertasSorted, String.CASE_INSENSITIVE_ORDER);
		                    comboBoxOfertas.addItem(" ");
		                    for (String offer : ofertasSorted) {
		                    	
		                        comboBoxOfertas.addItem(offer);

		                    }
	                        
	                    }
	                }
	                
    				} catch (Exception exc) {System.err.println("Error al obtener las ofertas laborales");} 
	                
	                
    		}
    	});     
    	
    	JLabel lblEmpre = new JLabel("Empresa");
    	lblEmpre.setHorizontalAlignment(SwingConstants.LEFT);
    	GridBagConstraints gbc_lblEmpre = new GridBagConstraints();
    	gbc_lblEmpre.anchor = GridBagConstraints.WEST;
    	gbc_lblEmpre.insets = new Insets(0, 15, 5, 5);
    	gbc_lblEmpre.gridx = 0;
    	gbc_lblEmpre.gridy = 0;
    	getContentPane().add(lblEmpre, gbc_lblEmpre);
    	
    	
    	GridBagConstraints gbc_cbEmpresa = new GridBagConstraints();
    	gbc_cbEmpresa.fill = GridBagConstraints.HORIZONTAL;
    	gbc_cbEmpresa.gridwidth = 2;
    	gbc_cbEmpresa.insets = new Insets(0, 0, 10, 5);
    	gbc_cbEmpresa.gridx = 1;
    	gbc_cbEmpresa.gridy = 0;
    	gbc_cbEmpresa.ipadx = 180;
    	getContentPane().add(comboBoxEmpresa, gbc_cbEmpresa);
    	
    	
    	JLabel lblNewLabel = new JLabel("Oferta");
      	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
      	gbc_lblNewLabel.insets = new Insets(0, 15, 5, 5);
      	gbc_lblNewLabel.gridx = 0;
      	gbc_lblNewLabel.gridy = 1;
      	getContentPane().add(lblNewLabel, gbc_lblNewLabel);
      	
      	//COMBOBOX OFERTA
      	comboBoxOfertas = new JComboBox<>();
      	comboBoxOfertas.setEditable(false);
      	comboBoxOfertas.setEnabled(true);

      	
      	GridBagConstraints gbc_cbOferta = new GridBagConstraints();
      	gbc_cbOferta.fill = GridBagConstraints.HORIZONTAL;
      	gbc_cbOferta.gridwidth = 2;
      	gbc_cbOferta.insets = new Insets(0, 0, 10, 5);
      	gbc_cbOferta.gridx = 1;
      	gbc_cbOferta.gridy = 1;
      	gbc_cbOferta.ipadx=180;
      	getContentPane().add(comboBoxOfertas, gbc_cbOferta);
      	
      	JLabel lblOfertaDetalle = new JLabel("Detalles de Oferta:");
      	GridBagConstraints gbc_lblOfDet = new GridBagConstraints();
      	gbc_lblOfDet.gridheight = 2;
      	gbc_lblOfDet.anchor = GridBagConstraints.WEST;
      	gbc_lblOfDet.insets = new Insets(0, 15, 5, 5);
      	gbc_lblOfDet.gridx = 0;
      	gbc_lblOfDet.gridy = 2;
      	getContentPane().add(lblOfertaDetalle, gbc_lblOfDet);
  	
      	
      	ofertaDetalle = new JTextArea();
      	ofertaDetalle.setEditable(false);
      	ofertaDetalle.setLineWrap(true);
        ofertaDetalle.setWrapStyleWord(true);
        
      	
      	JScrollPane infoScrollPane = new JScrollPane(ofertaDetalle);
      	infoScrollPane.setBounds(150, 80, 300, 120);
        infoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        GridBagConstraints gbc_infoScrollPane = new GridBagConstraints();
        gbc_infoScrollPane.anchor = GridBagConstraints.SOUTH;
        gbc_infoScrollPane.gridwidth = 2;
        gbc_infoScrollPane.gridheight = 3;
        gbc_infoScrollPane.fill = GridBagConstraints.HORIZONTAL;
        gbc_infoScrollPane.ipadx = 200;
        gbc_infoScrollPane.ipady = 50;
        gbc_infoScrollPane.insets = new Insets(5, 0, 5, 5);
        gbc_infoScrollPane.gridx = 1;
        gbc_infoScrollPane.gridy = 2;
        getContentPane().add(infoScrollPane, gbc_infoScrollPane);
        
        JButton Confirmar = new JButton("Confirmar");
        
        GridBagConstraints gbc_Confirmar = new GridBagConstraints();
        gbc_Confirmar.insets = new Insets(5, 5, 0, 5);
        gbc_Confirmar.gridx = 2;
        gbc_Confirmar.gridy = 6;
        getContentPane().add(Confirmar, gbc_Confirmar);
        
        JButton Rechazar = new JButton("Rechazar");
        GridBagConstraints gbc_Rechazar = new GridBagConstraints();
        gbc_Rechazar.insets = new Insets(5, 5, 0, 10);
        gbc_Rechazar.gridx = 3;
        gbc_Rechazar.gridy = 6;
        getContentPane().add(Rechazar, gbc_Rechazar);
        
        
        comboBoxOfertas.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

    			ofertaDetalle.setText("");
    			

	                if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem();

	                    DTOfertaExtendido dto = ICO.obtenerOfertaLaboral(selectedOferta);
	                	ofertaDetalle.append(dto.toString());
	                	ofertaDetalle.setCaretPosition(0);     
	 
	                }

	                
	                
    		}
    	});   
        
        
        
        Rechazar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        		
	        		if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem();
	                    controlOferta.rechazoOL(selectedOferta);
	               	}
        		
        		} catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "Error al rechazar" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		limpiarFormulario();
        		dispose();
        		
        		
        	}
        });
        
        Confirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		try {
	        		if (comboBoxOfertas.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != 0) {
	                    String selectedOferta = (String) comboBoxOfertas.getSelectedItem();
	                    controlOferta.aceptoOL(selectedOferta);
	                    JOptionPane.showMessageDialog(null, "Oferta confirmada", "", JOptionPane.INFORMATION_MESSAGE);
	                    
	        		}
        		} catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "Error al confirmar" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        HashSet<String> empresas = controlUsuario.listarEmpresas();
        List<String> empresasSorted = new ArrayList<>(empresas);
        Collections.sort(empresasSorted, String.CASE_INSENSITIVE_ORDER);
        comboBoxEmpresa.addItem(" ");
        for (String nickname : empresasSorted) {
        	
            comboBoxEmpresa.addItem(nickname);

        }

        revalidate(); // Actualizar la interfaz gráfica
    }
    
}
