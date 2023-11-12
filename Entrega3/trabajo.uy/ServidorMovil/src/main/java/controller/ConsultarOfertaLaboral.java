package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.DtOfertaExtendido;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;

import java.io.IOException;


@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;

    public ConsultarOfertaLaboral() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nombreOferta = request.getParameter("oferta");
		try {
		DtOfertaExtendido oferta = servidor.obtenerOfertaLaboral(nombreOferta);
		request.setAttribute("oferta", oferta);
		request.getRequestDispatcher("/WEB-INF/consultarOferta/ofertaLaboral.jsp").forward(request, response);
		} catch (OfertaLaboralNoEncontrada_Exception exc) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
