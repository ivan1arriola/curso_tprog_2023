package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.OfertaLaboralBean;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;

import java.io.IOException;
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
        FabricaWeb.getInstance();
		this.logica = FabricaWeb.getLogica();
    }
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,  HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request,  response);

		
		
        String consulta = request.getParameter("search");
        String keyword = request.getParameter("key");

        Set<OfertaLaboralBean> ofertas = null;

        try {
        	if (keyword != null && !keyword.isEmpty()) {
        		ofertas = logica.buscarOfertasPorKeyword(keyword);
        		
        	} else if (consulta != null && !consulta.isEmpty()) {
                ofertas = logica.buscarOfertasPorInput(consulta);
            } else {
                ofertas = logica.listarDatosOfertas();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ofertas = logica.listarDatosOfertas();
            } catch (OfertaLaboralNoEncontrada_Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("ofertasLaborales",  ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/ofertaslaborales.jsp").forward(request,  response);
    }


}
