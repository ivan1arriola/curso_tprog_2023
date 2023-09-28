package main.java.presentacion.componentes;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CamposEmpresa extends JPanel implements IFormulario {
    private static final long serialVersionUID = -4855817509680138235L;

    
 // Campos específicos para el tipo Empresa
    private JTextField nombreEmpresaField;
    private JTextArea descripcionField;
    private JScrollPane descripcionScrollPane;
    private JTextField urlField;

 // Etiquetas para los campos de Empresa
    private JLabel lblNombreEmpresa;
    private JLabel lblDescripcion;
    private JLabel lblUrl;

    public CamposEmpresa() {
        setGridLayout(3, 2);

        lblNombreEmpresa = new JLabel("Nombre de la Empresa*:");
        nombreEmpresaField = new JTextField();

        lblDescripcion = new JLabel("Descripción*:");
        descripcionField = new JTextArea();
        descripcionField.setLineWrap(true);
        descripcionField.setWrapStyleWord(true);
        descripcionScrollPane = new JScrollPane(descripcionField);

        lblUrl = new JLabel("URL de la Empresa:");
        urlField = new JTextField();

        // Agregar los componentes al panel
        add(lblNombreEmpresa);
        add(nombreEmpresaField);
        add(lblDescripcion);
        add(descripcionScrollPane);
        add(lblUrl);
        add(urlField);
    }


    public void setGridLayout(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
    }


    public void setEditable(boolean habilitar) {
        nombreEmpresaField.setEditable(habilitar);
        descripcionField.setEditable(habilitar);
        urlField.setEditable(habilitar);
    }


    
    
    @Override
    public boolean validar() {
    	String nombreEmpresa = nombreEmpresaField.getText().trim();
        String descripcion = descripcionField.getText().trim();
        
        // Validar campos específicos para Empresa
        if (nombreEmpresa.isEmpty()) {
            throw new IllegalArgumentException("El campo de Nombre de Empresa no puede estar vacío.");
        }
        if (descripcion.isEmpty()) {
            throw new IllegalArgumentException("El campo de Descripción no puede estar vacío.");
        }
    	
        return true;
    }

    @Override
    public void limpiar() {
        nombreEmpresaField.setText("");
        descripcionField.setText("");
        urlField.setText("");
    }

    public void setCampos(String nombreEmpresa, String descripcion, String url) {
        nombreEmpresaField.setText(nombreEmpresa.trim());
        descripcionField.setText(descripcion.trim());
        urlField.setText(url.trim());
    }

    public void setCampos(String nombreEmpresa, String descripcion) {
        setCampos(nombreEmpresa, descripcion, ""); // Llama a la versión completa de setCampos con URL vacía
    }
    
    public String getNombreEmpresa() {
        return nombreEmpresaField.getText().trim();
    }

    public String getDescripcion() {
        return descripcionField.getText().trim();
    }

    public String getUrl() {
        return urlField.getText().trim();
    }

    
        
   

        
}
