package presentacion;

import excepciones.ExcepcionKeywordVacia;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
//import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
//import javax.swing.JDialog;
import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.JPasswordField;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.BorderLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class AltaDeKeywords extends JInternalFrame {
    private JTextField keywordIngresada;

    /**
     * Create the application.
     */
    public AltaDeKeywords(ICtrlOferta ICO, ICtrlUsuario ICU) {
        initialize(ICO);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(ICtrlOferta ICO) {
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Keywords");
        setBounds(30, 30, 477, 153);
        getContentPane().setLayout(null);  //Absolute Layout

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                String text = keywordIngresada.getText();
                boolean notexist;
                try {
                    notexist = ICO.altaKeyword(text);
                    if (notexist) {
                        JOptionPane.showMessageDialog(AltaDeKeywords.this, "La Keyword fue dada de alta exitosamente.", "Alta de Keywords", JOptionPane.INFORMATION_MESSAGE);
                        limpiarFormulario();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(AltaDeKeywords.this, "Ingrese una keyword no existente.", "ERROR - Alta de Keywords", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ExcepcionKeywordVacia e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(AltaDeKeywords.this, e.getMessage(), "ERROR - Alta de Keywords", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnAceptar.setBounds(63, 78, 117, 25);
        getContentPane().add(btnAceptar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                limpiarFormulario();
                dispose();  // cierra ventana
            }
        });
        btnCerrar.setBounds(297, 78, 117, 25);
        getContentPane().add(btnCerrar);

        keywordIngresada = new JTextField();
        keywordIngresada.setBounds(109, 30, 346, 32);
        getContentPane().add(keywordIngresada);
        keywordIngresada.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNombre.setBounds(23, 38, 133, 15);
        getContentPane().add(lblNombre);
    }

    private void limpiarFormulario() {
        keywordIngresada.setText("");
    }
}