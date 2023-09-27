package main.java.presentacion;

import java.awt.BorderLayout;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.logica.Fabrica;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.presentacion.componentes.IAceptarCancelar;
import main.java.presentacion.componentes.PanelBotonesAceptarCancelar;
import main.java.presentacion.formularios.FormularioCrearPaquete;

public class CrearPaqueteDeTiposPublicacionOfertasLaborales extends JInternalFrame implements IAceptarCancelar {
	
    private static final long serialVersionUID = 1L;
	private ICtrlOferta ctrlOferta;
    private FormularioCrearPaquete formulario;
    private PanelBotonesAceptarCancelar botonesPanel;
    private JPanel panel;

    public CrearPaqueteDeTiposPublicacionOfertasLaborales() {
        ctrlOferta = Fabrica.getInstance().getICtrlOferta();
        setTitle("Crear Paquete De Tipos De Publicacion De Ofertas Laborales");
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setClosable(true);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        
        

        // Crea el formulario y agrégalo al panel principal
        formulario = new FormularioCrearPaquete();
        mainPanel.add(formulario, BorderLayout.CENTER);

        // Crea el PanelBotones
        botonesPanel = new PanelBotonesAceptarCancelar();
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        // Configura los Listener de aceptar y cancelar
        botonesPanel.setListener(this);        
       
        
    }

    @Override
    public void onCancelar() {
        formulario.limpiar();
        dispose();
    }

    @Override
    public void onAceptar() {
        String nombre = formulario.getNombre();
        String descripcion = formulario.getDescripcion();
        int periodoValidez = formulario.getPeriodoValidez();
        int descuento = formulario.getDescuento();

        if (formulario.validar()) {
            try {
                boolean res = ctrlOferta.altaPaqueteOL(nombre, descripcion, periodoValidez, LocalDate.now(), descuento);
                if (res) {
                    JOptionPane.showMessageDialog(null, "Operación completada con éxito.", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    formulario.limpiar();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Algo salió mal.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException e1) {
                // Mostrar el mensaje de error en una ventana emergente
                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
