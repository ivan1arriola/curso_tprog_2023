package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlUsuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/usuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarUsuarios() {
        super();
    }
    
    private HashSet<DTUsuario> getUsuarios(){
    	
    	ICtrlUsuario ctrl = Fabrica.getInstance().getICtrlUsuario();
    	
    	 Set<String> nicknames = ctrl.listarNicknamesUsuarios();
         HashSet<DTUsuario> usuarios = new HashSet<DTUsuario>();
         
         for(String usuario : nicknames) {
         	usuarios.add(ctrl.obtenerDatosUsuario(usuario));
         }
    	
    	
		return usuarios;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashSet<DTUsuario> ofertas = getUsuarios();

        request.setAttribute("usuarios", ofertas);

        request.getRequestDispatcher("/WEB-INF/listar/usuarios.jsp").forward(request, response);
    
    }

}
