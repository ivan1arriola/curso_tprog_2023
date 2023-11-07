package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import javabeans.PaqueteBean;
import javabeans.UsuarioBean;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;
import java.io.IOException;
import java.util.List;

@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarOfertaLaboral() {
        super();
        logica = FabricaWeb.getLogica();
    }

    private OfertaLaboralBean cargarDatosIniciales(String nombreOferta) throws OfertaLaboralNoEncontrada_Exception {
        return logica.obtenerDatosOfertaLaboral(nombreOferta);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);

        String nombreOferta = request.getParameter("o");


        if (nombreOferta != null && !nombreOferta.isEmpty()) {
            HttpSession session = request.getSession(false);
            String nickname = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            UsuarioBean user = logica.obtenerDatosUsuario(nickname);
            boolean estaFav = false;
            for (OfertaLaboralBean elemento : user.getOferFavs()) {
            	estaFav = elemento.getNombre() == nombreOferta;
            	if(estaFav)
            		break;
            }
            
            request.setAttribute("estaFav", estaFav);
            
            try {
                boolean estaVigente = logica.estaVigenteOferta(nombreOferta);
                request.setAttribute("vigente", estaVigente);

                if (nickname == null) nickname = "";
                if (tipoUsuario == null) {
                    tipoUsuario = TipoUsuario.Visitante;
                }

                OfertaLaboralBean ofertaBean = cargarDatosIniciales(nombreOferta);


                boolean esCreadorOferta = ofertaBean.getNicknameEmpresa().equals(nickname);
                request.setAttribute("duenio", esCreadorOferta);

                if (tipoUsuario == TipoUsuario.Empresa && esCreadorOferta) {
                    PaqueteBean paquete = logica.obtenerPaqueteDeOferta(nombreOferta, nickname);
                    List<UsuarioBean> postulantes = logica.obtenerPostulantesDeOferta(nombreOferta, nickname);

                    request.setAttribute("paquete", paquete);
                    request.setAttribute("postulantes", postulantes);
                }

                if (tipoUsuario == TipoUsuario.Postulante) {
                    ofertaBean = cargarDatosPostulante(ofertaBean, nombreOferta, nickname);
                }

                request.setAttribute("ofertaLaboral", ofertaBean);
                request.setAttribute("nombreOferta", ofertaBean.getNombre());
                request.getRequestDispatcher("/WEB-INF/consultarOferta/consultarOferta.jsp").forward(request, response);
                return;
            } catch (IOException e) {
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: No se proporcionó el nombre";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }


    private OfertaLaboralBean cargarDatosPostulante(OfertaLaboralBean ofertaBean, String nombreOferta, String nickname) {
        try {
            ofertaBean = logica.cargarDatosDePostulante(ofertaBean, nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ofertaBean;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
    	
    	String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");
    	String nombreOferta = (String) request.getParameter("nombreOferta");    	
    	
    	if (request.getParameter("corazonDesm") != null) {
    		try {
				servidor.marcarFavorito(nicknameUsuarioLogueado, nombreOferta);
				response.sendRedirect(request.getContextPath() + "/consultarofertalaboral?o=" + nombreOferta);
			} catch (ExceptionUsuarioNoEncontrado_Exception | OfertaLaboralNoEncontrada_Exception e) {
				e.printStackTrace();
			}

        } else if (request.getParameter("corazonMarc") != null) {
    		try {
				servidor.desmarcarFavorito(nicknameUsuarioLogueado, nombreOferta);
				response.sendRedirect(request.getContextPath() + "/consultarofertalaboral?o=" + nombreOferta);
			} catch (ExceptionUsuarioNoEncontrado_Exception | OfertaLaboralNoEncontrada_Exception e) {
				e.printStackTrace();
			}
        }
    }
}
