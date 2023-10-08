package main.java.presentacion; 

import javax.swing.JFrame; 
import javax.swing.JInternalFrame; 
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import java.awt.event.ActionListener; 
import java.time.format.DateTimeFormatter; 
import java.util.HashSet; 
import java.util.Set; 
import java.awt.event.ActionEvent; 
import javax.swing.table.DefaultTableModel; 
import main.java.excepciones.ExcepcionTipoOfertaNoExistente; 
import main.java.logica.datatypes.DTCantTO; 
import main.java.logica.datatypes.DTPaquete; 
import main.java.logica.datatypes.DTTipoOferta; 
import main.java.logica.interfaces.ICtrlOferta; 
import main.java.logica.interfaces.ICtrlUsuario; 

import javax.swing.JTextField; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import java.awt.Font; 
import javax.swing.JTable; 

@SuppressWarnings("serial")
public class ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales extends JInternalFrame {
	private JTextField CostoPaquete; 
	private JTextField PaqueteDescuento; 
	private JTextField ValidezPaquete; 
	private JTextField Descripcion; 
	private JTable table; 
	private JTextField campoTipoDePublicacion; 
	private JTextField fechaDeAlta; 
	private JTextField costo; 
	private JTextField duracion; 
	private JTextField exposicion; 
	private JTextField descripcion; 
	private ICtrlUsuario icu; 
    private ICtrlOferta ico; 
    private JComboBox<String> listadoPaquetes; 
    private static DefaultTableModel tableModel; 
    private DTPaquete paqDT; 

	/**
	 * Create the application.
	 */
	public ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales(ICtrlOferta ICO,  ICtrlUsuario ICU) {
		ico = ICO; 
		initialize(ICO); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ICtrlOferta ICO) {
        setResizable(true); 
        setIconifiable(true); 
        setMaximizable(true); 
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
        setClosable(true); 
        setTitle("Consulta de Paquete de Tipos de Publicación de Ofertas Laborales"); 
        setBounds(30,   30,   688,   713); 
        getContentPane().setLayout(null);  //Absolute Layout
        
        JButton VerInformacion = new JButton("Ver Informacion"); 
        VerInformacion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		String NombretipoDePub = campoTipoDePublicacion.getText();  // obtengo del campo asociado a "Tipo de publicación:"
        		try {
        			DTTipoOferta DTO = ICO.obtenerDatosTO(NombretipoDePub); 
        			
            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // definir formato de fecha
            		fechaDeAlta.setText(DTO.getFechaAlta().format(formatter));  // muestro la fecha
            		
            		costo.setText(String.valueOf(DTO.getCosto()));  // muestro el costo
            		
            		duracion.setText(String.valueOf(DTO.getDuracion()));  // muestro la duración
            		
            		exposicion.setText(String.valueOf(DTO.getExposicion()));  // muestro la exposición
            		
            		descripcion.setText(DTO.getDescripcion()); 
            		
        		} catch (ExcepcionTipoOfertaNoExistente eto) {
        			JOptionPane.showMessageDialog(null,   eto.getMessage(),   "Error",   JOptionPane.ERROR_MESSAGE); 
        		}   		
        	}
        }); 
        VerInformacion.setBounds(509,   376,   156,   25); 
        getContentPane().add(VerInformacion); 
        
        listadoPaquetes = new JComboBox<String>(); 
        listadoPaquetes.addItem(""); // casilla vacia
        listadoPaquetes.setBounds(158,   36,   365,   21); 
        getContentPane().add(listadoPaquetes); 
   
        CostoPaquete = new JTextField(); 
        CostoPaquete.setEditable(false); 
        CostoPaquete.setBounds(160,   87,   505,   19); 
        getContentPane().add(CostoPaquete); 
        CostoPaquete.setColumns(10); 
        
        JButton btnCerrar = new JButton("Cerrar"); 
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		limpiarFormulario(); 
        		dispose();  // cierra ventana
        	}
        }); 
        btnCerrar.setBounds(548,   644,   117,   25); 
        getContentPane().add(btnCerrar); 
        
        JLabel lblNombre = new JLabel("Costo:"); 
        lblNombre.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre.setBounds(33,   88,   133,   15); 
        getContentPane().add(lblNombre); 
        
        JLabel lblPaquete = new JLabel("Paquete:"); 
        lblPaquete.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblPaquete.setBounds(33,   36,   133,   15); 
        getContentPane().add(lblPaquete); 
        
        JLabel lblCosto = new JLabel("Descuento:"); 
        lblCosto.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblCosto.setBounds(33,   126,   117,   15); 
        getContentPane().add(lblCosto); 
        
        
        
        PaqueteDescuento = new JTextField(); 
        PaqueteDescuento.setEditable(false); 
        PaqueteDescuento.setColumns(10); 
        PaqueteDescuento.setBounds(160,   125,   507,   19); 
        getContentPane().add(PaqueteDescuento); 
        
        ValidezPaquete = new JTextField(); 
        ValidezPaquete.setEditable(false); 
        ValidezPaquete.setColumns(10); 
        ValidezPaquete.setBounds(160,   165,   507,   19); 
        getContentPane().add(ValidezPaquete); 
        
        Descripcion = new JTextField(); 
        Descripcion.setEditable(false); 
        Descripcion.setColumns(10); 
        Descripcion.setBounds(160,   207,   507,   19); 
        getContentPane().add(Descripcion); 
        
        JLabel lblCosto_1 = new JLabel("Validez:"); 
        lblCosto_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblCosto_1.setBounds(33,   167,   133,   15); 
        getContentPane().add(lblCosto_1); 
        
        JLabel lblCosto_1_1 = new JLabel("Descripción:"); 
        lblCosto_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblCosto_1_1.setBounds(33,   209,   133,   15); 
        getContentPane().add(lblCosto_1_1); 
        
        String[] columnNames = {"Tipo de Publicacion",   "Cantidad"}; 
        tableModel = new DefaultTableModel(columnNames,   0); 
        
        table = new JTable(tableModel); 
        table.setBounds(33,   275,   632,   75); 
        getContentPane().add(table); 
        
        // JScrollPane scrollPane = new JScrollPane(table); 
        
        JLabel lblCosto_1_1_1 = new JLabel("Información de los tipos de publicación y sus cantidades"); 
        lblCosto_1_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblCosto_1_1_1.setBounds(103,   248,   562,   15); 
        getContentPane().add(lblCosto_1_1_1); 
        
        JLabel lblCosto_1_1_2 = new JLabel("Tipo de publicación:"); 
        lblCosto_1_1_2.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblCosto_1_1_2.setBounds(33,   380,   185,   15); 
        getContentPane().add(lblCosto_1_1_2); 
        
        campoTipoDePublicacion = new JTextField(); 
        campoTipoDePublicacion.setBounds(215,   378,   282,   22); 
        getContentPane().add(campoTipoDePublicacion); 
        campoTipoDePublicacion.setColumns(10); 
        
        JButton BotonAceptar = new JButton("Aceptar"); 
        BotonAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String paqueteSeleccionado = (String) listadoPaquetes.getSelectedItem(); 
        		if (paqueteSeleccionado != "") {
        			paqDT = ICO.obtenerDatosPaquete(paqueteSeleccionado); 
            		CostoPaquete.setText(String.valueOf(paqDT.getCosto()));  // muestro el costo
            		PaqueteDescuento.setText(String.valueOf(paqDT.getDescuento())); 
            		ValidezPaquete.setText(String.valueOf(paqDT.getValidez())); 
            		Descripcion.setText(String.valueOf(paqDT.getDescripcion())); 
    	        } 
        		actualizarTabla(); 
        	}
        }); 
        BotonAceptar.setBounds(536,   36,   130,   21); 
        getContentPane().add(BotonAceptar); 
        
        JLabel lblNombre_1 = new JLabel("Fecha de alta:"); 
        lblNombre_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre_1.setBounds(33,   426,   133,   15); 
        getContentPane().add(lblNombre_1); 
        
        JLabel lblNombre_1_1 = new JLabel("Costo:"); 
        lblNombre_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre_1_1.setBounds(33,   468,   89,   15); 
        getContentPane().add(lblNombre_1_1); 
        
        fechaDeAlta = new JTextField(); 
        fechaDeAlta.setEditable(false); 
        fechaDeAlta.setBounds(215,   420,   450,   30); 
        getContentPane().add(fechaDeAlta); 
        
        costo = new JTextField(); 
        costo.setEditable(false); 
        costo.setBounds(215,   462,   450,   30); 
        getContentPane().add(costo); 
        
        duracion = new JTextField(); 
        duracion.setEditable(false); 
        duracion.setBounds(215,   504,   450,   30); 
        getContentPane().add(duracion); 
        
        exposicion = new JTextField(); 
        exposicion.setEditable(false); 
        exposicion.setBounds(215,   546,   450,   30); 
        getContentPane().add(exposicion); 
        
        JLabel lblNombre_1_1_1 = new JLabel("Duración:"); 
        lblNombre_1_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre_1_1_1.setBounds(33,   511,   89,   15); 
        getContentPane().add(lblNombre_1_1_1); 
        
        descripcion = new JTextField(); 
        descripcion.setEditable(false); 
        descripcion.setBounds(215,   588,   450,   30); 
        getContentPane().add(descripcion); 
        
        JLabel lblNombre_1_1_1_1 = new JLabel("Exposición:"); 
        lblNombre_1_1_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre_1_1_1_1.setBounds(34,   553,   103,   15); 
        getContentPane().add(lblNombre_1_1_1_1); 
        
        JLabel lblNombre_1_1_1_1_1 = new JLabel("Descripción:"); 
        lblNombre_1_1_1_1_1.setFont(new Font("Dialog",   Font.BOLD,   16)); 
        lblNombre_1_1_1_1_1.setBounds(33,   595,   117,   15); 
        getContentPane().add(lblNombre_1_1_1_1_1); 

        
	}
	
	public void actualizar() {
		Set<String> paquetes = ico.listarPaquetes(); 
	
    	for (String element1 : paquetes) {
    		listadoPaquetes.addItem(element1); 
    	}
	}
	
	public void actualizarTabla() {
		tableModel.setRowCount(0); 
		
		Set<DTCantTO> tiposPub = paqDT.getTiposDePub(); 
		
        tableModel.setRowCount(0); 

        // Agregar los datos del conjunto como filas en la tabla
        tableModel.addRow(new Object[]{"Tipo de publicación",  "Cantidad"}); 
        for (DTCantTO item : tiposPub) {
            tableModel.addRow(new Object[]{item.getNombre(),   item.getCantidad()}); 
        }
	}
	
	private void limpiarFormulario() {
        fechaDeAlta.setText("");  // muestro la fecha
		costo.setText("");  // muestro el costo
		duracion.setText("");  // muestro la duración
		exposicion.setText("");  // muestro la exposición
		descripcion.setText(""); 
		CostoPaquete.setText("");  // muestro el costo
		PaqueteDescuento.setText(""); 
		ValidezPaquete.setText(""); 
		Descripcion.setText(""); 
		tableModel.setRowCount(0); 
    }
    
}

	