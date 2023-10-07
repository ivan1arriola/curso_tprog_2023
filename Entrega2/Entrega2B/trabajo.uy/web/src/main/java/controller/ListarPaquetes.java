package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTCantTO;
import model.Datatypes.DTPaquete;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class ListarPaquetes
 */
@WebServlet("/paquetes")
public class ListarPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarPaquetes() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Set<DTPaquete> obtenerPaquetes() {
        Set<DTPaquete> lista = new HashSet<>(); // Corrige el tipo y utiliza el constructor adecuado

        // Crear objetos DTCantTO (asegúrate de que esta clase exista y tenga un constructor adecuado)
        Set<DTCantTO> cantidades = new HashSet<>();
        DTCantTO cantidad1 = new DTCantTO("nombre", 2);
        DTCantTO cantidad2 = new DTCantTO("nombre2", 5);

        // Crear objeto DTPaquete y agregarlo a la lista
        DTPaquete paquete = new DTPaquete("Nombre", 0, 0, 0, "descripcion", cantidades, LocalDate.now());
        DTPaquete paquete2 = new DTPaquete("2222", 0, 0, 0, "desc222ripcion", cantidades, LocalDate.now());
        DTPaquete paquete3 = new DTPaquete("No3333mbre", 0, 0, 0, "33333", cantidades, LocalDate.now());
        
        lista.add(paquete);
        lista.add(paquete2);
        lista.add(paquete3);

        return lista;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Set<DTPaquete> paquetes = obtenerPaquetes();

            request.setAttribute("paquetes", paquetes);
            request.getRequestDispatcher("/WEB-INF/listar/paquetes.jsp").forward(request, response);
        } catch (Exception e) {
            String mensajeError = "Ocurrió un error: " + e.getMessage();
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }
}
