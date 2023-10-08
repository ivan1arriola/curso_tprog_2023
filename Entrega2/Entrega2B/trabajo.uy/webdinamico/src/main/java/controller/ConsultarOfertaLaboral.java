package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTOfertaExtendido;
import main.java.logica.interfaces.ICtrlOferta;
import auxiliar.OfertaLaboralBean;
import enumeration.TipoUsuario;

import java.io.IOException;

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
        ofertaLaboral.setEstado(dtOferta.getEstado());
        ofertaLaboral.setFechaDeAlta(dtOferta.getFechaDeAlta());
        ofertaLaboral.setHorario(dtOferta.getHorario());
        ofertaLaboral.setImagen(dtOferta.getImagen());
        ofertaLaboral.setRemuneracion(dtOferta.getRemuneracion());
        ofertaLaboral.setPostulaciones(dtOferta.getPostulaciones());

        return ofertaLaboral;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreOferta = request.getParameter("o");
        if (nombreOferta != null && !nombreOferta.isEmpty()) {

            HttpSession session = request.getSession(false);

            String nickname = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            try {
                if (nickname == null) nickname = "";
                if (tipoUsuario == null) tipoUsuario = TipoUsuario.Visitante;

                OfertaLaboralBean ofertaBean = cargarDatosIniciales(nombreOferta);

                if (tipoUsuario == TipoUsuario.Empresa) {
                    // Agregar lógica para empresas si es necesario
                }

                if (tipoUsuario == TipoUsuario.Postulante) {
                    // Agregar lógica para postulantes si es necesario
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Agregar lógica para manejar las solicitudes POST si es necesario
        doGet(request, response);
    }
}
