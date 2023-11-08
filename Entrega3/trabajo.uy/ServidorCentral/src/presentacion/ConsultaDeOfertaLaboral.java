package presentacion;


import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioNoEncontrado;
import excepciones.OfertaLaboralNoEncontrada;
import logica.Fabrica;
import logica.datatypes.DTOfertaExtendido;
import logica.interfaces.ICtrlUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public ConsultaDeOfertaLaboral(JFrame gui, ICtrlUsuario icu) {
        icUsuario = icu;

        verPostulacionesJInternalFrame = new VerPostulaciones();
        verPostulacionesJInternalFrame.setSize(700, 300);
        verPostulacionesJInternalFrame.setLocation(38, 63);
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
        setBounds(30, 30, 620, 650);
        getContentPane().setLayout(null);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(466, 584, 117, 25);
        getContentPane().add(btnCerrar);

        listaEmpresas = new JComboBox<String>();
        listaEmpresas.setBounds(183, 6, 400, 28);
        getContentPane().add(listaEmpresas);
        
        listaEmpresas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            	listaOfertasLaborales.removeAllItems();
            	limpiarFormulario();
    
            }
        });
        
        
        
        


        JLabel lblIngresoCI_1 = new JLabel("Lista de empresas:");
        lblIngresoCI_1.setBounds(12, 11, 170, 15);
        getContentPane().add(lblIngresoCI_1);
        
        JButton btnAceptar_1 = new JButton("Desplegar Ofertas Laborales");
        btnAceptar_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            
                listaOfertasLaborales.removeAllItems();
                nombreE = (String) listaEmpresas.getSelectedItem();
            
                try {
                	       	
                    Set<String> ofertas_laborales = icUsuario.listarOfertasLaborales(nombreE);
                    ofertas_laborales.iterator();
                    if (!ofertas_laborales.isEmpty()) {
                    	//listaOfertasLaborales.addItem("");
                        for (String it : ofertas_laborales) {
                            listaOfertasLaborales.addItem(it);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ConsultaDeOfertaLaboral.this, "La empresa seleccionada no tiene ofertas laborales.", "ERROR - Consulta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(ConsultaDeOfertaLaboral.this, e1.getMessage(), "ERROR - Consulta de Oferta Laboral", JOptionPane.ERROR_MESSAGE);
                } catch (ExceptionEmpresaInvalida e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExceptionUsuarioNoEncontrado e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
            }
        });
        
        btnAceptar_1.setBounds(133, 40, 450, 25);
        getContentPane().add(btnAceptar_1);
        

        listaOfertasLaborales = new JComboBox<String>();
        listaOfertasLaborales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

                String selectedUsuario1 = (String) listaOfertasLaborales.getSelectedItem();
                
                //if (listaOfertasLaborales.getSelectedIndex()!=0 && listaOfertasLaborales.getSelectedIndex()!=-1) {
                if (listaOfertasLaborales.getSelectedIndex()!=-1) {
	                if (selectedUsuario1 != null) {
	                    selectedUsuario = selectedUsuario1;
	                }
	                DTOfertaExtendido infoConsOf = null;
	                
	               
	                try {
	                	infoConsOf = icUsuario.consultaOfertaLaboral(selectedUsuario);
	                } catch (OfertaLaboralNoEncontrada e) {
	                    throw new RuntimeException(e);
	                }
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
	                } else {
	                    tfPaquete.setText(paq);
	                }
	
	                Set<String> keywords = null;
	                try {
	                    keywords = icUsuario.listarKeywords(selectedUsuario1);
	                } catch (OfertaLaboralNoEncontrada e) {
	                    throw new RuntimeException(e);
	                }
	
	                tAKey.setText("");
	                Iterator<String> iterator = keywords.iterator();
	                while (iterator.hasNext()) {
	                    tAKey.append(iterator.next());
	                    tAKey.append("\n");
	                }
                }

            }
        });
        listaOfertasLaborales.setBounds(183, 73, 400, 28);
        getContentPane().add(listaOfertasLaborales);

        JLabel lblIngresoCI_1_2 = new JLabel("Ofertas Laborales:");
        lblIngresoCI_1_2.setBounds(12, 80, 170, 15);
        getContentPane().add(lblIngresoCI_1_2);

        JLabel lblIngresoCI_1_2_1 = new JLabel("Nombre:");
        lblIngresoCI_1_2_1.setBounds(12, 118, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1);

        JLabel lblIngresoCI_1_2_1_1 = new JLabel("Descripción:");
        lblIngresoCI_1_2_1_1.setBounds(12, 156, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1);

        JLabel lblIngresoCI_1_2_1_1_1 = new JLabel("Fecha de alta:");
        lblIngresoCI_1_2_1_1_1.setBounds(12, 190, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1);

        tfNombre = new JTextField();
        tfNombre.setEditable(false);
        tfNombre.setBounds(183, 110, 400, 25);
        getContentPane().add(tfNombre);

        tfDescripcion = new JTextField();
        tfDescripcion.setEditable(false);
        tfDescripcion.setBounds(183, 148, 400, 25);
        getContentPane().add(tfDescripcion);

        tfFechaDeAlta = new JTextField();
        tfFechaDeAlta.setEditable(false);
        tfFechaDeAlta.setBounds(183, 185, 400, 25);
        getContentPane().add(tfFechaDeAlta);

        JLabel lblIngresoCI_1_2_1_1_1_1 = new JLabel("Costo:");
        lblIngresoCI_1_2_1_1_1_1.setBounds(12, 231, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1);

        JLabel lblIngresoCI_1_2_1_1_1_1_1 = new JLabel("Remuneración:");
        lblIngresoCI_1_2_1_1_1_1_1.setBounds(12, 269, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1);

        tfCosto = new JTextField();
        tfCosto.setEditable(false);
        tfCosto.setBounds(183, 223, 400, 25);
        getContentPane().add(tfCosto);

        tfRemuneracion = new JTextField();
        tfRemuneracion.setEditable(false);
        tfRemuneracion.setBounds(183, 261, 400, 25);
        getContentPane().add(tfRemuneracion);

        JLabel lblIngresoCI_1_2_1_1_1_1_1_1 = new JLabel("Horario:");
        lblIngresoCI_1_2_1_1_1_1_1_1.setBounds(12, 310, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1);

        tfHorario = new JTextField();
        tfHorario.setEditable(false);
        tfHorario.setBounds(183, 302, 400, 25);
        getContentPane().add(tfHorario);

        tfDepartamento = new JTextField();
        tfDepartamento.setEditable(false);
        tfDepartamento.setBounds(183, 343, 400, 25);
        getContentPane().add(tfDepartamento);

        tfCiudad = new JTextField();
        tfCiudad.setEditable(false);
        tfCiudad.setBounds(183, 384, 400, 25);
        getContentPane().add(tfCiudad);

        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1 = new JLabel("Departamento:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1.setBounds(12, 351, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1);

        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1 = new JLabel("Ciudad:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1.setBounds(12, 394, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1_1);

        JButton btnVerPostulaciones = new JButton("Ver postulaciones");
        btnVerPostulaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                try {
                    verPostulacionesJInternalFrame.actualizar(selectedUsuario);
                } catch (OfertaLaboralNoEncontrada e) {
                    throw new RuntimeException(e);
                }
                verPostulacionesJInternalFrame.setVisible(true);

            }
        });
        btnVerPostulaciones.setBounds(133, 551, 450, 25);
        getContentPane().add(btnVerPostulaciones);

        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1 = new JLabel("Keywords:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1.setBounds(12, 485, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1_1_1_1_1_1);

        tAKey = new JTextArea();
        tAKey.setBounds(183, 480, 400, 60);
        getContentPane().add(tAKey);
        tAKey.setEditable(false);

        tfPaquete = new JTextField();
        tfPaquete.setEditable(false);
        tfPaquete.setBounds(183, 432, 400, 25);
        getContentPane().add(tfPaquete);

        JLabel lblIngresoCI_1_2_1_1_1_1_1_1_1_1_2 = new JLabel("Paquete:");
        lblIngresoCI_1_2_1_1_1_1_1_1_1_1_2.setBounds(12, 432, 170, 15);
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
        tfPaquete.setText("");
    }

    public void actualizar() {
        listaEmpresas.removeAllItems();
        listaEmpresas.addItem("");
        Set<String> empresasUnsort = icUsuario.listarEmpresas();
        List<String> empresas = new ArrayList<>(empresasUnsort);
        Collections.sort(empresas, String.CASE_INSENSITIVE_ORDER);
        
        for (String elemento : empresas) {
            listaEmpresas.addItem(elemento);
        }
    }
}
