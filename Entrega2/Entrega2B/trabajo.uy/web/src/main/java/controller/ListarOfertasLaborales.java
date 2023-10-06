package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTHora;
import model.Datatypes.DTHorario;
import model.Datatypes.DTOfertaLaboral;
import model.Enumerados.DepUY;
import model.Enumerados.EstadoOL;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ListarOfertasLaborales
 */
@WebServlet("/ofertaslaborales")
public class ListarOfertasLaborales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarOfertasLaborales() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Funcion para simular la logica
    private List<DTOfertaLaboral> getOfertasLaborales() {
        List<DTOfertaLaboral> ofertas = new ArrayList<>();

        ofertas.add(new DTOfertaLaboral(
        	    "Desarrollador Frontend",
        	    "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
        	    LocalDate.of(2023, 8, 14),
        	    90000.0f,
        	    90000.0f,
        	    new DTHorario(new DTHora(9, 0), new DTHora(18, 0)),
        	    DepUY.Montevideo,
        	    "Montevideo",
        	    EstadoOL.Confirmada, // Cambiar a EstadoOL.CONFIRMADA
        	    null // Establecer la imagen como null
        	));

        	ofertas.add(new DTOfertaLaboral(
        	    "Estratega de Negocios",
        	    "Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.",
        	    LocalDate.of(2023, 8, 14),
        	    80000.0f,
        	    80000.0f,
        	    new DTHorario(new DTHora(8, 0), new DTHora(17, 0)),
        	    DepUY.Maldonado,
        	    "Punta del Este",
        	    EstadoOL.Confirmada, // Cambiar a EstadoOL.CONFIRMADA
        	    null // Establecer la imagen como null
        	));

        	ofertas.add(new DTOfertaLaboral(
        	    "A. de Marketing Digital",
        	    "Únete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
        	    LocalDate.of(2023, 8, 15),
        	    80000.0f,
        	    80000.0f,
        	    new DTHorario(new DTHora(10, 0), new DTHora(19, 0)),
        	    DepUY.Flores,
        	    "Flores",
        	    EstadoOL.Confirmada, // Cambiar a EstadoOL.CONFIRMADA
        	    null // Establecer la imagen como null
        	));


        return ofertas;
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene las ofertas laborales
        List<DTOfertaLaboral> ofertas = getOfertasLaborales();

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("ofertasLaborales", ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/ofertaslaborales.jsp").forward(request, response);
    }

}
