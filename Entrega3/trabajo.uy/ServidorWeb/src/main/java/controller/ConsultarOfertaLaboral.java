package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;
import java.io.IOException;

@WebServlet("/consultarofertalaboral")
public class ConsultarOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarOfertaLaboral() {
        super();
        logica = FabricaWeb.getInstance().getLogica();
    }

    private OfertaLaboralBean cargarDatosIniciales(String nombreOferta) {
        return logica.obtenerDatosOfertaLaboral(nombreOferta);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

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
                request.getRequestDispatcher("/WEB-INF/consultarOferta/consultarOferta.jsp").forward(request, response);
            } catch (IOException e) {
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

    private OfertaLaboralBean cargarDatosEmpresa(OfertaLaboralBean ofertaBean, String nombreOferta, String empresaNickname) {
        try {
            ofertaBean = logica.cargarPaquete(ofertaBean, empresaNickname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ofertaBean = logica.cargarPostulantes(ofertaBean, empresaNickname);
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