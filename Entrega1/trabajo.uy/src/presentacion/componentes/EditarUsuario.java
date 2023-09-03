package presentacion.componentes;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTPostulante;
import logica.Datatypes.DTUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditarUsuario extends JPanel implements IFormulario {

    private static final long serialVersionUID = -4855817509680138235L;
    
    private boolean esEmpresa;
    private boolean modoEdicion;

    private JTextField nicknameField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;

    // Campos específicos para el tipo Empresa
    private JTextField nombreEmpresaField;
    private JTextField descripcionField;
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
        modoEdicion = false;
        
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
        descripcionField = new JTextField();
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
        add(descripcionField);
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
        descripcionField.setVisible(visible);
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
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();

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
        if (esEmpresa) {
            // Validar campos específicos para Empresa
            if (nombreEmpresaField.getText().isEmpty() || descripcionField.getText().isEmpty() ) {
                return false; 
            }
        } else {
            // Validar campos específicos para Postulante
            if (fechaNacimientoField.getText().isEmpty() || nacionalidadField.getText().isEmpty()) {
                return false;
            }
        }
        
      
        if (nicknameField.getText().isEmpty() || nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() || correoField.getText().isEmpty()) {
            return false; 
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

