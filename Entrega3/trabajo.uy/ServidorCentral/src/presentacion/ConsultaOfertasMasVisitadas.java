package presentacion;

//import excepciones.ExcepcionTipoOfertaNoExistente;
//import excepciones.NoExistePaquete;
import excepciones.OfertaLaboralNoEncontrada;
//import logica.datatypes.DTCantTO;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPaquete;
//import logica.datatypes.DTTipoOferta;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;


import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
//import javax.swing.JFrame;
import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
import javax.swing.JLabel;
//import javax.swing.JDialog;
//import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import javax.swing.JTextArea;
import javax.swing.SwingConstants;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.JPasswordField;
import javax.swing.JTable;
//import javax.swing.JList;
//import javax.swing.JSpinner;
import javax.swing.JRadioButton;
//import javax.swing.DefaultListModel;
//import javax.swing.ListSelectionModel;
//import javax.swing.JComponent;
//import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;


//import java.awt.*;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class ConsultaOfertasMasVisitadas extends JInternalFrame {
    private static DefaultTableModel tableModel;
    private JTable table;
    private ICtrlUsuario icUsuario;
    private ICtrlOferta ico;
    private DTPaquete paqDT;

    /**
     * Create the application.
     */
    public ConsultaOfertasMasVisitadas(ICtrlOferta ICO, ICtrlUsuario ICU) {
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
        setTitle("Ofertas laborales más visitadas");
        setBounds(30, 30, 715, 279);
        getContentPane().setLayout(null);

        //////////////////// BOTON CERRAR //////////////
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();  // cierra ventana
            }
        });
        btnCerrar.setBounds(569, 208, 117, 25);
        getContentPane().add(btnCerrar);
        //////////////////////////////////////////////

        
        
        // Definir los nombres de las columnas
        String[] columnNames = {"#", "Oferta Laboral", "Empresa", "Tipo de publicación", "Visitas"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        
        table.setBounds(10, 101, 676, 97);
        getContentPane().add(table);

        // JScrollPane scrollPane = new JScrollPane(table); 

        JLabel labelTitulo = new JLabel("Ofertas laborales más visitadas en el sitio");
        labelTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
        labelTitulo.setBounds(163, 22, 576, 15);
        getContentPane().add(labelTitulo);
        
        JLabel lblNewLabel = new JLabel("Seleccione el tipo de oferta: ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 69, 235, 14);
        getContentPane().add(lblNewLabel);
        
        JRadioButton btnVigentes = new JRadioButton("Vigentes");
        btnVigentes.setFont(new Font("Arial", Font.PLAIN, 14));
        btnVigentes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String op = "C";
                actualizarTabla(op);
        	}
        });
        btnVigentes.setBounds(234, 65, 82, 23);
        getContentPane().add(btnVigentes);
        
        JRadioButton btnFinalizadas = new JRadioButton("Vencidas");
        btnFinalizadas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnFinalizadas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String op = "F";
                actualizarTabla(op);
        	}
        });
        btnFinalizadas.setBounds(328, 65, 94, 23);
        getContentPane().add(btnFinalizadas);
        
        JRadioButton btnTodas = new JRadioButton("Todas");
        btnTodas.setFont(new Font("Arial", Font.PLAIN, 14));
        btnTodas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String op = "F";
                actualizarTabla(op);
        	}
        });
        btnTodas.setBounds(424, 65, 82, 23);
        getContentPane().add(btnTodas);


        ButtonGroup buttonGroup = new ButtonGroup();
  
        buttonGroup.add(btnVigentes);
        buttonGroup.add(btnFinalizadas);
        buttonGroup.add(btnTodas);
        



    }

    
    public void actualizarTabla(String op) {
    	
    	// Define un tamaño de fuente para la tabla
    	Font customFont = new Font("Arial", Font.PLAIN, 14);  // Puedes ajustar el tamaño y la fuente según tus preferencias

    	// Asigna la fuente personalizada a la tabla
    	table.setFont(customFont);
    	
        // Elimina todas las filas existentes en el modelo de la tabla
        tableModel.setRowCount(0);
        
        Set<DTOfertaExtendido> ofertas = null;
        
        if (op == "C") { 
        	ofertas = ico.listarOfertasLaboralesConfirmadasYNoVencidas();
        } else if (op == "F") {
        	Set<DTOfertaExtendido> of1 = ico.listarOfertasLaboralesConfirmadas();
        	Set<DTOfertaExtendido> of2 = ico.listarOfertasLaboralesConfirmadasYNoVencidas();
        	
        	// Crear una copia del conjunto of1 para preservar los datos originales
        	ofertas = new HashSet<>(of1);
        	ofertas.removeAll(of2);
        	
        } else {
        	ofertas = ico.listarOfertasLaboralesConfirmadas();
        }
        		

        // Convierte el Set a una lista para poder ordenarla
        List<DTOfertaExtendido> listaOfertas = ofertas.stream().collect(Collectors.toList());

	     // Ordena la lista de ofertas por la cantidad de visitas en orden descendente
	     listaOfertas.sort(Comparator.comparingInt(DTOfertaExtendido::getCantVisitas).reversed());
	
	     // Toma las primeras 5 ofertas de la lista ordenada (las 5 con mayor cantidad de visitas)
	     List<DTOfertaExtendido> cincoOfertasMasVisitadas = listaOfertas.stream().limit(5).collect(Collectors.toList());
        
	     tableModel.addRow(new Object[]{null, null, null, null, null});
	     Integer indice = 1;

	     DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
	     headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	     Font boldFont = new Font(table.getFont().getFontName(), Font.BOLD, table.getFont().getSize());
	        	
	     // AJUSTAR EL ANCHO DE LAS COLUMNAS 
	     table.getColumnModel().getColumn(0).setPreferredWidth(10); // Ancho de la columna del índice 
	     table.getColumnModel().getColumn(1).setPreferredWidth(170); // Ancho de la columna del nombre de la oferta
	     table.getColumnModel().getColumn(2).setPreferredWidth(110);
	     table.getColumnModel().getColumn(3).setPreferredWidth(120);
	     table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
	        
	     // CENTRAR COLUMNAS
	     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	     centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	     for (int i = 0; i < tableModel.getColumnCount(); i++) {
	    	 table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	     }
	       
	     // NEGRITA PARA EL TITULO DE LAS COLUMNAS
	     for (int i = 0; i < tableModel.getColumnCount(); i++) {
	    	 table.setValueAt("<html><b>" + tableModel.getColumnName(i) + "</b></html>", 0, i);
	         table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
	         table.getTableHeader().setFont(boldFont);
	     }
	        
	     // SE AGREGAN LAS OFERTAS LABORALES MAS VISITADAS
	     for (DTOfertaExtendido oferta : cincoOfertasMasVisitadas) {
	    	 String tipoPub;
	    	 try {
	    		 tipoPub = ico.obtenerTipoPubOfertaLaboral(oferta.getNombre());
		         tableModel.addRow(new Object[]{indice, oferta.getNombre(), oferta.getNicknameEmpresaPublicadora(), tipoPub, oferta.getCantVisitas()});
		         indice = indice +1;	
	    	 } catch (OfertaLaboralNoEncontrada e) {
	    		 // TODO Auto-generated catch block
	    		 e.printStackTrace();
	    	 }
	    }
    }
}

	