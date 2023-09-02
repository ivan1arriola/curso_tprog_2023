package presentacion;

import java.awt.BorderLayout;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.Fabrica;
import logica.Interfaces.ICtrlOferta;
import presentacion.componentes.AceptarListener;
import presentacion.componentes.CancelarListener;
import presentacion.componentes.PanelBotones;
import presentacion.formularios.FormularioCrearPaquete;

public class CrearPaqueteDeTiposPublicacionOfertasLaborales extends JInternalFrame implements AceptarListener, CancelarListener {
	
    private static final long serialVersionUID = 1L;
	private ICtrlOferta ctrlOferta;
    private FormularioCrearPaquete formulario;
    private PanelBotones botonesPanel;

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
        botonesPanel = new PanelBotones();
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        // Configura los Listener de aceptar y cancelar
        botonesPanel.setAceptarListener(this);
        botonesPanel.setCancelarListener(this);
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
