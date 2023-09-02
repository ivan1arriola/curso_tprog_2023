package presentacion.componentes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel {
    private JButton btnAceptar;
    private JButton btnCancelar;
    private AceptarListener aceptarListener;
    private CancelarListener cancelarListener;

    public PanelBotones() {
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aceptarListener != null) {
                    aceptarListener.onAceptar();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cancelarListener != null) {
                    cancelarListener.onCancelar();
                }
            }
        });

        add(btnAceptar);
        add(btnCancelar);
    }

    public void setAceptarListener(AceptarListener listener) {
        this.aceptarListener = listener;
    }

    public void setCancelarListener(CancelarListener listener) {
        this.cancelarListener = listener;
    }
}