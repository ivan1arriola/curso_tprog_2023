package presentacion;

import javax.swing.JInternalFrame;


import excepciones.UsuarioNoExisteException;
import logica.Interfaces.ICtrlUsuario;
import logica.Interfaces.ICtrlOferta;
import logica.Fabrica;
import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;
import javax.swing.*;
import java.awt.*;
	
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;




/**
 * JInternalFrame que permite consultar la información de un usuario del sistema.
 * @author TProg2017
 *
 */
@SuppressWarnings("serial")
public class AltaOfertaLaboral extends JInternalFrame {
	private JTable table;
	private JTextField nombre;
	private JTextField ciudad;
	private JTextField remuneracion;
	private JTextField desde;
	private JTextField hasta;
	private JTextField fechaAlta_1;
	private JComboBox<String> listadoEmpresas;
	private JComboBox<String> listadoOfertas;
	private JComboBox<String> keywords;
	private ICtrlUsuario ICU;
    private JTextArea descripcion;
	
	
    /**
     * Create the frame.
     */
    public AltaOfertaLaboral(ICtrlUsuario icu) {
    	Fabrica fabrica = Fabrica.getInstance();
        ICtrlOferta ico = fabrica.getICtrlOferta();
        ICU = icu;
    	// Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Oferta Laboral");
        setBounds(30, 30, 477, 505);
        
        
        
        
        // Absolute layout
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Listado Empresas");
        lblNewLabel.setBounds(21, 31, 98, 14);
        getContentPane().add(lblNewLabel);
        
        table = new JTable();
        table.setBounds(67, 45, 1, 1);
        getContentPane().add(table);
        
        listadoEmpresas = new JComboBox<String>();
        listadoEmpresas.setBounds(173, 27, 253, 22);
        getContentPane().add(listadoEmpresas);
        
        listadoEmpresas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
          		
          		
          		
        		try {
        			icu.listarEmpresas();
        			listadoEmpresas.removeAllItems();
            		Set<String> nombs = icu.listarEmpresas();
            		Iterator<String> iterator = nombs.iterator();
            		while(iterator.hasNext()) {
                    	String elem = iterator.next();
                    	listadoEmpresas.addItem(elem);
            		}
        		}
        		catch (Exception e1) {
        			JOptionPane.showMessageDialog(AltaOfertaLaboral.this, e1,"ERROR - Alta de Empresa", JOptionPane.ERROR_MESSAGE);
        		}
        		
      		
        	}
        });
        
        JLabel lblNewLabel_1 = new JLabel("Listado Oferta");
        lblNewLabel_1.setBounds(21, 64, 107, 14);
        getContentPane().add(lblNewLabel_1);
        
        listadoOfertas = new JComboBox<String>();
        listadoOfertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String empresa = (String) listadoEmpresas.getSelectedItem();
        		try {
        			icu.listarOfertasLaborales(empresa);
        			listadoOfertas.removeAllItems();
            		Set<String> nombs = icu.listarEmpresas();
            		Iterator<String> iterator = nombs.iterator();
            		while(iterator.hasNext()) {
                    	String elem = iterator.next();
                    	listadoOfertas.addItem(elem);
            		}
        		}
        		catch (Exception e1) {
        			JOptionPane.showMessageDialog(AltaOfertaLaboral.this, e1,"ERROR - Alta de Empresa", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        listadoOfertas.setBounds(173, 60, 253, 22);
        getContentPane().add(listadoOfertas);
        
        
        
        JLabel lblNewLabel_2 = new JLabel("Nombre");
        lblNewLabel_2.setBounds(22, 115, 46, 14);
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Descripcion");
        lblNewLabel_3.setBounds(21, 155, 78, 14);
        getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Desde ");
        lblNewLabel_4.setBounds(22, 215, 46, 14);
        getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Hasta");
        lblNewLabel_5.setBounds(261, 215, 46, 14);
        getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Remuneracion");
        lblNewLabel_6.setBounds(21, 255, 98, 14);
        getContentPane().add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Departamento");
        lblNewLabel_7.setBounds(21, 300, 98, 14);
        getContentPane().add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Ciudad");
        lblNewLabel_8.setBounds(261, 300, 46, 14);
        getContentPane().add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Fecha de Alta");
        lblNewLabel_9.setBounds(21, 341, 79, 14);
        getContentPane().add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Keywords");
        lblNewLabel_10.setBounds(21, 378, 68, 14);
        getContentPane().add(lblNewLabel_10);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// leer listadoEmpresa
        		// leer listadoOferta
        		// private JTextField nombre;
        		// private JTextField ciudad;
        		// private JTextField remuneracion;
        		// private JTextField desde;
        		// private JTextField hasta;
        		// private JTextField fechaAlta_1;
        		//String empresaSelec = (String) listadoEmpresa.getSelectedItem();
        		//String ofertaSelec = (String) listadoOferta.getSelectedItem();
        		//String nom = nombre.getText(); // leo nombre
        		//String ciu = ciudad.getText(); // leo ciudad
        		//String desc = descripcion.getText(); // esta mal
        		
        		
        		boolean b = true; //= icu.altaOfertaLaboral(IS_CLOSED_PROPERTY, TOOL_TIP_TEXT_KEY, GLASS_PANE_PROPERTY, FRAME_ICON_PROPERTY, null, ABORT, CONTENT_PANE_PROPERTY, TOOL_TIP_TEXT_KEY, null, null)
        		
                if(b) { 
                //	JOptionPane.showMessageDialog(this, "La oferta laboral se ha creado con éxito.", "Alta Oferta Laboral", JOptionPane.INFORMATION_MESSAGE); }
                //	else {JOptionPane.showMessageDialog(this, "...", "Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);}
        	}
        	}
        });
        
        btnAceptar.setBounds(114, 424, 89, 23);
        getContentPane().add(btnAceptar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(337, 424, 89, 23);
        getContentPane().add(btnCerrar);
        
        nombre = new JTextField();
        nombre.setBounds(109, 112, 318, 20);
        getContentPane().add(nombre);
        nombre.setColumns(10);
        
        descripcion = new JTextArea();
        descripcion.setBounds(109, 150, 317, 54);
        getContentPane().add(descripcion);
        
        JComboBox departamento = new JComboBox();
        departamento.setBounds(111, 296, 139, 22);
        getContentPane().add(departamento);
        
        ciudad = new JTextField();
        ciudad.setBounds(317, 297, 109, 20);
        getContentPane().add(ciudad);
        ciudad.setColumns(10);
        
        remuneracion = new JTextField();
        remuneracion.setBounds(109, 252, 317, 20);
        getContentPane().add(remuneracion);
        remuneracion.setColumns(10);
        
        desde = new JTextField();
        desde.setBounds(109, 212, 141, 20);
        getContentPane().add(desde);
        desde.setColumns(10);
        
        hasta = new JTextField();
        hasta.setBounds(307, 212, 119, 20);
        getContentPane().add(hasta);
        hasta.setColumns(10);
        
        JList list = new JList();
        list.setBounds(172, 366, 51, -25);
        getContentPane().add(list);
        
        keywords = new JComboBox<String>();
        keywords.setBounds(108, 374, 318, 22);
        getContentPane().add(keywords);
        
        fechaAlta_1 = new JTextField();
        fechaAlta_1.setBounds(110, 338, 316, 20);
        getContentPane().add(fechaAlta_1);
        fechaAlta_1.setColumns(10);
        
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
		fechaAlta_1.setText(currentDate.format(dateFormatter));
		fechaAlta_1.setEditable(false);
    }
    
    public void actualizar() {
		listadoEmpresas.removeAllItems();
        HashSet<String> empresas = ICU.listarEmpresas();
        
        listadoEmpresas.addItem("");
        for(String elemento : empresas) {
        	listadoEmpresas.addItem(elemento);
        }     				
	}
    
    private void limpiarFormulario() {
    	nombre.setText("");
    	descripcion.setText("");
    	
    	desde.setText("");
        hasta.setText("");
        remuneracion.setText("");
        ciudad.setText("");
        fechaAlta_1.setText("");
    }
}