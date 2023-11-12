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
import logica.servidor.TipoUsuarioNoValido_Exception;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


@WebServlet("/verpostulacion")
public class verpostulaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;
       

    public verpostulaciones() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickname = (String) request.getSession().getAttribute("nickname");
		List<DtPostulacion> postulaciones = new ArrayList<>();
		//List<DtPostulacion> postulacionOferta = new ArrayList<>();
		

		try {
			DtUsuario usuario = (DtUsuario) servidor.obtenerDatosUsuario(nickname);
			request.setAttribute("usuario", usuario);
			request.setAttribute("nickname", nickname);
			List<DtOfertaExtendido> ofertas = servidor.obtenerDTOfertasLaboralesConfirmadas().getOfertasExtendido();
			
			//String nom = servidor.listarPostulacionesPostulante(nickname).getListaString().get(1);
			//System.out.println(nom);

			List<DtOfertaExtendido> ofertasPostulado = new ArrayList<>();
			
			for(DtOfertaExtendido offer : ofertas) {
				
				boolean existe = servidor.hayPostulacionW(nickname, offer.getNombre()); 
				
				if (existe) {
					
					
				 DtPostulacion dtpos = servidor.obtenerDatosPostulacionW(nickname, offer.getNombre());
 			     ofertasPostulado.add(offer);
				 postulaciones.add(dtpos);
				 
		 
				}
			}
	
			request.setAttribute("ofertas", ofertasPostulado);
			request.setAttribute("postulaciones", postulaciones);
		} catch (ExceptionUsuarioNoEncontrado_Exception exc) {
			exc.printStackTrace();
		} catch (TipoUsuarioNoValido_Exception exc) {

			exc.printStackTrace();
		}
		
		request.setAttribute("postulaciones", postulaciones);
		
		if (!postulaciones.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/listarPostula/verpostulacion.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/listarPostula/sinpostulaciones.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

