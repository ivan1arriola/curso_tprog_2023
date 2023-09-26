package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Datatypes.DTUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
    
    // Simulación de inicio de sesión
    private boolean evaluarCredenciales(String identificador, String contraseña) {
        // Simulamos un inicio de sesión exitoso si el identificador es "usuario" y la contraseña es "contraseña"
        return identificador.equals("usuario") && contraseña.equals("contraseña");
    }
    
    // Simula obtenerUsuario de la logica
    private DTUsuario obtenerUsuario(String nickname) {
    	return new DTUsuario(nickname, "prueba@prueba.com", "apellido", "nombre");
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
	        // Si no existe una sesión o el usuario no ha iniciado sesión, mostrar la página de inicio de sesión
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
            String imagen = (usuario.getImagen() == null) ? request.getContextPath() + "/nopicture.png" : usuario.getImagen();
            session.setAttribute("imagen", imagen);

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
