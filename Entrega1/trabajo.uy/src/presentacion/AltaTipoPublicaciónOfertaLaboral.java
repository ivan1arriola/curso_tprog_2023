package presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;
import presentacion.componentes.AceptarListener;
import presentacion.componentes.AltaTipoPublicacionOfertaLaboralForm;
import presentacion.componentes.CancelarListener;
import presentacion.componentes.PanelBotones;

import javax.swing.SwingConstants;

public class AltaTipoPublicaciónOfertaLaboral extends JInternalFrame implements AceptarListener, CancelarListener {
	
	private static final long serialVersionUID = 1L;
	Fabrica fabrica = Fabrica.getInstance();
    ICtrlOferta ctrlOferta = fabrica.getICtrlOferta();

    private JTextField nombreField;
    private JTextArea descripcionTextArea;
    private JFormattedTextField costoField;
    private AltaTipoPublicacionOfertaLaboralForm formulario;
    
    public AltaTipoPublicaciónOfertaLaboral() {
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setClosable(true);
        setTitle("Alta de Tipo de publicación de Oferta Laboral");
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Crea el formulario y agrégalo al panel principal
        formulario = new AltaTipoPublicacionOfertaLaboralForm();
        mainPanel.add(formulario, BorderLayout.CENTER);

        // Crea el PanelBotones
        PanelBotones botonesPanel = new PanelBotones();
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        // Configura los Listener de aceptar y cancelar
        botonesPanel.setAceptarListener(this);
        botonesPanel.setCancelarListener(this);
    }

	@Override
	public void onCancelar() {
		// Logica para boton Cancelar
		formulario.limpiar();
		setVisible(false);
		
	}

	@Override
	public void onAceptar() {
	    if (formulario.validar()) {
	        String nombre = formulario.getNombre();
	        String descripcion = formulario.getDescripcion();
	        LocalDate fechaAlta = formulario.getFechaAlta();
	        int exposicion = formulario.getExposicion();
	        int duracion = formulario.getDuracion();
	        float costo = formulario.getCosto();
	        
	        //TODO: Hay algo que falla aqui que le da el alta al tipo de publicacion a pesar de tener nombre repetido

	        try {
	            boolean res = ctrlOferta.altaTipoPublicacionOL(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
	            System.out.print(res);
	            if (res) {
	                JOptionPane.showMessageDialog(null, "Tipo de publicación de oferta laboral creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                dispose();
	            }
	            formulario.limpiarFormulario();
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

}
