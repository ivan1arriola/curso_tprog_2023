package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.excepciones.UsuarioNoExisteException;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlOferta;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import enumeration.TipoUsuario;

/**
 * Servlet implementation class ConsultarUsuario
 */
@WebServlet("/consultarusuario")
public class ConsultarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public DTUsuario obtenerDatosUsuario(String nick) throws UsuarioNoExisteException {
      return Fabrica.getInstance().getICtrlUsuario().obtenerDatosUsuario(nick);
    }
    
    public void cargarDTPaquete (HttpServletRequest request, HttpServletResponse response, String nickname) {
    	
    	ICtrlOferta ctrl = Fabrica.getInstance().getICtrlOferta();
    	Set<String> paquetes = ctrl.listarComprasPaquete(nickname);
    	
    	Set<DTPaquete> dtPaquetes = new HashSet<DTPaquete>(); 
    	
    	if(paquetes != null && !paquetes.isEmpty()) {
    		for(String nombre : paquetes) {
    			dtPaquetes.add(ctrl.obtenerDatosPaquete(nombre));
    		}
    		
    	}

		request.setAttribute("paquetes", dtPaquetes);
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
        String nickname = request.getParameter("u");
        String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");
        TipoUsuario tipo = (TipoUsuario) request.getSession().getAttribute("tipoUsuario");
        
        

        if (nickname != null && !nickname.isEmpty()) {
            try {
            	
            	
        		
                DTUsuario usuario = obtenerDatosUsuario(nickname);
                if (usuario == null) {
                    throw new Exception("No se encontraron datos de usuario.");
                }
                request.setAttribute("usuario", usuario);

                if (nickname.equals(nicknameUsuarioLogueado)) {
                	if(TipoUsuario.Empresa == tipo) cargarDTPaquete(request, response, nickname);
                	request.setAttribute("editable", true);
                } else {
                	request.setAttribute("editable", false);
                }
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarUsuario/infoUsuario.jsp");
                    dispatcher.forward(request, response);
                
            } catch (Exception e) {
                String mensajeError = "Ocurrió un error al obtener los datos del usuario: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            String mensajeError = "Ocurrió un error al obtener los datos del usuario: No se proporcionó el usuario";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }


}
