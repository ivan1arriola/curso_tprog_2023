package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.Fabrica;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class AltaUsuario
 */
@WebServlet("/altausuario")
public class AltaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
        }
    }
    
    private boolean altaEmpresaURL(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
    	return Fabrica.getInstance().getICtrlUsuario().altaEmpresaURLyImagen(nick, contraseña, nombre, apellido, mail, desc, URL, null);
	} 
    
    
    public boolean altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
		return Fabrica.getInstance().getICtrlUsuario().altaPostulanteImagen(nick, contraseña, nombre, apellido, fecha_nac, mail, nacionalidad, null);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String tipoUsuario = request.getParameter("tipo-usuario");

        String descripcionEmpresa = null;
        String sitioWebEmpresa = null;
        String fechaNacimiento = null;
        String nacionalidad = null;

        try {
            boolean registroExitoso;

            if ("empresa".equals(tipoUsuario)) {
                descripcionEmpresa = request.getParameter("descripcion");
                sitioWebEmpresa = request.getParameter("sitio-web");
                registroExitoso = altaEmpresaURL(nickname, password, nombre, apellido, email, descripcionEmpresa, sitioWebEmpresa);
            } else {
                fechaNacimiento = request.getParameter("fecha-nacimiento");
                nacionalidad = request.getParameter("nacionalidad");
                registroExitoso = altaPostulante(nickname, password, nombre, apellido, email, LocalDate.parse(fechaNacimiento), nacionalidad);
            }

            if (registroExitoso) {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } catch (ExceptionUsuarioCorreoRepetido e) {
            manejarExcepcion(request, response, "El correo electrónico ya está registrado. Elija otro");
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            manejarExcepcion(request, response, "El nick y el correo electrónico ya están registrados. Elija otros");
        } catch (ExceptionUsuarioNickRepetido e) {
            manejarExcepcion(request, response, "El nickname ya está registrado. Elija otro");
        }
    

    }
    
    private void manejarExcepcion(HttpServletRequest request, HttpServletResponse response, String mensajeAlerta) throws ServletException, IOException {
        request.setAttribute("alert", mensajeAlerta);
        request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
    }



}
