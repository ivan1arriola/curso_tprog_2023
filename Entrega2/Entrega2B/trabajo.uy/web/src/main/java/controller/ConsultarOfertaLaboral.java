package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ConsultarOfertaLaboral
 */
@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el valor del parámetro "oferta" de la URL
        String ofertaLaboral = request.getParameter("o");

        // Verificar si se proporcionó un valor para el parámetro
        if (ofertaLaboral != null && !ofertaLaboral.isEmpty()) {
            // Hacer algo con el valor del parámetro, por ejemplo, imprimirlo en la respuesta
            response.getWriter().append("Oferta Laboral: ").append(ofertaLaboral);
        } else {
            // Manejar el caso en el que el parámetro no se proporcionó
            response.getWriter().append("El parámetro 'o' no se proporcionó en la URL.");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
