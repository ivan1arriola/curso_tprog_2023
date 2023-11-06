package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.DtOfertaExtendido;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;

import java.io.IOException;

import enumerado.TipoUsuario;
import logica.servidor.DtPostulacion;
import logica.servidor.DtUsuario;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;

@WebServlet("/consultarpostulacion")
public class ConsultarPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;
  
    public ConsultarPostulacion() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombreOferta = request.getParameter("oferta");
        String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");
        DtPostulacion dtpost = null;
        
        try {
	        DtUsuario usuario = servidor.obtenerDatosUsuario(nicknameUsuarioLogueado);
	        String nombrePostulante = usuario.getNombre() + " " + usuario.getApellido();
	        request.setAttribute("postulante", nombrePostulante);
	        request.setAttribute("postulanteNick", nicknameUsuarioLogueado);
	        dtpost = servidor.obtenerDatosPostulacionW(nicknameUsuarioLogueado, nombreOferta);
			request.setAttribute("infopostulacion", dtpost);
			request.getRequestDispatcher("/WEB-INF/consultarPostulacion/postulacion.jsp").forward(request, response);
			
			//request.getRequestDispatcher("/WEB-INF/consultarPostulacion/postulacioninexistente.jsp").forward(request, response);
		} catch (ExceptionUsuarioNoEncontrado_Exception exc) {
			throw new RuntimeException(exc);
		}
       

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
