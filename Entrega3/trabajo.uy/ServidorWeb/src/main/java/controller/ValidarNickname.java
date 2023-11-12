package controller;

import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FabricaWeb;

import java.io.IOException;

/**
 * Servlet implementation class Validar
 */
@WebServlet("/validarNickname")
public class ValidarNickname extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;
    public ValidarNickname() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException,  IOException {

        response.setHeader("Access-Control-Allow-Origin",  "*"); // Permite todas las solicitudes desde cualquier origen (no recomendado para producción).
        response.setHeader("Access-Control-Allow-Methods",  "GET,  POST,  PUT,  DELETE,  OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",  "Content-Type,  Authorization");


        String nickname = request.getParameter("nickname");

        // Aquí debes agregar la lógica para verificar si el correo electrónico ya está registrado
        boolean nicknameDisponible = logica.nicknameDisponible(nickname);

        if (nicknameDisponible) {
            response.setStatus(HttpServletResponse.SC_OK); // El nickname está disponible
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // El nickname ya está registrado
        }
    }

}
