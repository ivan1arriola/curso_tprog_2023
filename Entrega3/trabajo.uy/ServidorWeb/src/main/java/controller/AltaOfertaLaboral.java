package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import utils.FabricaWeb;
import webservice.DepUY;
import webservice.TipoUsuario;
import enumeration.EstadoOfertaLaboral;
import interfaces.ILogica;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
            if (tipo == null || tipo == TipoUsuario.POSTULANTE || tipo == TipoUsuario.VISITANTE) {
                response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
            } else {
                String nickname = (String) session.getAttribute("nickname");
                cargarTipoOferta(request, response);
                cargarPaquetes(request, response, nickname);
                request.getRequestDispatcher("/WEB-INF/altaOfertaLaboral/altaOfertaLaboral.jsp").forward(request, response);
            }
        }
    }

    private void cargarPaquetes(HttpServletRequest request, HttpServletResponse response, String nickname) {
        Set<String> paquetes = logica.listarPaquetesDeEmpresa(nickname);
        request.setAttribute("paquetes", paquetes);
    }

    private void cargarTipoOferta(HttpServletRequest request, HttpServletResponse response) {
        Set<String> tipoPublicaciones = logica.listarTipoDePublicaciones();
        request.setAttribute("tipoPublicaciones", tipoPublicaciones);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipoOferta = request.getParameter("tipoOferta");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String horaInicio = request.getParameter("horaInicio");
        String horaFinal = request.getParameter("horaFinal");
        String departamentoStr = request.getParameter("departamento");
        String ciudad = request.getParameter("ciudad");
        String formaPago = request.getParameter("formaPago");
        String[] keywords = request.getParameterValues("keywords[]");
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));

        if (formaPago.equals("1") || formaPago.equals("0")) {
            formaPago = null;
        }

        float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));

        DepUY departamento = null;
        if (departamentoStr != null && !departamentoStr.isEmpty()) {
            try {
                departamento = DepUY.valueOf(departamentoStr);
            } catch (IllegalArgumentException e) {
            }
        }

        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

        try {
            logica.altaOfertaLaboral(
                nickname, tipoOferta, nombre, descripcion, new DTHorario(obtenerDTHora(horaInicio), obtenerDTHora(horaFinal)),
                remuneracion, ciudad, departamento, LocalDate.now(), keywordsSet, EstadoOfertaLaboral.INGRESADA, null, formaPago
            );
            response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/altaofertalaboral");
        }
    }

    private DTHora obtenerDTHora(String horaStr) {
        DTHora dtHora = null;

        String[] partes = horaStr.split(":");

        if (partes.length == 2) {
            try {
                int hora = Integer.parseInt(partes[0]);
                int minutos = Integer.parseInt(partes[1]);
                dtHora = new DTHora(hora, minutos);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir la hora y los minutos.");
            }
        } else {
            System.err.println("Formato de hora no válido.");
        }

        return dtHora;
    }
}
