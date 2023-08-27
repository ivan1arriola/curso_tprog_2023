package presentacion;

import javax.swing.*;
import java.awt.*;


import java.util.HashSet;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;
import logica.Interfaces.ICtrlUsuario;
import logica.Manejadores.PaqueteHandler;
import logica.Manejadores.TipoOfertaHandler;

public class AgregarTipodePublicacióndeOfertaLaboral extends JInternalFrame {
    private JTextField CantidadMostrar;
    private ICtrlUsuario icu;
    private ICtrlOferta ico;
    /**
     * Create the application.
     */
    public AgregarTipodePublicacióndeOfertaLaboral(ICtrlOferta ICO,ICtrlUsuario ICU) {
        initialize(ICO);
        
        HashSet<String> paquetes = ICO.listarPaquetes();
        JComboBox<String> PaquetesVisualizar = new JComboBox<String>();
        for (String nombre : paquetes) {
        	PaquetesVisualizar.addItem(nombre);
        }
        PaquetesVisualizar.setBounds(280, 27, 298, 24);
        getContentPane().add(PaquetesVisualizar);
        
        HashSet<String> publicaciones = ICO.listarTipoDePublicaciones();
        JComboBox<String> TipoPublicacionMostrar = new JComboBox<String>();
        for (String nombre : publicaciones) {
        	TipoPublicacionMostrar.addItem(nombre);
        }
        TipoPublicacionMostrar.setBounds(280, 86, 298, 24);
        getContentPane().add(TipoPublicacionMostrar);
        
        JButton btnNewButton = new JButton("Aceptar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String text = CantidadMostrar.getText();
        		
                try {
                    int valor = text.isEmpty() ? 0 : Integer.parseInt(text);
                    JOptionPane.showMessageDialog(text, "Se ha vinculado el tipo de publicacion a la Oferta Laboral", "Alta de Postulante", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid input: " + text);
                    JOptionPane.showMessageDialog(text, "Ingrese por favor un numero", "Alta de Postulante", JOptionPane.ERROR_MESSAGE);
                }
//                String op1 =  PaquetesVisualizar.getText();
//                String op2 =  TipoPublicacionMostrar.getText();
//                ICO.agregarTipoOfertaPaq(op1, op2,valor);
        	}
        });
       
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
        setTitle("Agregar Tipo de Publicación de Oferta Laboral");
        setBounds(30, 30, 642, 305);
        getContentPane().setLayout(null);
        
   
        
        
        
        
        JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Paquetes");
        lblNewJgoodiesTitle.setBounds(27, 32, 130, 15);
        getContentPane().add(lblNewJgoodiesTitle);
        
        JLabel lblIngresoCI_1 = new JLabel("Cantidad:");
        lblIngresoCI_1.setBounds(27, 143, 170, 15);
        getContentPane().add(lblIngresoCI_1);
        
        
        
        
        
        
        
        
        CantidadMostrar = new JTextField();
        CantidadMostrar.setColumns(10);
        CantidadMostrar.setBounds(280, 139, 298, 24);
        getContentPane().add(CantidadMostrar);
        
        JLabel lblIngresoCI_1_1 = new JLabel("Tipo publicacion:");
        lblIngresoCI_1_1.setBounds(27, 91, 170, 15);
        getContentPane().add(lblIngresoCI_1_1);
        
        
        
        
        
        
        
        
        
        JButton btnNewButton_1 = new JButton("Cancelar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose(); // cierra ventana
            }
        });
        btnNewButton_1.setBounds(359, 201, 117, 25);
        getContentPane().add(btnNewButton_1);
        
        
    }
}
