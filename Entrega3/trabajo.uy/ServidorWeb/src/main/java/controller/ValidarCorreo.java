package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Validar
 */
@WebServlet("/verificarCorreo")
public class ValidarCorreo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarCorreo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        // Aquí debes agregar la lógica para verificar si el correo electrónico ya está registrado
        boolean emailDisponible = true;

        if (emailDisponible) {
            response.setStatus(HttpServletResponse.SC_OK); // El correo electrónico está disponible
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT); // El correo electrónico ya está registrado
        }
    }

}
