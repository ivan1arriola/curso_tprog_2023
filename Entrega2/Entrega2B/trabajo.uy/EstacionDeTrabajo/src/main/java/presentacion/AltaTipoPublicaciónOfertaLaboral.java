package main.java.presentacion;

import java.awt.BorderLayout;
import java.time.*;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.java.logica.Fabrica;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.presentacion.componentes.IAceptarCancelar;
import main.java.presentacion.componentes.PanelBotonesAceptarCancelar;
import main.java.presentacion.formularios.FormularioAltaTipoPublicacion;

public class AltaTipoPublicaciónOfertaLaboral extends JInternalFrame implements IAceptarCancelar {
	
	private static final long serialVersionUID = 1L;
    ICtrlOferta ctrlOferta = Fabrica.getInstance().getICtrlOferta();

    private FormularioAltaTipoPublicacion formulario;
    private PanelBotonesAceptarCancelar botonesPanel;
    
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
        formulario = new FormularioAltaTipoPublicacion();
        mainPanel.add(formulario, BorderLayout.CENTER);

        // Crea el PanelBotones
        botonesPanel = new PanelBotonesAceptarCancelar();
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        // Configura los Listener de aceptar y cancelar
        botonesPanel.setListener(this);
    }

	@Override
	public void onCancelar() {
		// logica para boton Cancelar
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
