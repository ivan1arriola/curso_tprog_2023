package presentacion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import logica.Datatypes.*;
import logica.Interfaces.*;
import logica.Clases.*;
import java.time.LocalDate;   
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import excepciones.PostulaExistenteException;


public class ElegirPostulante extends JInternalFrame {

	private ICtrlUsuario controlUsr;
	private ICtrlOferta controlOfer;
	private JTextField cvReducido;
    private JTextArea motivacion;
    private LocalDate fechaPostulacion;
	private JLabel LblcvRed;
    private JLabel Lblmotiva;
    private JLabel Lblfecha;
    private JLabel Postulante;
    private JTextField dateTextField;
    private DateTimeFormatter dateFormatter;
    private JTextField textField;
    private final JButton btnCancelar = new JButton("Cancelar");
    private JComboBox cbEmpresa;
    private JComboBox cbOferta;
    private JComboBox comboBoxPostula;
    private JTextArea cvred;
    private JTextArea motiva;
	
    
    public ElegirPostulante(String empresa,String offer, ICtrlUsuario icu, ICtrlOferta ico) {
    	controlUsr = icu;
    	controlOfer=ico;
    	HashSet<String> postulantes = icu.obtenerNicknamesPostulantes();
    	
      	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear Postulación");
        
        JComboBox<String> comboBoxPostula = new JComboBox<String>();
        for (String nick : postulantes) {
            comboBoxPostula.addItem(nick);
        }
        JLabel Lblfecha= new JLabel("Fecha de Postulación");
        
        
        GridBagLayout gbl=new GridBagLayout();
        gbl.columnWeights = new double[]{0.0, 1.0, 0.0};
        GridBagConstraints gbc=new GridBagConstraints();
        //FlowLayout flowLayout1=new FlowLayout();
        
      	getContentPane().setLayout(gbl);
      	
      	JLabel lblEmpre = new JLabel("Empresa");
      	GridBagConstraints gbc_lblEmpre = new GridBagConstraints();
      	gbc_lblEmpre.insets = new Insets(0, 0, 5, 5);
      	gbc_lblEmpre.gridx = 0;
      	gbc_lblEmpre.gridy = 0;
      	getContentPane().add(lblEmpre, gbc_lblEmpre);
      	
      	JComboBox cbEmpresa = new JComboBox();
      	cbEmpresa.setEditable(false);
      	GridBagConstraints gbc_cbEmpresa = new GridBagConstraints();
      	gbc_cbEmpresa.insets = new Insets(0, 0, 5, 5);
      	gbc_cbEmpresa.gridx = 1;
      	gbc_cbEmpresa.gridy = 0;
      	gbc_cbEmpresa.ipadx = 180;
      	getContentPane().add(cbEmpresa, gbc_cbEmpresa);
      	
      	JLabel lblNewLabel = new JLabel("Oferta");
      	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
      	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
      	gbc_lblNewLabel.gridx = 0;
      	gbc_lblNewLabel.gridy = 1;
      	getContentPane().add(lblNewLabel, gbc_lblNewLabel);
      	
      	JComboBox cbOferta = new JComboBox();
      	cbOferta.setEditable(false);
      	GridBagConstraints gbc_cbOferta = new GridBagConstraints();
      	gbc_cbOferta.insets = new Insets(0, 0, 5, 5);
      	gbc_cbOferta.gridx = 1;
      	gbc_cbOferta.gridy = 1;
      	gbc_cbOferta.ipadx=180;
      	getContentPane().add(cbOferta, gbc_cbOferta);

      	
      	////
      	cbEmpresa.setSelectedItem(empresa);
      	cbOferta.setSelectedItem(offer);
      	
      	
      	/////////
      	
      	
      	JLabel Postulante = new JLabel("Postulante");
        gbl.setConstraints(Postulante, gbc);
        GridBagConstraints gbc_postula = new GridBagConstraints();
        gbc_postula.insets = new Insets(5, 0, 5, 5);
        gbc_postula.gridx = 0;
        gbc_postula.gridy = 2;
        getContentPane().add(Postulante, gbc_postula);
        
        

        
        comboBoxPostula.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedUsuario = (String) comboBoxPostula.getSelectedItem();
                
            };
        });
        ////
        
        gbl.setConstraints(comboBoxPostula, gbc);
        GridBagConstraints gbc_combo = new GridBagConstraints();
        gbc_combo.ipadx = 180;
        gbc_combo.insets = new Insets(5, 0, 5, 5);
        gbc_combo.gridx = 1;
        gbc_combo.gridy = 2;
        getContentPane().add(comboBoxPostula,gbc_combo);
      	
      	
      	////
        JLabel LblcvRed = new JLabel("Ingrese CV Reducido");
        gbl.setConstraints(LblcvRed, gbc);
        GridBagConstraints gbc_LblcvRed = new GridBagConstraints();
        gbc_LblcvRed.insets = new Insets(0, 5, 5, 5);
        gbc_LblcvRed.gridx = 0;
        gbc_LblcvRed.gridy = 3;
        getContentPane().add(LblcvRed, gbc_LblcvRed);
        /////
        
        
        //////
        
        
        JTextArea cvred = new JTextArea();
        JScrollPane scroll1 = new JScrollPane(cvred);
        scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //cvred.setSize(200, 200);
        gbl.setConstraints(cvred, gbc);
        
        GridBagConstraints gbc_cvred = new GridBagConstraints();
        gbc_cvred.ipadx = 250;
        gbc_cvred.ipady = 50;
        gbc_cvred.insets = new Insets(0, 0, 5, 5);
        gbc_cvred.gridx = 1;
        gbc_cvred.gridy = 3;
        getContentPane().add(scroll1, gbc_cvred);
        
        
        /////
        JLabel Lblmotiva_1 = new JLabel("Motivación");
        gbl.setConstraints(Lblmotiva_1, gbc);
        GridBagConstraints gbc_Lblmotiva_1 = new GridBagConstraints();
        gbc_Lblmotiva_1.insets = new Insets(0, 0, 5, 5);
        gbc_Lblmotiva_1.gridx = 0;
        gbc_Lblmotiva_1.gridy = 4;
        getContentPane().add(Lblmotiva_1, gbc_Lblmotiva_1);
        
        
        /////
        JTextArea motiva = new JTextArea();
        JScrollPane scroll2 = new JScrollPane(motiva);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        gbl.setConstraints(motiva, gbc);
        GridBagConstraints gbc_motiva = new GridBagConstraints();
        gbc_motiva.insets = new Insets(0, 0, 5, 5);
        gbc_motiva.ipadx = 250;
        gbc_motiva.ipady = 50;
        gbc_motiva.gridx = 1;
        gbc_motiva.gridy = 4;
        getContentPane().add(scroll2, gbc_motiva);
        
        
        
        dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        
        JLabel lblFecha = new JLabel("Fecha de Postulación");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
        gbc_lblFecha.anchor = GridBagConstraints.EAST;
        gbc_lblFecha.gridx = 0;
        gbc_lblFecha.gridy = 5;
        getContentPane().add(lblFecha, gbc_lblFecha);
        dateTextField = new JTextField(10); // El tamaño se puede ajustar
        dateTextField.setHorizontalAlignment(SwingConstants.CENTER);
        dateTextField.setText(currentDate.format(dateFormatter));
        dateTextField.setEditable(false);
        GridBagConstraints gbc_dateTextField = new GridBagConstraints();
        gbc_dateTextField.ipadx = 75;
        gbc_dateTextField.insets = new Insets(0, 0, 5, 5);
        gbc_dateTextField.gridx = 1;
        gbc_dateTextField.gridy = 5;
        getContentPane().add(dateTextField, gbc_dateTextField);
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 6;
        
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	limpiarFormulario();
        	
        	}
        	
        });
        
        JButton btnCrear = new JButton("Crear");
        GridBagConstraints gbc_btnCrear = new GridBagConstraints();
        gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
        gbc_btnCrear.gridx = 2;
        gbc_btnCrear.gridy = 5;
        getContentPane().add(btnCrear, gbc_btnCrear);
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        

        
       String esPostulante = (String) comboBoxPostula.getSelectedItem(); 
       String esEmpresa = (String) cbEmpresa.getSelectedItem();  
       String esOferta = (String) cbOferta.getSelectedItem(); 
       
       btnCrear.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   
        	   try {

               if(icu.existePostulacion(esPostulante, esOferta))
               { 
            	   throw new PostulaExistenteException("Ya existe una postulación para estos datos");
               }
               
               icu.crearPostulacion(esOferta,esPostulante , cvred.getText() , motiva.getText(), currentDate);
               //crea postulación
               JOptionPane.showMessageDialog(btnCrear, "Postulación creada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        	   
             	} catch (PostulaExistenteException ex) {
             		cbEmpresa.setEditable(true);
                    cbOferta.setEditable(true);
        	    JOptionPane.showMessageDialog(btnCrear, "Ya existe una postulación del usuario"+esPostulante+"para la oferta" + esOferta, "Error", JOptionPane.ERROR_MESSAGE);
        	   }
               
           };
       });
       
       
       
       

   

}
    
    
    private void limpiarFormulario() {
        
    	cbEmpresa.setSelectedItem(null);
        cbOferta.setSelectedItem(null);
        comboBoxPostula.setSelectedItem(null);
    	cvred.setText("");
    	motiva.setText("");
    	dispose();
    }
    

}


