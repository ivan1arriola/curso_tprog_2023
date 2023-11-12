package controller;

import interfaces.ILogica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.DtTipoOferta;
import utils.FabricaWeb;

import java.io.IOException;

/**
 * Servlet implementation class ConsultarTipoPublicacion
 */
@WebServlet("/consultartipopublicacion")
public class ConsultarTipoPublicacion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarTipoPublicacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request,  HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request,  response);
        ILogica logica = FabricaWeb.getLogica();

        String nombre = request.getParameter("tp");

        try {
           if (nombre != null && !nombre.isEmpty()) {
                DtTipoOferta tipoPublicacion = logica.obtenerDatosTO(nombre);
                if (tipoPublicacion == null) throw new Exception("Tipo Oferta inexistente");
                
                request.setAttribute("nombrePagina",  tipoPublicacion.getNombre());
                request.setAttribute("tipoPublicacion",  tipoPublicacion);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarTipoPublicacion/tipopublicacion.jsp");
                dispatcher.forward(request,  response);
            } else {
                throw new Exception("No se ingresó nombre de tipo de publicación");
            }
        } catch (Exception e) {
            String mensajeError = e.getMessage();
            request.setAttribute("mensajeError",  mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request,  response);
        }
    }
}

