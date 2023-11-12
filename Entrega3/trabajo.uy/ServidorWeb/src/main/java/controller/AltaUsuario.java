package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import utils.FabricaWeb;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import interfaces.ILogica;

@WebServlet("/altausuario")
@MultipartConfig
public class AltaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private ILogica logica;

    public AltaUsuario() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/altaUsuario/altaUsuario.jsp").forward(request, response);
        }
    }
    
    private byte[] procesarImagen(Part imagenPart) throws IOException {
        if (imagenPart == null) {
            return null;
        }

        try (InputStream input = imagenPart.getInputStream()) {
            byte[] imagenBytes = input.readAllBytes();
            if (imagenBytes.length == 0) {
                return null;
            }
            return imagenBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String tipoUsuario = request.getParameter("tipo-usuario");
        
        
        Part imagenPart = request.getPart("imagen");
        
        byte[] imagenBytes  = procesarImagen(imagenPart);
        
        

        String descripcionEmpresa = null;
        String sitioWebEmpresa = null;
        String fechaNacimiento = null;
        String nacionalidad = null;

        try {
            boolean registroExitoso;

			if ("empresa".equals(tipoUsuario)) {
                descripcionEmpresa = request.getParameter("descripcion");
                sitioWebEmpresa = request.getParameter("sitio-web");
                logica.altaEmpresa(nickname, password, nombre, apellido, email, descripcionEmpresa, sitioWebEmpresa, imagenBytes);
                registroExitoso = true; 
            } else {
                fechaNacimiento = request.getParameter("fecha-nacimiento");
                nacionalidad = request.getParameter("nacionalidad");
                logica.altaPostulante(nickname, password, nombre, apellido, email, LocalDate.parse(fechaNacimiento), nacionalidad, imagenBytes);
                registroExitoso = true;
            }

            if (!registroExitoso) {
                manejarExcepcion(request, response, "El nickname y/o correo electrónico ya está registrado. Elija otro");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } catch (Exception e) {
            manejarExcepcion(request, response, "El correo electrónico ya está registrado. Elija otro");
        }
    }

    private void manejarExcepcion(HttpServletRequest request, HttpServletResponse response, String mensajeAlerta) throws ServletException, IOException {
        request.setAttribute("alert", mensajeAlerta);
        doGet(request, response);
    }
}
