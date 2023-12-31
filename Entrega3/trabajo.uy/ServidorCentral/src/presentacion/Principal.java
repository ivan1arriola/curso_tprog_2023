package presentacion;

import excepciones.NoExistePaquete;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;
//import logica.manejadores.UsuarioHandler;
import logica.servidor.Servidor;

//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JDesktopPane;
//import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JLabel;
//import javax.swing.JDialog;
//import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.JPasswordField;
//import javax.swing.JTable;
//import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JSpinner;
//import javax.swing.JSplitPane;
//import javax.swing.JRadioButton;
//import javax.swing.DefaultListModel;
//import javax.swing.ListSelectionModel;
//import javax.swing.JComponent;
//import javax.swing.SpinnerNumberModel;
//import javax.swing.ButtonGroup;

//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.awt.Font;


public class Principal {
    private JFrame frmGestionDeUsuarios;
    private AltaDeUsuario altaDeUsuarioInternalFrame;
    private ConsultaDeUsuario consultaDeUsuarioInternalFrame;
    private ModificarDatosDeUsuarioV2 modificarDatosDeUsuarioInternalFrame;
    private CargarDatos cargarDatosInternalFrame;
    private ConsultaDeOfertaLaboral consultaDeOfertaLaboralInternalFrame;
    private AltaOfertaLaboral altaDeOfertaLaboralInternalFrame; // Falta implementar de acá en más
    private PostulacionOfertaLaboral postulacionAOfertaLaboralInternalFrame;
    private AltaTipoPublicaciónOfertaLaboral altaDeTipoDePublicacionDeOfertaLaboralInternalFrame;
    private CrearPaqueteDeTiposPublicacionOfertasLaborales crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame;
    private AgregarTipodePublicacióndeOfertaLaboral agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame;
    
    private ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame;
    private AltaDeKeywords altaDeKeywordsInternalFrame;
    private AceptarOferta aceptarOfertaInternalFrame;
    private ConsultaOfertasMasVisitadas consultaOfertasMasVisitadasInternalFrame;

    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    private void empezarConexion() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUy");
        entityManager = entityManagerFactory.createEntityManager();
    }
    private void cerrarConexion() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }

        entityManager = null;
        entityManagerFactory = null;
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


        // Base de Datos
        empezarConexion();
        KeywordHandler.setBaseDatos(entityManager);
        OfertaLaboralHandler.setBaseDatos(entityManager);
        PaqueteHandler.setBaseDatos(entityManager);
        TipoOfertaHandler.setBaseDatos(entityManager);
        UsuarioHandler.setBaseDatos(entityManager);






        // Se crean los tres InternalFrame y se incluyen al Frame principal ocultos.
        // De esta forma,   no es necesario crear y destruir objetos lo que enlentece la ejecución.
        // Cada InternalFrame usa un layout diferente,   simplemente para mostrar distintas opciones.

        // Alta de Usuario
        altaDeUsuarioInternalFrame = new AltaDeUsuario(frmGestionDeUsuarios,  ICU);
        altaDeUsuarioInternalFrame.setSize(408,  189);
        altaDeUsuarioInternalFrame.setLocation(89,  77);
        altaDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(altaDeUsuarioInternalFrame);

        // Consulta de Usuario
        consultaDeUsuarioInternalFrame = new ConsultaDeUsuario(frmGestionDeUsuarios,  ICU,  ICO);
        consultaDeUsuarioInternalFrame.setSize(500,  510);
        consultaDeUsuarioInternalFrame.setLocation(89,  77);
        consultaDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(consultaDeUsuarioInternalFrame);
        //frmGestionDeUsuarios.getContentPane().add(consultaDeUsuarioInternalFrame);


        // CargarDatos
        cargarDatosInternalFrame = new CargarDatos(ICU,  ICO);
        cargarDatosInternalFrame.setSize(480,  189);
        cargarDatosInternalFrame.setLocation(89,  77);
        cargarDatosInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(cargarDatosInternalFrame);

        // ModificarDatosDeUsuario
        modificarDatosDeUsuarioInternalFrame = new ModificarDatosDeUsuarioV2(frmGestionDeUsuarios,  ICU);
        modificarDatosDeUsuarioInternalFrame.setSize(450,  190);
        modificarDatosDeUsuarioInternalFrame.setLocation(89,  77);
        modificarDatosDeUsuarioInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(modificarDatosDeUsuarioInternalFrame);

        // ConsultaDeOfertaLaboral
        consultaDeOfertaLaboralInternalFrame = new ConsultaDeOfertaLaboral(frmGestionDeUsuarios,  ICU);
        consultaDeOfertaLaboralInternalFrame.setSize(620,  660);
        consultaDeOfertaLaboralInternalFrame.setLocation(89,  77);
        consultaDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(consultaDeOfertaLaboralInternalFrame);

        // agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame;
        agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame = new AgregarTipodePublicacióndeOfertaLaboral(ICO,  ICU);
        agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame.setSize(600,  250);
        agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame.setLocation(89,  77);
        agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame);

        // consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame
        consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame = new ConsultadePaquetedeTiposdePublicacióndeOfertasLaborales(ICO,  ICU);
        consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setSize(700,  715);
        consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setLocation(89,  77);
        consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame);

        altaDeKeywordsInternalFrame = new AltaDeKeywords(ICO,  ICU);
        altaDeKeywordsInternalFrame.setSize(500,  180);
        altaDeKeywordsInternalFrame.setLocation(89,  77);
        altaDeKeywordsInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(altaDeKeywordsInternalFrame);

        postulacionAOfertaLaboralInternalFrame = new PostulacionOfertaLaboral(ICO,  ICU);
        postulacionAOfertaLaboralInternalFrame.setSize(510,  290);
        postulacionAOfertaLaboralInternalFrame.setLocation(89,  77);
        postulacionAOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(postulacionAOfertaLaboralInternalFrame);

        altaDeTipoDePublicacionDeOfertaLaboralInternalFrame = new AltaTipoPublicaciónOfertaLaboral();
        altaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setSize(500,  350);
        altaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setLocation(89,  77);
        altaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(altaDeTipoDePublicacionDeOfertaLaboralInternalFrame);

        crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame = new CrearPaqueteDeTiposPublicacionOfertasLaborales();
        crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setSize(570,  270);
        crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setLocation(89,  77);
        crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame);


        altaDeOfertaLaboralInternalFrame = new AltaOfertaLaboral(ICU);
        altaDeOfertaLaboralInternalFrame.setSize(550,  550);
        altaDeOfertaLaboralInternalFrame.setLocation(50,  50);
        altaDeOfertaLaboralInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(altaDeOfertaLaboralInternalFrame);

        aceptarOfertaInternalFrame = new AceptarOferta(ICO,  ICU);
        aceptarOfertaInternalFrame.setSize(400,  260);
        aceptarOfertaInternalFrame.setLocation(89,  77);
        aceptarOfertaInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(aceptarOfertaInternalFrame);
        
        consultaOfertasMasVisitadasInternalFrame = new ConsultaOfertasMasVisitadas(ICO,  ICU);
        consultaOfertasMasVisitadasInternalFrame.setSize(700,  270); // revisar tamaño
        consultaOfertasMasVisitadasInternalFrame.setLocation(89,  77);
        consultaOfertasMasVisitadasInternalFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(consultaOfertasMasVisitadasInternalFrame);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmGestionDeUsuarios.setVisible(true);
                    (new Servidor()).publicar();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        // Se crea el Frame con las dimensiones indicadas.
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Estacion de Trabajo");
        frmGestionDeUsuarios.setBounds(80,  80,  811,  860);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se crea una barra de menú (JMenuBar) con dos menú (JMenu) desplegables.
        // Cada menú contiene diferentes opciones (JMenuItem),   los cuales tienen un 
        // evento asociado que permite realizar una acción una vez se seleccionan. 
        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem CargarDatos = new JMenuItem("Cargar datos");
        CargarDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                cargarDatosInternalFrame.setVisible(true);
            }
        });
        menuSistema.add(CargarDatos);

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
            public void actionPerformed(ActionEvent evento) {
                // Muestro el InternalFrame para dar de alta un usuario.
                altaDeUsuarioInternalFrame.setVisible(true);
            }
        });

        menuUsuarios.add(menuItemAltaDeUsuario); // se añade a los casos de uso

        // Se crea Consulta de Usuario y se añade a la lista de "Casos de Uso"
        JMenuItem menuItemConsultaDeUsuario = new JMenuItem("Consulta de Usuario");

        menuItemConsultaDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                // Muestro el InternalFrame para registrar un usuario
                consultaDeUsuarioInternalFrame.actualizar();
                consultaDeUsuarioInternalFrame.setVisible(true);
            }
        });

        menuUsuarios.add(menuItemConsultaDeUsuario);

        // Se crea Consulta de Usuario y se añade a la lista de "Casos de Uso"
        JMenuItem menuItemModificarDatosDeUsuario = new JMenuItem("Modificar Datos de Usuario");

        menuItemModificarDatosDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                // Muestro el InternalFrame para registrar un usuario
                modificarDatosDeUsuarioInternalFrame.actualizar();
                modificarDatosDeUsuarioInternalFrame.setVisible(true);
            }
        });

        menuUsuarios.add(menuItemModificarDatosDeUsuario);

        JMenu mnOfertaL = new JMenu("Oferta Laboral");
        menuBar.add(mnOfertaL);

        JMenuItem AltaDeOferta = new JMenuItem("Alta de Oferta Laboral");
        AltaDeOferta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                altaDeOfertaLaboralInternalFrame.actualizar();
                altaDeOfertaLaboralInternalFrame.setVisible(true);
            }
        });

        mnOfertaL.add(AltaDeOferta);

        JMenuItem COL = new JMenuItem("Consulta de Oferta Laboral");
        COL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                consultaDeOfertaLaboralInternalFrame.actualizar();
                consultaDeOfertaLaboralInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(COL);

        JMenuItem POL = new JMenuItem("Postulación a Oferta Laboral");
        POL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                postulacionAOfertaLaboralInternalFrame.actualizar();
                postulacionAOfertaLaboralInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(POL);

        JMenuItem ATPOL = new JMenuItem("Alta de Tipo de Publicación de Oferta Laboral");
        ATPOL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                altaDeTipoDePublicacionDeOfertaLaboralInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(ATPOL);

        JMenuItem CrearPTPOL = new JMenuItem("Crear Paquete de Tipos de publicación de Ofertas Laborales");
        CrearPTPOL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                crearPaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(CrearPTPOL);

        JMenuItem ATPOLP = new JMenuItem("Agregar Tipo de publicación de Oferta Laboral a Paquete");
        ATPOLP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame.actualizar();
                } catch (NoExistePaquete e) {
                    throw new RuntimeException(e);
                }
                agregarTipoDePublicacionDeOfertaLaboralAPaquetenternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(ATPOLP);


        JMenuItem ConsultarPTPOL = new JMenuItem("Consulta de Paquete de Tipos de publicación de Ofertas Laborales");
        ConsultarPTPOL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.actualizar();
                consultaDePaqueteDeTiposDePublicacionDeOfertasLaboralesInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(ConsultarPTPOL);


        JMenuItem aceptarOffer = new JMenuItem("Aceptar/Rechazar Oferta");
        aceptarOffer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                aceptarOfertaInternalFrame.actualizar();
                aceptarOfertaInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(aceptarOffer);
        
        JMenuItem consOfMasVis = new JMenuItem("Ofertas laborales más visitadas");
        consOfMasVis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String operacionInicial = "C";
                consultaOfertasMasVisitadasInternalFrame.actualizarTabla(operacionInicial);
                consultaOfertasMasVisitadasInternalFrame.setVisible(true);
            }
        });
        mnOfertaL.add(consOfMasVis);


        JMenu Keywords = new JMenu("Keywords");
        menuBar.add(Keywords);

        JMenuItem AltaDeKeyword = new JMenuItem("Alta de Keywords");
        AltaDeKeyword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                altaDeKeywordsInternalFrame.setVisible(true);
            }
        });
        Keywords.add(AltaDeKeyword);
        frmGestionDeUsuarios.getContentPane().setLayout(null);

    }
}
