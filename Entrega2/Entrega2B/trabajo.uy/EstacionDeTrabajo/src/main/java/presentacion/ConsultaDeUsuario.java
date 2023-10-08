package main.java.presentacion;



import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;
//import main.java.logica.Fabrica;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    private JLabel lblInfoUsuario;
    private JLabel lblIngresoNick1;
    private JButton btnCerrar;
    private JComboBox<String> comboBoxUsuarios;
    
    private JTextArea empresaDesc;
    private JTextField empresaUrl;
    private JTextField postuFecha;
    private JTextField nacionalidad;
    private JLabel lblUrl;
    private JLabel lblDesc;
    
    private JLabel lblFecha;
    private JLabel lblNacion;
   
  
    public ConsultaDeUsuario(JFrame base,   ICtrlUsuario icUsuario,   ICtrlOferta ico) {
        
        controlUsr = icUsuario;
        controlOfer = ico;
         
        // Propiedades del JInternalFrame como dimensión,   posición dentro del frame,   etc.
        setResizable(true);
        setIconifiable(false);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Usuario");
        setBounds(30,   30,   500,   550);
   
        getContentPane().setLayout(null);
        
        //Título Info de Usuario
        lblInfoUsuario = new JLabel("Información de Usuario");
        lblInfoUsuario.setFont(new Font("Tahoma",   Font.PLAIN,   14));
        lblInfoUsuario.setBounds(207,   72,   180,   14);
        getContentPane().add(lblInfoUsuario);
        

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario.
        lblIngresoNick = new JLabel("Nickname:");
        lblIngresoNick.setBounds(14,   105,   141,   14);
        getContentPane().add(lblIngresoNick);


        textFieldNick = new JTextField();
        textFieldNick.setBounds(193,   98,   250,   30);
        getContentPane().add(textFieldNick);
        textFieldNick.setEditable(false);

       
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(14,   143,   147,   23);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setBounds(193,   140,   250,   30);
        getContentPane().add(textFieldNombre);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(14,   189,   65,   14);
        getContentPane().add(lblApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setEditable(false);
        textFieldApellido.setBounds(193,   182,   250,   30);
        getContentPane().add(textFieldApellido);

       
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(385,   445,   89,   23);
        getContentPane().add(btnCerrar);
        
        JLabel lblCorreo = new JLabel("Correo Electrónico:");
        lblCorreo.setBounds(14,   224,   170,   14);
        getContentPane().add(lblCorreo);
        
        textFieldCorreo = new JTextField();
        textFieldCorreo.setEditable(false);
        textFieldCorreo.setBounds(193,   217,   250,   30);
        getContentPane().add(textFieldCorreo);
        
        
        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        lblTipo.setBounds(14,   265,   141,   14);
        getContentPane().add(lblTipo);
        
        textTipo = new JTextField();
        textTipo.setEditable(false);
        textTipo.setBounds(193,   259,   250,   30);
        getContentPane().add(textTipo);
        

        
        lblIngresoNick1 = new JLabel("Lista de usuarios:");
        lblIngresoNick1.setBounds(14,   38,   170,   15);
        getContentPane().add(lblIngresoNick1);
    
        empresaDesc = new JTextArea();
        empresaDesc.setEditable(false);
        empresaDesc.setBounds(193,   334,   250,   65);
        empresaDesc.setVisible(false);
        getContentPane().add(empresaDesc); 
        empresaDesc.setLineWrap(true);
        empresaDesc.setWrapStyleWord(true);
        
        empresaUrl = new JTextField();
        empresaUrl.setEditable(false);
        empresaUrl.setBounds(193,   300,   250,   23);
        empresaUrl.setVisible(false);
        getContentPane().add(empresaUrl); 
        
       
        comboBoxUsuarios = new JComboBox<>();
        comboBoxUsuarios.setEditable(false);
        comboBoxUsuarios.setVisible(true);
        
                          
       // JComboBox<String> comboBoxUsuarios = new JComboBox<>();
        
        /*for (String nickname : nicks) {
        	//System.out.println(nickname);
            comboBoxUsuarios.addItem(nickname);
        }*/
        
        JScrollPane scrollPane = new JScrollPane(empresaDesc);
        scrollPane.setBounds(193,   334,   250,   65);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);
        
        lblUrl= new JLabel("URL:");
        lblUrl.setBounds(14,   305,   46,   14);
        getContentPane().add(lblUrl);
        
        lblFecha= new JLabel("Fecha de Nacimiento:");
        lblFecha.setBounds(14,   305,   170,   14);
        getContentPane().add(lblFecha);
              
        lblDesc = new JLabel("Descripción:");
        lblDesc.setBounds(14,   345,   100,   14);
        getContentPane().add(lblDesc);
        
        lblNacion = new JLabel("Nacionalidad:");
        lblNacion.setBounds(14,   345,   100,   14);
        getContentPane().add(lblNacion);
        
        
        postuFecha = new JTextField();
        postuFecha.setEditable(false);
        postuFecha.setBounds(193,   300,   250,   23);
        postuFecha.setVisible(false);
        getContentPane().add(postuFecha);
        
        nacionalidad = new JTextField();
        nacionalidad.setEditable(false);
        nacionalidad.setBounds(193,   340,   250,   23);
        nacionalidad.setVisible(false);
        getContentPane().add(nacionalidad);
        
        
        comboBoxUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
            	
            	Set<String> nicks = controlUsr.listarNicknamesUsuarios();

                String selectedUsuario = (String) comboBoxUsuarios.getSelectedItem();
                
                if (comboBoxUsuarios.getSelectedIndex() != -1 && comboBoxUsuarios.getSelectedIndex() != 0) { 
                	
	                DTUsuario dtusr = controlUsr.obtenerDatosUsuario(selectedUsuario);
	                String tipo;
	                if (dtusr instanceof DTEmpresa) {
	                	tipo = "Empresa";
	                	
	                	String dire;
	
	                	DTEmpresa empresa = (DTEmpresa) dtusr;
	                	
	                	if (empresa.getUrl()==null) {
	                		dire = "No tiene"; } 
	                	else {
	                		dire=empresa.getUrl(); }
		                	nacionalidad.setVisible(false);
		                	postuFecha.setVisible(false);
		                	lblNacion.setVisible(false);
		                	lblFecha.setVisible(false);
	                		empresaDesc.append(empresa.getDescripcion());
	                		empresaDesc.setVisible(true);
	                		lblDesc.setVisible(true);
	                		
	                		empresaUrl.setText(dire);
	                		empresaUrl.setVisible(true);
	                		scrollPane.setVisible(true);
	                		lblUrl.setVisible(true);
	                	                	
	                	
	                } else {
	                	tipo="Postulante";
	                	
	                	
	                	empresaDesc.setVisible(false);
                		empresaUrl.setVisible(false);
                		scrollPane.setVisible(false);
                		lblUrl.setVisible(false);
                		lblDesc.setVisible(false);
                		
	                	
	                	DTPostulante postula = (DTPostulante) dtusr;
	                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                	String formattedFecha = postula.getFecha_nac().format(formatter);
	                	
	                	nacionalidad.setVisible(true);
	                	postuFecha.setVisible(true);
	                	lblNacion.setVisible(true);
	                	lblFecha.setVisible(true);
	                	
	                	nacionalidad.setText(postula.getNacionalidad());
	                	postuFecha.setText(formattedFecha);
	                	
	                
	                }
	                
	                empresaDesc.setCaretPosition(0);
	                textFieldNick.setText(dtusr.getNickname());
	                textFieldNombre.setText(dtusr.getNombre());
	                textFieldApellido.setText(dtusr.getApellido());
	                textFieldCorreo.setText(dtusr.getcorreoElectronico());
	                textTipo.setText(tipo);
            
                } else if (comboBoxUsuarios.getSelectedIndex()==0) {
                	lblUrl.setVisible(false);
                	lblDesc.setVisible(false);
                	scrollPane.setVisible(false);
                	empresaUrl.setVisible(false);
                	empresaDesc.setVisible(false);
                	nacionalidad.setVisible(false);
                	postuFecha.setVisible(false);
                	lblNacion.setVisible(false);
                	lblFecha.setVisible(false);
                	
                }
            }
        });
       
        
        getContentPane().add(comboBoxUsuarios);
        
        comboBoxUsuarios.setBounds(160,   30,   300,   30);    
        
        JButton btnVerOfertas = new JButton("Ver Ofertas");
        btnVerOfertas.setVisible(true);
        
        btnVerOfertas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evemto) {
        		if (comboBoxUsuarios.getSelectedIndex() != -1 && comboBoxUsuarios.getSelectedIndex() != 0) {
	        		String selectedUsuario = (String) comboBoxUsuarios.getSelectedItem();
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
		                	
		                    ConsultarOfertas detallesOferta = new ConsultarOfertas(offerDetails,  controlOfer,  controlUsr,  selectedUsuario);
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
        
        
        btnVerOfertas.setBounds(87,   415,   300,   25);
        getContentPane().add(btnVerOfertas);
        
        btnVerOfertas.setVisible(true);
        
          
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNick.setText("");
        textFieldCorreo.setText("");
        textTipo.setText("");
        empresaUrl.setText("");
        empresaDesc.setText("");
        nacionalidad.setText("");
    	postuFecha.setText("");

    }
    
    public void actualizar() {
        comboBoxUsuarios.removeAllItems(); // Limpiar los elementos actuales
        
        
        Set<String> nicksUnsort = controlUsr.listarNicknamesUsuarios();
        
        comboBoxUsuarios.addItem(" ");
        
        List<String> nicks = new ArrayList<>(nicksUnsort);
        Collections.sort(nicks,   String.CASE_INSENSITIVE_ORDER);
        
        for (String nickname : nicks) {
            comboBoxUsuarios.addItem(nickname);
        }
        
        revalidate(); // Actualizar la interfaz gráfica
    }
}




