package presentacion;

import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.NoExistePaquete;
import excepciones.OfertaLaboralNoEncontrada;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTTipoOferta;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.Comparator;
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
        setTitle("Consulta de Paquete de Tipos de Publicación de Ofertas Laborales");
        setBounds(30, 30, 704, 337);
        getContentPane().setLayout(null);

        //////////////////// BOTON CERRAR //////////////
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();  // cierra ventana
            }
        });
        btnCerrar.setBounds(549, 271, 117, 25);
        getContentPane().add(btnCerrar);
        //////////////////////////////////////////////

        
        
        // Definir los nombres de las columnas
        String[] columnNames = {"#", "Oferta Laboral", "Empresa", "Tipo de publicación", "Cantidad de visitas"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        
        table.setBounds(33, 67, 632, 175);
        getContentPane().add(table);

        // JScrollPane scrollPane = new JScrollPane(table); 

        JLabel labelTitulo = new JLabel("Ofertas Laborales mas visitadas en el sitio");
        labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        labelTitulo.setBounds(153, 29, 576, 15);
        getContentPane().add(labelTitulo);
        
        
        
	     
	     
        actualizarTabla();


    }

    
    public void actualizarTabla() {
        // Elimina todas las filas existentes en el modelo de la tabla
        tableModel.setRowCount(0);
        
        Set<DTOfertaExtendido> ofertas = ico.listarOfertasLaboralesConfirmadas();

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
	        table.getColumnModel().getColumn(0).setPreferredWidth(15); // Ancho de la columna del índice 
	        table.getColumnModel().getColumn(1).setPreferredWidth(170); // Ancho de la columna del nombre de la oferta
	        table.getColumnModel().getColumn(2).setPreferredWidth(80);
	        table.getColumnModel().getColumn(3).setPreferredWidth(100);
	        
	        
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

	