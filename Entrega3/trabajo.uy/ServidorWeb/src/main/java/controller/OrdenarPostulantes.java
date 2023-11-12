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
import logica.servidor.*;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebServlet("/ordenarpostulantes")
public class OrdenarPostulantes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ILogica logica;
    private final Servidor servidor;

    public OrdenarPostulantes() {
        super();
        logica = FabricaWeb.getLogica();
        ServidorService servsevice = new ServidorService();
        servidor = servsevice.getServidorPort();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            String nombreOferta = request.getParameter("oferta");
            String nicknameEmpresa = (String) session.getAttribute("nickname");
            TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");

        try {

            OfertaLaboralBean ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);
            String nicknameEmpresaPublicadora = ofertaLaboralBean.getNicknameEmpresa();


            // si no es del tipo usuario = Empresa o no es la empresa publicadora, redirije a error
            if (TipoUsuario.Empresa != tipoUsuario || !nicknameEmpresaPublicadora.equals(nicknameEmpresa)) {

                request.setAttribute("nombreError", "No tiene permisos suficientes para visualizar esta pagina");
                request.setAttribute("mensajeError", "Solo la empresa publicadora puede visualizar las postulaciones");
                request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
                return;

            } else {
                boolean estaFinalizada = logica.estaFinalizada(nombreOferta);
                if (estaFinalizada){
                    // redirigir a consultar postulaciones
                    return;
                }

                boolean estaVigente = logica.estaVigenteOferta(nombreOferta);
                request.setAttribute("estaVigente", estaVigente);


                List<String> postulantes = servidor.obtenerPosiciones(nombreOferta).getListaString();
                request.setAttribute("postulantes", postulantes);


                request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());


                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ordenarPostulaciones/ordenarPostulaciones.jsp");
                dispatcher.forward(request, response);





            }
        } catch (OfertaLaboralNoEncontrada_Exception ex) {
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError",
                    "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return;
        } catch (NoHayOrdenDefinidoDePostulantes_Exception e) {

            seleccionarOrdenPrimeraVez(nombreOferta, tipoUsuario, nicknameEmpresa,request, response);
        }


    }

    private void seleccionarOrdenPrimeraVez(String nombreOferta, TipoUsuario tipoUsuario, String nicknameEmpresa, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            OfertaLaboralBean ofertaLaboralBean = logica.obtenerDatosOfertaLaboral(nombreOferta);
            String nicknameEmpresaPublicadora = ofertaLaboralBean.getNicknameEmpresa();
            // si no es del tipo usuario = Empresa o no es la empresa publicadora, redirije a error
            if (TipoUsuario.Empresa != tipoUsuario || !nicknameEmpresaPublicadora.equals(nicknameEmpresa)) {

                request.setAttribute("nombreError", "No tiene permisos suficientes para visualizar esta pagina");
                request.setAttribute("mensajeError", "Solo la empresa publicadora puede visualizar las postulaciones");
                request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
                return;

            } else {
                boolean estaFinalizada = logica.estaFinalizada(nombreOferta);
                if (estaFinalizada){
                    // redirigir a consultar postulaciones
                    return;
                }

                boolean estaVigente = logica.estaVigenteOferta(nombreOferta);
                request.setAttribute("estaVigente", estaVigente);


                List<String> nicknamesPostulantes = servidor.listarPostulantesOfertaLaboral(nombreOferta).getListaString();
                request.setAttribute("postulantes", nicknamesPostulantes);
                request.setAttribute("imagenOferta", ofertaLaboralBean.getImagen());


                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ordenarPostulaciones/ordenarPostulaciones.jsp");
                dispatcher.forward(request, response);


            }
        } catch (OfertaLaboralNoEncontrada_Exception | ServletException | IOException e) {
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError",
                    "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orden = request.getParameter("orden");
        if (orden.startsWith("[") && orden.endsWith("]")) {
            orden = orden.substring(1, orden.length() - 1);
        }
        String[] ordenArray = orden.split(",");
        List<String> ordenPostulantes = new ArrayList<>(Arrays.asList(ordenArray));

        // Imprimir el orden o realizar otras acciones necesarias
        System.out.println("Orden recibido en el servlet: " + ordenPostulantes);

        String nombreOferta = request.getParameter("oferta");

        try{
            WrapperLista wrapperListaPostulantes = new WrapperLista();

            for (String nicknamePostulante : ordenPostulantes){
                wrapperListaPostulantes.getListaString().add(nicknamePostulante);
            }

            servidor.establecerPosiciones(nombreOferta, wrapperListaPostulantes);

            //doGet(request,response);
            response.sendRedirect(request.getContextPath()+"/consultarofertalaboral?o=" + nombreOferta);
            return;


        } catch (OfertaLaboralNoEncontrada_Exception e) {
            request.setAttribute("nombreError", "Oferta Laboral No Encontrada");
            request.setAttribute("mensajeError",
                    "La oferta laboral no fue encontrada. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return;
        } catch (AsignarOrdenAOfertaFinalizada_Exception e) {
            request.setAttribute("nombreError", "Asignar Orden a Oferta Laboral ya Finalizada");
            request.setAttribute("mensajeError",
                    "No se puede asignar un ordena  una oferta laboral que ya finalizo");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            request.setAttribute("nombreError", "Usuario No Encontrado");
            request.setAttribute("mensajeError",
                    "El usuario no fue encontrado. Por favor, verifique la información e inténtelo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return;
        } catch (AsignarOrdenAOfertaNoVencida_Exception e) {
            request.setAttribute("nombreError", "Asignar orden a oferta no vencida");
            request.setAttribute("mensajeError",
                    "No se puede asignar un orden de postulantes a una oferta que no vencio");
            request.getRequestDispatcher("/WEB-INF/errores/errorException.jsp").forward(request, response);
            return;
        }
    }
}
