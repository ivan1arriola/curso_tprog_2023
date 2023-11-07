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
		String nickname = (String) request.getSession().getAttribute("nickname");
        DtPostulacion dtpost = null;
                   
        try {
        	
        	boolean existe = servidor.hayPostulacionW(nickname, nombreOferta); 
        	
        	DtUsuario usuario = servidor.obtenerDatosUsuario(nickname);
	        String nombrePostulante = usuario.getNombre() + " " + usuario.getApellido();
	        DtOfertaExtendido offer = servidor.obtenerOfertaLaboral(nombreOferta);
	        byte[] imagenOferta = offer.getImagen();
    		
	        request.setAttribute("oferta", offer);
	        request.setAttribute("postulante", nombrePostulante);
	        request.setAttribute("nickname", nickname);
	        request.setAttribute("imagenOferta", imagenOferta);
        	
        	if(existe) {
        		dtpost = servidor.obtenerDatosPostulacionW(nickname, nombreOferta);
	          	request.setAttribute("postulacion", dtpost);
		  		request.getRequestDispatcher("/WEB-INF/consultarPostulacion/postulacion.jsp").forward(request, response);
        	} else {
			
        		request.getRequestDispatcher("/WEB-INF/consultarPostulacion/nopostulacion.jsp").forward(request, response);
        	}
		} catch (ExceptionUsuarioNoEncontrado_Exception exc) {
			request.getRequestDispatcher("/WEB-INF/consultarPostulacion/nopostulacion.jsp").forward(request, response);
			throw new RuntimeException(exc);
		} catch (OfertaLaboralNoEncontrada_Exception exc) {
			throw new RuntimeException(exc);
		}
       

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
