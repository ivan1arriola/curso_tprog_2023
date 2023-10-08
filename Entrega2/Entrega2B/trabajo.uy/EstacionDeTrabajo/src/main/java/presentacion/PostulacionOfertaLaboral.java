package main.java.presentacion;

import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
//import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PostulacionOfertaLaboral extends JInternalFrame {
	//private ICtrlOferta controlOferta;
	private ICtrlUsuario controlUsuario;
	private Set<String> empresas;
	private JComboBox<String> comboBoxEmpresa;
	private JComboBox<String> comboBoxOfertas;
	private JTextArea infoOferta;
	
	public PostulacionOfertaLaboral(ICtrlOferta ico,  ICtrlUsuario icUsuario) {
		
  	//controlOferta = ico;
		controlUsuario = icUsuario;
		
		empresas = icUsuario.listarEmpresas();

        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Postulación a Oferta Laboral");
        setBounds(30,  30,  500,  280);
        
        
        JLabel eligeEmpresa = new JLabel("Elija empresa:");
        eligeEmpresa.setBounds(10,  0,  150,  40);
        JLabel labelOfertas = new JLabel("Ofertas disponibles:");
        labelOfertas.setBounds(10,  40,  150,  40);
        
        // Combo Empresas
        comboBoxEmpresa = new JComboBox<>();
        comboBoxEmpresa.setBounds(240,  5,  210,  30);
        comboBoxEmpresa.setEditable(false);
        comboBoxEmpresa.setVisible(true);
        
        
        
       comboBoxOfertas = new JComboBox<>(); 
       comboBoxOfertas.setBounds(240, 40,  210,  30);
        
        
        comboBoxEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            	
            	try {
            		
            		            		
                    if (comboBoxEmpresa.getSelectedIndex() != -1 && comboBoxEmpresa.getSelectedIndex() != 0) {
                        String selectedEmpresa = (String) comboBoxEmpresa.getSelectedItem();
                        Set<String> ofertasEmpresa = icUsuario.listarOfertasLaborales(selectedEmpresa);

                        comboBoxOfertas.removeAllItems(); // Limpiar el comboBoxOfertas
                     
                        if (ofertasEmpresa.isEmpty()) {


                            JOptionPane.showMessageDialog(null,  "No hay ofertas de esta empresa",  "Mensaje",  JOptionPane.INFORMATION_MESSAGE);
                        } 
                        else {
                        	List<String> ofertasSorted = new ArrayList<>(ofertasEmpresa);
                            Collections.sort(ofertasSorted,  String.CASE_INSENSITIVE_ORDER);
                        	
                            for (String oferta : ofertasSorted) {
                                comboBoxOfertas.addItem(oferta);
                            }
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    System.err.println("Error al obtener las ofertas laborales");
                }
                     
              }
      });
        
        //////////////
     
        JLabel verOferta = new JLabel("Ver Oferta");
        verOferta.setBounds(10,  135,  82,  30);
        JTextArea infoOferta = new JTextArea();
        infoOferta.setEditable(false);;
        //infoOferta.setBounds(252,  81,  198,  148);
        
        
        JScrollPane infoScrollPane = new JScrollPane(infoOferta);
        infoScrollPane.setBounds(150,  80,  300,  120);
        infoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        comboBoxOfertas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            	
            	if (comboBoxOfertas.getSelectedIndex() != -1) {
            		String oferta = (String) comboBoxOfertas.getSelectedItem();
             	    DTOfertaExtendido dtO = ico.obtenerOfertaLaboral(oferta);
             	    infoOferta.append(dtO.toString());
            	}
                
              }
      });
        
        
        eligeEmpresa.setVisible(true);
        getContentPane().add(eligeEmpresa);
        comboBoxEmpresa.setVisible(true);
        getContentPane().add(comboBoxEmpresa);
        labelOfertas.setVisible(true);
        getContentPane().add(labelOfertas);
        comboBoxOfertas.setVisible(true);
        getContentPane().add(comboBoxOfertas);
        infoScrollPane.setVisible(true);
        getContentPane().add(infoScrollPane);
        verOferta.setVisible(true);
        getContentPane().add(verOferta);
        
        JButton btnNewButton = new JButton("Elegir Postulante");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		
        		if (comboBoxEmpresa.getSelectedIndex() != -1 && comboBoxOfertas.getSelectedIndex() != -1  &&  comboBoxEmpresa.getSelectedIndex() != 0) {
        			String emp = (String) comboBoxEmpresa.getSelectedItem();
        			String offer = (String) comboBoxOfertas.getSelectedItem();
        			ElegirPostulante eligePostu = new ElegirPostulante(emp, offer,  icUsuario, ico);
        			eligePostu.actualizar(emp,  offer);
        			getContentPane().add(eligePostu);
        			eligePostu.setVisible(true);
        		}
        		
        		else {
        			JOptionPane.showMessageDialog(PostulacionOfertaLaboral.this, 
                        "Elija empresa y oferta", 
                        "Información", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
        		}
        	}
        });
        btnNewButton.setBounds(32,  213,  221,  23);
        getContentPane().add(btnNewButton);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(265,  212,  213,  24);
        getContentPane().add(btnCerrar);


	}
	
    
    
    private void limpiarFormulario() {
        infoOferta.setText("");
    }
	
    public void actualizar() {
        comboBoxEmpresa.removeAllItems(); 
        
        //comboBoxOfertas.removeAllItems(); 
        Set<String> empresas = controlUsuario.listarEmpresas();
        List<String> empresasSorted = new ArrayList<>(empresas);
        Collections.sort(empresasSorted,  String.CASE_INSENSITIVE_ORDER);
        comboBoxEmpresa.addItem(" ");
        for (String nickname : empresasSorted) {
        	
            comboBoxEmpresa.addItem(nickname);

        }

        revalidate(); // Actualizar la interfaz gráfica
    }
} //
