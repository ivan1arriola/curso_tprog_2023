package main.java.presentacion.componentes;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;

import javax.swing.BoxLayout;

// Sirve tanto para Modificar Usuario como Consultar Usuario

public class InfoUsuario extends JPanel implements IFormularioUsuario {

    private static final long serialVersionUID = -4855817509680138235L;
    
    private boolean esEmpresa;
    private boolean conContrasenia;
    
    // Campos comunes de Usuario
    private CamposUsuario camposUsuario;
    

    // Campos específicos para el tipo Empresa
    private CamposEmpresa camposEmpresa;

    // Campos específicos para el tipo Postulante
    private CamposPostulante camposPostulante;
    
   
    // Indicador de que tipo de usuario es
    private JPanel tipoUsuarioPanel; // Nuevo JPanel para contener los componentes
	private JLabel lblTipoUsuario;
	private JTextField tipoUsuarioField;

	// Campos específicos para la contrasenia
    private CamposContrasenia campoContrasenia;

	public InfoUsuario() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension maxPanelSize = new Dimension(Integer.MAX_VALUE, 1500);
	    
	    

	    // Inicializa los Jpanel de los campos
	    tipoUsuarioPanel = new JPanel();
	    tipoUsuarioPanel.setMaximumSize(maxPanelSize);
	    camposUsuario = new CamposUsuario();
        camposEmpresa = new CamposEmpresa();
        camposPostulante = new CamposPostulante();
        campoContrasenia = new CamposContrasenia();
     
        
	    // Agrega los componentes al tipoUsuarioPanel
        lblTipoUsuario = new JLabel("Tipo Usuario:");
	    tipoUsuarioField = new JTextField();
	    tipoUsuarioField.setEditable(false);
        tipoUsuarioPanel.setLayout(new GridLayout(1, 2));
        tipoUsuarioPanel.add(lblTipoUsuario);
        tipoUsuarioPanel.add(tipoUsuarioField);

	   

        // Agregar los JPanel de campos uno debajo del otro
        add(tipoUsuarioPanel);
        add(camposUsuario);
        add(campoContrasenia);
        add(camposEmpresa);
        add(camposPostulante);
        
	  

	    setEditable(false);
	    ocultarCampos();
	}
	

    private void setVisibleCamposUsuario(boolean visible) {  	
    	tipoUsuarioPanel.setVisible(visible);
    	camposUsuario.setVisible(visible);
    
    }

    private void setVisibleCamposEmpresa(boolean visible) {
        camposEmpresa.setVisible(visible);
    }

    private void setVisibleCamposPostulante(boolean visible) {
        camposPostulante.setVisible(visible);
    }
    
    private void setVisibleCampoContrasenia(boolean visible) {
        campoContrasenia.setVisible(visible);
    }


    
    private void ocultarCampos() {

    	setVisibleCamposUsuario(false);
    	
    	setVisibleCampoContrasenia(false);

    	setVisibleCamposEmpresa(false);

    	setVisibleCamposPostulante(false);
    	
    }
    
    public void setEditable(boolean habilitar) {
        camposUsuario.setEditable(habilitar);
        camposEmpresa.setEditable(habilitar);
        camposPostulante.setEditable(habilitar);
        campoContrasenia.setEditable(habilitar);
    }
    
    public void setEditable(boolean habilitar, boolean altaUsuario) {
        camposUsuario.setEditable(habilitar, altaUsuario);
        camposEmpresa.setEditable(habilitar);
        camposPostulante.setEditable(habilitar);
        campoContrasenia.setEditable(habilitar);
    }

    
    private void modoEmpresa(){
    	ocultarCampos();
        setVisibleCamposUsuario(true);
        setVisibleCamposEmpresa(true);
    	esEmpresa = true;
    	tipoUsuarioField.setText("Empresa");

    }
    
    private void modoPostulante(){
    	ocultarCampos();
        setVisibleCamposUsuario(true);
        setVisibleCamposPostulante(true);
    	esEmpresa = false;
    	tipoUsuarioField.setText("Postulante");
    }
    
    public void modoCrearPostulante() {
    	limpiar();
    	setEditable(true, true);
    	setVisibleCampoContrasenia(true);
    	modoPostulante();
    	conContrasenia = true;

    }
    
    public void modoCrearEmpresa() {
    	limpiar();
    	setEditable(true, true);
    	setVisibleCampoContrasenia(true);
    	modoEmpresa();
    	conContrasenia = true;

    }

    
    private void setDTUsuario(DTUsuario usuario) {
        limpiar();
        ocultarCampos();
               
        camposUsuario.setCampos(
        		usuario.getNickname(), 
        		usuario.getNombre(), 
        		usuario.getApellido(), 
        		usuario.getCorreo_electronico()
        );
        
        if (usuario instanceof DTPostulante) {
        	
        	DTPostulante postulante = (DTPostulante) usuario;
        	
        	modoPostulante();
        	
        	camposPostulante.setCampos(postulante.getFecha_nac(), postulante.getNacionalidad());
        	
        	
            
        } else if (usuario instanceof DTEmpresa) {
        	
        	DTEmpresa empresa = (DTEmpresa) usuario;
        	
        	modoEmpresa();
        	
        	camposEmpresa.setCampos(empresa.getNombreEmpresa(), empresa.getDescripcion(), empresa.getUrl());
        	
           
        }
        
        setEditable(false);
    }
    


    
    
    @Override
    public DTUsuario getDTUsuario() {
        String nickname = camposUsuario.getNickname();

        if (nickname.isEmpty()) {
            throw new IllegalArgumentException("No se ha ingresado un nickname.");
        }

        validar();

        String nombre = camposUsuario.getNombre();
        String apellido = camposUsuario.getApellido();
        String correo = camposUsuario.getCorreo();

        if (esEmpresa) {
            String nombreEmpresa = camposEmpresa.getNombreEmpresa();
            String descripcion = camposEmpresa.getDescripcion();
            String url = camposEmpresa.getUrl();

            return new DTEmpresa(nickname, correo, apellido, nombre, nombreEmpresa, descripcion, url);
        } else {
            LocalDate fechaNacimiento = camposPostulante.getFechaNacimiento();
            String nacionalidad = camposPostulante.getNacionalidad();

            return new DTPostulante(nickname, correo, apellido, nombre, "", fechaNacimiento, nacionalidad);
        }
    }

    
 





    @Override
    public boolean validar() {
        camposUsuario.validar();
        if (conContrasenia) {
            campoContrasenia.validar();
        }
        if (esEmpresa) {
            camposEmpresa.validar();
        } else {
            camposPostulante.validar();
        }

        return true;
    }





	@Override
	public void limpiar() {
		camposUsuario.limpiar();
	    campoContrasenia.limpiar();
	    camposEmpresa.limpiar();
	    camposPostulante.limpiar();
	    setEditable(false);
	    
	}




	@Override
	public String getContrasenia() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void reiniciarFormulario() {
		limpiar();
		
	}


	@Override
	public void modoAltaEmpresa() {
	    limpiar();
	    setEditable(true, true);
	    setVisibleCampoContrasenia(true);
	    modoEmpresa();
	    conContrasenia = true;
	}

	@Override
	public void modoAltaPostulante() {
	    limpiar();
	    setEditable(true, true);
	    setVisibleCampoContrasenia(true);
	    modoPostulante();
	    conContrasenia = true;
	}

	@Override
	public void mostrarEmpresa(DTEmpresa empresa) {
	    limpiar();
	    ocultarCampos();
	    modoEmpresa();
	    setDTUsuario(empresa);
	    setEditable(false);
	}

	@Override
	public void mostrarPostulante(DTPostulante postulante) {
	    limpiar();
	    ocultarCampos();
	    modoPostulante();
	    setDTUsuario(postulante);
	    setEditable(false);
	}

	@Override
	public void modificarEmpresa(DTEmpresa empresa) {
	    limpiar();
	    ocultarCampos();
	    modoEmpresa();
	    setDTUsuario(empresa);
	    setEditable(true);
	}

	@Override
	public void modificarPostulante(DTPostulante postulante) {
	    limpiar();
	    ocultarCampos();
	    modoPostulante();
	    setDTUsuario(postulante);
	    setEditable(true);
	}
	
	@Override
	public void modificarEmpresa(DTEmpresa empresa, String contrasenia) {
	    limpiar();
	    ocultarCampos();
	    modoEmpresa();
	    setDTUsuario(empresa);
	    setEditable(true);
	}

	@Override
	public void modificarPostulante(DTPostulante postulante, String contrasenia) {
	    limpiar();
	    ocultarCampos();
	    modoPostulante();
	    setDTUsuario(postulante);
	    setEditable(true);
	}


	
	
	
}