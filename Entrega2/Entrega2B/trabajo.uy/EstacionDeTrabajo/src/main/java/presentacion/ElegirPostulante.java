package main.java.presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

import java.time.LocalDate;   
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



//public class ElegirPostulante extends JInternalFrame {
public class ElegirPostulante extends JDialog {

	private ICtrlUsuario controlUsr;
	private JLabel LblcvRed;
    private JLabel Lblmotiva;
    private JLabel LblFecha;
    private JLabel Postulante;
    private JTextField dateTextField;
    private DateTimeFormatter dateFormatter;
    private JComboBox<String> cbEmpresa;
    private JComboBox<String> cbOferta;
    private JComboBox<String> cbPostula;
    private JTextArea cvred;
    private JTextArea motiva;
    private JTextField textField_1;
    private Set<String> postulantes;
    private Set<String> empresas;
    private Set<String> ofertas;
    
    public ElegirPostulante(String empresa, String offer,  ICtrlUsuario icu,  ICtrlOferta ico) {
    	controlUsr = icu;
    	postulantes = icu.obtenerNicknamesPostulantes();
    	
    	
      	setResizable(true);
        //setIconifiable(true);
        //setMaximizable(true);
        // setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Crear Postulación");
        setBounds(30,  30,  500,  400);
        
        
        GridBagLayout gbl=new GridBagLayout();
        gbl.columnWeights = new double[]{0.0,  1.0,  0.0};
        GridBagConstraints gbc=new GridBagConstraints();
      
      	getContentPane().setLayout(gbl);
      	
      	JLabel lblEmpre = new JLabel("Empresa");
      	lblEmpre.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblEmpre = new GridBagConstraints();
      	gbc_lblEmpre.anchor = GridBagConstraints.WEST;
      	gbc_lblEmpre.insets = new Insets(0,  15,  5,  5);
      	gbc_lblEmpre.gridx = 0;
      	gbc_lblEmpre.gridy = 0;
      	getContentPane().add(lblEmpre,  gbc_lblEmpre);
      	
      	//COMBO BOX EMPRESA
      	cbEmpresa = new JComboBox<>();
      	cbEmpresa.setEnabled(false);
      	cbEmpresa.setEditable(false);
      	
      	cbEmpresa.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent evento) {

      			cbOferta.removeAllItems();
      			
      				try {
	                if (cbEmpresa.getSelectedIndex() != -1 && cbEmpresa.getSelectedIndex() != 0) {
	                    String selectedEmpresa = (String) cbEmpresa.getSelectedItem();
	                    Set<String> ofertasEmpresa = icu.listarOfertasLaborales(selectedEmpresa);
	                    cbOferta.removeAllItems(); // Limpiar el comboBoxOfertas
	                 
	                    if (ofertasEmpresa.isEmpty()) {
	
	                        JOptionPane.showMessageDialog(ElegirPostulante.this,  "No hay ofertas de esta empresa",  "Mensaje",  JOptionPane.INFORMATION_MESSAGE);
	                    } 
	                    else {
	                        for (String oferta : ofertasEmpresa) {
	                            cbOferta.addItem(oferta);
	                        }
	                    }
	                }
	                
      				} catch (Exception exc) {
      					System.err.println("Error al obtener las ofertas laborales"); } 
	                
	                
      		}
      	});     
      	
      	
      	
      	GridBagConstraints gbc_cbEmpresa = new GridBagConstraints();
      	gbc_cbEmpresa.insets = new Insets(5,  0,  5,  5);
      	gbc_cbEmpresa.gridx = 1;
      	gbc_cbEmpresa.gridy = 0;
      	gbc_cbEmpresa.ipadx = 180;
      	getContentPane().add(cbEmpresa,  gbc_cbEmpresa);
      	
 
      	JLabel lblNewLabel = new JLabel("Oferta");
      	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
      	gbc_lblNewLabel.insets = new Insets(0,  15,  5,  5);
      	gbc_lblNewLabel.gridx = 0;
      	gbc_lblNewLabel.gridy = 1;
      	getContentPane().add(lblNewLabel,  gbc_lblNewLabel);
      	
      	//COMBOBOX OFERTA
      	cbOferta = new JComboBox<>();
      	cbOferta.setEditable(false);
      	cbOferta.setEnabled(false);
      	
      	GridBagConstraints gbc_cbOferta = new GridBagConstraints();
      	gbc_cbOferta.insets = new Insets(5,  0,  5,  5);
      	gbc_cbOferta.gridx = 1;
      	gbc_cbOferta.gridy = 1;
      	gbc_cbOferta.ipadx=180;
      	getContentPane().add(cbOferta,  gbc_cbOferta);
      	
       
        /////////
     
        Postulante = new JLabel("Postulante");
        gbl.setConstraints(Postulante,  gbc);
        GridBagConstraints gbc_Postulante = new GridBagConstraints();
        gbc_Postulante.anchor = GridBagConstraints.WEST;
        gbc_Postulante.insets = new Insets(5,  15,  5,  5);
        gbc_Postulante.gridx = 0;
        gbc_Postulante.gridy = 2;
        getContentPane().add(Postulante,  gbc_Postulante);
        
        
        //COMBOBOX POSTULANTE
        cbPostula = new JComboBox<String>();

    	
        /*cbPostula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedUsuario = (String) cbPostula.getSelectedItem();
                
            };
        });*/


        
        gbl.setConstraints(cbPostula,  gbc);
        GridBagConstraints gbc_cbPostula = new GridBagConstraints();
        gbc_cbPostula.ipadx = 180;
        gbc_cbPostula.insets = new Insets(5,  0,  5,  5);
        gbc_cbPostula.gridx = 1;
        gbc_cbPostula.gridy = 2;
        getContentPane().add(cbPostula, gbc_cbPostula);
        

        ////
        LblcvRed = new JLabel("CV Reducido");
        LblcvRed.setHorizontalAlignment(SwingConstants.CENTER);
        gbl.setConstraints(LblcvRed,  gbc);
        GridBagConstraints gbc_LblcvRed = new GridBagConstraints();
        gbc_LblcvRed.anchor = GridBagConstraints.WEST;
        gbc_LblcvRed.insets = new Insets(0,  15,  5,  5);
        gbc_LblcvRed.gridx = 0;
        gbc_LblcvRed.gridy = 3;
        getContentPane().add(LblcvRed,  gbc_LblcvRed);
        /////
        
  
        cvred = new JTextArea();
        JScrollPane scroll1 = new JScrollPane(cvred);
        scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //gbl.setConstraints(cvred,  gbc);
        
        GridBagConstraints gbc_cvred = new GridBagConstraints();
        gbc_cvred.ipadx = 250;
        gbc_cvred.ipady = 50;
        gbc_cvred.insets = new Insets(0,  0,  5,  5);
        gbc_cvred.gridx = 1;
        gbc_cvred.gridy = 3;
        getContentPane().add(scroll1,  gbc_cvred);
        
        JLabel URL = new JLabel("URL (Opcional)");
        GridBagConstraints gbc_URL = new GridBagConstraints();
        gbc_URL.anchor = GridBagConstraints.WEST;
        gbc_URL.insets = new Insets(0,  15,  5,  5);
        gbc_URL.gridx = 0;
        gbc_URL.gridy = 4;
        getContentPane().add(URL,  gbc_URL);
        
        textField_1 = new JTextField(); // url
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0,  0,  5,  5);
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 4;
        gbc_textField_1.ipadx = 150;
        getContentPane().add(textField_1,  gbc_textField_1);
        textField_1.setColumns(10);
        
        
        /////
        Lblmotiva = new JLabel("Motivación");
        gbl.setConstraints(Lblmotiva,  gbc);
        GridBagConstraints gbc_Lblmotiva = new GridBagConstraints();
        gbc_Lblmotiva.anchor = GridBagConstraints.WEST;
        gbc_Lblmotiva.insets = new Insets(0,  15,  5,  5);
        gbc_Lblmotiva.gridx = 0;
        gbc_Lblmotiva.gridy = 5;
        getContentPane().add(Lblmotiva,  gbc_Lblmotiva);
        
        
        /////
        motiva = new JTextArea();
        JScrollPane scroll2 = new JScrollPane(motiva);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //gbl.setConstraints(motiva,  gbc);
        GridBagConstraints gbc_motiva = new GridBagConstraints();
        gbc_motiva.insets = new Insets(0,  0,  5,  5);
        gbc_motiva.ipadx = 250;
        gbc_motiva.ipady = 50;
        gbc_motiva.gridx = 1;
        gbc_motiva.gridy = 5;
        getContentPane().add(scroll2,  gbc_motiva);
        
        //
        
        dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        
        LblFecha = new JLabel("Fecha Postulación");
        GridBagConstraints gbc_LblFecha = new GridBagConstraints();
        gbc_LblFecha.insets = new Insets(0,  15,  5,  5);
        gbc_LblFecha.anchor = GridBagConstraints.WEST;
        gbc_LblFecha.gridx = 0;
        gbc_LblFecha.gridy = 6;
        getContentPane().add(LblFecha,  gbc_LblFecha);
        
        dateTextField = new JTextField(10); // El tamaño se puede ajustar
        dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
        dateTextField.setText(currentDate.format(dateFormatter));
        dateTextField.setEditable(false);
        GridBagConstraints gbc_dateTextField = new GridBagConstraints();
        gbc_dateTextField.ipadx = 75;
        gbc_dateTextField.insets = new Insets(0,  0,  5,  5);
        gbc_dateTextField.gridx = 1;
        gbc_dateTextField.gridy = 6;
        getContentPane().add(dateTextField,  gbc_dateTextField);
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0,  0,  0,  5);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 7;
        
        
        //BOTON CANCELAR
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evento) {
        		limpiarFormulario();
        		dispose();
        	}
        });
        getContentPane().add(btnCancelar,  gbc_btnCancelar);
        
        //BOTON CREAR
        JButton btnCrear = new JButton("Crear");
        GridBagConstraints gbc_btnCrear = new GridBagConstraints();
        gbc_btnCrear.ipadx = 20;
        gbc_btnCrear.anchor = GridBagConstraints.EAST;
        gbc_btnCrear.insets = new Insets(0,  0,  0,  5);
        gbc_btnCrear.gridx = 1;
        gbc_btnCrear.gridy = 7;
        getContentPane().add(btnCrear,  gbc_btnCrear);
        
        
        btnCrear.addActionListener(new ActionListener() {
        
        	public void actionPerformed(ActionEvent evento) {
        		
                String esOferta = (String) cbOferta.getSelectedItem();
                String esPostulante = (String) cbPostula.getSelectedItem(); 	
                DTOfertaExtendido dtofer = ico.obtenerOfertaLaboral(offer);
                LocalDate fechaAlta = dtofer.getFechaDeAlta();
                int duracion = ico.tipoOferta(esOferta).getDuracion(); //duracion segun tipo de pub
                //ver de qué tipo es la oferta para ver su validez: oferta.fechaAlta + tipo.validez
                LocalDate fechaResultado = fechaAlta.plusDays(duracion);
                
                if (icu.existePostulacion(esPostulante,  esOferta)) { 
                	JOptionPane.showMessageDialog(ElegirPostulante.this,  "El usuario indicado ya se encuentra postulado a la oferta indicada.",  "ERROR - Elegir Postulante",  JOptionPane.ERROR_MESSAGE);
                	cbEmpresa.setEnabled(true);
                	cbOferta.setEnabled(true);
                }  else if (motiva.getText().isBlank()) {
                	JOptionPane.showMessageDialog(ElegirPostulante.this,  "No ha escrito la motivación.",  "ERROR - Elegir Postulante",  JOptionPane.ERROR_MESSAGE);
                } else if (cvred.getText().isBlank()) {
                	JOptionPane.showMessageDialog(ElegirPostulante.this,  "No ha escrito el CV reducido.",  "ERROR - Elegir Postulante",  JOptionPane.ERROR_MESSAGE);
                } else if (currentDate.isAfter(fechaResultado)) {
                	
                	//verificar las condiciones de fechas: tipo.validez+ oferta.fechaalta
                	JOptionPane.showMessageDialog(ElegirPostulante.this,  "Oferta no vigente",  "ERROR - Elegir Postulante",  JOptionPane.ERROR_MESSAGE);
                }
                
                else {
                    String esEmpresa = (String) cbEmpresa.getSelectedItem();
                    String curriculumVitae = cvred.getText();
                    ico.altaPostulacion(esOferta,  esPostulante,  curriculumVitae,  motiva.getText(),  textField_1.getText(),  currentDate);
                    JOptionPane.showMessageDialog(btnCrear,  "Postulación creada exitosamente",  "Éxito",  JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                }
        	}
        });
        
        

        setVisible(true);
   
}
    
    private void limpiarFormulario() {
        
    	cbEmpresa.setSelectedItem(null);
        cbOferta.setSelectedItem(null);
        cbPostula.setSelectedItem(null);
    	cvred.setText("");
    	motiva.setText("");
    	dispose();
    }
    
    public void actualizar(String empresa, String offer) {
        cbEmpresa.removeAllItems(); 
        cbOferta.removeAllItems();
        
        //comboBoxOfertas.removeAllItems(); 
        empresas = controlUsr.listarEmpresas();
        postulantes = controlUsr.obtenerNicknamesPostulantes();
        
        try {
        ofertas = controlUsr.listarOfertasLaborales(empresa);
        }
        catch (Exception e){
        	JOptionPane.showMessageDialog(this,  "Búsqueda de ofertas inválida",  "Error",  JOptionPane.INFORMATION_MESSAGE);
        }
        
        //infoOferta.setText("");
        cbEmpresa.addItem(" ");
        
        List<String> empresasSorted = new ArrayList<>(empresas);
        Collections.sort(empresasSorted,  String.CASE_INSENSITIVE_ORDER);
        for (String nickname : empresasSorted) {
        	
            cbEmpresa.addItem(nickname);

        }
        cbPostula.addItem(" ");	
        
        List<String> postulantesSorted = new ArrayList<>(postulantes);
        Collections.sort(postulantesSorted,  String.CASE_INSENSITIVE_ORDER);
        
    	for (String nick : postulantesSorted) {
            cbPostula.addItem(nick);
        }
    	
    	List<String> ofertasSorted = new ArrayList<>(ofertas);
        Collections.sort(ofertasSorted,  String.CASE_INSENSITIVE_ORDER);
    	
    	for (String nick : ofertasSorted) {
            cbOferta.addItem(nick);
        }
        
                
        cbEmpresa.setSelectedItem(empresa);
        cbOferta.setSelectedItem(offer);
        
        revalidate(); // Actualizar la interfaz gráfica
    }
    

}