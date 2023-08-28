package presentacion;

import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.UsuarioNoExisteException;
import logica.Interfaces.ICtrlUsuario;
import logica.Interfaces.ICtrlOferta;
import logica.Fabrica;
import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTHorario;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;
import logica.Datatypes.DTHora;
import logica.Enumerados.DepUY;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;




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
	private JTextField fechaAlta_1;
	private JComboBox<String> listadoEmpresas;
	private JComboBox<String> listadoOfertas;
	private JComboBox<String> listadoKeywords;
	private JComboBox<String> listadoDepartamentos;
	private ICtrlUsuario ICU;
    private JTextArea descripcion;
    private String empresa;
    private String ofertaLab;
	private ICtrlOferta ICO;
	private JSpinner desdehora;
	private JSpinner desdemin;
	private JSpinner hastahora;
	private JSpinner hastamin;
	private List<String> ks;
	private String dep;
    /**
     * Create the frame.
     */
    public AltaOfertaLaboral(ICtrlUsuario icu) {
    	Fabrica fabrica = Fabrica.getInstance();
        ICtrlOferta ico = fabrica.getICtrlOferta();
        ICU = icu;
        ICO = ico;
    	// Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Oferta Laboral");
        setBounds(30, 30, 564, 505);
        
        
        
        
        // Absolute layout
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Listado Empresas");
        lblNewLabel.setBounds(21, 31, 191, 14);
        getContentPane().add(lblNewLabel);
        
        table = new JTable();
        table.setBounds(67, 45, 1, 1);
        getContentPane().add(table);
        
        listadoKeywords = new JComboBox<String>();
        listadoKeywords.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String k = (String) listadoKeywords.getSelectedItem();
        		ks.add(k);
        		// listadoKeywords.removeItem(k);
        	}
        });
        listadoKeywords.setBounds(134, 374, 389, 22);
        getContentPane().add(listadoKeywords);
        
        listadoEmpresas = new JComboBox<String>();
        listadoEmpresas.setBounds(270, 27, 253, 22);
        getContentPane().add(listadoEmpresas);
        
        listadoEmpresas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		empresa = (String) listadoEmpresas.getSelectedItem();
        	}
        });
        
        JLabel lblNewLabel_1 = new JLabel("Listado de Tipo de Oferta");
        lblNewLabel_1.setBounds(21, 64, 191, 18);
        getContentPane().add(lblNewLabel_1);
        
        listadoOfertas = new JComboBox<String>();
        listadoOfertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		ofertaLab = (String) listadoOfertas.getSelectedItem();
        	}
        });
        listadoOfertas.setBounds(270, 60, 253, 22);
        getContentPane().add(listadoOfertas);
        
        
        
        JLabel lblNewLabel_2 = new JLabel("Nombre");
        lblNewLabel_2.setBounds(22, 115, 138, 14);
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Descripcion");
        lblNewLabel_3.setBounds(21, 155, 139, 14);
        getContentPane().add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Desde ");
        lblNewLabel_4.setBounds(22, 215, 98, 14);
        getContentPane().add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Hasta");
        lblNewLabel_5.setBounds(299, 215, 46, 14);
        getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Remuneracion");
        lblNewLabel_6.setBounds(21, 255, 177, 14);
        getContentPane().add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Departamento");
        lblNewLabel_7.setBounds(21, 300, 139, 14);
        getContentPane().add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Ciudad");
        lblNewLabel_8.setBounds(345, 300, 65, 14);
        getContentPane().add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Fecha de Alta");
        lblNewLabel_9.setBounds(21, 341, 177, 14);
        getContentPane().add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Keywords");
        lblNewLabel_10.setBounds(21, 378, 139, 14);
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
        		DTHora desde = new DTHora((int) desdehora.getValue(),(int) desdemin.getValue());
        		DTHora hasta = new DTHora((int) hastahora.getValue(),(int) hastamin.getValue());
        		DTHorario horario = new DTHorario(desde,hasta);
        		
                DepUY departamento = null;
            	
                switch (dep) {
                    case "Artigas":
                        departamento = DepUY.Artigas;
                        break;
                    case "Salto":
                    	departamento = DepUY.Salto;
                        break;
                    case "Paysandú":
                    	departamento = DepUY.Paysandú;
                        break;
                    case "Rionegro":
                    	departamento = DepUY.RioNegro;
                        break;
                    case "Soriano":
                    	departamento = DepUY.Soriano;
                        break;
                    case "Colonia":
                    	departamento = DepUY.Colonia;
                        break;
                    case "Rivera":
                    	departamento = DepUY.Rivera;
                        break;
                    case "Tacuarembo":
                    	departamento = DepUY.Tacuarembo;
                        break;
                    case "Durazno":
                    	departamento = DepUY.Durazno;
                        break;
                    case "Flores":
                    	departamento = DepUY.Flores;
                        break;
                    case "Florida":
                    	departamento = DepUY.Florida;
                        break;
                    case "Sanjosé":
                    	departamento = DepUY.SanJosé;
                        break;
                    case "Canelones":
                    	departamento = DepUY.Canelones;
                        break;
                    case "Montevideo":
                    	departamento = DepUY.Montevideo;
                        break;
                    case "Cerrolargo":
                    	departamento = DepUY.CerroLargo;
                        break;
                    case "Treintaytres":
                    	departamento = DepUY.TreintaYTres;
                        break;
                    case "Lavalleja":
                    	departamento = DepUY.Lavalleja;
                        break;
                    case "Rocha":
                    	departamento = DepUY.Rocha;
                        break;
                    case "Maldonado":
                    	departamento = DepUY.Maldonado;
                        break;
                    default:
                        // System.out.println("Unknown department: " + input);
                        break;
                }
        		
        		try {
        			boolean b = ICU.altaOfertaLaboral(empresa, ofertaLab, nombre.getText(), descripcion.getText(), horario, Float.parseFloat(remuneracion.getText()), ciudad.getText(), departamento, LocalDate.now(),ks);
        			if(!b) {
        				JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "Ya existe una oferta laboral con el nombre indicado.", "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
        			}else {
        				JOptionPane.showMessageDialog(AltaOfertaLaboral.this, "La oferta laboral se dio de alta exitosamente", "Alta Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);
        				limpiarFormulario();
        			}
        		} catch (ExceptionUsuarioNoEncontrado | ExceptionEmpresaInvalida e1) {
        			JOptionPane.showMessageDialog(AltaOfertaLaboral.this, e1.getMessage(), "ERROR - Alta Oferta Laboral", JOptionPane.ERROR_MESSAGE);
        		}
        		
        		
        	}
        });
        
        btnAceptar.setBounds(218, 424, 89, 23);
        getContentPane().add(btnAceptar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(434, 424, 89, 23);
        getContentPane().add(btnCerrar);
        
        nombre = new JTextField();
        nombre.setBounds(205, 113, 318, 20);
        getContentPane().add(nombre);
        nombre.setColumns(10);
        
        descripcion = new JTextArea();
        descripcion.setBounds(206, 145, 317, 54);
        getContentPane().add(descripcion);
        
        listadoDepartamentos = new JComboBox<String>();
        listadoDepartamentos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dep = (String) listadoDepartamentos.getSelectedItem();
        	}
        });
        listadoDepartamentos.setBounds(141, 296, 191, 22);
        getContentPane().add(listadoDepartamentos);
        
        listadoDepartamentos.addItem("Artigas");
        listadoDepartamentos.addItem("Salto");
        listadoDepartamentos.addItem("Paysandú");
        listadoDepartamentos.addItem("RioNegro");
        listadoDepartamentos.addItem("Soriano");
        listadoDepartamentos.addItem("Colonia");
        listadoDepartamentos.addItem("Rivera");
        listadoDepartamentos.addItem("Tacuarembo");
        listadoDepartamentos.addItem("Durazno");
        listadoDepartamentos.addItem("Flores");
        listadoDepartamentos.addItem("Florida");
        listadoDepartamentos.addItem("SanJosé");
        listadoDepartamentos.addItem("Canelones");
        listadoDepartamentos.addItem("Montevideo");
        listadoDepartamentos.addItem("CerroLargo");
        listadoDepartamentos.addItem("TreintaYTres");
        listadoDepartamentos.addItem("Lavalleja");
        listadoDepartamentos.addItem("Rocha");
        listadoDepartamentos.addItem("Maldonado");
        
        ciudad = new JTextField();
        ciudad.setBounds(414, 298, 109, 20);
        getContentPane().add(ciudad);
        ciudad.setColumns(10);
        
        remuneracion = new JTextField();
        remuneracion.setBounds(141, 253, 382, 20);
        getContentPane().add(remuneracion);
        remuneracion.setColumns(10);
        
        JList list = new JList();
        list.setBounds(172, 366, 51, -25);
        getContentPane().add(list);
       
        
        fechaAlta_1 = new JTextField();
        fechaAlta_1.setBounds(134, 339, 389, 20);
        getContentPane().add(fechaAlta_1);
        fechaAlta_1.setColumns(10);
        
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
		fechaAlta_1.setText(currentDate.format(dateFormatter));
		fechaAlta_1.setEditable(false);
		
		desdehora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		desdehora.setBounds(114, 213, 46, 20);
		getContentPane().add(desdehora);
		
		desdemin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		desdemin.setBounds(166, 213, 46, 20);
		getContentPane().add(desdemin);
		
		hastahora = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		hastahora.setBounds(383, 213, 46, 20);
		getContentPane().add(hastahora);
		
		hastamin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		hastamin.setBounds(434, 213, 46, 20);
		getContentPane().add(hastamin);
    }
    
    public void actualizar() {
    	ks = new ArrayList<>();
    	
		listadoEmpresas.removeAllItems();
		listadoKeywords.removeAllItems();
        HashSet<String> empresas = ICU.listarEmpresas();
        HashSet<String> keys = ICO.listarKeywords();
        
        // listadoKeywords.addItem("");
        for(String elemento1 : keys) {
        	listadoKeywords.addItem(elemento1);
        }
        
        listadoEmpresas.addItem("");
        for(String elemento : empresas) {
        	listadoEmpresas.addItem(elemento);
        }
        
        HashSet<String> tiposDePub = ICO.listarTipoDePublicaciones();
		listadoOfertas.removeAllItems();
		for (String elemento : tiposDePub) {
			listadoOfertas.addItem(elemento);
		}
	}
    
    private void limpiarFormulario() {
    	nombre.setText("");
    	descripcion.setText("");
        remuneracion.setText("");
        ciudad.setText("");
        fechaAlta_1.setText("");
    }
}