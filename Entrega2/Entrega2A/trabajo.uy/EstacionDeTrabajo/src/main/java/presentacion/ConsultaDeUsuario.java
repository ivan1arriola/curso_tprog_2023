package main.java.presentacion;
//

import main.java.logica.Interfaces.*;
import main.java.logica.Fabrica;
//import main.java.logica.Clases.*;
import main.java.logica.Datatypes.*;
import javax.swing.*;

import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;

import java.awt.event.*;
import java.awt.Font;
import java.util.*;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")

public class ConsultaDeUsuario extends JInternalFrame {
	
	private ICtrlUsuario controlUsr;
	private ICtrlOferta controlOfer;
    
    private JTextField textFieldNick;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldCorreo;
    private JTextField textTipo;

    private JLabel lblIngresoNick;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblTipo;
    private JLabel lblCorreo;
    private JLabel lblInfoUsuario;
    private JLabel lblInfoExtra;
    private JTextField textField;
    private JLabel lblIngresoNick_1;
    private JButton btnCerrar;
    private JComboBox<String> comboBoxUsuarios;
    
    private JTextArea EmpresaDesc;
    private JTextField EmpresaUrl;
    private JTextField PostuFecha;
    private JTextField Nacionalidad;
    private JLabel lblUrl;
    private JLabel lblDesc;
    
    private JLabel lblFecha;
    private JLabel lblNacion;
    private JTextField Fecha;
    private JTextField postNacion;
  
    public ConsultaDeUsuario(JFrame base, ICtrlUsuario icu, ICtrlOferta ico) {

    	//desktopPane = new JDesktopPane();
        //getContentPane().add(desktopPane);
    	/*Fabrica fabrica = Fabrica.getInstance();
        icu = fabrica.getICtrlUsuario();
        ico = fabrica.getICtrlOferta();*/
        
        controlUsr = icu;
        controlOfer = ico;
         
        // Propiedades del JInternalFrame como dimensión, posición dentro del frame, etc.
        setResizable(true);
        setIconifiable(false);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Usuario");
        setBounds(30, 30, 500, 550);
   
        getContentPane().setLayout(null);
        
        //Título Info de Usuario
        lblInfoUsuario = new JLabel("Información de Usuario");
        lblInfoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInfoUsuario.setBounds(207, 72, 180, 14);
        getContentPane().add(lblInfoUsuario);
        

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario.
        lblIngresoNick = new JLabel("Nickname:");
        lblIngresoNick.setBounds(14, 105, 141, 14);
        getContentPane().add(lblIngresoNick);


        textFieldNick = new JTextField();
        textFieldNick.setBounds(193, 98, 250, 30);
        getContentPane().add(textFieldNick);
        textFieldNick.setEditable(false);

       
        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(14, 143, 147, 23);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setBounds(193, 140, 250, 30);
        getContentPane().add(textFieldNombre);

        lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(14, 189, 65, 14);
        getContentPane().add(lblApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setEditable(false);
        textFieldApellido.setBounds(193, 182, 250, 30);
        getContentPane().add(textFieldApellido);

       
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(385, 445, 89, 23);
        getContentPane().add(btnCerrar);
        
        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setBounds(14, 224, 170, 14);
        getContentPane().add(lblCorreo);
        
        textFieldCorreo = new JTextField();
        textFieldCorreo.setEditable(false);
        textFieldCorreo.setBounds(193, 217, 250, 30);
        getContentPane().add(textFieldCorreo);
        
        
        JLabel lblTipo = new JLabel("Tipo de Usuario");
        lblTipo.setBounds(14, 265, 141, 14);
        getContentPane().add(lblTipo);
        
        textTipo = new JTextField();
        textTipo.setEditable(false);
        textTipo.setBounds(193, 259, 250, 30);
        getContentPane().add(textTipo);
        

        
        lblIngresoNick_1 = new JLabel("Lista de usuarios:");
        lblIngresoNick_1.setBounds(14, 38, 170, 15);
        getContentPane().add(lblIngresoNick_1);
    
        EmpresaDesc = new JTextArea();
        EmpresaDesc.setEditable(false);
        EmpresaDesc.setBounds(193, 334, 250, 65);
        EmpresaDesc.setVisible(false);
        getContentPane().add(EmpresaDesc); 
        EmpresaDesc.setLineWrap(true);
        EmpresaDesc.setWrapStyleWord(true);
        
        EmpresaUrl = new JTextField();
        EmpresaUrl.setEditable(false);
        EmpresaUrl.setBounds(193, 300, 250, 23);
        EmpresaUrl.setVisible(false);
        getContentPane().add(EmpresaUrl); 
        
       
        comboBoxUsuarios = new JComboBox<>();
        comboBoxUsuarios.setEditable(false);
        comboBoxUsuarios.setVisible(true);
        
                          
       // JComboBox<String> comboBoxUsuarios = new JComboBox<>();
        
        /*for (String nickname : nicks) {
        	//System.out.println(nickname);
            comboBoxUsuarios.addItem(nickname);
        }*/
        
        JScrollPane scrollPane = new JScrollPane(EmpresaDesc);
        scrollPane.setBounds(193, 334, 250, 65);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);
        
        lblUrl= new JLabel("URL");
        lblUrl.setBounds(14, 305, 46, 14);
        getContentPane().add(lblUrl);
        
        lblFecha= new JLabel("Fecha de Nacimiento");
        lblFecha.setBounds(14, 305, 120, 14);
        getContentPane().add(lblFecha);
              
        lblDesc = new JLabel("Descripción");
        lblDesc.setBounds(14, 345, 100, 14);
        getContentPane().add(lblDesc);
        
        lblNacion = new JLabel("Nacionalidad");
        lblNacion.setBounds(14, 345, 100, 14);
        getContentPane().add(lblNacion);
        
        
        PostuFecha = new JTextField();
        PostuFecha.setEditable(false);
        PostuFecha.setBounds(193, 300, 250, 23);
        PostuFecha.setVisible(false);
        getContentPane().add(PostuFecha);
        
        Nacionalidad = new JTextField();
        Nacionalidad.setEditable(false);
        Nacionalidad.setBounds(193, 340, 250, 23);
        Nacionalidad.setVisible(false);
        getContentPane().add(Nacionalidad);
        
        
        comboBoxUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	HashSet<String> nicks = controlUsr.listarNicknamesUsuarios();

                String selectedUsuario = (String) comboBoxUsuarios.getSelectedItem();
                
                if (comboBoxUsuarios.getSelectedIndex() != -1 && comboBoxUsuarios.getSelectedIndex() != 0 ) { 
                	
	                DTUsuario dtusr = controlUsr.obtenerDatosUsuario(selectedUsuario);
	                String tipo;
	                if(dtusr instanceof DTEmpresa) {
	                	tipo = "Empresa";
	                	
	                	String dire;
	
	                	DTEmpresa empresa = (DTEmpresa) dtusr;
	                	
	                	if(empresa.getUrl()==null) {dire = "No tiene";} else {dire=empresa.getUrl();}
		                	Nacionalidad.setVisible(false);
		                	PostuFecha.setVisible(false);
		                	lblNacion.setVisible(false);
		                	lblFecha.setVisible(false);
	                		EmpresaDesc.append(empresa.getDescripcion());
	                		EmpresaDesc.setVisible(true);
	                		lblDesc.setVisible(true);
	                		
	                		EmpresaUrl.setText(dire);
	                		EmpresaUrl.setVisible(true);
	                		scrollPane.setVisible(true);
	                		lblUrl.setVisible(true);
	                	                	
	                	
	                } else {
	                	tipo="Postulante";
	                	
	                	
	                	EmpresaDesc.setVisible(false);
                		EmpresaUrl.setVisible(false);
                		scrollPane.setVisible(false);
                		lblUrl.setVisible(false);
                		lblDesc.setVisible(false);
                		
	                	
	                	DTPostulante postula = (DTPostulante) dtusr;
	                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                	String formattedFecha = postula.getFecha_nac().format(formatter);
	                	
	                	Nacionalidad.setVisible(true);
	                	PostuFecha.setVisible(true);
	                	lblNacion.setVisible(true);
	                	lblFecha.setVisible(true);
	                	
	                	Nacionalidad.setText(postula.getNacionalidad());
	                	PostuFecha.setText(formattedFecha);
	                	
	                
	                };
	                EmpresaDesc.setCaretPosition(0);
	                textFieldNick.setText(dtusr.getNickname());
	                textFieldNombre.setText(dtusr.getNombre());
	                textFieldApellido.setText(dtusr.getApellido());
	                textFieldCorreo.setText(dtusr.getCorreo_electronico());
	                textTipo.setText(tipo);
            
                } else if (comboBoxUsuarios.getSelectedIndex()==0) {
                	lblUrl.setVisible(false);
                	lblDesc.setVisible(false);
                	scrollPane.setVisible(false);
                	EmpresaUrl.setVisible(false);
                	EmpresaDesc.setVisible(false);
                	Nacionalidad.setVisible(false);
                	PostuFecha.setVisible(false);
                	lblNacion.setVisible(false);
                	lblFecha.setVisible(false);
                	
                }
            }
        });
       
        
        getContentPane().add(comboBoxUsuarios);
        
        comboBoxUsuarios.setBounds(160, 30, 300, 30);    
        
        JButton btnVerOfertas = new JButton("Ver Ofertas");
        btnVerOfertas.setVisible(true);
        
        btnVerOfertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if( comboBoxUsuarios.getSelectedIndex() != -1 && comboBoxUsuarios.getSelectedIndex() != 0) {
	        		String selectedUsuario = (String)comboBoxUsuarios.getSelectedItem();
	        		Set<String> offerDetails;
					try {
						offerDetails = controlUsr.listarOfertasLaborales(selectedUsuario);
		                if (offerDetails.isEmpty()) {
		                    JOptionPane.showMessageDialog(
		                        ConsultaDeUsuario.this,
		                        "No hay ofertas registradas para este usuario.",
		                        "Información",
		                        JOptionPane.INFORMATION_MESSAGE
		                    );
		                } else {
		                	
		                    ConsultarOfertas detallesOferta = new ConsultarOfertas(offerDetails,controlOfer,controlUsr,selectedUsuario);
		                    detallesOferta.setVisible(true);
		                    getContentPane().add(detallesOferta);
		                    detallesOferta.toFront();
		                    // base.add(detallesOferta);
		                    //desktopPane.setVisible(true);	                
		                }
					} catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
        		}
        	}
        });
        
        
        btnVerOfertas.setBounds(87, 415, 300, 25);
        getContentPane().add(btnVerOfertas);
        
        btnVerOfertas.setVisible(true);
        
          
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNick.setText("");
        textFieldCorreo.setText("");
        textTipo.setText("");
        EmpresaUrl.setText("");
        EmpresaDesc.setText("");
        Nacionalidad.setText("");
    	PostuFecha.setText("");

    };
    
    public void actualizar() {
        comboBoxUsuarios.removeAllItems(); // Limpiar los elementos actuales
        
        
        HashSet<String> nicksUnsort = controlUsr.listarNicknamesUsuarios();
        
        comboBoxUsuarios.addItem(" ");
        
        List<String> nicks = new ArrayList<>(nicksUnsort);
        Collections.sort(nicks, String.CASE_INSENSITIVE_ORDER);
        
        for (String nickname : nicks) {
            comboBoxUsuarios.addItem(nickname);
        }
        
        revalidate(); // Actualizar la interfaz gráfica
    }
}




