package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class AltaTipoPublicaciónOfertaLaboral extends JInternalFrame {
    Fabrica fabrica = Fabrica.getInstance();
    ICtrlOferta ctrlOferta = fabrica.getICtrlOferta();

    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AltaTipoPublicaciónOfertaLaboral frame = new AltaTipoPublicaciónOfertaLaboral();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AltaTipoPublicaciónOfertaLaboral() {
        setResizable(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setBounds(100, 100, 588, 590);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setTitle("Alta de Tipo de publicación de Oferta Laboral");

        JButton btnNewButton = new JButton("Enviar");
        getContentPane().add(btnNewButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        panel.setLayout(new FormLayout(new ColumnSpec[] {
                ColumnSpec.decode("max(99dlu;pref):grow"),
                ColumnSpec.decode("default:grow"),},
                new RowSpec[] {
                        RowSpec.decode("fill:pref:grow"),
                        RowSpec.decode("fill:pref:grow"),
                        RowSpec.decode("fill:pref:grow"),
                        RowSpec.decode("fill:pref:grow"),
                        RowSpec.decode("fill:pref:grow"),
                        RowSpec.decode("fill:pref:grow"),}));

        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JSpinner exposicionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner duracionSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        JFormattedTextField costoField = new JFormattedTextField(new DecimalFormat("#.##"));

        JTextField fechaAltaField = new JTextField();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(dateFormatter);
        fechaAltaField.setText(formattedDate);
        fechaAltaField.setEditable(false);

        panel.add(new JLabel("Nombre:"), "1, 1, center, default");
        panel.add(nombreField, "2, 1, fill, fill");

        panel.add(new JLabel("Descripción:"), "1, 2, center, default");
        panel.add(descripcionField, "2, 2, fill, fill");

        panel.add(new JLabel("Exposición:"), "1, 3, center, default");
        panel.add(exposicionSpinner, "2, 3, fill, fill");

        panel.add(new JLabel("Duración en días:"), "1, 4, center, default");
        panel.add(duracionSpinner, "2, 4, fill, fill");

        costoField.setValue(0.0);
        costoField.setColumns(10);
        panel.add(new JLabel("Costo:"), "1, 5, center, default");
        panel.add(costoField, "2, 5, fill, fill");

        panel.add(new JLabel("Fecha de alta:"), "1, 6, center, default");
        panel.add(fechaAltaField, "2, 6, fill, fill");

        getContentPane().add(panel);

        pack();
        
        
        nombreField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nombreField.setBackground(UIManager.getColor("TextField.background"));
            }
        });

        descripcionField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                descripcionField.setBackground(UIManager.getColor("TextField.background"));
            }

            
			@Override
            public void removeUpdate(DocumentEvent e) {
                descripcionField.setBackground(UIManager.getColor("TextField.background"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                descripcionField.setBackground(UIManager.getColor("TextField.background"));
            }
        });

      

        
        

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String descripcion = descripcionField.getText();
                LocalDate fechaAlta = currentDate;

                boolean valid = true;

                if (nombre.isEmpty()) {
                    nombreField.setBackground(Color.RED);
                    valid = false;
                    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    nombreField.setBackground(UIManager.getColor("TextField.background"));
                }

                if (descripcion.isEmpty()) {
                    descripcionField.setBackground(Color.RED);
                    valid = false;
                    JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    descripcionField.setBackground(UIManager.getColor("TextField.background"));
                }

                int exposicion = (int) exposicionSpinner.getValue();
                int duracion = (int) duracionSpinner.getValue();

                float costo = 0.0f;
                try {
                    costo = Float.parseFloat(costoField.getText());
                } catch (NumberFormatException ex) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "El costo debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (valid) {
                    try {
                        boolean res = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
                        System.out.print(res);
                        if (res) {
                            JOptionPane.showMessageDialog(null, "Tipo de publicación de oferta laboral creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
