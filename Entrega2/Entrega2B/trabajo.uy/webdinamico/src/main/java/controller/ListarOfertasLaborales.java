package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.interfaces.ICtrlOferta;

import java.io.IOException;
import java.util.HashSet;

import org.apache.jasper.tagplugins.jstl.core.Set;

/**
 * Servlet implementation class ListarOfertasLaborales
 */
@WebServlet("/ofertaslaborales")
public class ListarOfertasLaborales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICtrlOferta ctrlOferta;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarOfertasLaborales() {
        super();
        this.ctrlOferta = Fabrica.getInstance().getICtrlOferta();
    }
    
    private HashSet<DTOfertaExtendido> getOfertasLaborales() {
        HashSet<DTOfertaExtendido> ofertas = (HashSet<DTOfertaExtendido>) ctrlOferta.listarOfertasLaboralesConfirmadas();
        return ofertas;
    }
    
    
    
    
    private HashSet<DTOfertaExtendido> buscarPorInput(String consulta){
    	HashSet<String> ofertas = (HashSet<String>) ctrlOferta.listarOfertasLaboralesConfirmadas(consulta);
    	
    	if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
    	
    	for( String nombreOferta : ofertas) {
    		DTOfertaExtendido dtOferta = ctrlOferta.obtenerOfertaLaboral(nombreOferta);
    		dtOfertas.add(dtOferta);
    	}
    	
    	
		return dtOfertas;
    	
    	
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consulta = request.getParameter("search");
        String keyword = request.getParameter("key");

        HashSet<DTOfertaExtendido> ofertas = null;

        try {
        	if(keyword != null && !keyword.isEmpty()) {
        		ofertas = buscarPorKeyword(keyword);
        		
        	} else if (consulta != null && !consulta.isEmpty()) {
                ofertas = buscarPorInput(consulta);
            } else {
                ofertas = getOfertasLaborales();
            }
        } catch (Exception e) {
            // Handle the exception and provide an error message
            e.printStackTrace(); // Log the exception for debugging
            // In case of an error, fall back to getOfertasLaborales()
            ofertas = getOfertasLaborales();
        }

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("ofertasLaborales", ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/ofertaslaborales.jsp").forward(request, response);
    }

	private HashSet<DTOfertaExtendido> buscarPorKeyword(String keyword) {
		HashSet<String> ofertas = (HashSet<String>) ctrlOferta.listarOfertasLaboralesKeywords(keyword);
		
		if(ofertas.isEmpty()) {
    		return null;
    	}
    	
    	HashSet<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();
    	
    	for( String nombreOferta : ofertas) {
    		DTOfertaExtendido dtOferta = ctrlOferta.obtenerOfertaLaboral(nombreOferta);
    		dtOfertas.add(dtOferta);
    	}
    	
    	
		return dtOfertas;
	}


}
