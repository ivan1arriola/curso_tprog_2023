package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTHora;
import model.Datatypes.DTHorario;
import model.Datatypes.DTOfertaLaboral;
import model.Enumerados.DepUY;
import model.Enumerados.EstadoOL;

import java.io.IOException;
import java.time.LocalDate;

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
    
    //Simula la logica
    private DTOfertaLaboral getOfertaLaboral(String nombre) {
    	return new DTOfertaLaboral(
        	    "Desarrollador Frontend",
        	    "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
        	    LocalDate.of(2023, 8, 14),
        	    90000.0f,
        	    90000.0f,
        	    new DTHorario(new DTHora(9, 0), new DTHora(18, 0)),
        	    DepUY.Montevideo,
        	    "Montevideo",
        	    EstadoOL.Confirmada, // Cambiar a EstadoOL.CONFIRMADA
        	    null // Establecer la imagen como null
        	);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreOferta = request.getParameter("o");

        if (nombreOferta != null && !nombreOferta.isEmpty()) {
            try {
            	DTOfertaLaboral ofertaLaboral = getOfertaLaboral(nombreOferta);

                request.setAttribute("ofertaLaboral", ofertaLaboral);

                request.getRequestDispatcher("/WEB-INF/consultarOferta/infoOfertaLabora.jsp").forward(request, response);
            } catch (Exception e) {
            	// Redirigir a la página errorPage.jsp y pasar el mensaje de error como atributo de solicitud
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
        	
        	// El parámetro 'o' no se proporcionó en la URL.
        	String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: No se proporciono el nombre";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);  
            
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
