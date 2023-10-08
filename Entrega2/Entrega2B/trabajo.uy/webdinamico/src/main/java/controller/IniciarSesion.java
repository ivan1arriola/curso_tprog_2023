package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTUsuario;

import java.io.IOException;
import java.util.Base64;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean evaluarCredenciales(String identificador, String contraseña) {
        return Fabrica.getInstance().getICtrlUsuario().validarCredenciales(identificador, contraseña);
    }
    
    private DTUsuario obtenerUsuario(String nickname) {
    	return Fabrica.getInstance().getICtrlUsuario().obtenerDatosUsuario(nickname);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false); // No crear una nueva sesión si no existe
	    if (session != null && session.getAttribute("usuario") != null) {
	        response.sendRedirect(request.getContextPath() + "/home");
	    } else {
	        request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Obtener los valores del formulario
		String identificador = request.getParameter("identificador-input");
		String contraseña = request.getParameter("password-input");
	    
	    // Llamar al método iniciarSesion y verificar el resultado
	    boolean credencialesValidadas = evaluarCredenciales(identificador, contraseña);
	    
	    if (credencialesValidadas) {
	    	// Si las credenciales son validadas crear una sesión y almacenar información en ella
            HttpSession session = request.getSession();
            DTUsuario usuario = obtenerUsuario(identificador);
            session.setAttribute("usuario", usuario.getNickname()); 
            byte[] imagenBytes = usuario.getImagen();
            if (imagenBytes != null) {
                String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
                session.setAttribute("imagen", imagenBase64);
            }

            // Redireccionar a una página de bienvenida
            response.sendRedirect(request.getContextPath() + "/home");
	    } else {
	        // Si el inicio de sesión falla, redireccionar de nuevo a la página de inicio de sesión con un mensaje de error
	        request.setAttribute("mensajeError", "Inicio de sesión fallido. Verifique sus credenciales.");
	        request.setAttribute("identificador", identificador); 
	        request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
	    }
	}


}
