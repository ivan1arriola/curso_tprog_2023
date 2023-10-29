package controller;

import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class ListarTiposPublicacion
 */
@WebServlet("/tipospublicacion")
public class ListarTiposPublicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTiposPublicacion() {
        super();
        // TODO Auto-generated constructor stub
    }


    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);
		ILogica logica = FabricaWeb.getLogica();
		request.setAttribute("tiposOferta", logica.obtenerTipoOfertas());
		request.getRequestDispatcher("/WEB-INF/listar/tipospublicacion.jsp").forward(request, response);
	}

}
