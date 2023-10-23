package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import utils.FabricaWeb;
import webservice.TipoUsuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import interfaces.ILogica;

@WebServlet("/consultarusuario")
public class ConsultarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarUsuario() {
        super();
        
        logica = FabricaWeb.getInstance().getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

        String nicknameParametro = request.getParameter("u");
        String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");
        TipoUsuario tipoUsuarioLogueado = (TipoUsuario) request.getSession().getAttribute("tipoUsuario");

        //ILogica logica = FabricaWeb.getInstance().getLogica();

        if (nicknameParametro != null && !nicknameParametro.isEmpty()) {
            try {
                UsuarioBean usuario = logica.obtenerDatosUsuario(nicknameParametro);

                // valida que en efecto los datos de la session coinciden con los de la logica en caso de consultar su propio perfil
                boolean consultaSuPerfil = validarConsultaUsuario(nicknameParametro, nicknameUsuarioLogueado, tipoUsuarioLogueado, usuario);

                if (consultaSuPerfil && usuario.getTipo() == TipoUsuario.EMPRESA) {
                    usuario = cargarPaquete(usuario, nicknameParametro);
                    usuario = cargarOfertasLaborales(usuario, nicknameParametro, true );
                }
                if (consultaSuPerfil && usuario.getTipo() == TipoUsuario.POSTULANTE) {
                    usuario = cargarPostulaciones(usuario, nicknameParametro);
                }
                
                if(!consultaSuPerfil && usuario.getTipo() == TipoUsuario.EMPRESA) {
                	usuario = cargarOfertasLaborales(usuario, nicknameParametro, false);
                }

                request.setAttribute("consultaSuPerfil", consultaSuPerfil);
                request.setAttribute("usuario", usuario);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarUsuario/consultarUsuario.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                String mensajeError = "Ocurrió un error al obtener los datos del usuario: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            String mensajeError = "Ocurrió un error al obtener los datos del usuario: No se proporcionó el usuario";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }



	
    private UsuarioBean cargarOfertasLaborales(UsuarioBean usuario, String nicknameParametro, boolean mostrarTodas) {
        Set<String> nombresOfertas;
        
        if (!mostrarTodas) {
            nombresOfertas = logica.listarOfertasConfirmadasDeEmpresa(nicknameParametro);
        } else {
            nombresOfertas = logica.listarOfertasLaboralesDeEmpresa(nicknameParametro);
        }
        
        Set<OfertaLaboralBean> ofertasLaborales = new HashSet<OfertaLaboralBean>();
        
        if (nombresOfertas != null && !nombresOfertas.isEmpty()) {
            for (String nombre : nombresOfertas) {
                ofertasLaborales.add(logica.obtenerDatosOfertaLaboral(nombre));
            }
        }
        
        usuario.setOfertasLaborales(ofertasLaborales);
        return usuario;
    }


	private UsuarioBean cargarPostulaciones(UsuarioBean usuario, String nicknameParametro) {
		Set<String> nombreOfertasConPostulacion = logica.listarPostulacionesDePostulante(nicknameParametro);
		Set<PostulacionBean> postulaciones = new HashSet<PostulacionBean>();
		
		if (nombreOfertasConPostulacion != null && !nombreOfertasConPostulacion.isEmpty()) {
            for (String nombreOferta : nombreOfertasConPostulacion) {
            	postulaciones.add(logica.obtenerDatosPostulacion(nombreOferta, nicknameParametro));
            }
        }
		
		usuario.setPostulaciones(postulaciones);
		
		return usuario;
	}



	public UsuarioBean cargarPaquete(UsuarioBean usuario, String nickname) {
        Set<String> nombresPaquetes = logica.listarPaquetesDeEmpresa(nickname);  	
        Set<PaqueteBean> paquetes = new HashSet<PaqueteBean>();
        if (nombresPaquetes != null && !nombresPaquetes.isEmpty()) {
            for (String nombre : nombresPaquetes) {
            	paquetes.add(logica.obtenerDatosPaquete(nombre));
            }
        }
        usuario.setPaquetes(paquetes);
        return usuario;
    }
    
	
    
    
    

    private boolean validarConsultaUsuario(String nicknameParametro, String nicknameUsuarioLogueado, TipoUsuario tipoUsuarioLogueado, UsuarioBean usuario) throws Exception {
        if (usuario.getError() != null) {
            throw new Exception(usuario.getError());
        }

        boolean consultaSuPerfil = nicknameParametro.equals(nicknameUsuarioLogueado);

        if (consultaSuPerfil && !nicknameParametro.equals(usuario.getNickname())) {
            throw new Exception("El nickname en la lógica no coincide con el nickname de la sesión");
        }

        if (consultaSuPerfil && !usuario.getTipo().equals(tipoUsuarioLogueado)) {
            throw new Exception("El tipo de usuario en la lógica no coincide con el tipo de usuario de la sesión");
        }

        return consultaSuPerfil;
    }
}
