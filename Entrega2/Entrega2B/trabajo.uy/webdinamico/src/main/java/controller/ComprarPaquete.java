package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet implementation class ComprarPaquete
 */
@WebServlet("/comprarpaquete")
public class ComprarPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paquete = request.getParameter("p");
		HttpSession session = request.getSession(false);
		String nickname = (String) session.getAttribute("nickname");
		
		int valor = (int) Fabrica.getInstance().getICtrlOferta().obtenerDatosPaquete(paquete).getCosto();
		
		Fabrica.getInstance().getICtrlOferta().compraPaquetes(nickname, paquete, LocalDate.now(), (int) valor);
		
		response.sendRedirect(request.getContextPath() + "/consultarpaquete?p=" + paquete);
				
				
		// TODO Auto-generated method stub
	}

}
