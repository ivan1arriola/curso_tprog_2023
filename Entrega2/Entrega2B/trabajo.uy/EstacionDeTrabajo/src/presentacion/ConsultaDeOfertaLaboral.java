package presentacion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

import java.util.Set;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioNoEncontrado;
import logica.Fabrica;
import logica.datatypes.DTOfertaExtendido;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JComboBox;

public class ConsultaDeOfertaLaboral extends JInternalFrame {
	private ICtrlUsuario icUsuario; 
	private JTextField tfNombre;
	private JTextField tfDescripcion;
	private JTextField tfFechaDeAlta;
	private JTextField tfCosto;
	private JTextField tfRemuneracion;
	private JTextField tfHorario;
	private JTextField tfDepartamento;
	private JTextField tfCiudad;
	private JComboBox<String> listaEmpresas;
	private JComboBox<String> listaOfertasLaborales;
	private String nombreE;
	private String selectedUsuario;
	private VerPostulaciones verPostulacionesJInternalFrame;
	private JTextArea tAKey;
	private JTextField tfPaquete;
	
	/**
	 * Create the application.
	 */
	public ConsultaDeOfertaLaboral(JFrame gui,  ICtrlUsuario icu) { 
		icUsuario = icu;
		
		verPostulacionesJInternalFrame = new VerPostulaciones();
        verPostulacionesJInternalFrame.setSize(700,  300);
        verPostulacionesJInternalFrame.setLocation(38,  63);
        verPostulacionesJInternalFrame.setVisible(false);
        gui.getContentPane().add(verPostulacionesJInternalFrame);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Fabrica fab = Fabrica.getInstance();
		ICtrlUsuario icUsuario = fab.getICtrlUsuario();
		
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Oferta Laboral");
        setBounds(30,  30,  660,  716);
        getContentPane().setLayout(null);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		limpiarFormulario();
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(521,  647,  117,  25);
        getContentPane().add(btnCerrar);
        
        listaEmpresas = new JComboBox<String>();
        listaEmpresas.setBounds(186,  20,  455,  28);
        getContentPane().add(listaEmpresas);

        

        
        JLabel lblIngresoCI_1 = new JLabel("Lista de empresas:");
        lblIngresoCI_1.setBounds(12,  27,  170,  15);
        getContentPane().add(lblIngresoCI_1);
        
        listaOfertasLaborales = new JComboBox<String>();
        listaOfertasLaborales.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		
        		String selectedUsuario1 = (String) listaOfertasLaborales.getSelectedItem();
        		
        		if (selectedUsuario1 != null) {
        			selectedUsuario = selectedUsuario1;
        		}
        		DTOfertaExtendido infoConsOf = icUsuario.consultaOfertaLaboral(selectedUsuario);
        		tfNombre.setText(infoConsOf.getNombre());
        		tfDescripcion.setText(infoConsOf.getDescripcion());
        		
        		
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		String fechaComoString = infoConsOf.getFechaDeAlta().format(formatter);
        		tfFechaDeAlta.setText(fechaComoString);
        		
        		tfCosto.setText(String.valueOf(infoConsOf.getCosto()));
        		
        		tfRemuneracion.setText(String.valueOf(infoConsOf.getRemuneracion()));
        		
        		tfHorario.setText((infoConsOf.getHorario()).getDesde() + "-" + (infoConsOf.getHorario()).getHasta());
        		
        		tfDepartamento.setText((infoConsOf.getDepartamento()).name());
        		
        		tfCiudad.setText(infoConsOf.getCiudad());
        		String paq = infoConsOf.getPaquete();
        		
        		if (paq == null) {
        			tfPaquete.setText("No fue comprado con un paquete.");
        		}
        		else {
        			tfPaquete.setText(paq);
        		}
        		
        		Set<String> keywords = icUsuario.listarKeywords(selectedUsuario1);
        		
        		tAKey.setText("");
                Iterator<String> iterator = keywords.iterator();
                while (iterator.hasNext()) {
                	tAKey.append(iterator.next());
                	tAKey.append("\n");
                }
        		
        		

        	}
        });
        listaOfertasLaborales.setBounds(186,  107,  455,  28);
        getContentPane().add(listaOfertasLaborales);
        
        JButton btnAceptar_1 = new JButton("Desplegar Ofertas Laborales");
        btnAceptar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		listaOfertasLaborales.removeAllItems();
        		nombreE = (String) listaEmpresas.getSelectedItem();
        		try {
                    Set<String> ofertas_laborales = icUsuario.listarOfertasLaborales(nombreE);
                    ofertas_laborales.iterator();
                    if (!ofertas_laborales.isEmpty()) {
                    	for (String it : ofertas_laborales) {
                    		listaOfertasLaborales.addItem(it);
                    	}
                    } else {
                    	JOptionPane.showMessageDialog(ConsultaDeOfertaLaboral.this,  "La empresa seleccionada no tiene ofertas laborales.",  "ERROR - Consulta de Oferta Laboral",  JOptionPane.ERROR_MESSAGE);
                    }

        		} catch (IllegalArgumentException e1) {
        			JOptionPane.showMessageDialog(ConsultaDeOfertaLaboral.this,  e1.getMessage(),  "ERROR - Consulta de Oferta Laboral",  JOptionPane.ERROR_MESSAGE);
        		} catch (ExceptionEmpresaInvalida e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExceptionUsuarioNoEncontrado e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnAceptar_1.setBounds(12,  67,  629,  25);
        getContentPane().add(btnAceptar_1);
        
        JLabel lblIngresoCI_1_2 = new JLabel("Ofertas Laborales:");
        lblIngresoCI_1_2.setBounds(12,  114,  170,  15);
        getContentPane().add(lblIngresoCI_1_2);
        

        
        JLabel lblIngresoCI_1_2_1 = new JLabel("Nombre:");
        lblIngresoCI_1_2_1.setBounds(12,  156,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1);
        
        JLabel lblIngresoCI_1_2_1_1 = new JLabel("Descripción:");
        lblIngresoCI_1_2_1_1.setBounds(12,  199,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1);
        
        JLabel lblIngresoCI_1_2_1_1_1 = new JLabel("Fecha de alta:");
        lblIngresoCI_1_2_1_1_1.setBounds(12,  240,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1);
        
        tfNombre = new JTextField();
        tfNombre.setEditable(false);
        tfNombre.setBounds(186,  149,  455,  30);
        getContentPane().add(tfNombre);
        
        tfDescripcion = new JTextField();
        tfDescripcion.setEditable(false);
        tfDescripcion.setBounds(186,  192,  455,  30);
        getContentPane().add(tfDescripcion);
        
        tfFechaDeAlta = new JTextField();
        tfFechaDeAlta.setEditable(false);
        tfFechaDeAlta.setBounds(186,  233,  455,  30);
        getContentPane().add(tfFechaDeAlta);
        
        JLabel lblIngresoCI_1_2_1_1_1_1 = new JLabel("Costo:");
        lblIngresoCI_1_2_1_1_1_1.setBounds(12,  283,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1 = new JLabel("Remuneración:");
        lblIngresoCI_1_2_1_1_1_1_1.setBounds(12,  328,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1);
        
        tfCosto = new JTextField();
        tfCosto.setEditable(false);
        tfCosto.setBounds(186,  275,  455,  30);
        getContentPane().add(tfCosto);
        
        tfRemuneracion = new JTextField();
        tfRemuneracion.setEditable(false);
        tfRemuneracion.setBounds(186,  321,  455,  30);
        getContentPane().add(tfRemuneracion);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1_1 = new JLabel("Horario:");
        lblIngresoCI_1_2_1_1_1_1_1_1.setBounds(12,  378,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1);
        
        tfHorario = new JTextField();
        tfHorario.setEditable(false);
        tfHorario.setBounds(186,  371,  455,  30);
        getContentPane().add(tfHorario);
        
        tfDepartamento = new JTextField();
        tfDepartamento.setEditable(false);
        tfDepartamento.setBounds(186,  413,  455,  30);
        getContentPane().add(tfDepartamento);
        
        tfCiudad = new JTextField();
        tfCiudad.setEditable(false);
        tfCiudad.setBounds(186,  455,  455,  30);
        getContentPane().add(tfCiudad);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1 = new JLabel("Departamento:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1.setBounds(12,  420,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1 = new JLabel("Ciudad:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1.setBounds(12,  462,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1_1);
        
        JButton btnVerPostulaciones = new JButton("Ver postulaciones");
        btnVerPostulaciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		verPostulacionesJInternalFrame.actualizar(selectedUsuario);
        		verPostulacionesJInternalFrame.setVisible(true);
        		
        	}
        });
        btnVerPostulaciones.setBounds(12,  610,  626,  25);
        getContentPane().add(btnVerPostulaciones);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1 = new JLabel("Keywords:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1.setBounds(12,  560,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1);
        
        tAKey = new JTextArea();
        tAKey.setBounds(186,  537,  455,  61);
        getContentPane().add(tAKey);
        tAKey.setEditable(false);
        
        tfPaquete = new JTextField();
        tfPaquete.setEditable(false);
        tfPaquete.setBounds(186,  497,  455,  30);
        getContentPane().add(tfPaquete);
        
        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1_2 = new JLabel("Paquete:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1_2.setBounds(12,  504,  170,  15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1_1_2);

        
	}
	
	private void limpiarFormulario() {
		tfNombre.setText("");
		tfDescripcion.setText("");
		tfFechaDeAlta.setText("");
		tfCosto.setText("");
		tfRemuneracion.setText("");
		tfHorario.setText("");
		tfDepartamento.setText("");
		tfCiudad.setText("");
		tAKey.setText("");
	}
	
	public void actualizar() {
		listaEmpresas.removeAllItems();
        Set<String> empresas = icUsuario.listarEmpresas();
        for (String elemento : empresas) {
        	listaEmpresas.addItem(elemento);
        }     				
	}
}
