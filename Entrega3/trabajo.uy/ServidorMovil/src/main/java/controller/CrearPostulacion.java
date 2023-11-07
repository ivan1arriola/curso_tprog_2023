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
import logica.servidor.DtOfertaExtendido;
import logica.servidor.DtUsuario;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;


@WebServlet("/crearpostulacion")
public class CrearPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;

    public CrearPostulacion() {
    	super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


	protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nickname = (String) request.getSession().getAttribute("nickname");
	    String nombreOferta = request.getParameter("oferta");
		DtOfertaExtendido oferta = null;
		boolean existePost = false;
		DtUsuario usuario;
		
		try {
			oferta = servidor.obtenerOfertaLaboral(nombreOferta); 
			usuario = servidor.obtenerDatosUsuario(nickname);
			if (oferta.getImagen()!= null) {
				request.setAttribute("imagenOferta", oferta.getImagen());
			}
			
			existePost = servidor.hayPostulacionW(nickname, nombreOferta);
		} catch (OfertaLaboralNoEncontrada_Exception e) {

			throw new RuntimeException(e);
		} catch (ExceptionUsuarioNoEncontrado_Exception e) {

			throw new RuntimeException(e);
		}


		String postulante = usuario.getNombre() + " " + usuario.getApellido();
		request.setAttribute("nickname", nickname);
		request.setAttribute("postulante", postulante);
		request.setAttribute("nombreOferta", nombreOferta);
		request.setAttribute("oferta", oferta);
		RequestDispatcher dispatcher;
		
		if (!existePost) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/crearPostulacion/crearPostulacion.jsp");
		} else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/crearPostulacion/postulacionexistente.jsp");
		}
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String cvAbrev = request.getParameter("cvAbreviado");
			String motiv = request.getParameter("motivacion");
			HttpSession session = request.getSession(false);
			String nickname = (String) session.getAttribute("nickname");
			String nombreOferta = request.getParameter("nombreOferta");
			String videoYouTube = request.getParameter("videoYouTube");
			String currentDateStr = LocalDate.now().toString();

		servidor.altaPostulacion(nombreOferta, nickname,  cvAbrev,  motiv, "", currentDateStr, videoYouTube);
			response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
			
		} catch (ExceptionUsuarioNoEncontrado_Exception | OfertaLaboralNoEncontrada_Exception e) {
			throw new RuntimeException(e);
		}

	}

}

