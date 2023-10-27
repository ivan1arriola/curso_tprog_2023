package controller;

import enumerado.TipoUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.servidor.*;

import java.io.IOException;
import java.util.Base64;


@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServidorService servidorService;
    private Servidor servidor;
       

    public IniciarSesion() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("nickname") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/iniciarSesion/iniciarSesion.jsp").forward(request, response);
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identificador = request.getParameter("identificador-input");
        String contrasenia = request.getParameter("password-input");

        try {
            boolean credencialesValidadas = servidor.validarCredenciales(identificador, contrasenia);

            if (credencialesValidadas) {
                iniciarSesion(request, response, identificador);
            } else {
                request.setAttribute("mensajeError", "Inicio de sesión fallido. Verifique sus credenciales.");
                request.setAttribute("identificador", identificador);
                request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Ocurrió un error al iniciar sesión.");
            request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
        }

        doGet(request, response);
	}

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response, String identificador) throws Exception {
        DtUsuario usuario = servidor.obtenerDatosUsuario(identificador);
        HttpSession session = request.getSession();
        session.setAttribute("nickname", usuario.getNickname());

        TipoUsuario tipo;

        if (usuario instanceof DtPostulante) {
            tipo = TipoUsuario.POSTULANTE;
        } else if (usuario instanceof DtEmpresa) {
            tipo = TipoUsuario.EMPRESA;
        } else {
            throw new Exception("El tipo de usuario no es reconocido: " + usuario.getClass().getName());
        }

        session.setAttribute("tipoUsuario", tipo);

        byte[] imagenBytes = usuario.getImagen();

        if (imagenBytes != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            session.setAttribute("imagen", imagenBase64);
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }
	

}
