package main.java.presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
import main.java.logica.Fabrica;
import main.java.logica.interfaces.ICtrlOferta;
<<<<<<< HEAD
=======
import main.java.presentacion.componentes.IAceptarCancelar;
import main.java.presentacion.componentes.PanelBotonesAceptarCancelar;
//import main.java.presentacion.formularios.FormularioAltaTipoPublicacion;
>>>>>>> 496862e437dbf17eb548948dd80cc8894fd9e027
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaTipoPublicaciónOfertaLaboral extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
    private ICtrlOferta ctrlOferta = Fabrica.getInstance().getICtrlOferta();
    private JTextField nombreField;
    private JTextArea descripcionTextArea;
    private JSpinner exposicionSpinner;
    private JSpinner duracionSpinner;
    private JSpinner costoSpinner;
    private JTextField fechaAltaField;
    private LocalDate fechaActual;
       
    public AltaTipoPublicaciónOfertaLaboral() {
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setClosable(true);
        setTitle("Alta de Tipo de publicación de Oferta Laboral");
        setBounds(100, 100, 380, 380);

        //JPanel mainPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        //gbl.columnWeights = new double[]{0.0, 1.0};
        //GridBagConstraints gbc=new GridBagConstraints();
        getContentPane().setLayout(gbl);
      	
      	JLabel lblNombre = new JLabel("Nombre");
      	lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblNombre = new GridBagConstraints();
      	gbc_lblNombre.anchor = GridBagConstraints.WEST;
      	gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
      	gbc_lblNombre.gridx = 0;
      	gbc_lblNombre.gridy = 0;
      	getContentPane().add(lblNombre, gbc_lblNombre);
        
      	nombreField = new JTextField();
      	GridBagConstraints gbc_nombre = new GridBagConstraints();
      	gbc_nombre.ipady = 7;
      	gbc_nombre.insets = new Insets(5, 5, 5, 5);
      	gbc_nombre.gridx = 1;
      	gbc_nombre.gridy = 0;
      	gbc_nombre.ipadx = 120;
      	getContentPane().add(nombreField, gbc_nombre);
      	nombreField.setColumns(10);
      	
      	JLabel lblDesc = new JLabel("Descripción");
      	lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblDesc = new GridBagConstraints();
      	gbc_lblDesc.anchor = GridBagConstraints.WEST;
      	gbc_lblDesc.insets = new Insets(0, 0, 5, 5);
      	gbc_lblDesc.gridx = 0;
      	gbc_lblDesc.gridy = 1;
      	getContentPane().add(lblDesc, gbc_lblDesc);
      	
      	descripcionTextArea = new JTextArea();
      	JScrollPane scrollDesc = new JScrollPane(descripcionTextArea);
        scrollDesc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollDesc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        GridBagConstraints gbc_Desc = new GridBagConstraints();
        gbc_Desc.fill = GridBagConstraints.HORIZONTAL;
        gbc_Desc.insets = new Insets(5, 5, 5, 5);
        gbc_Desc.ipadx = 120;
        gbc_Desc.ipady = 50;
        gbc_Desc.gridx = 1;
        gbc_Desc.gridy = 1;
        getContentPane().add(scrollDesc, gbc_Desc);
        
        
    	JLabel lblExpo = new JLabel("Exposición");
      	GridBagConstraints gbc_lblExpo = new GridBagConstraints();
      	gbc_lblExpo.anchor = GridBagConstraints.WEST;
      	gbc_lblExpo.insets = new Insets(0, 0, 5, 5);
      	gbc_lblExpo.gridx = 0;
      	gbc_lblExpo.gridy = 2;
      	getContentPane().add(lblExpo, gbc_lblExpo);
        
        exposicionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        GridBagConstraints gbc_Spin = new GridBagConstraints();
        gbc_Spin.anchor = GridBagConstraints.EAST;
        gbc_Spin.insets = new Insets(5, 5, 5, 5);
        gbc_Spin.ipadx = 10;
        gbc_Spin.ipady = 7;
        gbc_Spin.gridx = 1;
        gbc_Spin.gridy = 2;
        getContentPane().add(exposicionSpinner, gbc_Spin);
        
        
        JLabel lblDura = new JLabel("Duración(días)");
      	GridBagConstraints gbc_lblDura = new GridBagConstraints();
      	gbc_lblDura.anchor = GridBagConstraints.WEST;
      	gbc_lblDura.insets = new Insets(0, 0, 5, 5);
      	gbc_lblDura.gridx = 0;
      	gbc_lblDura.gridy = 3;
      	getContentPane().add(lblDura, gbc_lblDura);
        
        duracionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        GridBagConstraints gbc_Dura = new GridBagConstraints();
        gbc_Dura.anchor = GridBagConstraints.EAST;
        gbc_Dura.insets = new Insets(5, 5, 5, 5);
        gbc_Dura.ipadx = 5;
        gbc_Dura.ipady = 7;
        gbc_Dura.gridx = 1;
        gbc_Dura.gridy = 3;
        getContentPane().add(duracionSpinner, gbc_Dura);
        
        JLabel lblCosto = new JLabel("Costo");
      	GridBagConstraints gbc_lblCosto = new GridBagConstraints();
      	gbc_lblCosto.anchor = GridBagConstraints.WEST;
      	gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
      	gbc_lblCosto.gridx = 0;
      	gbc_lblCosto.gridy = 4;
      	getContentPane().add(lblCosto, gbc_lblCosto);
        
      	costoSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        GridBagConstraints gbc_Costo = new GridBagConstraints();
        gbc_Costo.anchor = GridBagConstraints.EAST;
        gbc_Costo.insets = new Insets(5, 5, 5, 5);
        gbc_Costo.ipadx = 5;
        gbc_Costo.ipady = 7;
        gbc_Costo.gridx = 1;
        gbc_Costo.gridy = 4;
        getContentPane().add(costoSpinner, gbc_Costo);
        
        JLabel lblFecha = new JLabel("Fecha de Alta");
      	GridBagConstraints gbc_lblFecha = new GridBagConstraints();
      	gbc_lblFecha.anchor = GridBagConstraints.WEST;
      	gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
      	gbc_lblFecha.gridx = 0;
      	gbc_lblFecha.gridy = 5;
      	getContentPane().add(lblFecha, gbc_lblFecha);
      	
      	fechaActual = LocalDate.now();
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatter);
        
        fechaAltaField = new JTextField();
        fechaAltaField.setHorizontalAlignment(SwingConstants.TRAILING);
        fechaAltaField.setEditable(false);
        fechaAltaField.setText(fechaFormateada);
        
        GridBagConstraints gbc_Fecha = new GridBagConstraints();
        gbc_Fecha.anchor = GridBagConstraints.EAST;
        gbc_Fecha.insets = new Insets(5, 5, 5, 5);
        gbc_Fecha.ipadx = 5;
        gbc_Fecha.ipady = 7;
        gbc_Fecha.gridx = 1;
        gbc_Fecha.gridy = 5;
        getContentPane().add(fechaAltaField, gbc_Fecha);
        
        
        JButton Aceptar = new JButton("Aceptar");
        GridBagConstraints gbc_Aceptar = new GridBagConstraints();
        gbc_Aceptar.ipadx = 4;
        gbc_Aceptar.insets = new Insets(10, 0, 0, 5);
        gbc_Aceptar.gridx = 0;
        gbc_Aceptar.gridy = 6;
        getContentPane().add(Aceptar, gbc_Aceptar);
        
        JButton Cancelar = new JButton("Cancelar");
        GridBagConstraints gbc_Cancelar = new GridBagConstraints();
        gbc_Cancelar.insets = new Insets(10, 0, 0, 0);
        gbc_Cancelar.ipadx = 4;
        gbc_Cancelar.anchor = GridBagConstraints.EAST;
        gbc_Cancelar.gridx = 1;
        gbc_Cancelar.gridy = 6;
        getContentPane().add(Cancelar, gbc_Cancelar);
        
     
        
                
        /*FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setHgap(10);
        JPanel containerPanel = new JPanel(flowLayout);
        GridBagConstraints gbc_botones = new GridBagConstraints();
        gbc_botones.fill = GridBagConstraints.HORIZONTAL;
        gbc_botones.insets = new Insets(10, 0, 0, 0);
        gbc_botones.gridx = 1;
        gbc_botones.gridy = 6;
        containerPanel.add(Aceptar);
        containerPanel.add(Cancelar);
        getContentPane().add(containerPanel, gbc_botones);*/
        
        Aceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
        			
        			String fechaTexto = fechaAltaField.getText();
        			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        			LocalDate fechaConvertida = LocalDate.parse(fechaTexto, formatter);
        			String nombre = nombreField.getText();
        	        String descripcion = descripcionTextArea.getText();
        	        int expos = (int) exposicionSpinner.getValue();
        	        int duracion = (int) duracionSpinner.getValue();
        	        float costo = (float) costoSpinner.getValue();
        	        
        		        			
    	            boolean res = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, expos, duracion, costo, fechaConvertida);
    	            System.out.print(res);
    	            if (res) {
    	                JOptionPane.showMessageDialog(null, "Tipo de publicación de oferta laboral creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    	                dispose();
    	            }
    	            nombreField.setText("");
    	    		descripcionTextArea.setText("");
    	    		fechaAltaField.setText("");
    	    		exposicionSpinner.setValue(1);
    	    		duracionSpinner.setValue(1);
    	    	    costoSpinner.setValue(1);
    	        } catch (IllegalArgumentException ex) {
    	            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	
		nombreField.setText("");
		descripcionTextArea.setText("");
		fechaAltaField.setText("");
		exposicionSpinner.setValue(1);
		duracionSpinner.setValue(1);
	    costoSpinner.setValue(1);
	    	
		dispose();
		//setVisible(false);
		
	}


}
