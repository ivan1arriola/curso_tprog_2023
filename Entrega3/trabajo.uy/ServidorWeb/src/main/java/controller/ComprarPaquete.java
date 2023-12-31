package controller;

import interfaces.ILogica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Set;

import logica.servidor.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception;
import logica.servidor.ExceptionCompraPaqueteConValorNegativo_Exception;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.ExceptionValidezNegativa_Exception;
import logica.servidor.NoExistePaquete_Exception;
import utils.FabricaWeb;

@WebServlet("/comprarpaquete")
public class ComprarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ComprarPaquete() {
        super();
    }

    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        FabricaWeb.getKeywordsLoader().cargarKeywords(request,  response);
        request.setCharacterEncoding("UTF-8");

        
        String paquete = request.getParameter("paquete");
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

     // En el servlet que envía la información
        String parametroCodificado = URLEncoder.encode(paquete,  "UTF-8");
        
        ILogica logica = FabricaWeb.getLogica();

        int valor = 0;
        try {
            valor = (int) logica.obtenerDatosPaquete(paquete).getCosto();
        } catch (NoExistePaquete_Exception e) {
            throw new RuntimeException(e);
        }

        try {
			logica.compraPaquetes(nickname,  paquete,  LocalDate.now(),  valor);
		} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception e) {
			
			e.printStackTrace();
		} catch (ExceptionCompraPaqueteConValorNegativo_Exception e) {
			
			e.printStackTrace();
		} catch (ExceptionUsuarioNoEncontrado_Exception e) {
			
			e.printStackTrace();
		} catch (ExceptionValidezNegativa_Exception e) {
			
			e.printStackTrace();
		} catch (NoExistePaquete_Exception e) {
			
			e.printStackTrace();
		}
        
        /* sirve para testear si se compra el paquete
        try {
			Set<String> paqs = logica.listarPaquetesDeEmpresa(nickname);
			String s = "";
			for (String elem : paqs) {
				s = s + " " + elem;
			}
			
	        request.setAttribute("mensajeError",  s);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
	        dispatcher.forward(request,  response);
		} catch (ExceptionUsuarioNoEncontrado_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
        
        response.sendRedirect(request.getContextPath() + "/consultarpaquete?p=" + parametroCodificado);
    }
}
