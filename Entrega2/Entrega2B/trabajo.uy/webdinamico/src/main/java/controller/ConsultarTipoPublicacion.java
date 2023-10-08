package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ConsultarTipoPublicacion
 */
@WebServlet("/consultartipopublicacion")
public class ConsultarTipoPublicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarTipoPublicacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipoPublicacion = request.getParameter("tp");

        if (tipoPublicacion != null && !tipoPublicacion.isEmpty()) {
            // Hacer algo con el valor del parámetro, por ejemplo, imprimirlo en la respuesta
            response.getWriter().append("Oferta Laboral: ").append(tipoPublicacion);
        } else {
            // Manejar el caso en el que el parámetro no se proporcionó
            response.getWriter().append("El parámetro 'tp' no se proporcionó en la URL.");
        }
	}

}
