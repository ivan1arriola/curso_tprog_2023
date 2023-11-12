package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.UsuarioBean;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import utils.FabricaWeb;

import java.io.IOException;
import enumeration.TipoUsuario;
import interfaces.ILogica;

@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IniciarSesion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("nickname") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	ILogica logica = FabricaWeb.getLogica();
        String identificador = request.getParameter("identificador-input");
        String contraseña = request.getParameter("password-input");
        
        try {
            boolean credencialesValidadas = logica.validarCredenciales(identificador, contraseña);

            if (credencialesValidadas) {
                iniciarSesion(request, response, identificador);
            } else {
                request.setAttribute("mensajeError", "Inicio de sesión fallido. Verifique sus credenciales.");
                request.setAttribute("identificador", identificador);
                request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
            }
        }  catch (ExceptionUsuarioNoEncontrado_Exception e){
            request.setAttribute("mensajeError", "Ocurrió un error al iniciar sesión. No se encontro el usuario " + identificador);
            request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Ocurrió un error al iniciar sesión." + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
        }
    }


    

    

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response, String identificador) throws Exception {
        HttpSession session = request.getSession();
        ILogica logica = FabricaWeb.getLogica();
        UsuarioBean usuario = logica.obtenerDatosUsuario(identificador);
        session.setAttribute("nickname", usuario.getNickname());

        TipoUsuario tipo = usuario.getTipo();
        if (tipo == null) throw new Exception("El tipo de usuario no es reconocido: " + usuario.getClass().getName());
        session.setAttribute("tipoUsuario", tipo);

        String imagen = usuario.getImagen();
        if (imagen != null) {
            session.setAttribute("imagen", imagen);
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }

}

