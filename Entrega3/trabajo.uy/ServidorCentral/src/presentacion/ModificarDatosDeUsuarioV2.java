package presentacion;

import excepciones.ExceptionUsuarioNoEncontrado;
import logica.Fabrica;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTPostulante;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
import javax.swing.JLabel;
//import javax.swing.JDialog;
//import javax.swing.JTextField;
import javax.swing.JComboBox;
//import javax.swing.JTextArea;
import javax.swing.SwingConstants;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.JPasswordField;
//import javax.swing.JTable;
//import javax.swing.JList;
//import javax.swing.JSpinner;
//import javax.swing.JSplitPane;
//import javax.swing.JRadioButton;
//import javax.swing.DefaultListModel;
//import javax.swing.ListSelectionModel;
//import javax.swing.JComponent;
//import javax.swing.SpinnerNumberModel;
//import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@SuppressWarnings("serial")
public class ModificarDatosDeUsuarioV2 extends JInternalFrame {
    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private ICtrlUsuario icUsuario;
    private JButton btnCancelar;
    private JLabel lblIngreseNombre;
    private JComboBox<String> listarUsuarios;
    private ModificarDatosDeUsuarioEmpresa mDUEmpresa;
    private ModificarDatosDeUsuarioPostulante mDUPost;


    public ModificarDatosDeUsuarioV2(JFrame gui,  ICtrlUsuario icUsuario) {
        // Se inicializa con el controlador de usuarios
        // Fabrica fabrica = Fabrica.getInstance();
        // icUsuario = fabrica.getICtrlUsuario();

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar Datos de Usuario");
        setBounds(10,  40,  457,  187);
        getContentPane().setLayout(null);

        lblIngreseNombre = new JLabel("Seleccione un usuario al cual desee modificarle los datos");
        lblIngreseNombre.setBounds(12,  11,  417,  34);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);


        listarUsuarios = new JComboBox<>();
        listarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

                try {
                    icUsuario.listarNicknamesUsuarios();

                } catch (IllegalArgumentException ex) {
                    System.err.println("Error al obtener los usuarios");
                }

            }
        });
        listarUsuarios.setBounds(63,  56,  325,  22);
        getContentPane().add(listarUsuarios);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario,   se invoca un método reutilizable para ello. 
        btnCancelar = new JButton("Cerrar");
        btnCancelar.setBounds(277,  109,  111,  25);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);

        JButton modificarBtn = new JButton("Ir a modificar");
        modificarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

                String selectedUsuario = (String) listarUsuarios.getSelectedItem(); // agarro el usuario
                DTUsuario dtus = null; // obtengo los datos
                try {
                    dtus = icUsuario.obtenerDatosUsuario(selectedUsuario);
                } catch (ExceptionUsuarioNoEncontrado e) {
                    throw new RuntimeException(e);
                }

                // El combobox no esta vacio y el usuario es un POSTULANTE
                if (listarUsuarios.getSelectedIndex() != -1 && listarUsuarios.getSelectedIndex() != 0 && !(dtus instanceof DTEmpresa)) {
                    DTPostulante dtpostu = (DTPostulante) dtus;
                    try {
                        mDUPost = new ModificarDatosDeUsuarioPostulante(icUsuario,  dtpostu);
                    } catch (ExceptionUsuarioNoEncontrado e) {
                        throw new RuntimeException(e);
                    }
                    gui.getContentPane().add(mDUPost);
                    setVisible(false);
                    mDUPost.setVisible(true);
                    // modificarUser.toFront();
                }

                // El combobox no esta vacio y el usuario es una EMPRESA
                else if (listarUsuarios.getSelectedIndex() != -1 && listarUsuarios.getSelectedIndex() != 0 && dtus instanceof DTEmpresa) {
                    DTEmpresa dtempre = (DTEmpresa) dtus;
                    try {
                        mDUEmpresa = new ModificarDatosDeUsuarioEmpresa(icUsuario,  dtempre);
                    } catch (ExceptionUsuarioNoEncontrado e) {
                        throw new RuntimeException(e);
                    }
                    gui.getContentPane().add(mDUEmpresa);
                    setVisible(false);
                    mDUEmpresa.setVisible(true);
                    //modificarUser.toFront();
                }
            }
        });
        modificarBtn.setBounds(63,  110,  146,  23);
        getContentPane().add(modificarBtn);


    }


    public void actualizar() {
        Fabrica fabrica = Fabrica.getInstance();
        icUsuario = fabrica.getICtrlUsuario();

        listarUsuarios.removeAllItems();

        //comboBoxOfertas.removeAllItems(); 
        Set<String> usuario = icUsuario.listarNicknamesUsuarios();
        List<String> usuariosOrdenados = new ArrayList<>(usuario);
        Collections.sort(usuariosOrdenados,  String.CASE_INSENSITIVE_ORDER);
        listarUsuarios.addItem(" ");
        for (String nickname : usuariosOrdenados) {
            listarUsuarios.addItem(nickname);
        }

        revalidate(); // Actualizar la interfaz gráfica
    }

}