package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;

import java.io.IOException;

/**
 * Servlet implementation class ValidarNombreOferta
 */
@WebServlet("/validarnombreoferta")
public class ValidarNombreOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ValidarNombreOferta() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        
        String nombreOferta = request.getParameter("oferta");
        
        boolean existeOferta = servidor.existeOfertaLaboral(nombreOferta);
        
        if (!existeOferta) {
            response.setStatus(HttpServletResponse.SC_OK); // El nombre está disponible
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // El nombre ya está registrado
        }
        
		
		
	}

}
