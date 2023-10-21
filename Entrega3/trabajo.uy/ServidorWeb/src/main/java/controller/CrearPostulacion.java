package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.datatypes.DTUsuario;
import utils.FabricaWeb;

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
		FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

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
		String cvAbrev = request.getParameter("cvAbreviado");
		String motiv = request.getParameter("motivacion");
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute("nickname");
		String nombreOferta = request.getParameter("nombreOferta");
		Fabrica.getInstance().getICtrlOferta().altaPostulacion(nombreOferta, nickname,  cvAbrev,  motiv, null, LocalDate.now());
		response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
	}

}
