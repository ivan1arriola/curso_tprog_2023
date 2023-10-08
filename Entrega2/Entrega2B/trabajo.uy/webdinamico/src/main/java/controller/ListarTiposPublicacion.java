package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.excepciones.ExcepcionTipoOfertaNoExistente;
import main.java.logica.Fabrica;
import main.java.logica.Datatypes.DTTipoOferta;
import main.java.logica.Interfaces.ICtrlOferta;

import java.io.IOException;
import java.util.HashSet;

/**
 * Servlet implementation class ListarTiposPublicacion
 */
@WebServlet("/tipospublicacion")
public class ListarTiposPublicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTiposPublicacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	private HashSet<DTTipoOferta> obtenerTipoOfertas(){
		ICtrlOferta ctrl = Fabrica.getInstance().getICtrlOferta();
		HashSet<String> lista =  ctrl.listarTipoDePublicaciones();
		HashSet<DTTipoOferta> tipoOfertas = new HashSet<DTTipoOferta>();
		for (String nombreTipoOferta : lista) {
			try {
				tipoOfertas.add(ctrl.obtenerDatosTO(nombreTipoOferta));
			} catch (ExcepcionTipoOfertaNoExistente e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return tipoOfertas;
		
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("tiposOferta", obtenerTipoOfertas());
		request.getRequestDispatcher("/WEB-INF/listar/tipospublicacion.jsp").forward(request, response);
	}

}