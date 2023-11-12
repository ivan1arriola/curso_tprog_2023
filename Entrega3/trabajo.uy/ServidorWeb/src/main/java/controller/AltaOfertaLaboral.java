package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import javabeans.CantTipoPublicacionBean;
import javabeans.PaqueteBean;
import logica.servidor.*;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

@WebServlet("/altaofertalaboral")
@MultipartConfig
public class AltaOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public AltaOfertaLaboral() {
        super();
        logica = FabricaWeb.getInstance().getLogica();
    }

    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request,  response);
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
        } else {
            TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
            if (tipo == null || tipo == TipoUsuario.Postulante || tipo == TipoUsuario.Visitante) {
                response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
            } else {
                String nickname = (String) session.getAttribute("nickname");
                cargarTipoOferta(request,  response);
                try {
                    cargarPaquetes(request,  response,  nickname);
                } catch (ExceptionUsuarioNoEncontrado_Exception | NoExistePaquete_Exception e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("/WEB-INF/altaOfertaLaboral/altaOfertaLaboral.jsp").forward(request,  response);
            }
        }
    }

    private void cargarPaquetes(HttpServletRequest request,  HttpServletResponse response,  String nickname) throws ExceptionUsuarioNoEncontrado_Exception,  NoExistePaquete_Exception {
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        List<String> paquetesEmp;
        
		try {
			paquetesEmp = servidor.listarPaquetesNoVencidos(nickname).getListaString();
			for (String paquete : paquetesEmp) {
				PaqueteBean paqB = logica.obtenerDatosPaquete(paquete);
			}
			Set<String> paquetes = new HashSet<>(paquetesEmp);
			request.setAttribute("paquetes",  paquetes);
		} catch (ExceptionEmpresaInvalida_Exception | ExceptionUsuarioNoEncontrado_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private void cargarTipoOferta(HttpServletRequest request,  HttpServletResponse response) {
        Set<String> tipoPublicaciones = logica.listarTipoDePublicaciones();
        request.setAttribute("tipoPublicaciones",  tipoPublicaciones);
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

    
    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");
        
        String nombre = request.getParameter("nombre");
        // Parsear la cadena a LocalTime
        
        List<String> empresas = servidor.listarEmpresas().getListaString();
        String empresaPostul = "";
        
        for (String emp : empresas) {
        	Set<String> ofertas_lab;
			try {
				ofertas_lab = logica.listarOfertasLaboralesDeEmpresa(emp);
	    		for (String olb : ofertas_lab) {
	        		if (olb.equals(nombre)) {
	        			empresaPostul = emp;
	        			break;
	        		}
	        	}
			} catch (ExceptionUsuarioNoEncontrado_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        if (empresaPostul.equals("")) {
        	
            String tipoOferta = request.getParameter("tipoOferta");
            String descripcion = request.getParameter("descripcion");
            String horarioInicio = request.getParameter("horaInicio");
            String horarioFinal = request.getParameter("horaFinal");
        	
            Part imagenPart = request.getPart("imagen");
            
            byte[] imagenBytes  = procesarImagen(imagenPart);
            
            String departamentoStr = request.getParameter("departamento");
            String ciudad = request.getParameter("ciudad");
            String formaPago = request.getParameter("formaPago");
            String[] keywords = request.getParameterValues("keywords[]");
            
            String keywordsString = "";
            if (keywords != null) {
            	keywordsString = String.join(":",  keywords);
            }
            
            
            float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));

            if (!formaPago.equals("1")) {
            	
            	tipoOferta = request.getParameter("tipoOfertaPart");
            	String[] partes = tipoOferta.split(" ");
            	tipoOferta = partes[0];
            }
            
            try {
            	if (imagenBytes != null) {
            		servidor.altaOfertaLaboralConImagen( nickname,  tipoOferta,  nombre,  descripcion,  horarioInicio,  horarioFinal, 
        				    remuneracion,  ciudad,  departamentoStr,  keywordsString,  imagenBytes,  formaPago);
                    // Lógica de negocio para determinar si se debe mostrar el modal

                    request.setAttribute("mensajeExito",  "La oferta laboral se ha dado de alta éxitosamente.");
                    dispatcher = request.getRequestDispatcher("/WEB-INF/exitoPage.jsp");
                    dispatcher.forward(request,  response);
            	} else {
            		servidor.altaOfertaLaboral( nickname,  tipoOferta,  nombre,  descripcion,  horarioInicio,  horarioFinal, 
        				    remuneracion,  ciudad,  departamentoStr,  keywordsString,  formaPago);
                    request.setAttribute("mensajeExito",  "La oferta laboral se ha dado de alta éxitosamente.");
                    dispatcher = request.getRequestDispatcher("/WEB-INF/exitoPage.jsp");
                    dispatcher.forward(request,  response);
            	}
				
			} catch (ExceptionRemuneracionOfertaLaboralNegativa_Exception | ExceptionUsuarioNoEncontrado_Exception
					| NoExistePaquete_Exception e) {
				// TODO Auto-generated catch block
                request.setAttribute("mensajeError",  "ERROR");
                dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request,  response);
			} catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa_Exception |
                     ExceptionCostoPaqueteNoNegativo_Exception | ExceptionPaqueteNoVigente_Exception |
                     ExceptionDescuentoInvalido_Exception e) {
                throw new RuntimeException(e);
            }
        }

            
    }


}
