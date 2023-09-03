package presentacion.componentes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarUsuario extends JPanel implements IFormulario {

    private static final long serialVersionUID = -4855817509680138235L;
    
    private boolean esEmpresa;
    private JTextField nicknameField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;

    // Campos específicos para el tipo Empresa
    private JTextField nombreEmpresaField;
    private JTextArea descripcionField;
    private JScrollPane descripcionScrollPane;
    private JTextField urlField;

    // Campos específicos para el tipo Postulante
    private JTextField fechaNacimientoField;
    private JTextField nacionalidadField;
    
    // Etiquetas para los campos de Usuario
    private JLabel lblNickname;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblCorreo;

    // Etiquetas para los campos de Empresa
    private JLabel lblNombreEmpresa;
    private JLabel lblDescripcion;
    private JLabel lblUrl;

    // Etiquetas para los campos de Postulante
    private JLabel lblFechaNacimiento;
    private JLabel lblNacionalidad;

    public EditarUsuario() {
        setLayout(new GridLayout(9, 2));
        
        // Campos Usuario

        lblNickname = new JLabel("Nickname:");
        nicknameField = new JTextField();
        nicknameField.setEditable(false);
        
        lblNombre = new JLabel("Nombre:");
        nombreField = new JTextField();
        
        lblApellido = new JLabel("Apellido:");
        apellidoField = new JTextField();
        
        lblCorreo = new JLabel("Correo electrónico:");
        correoField = new JTextField();
        
        // Campos Empresa

        lblNombreEmpresa = new JLabel("Nombre de la Empresa:");
        nombreEmpresaField = new JTextField();
        
        
        lblDescripcion = new JLabel("Descripción:");
        descripcionField = new JTextArea();
        descripcionField.setLineWrap(true);
        descripcionField.setWrapStyleWord(true);
        descripcionScrollPane = new JScrollPane(descripcionField);
        
        lblUrl = new JLabel("URL de la Empresa:");
        urlField = new JTextField();
        
        // Campos Postulante

        lblFechaNacimiento = new JLabel("Fecha de Nacimiento (dd-MM-yyyy):");
        fechaNacimientoField = new JTextField();
        lblNacionalidad = new JLabel("Nacionalidad:");
        nacionalidadField = new JTextField();

        // Agregar todos los campos a este panel
        add(lblNickname);
        add(nicknameField);
        add(lblNombre);
        add(nombreField);
        add(lblApellido);
        add(apellidoField);
        add(lblCorreo);
        add(correoField);

        add(lblNombreEmpresa);
        add(nombreEmpresaField);
        add(lblDescripcion);
        add(descripcionScrollPane);
        add(lblUrl);
        add(urlField);

        add(lblFechaNacimiento);
        add(fechaNacimientoField);
        add(lblNacionalidad);
        add(nacionalidadField);
        
        setEditable(false);
        ocultarCampos();
        
    }
    
    private void setVisibleCamposUsuario(boolean visible) {
        nicknameField.setVisible(visible);
        lblNickname.setVisible(visible);
        nombreField.setVisible(visible);
        lblNombre.setVisible(visible);
        apellidoField.setVisible(visible);
        lblApellido.setVisible(visible);
        correoField.setVisible(visible);
        lblCorreo.setVisible(visible);
    }

    private void setVisibleCamposEmpresa(boolean visible) {
        nombreEmpresaField.setVisible(visible);
        lblNombreEmpresa.setVisible(visible);
        descripcionScrollPane.setVisible(visible);
        lblDescripcion.setVisible(visible);
        urlField.setVisible(visible);
        lblUrl.setVisible(visible);
    }

    private void setVisibleCamposPostulante(boolean visible) {
        fechaNacimientoField.setVisible(visible);
        lblFechaNacimiento.setVisible(visible);
        nacionalidadField.setVisible(visible);
        lblNacionalidad.setVisible(visible);
    }

    
    private void ocultarCampos() {
    	// Ocultar campos y etiquetas de Usuario
    	setVisibleCamposUsuario(false);

        // Ocultar campos y etiquetas de Empresa
    	setVisibleCamposEmpresa(false);

        // Ocultar campos y etiquetas de Postulante
    	setVisibleCamposPostulante(false);
    }
    
    public void setEditable(boolean habilitar) {
        // Habilitar o deshabilitar todos los campos, dependiendo del valor de "habilitar"
        nicknameField.setEditable(false);
        nombreField.setEditable(habilitar);
        apellidoField.setEditable(habilitar);
        correoField.setEditable(false);
        nombreEmpresaField.setEditable(false);
        descripcionField.setEditable(false);
        urlField.setEditable(false);
        fechaNacimientoField.setEditable(false);
        nacionalidadField.setEditable(false);
    }

    public void setDTUsuario(DTUsuario usuario) {
        limpiar();

        ocultarCampos();

        nicknameField.setText(usuario.getNickname());
        nombreField.setText(usuario.getNombre());
        apellidoField.setText(usuario.getApellido());
        correoField.setText(usuario.getCorreo_electronico());
        
        setVisibleCamposUsuario(true);

        if (usuario instanceof DTPostulante) {
        	
        	esEmpresa = false;
        	
            DTPostulante postulante = (DTPostulante) usuario;
            LocalDate fechaNacimiento = postulante.getFecha_nac();
            String nacionalidad = postulante.getNacionalidad();

           
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaFormateada = fechaNacimiento.format(formatter);
            
            fechaNacimientoField.setText(fechaFormateada);
            nacionalidadField.setText(nacionalidad);

            setVisibleCamposPostulante(true);

            setLayout(new GridLayout(9, 2)); 
            
        } else if (usuario instanceof DTEmpresa) {
        	
        	esEmpresa = true;
        	
            DTEmpresa empresa = (DTEmpresa) usuario;
            String nombreEmpresa = empresa.getNombreEmpresa();
            String descripcion = empresa.getDescripcion();
            String url = empresa.getUrl();

            
            nombreEmpresaField.setText(nombreEmpresa);
            descripcionField.setText(descripcion);
            urlField.setText(url);

            setVisibleCamposEmpresa(true);

            setLayout(new GridLayout(9, 2));
        }
        
        setEditable(false);
    }
    
    public DTUsuario getDTUsuario() {
    	String nickname = nicknameField.getText();
    	
    	if (nickname.isEmpty()) {
            throw new IllegalArgumentException("No ha seleccionado ningun usuario");
        }
    	
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String correo = correoField.getText().trim();

        if (esEmpresa) {
            String nombreEmpresa = nombreEmpresaField.getText();
            String descripcion = descripcionField.getText();
            String url = urlField.getText();

            return new DTEmpresa(nickname, correo, apellido, nombre, nombreEmpresa, descripcion, url);
        } else {
            String fechaNacimientoStr = fechaNacimientoField.getText();
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String nacionalidad = nacionalidadField.getText();

            return new DTPostulante(nickname, correo, apellido, nombre, fechaNacimiento, nacionalidad);
        }
    }





    
    @Override
    public boolean validar() {
        // Aplicar trim() a todos los campos
        String nombreEmpresa = nombreEmpresaField.getText().trim();
        String descripcion = descripcionField.getText().trim();
        String fechaNacimiento = fechaNacimientoField.getText().trim();
        String nacionalidad = nacionalidadField.getText().trim();
        String nickname = nicknameField.getText().trim();
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String correo = correoField.getText().trim();

        if (esEmpresa) {
            // Validar campos específicos para Empresa
            if (nombreEmpresa.isEmpty()) {
                throw new IllegalArgumentException("El campo de Nombre de Empresa no puede estar vacío.");
            }
            if (descripcion.isEmpty()) {
                throw new IllegalArgumentException("El campo de Descripción no puede estar vacío.");
            }
        } else {
            // Validar campos específicos para Postulante
            if (fechaNacimiento.isEmpty()) {
                throw new IllegalArgumentException("El campo de Fecha de Nacimiento no puede estar vacío.");
            }
            if (nacionalidad.isEmpty()) {
                throw new IllegalArgumentException("El campo de Nacionalidad no puede estar vacío.");
            }
        }

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
	    // Limpiar todos los campos
	    nicknameField.setText("");
	    nombreField.setText("");
	    apellidoField.setText("");
	    correoField.setText("");
	    nombreEmpresaField.setText("");
	    descripcionField.setText("");
	    urlField.setText("");
	    fechaNacimientoField.setText("");
	    nacionalidadField.setText("");
	    
	    setEditable(false);
	    
	}
	
	
}

