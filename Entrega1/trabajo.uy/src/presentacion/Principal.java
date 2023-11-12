package presentacion;

import java.awt.EventQueue;

import javax.swing.*;
import logica.*;
import logica.Interfaces.ICtrlUsuario;
import logica.Interfaces.ICtrlOferta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

import java.io.FileReader;
import java.io.IOException;

import java.io.FileReader;
import java.io.IOException;

public class Principal {
    private JFrame frmGestionDeUsuarios;
    private ICtrlUsuario ICU;
    private AltaDeUsuario AltaDeUsuarioInternalFrame;
    private ConsultaDeUsuario ConsultaDeUsuarioInternalFrame;
    private ModificarDatosDeUsuario ModificarDatosDeUsuarioInternalFrame;
    private CargarDatos CargarDatosInternalFrame;
    private ConsultaDeOfertaLaboral ConsultaDeOfertaLaboralInternalFrame;
    private AltaOfertaLaboral AltaDeOfertaLaboralInternalFrame; // Falta implementar de acá en más
    private PostulacionOfertaLaboral PostulacionAOfertaLaboralInternalFrame;
    private AltaTipoPublicaciónOfertaLaboral AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame;
    private CrearPaqueteDeTiposPublicacionOfertasLaborales CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame;
    private AgregarTipodePublicacióndeOfertaLaboral AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame;
    private ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame;
    private AltaDeKeywords AltaDeKeywordsInternalFrame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmGestionDeUsuarios.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Principal() {
        initialize();

        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        ICtrlUsuario ICU = fabrica.getICtrlUsuario();
        ICtrlOferta ICO = fabrica.getICtrlOferta();
        
        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma, no es necesario crear y destruir objetos lo que enlentece la ejecución.
        // Cada InternalFrame usa un layout diferente, simplemente para mostrar distintas opciones.
        
        // Alta de Usuario
        AltaDeUsuarioInternalFrame = new AltaDeUsuario(frmGestionDeUsuarios, ICU);
        AltaDeUsuarioInternalFrame.setSize(408, 189);
        AltaDeUsuarioInternalFrame.setLocation(89, 77);
        AltaDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(AltaDeUsuarioInternalFrame);
        
        // Consulta de Usuario
        ConsultaDeUsuarioInternalFrame = new ConsultaDeUsuario(frmGestionDeUsuarios, ICU, ICO);
        ConsultaDeUsuarioInternalFrame.setSize(500, 500);
        AltaDeUsuarioInternalFrame.setLocation(0, 0);
        frmGestionDeUsuarios.getContentPane().add(ConsultaDeUsuarioInternalFrame);
        ConsultaDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(ConsultaDeUsuarioInternalFrame);
        
        
        // CargarDatos
        CargarDatosInternalFrame = new CargarDatos(ICU, ICO);
        CargarDatosInternalFrame.setSize(480, 189);
        CargarDatosInternalFrame.setLocation(89, 77);
        CargarDatosInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(CargarDatosInternalFrame);
        
        // ModificarDatosDeUsuario
        ModificarDatosDeUsuarioInternalFrame = new ModificarDatosDeUsuario();
        ModificarDatosDeUsuarioInternalFrame.setSize(550, 550);
        ModificarDatosDeUsuarioInternalFrame.setLocation(89, 77);
        ModificarDatosDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(ModificarDatosDeUsuarioInternalFrame);
        
        // ConsultaDeOfertaLaboral
        ConsultaDeOfertaLaboralInternalFrame = new ConsultaDeOfertaLaboral(frmGestionDeUsuarios, ICU);
        ConsultaDeOfertaLaboralInternalFrame.setSize(670, 600);
        ConsultaDeOfertaLaboralInternalFrame.setLocation(89, 77);
        ConsultaDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(ConsultaDeOfertaLaboralInternalFrame);

        // AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame
        AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame = new AgregarTipodePublicacióndeOfertaLaboral(ICO,ICU);
        AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame.setSize(600, 300);
        AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame.setLocation(89, 77);
        AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame);
        
        // ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame
        ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame = new ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales(ICO, ICU);
        ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setSize(700, 715);
        ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setLocation(89, 77);
        ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame);
        
        AltaDeKeywordsInternalFrame = new AltaDeKeywords(ICO, ICU);
        AltaDeKeywordsInternalFrame.setSize(500, 180);
        AltaDeKeywordsInternalFrame.setLocation(89, 77);
        AltaDeKeywordsInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(AltaDeKeywordsInternalFrame);
        
        PostulacionAOfertaLaboralInternalFrame = new PostulacionOfertaLaboral(ICO,ICU);
        PostulacionAOfertaLaboralInternalFrame.setSize(550, 290);
        PostulacionAOfertaLaboralInternalFrame.setLocation(89, 77);
        PostulacionAOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(PostulacionAOfertaLaboralInternalFrame);
        
        AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame = new AltaTipoPublicaciónOfertaLaboral();
        AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setSize(550, 290);
        AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setLocation(89, 77);
        AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame);

        CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame = new CrearPaqueteDeTiposPublicacionOfertasLaborales();
        CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setSize(570, 270);
        CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setLocation(89, 77);
        CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame);
        
        AltaDeOfertaLaboralInternalFrame = new AltaOfertaLaboral(ICU);
        AltaDeOfertaLaboralInternalFrame.setSize(550, 500);
        AltaDeOfertaLaboralInternalFrame.setLocation(89, 77);
        AltaDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(AltaDeOfertaLaboralInternalFrame);
        
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("trabajo.uy");
        frmGestionDeUsuarios.setBounds(80, 80, 811, 860);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem), los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem CD = new JMenuItem("Cargar datos");
        CD.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CargarDatosInternalFrame.setVisible(true);
        	}
        });
        menuSistema.add(CD);
        
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmGestionDeUsuarios.setVisible(false);
                frmGestionDeUsuarios.dispose();
            }
        });
        menuSistema.add(menuSalir);

        // CASOS DE USO VINCULADOS CON USUARIOS
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);
        
        // Se crea Alta de Usuario y se añade a la lista de "Casos de Uso"
        JMenuItem menuItemAltaDeUsuario = new JMenuItem("Alta de Usuario");
        menuItemAltaDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para dar de alta un usuario.
            	AltaDeUsuarioInternalFrame.setVisible(true);
            }
        });
     
        menuUsuarios.add(menuItemAltaDeUsuario); // se añade a los casos de uso
        
        // Se crea Consulta de Usuario y se añade a la lista de "Casos de Uso"
        JMenuItem menuItemConsultaDeUsuario = new JMenuItem("Consulta de Usuario");
        
        menuItemConsultaDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
            	ConsultaDeUsuarioInternalFrame.actualizar();
                ConsultaDeUsuarioInternalFrame.setVisible(true);
            }
        });   
        
        menuUsuarios.add(menuItemConsultaDeUsuario);
        
        // Se crea Consulta de Usuario y se añade a la lista de "Casos de Uso"
        JMenuItem menuItemModificarDatosDeUsuario = new JMenuItem("Modificar Datos de Usuario");
        
        menuItemModificarDatosDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
            	ModificarDatosDeUsuarioInternalFrame.actualizar();
            	ModificarDatosDeUsuarioInternalFrame.setVisible(true);
            }
        });
        
        menuUsuarios.add(menuItemModificarDatosDeUsuario);
        
        JMenu mnOfertaL = new JMenu("Oferta Laboral");
        menuBar.add(mnOfertaL);
        
        JMenuItem AltaDeOferta = new JMenuItem("Alta de Oferta Laboral");
        AltaDeOferta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AltaDeOfertaLaboralInternalFrame.actualizar();
        		AltaDeOfertaLaboralInternalFrame.setVisible(true);
        	}
        });

        mnOfertaL.add(AltaDeOferta);
        
        JMenuItem COL = new JMenuItem("Consulta de Oferta Laboral");
        COL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConsultaDeOfertaLaboralInternalFrame.actualizar();
        		ConsultaDeOfertaLaboralInternalFrame.setVisible(true); 
        	}
        });
        mnOfertaL.add(COL);
        
        JMenuItem POL = new JMenuItem("Postulación a Oferta Laboral");
        POL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	PostulacionAOfertaLaboralInternalFrame.actualizar();
            	PostulacionAOfertaLaboralInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(POL);
        
        JMenuItem ATPOL = new JMenuItem("Alta de Tipo de Publicación de Oferta Laboral");
        ATPOL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		AltaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setVisible(true);
        	}
        });
        mnOfertaL.add(ATPOL);
        
        JMenuItem CrearPTPOL = new JMenuItem("Crear Paquete de Tipos de publicación de Ofertas Laborales");
        CrearPTPOL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CrearPaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setVisible(true);
        	}
        });
        mnOfertaL.add(CrearPTPOL);
        
        JMenuItem ATPOLP = new JMenuItem("Agregar Tipo de publicación de Oferta Laboral a Paquete");
        ATPOLP.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame.actualizar();
        		AgregarTipoDePublicaciónDeOfertaLaboralAPaquetenternalFrame.setVisible(true);
        	}
        });
        mnOfertaL.add(ATPOLP);
        
        
        JMenuItem ConsultarPTPOL = new JMenuItem("Consulta de Paquete de Tipos de publicación de Ofertas Laborales");
        ConsultarPTPOL.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.actualizar();
        		ConsultaDePaqueteDeTiposDePublicaciónDeOfertasLaboralesInternalFrame.setVisible(true);
        	}
        });
        mnOfertaL.add(ConsultarPTPOL);

        
        JMenu Keywords = new JMenu("Keywords");
        menuBar.add(Keywords);
        
        JMenuItem AltaDeKeyword = new JMenuItem("Alta de Keywords");
        AltaDeKeyword.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		AltaDeKeywordsInternalFrame.setVisible(true);
        	}
        });
        Keywords.add(AltaDeKeyword);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        
    }
}
