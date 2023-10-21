package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import utils.FabricaWeb;

import java.io.IOException;
import java.time.LocalDate;

import interfaces.ILogica;

@WebServlet("/altausuario")
@MultipartConfig
public class AltaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ILogica logica;

    public AltaUsuario() {
        super();
        logica = FabricaWeb.getInstance().getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
        }
    }

   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String tipoUsuario = request.getParameter("tipo-usuario");

        String descripcionEmpresa = null;
        String sitioWebEmpresa = null;
        String fechaNacimiento = null;
        String nacionalidad = null;

        try {
            boolean registroExitoso;

			if ("empresa".equals(tipoUsuario)) {
                descripcionEmpresa = request.getParameter("descripcion");
                sitioWebEmpresa = request.getParameter("sitio-web");
                logica.altaEmpresa(nickname, password, nombre, apellido, email, descripcionEmpresa, sitioWebEmpresa);
                registroExitoso = true; // se romperia antes de llegar aqui si fuera false
            } else {
                fechaNacimiento = request.getParameter("fecha-nacimiento");
                nacionalidad = request.getParameter("nacionalidad");
                logica.altaPostulante(nickname, password, nombre, apellido, email, LocalDate.parse(fechaNacimiento), nacionalidad);
                registroExitoso = true;
            }

            if (!registroExitoso) {
                manejarExcepcion(request, response, "El nickname y/o correo electrónico ya está registrado. Elija otro");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } catch (ExceptionUsuarioCorreoRepetido e) {
            manejarExcepcion(request, response, "El correo electrónico ya está registrado. Elija otro");
        } catch (ExceptionUsuarioNickYCorreoRepetidos e) {
            manejarExcepcion(request, response, "El nick y el correo electrónico ya están registrados. Elija otros");
        } catch (ExceptionUsuarioNickRepetido e) {
            manejarExcepcion(request, response, "El nickname ya está registrado. Elija otro");
        }
    }

    private void manejarExcepcion(HttpServletRequest request, HttpServletResponse response, String mensajeAlerta) throws ServletException, IOException {
        request.setAttribute("alert", mensajeAlerta);
        doGet(request, response);
    }
}
