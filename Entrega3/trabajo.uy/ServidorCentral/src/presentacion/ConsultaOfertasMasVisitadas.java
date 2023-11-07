package presentacion;

import excepciones.ExcepcionTipoOfertaNoExistente;
import excepciones.NoExistePaquete;
import logica.datatypes.DTCantTO;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTTipoOferta;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

import javax.swing.*;
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
        setBounds(30, 30, 688, 713);
        getContentPane().setLayout(null);

        //////////////////// BOTON CERRAR //////////////
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();  // cierra ventana
            }
        });
        btnCerrar.setBounds(548, 644, 117, 25);
        getContentPane().add(btnCerrar);
        //////////////////////////////////////////////

        
        
        // Definir los nombres de las columnas
        String[] columnNames = {"#", "Oferta Laboral", "Empresa", "Tipo de publicación", "Cantidad de visitas"};
        tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        
        table.setBounds(33, 67, 632, 260);
        getContentPane().add(table);

        // JScrollPane scrollPane = new JScrollPane(table); 

        JLabel labelTitulo = new JLabel("Ofertas Laborales mas visitadas en el sitio");
        labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        labelTitulo.setBounds(76, 26, 355, 15);
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
        

	    Integer indice = 1;
        for (DTOfertaExtendido oferta : cincoOfertasMasVisitadas) {
        	String tipoPub = ico.obtenerTipoPubOfertaLaboral(oferta.getNombre());
            tableModel.addRow(new Object[]{indice, oferta.getNombre(), oferta.getNicknameEmpresaPublicadora(), tipoPub, oferta.getCantVisitas()});
            indice = indice +1;
        }


}

	