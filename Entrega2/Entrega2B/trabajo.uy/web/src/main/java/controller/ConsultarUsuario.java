package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTPostulante;
import model.Datatypes.DTUsuario;

import java.io.IOException;
import java.time.LocalDate;

import excepciones.UsuarioNoExisteException;

/**
 * Servlet implementation class ConsultarUsuario
 */
@WebServlet("/consultarusuario")
public class ConsultarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public DTUsuario obtenerDatosUsuario(String nick) throws UsuarioNoExisteException {
        if ("lgarcia".equals(nick)) {
            // Realizar la acción correspondiente para lgarcia
            DTUsuario datosUsuario = new DTPostulante("lgarcia", "lgarcia85@gmail.com", "García", "Lucía", null, LocalDate.of(1985, 3, 15), "Uruguaya");
            return datosUsuario;
        } else {
            throw new UsuarioNoExisteException(nick);
        }
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("u");

        if (nickname != null && !nickname.isEmpty()) {
            try {
                DTUsuario usuario = obtenerDatosUsuario(nickname);

                // Guardar el objeto DTUsuario en el atributo de solicitud para que esté disponible en infoUsuario.jsp
                request.setAttribute("usuario", usuario);

                // Redirigir a la página infoUsuario.jsp
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultar/infoUsuario.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                // Redirigir a la página errorPage.jsp y pasar el mensaje de error como atributo de solicitud
                String mensajeError = "Ocurrió un error al obtener los datos del usuario: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // El parámetro 'u' no se proporcionó en la URL.
        	String mensajeError = "Ocurrió un error al obtener los datos del usuario: No se proporciono el usuario";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);        }
    }


}
