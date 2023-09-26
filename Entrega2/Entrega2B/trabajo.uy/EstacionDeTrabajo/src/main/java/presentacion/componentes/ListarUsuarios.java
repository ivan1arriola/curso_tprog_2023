package main.java.presentacion.componentes;

import java.util.HashSet;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class ListarUsuarios extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private JComboBox<String> nicknamesComboBox;
    private ListarUsuariosObserver observer;

    public ListarUsuarios() {
        setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblSeleccionarUsuario = new JLabel("Seleccionar Usuario");
        add(lblSeleccionarUsuario);
        
        nicknamesComboBox = new JComboBox<>();
        add(nicknamesComboBox);

        // Agregar un elemento por defecto "Selecciona un usuario" en el JComboBox
        nicknamesComboBox.addItem("Selecciona un usuario");

        // Agregar ActionListener al JComboBox para manejar la selección
        nicknamesComboBox.addActionListener(e -> {
            String selectedUser = getUsuarioNickname();
            
            if (observer != null) {
                if (selectedUser != null) {
                    observer.desplegarInfo(selectedUser);
                } else {
                    observer.limpiarInfo();
                }
            }
        });
    }

    public void setUsuarios(HashSet<String> usuarios) {
        nicknamesComboBox.removeAllItems();

        // Agregar el elemento "Selecciona un usuario" como el primer elemento
        nicknamesComboBox.addItem("Selecciona un usuario");

        for (String usuario : usuarios) {
            nicknamesComboBox.addItem(usuario);
        }
    }

    public void setObserver(ListarUsuariosObserver observer) {
        this.observer = observer;
    }
    
    public void setComboBoxEnabled(boolean enabled) {
        nicknamesComboBox.setEnabled(enabled);
    }
    
    public String getUsuarioNickname() {
    	
    	String nickname = (String) nicknamesComboBox.getSelectedItem();
    	if (nickname!= null && nickname.equals("Selecciona un usuario")) {
    		return null;
    	}
    	return nickname;
    }
}
