package main.java.presentacion;

import javax.swing.JInternalFrame;

import main.java.excepciones.UsuarioNoExisteException;
import main.java.logica.Interfaces.ICtrlUsuario;
import main.java.presentacion.componentes.InfoUsuario;
import main.java.presentacion.componentes.IEditable;
import main.java.presentacion.componentes.ListarUsuariosObserver;
import main.java.presentacion.componentes.PanelBotonesAceptarCancelar;
import main.java.presentacion.componentes.PanelBotonesEditarCerrar;
import main.java.presentacion.componentes.SelectorUsuario;
import main.java.presentacion.formularios.FormularioCrearPaquete;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;


public class ModificarDatosDeUsuario extends JInternalFrame implements IEditable, ListarUsuariosObserver  {

    private static final long serialVersionUID = 5426782142597606996L;
	private ICtrlUsuario ctrlUsuario;
    private SelectorUsuario menu;
    private InfoUsuario datosUsuario;
    private PanelBotonesEditarCerrar botones;
    private String nicknameUsuarioDesplegado;
    
    public ModificarDatosDeUsuario() {
    	ctrlUsuario = Fabrica.getInstance().getICtrlUsuario();
    	
        setTitle("Modificar Datos de Usuario");
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setClosable(true);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        
        
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        
        // Crear menu de usuarios
        menu = new SelectorUsuario();
        menu.setObserver(this);
        mainPanel.add(menu, BorderLayout.NORTH);

        // Crea el formulario y agrégalo al panel principal
        datosUsuario = new InfoUsuario();
        mainPanel.add(datosUsuario, BorderLayout.CENTER);

        // Crea el PanelBotones
        botones = new PanelBotonesEditarCerrar();
        mainPanel.add(botones, BorderLayout.SOUTH);

        // Configura los Listener de aceptar y cancelar
        botones.setEditable(this);
    	
    }

    @Override
    public void desplegarInfo(String selectedUser) {
        if (!selectedUser.equals("Selecciona un usuario")) {
            try {
                DTUsuario usuario = ctrlUsuario.obtenerDatosUsuario(selectedUser);
                if (usuario instanceof DTEmpresa) {
                	datosUsuario.mostrarEmpresa((DTEmpresa) usuario);
                } else if (usuario instanceof DTPostulante) {
                	datosUsuario.mostrarPostulante((DTPostulante) usuario);
                }
                nicknameUsuarioDesplegado = usuario.getNickname();
            } catch (Exception e) {
                // Captura la excepción y muestra un mensaje de error al usuario
                JOptionPane.showMessageDialog(
                    this,
                    "Error al obtener los datos del usuario. El usuario no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    @Override
    public void limpiarInfo() {
        datosUsuario.reiniciarFormulario();
        nicknameUsuarioDesplegado = "";
        menu.setComboBoxEnabled(true);
    }

    @Override
    public void onEditar() {
    	try {
    		menu.setComboBoxEnabled(false);
            botones.setModoEdicion(true);
            
            try {
                DTUsuario usuario = ctrlUsuario.obtenerDatosUsuario(nicknameUsuarioDesplegado);
                if (usuario instanceof DTEmpresa) {
                	datosUsuario.modificarEmpresa((DTEmpresa) usuario);
                } else if (usuario instanceof DTPostulante) {
                	datosUsuario.modificarPostulante((DTPostulante) usuario);
                }
            } catch (Exception e) {
                // Captura la excepción y muestra un mensaje de error al usuario
                JOptionPane.showMessageDialog(
                    this,
                    "Error al obtener los datos del usuario. El usuario no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
            
            

    	} catch (IllegalArgumentException e) {
            String mensaje = e.getMessage();
            // Manejar el mensaje de error aquí, por ejemplo, mostrarlo en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    	
    }

    @Override
    public void onGuardar() {
        try {
            datosUsuario.validar();
            
            DTUsuario usuarioEditado = datosUsuario.getDTUsuario();

            // Llamar al controlador para guardar los cambios
            ctrlUsuario.ingresarDatosEditados(usuarioEditado.getNickname(), usuarioEditado.getNombre(), usuarioEditado.getApellido());

            // Limpiar la edición y deshabilitarla
            datosUsuario.reiniciarFormulario();
            botones.setModoEdicion(false);
            menu.setComboBoxEnabled(true);

            desplegarInfo(usuarioEditado.getNickname());
        } catch (IllegalArgumentException e) {
            String mensaje = e.getMessage();
            // Manejar el mensaje de error aquí, por ejemplo, mostrarlo en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void onCerrar() {
        this.dispose();
        limpiarInfo();
    }

	public void actualizar() {
		menu.setUsuarios(ctrlUsuario.listarNicknamesUsuarios());
		menu.setComboBoxEnabled(true);
		
	}

	@Override
	public void onDescartar() {
	    try {
	        DTUsuario usuarioEditado = datosUsuario.getDTUsuario();
	        datosUsuario.setEditable(false);
	        botones.setModoEdicion(false);
	        desplegarInfo(usuarioEditado.getNickname());
	        menu.setComboBoxEnabled(true);
	    } catch (IllegalArgumentException e) {
	        String mensaje = e.getMessage();
	        // Manejar el mensaje de error aquí, por ejemplo, mostrarlo en un cuadro de diálogo
	        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}