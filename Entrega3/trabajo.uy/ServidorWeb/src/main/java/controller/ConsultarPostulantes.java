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

        //TODO: tiene que separar el caso de Seleccionar postulación a Oferta Laboral del de Consultar Postulaciones
        HttpSession session = request.getSession();
        String nombreOferta = request.getParameter("oferta");
        String nicknameEmpresa = (String) session.getAttribute("nickname");
        TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        // si no es del tipo usuario = Empresa , redirigir a error
        if (TipoUsuario.Empresa != tipoUsuario){

            request.setAttribute("nombreError", "No tiene permisos suficientes para visualizar esta pagina");
            request.setAttribute("mensajeError", "Solo la empresa publicadora puede visualizar las postulaciones");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orden = request.getParameter("orden");
        if (orden.startsWith("[") && orden.endsWith("]")) {
            orden = orden.substring(1, orden.length() - 1);
        }
        String[] ordenArray = orden.split(",");
        List<String> ordenPostulantes = new ArrayList<>(Arrays.asList(ordenArray));

        // Imprimir el orden o realizar otras acciones necesarias
        System.out.println("Orden recibido en el servlet: " + ordenPostulantes);

        ServidorService servsevice = new ServidorService();
        Servidor servidor = servsevice.getServidorPort();

        String nombreOferta = request.getParameter("oferta");
        OfertaLaboralBean ofertaLaboralBean = null;
        try {
            ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);
            String nicknameEmpresa = ofertaLaboralBean.getNicknameEmpresa();

            WrapperLista wrapperListaPostulantes = new WrapperLista();


            for (String nicknamePostulante : ordenPostulantes){
                wrapperListaPostulantes.getListaString().add(nicknamePostulante);
            }

            servidor.establecerPosiciones(nombreOferta, nicknameEmpresa, wrapperListaPostulantes);


        } catch (OfertaLaboralNoEncontrada_Exception e) {
            // Configura los atributos necesarios para la página de error
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError", "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");

            // Redirige a la página de error en caso de OfertaLaboralNoEncontrada_Exception
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return; // Asegúrate de que no continúe ejecutando el código después de la redirección.
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            // Configura los atributos necesarios para la página de error
            request.setAttribute("nombreError", "Usuario No Encontrado");
            request.setAttribute("mensajeError", "El usuario no fue encontrado. Por favor, verifique la información e inténtelo de nuevo.");

            // Redirige a la página de error en caso de ExceptionUsuarioNoEncontrado_Exception
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return; // Asegúrate de que no continúe ejecutando el código después de la redirección.
        }





        request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());
        request.setAttribute("postulantesFinal", ordenPostulantes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPostulaciones/resultado.jsp");
        dispatcher.forward(request, response);


    }


}
