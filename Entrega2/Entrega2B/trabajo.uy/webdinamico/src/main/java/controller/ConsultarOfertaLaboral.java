package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTOfertaExtendido;

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
    }
    
    private  DTOfertaExtendido getOfertaLaboral(String nombre) {
    	return Fabrica.getInstance().getICtrlOferta().obtenerOfertaLaboral(nombre);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreOferta = request.getParameter("o");

        if (nombreOferta != null && !nombreOferta.isEmpty()) {
            try {
            	DTOfertaExtendido ofertaLaboral = getOfertaLaboral(nombreOferta);
                request.setAttribute("ofertaLaboral", ofertaLaboral);
                request.getRequestDispatcher("/WEB-INF/consultarOferta/infoOfertaLabora.jsp").forward(request, response);
                
            } catch (Exception e) {
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
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
