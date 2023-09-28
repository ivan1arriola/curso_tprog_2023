package main.java.presentacion.componentes;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CamposContrasenia extends JPanel implements IFormulario {
    private static final long serialVersionUID = -4855817509680138235L;

    private JLabel lblContrasenia;
    private JTextField contraseniaField;
    private JLabel lblConfirmarContrasenia;
    private JTextField confirmarContraseniaField;

    public CamposContrasenia() {
        setGridLayout(2, 2);

        lblContrasenia = new JLabel("Contraseña*:");
        contraseniaField = new JTextField();

        lblConfirmarContrasenia = new JLabel("Confirmar Contraseña*:");
        confirmarContraseniaField = new JTextField();

        add(lblContrasenia);
        add(contraseniaField);
        add(lblConfirmarContrasenia);
        add(confirmarContraseniaField);
    }

    public void setGridLayout(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
    }

    public void setEditable(boolean habilitar) {
        contraseniaField.setEditable(habilitar);
        confirmarContraseniaField.setEditable(habilitar);
    }

    @Override
    public boolean validar() {
        String contrasenia = contraseniaField.getText().trim();
        String confirmarContrasenia = confirmarContraseniaField.getText().trim();

        if (contrasenia.isEmpty()) {
            throw new IllegalArgumentException("El campo de Contraseña no puede estar vacío.");
        }

        if (confirmarContrasenia.isEmpty()) {
            throw new IllegalArgumentException("El campo de Confirmar Contraseña no puede estar vacío.");
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        return true;
    }

    @Override
    public void limpiar() {
        contraseniaField.setText("");
        confirmarContraseniaField.setText("");
    }

    public void setCampos(String contrasenia) {
        contraseniaField.setText(contrasenia.trim());
    }

    public String getContrasenia() {
        return contraseniaField.getText().trim();
    }
    
    public void setMostrarConfirmarContrasenia(boolean mostrar) {
        if (mostrar) {
            setGridLayout(2, 2); 
            add(lblConfirmarContrasenia);
            add(confirmarContraseniaField);
        } else {
            setGridLayout(1, 2); 
            remove(lblConfirmarContrasenia);
            remove(confirmarContraseniaField);
        }

        revalidate(); 
        repaint();    
    }


  
}

