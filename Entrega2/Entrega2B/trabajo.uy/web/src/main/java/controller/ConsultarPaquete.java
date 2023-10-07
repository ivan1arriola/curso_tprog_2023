package controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombrePaquete = request.getParameter("p");

        try {
            DTPaquete paquete = obtenerPaquetePorNombre(nombrePaquete);

      
                request.setAttribute("nombrePaquete", paquete.getNombre());
                request.setAttribute("imagenPaquete", "https://shorturl.at/ceCD2");// TODO: Reemplaza con el atributo imagen de DTPaquete
                request.setAttribute("costoPaquete", paquete.getCosto());
                request.setAttribute("descuentoPaquete", paquete.getDescuento());
                request.setAttribute("validezPaquete", paquete.getValidez() + " días");
                request.setAttribute("descripcionPaquete", paquete.getDescripcion());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaFormateada = paquete.getFechaAlta().format(formatter);

                request.setAttribute("fechaPaquete", fechaFormateada);

                request.setAttribute("tiposDePublicacion", paquete.getTiposDePub());
            

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPaquete/paquete.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            // Manejar la excepción
            request.setAttribute("mensajeError", "Error al obtener el paquete: " + e.getMessage());

            // Redirigir a una página de error o mostrar un mensaje de error en el JSP
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            errorDispatcher.forward(request, response);
        }
    }


    private DTPaquete obtenerPaquetePorNombre(String nombrePaquete) {
    	// Configurar los tipos de publicación y sus cantidades (hardcodeados)
    	Set<DTCantTO> tiposDePublicacion = new HashSet<DTCantTO>();
    	tiposDePublicacion.add(new DTCantTO("Premium", 1));
    	tiposDePublicacion.add(new DTCantTO("Estándar", 1));
    	tiposDePublicacion.add(new DTCantTO("Destacada", 1));
    	tiposDePublicacion.add(new DTCantTO("Básica", 4));

    	// Crear el objeto DTPaquete
    	DTPaquete paquete = new DTPaquete("Básico", 240, 20, 30, 
    	    "Publica ofertas laborales en nuestra plataforma por un período de 30 días", 
    	    tiposDePublicacion, LocalDate.of(2023, 8, 16));
    	return paquete;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
