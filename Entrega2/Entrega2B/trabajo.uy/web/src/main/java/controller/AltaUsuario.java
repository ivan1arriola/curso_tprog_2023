package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Genera un nuevo usuario en el sistema
		doGet(request, response);
	}

}
