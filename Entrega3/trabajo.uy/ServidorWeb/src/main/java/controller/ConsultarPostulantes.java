package controller;

import enumeration.TipoUsuario;
import interfaces.ILogica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import javabeans.UsuarioBean;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.List;

@WebServlet("/consultarpostulantes")
public class ConsultarPostulantes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarPostulantes() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // tiene que mostrar todos los postulantes de la pagina
        HttpSession session = request.getSession();
        String nombreOferta = request.getParameter("oferta");
        String nicknameEmpresa = (String) session.getAttribute("nickname");
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        // si no es del tipo usuario = Empresa , redirigir a error
        if (TipoUsuario.Empresa != tipoUsuario){
            //TODO: redirijir a error
        } else {
            try {
                List<String> postulantes = logica.obtenerPostulantesDeOfertaString(nombreOferta, nicknameEmpresa);
                request.setAttribute("postulantes", postulantes);

                OfertaLaboralBean ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);

                request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPostulaciones/consultarPostulaciones.jsp");
                dispatcher.forward(request, response);


            } catch (OfertaLaboralNoEncontrada_Exception e) {
                throw new RuntimeException(e);
            } catch (ExceptionUsuarioNoEncontrado_Exception e) {
                throw new RuntimeException(e);
            }


        }




    }


}
