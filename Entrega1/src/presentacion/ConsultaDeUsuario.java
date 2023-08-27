package presentacion;
//

import logica.Interfaces.*;
import logica.Fabrica;
//import logica.Clases.*;
import logica.Datatypes.*;
import javax.swing.*;

import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioNoEncontrado;

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
    
    private JTextArea textExtra;

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
        setBounds(30, 30, 500, 500);
   
        getContentPane().setLayout(null);
        
        //Título Info de Usuario
        lblInfoUsuario = new JLabel("Información de Usuario");
        lblInfoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInfoUsuario.setBounds(160, 75, 180, 14);
        getContentPane().add(lblInfoUsuario);
        

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // la cédula del usuario.
        lblIngresoNick = new JLabel("Nickname:");
        lblIngresoNick.setBounds(14, 110, 141, 14);
        getContentPane().add(lblIngresoNick);


        textFieldNick = new JTextField();
        textFieldNick.setBounds(160, 104, 250, 30);
        getContentPane().add(textFieldNick);
        textFieldNick.setEditable(false);

       
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(14, 149, 147, 23);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setBounds(160, 145, 250, 30);
        getContentPane().add(textFieldNombre);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(14, 193, 65, 14);
        getContentPane().add(lblApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setEditable(false);
        textFieldApellido.setBounds(160, 185, 250, 30);
        getContentPane().add(textFieldApellido);

       
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        btnCerrar.setBounds(385, 436, 89, 23);
        getContentPane().add(btnCerrar);
        
        JLabel lblCorreo = new JLabel("Correo ElectróncontrolOfer:");
        lblCorreo.setBounds(14, 236, 113, 14);
        getContentPane().add(lblCorreo);
        
        textFieldCorreo = new JTextField();
        textFieldCorreo.setEditable(false);
        textFieldCorreo.setBounds(160, 228, 250, 30);
        getContentPane().add(textFieldCorreo);
        
        
        JLabel lblTipo = new JLabel("Tipo de Usuario:");
        lblTipo.setBounds(14, 275, 98, 14);
        getContentPane().add(lblTipo);
        
        textTipo = new JTextField();
        textTipo.setEditable(false);
        textTipo.setBounds(160, 268, 250, 30);
        getContentPane().add(textTipo);
        

        
        lblIngresoNick_1 = new JLabel("Lista de usuarios:");
        lblIngresoNick_1.setBounds(14, 38, 170, 15);
        getContentPane().add(lblIngresoNick_1);
        
        lblInfoExtra = new JLabel("Información Adicional:");
        lblInfoExtra.setBounds(14, 321, 170, 15);
        getContentPane().add(lblInfoExtra);
        
        
        textExtra = new JTextArea();
        textExtra.setEditable(false);
        textExtra.setBounds(160, 321, 250, 51);
        getContentPane().add(textExtra);
        
        JScrollPane scrollPane = new JScrollPane(textExtra);
        scrollPane.setBounds(160, 321, 250, 55);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);     
        
       
        comboBoxUsuarios = new JComboBox<>();
        comboBoxUsuarios.setEditable(false);
        comboBoxUsuarios.setVisible(true);
        
                          
       // JComboBox<String> comboBoxUsuarios = new JComboBox<>();
        
        /*for (String nickname : nicks) {
        	//System.out.println(nickname);
            comboBoxUsuarios.addItem(nickname);
        }*/
        
        
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
	                	textExtra.setText("");
		                textExtra.append("Empresa: " + empresa.getNombreEmpresa()+ "\n"); 
		                textExtra.append("Descripcion: " + empresa.getDescripcion()+ "\n");
		                textExtra.append("URL:" + dire+ "\n");
		                textExtra.append("\n");
	                } else {
	                	tipo="Postulante";
	                	DTPostulante postula = (DTPostulante) dtusr;
	                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                	String formattedFecha = postula.getFecha_nac().format(formatter);
	                	
	                	textExtra.setText("");
		                textExtra.append("Fecha de Nacimiento: " + formattedFecha+ "\n");
		                textExtra.append("Nacionalidad: " + postula.getNacionalidad()+ "\n");
		                textExtra.append("\n");
	                
	                };
	                textExtra.setCaretPosition(0);
	                textFieldNick.setText(dtusr.getNickname());
	                textFieldNombre.setText(dtusr.getNombre());
	                textFieldApellido.setText(dtusr.getApellido());
	                textFieldCorreo.setText(dtusr.getCorreo_electronico());
	                textTipo.setText(tipo);
            
                }
            }
        });
       
        
        getContentPane().add(comboBoxUsuarios);
        
        comboBoxUsuarios.setBounds(160, 30, 283, 30);    
        
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
		                    ConsultarOfertas detallesOferta = new ConsultarOfertas(offerDetails,controlOfer);
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
        
        
        btnVerOfertas.setBounds(87, 400, 300, 25);
        getContentPane().add(btnVerOfertas);
        btnVerOfertas.setVisible(true);;
        
          
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNick.setText("");
        textFieldCorreo.setText("");
        textTipo.setText("");
        textExtra.setText("");
    };
    
    public void actualizar() {
        comboBoxUsuarios.removeAllItems(); // Limpiar los elementos actuales
        
        
        HashSet<String> nicks = controlUsr.listarNicknamesUsuarios();
        
        comboBoxUsuarios.addItem(" ");
        for (String nickname : nicks) {
            comboBoxUsuarios.addItem(nickname);
        }
        
        revalidate(); // Actualizar la interfaz gráfica
    };
    
    
    

}




