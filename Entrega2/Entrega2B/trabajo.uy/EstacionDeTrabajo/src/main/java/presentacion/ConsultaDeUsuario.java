package main.java.presentacion;
//

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.java.excepciones.ExceptionEmpresaInvalida;
import main.java.excepciones.ExceptionUsuarioNoEncontrado;
import main.java.logica.Datatypes.DTEmpresa;
import main.java.logica.Datatypes.DTPostulante;
import main.java.logica.Datatypes.DTUsuario;
import main.java.logica.Interfaces.ICtrlOferta;
import main.java.logica.Interfaces.ICtrlUsuario;
import main.java.presentacion.componentes.InfoUsuario;
import main.java.presentacion.componentes.ListarUsuariosObserver;
import main.java.presentacion.componentes.SelectorUsuario;

public class ConsultaDeUsuario extends JInternalFrame implements ListarUsuariosObserver {

	private static final long serialVersionUID = -2688641613552122875L;
	private ICtrlUsuario controlUsr;
    private ICtrlOferta controlOfer;
    private SelectorUsuario selector;
    private InfoUsuario usuarioInfo;
    private JButton btnCerrar;

    public ConsultaDeUsuario(JFrame base, ICtrlUsuario icu, ICtrlOferta ico) {

        controlUsr = icu;
        controlOfer = ico;

        setResizable(true);
        setIconifiable(false);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta de Usuario");
        setBounds(30, 30, 500, 550);

        getContentPane().setLayout(new BorderLayout());

        // Panel en el norte
        selector = new SelectorUsuario();
        selector.setObserver(this);
        getContentPane().add(selector, BorderLayout.NORTH);

        // Panel en el centro
        usuarioInfo = new InfoUsuario();
        getContentPane().add(usuarioInfo, BorderLayout.CENTER);

        // Panel en el sur
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton btnVerOfertas = new JButton("Ver Ofertas");
        panel.add(btnVerOfertas);

        btnCerrar = new JButton("Cerrar");
        panel.add(btnCerrar);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        btnVerOfertas.setVisible(true);

        btnVerOfertas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedUsuario = selector.getUsuarioNickname();
                if ( selectedUsuario!= null) {
                    
                    Set<String> offerDetails;
                    try {
                        offerDetails = controlUsr.listarOfertasLaborales(selectedUsuario);
                        if (offerDetails.isEmpty()) {
                            JOptionPane.showMessageDialog(
                                    ConsultaDeUsuario.this,
                                    "No hay ofertas registradas para este usuario.",
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            ConsultarOfertas detallesOferta = new ConsultarOfertas(offerDetails, controlOfer, controlUsr, selectedUsuario);
                            detallesOferta.setVisible(true);
                            getContentPane().add(detallesOferta, BorderLayout.CENTER);
                            detallesOferta.toFront();
                        }
                    } catch (ExceptionEmpresaInvalida | ExceptionUsuarioNoEncontrado e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnVerOfertas.setVisible(true);
    }

    public void actualizar() {
        selector.setUsuarios(controlUsr.listarNicknamesUsuarios());
    }

    @Override
    public void desplegarInfo(String selectedUser) {
        if (!selectedUser.equals("Selecciona un usuario")) {
            try {
                DTUsuario usuario = controlUsr.obtenerDatosUsuario(selectedUser);
                if (usuario instanceof DTEmpresa) {
                    usuarioInfo.mostrarEmpresa((DTEmpresa) usuario);
                } else if (usuario instanceof DTPostulante) {
                    usuarioInfo.mostrarPostulante((DTPostulante) usuario);
                }
            } catch (Exception e) {
                // Captura la excepción y muestra un mensaje de error al usuario
                JOptionPane.showMessageDialog(
                    this,
                    "Error al obtener los datos del usuario. El usuario no existe.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    
   
    
   


    @Override
    public void limpiarInfo() {
    	usuarioInfo.reiniciarFormulario();
    }
}
