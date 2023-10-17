package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.OfertaLaboralBean;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.interfaces.ICtrlOferta;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import interfaces.ILogica;


/**
 * Servlet implementation class ListarOfertasLaborales
 */
@WebServlet("/ofertaslaborales")
public class ListarOfertasLaborales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ILogica logica;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarOfertasLaborales() {
        super();
        this.logica = FabricaWeb.getInstance().getLogica();
    }
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

		
		
        String consulta = request.getParameter("search");
        String keyword = request.getParameter("key");

        Set<OfertaLaboralBean> ofertas = null;

        try {
        	if(keyword != null && !keyword.isEmpty()) {
        		ofertas = logica.buscarOfertasPorKeyword(keyword);
        		
        	} else if (consulta != null && !consulta.isEmpty()) {
                ofertas = logica.buscarOfertasPorInput(consulta);
            } else {
                ofertas = logica.listarDatosOfertas();
            }
        } catch (Exception e) {
            // Handle the exception and provide an error message
            e.printStackTrace(); // Log the exception for debugging
            // In case of an error, fall back to getOfertasLaborales()
            ofertas = logica.listarDatosOfertas();
        }

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("ofertasLaborales", ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/ofertaslaborales.jsp").forward(request, response);
    }


}
