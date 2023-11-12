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
import logica.servidor.*;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            HttpSession session = request.getSession();
            String nombreOferta = request.getParameter("oferta");
            String nicknameEmpresa = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            ServidorService servsevice = new ServidorService();
            Servidor servidor = servsevice.getServidorPort();


            OfertaLaboralBean ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);


            // si no es del tipo usuario = Empresa , redirigir a error
            if (TipoUsuario.Empresa != tipoUsuario || !ofertaLaboralBean.getNicknameEmpresa().equals(nicknameEmpresa)){

                request.setAttribute("nombreError", "No tiene permisos suficientes para visualizar esta pagina");
                request.setAttribute("mensajeError", "Solo la empresa publicadora puede visualizar las postulaciones");
                request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
                return;

            } else {



                boolean estaFinalizada = logica.estaFinalizada(nombreOferta);
                boolean hayOrdenDefinido = servidor.hayOrdenDefinido(nombreOferta);

                List<String> nicknamePostulantes;
                if (hayOrdenDefinido){
                    // Obtengo la lista devuelta del servidor
                    nicknamePostulantes  = servidor.obtenerPosiciones(nombreOferta).getListaString();
                } else {
                   nicknamePostulantes = servidor.listarPostulantesOfertaLaboral(nombreOferta).getListaString();
                }

                request.setAttribute("estaFinalizada",estaFinalizada);
                request.setAttribute("hayOrdenDefinido", hayOrdenDefinido);
                request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());
                request.setAttribute("nicknamesPostulantes", nicknamePostulantes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPostulaciones/consultarPostulaciones.jsp");
                dispatcher.forward(request, response);
                return;





            }
        } catch (OfertaLaboralNoEncontrada_Exception e) {
            // Configura los atributos necesarios para la página de error
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError", "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        } catch (NoHayOrdenDefinidoDePostulantes_Exception e) {
            throw new RuntimeException(e);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);

    }


}

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
import logica.servidor.*;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            HttpSession session = request.getSession();
            String nombreOferta = request.getParameter("oferta");
            String nicknameEmpresa = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

            ServidorService servsevice = new ServidorService();
            Servidor servidor = servsevice.getServidorPort();


            OfertaLaboralBean ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);


            // si no es del tipo usuario = Empresa , redirigir a error
            if (TipoUsuario.Empresa != tipoUsuario || !ofertaLaboralBean.getNicknameEmpresa().equals(nicknameEmpresa)){

                request.setAttribute("nombreError", "No tiene permisos suficientes para visualizar esta pagina");
                request.setAttribute("mensajeError", "Solo la empresa publicadora puede visualizar las postulaciones");
                request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
                return;

            } else {



                boolean estaFinalizada = logica.estaFinalizada(nombreOferta);
                boolean hayOrdenDefinido = servidor.hayOrdenDefinido(nombreOferta);

                List<String> nicknamePostulantes;
                if (hayOrdenDefinido){
                    // Obtengo la lista devuelta del servidor
                    nicknamePostulantes  = servidor.obtenerPosiciones(nombreOferta).getListaString();
                } else {
                   nicknamePostulantes = servidor.listarPostulantesOfertaLaboral(nombreOferta).getListaString();
                }

                request.setAttribute("estaFinalizada",estaFinalizada);
                request.setAttribute("hayOrdenDefinido", hayOrdenDefinido);
                request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());
                request.setAttribute("nicknamesPostulantes", nicknamePostulantes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPostulaciones/consultarPostulaciones.jsp");
                dispatcher.forward(request, response);
                return;





            }
        } catch (OfertaLaboralNoEncontrada_Exception e) {
            // Configura los atributos necesarios para la página de error
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError", "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        } catch (NoHayOrdenDefinidoDePostulantes_Exception e) {
            throw new RuntimeException(e);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);

    }


}
