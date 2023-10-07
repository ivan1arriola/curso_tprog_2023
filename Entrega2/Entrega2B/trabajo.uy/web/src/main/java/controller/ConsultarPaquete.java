package controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTCantTO;
import main.java.logica.Datatypes.DTPaquete;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


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
    	return Fabrica.getInstance().getICtrlOferta().obtenerDatosPaquete(nombrePaquete);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
