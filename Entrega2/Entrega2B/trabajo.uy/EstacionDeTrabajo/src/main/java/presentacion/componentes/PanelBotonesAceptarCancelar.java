package main.java.presentacion.componentes;

import javax.swing.*;

import main.java.presentacion.AltaDeUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotonesAceptarCancelar extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6406460132162879057L;
	private JButton btnAceptar;
    private JButton btnCancelar;
    private IAceptarCancelar clasePadre;

    public PanelBotonesAceptarCancelar() {
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clasePadre != null) {
                	clasePadre.onAceptar();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clasePadre != null) {
                	clasePadre.onCancelar();
                }
            }
        });

        add(btnAceptar);
        add(btnCancelar);
    }

    
    public void setAceptarVisible(boolean visible) {
        btnAceptar.setVisible(visible);
    }

	public void setListener(IAceptarCancelar listener) {
		this.clasePadre = listener;
		
	}
}