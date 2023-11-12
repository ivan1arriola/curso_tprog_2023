package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import logica.servidor.TipoUsuarioNoValido_Exception;
import utils.FabricaWeb;
import org.json.JSONObject;

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
        PostulacionBean postulacion = null;
        try {
            postulacion = logica.obtenerDatosPostulacionW(nickname, nombreOferta);
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            throw new RuntimeException(e);
        } catch (TipoUsuarioNoValido_Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("postulacion",postulacion);

        OfertaLaboralBean ofertaLaboral = null;
        try {
            ofertaLaboral = logica.obtenerDatosOfertaLaboral(nombreOferta);
        } catch (OfertaLaboralNoEncontrada_Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("oferta", ofertaLaboral);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp");
        dispatcher.forward(request, response);
    }

    /* Para el AJAX*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "*"); // Permite todas las solicitudes desde cualquier origen (no recomendado para producción).
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");


        // Recuperar los datos enviados desde el cliente
        String nickname = request.getParameter("postulante");
        String nombreOferta = request.getParameter("oferta");

        UsuarioBean usuario = logica.obtenerDatosUsuario(nickname);
        try {
            PostulacionBean postulacion = logica.obtenerDatosPostulacionW(nickname, nombreOferta);

            // Preparar una respuesta en formato JSON
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("nombre", usuario.getNombre() + " " + usuario.getApellido());
            jsonResponse.put("curriculum", postulacion.getCVitae());
            jsonResponse.put("motivacion", postulacion.getMotivacion());
            jsonResponse.put("fecha", postulacion.getFecha());
            jsonResponse.put("video", postulacion.getVideo());

            // Configurar la respuesta HTTP
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Enviar la respuesta al cliente
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();



        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            // Manejar la excepción cuando el usuario no se encuentra
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Código de respuesta HTTP 404
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Usuario no encontrado");

            // Enviar la respuesta de error al cliente
            PrintWriter out = response.getWriter();
            out.print(errorResponse);
            out.flush();

        } catch (Exception e) {
            // Manejar otras excepciones personalizadas si es necesario
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código de respuesta HTTP 500
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Ocurrió un error inesperado");

            // Enviar la respuesta de error al cliente
            PrintWriter out = response.getWriter();
            out.print(errorResponse);
            out.flush();
        }





    }

}

