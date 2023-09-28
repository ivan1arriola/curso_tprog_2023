package main.java.presentacion.componentes;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CamposUsuario extends JPanel implements IFormulario {
    private static final long serialVersionUID = -4855817509680138235L;

    private JTextField nicknameField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;

    // Etiquetas comunes
    private JLabel lblNickname;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreo;

    public CamposUsuario() {
    	
        lblNickname = new JLabel("Nickname*:");
        nicknameField = new JTextField();
        
        nicknameField.setEditable(false);

        lblNombre = new JLabel("Nombre*:");
        nombreField = new JTextField();

        lblApellido = new JLabel("Apellido*:");
        apellidoField = new JTextField();

        lblCorreo = new JLabel("Correo electrónico*:");
        correoField = new JTextField();

        // Agregar los campos comunes a este panel
        setGridLayout(4,2);
        add(lblNickname);
        add(nicknameField);
        add(lblNombre);
        add(nombreField);
        add(lblApellido);
        add(apellidoField);
        add(lblCorreo);
        add(correoField);

    }

    public void setGridLayout(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
    }


    public void setEditable(boolean habilitar) {
        // Todos los campos excepto correo y nickname serán editables
        nombreField.setEditable(habilitar);
        apellidoField.setEditable(habilitar);
        
        // Correo y nickname no serán editables en cualquier caso
        nicknameField.setEditable(false);
        correoField.setEditable(false);
    }

    public void setEditable(boolean habilitar, boolean esAltaUsuario) {
        // Llama a la versión sin esAltaUsuario para habilitar los campos comunes
        setEditable(habilitar);

        // Si es un "alta usuario", entonces habilita el correo y el nickname
        if (esAltaUsuario) {
            nicknameField.setEditable(true);
            correoField.setEditable(true);
        }
    }


    
    
    @Override
    public boolean validar() {
    	String nickname = nicknameField.getText().trim();
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String correo = correoField.getText().trim();
        
        if (nickname.isEmpty()) {
            throw new IllegalArgumentException("El campo de Nickname no puede estar vacío.");
        }
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El campo de Nombre no puede estar vacío.");
        }
        if (apellido.isEmpty()) {
            throw new IllegalArgumentException("El campo de Apellido no puede estar vacío.");
        }
        if (correo.isEmpty()) {
            throw new IllegalArgumentException("El campo de Correo no puede estar vacío.");
        }
        // Validar el formato del correo electrónico genérico
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("El campo de Correo no tiene un formato de correo electrónico válido.");
        }
        if (!nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚüÜçÇäÄëËïÏöÖ]+( [a-zA-ZñÑáéíóúÁÉÍÓÚüÜçÇäÄëËïÏöÖ]+)*")) {
            throw new IllegalArgumentException("El campo de Nombre solo puede contener letras y espacios.");
        }
        if (!apellido.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚüÜçÇäÄëËïÏöÖ]+( [a-zA-ZñÑáéíóúÁÉÍÓÚüÜçÇäÄëËïÏöÖ]+)*")) {
            throw new IllegalArgumentException("El campo de Apellido solo puede contener letras y espacios.");
        }
        
        return true;
    }

    @Override
    public void limpiar() {
    		nicknameField.setText("");
    		    nombreField.setText("");
    		    apellidoField.setText("");
    		    correoField.setText("");
    		    setEditable(false);
    }
    
        
    // Setter para establecer todos los campos juntos
    public void setCampos(String nickname, String nombre, String apellido, String correo) {
        // Configura los valores en los campos
        nicknameField.setText(nickname.trim());
        nombreField.setText(nombre.trim());
        apellidoField.setText(apellido.trim());
        correoField.setText(correo.trim());
    }
    
    // Getter para el campo nicknameField
    public String getNickname() {
        return nicknameField.getText().trim();
    }

    // Getter para el campo nombreField
    public String getNombre() {
        return nombreField.getText().trim();
    }

    // Getter para el campo apellidoField
    public String getApellido() {
        return apellidoField.getText().trim();
    }

    // Getter para el campo correoField
    public String getCorreo() {
        return correoField.getText().trim();
    }

        
}
