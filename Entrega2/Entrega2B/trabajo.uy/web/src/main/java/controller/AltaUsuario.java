package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;

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
        // Verificar si ya existe una sesión
        HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
        if (session != null && session.getAttribute("usuario") != null) {
            // Si existe una sesión y el atributo "usuario" está presente, redirigir a la página de inicio
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Si no existe una sesión o el usuario no ha iniciado sesión, mostrar la página de alta de usuario
            request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
        }
    }
    
    private boolean altaEmpresaURL(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
    	throw new ExceptionUsuarioNickRepetido("El nickname ya está en uso.");
	} 
    
    
    public boolean altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
		return true;
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
