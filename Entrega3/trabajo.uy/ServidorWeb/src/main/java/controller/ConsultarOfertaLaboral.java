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
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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



            try {
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
            } catch (IOException e) {
                String mensajeError = "Ocurrió un error al obtener los datos de la oferta laboral: " + e.getMessage();
                request.setAttribute("mensajeError", mensajeError);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
                dispatcher.forward(request, response);
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

    private OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean, String nombreOferta, String empresaNickname) {
        try {
            //ofertaBean = logica.cargarPaquete(ofertaBean, empresaNickname);
            //ofertaBean = logica.cargarPostulantes(ofertaBean, empresaNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //ofertaBean = logica.cargarPostulantes(ofertaBean, empresaNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ofertaBean;
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
        doGet(request, response);
    }
}
