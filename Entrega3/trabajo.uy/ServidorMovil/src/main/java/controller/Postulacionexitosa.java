package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.servidor.*;
import java.io.IOException;
//import java.util.Base64;


@WebServlet("/postulacionexitosa")
public class Postulacionexitosa extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServidorService servidorService;
    private Servidor servidor;
       

    public Postulacionexitosa() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("nickname") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/crearPostulacion/postulacionExitosa.jsp").forward(request, response);
        }
        */
		//String nombre = (String) request.getSession().getAttribute("postulante");
		
        request.getRequestDispatcher("/WEB-INF/crearPostulacion/postulacionExitosa.jsp").forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}

