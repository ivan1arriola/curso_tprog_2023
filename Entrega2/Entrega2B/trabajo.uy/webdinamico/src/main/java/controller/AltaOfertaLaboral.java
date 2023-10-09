package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTCompraPaquetes;
import main.java.logica.datatypes.DTEmpresaConCompras;
import java.util.Set;

import java.io.IOException;
import enumeration.TipoUsuario;

/**
 * Servlet implementation class AltaOfertaLaboral
 */
@WebServlet("/altaofertalaboral")
public class AltaOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
		} else {
			TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
			if( tipo == null || tipo== TipoUsuario.Postulante || tipo==TipoUsuario.Visitante) {
				response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
			} else {
				String nickname = (String) session.getAttribute("nickname");
				cargarTipoOferta(request, response);
				cargarKeywords(request, response);
				cargarPaquetes(request, response, nickname);
				request.getRequestDispatcher("/WEB-INF/altaOfertaLaboral/altaOfertaLaboral.jsp").forward(request, response);
			}
			
		}
		
	}
	
	
	private void cargarPaquetes(HttpServletRequest request, HttpServletResponse response, String nickname) {
		DTEmpresaConCompras dtcp = (DTEmpresaConCompras) Fabrica.getInstance().getICtrlUsuario().obtenerDatosUsuarioEspecial(nickname, nickname);
		Set<DTCompraPaquetes> compras = dtcp.getCompraPaquetes();
		Set<String> paquetes;
		
		for (DTCompraPaquetes elem : compras) {
			paquetes.add(elem.getNombre());
		}
		
		request.setAttribute("paquetes", paquetes);
	}

	private void cargarKeywords(HttpServletRequest request, HttpServletResponse response) {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
	}

	private void cargarTipoOferta(HttpServletRequest request, HttpServletResponse response) {
		Set<String> tipoPublicaciones = Fabrica.getInstance().getICtrlOferta().listarTipoDePublicaciones();
		request.setAttribute("tipoPublicaciones", tipoPublicaciones);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
