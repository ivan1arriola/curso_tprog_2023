package presentacion;

//import java.awt.EventQueue;

import excepciones.OfertaLaboralNoEncontrada;
import logica.Fabrica;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPostulacion;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
import javax.swing.JLabel;
//import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JComboBox;
//import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
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
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class VerPostulaciones extends JInternalFrame {
    private JTextField tfFecha;
    private JTextField tfURLDocExtras;
    private JTextField tfDescripcion;
    private JTextField tfCV;
    private ICtrlUsuario icUsuario;
    private Set<DTPostulacion> postulaciones;
    private JComboBox<String> listadoPostulaciones;

    /**
     * Create the application.
     */
    public VerPostulaciones() {
        Fabrica fabrica = Fabrica.getInstance();
        icUsuario = fabrica.getICtrlUsuario();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Ver postulaciones");
        setBounds(30, 30, 678, 285);
        getContentPane().setLayout(null);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                setVisible(false);
            }
        });
        btnCerrar.setBounds(338, 216, 117, 25);
        getContentPane().add(btnCerrar);

        listadoPostulaciones = new JComboBox<String>();
        listadoPostulaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                String selected_post = (String) listadoPostulaciones.getSelectedItem();
                for (DTPostulacion element : postulaciones) {
                    if (element.getPostulante().equals(selected_post)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String formattedFecha = element.getFecha().format(formatter);
                        tfFecha.setText(formattedFecha);
                        tfCV.setText(element.getcVitae());
                        tfURLDocExtras.setText(element.getuRLDocExtras());
                        tfDescripcion.setText(element.getMotivacion());
                    }
                }

            }
        });
        listadoPostulaciones.setBounds(186, 12, 455, 28);
        getContentPane().add(listadoPostulaciones);

        JLabel lblPostulaciones = new JLabel("Lista de postulaciones:");
        lblPostulaciones.setBounds(12, 19, 170, 15);
        getContentPane().add(lblPostulaciones);

        JLabel lblIngresoCI_1_2_1 = new JLabel("Fecha:");
        lblIngresoCI_1_2_1.setBounds(12, 59, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1);

        JLabel lblIngresoCI_1_2_1_1 = new JLabel("URLDocExtras:");
        lblIngresoCI_1_2_1_1.setBounds(12, 102, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1);

        JLabel lblIngresoCI_1_2_1_1_1 = new JLabel("Motivación:");
        lblIngresoCI_1_2_1_1_1.setBounds(12, 143, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1);

        JLabel lblIngresoCI_1_2_1_1_1_1 = new JLabel("CV:");
        lblIngresoCI_1_2_1_1_1_1.setBounds(12, 186, 170, 15);
        getContentPane().add(lblIngresoCI_1_2_1_1_1_1);

        tfFecha = new JTextField();
        tfFecha.setEditable(false);
        tfFecha.setBounds(186, 52, 455, 30);
        getContentPane().add(tfFecha);

        tfURLDocExtras = new JTextField();
        tfURLDocExtras.setEditable(false);
        tfURLDocExtras.setBounds(186, 95, 455, 30);
        getContentPane().add(tfURLDocExtras);

        tfDescripcion = new JTextField();
        tfDescripcion.setEditable(false);
        tfDescripcion.setBounds(186, 136, 455, 30);
        getContentPane().add(tfDescripcion);

        tfCV = new JTextField();
        tfCV.setEditable(false);
        tfCV.setBounds(186, 178, 455, 30);
        getContentPane().add(tfCV);


    }

    public void actualizar(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        String nombre_o = nombre_oferta;
        DTOfertaExtendido DatosOferta = icUsuario.consultaOfertaLaboral(nombre_o);
        postulaciones = DatosOferta.getPostulaciones();

        for (DTPostulacion element : postulaciones) {
            listadoPostulaciones.addItem(element.getPostulante());
        }
    }
}
