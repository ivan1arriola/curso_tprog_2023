package presentacion;
import logica.Interfaces.*;
import logica.Clases.*;
import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;
import logica.Datatypes.DTOfertaExtendido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
//import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PostulacionOfertaLaboral extends JInternalFrame {
	//private ICtrlOferta controlOferta;
	private ICtrlUsuario controlUsuario;
	private HashSet<String> empresas;
	private JComboBox<String> comboBoxEmpresa;
	private JComboBox<String> comboBoxOfertas;
	private JTextArea infoOferta;
	
	public PostulacionOfertaLaboral(ICtrlOferta ico, ICtrlUsuario icu) {
		
  	//controlOferta = ico;
		controlUsuario = icu;
		
		empresas = icu.listarEmpresas();

        
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        getContentPane().setLayout(null);
        setTitle("Postulación a Oferta Laboral");
        setBounds(30, 30, 500, 280);
        
        
        JLabel eligeEmpresa = new JLabel("Elija empresa:");
        eligeEmpresa.setBounds(10, 0, 150, 40);
        JLabel labelOfertas = new JLabel("Ofertas disponibles:");
        labelOfertas.setBounds(10, 40, 150, 40);
        
        // Combo Empresas
        comboBoxEmpresa = new JComboBox<>();
        comboBoxEmpresa.setBounds(240, 5, 210, 30);
        comboBoxEmpresa.setEditable(false);
        comboBoxEmpresa.setVisible(true);
        
        /*for (String emp : empresas) {
            comboBoxEmpresa.addItem(emp);
        }
        comboBoxEmpresa.setVisible(true);*/
        
        
       comboBoxOfertas = new JComboBox<>(); 
        comboBoxOfertas.setBounds(240,40, 210, 30);
        
        
        comboBoxEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {
            		
            		            		
                    if (comboBoxEmpresa.getSelectedIndex() != -1 && comboBoxEmpresa.getSelectedIndex() != 0 ) {
                        String selectedEmpresa = (String) comboBoxEmpresa.getSelectedItem();
                        Set<String> ofertasEmpresa = icu.listarOfertasLaborales(selectedEmpresa);

                        comboBoxOfertas.removeAllItems(); // Limpiar el comboBoxOfertas
                     
                        if (ofertasEmpresa.isEmpty()) {


                            JOptionPane.showMessageDialog(null, "No hay ofertas de esta empresa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        } 
                        else {
                            for (String oferta : ofertasEmpresa) {
                                comboBoxOfertas.addItem(oferta);
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Error al obtener las ofertas laborales");
                }
                     
              };
      });
        
        //////////////
     
        JLabel verOferta = new JLabel("Ver Oferta");
        verOferta.setBounds(10, 135, 82, 30);
        JTextArea infoOferta = new JTextArea();
        infoOferta.setEditable(false);;
        //infoOferta.setBounds(252, 81, 198, 148);
        
        
        JScrollPane infoScrollPane = new JScrollPane(infoOferta);
        infoScrollPane.setBounds(150, 80, 300, 120);
        infoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        infoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    
        
        comboBoxOfertas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if(comboBoxOfertas.getSelectedIndex()!=-1) {
            		String oferta = (String) comboBoxOfertas.getSelectedItem();
             	    DTOfertaExtendido dtO = ico.obtenerOfertaLaboral(oferta);
             	    infoOferta.append(dtO.toString());
            	}
                
              };
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
        	public void actionPerformed(ActionEvent e) {
        		
        		if(comboBoxEmpresa.getSelectedIndex()!=-1 && comboBoxOfertas.getSelectedIndex()!=-1  &&  comboBoxEmpresa.getSelectedIndex()!=0) {
        			String emp = (String) comboBoxEmpresa.getSelectedItem();
        			String offer = (String) comboBoxOfertas.getSelectedItem();
        			ElegirPostulante eligePostu = new ElegirPostulante(emp,offer, icu,ico);
        			eligePostu.actualizar(emp, offer);
        			getContentPane().add(eligePostu);
        			eligePostu.setVisible(true);
        		}
        		
        		else {
        			JOptionPane.showMessageDialog(PostulacionOfertaLaboral.this,
                        "Elija empresa y oferta",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                    );}
        	}
        });
        btnNewButton.setBounds(32, 213, 221, 23);
        getContentPane().add(btnNewButton);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        	}
        });
        btnCerrar.setBounds(265, 212, 213, 24);
        getContentPane().add(btnCerrar);


	}
	
    
    
    private void limpiarFormulario() {
        infoOferta.setText("");
    };
	
    public void actualizar() {
        comboBoxEmpresa.removeAllItems(); 
        
        //comboBoxOfertas.removeAllItems(); 
        HashSet<String> empresas = controlUsuario.listarEmpresas();
        //infoOferta.setText("");
        comboBoxEmpresa.addItem(" ");
        for (String nickname : empresas) {
        	
            comboBoxEmpresa.addItem(nickname);

        }

        revalidate(); // Actualizar la interfaz gráfica
    }
} //
