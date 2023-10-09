package controller;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTUsuario;

/**
 * Servlet implementation class CrearPostulacion
 */

@WebServlet("/crearpostulacion")
public class CrearPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CrearPostulacion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute("nickname");
		Fabrica fabrica = Fabrica.getInstance();
		DTUsuario usuario = fabrica.getICtrlUsuario().obtenerDatosUsuario(nickname);
		String postulante = usuario.getNombre() + " " + usuario.getApellido();
		request.setAttribute("postulante", postulante);
		String nombreOferta = request.getParameter("id");
		request.setAttribute("oferta", nombreOferta);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/crearPostulacion/crearPostulacion.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
