package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import interfaces.ILogica;

import utils.FabricaWeb;
import webservice.TipoPublicacionBeanServidor;

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

	private Set<TipoPublicacionBeanServidor> obtenerTipoOfertas(){
		ILogica ctrl = FabricaWeb.getInstance().getLogica();
		Set<String> lista =  (HashSet<String>) ctrl.listarTipoDePublicaciones();
		Set<TipoPublicacionBeanServidor> tipoOfertas = new HashSet<TipoPublicacionBeanServidor>();
		for (String nombreTipoOferta : lista) {
			tipoOfertas.add(ctrl.obtenerDatosTipoPublicacion(nombreTipoOferta));
		}
		
		return tipoOfertas;
		
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

		request.setAttribute("tiposOferta", obtenerTipoOfertas());
		request.getRequestDispatcher("/WEB-INF/listar/tipospublicacion.jsp").forward(request, response);
	}

}
