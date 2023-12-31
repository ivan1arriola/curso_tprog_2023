package controller;

import enumeration.EstadoOfertaLaboral;
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
import logica.servidor.*;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarOfertaLaboral() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        FabricaWeb.getKeywordsLoader().cargarKeywords(request,  response);
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String nombreOferta = request.getParameter("o");
        
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();

        
        if (nombreOferta != null && !nombreOferta.isEmpty()) {
            HttpSession session = request.getSession(false);
            String nickname = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            UsuarioBean user = logica.obtenerDatosUsuario(nickname);
            boolean estaFav = false;
            
            for (OfertaLaboralBean elemento : user.getOferFavs()) {
            	estaFav = elemento.getNombre().equals(nombreOferta);
            	if (estaFav)
            		break;
            }
            
            request.setAttribute("estaFav",  estaFav);
            
            try {
                servidor.aumentarVisita(nombreOferta);
                if (nickname == null) nickname = "";
                if (tipoUsuario == null) {
                    tipoUsuario = TipoUsuario.Visitante;
                }

                OfertaLaboralBean ofertaBean = logica.obtenerDatosOfertaLaboral(nombreOferta);

                boolean estaVigente = logica.estaVigenteOferta(nombreOferta);
                request.setAttribute("vigente",  estaVigente);

                boolean estaFinalizada = logica.estaFinalizada(nombreOferta);
                request.setAttribute("estaFinalizada",  estaFinalizada);
              
                request.setAttribute("cantFavs",  ofertaBean.getCantFavs());
                boolean esCreadorOferta = ofertaBean.getNicknameEmpresa().equals(nickname);
                request.setAttribute("duenio",  esCreadorOferta);

                boolean hayOrdenDefinido = servidor.hayOrdenDefinido(nombreOferta); // indica si se definio orden de postulantes
                request.setAttribute("hayOrdenDefinido",  hayOrdenDefinido);

                if (hayOrdenDefinido){
                    WrapperLista listaDelServidor = servidor.obtenerPosiciones(nombreOferta);
                    request.setAttribute("postulantesOrden",  listaDelServidor.getListaString());
                }


                if (tipoUsuario == TipoUsuario.Empresa && esCreadorOferta) {
                    PaqueteBean paquete = logica.obtenerPaqueteDeOferta(nombreOferta,  nickname);
                    List<UsuarioBean> postulantes = logica.obtenerPostulantesDeOferta(nombreOferta,  nickname);

                    request.setAttribute("paquete",  paquete);
                    request.setAttribute("postulantes",  postulantes);
                }

                boolean permisoDenegado = ofertaBean.getEstado() != EstadoOfertaLaboral.Confirmada || !estaVigente;

                if (!esCreadorOferta && permisoDenegado){
                    request.setAttribute("nombreError",  "Esta oferta laboral no esta confirmada");
                    request.setAttribute(
                            "mensajeError", 
                            "No te puedes consultar a una oferta laboral que no este en estado confirmada si no eres la empresa publicadora"
                    );
                    request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request,  response);
                    return;
                }

                if (tipoUsuario == TipoUsuario.Postulante) {
                    ofertaBean = cargarDatosPostulante(ofertaBean,  nombreOferta,  nickname);
                }

                request.setAttribute("ofertaLaboral",  ofertaBean);
                request.setAttribute("nombreOferta",  ofertaBean.getNombre());
                request.getRequestDispatcher("/WEB-INF/consultarOferta/consultarOferta.jsp").forward(request,  response);
            } catch (IOException e) {
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError",  mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request,  response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: No se proporcionó el nombre";
            request.setAttribute("mensajeError",  mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request,  response);
        }
    }

    private OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean,  String nombreOferta,  String empresaNickname) {
        try {
            //ofertaBean = logica.cargarPaquete(ofertaBean,  empresaNickname);
            //ofertaBean = logica.cargarPostulantes(ofertaBean,  empresaNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //ofertaBean = logica.cargarPostulantes(ofertaBean,  empresaNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ofertaBean;
    }

    private OfertaLaboralBean cargarDatosPostulante(OfertaLaboralBean ofertaBean,  String nombreOferta,  String nickname) {
        try {
            ofertaBean = logica.cargarDatosDePostulante(ofertaBean,  nickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ofertaBean;
    }

    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        
    	ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    	
    	String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");
    	String nombreOferta = (String) request.getParameter("nombreOferta");    	
    	
    	if (request.getParameter("corazonDesm") != null) {
    		try {
    			servidor.marcarFavorito(nicknameUsuarioLogueado,  nombreOferta);
				response.sendRedirect(request.getContextPath() + "/consultarofertalaboral?o=" + URLEncoder.encode(nombreOferta,  "UTF-8"));
				return;
			} catch (ExceptionUsuarioNoEncontrado_Exception e) {
				e.printStackTrace();
			} catch (OfertaLaboralNoEncontrada_Exception e) {
				e.printStackTrace();
			}
			
    	} else if (request.getParameter("corazonMarc") != null) {
    		try {
    			
				servidor.desmarcarFavorito(nicknameUsuarioLogueado,  nombreOferta);
				response.sendRedirect(request.getContextPath() + "/consultarofertalaboral?o=" + URLEncoder.encode(nombreOferta,  "UTF-8"));
			} catch (ExceptionUsuarioNoEncontrado_Exception e) {
				e.printStackTrace();
			} catch (OfertaLaboralNoEncontrada_Exception e) {
				e.printStackTrace();
			}
    	}
    }
}
