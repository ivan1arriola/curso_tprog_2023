package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.DtOfertaExtendido;
import logica.servidor.DtPostulacion;
import logica.servidor.DtUsuario;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


@WebServlet("/ofertaslaborales")
public class VerPostulaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;
       

    public VerPostulaciones() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickname = (String) request.getSession().getAttribute("nickname");
		
		
		List<DtPostulacion> postulaciones = new ArrayList<>();
		try {
			DtUsuario usuario = (DtUsuario) servidor.obtenerDatosUsuario(nickname);
			request.setAttribute("usuario", usuario);
			List<DtOfertaExtendido> ofertas = servidor.obtenerDTOfertasLaboralesConfirmadas().getOfertasExtendido();
			request.setAttribute("ofertas", ofertas);
			postulaciones =  servidor.listarPostulacionesPostulante(nickname).getPostulaciones();
		} catch (ExceptionUsuarioNoEncontrado_Exception exc) {
			exc.printStackTrace();
		}
		
		request.setAttribute("postulaciones", postulaciones);
		
		if(!postulaciones.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/listarPostula/verpostulaciones.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/listarPostula/sinpostulaciones.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

