package main.java.presentacion;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import main.java.logica.Fabrica;
import main.java.logica.Interfaces.ICtrlOferta;


public class CrearPaqueteDeTiposPublicacionOfertasLaborales extends JInternalFrame {
	
    private static final long serialVersionUID = 1L;
	private ICtrlOferta ctrlOferta;
    //private FormularioCrearPaquete formulario;
    //private PanelBotonesAceptarCancelar botonesPanel;
	private JTextField nombre;
    private JTextArea descripcion;
    private JSpinner periodo;
    private JSpinner descuento;
    private JButton Aceptar;
    private JButton Cancelar;
    

    public CrearPaqueteDeTiposPublicacionOfertasLaborales() {
        ctrlOferta = Fabrica.getInstance().getICtrlOferta();
        setTitle("Crear Paquete De Tipos De Publicacion De Ofertas Laborales");
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setClosable(true);
        setBounds(100, 100, 300, 300);

               
        GridBagLayout gbl=new GridBagLayout();
        getContentPane().setLayout(gbl);
      	
      	JLabel lblNombre = new JLabel("Nombre");
      	lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblNombre = new GridBagConstraints();
      	gbc_lblNombre.anchor = GridBagConstraints.WEST;
      	gbc_lblNombre.insets = new Insets(0, 5, 5, 5);
      	gbc_lblNombre.gridx = 0;
      	gbc_lblNombre.gridy = 0;
      	getContentPane().add(lblNombre, gbc_lblNombre);
      	
      	nombre = new JTextField();
      	GridBagConstraints gbc_nombre = new GridBagConstraints();
      	gbc_nombre.ipady = 7;
      	gbc_nombre.insets = new Insets(5, 5, 5, 0);
      	gbc_nombre.gridx = 1;
      	gbc_nombre.gridy = 0;
      	gbc_nombre.ipadx = 100;
      	getContentPane().add(nombre, gbc_nombre);
      	nombre.setColumns(10);
      	
      	JLabel lblDesc = new JLabel("Descripción");
      	lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblDesc = new GridBagConstraints();
      	gbc_lblDesc.anchor = GridBagConstraints.WEST;
      	gbc_lblDesc.insets = new Insets(0, 5, 5, 5);
      	gbc_lblDesc.gridx = 0;
      	gbc_lblDesc.gridy = 1;
      	getContentPane().add(lblDesc, gbc_lblDesc);
      	
      	descripcion = new JTextArea();
      	JScrollPane scrollDesc = new JScrollPane(descripcion);
        scrollDesc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollDesc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        GridBagConstraints gbc_Desc = new GridBagConstraints();
        gbc_Desc.fill = GridBagConstraints.HORIZONTAL;
        gbc_Desc.insets = new Insets(5, 5, 5, 0);
        gbc_Desc.ipadx = 120;
        gbc_Desc.ipady = 50;
        gbc_Desc.gridx = 1;
        gbc_Desc.gridy = 1;
        getContentPane().add(scrollDesc, gbc_Desc);
        
        JLabel lblExpo = new JLabel("Validez");
      	GridBagConstraints gbc_lblExpo = new GridBagConstraints();
      	gbc_lblExpo.anchor = GridBagConstraints.WEST;
      	gbc_lblExpo.insets = new Insets(0, 5, 5, 5);
      	gbc_lblExpo.gridx = 0;
      	gbc_lblExpo.gridy = 2;
      	getContentPane().add(lblExpo, gbc_lblExpo);
        
        periodo = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        GridBagConstraints gbc_Spin = new GridBagConstraints();
        gbc_Spin.anchor = GridBagConstraints.EAST;
        gbc_Spin.insets = new Insets(5, 5, 5, 0);
        gbc_Spin.ipadx = 10;
        gbc_Spin.ipady = 7;
        gbc_Spin.gridx = 1;
        gbc_Spin.gridy = 2;
        getContentPane().add(periodo, gbc_Spin);
        
        JLabel lblDescuento = new JLabel("Descuento");
      	GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
      	gbc_lblDescuento.anchor = GridBagConstraints.WEST;
      	gbc_lblDescuento.insets = new Insets(0, 5, 5, 5);
      	gbc_lblDescuento.gridx = 0;
      	gbc_lblDescuento.gridy = 3;
      	getContentPane().add(lblDescuento, gbc_lblDescuento);
        
        descuento = new JSpinner(new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0));
        GridBagConstraints gbc_Descuento = new GridBagConstraints();
        gbc_Descuento.anchor = GridBagConstraints.EAST;
        gbc_Descuento.insets = new Insets(5, 5, 5, 0);
        gbc_Descuento.ipadx = 10;
        gbc_Descuento.ipady = 7;
        gbc_Descuento.gridx = 1;
        gbc_Descuento.gridy = 3;
        getContentPane().add(descuento, gbc_Descuento);
        
        
        Aceptar = new JButton("Aceptar");
        GridBagConstraints gbc_Aceptar = new GridBagConstraints();
        gbc_Aceptar.anchor = GridBagConstraints.WEST;
        gbc_Aceptar.ipadx = 4;
        gbc_Aceptar.insets = new Insets(10, 10, 5, 5);
        gbc_Aceptar.gridx = 0;
        gbc_Aceptar.gridy = 4;
        getContentPane().add(Aceptar, gbc_Aceptar);
        
        Cancelar = new JButton("Cancelar");
        GridBagConstraints gbc_Cancelar = new GridBagConstraints();
        gbc_Cancelar.insets = new Insets(10, 0, 5, 10);
        gbc_Cancelar.ipadx = 4;
        gbc_Cancelar.anchor = GridBagConstraints.EAST;
        gbc_Cancelar.gridx = 1;
        gbc_Cancelar.gridy = 4;
        getContentPane().add(Cancelar, gbc_Cancelar);
        
        
        Aceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			       			
        			String nombrePaq = nombre.getText();
        	        String descripcionField = descripcion.getText();
        	        int validez = (int) periodo.getValue();
        	        
        	        
        	        Double descuValue = (Double) descuento.getValue();
        	        float descuentoPaq = descuValue.floatValue();
        	        
        	        LocalDate fechaAlta = LocalDate.now();
        			        		        			
    	            boolean res = ctrlOferta.altaPaqueteOL(nombrePaq, descripcionField, validez, fechaAlta ,descuentoPaq, null);
    	            
    	            //System.out.print(res);
    	            if (res) {
    	                JOptionPane.showMessageDialog(null, "Paquete creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    	               
    	            }
    	            nombre.setText("");
    	    		descripcion.setText("");
    	    		periodo.setValue(1);
    	    		descuento.setValue(1.0);
    	    		

    	        } catch (Exception ex) {
    	            JOptionPane.showMessageDialog(null, "Ocurrió error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    	        }
        		
        	}
        });
        
        Cancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		onCancelar();
        	}
        });
  
     
    }


    public void onCancelar() {
    	nombre.setText("");
		descripcion.setText("");
		periodo.setValue(1);
		descuento.setValue(1);
        dispose();
    }
}

   