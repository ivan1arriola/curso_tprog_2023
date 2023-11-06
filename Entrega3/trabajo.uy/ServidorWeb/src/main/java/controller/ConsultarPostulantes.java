package controller;

import enumeration.TipoUsuario;
import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.UsuarioBean;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.List;

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
        String nicknameEmpresa = (String) session.getAttribute("usuario");

        // si no es del tipo usuario = Empresa , redirigir a error
        if (TipoUsuario.Empresa != session.getAttribute("tipoUsuario")){

        } else {
            try {
                List<String> postulantes = logica.obtenerPostulantesDeOfertaString(nombreOferta, nicknameEmpresa);
                request.setAttribute("postulantes", postulantes);


            } catch (OfertaLaboralNoEncontrada_Exception e) {
                throw new RuntimeException(e);
            } catch (ExceptionUsuarioNoEncontrado_Exception e) {
                throw new RuntimeException(e);
            }


        }




    }


}
