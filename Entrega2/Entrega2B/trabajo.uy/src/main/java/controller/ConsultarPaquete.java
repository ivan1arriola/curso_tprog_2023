package controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Datatypes.*;

/**
 * Servlet implementation class ConsultarPaquete
 */
@WebServlet("/consultarpaquete")
public class ConsultarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Obtener el valor del parámetro "p" paquete de la URL
        String paquete = request.getParameter("p");
    	
        // Configurar los atributos con datos hardcodeados
        request.setAttribute("nombrePaquete", "Básico");
        request.setAttribute("imagenPaquete", "https://shorturl.at/ceCD2");
        request.setAttribute("costoPaquete", 240);
        request.setAttribute("descuentoPaquete", 20);
        request.setAttribute("validezPaquete", "30 días");
        request.setAttribute("descripcionPaquete", "Publica ofertas laborales en nuestra plataforma por un período de 30 días");
        request.setAttribute("fechaPaquete", "16/08/23");
        
        // Configurar los tipos de publicación y sus cantidades (hardcodeados)
        List<DTCantTO> tiposDePublicacion = new ArrayList<>();
        tiposDePublicacion.add(new DTCantTO("Premium", 1));
        tiposDePublicacion.add(new DTCantTO("Estándar", 1));
        tiposDePublicacion.add(new DTCantTO("Destacada", 1));
        tiposDePublicacion.add(new DTCantTO("Básica", 4));
        // Puedes agregar más tipos de publicación según sea necesario

        request.setAttribute("tiposDePublicacion", tiposDePublicacion);

        // Obtener el RequestDispatcher para el JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPaquete/paquete.jsp");

        // Redirigir al JSP
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
