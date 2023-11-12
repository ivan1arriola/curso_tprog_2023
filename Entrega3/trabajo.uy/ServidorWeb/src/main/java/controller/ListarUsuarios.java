package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.UsuarioBean;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.Set;

import interfaces.ILogica;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/usuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarUsuarios() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);
		ILogica logica = FabricaWeb.getInstance().getLogica();

        Set<UsuarioBean> ofertas = logica.listarUsuarios();

        request.setAttribute("usuarios", ofertas);

        request.getRequestDispatcher("/WEB-INF/listar/usuarios.jsp").forward(request, response);
    
    }

}
