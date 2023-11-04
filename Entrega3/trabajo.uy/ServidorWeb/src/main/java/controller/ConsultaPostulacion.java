package controller;

import java.io.IOException;

import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import utils.FabricaWeb;

@WebServlet("/consultapostulacion")
public class ConsultaPostulacion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ILogica logica;

    public ConsultaPostulacion() {
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);

        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

        UsuarioBean usuario = logica.obtenerDatosUsuario(nickname);
        String nombrePostulante = usuario.getNombre() + " " + usuario.getApellido();
        request.setAttribute("postulante", nombrePostulante);

        String nombreOferta = request.getParameter("id");
        PostulacionBean postulacion = logica.obtenerDatosPostulacionW(nickname, nombreOferta);

        request.setAttribute("postulacion",postulacion);

        OfertaLaboralBean ofertaLaboral = logica.obtenerDatosOfertaLaboral(nombreOferta);
        request.setAttribute("oferta", ofertaLaboral);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // SIN IMPLEMENTACIÓN. NO SE REQUIERE, SÓLO CONSULTA.
        doGet(request, response);
    }
}

