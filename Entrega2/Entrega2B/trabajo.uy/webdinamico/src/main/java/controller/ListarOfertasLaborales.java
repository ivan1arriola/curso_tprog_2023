package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTOfertaExtendido;

import java.io.IOException;
import java.util.HashSet;

/**
 * Servlet implementation class ListarOfertasLaborales
 */
@WebServlet("/ofertaslaborales")
public class ListarOfertasLaborales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarOfertasLaborales() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Funcion para simular la logica
    private HashSet<DTOfertaExtendido> getOfertasLaborales() {
        HashSet<DTOfertaExtendido> ofertas = Fabrica.getInstance().getICtrlOferta().listarOfertasLaboralesConfirmadas();
        return ofertas;
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene las ofertas laborales
        HashSet<DTOfertaExtendido> ofertas = getOfertasLaborales();

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("ofertasLaborales", ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/ofertaslaborales.jsp").forward(request, response);
    }

}
