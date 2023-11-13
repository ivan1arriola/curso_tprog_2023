package controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

@WebServlet("/consultapostulacion")
public class ConsultaPostulacion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ILogica logica;

    public ConsultaPostulacion() {
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(jakarta.servlet.http.HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        FabricaWeb.getKeywordsLoader().cargarKeywords(request,  response);

        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

        UsuarioBean usuario = logica.obtenerDatosUsuario(nickname);

        String nombrePostulante = usuario.getNombre() + " " + usuario.getApellido();
        request.setAttribute("postulante",  nombrePostulante);

        String nombreOferta = request.getParameter("id");
        PostulacionBean postulacion = null;
        try {
            postulacion = logica.obtenerDatosPostulacionW(nickname,  nombreOferta);
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            throw new RuntimeException(e);
        } catch (TipoUsuarioNoValido_Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("postulacion", postulacion);

        OfertaLaboralBean ofertaLaboral = null;
        try {
            ofertaLaboral = logica.obtenerDatosOfertaLaboral(nombreOferta);
        } catch (OfertaLaboralNoEncontrada_Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("oferta",  ofertaLaboral);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultaPostulacion/consultaPostulacion.jsp");
        dispatcher.forward(request,  response);
    }

    /* Para el AJAX*/
    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {

        response.setHeader("Access-Control-Allow-Origin",  "*"); // Permite todas las solicitudes desde cualquier origen (no recomendado para producción).
        response.setHeader("Access-Control-Allow-Methods",  "GET,  POST,  PUT,  DELETE,  OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",  "Content-Type,  Authorization");


        // Recuperar los datos enviados desde el cliente
        String nickname = request.getParameter("postulante");
        String nombreOferta = request.getParameter("oferta");

        UsuarioBean usuario = logica.obtenerDatosUsuario(nickname);
        try {
            PostulacionBean postulacion = logica.obtenerDatosPostulacionW(nickname,  nombreOferta);
            
            Gson gson = new Gson();

         // Crear un objeto JsonObject
         JsonObject jsonResponse = new JsonObject();
         jsonResponse.addProperty("nombre", usuario.getNombre() + " " + usuario.getApellido());
         jsonResponse.addProperty("curriculum", postulacion.getCVitae());
         jsonResponse.addProperty("motivacion", postulacion.getMotivacion());
         jsonResponse.addProperty("fecha", postulacion.getFecha().toString());
         jsonResponse.addProperty("video", postulacion.getVideo());

         // Convertir JsonObject a cadena JSON
         String jsonResult = gson.toJson(jsonResponse);

         // Imprimir o hacer algo con la cadena JSON resultante
         System.out.println(jsonResult);
            // Configurar la respuesta HTTP
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Enviar la respuesta al cliente
            PrintWriter out = response.getWriter();
            out.print(jsonResponse);
            out.flush();



        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            // Manejar la excepción cuando el usuario no se encuentra
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Usuario no encontrado");

            response.getWriter().print(errorResponse.toString());
            response.getWriter().flush();

        } catch (Exception e) {
            // Manejar otras excepciones personalizadas si es necesario
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "Ocurrió un error inesperado");

            response.getWriter().print(errorResponse.toString());
            response.getWriter().flush();
        }





    }

}

