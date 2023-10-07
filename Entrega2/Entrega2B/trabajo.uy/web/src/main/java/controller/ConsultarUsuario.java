package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTUsuario;

import java.io.IOException;

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
      return Fabrica.getInstance().getICtrlUsuario().obtenerDatosUsuario(nick);
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("u");

        if (nickname != null && !nickname.isEmpty()) {
            try {
                DTUsuario usuario = obtenerDatosUsuario(nickname);
                request.setAttribute("usuario", usuario);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarUsuario/infoUsuario.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                String mensajeError = "Ocurrió un error al obtener los datos del usuario: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
        	String mensajeError = "Ocurrió un error al obtener los datos del usuario: No se proporciono el usuario";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);       
        }
    }


}
