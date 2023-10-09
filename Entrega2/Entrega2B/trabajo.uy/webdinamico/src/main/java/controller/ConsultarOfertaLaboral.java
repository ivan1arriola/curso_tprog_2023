package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
import main.java.logica.datatypes.DTOfertaExtendidoConKeywordsTit;
import main.java.logica.datatypes.DTOfertaExtendidoSinPConK;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.datatypes.DTPostulacion;
import main.java.logica.datatypes.DTUsuario;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;
import auxiliar.OfertaLaboralBean;
import enumeration.TipoUsuario;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICtrlOferta ctrl;

    public ConsultarOfertaLaboral() {
        super();
        ctrl = Fabrica.getInstance().getICtrlOferta();
    }

    private DTOfertaExtendido getOfertaLaboral(String nombre) {
        return ctrl.obtenerOfertaLaboral(nombre);
    }

    private OfertaLaboralBean cargarDatosIniciales(String nombreOferta) {
        OfertaLaboralBean ofertaLaboral = new OfertaLaboralBean();

        DTOfertaExtendido dtOferta = getOfertaLaboral(nombreOferta);

        ofertaLaboral.setNombre(dtOferta.getNombre());
        ofertaLaboral.setDescripcion(dtOferta.getDescripcion());
        ofertaLaboral.setCiudad(dtOferta.getCiudad());
        ofertaLaboral.setCosto(dtOferta.getCosto());
        ofertaLaboral.setDepartamento(dtOferta.getDepartamento());
        ofertaLaboral.setFechaDeAlta(dtOferta.getFechaDeAlta());
        ofertaLaboral.setHorario(dtOferta.getHorario());
        ofertaLaboral.setImagen(dtOferta.getImagen());
        ofertaLaboral.setRemuneracion(dtOferta.getRemuneracion());
        
        
        DTOfertaExtendidoSinPConK nuevoDatos = ctrl.infoOfertaLaboralVisitante(nombreOferta);
        ofertaLaboral.setKeywords(nuevoDatos.getKeywords());

        return ofertaLaboral;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
    	
        String nombreOferta = request.getParameter("o");
        if (nombreOferta != null && !nombreOferta.isEmpty()) {

            HttpSession session = request.getSession(false);
            String nickname = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            try {
                if (nickname == null) nickname = "";
                
                if (tipoUsuario == null) {
                	tipoUsuario = TipoUsuario.Visitante;
                }

                OfertaLaboralBean ofertaBean = cargarDatosIniciales(nombreOferta);

                if (tipoUsuario == TipoUsuario.Empresa) {
                	ofertaBean = cargarDatosEmpresa(ofertaBean, nombreOferta, nickname);
                }

                if (tipoUsuario == TipoUsuario.Postulante) {
                	ofertaBean = cargarDatosPostulante(ofertaBean, nombreOferta, nickname);
                }
                

                request.setAttribute("ofertaLaboral", ofertaBean);
                request.getRequestDispatcher("/WEB-INF/consultarOferta/infoOfertaLabora.jsp").forward(request, response);

            } catch (Exception e) {
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: No se proporcionó el nombre";
            request.setAttribute("mensajeError", mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }


    // Carga los DTUsuario de los postulantes
	private OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean, String nombreOferta, String empresaNickname) {
		ICtrlUsuario ctrlUsuario = Fabrica.getInstance().getICtrlUsuario();
		
		DTOfertaExtendidoSinPConK info = ctrl.infoOfertaLaboralEmpresa(empresaNickname, nombreOferta);
		
		DTPaquete paquete = null;
		
		if( info instanceof DTOfertaExtendidoConKeywordsTit) {
			ofertaBean.setMostrarPostulantesYPaquetes(true);
			DTOfertaExtendidoConKeywordsTit masData = (DTOfertaExtendidoConKeywordsTit) info;
			paquete = masData.getPaquete();
		} 
				
		DTOfertaExtendido dtOferta = getOfertaLaboral(nombreOferta);
		Set<DTUsuario> postulantes = new HashSet<DTUsuario>();
		
		Set<DTPostulacion> postulaciones = dtOferta.getPostulaciones();
		
		for( DTPostulacion postulacion : postulaciones) {
			String nicknameUsuario = postulacion.getPostulante();
			DTUsuario usuario = ctrlUsuario.obtenerDatosUsuario(nicknameUsuario);
			postulantes.add(usuario);
		}
		
        ofertaBean.setPostulantes(postulantes);
        ofertaBean.setPaquete(paquete);
 
    	
		return ofertaBean;
	}
    
    private OfertaLaboralBean cargarDatosPostulante(OfertaLaboralBean ofertaBean, String nombreOferta, String nickname) {
		DTOfertaExtendidoSinPConK nuevoDatos = ctrl.infoOfertaLaboralPostulante(nickname, nombreOferta);
		
		Set<DTUsuario> postulantes = new HashSet<DTUsuario>();
		    	
    	if ( nuevoDatos instanceof DTOfertaExtendidoConKeywordsPostulante) {
    		DTOfertaExtendidoConKeywordsPostulante conPostulantes = (DTOfertaExtendidoConKeywordsPostulante) nuevoDatos;
    		ICtrlUsuario ctrlUsuario = Fabrica.getInstance().getICtrlUsuario();
    		DTPostulacion postulacion = conPostulantes.getDatosPostulacion();
    		String nicknamePostulante = postulacion.getPostulante();
    		
    		postulantes.add(ctrlUsuario.obtenerDatosUsuario(nicknamePostulante));
    	}
    	
    	ofertaBean.setPostulantes(postulantes);
    	
		return ofertaBean;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
