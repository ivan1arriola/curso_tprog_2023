package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.FinalizarOfertaNoVencida_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;
import interfaces.ILogica;
import java.io.IOException;


@WebServlet("/finalizaroferta")
public class FinalizarOferta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public FinalizarOferta() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("nickname");

        String nombreOferta = request.getParameter("oferta");

        try {
            OfertaLaboralBean oferta = logica.obtenerDatosOfertaLaboral(nombreOferta);
            boolean esPropietario = oferta.getNicknameEmpresa().equals(nickname);
            if (esPropietario){
                logica.finalizarOferta(nombreOferta);
                response.sendRedirect("/consultaroferta?o=" + nombreOferta);

            } else {
                request.setAttribute("nombreError", "Acceso Denegado");
                request.setAttribute("mensajeError", "Solo el propietario puede modificar el estado");
                request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            }


        } catch (OfertaLaboralNoEncontrada_Exception e) {
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError", "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            request.setAttribute("nombreError", "Usuario No Encontrada");
            request.setAttribute("mensajeError", "La oferta laboral no esta asociada a ningun usuario X_X");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        } catch (FinalizarOfertaNoVencida_Exception e) {
            request.setAttribute("nombreError", "Oferta Laboral Aun Vigente");
            request.setAttribute("mensajeError", "La oferta laboral aun se encuentra vigente y no se puede finalizar");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        }


    }
}
