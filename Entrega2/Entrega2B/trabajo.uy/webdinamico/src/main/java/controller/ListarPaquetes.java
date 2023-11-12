package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.interfaces.ICtrlOferta;

import java.io.IOException;
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
    	ICtrlOferta ctrl = Fabrica.getInstance().getICtrlOferta();
    	
        Set<String> lista = ctrl.listarPaquetes();
        Set<DTPaquete> paquetes = new HashSet<DTPaquete>();
        
        for(String nombrePaquete : lista) {
        	paquetes.add(ctrl.obtenerDatosPaquete(nombrePaquete));
        }
        
        return paquetes;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
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
