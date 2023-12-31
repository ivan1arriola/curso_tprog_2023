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
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTUsuario;

/**
 * Servlet implementation class CrearPostulacion
 */

@WebServlet("/consultapostulacion")
public class consultaPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public consultaPostulacion() {
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
		
		DTPostulacion dtp = Fabrica.getInstance().getICtrlOferta().obtenerDatosPostulacionW(nickname, nombreOferta);
		request.setAttribute("CVRed", dtp.getcVitae());
		request.setAttribute("CVMot", dtp.getMotivacion());
		request.setAttribute("FechaPost", dtp.getFecha());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// SIN IMPLEMENTACIÓN. NO SE REQUIERE, SÓLO CONSULTA.
	}

}
