package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTUsuario;

import java.io.IOException;
import java.util.Base64;

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
        String identificador = request.getParameter("identificador-input");
        String contraseña = request.getParameter("password-input");
        boolean credencialesValidadas;
        try {
        	credencialesValidadas = validarCredenciales(identificador, contraseña);
        } catch (Exception e) {
        	credencialesValidadas = false;
        }

        if (credencialesValidadas) {
            iniciarSesion(request, response, identificador);
        } else {
            request.setAttribute("mensajeError", "Inicio de sesión fallido. Verifique sus credenciales.");
            request.setAttribute("identificador", identificador);
            request.getRequestDispatcher("/WEB-INF/iniciarsesion/iniciarsesion.jsp").forward(request, response);
        }
    }
    
    private boolean validarCredenciales(String identificador, String contraseña) {
        return Fabrica.getInstance().getICtrlUsuario().validarCredenciales(identificador, contraseña);
    }

    private DTUsuario obtenerUsuario(String nickname) {
        return Fabrica.getInstance().getICtrlUsuario().obtenerDatosUsuario(nickname);
    }
    

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response, String identificador) throws IOException {
        HttpSession session = request.getSession();
        DTUsuario usuario = obtenerUsuario(identificador);
        session.setAttribute("nickname", usuario.getNickname());
        if( usuario instanceof DTPostulante) {
        	session.setAttribute("tipoUsuario", "Postulante");
        }
        if( usuario instanceof DTEmpresa) {
        	session.setAttribute("tipoUsuario", "Empresa");
        }
        

        byte[] imagenBytes = usuario.getImagen();
        if (imagenBytes != null) {
            String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
            session.setAttribute("imagen", imagenBase64);
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }
}

