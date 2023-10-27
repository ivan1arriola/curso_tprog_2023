package presentacion;

import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPostulacion;
import logica.datatypes.DTPostulante;
import logica.datatypes.DTUsuario;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class ConsultarOfertas extends JDialog {

    private Set<DTPostulacion> postulaciones;
    private ICtrlOferta ico;
    private ICtrlUsuario icu;
    private String motiva;
    private String curriculumVitae;
    private LocalDate fecha;
    private String fechaFormat;

    public ConsultarOfertas(Set<String> offerDetailsUnsort, ICtrlOferta icoInstance, ICtrlUsuario icuInstance, String usuario) {

        ico = icoInstance;
        icu = icuInstance;

        List<String> offerDetails = new ArrayList<>(offerDetailsUnsort);
        Collections.sort(offerDetails, String.CASE_INSENSITIVE_ORDER);


        setResizable(true);
        // setIconifiable(true); 
        // setMaximizable(true); 
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setClosable(true); 
        setTitle("Ofertas Laborales");
        setSize(400, 200);
        setBounds(30, 30, 500, 500);

        // Panel de título
        JPanel titlePanel = new JPanel(new GridLayout(1, 2));
        JLabel leftTitleLabel = new JLabel("Ofertas Laborales", SwingConstants.CENTER);
        leftTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        JLabel rightTitleLabel = new JLabel("Elija oferta para ver detalles", SwingConstants.CENTER);
        rightTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        titlePanel.add(leftTitleLabel);
        titlePanel.add(rightTitleLabel);


        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.5);
        getContentPane().add(splitPane, BorderLayout.CENTER);


        JPanel leftPanel = new JPanel(new BorderLayout());

        // Panel con texto
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        detailsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(detailsScrollPane, BorderLayout.CENTER);
        //  splitPane.setLeftComponent(leftPanel);


        JPanel rightPanel = new JPanel(new BorderLayout());

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("");
        for (String oferta : offerDetails) {
            comboBox.addItem(oferta);
        }

        rightPanel.add(comboBox, BorderLayout.NORTH);
        JTextArea rightTextArea = new JTextArea();
        rightTextArea.setEditable(false);
        JScrollPane rightScrollPane = new JScrollPane(rightTextArea);
        rightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightPanel.add(rightScrollPane, BorderLayout.CENTER);
        rightTextArea.setLineWrap(true);
        rightTextArea.setWrapStyleWord(true);

        // Seleccion de oferta para mostrar detalle
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {

                String selectedOferta = (String) comboBox.getSelectedItem();

                DTOfertaExtendido dtOfer = ico.obtenerOfertaLaboral(selectedOferta);

                DTUsuario usr = icu.obtenerDatosUsuario(usuario);

                if (usr instanceof DTPostulante) {
                    postulaciones = dtOfer.getPostulaciones();

                    for (DTPostulacion post : postulaciones) {
                        if (post.getPostulante().equals(usuario)) {

                            curriculumVitae = post.getcVitae();
                            motiva = post.getMotivacion();
                            fecha = post.getFecha();
                            break;
                        }
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    fechaFormat = fecha.format(formatter);
                }


                rightTextArea.setText("");
                rightTextArea.append("Nombre: " + dtOfer.getNombre() + "\n" + "Descripción: " + dtOfer.getDescripcion()
                        + "\n" + "Fecha de alta: " + dtOfer.getFechaDeAlta() + "\n" + "Costo: " + (int) dtOfer.getCosto()
                        + "\n" + "Remuneración: " + (int) dtOfer.getRemuneracion() + "\n" + "Horario de Entrada: "
                        + dtOfer.getHorario().getDesde() + "\n" + "Horario de Salida: " + dtOfer.getHorario().getHasta()
                        + "\n" + "Departamento,   Ciudad: " + dtOfer.getDepartamento() + ",  " + dtOfer.getCiudad() + "\n");

                if (usr instanceof DTPostulante) {
                    rightTextArea.append("CV Reducido: " + curriculumVitae + "\n" + "Motivación: " + motiva + "\n"
                            + "Fecha de Postulación: " + fechaFormat);
                }


                rightTextArea.append("\n");

            }
        });


        // splitPane.setRightComponent(rightPanel);


        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        for (String detail : offerDetails) {

            DTOfertaExtendido datosOferta = ico.obtenerOfertaLaboral(detail);

            detailsTextArea.append("Título: " + datosOferta.getNombre() + "\n");
            detailsTextArea.append("Fecha de Alta: " + datosOferta.getFechaDeAlta() + "\n");
            detailsTextArea.append("Costo: " + (int) datosOferta.getCosto() + "\n");
            detailsTextArea.append("Remuneración: " + (int) datosOferta.getRemuneracion() + "\n");
            //detailsTextArea.append("Horario Entrada: " + datosOferta.getHorario().getDesde().toString() + "\n");
            //detailsTextArea.append("Horario Salida: " + datosOferta.getHorario().getHasta().toString() + "\n");
            detailsTextArea.append("Ciudad: " + datosOferta.getCiudad() + "\n");
            detailsTextArea.append("Departamento: " + datosOferta.getDepartamento().name() + "\n");
            detailsTextArea.append("\n");  // Add an empty line between offers
        }
        //pack();
        setVisible(true);
    }
}