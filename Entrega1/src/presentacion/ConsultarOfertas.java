package presentacion;
//

import logica.Interfaces.*;
import logica.Clases.*;
import logica.Datatypes.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.awt.Font;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

@SuppressWarnings("serial")

public class ConsultarOfertas extends JDialog {
	
	private ICtrlOferta ico;
    public ConsultarOfertas(Set<String> offerDetails,ICtrlOferta icoInstance) {
    	
    	ico=icoInstance;
    
    	setResizable(true);
        // setIconifiable(true);
        // setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setClosable(true);
        setTitle("Ofertas Laborales");
        setSize(400,200);
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
        /*JLabel leftLabel = new JLabel("Ofertas");
        leftLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        leftPanel.add(leftLabel, BorderLayout.NORTH);*/
        
        // Panel con texto
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        detailsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(detailsScrollPane, BorderLayout.CENTER);
      //  splitPane.setLeftComponent(leftPanel);  
        
        
        JPanel rightPanel = new JPanel(new BorderLayout());
        /*JLabel rightLabel = new JLabel("Opciones");
        rightPanel.add(rightLabel, BorderLayout.NORTH);*/   
  
        JComboBox<String> comboBox = new JComboBox<String>();
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
        
        
       // Seleccion de oferta para mostrar detalle 
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedOferta = (String) comboBox.getSelectedItem();
                            
                DTOfertaExtendido dtOfer = ico.obtenerOfertaLaboral(selectedOferta);
		        rightTextArea.setText("");  
                rightTextArea.append("Nombre: " + dtOfer.getNombre() + "\n" +
        				"Descripción: " + dtOfer.getDescripcion() + "\n" +
        				"Fecha de alta: " + dtOfer.getFechaDeAlta() + "\n" +
        				"Costo: " + dtOfer.getCosto() + "\n" +
        				"Remuneración: " + dtOfer.getRemuneracion() + "\n" +
        				"Horario de Entrada: " + dtOfer.getHorario().getDesde() + "\n" +
        				"Horario de Salida: " + dtOfer.getHorario().getHasta() + "\n" +
        				"Departamento, Ciudad: " + dtOfer.getDepartamento() + "," + dtOfer.getCiudad());
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
        	 //detailsTextArea.append("Fecha de Alta: " + datosOferta.getFechaDeAlta() + "\n");
        	 detailsTextArea.append("Costo: " + datosOferta.getCosto() + "\n");
        	 detailsTextArea.append("Remuneración: " + datosOferta.getRemuneracion() + "\n");
        	 //detailsTextArea.append("Horario Entrada: " + datosOferta.getHorario().getDesde().toString() + "\n");
        	 //detailsTextArea.append("Horario Salida: " + datosOferta.getHorario().getHasta().toString() + "\n");
        	 detailsTextArea.append("Ciudad: " + datosOferta.getCiudad() + "\n");
        	 detailsTextArea.append("Departamento: " + datosOferta.getDepartamento().name() + "\n");
        	 detailsTextArea.append("\n"); // Add an empty line between offers
         }
         //pack();
         setVisible(true);
     }
    }


