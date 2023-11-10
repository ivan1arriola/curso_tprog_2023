package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.servidor.ExceptionEmpresaInvalida_Exception;
import logica.servidor.ExceptionRemuneracionOfertaLaboralNegativa_Exception;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.NoExistePaquete_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@WebServlet("/altaofertalaboral")
public class AltaOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public AltaOfertaLaboral() {
        super();
        logica = FabricaWeb.getInstance().getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
        } else {
            TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
            if (tipo == null || tipo == TipoUsuario.Postulante || tipo == TipoUsuario.Visitante) {
                response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
            } else {
                String nickname = (String) session.getAttribute("nickname");
                cargarTipoOferta(request, response);
                try {
                    cargarPaquetes(request, response, nickname);
                } catch (ExceptionUsuarioNoEncontrado_Exception e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("/WEB-INF/altaOfertaLaboral/altaOfertaLaboral.jsp").forward(request, response);
            }
        }
    }

    private void cargarPaquetes(HttpServletRequest request, HttpServletResponse response, String nickname) throws ExceptionUsuarioNoEncontrado_Exception {
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        List<String> paquetesEmp;
		try {
			paquetesEmp = servidor.listarPaquetesNoVencidos(nickname).getListaString();
			Set<String> paquetes = new HashSet<>(paquetesEmp);
			request.setAttribute("paquetes", paquetes);
		} catch (ExceptionEmpresaInvalida_Exception | ExceptionUsuarioNoEncontrado_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private void cargarTipoOferta(HttpServletRequest request, HttpServletResponse response) {
        Set<String> tipoPublicaciones = logica.listarTipoDePublicaciones();
        request.setAttribute("tipoPublicaciones", tipoPublicaciones);
    }

    private byte[] procesarImagen(Part imagenPart) throws IOException {
        if (imagenPart == null) {
            return null;
        }

        try (InputStream input = imagenPart.getInputStream()) {
            byte[] imagenBytes = input.readAllBytes();
            if (imagenBytes.length == 0) {
                return null;
            }
            return imagenBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
    	
        String tipoOferta = request.getParameter("tipoOferta");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String horarioInicio = request.getParameter("horaInicio");
        String horarioFinal = request.getParameter("horaFinal");
        // Parsear la cadena a LocalTime
        
        Part imagenPart = request.getPart("imagen");
        
        byte[] imagenBytes  = procesarImagen(imagenPart);
        
        String departamentoStr = request.getParameter("departamento");
        String ciudad = request.getParameter("ciudad");
        String formaPago = request.getParameter("formaPago");
        String[] keywords = request.getParameterValues("keywords[]");
        
        String keywordsString = "";
        if(keywords != null) {
        	keywordsString = String.join(":", keywords);
        }
        
        
        float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));

        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

            try {
				servidor.altaOfertaLaboral( nickname, tipoOferta, nombre, descripcion, horarioInicio, horarioFinal,
				    remuneracion, ciudad, departamentoStr, keywordsString, null, formaPago);
				response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
			} catch (ExceptionRemuneracionOfertaLaboralNegativa_Exception | ExceptionUsuarioNoEncontrado_Exception
					| NoExistePaquete_Exception e) {
				// TODO Auto-generated catch block
                request.setAttribute("mensajeError", "ERROR");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
			}
            
    }


}
